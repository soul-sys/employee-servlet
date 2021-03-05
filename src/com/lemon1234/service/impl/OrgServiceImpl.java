package com.lemon1234.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lemon1234.service.OrgService;
import com.lemon1234.util.DBUtil;

public class OrgServiceImpl implements OrgService {

	@Override
	public JsonArray list(String phoneNum, Integer page, String limit) throws Exception {
		Connection conn = DBUtil.getConnection();
		
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("SELECT ");
		sBuffer.append("	o.id, ");
		sBuffer.append("	o.orgName ");
		sBuffer.append("FROM t_org o ");
		sBuffer.append("LEFT JOIN t_company c ON c.id = o.companyId ");
		sBuffer.append("LEFT JOIN t_employ e ON e.companyId = c.id ");
		sBuffer.append("WHERE e.phoneNum = ?");
		if(limit != null) {
			sBuffer.append(" LIMIT " + page + ", " + limit);	
		}
		
		PreparedStatement pstmt = conn.prepareStatement(sBuffer.toString());
		pstmt.setString(1, phoneNum);
		
		ResultSet rs = pstmt.executeQuery();
		JsonArray jsonArray = new JsonArray();
		
		while(rs.next()) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("id", rs.getInt("id"));
			jsonObject.addProperty("orgName", rs.getString("orgName"));
			jsonArray.add(jsonObject);
		}
		
		DBUtil.close(conn, pstmt);
		return jsonArray;
	}

	@Override
	public int delete(String org) throws Exception {
		Connection conn = DBUtil.getConnection();
		
		String sql = "DELETE FROM t_org WHERE id = " + org;
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int count = pstmt.executeUpdate();
		
		DBUtil.close(conn, pstmt);
		return count;
	}

	@Override
	public int add(String orgName, String id) throws Exception {
		Connection conn = DBUtil.getConnection();
		
		String sql = "INSERT INTO t_org(id, orgName, companyId, createDt) VALUES(null, ?, ?, NOW())";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, orgName);
		pstmt.setInt(2, Integer.parseInt(id));
		
		int count = pstmt.executeUpdate();
		
		DBUtil.close(conn, pstmt);
		
		return count;
	}

}
