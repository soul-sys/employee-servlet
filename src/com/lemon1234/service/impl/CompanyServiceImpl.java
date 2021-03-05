package com.lemon1234.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lemon1234.entity.Company;
import com.lemon1234.service.CompanyService;
import com.lemon1234.util.DBUtil;
import com.lemon1234.util.StringUtil;

public class CompanyServiceImpl implements CompanyService {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public JsonArray list(String name, Integer page, String limit) throws Exception {
		Connection conn = DBUtil.getConnection();
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("SELECT ");
		sBuffer.append("	c.id, ");
		sBuffer.append("	c.companyName, ");
		sBuffer.append("	c.introduce, ");
		sBuffer.append("	c.createDt, ");
		sBuffer.append("	(select count(1) from t_employ e where e.companyId = c.id) as count ");
		sBuffer.append("FROM t_company c ");
		
		if(StringUtil.isNotEmpty(name)) {
			sBuffer.append(" WHERE c.companyName like '%" + name + "%'");
		}
		sBuffer.append(" ORDER BY c.createDt DESC ");
		sBuffer.append(" LIMIT " + page + ", " + limit);

		PreparedStatement pstmt = conn.prepareStatement(sBuffer.toString());
		ResultSet rs = pstmt.executeQuery();
		
		JsonArray jsonArray = new JsonArray();
		
		while(rs.next()) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("id", rs.getInt("id"));
			jsonObject.addProperty("companyName", rs.getString("companyName"));
			jsonObject.addProperty("introduce", rs.getString("introduce"));
			jsonObject.addProperty("createDt", sdf.format(rs.getDate("createDt")));
			jsonObject.addProperty("count", rs.getInt("count"));
			
			jsonArray.add(jsonObject);
		}
		
		DBUtil.close(conn, pstmt);
		
		return jsonArray;
	}

	@Override
	public int delete(String id) throws Exception {
		Connection conn = DBUtil.getConnection();
		
		String sql = "DELETE FROM t_company WHERE id = " + id;
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int count = pstmt.executeUpdate();
		
		DBUtil.close(conn, pstmt);
		return count;
	}

	@Override
	public int add(String companyName, String introduce) throws Exception {
		Connection conn = DBUtil.getConnection();
		
		String sql = "INSERT INTO t_company(id, companyName, introduce, createDt) VALUES(null, ?,?, NOW())";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, companyName);
		pstmt.setString(2, introduce);
		
		int count = pstmt.executeUpdate();
		
		DBUtil.close(conn, pstmt);
		return count;
	}

	@Override
	public Company detail(String employ) throws Exception {
		Connection conn = DBUtil.getConnection();
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("SELECT ");
		sBuffer.append("	c.id, ");
		sBuffer.append("	c.companyName, ");
		sBuffer.append("	c.introduce, ");
		sBuffer.append("	c.createDt ");
		sBuffer.append("FROM t_company c ");
		sBuffer.append("LEFT JOIN t_employ e ON e.companyId = c.id ");
		sBuffer.append("WHERE e.phoneNum = ?");
		
		PreparedStatement pstmt = conn.prepareStatement(sBuffer.toString());
		pstmt.setString(1, employ);
		
		ResultSet rs = pstmt.executeQuery();
		
		Company company = null;
		if(rs.next()) {
			company = new Company();
			company.setCompanyName(rs.getString("companyName"));
			company.setIntroduce(rs.getString("introduce"));
			company.setCreateDt(rs.getDate("createDt"));
			company.setId(rs.getInt("id"));
		}
		
		DBUtil.close(conn, pstmt);
		
		return company;
	}
	
}