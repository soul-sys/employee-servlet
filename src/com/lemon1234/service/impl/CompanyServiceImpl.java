package com.lemon1234.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
		
		if(rs.next()) {
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

}
