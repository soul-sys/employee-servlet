package com.lemon1234.service.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lemon1234.entity.Employ;
import com.lemon1234.service.EmployService;
import com.lemon1234.util.DBUtil;
import com.lemon1234.util.StringUtil;

public class EmployServiceImpl implements EmployService {

	@Override
	public Employ login(String username) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "SELECT * FROM t_employ WHERE phoneNum = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, username);
		
		ResultSet rs = pstmt.executeQuery();
		Employ employ = null;
		while(rs.next()) {
			employ = new Employ();
			employ.setId(rs.getInt("id"));
			employ.setName(rs.getString("name"));
			employ.setAge(rs.getInt("age"));
			employ.setSex(rs.getInt("sex"));
			employ.setPhoneNum(rs.getString("phoneNum"));
			employ.setEmail(rs.getString("email"));
			employ.setPhoto(rs.getString("photo"));
			employ.setPidNo(rs.getString("pidNo"));
			employ.setOrgId(rs.getInt("orgId"));
			employ.setSalary(rs.getBigDecimal("salary"));
			employ.setCompanyId(rs.getInt("companyId"));
			employ.setRole(rs.getInt("role"));
			employ.setState(rs.getInt("state"));
			employ.setContractStartTime(rs.getDate("contractStartTime"));
			employ.setContractLeaveTime(rs.getDate("contractLeaveTime"));
			employ.setContractLeaveCause(rs.getString("contractLeaveCause"));
			employ.setPassword(rs.getString("password"));
		}
		
		return employ;
	}

	@Override
	public JsonArray list(String employ, Integer page, String limit, String name) throws Exception {
		Connection conn = DBUtil.getConnection();
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("SELECT ");
		sBuffer.append("	e.id, ");
		sBuffer.append("	e.name, ");
		sBuffer.append("	e.age, ");
		sBuffer.append("	e.phoneNum, ");
		sBuffer.append("	e.email, ");
		sBuffer.append("	e.sex, ");
		sBuffer.append("	o.orgName ");
		sBuffer.append("FROM t_employ e ");
		sBuffer.append("LEFT JOIN t_org o ON o.id = e.orgId");
		sBuffer.append("WHERE e.id != (SELECT id FROM t_employ e1 WHERE e1.phoneNum = ?) ");
		sBuffer.append("AND e.companyId = (SELECT companyId FROM t_employ e2 WHERE e2.phoneNum = ?) ");
		if(StringUtil.isNotEmpty(name)) {
			sBuffer.append("AND e.name like '%" + name + "%' ");
		}
		sBuffer.append(" LIMIT " + page + ", " + limit);
		
		PreparedStatement pstmt = conn.prepareStatement(sBuffer.toString());
		pstmt.setString(1, employ);
		pstmt.setString(2, employ);
		ResultSet rs = pstmt.executeQuery();
		
		JsonArray jsonArray = new JsonArray();
		
		while(rs.next()) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("id", rs.getInt("id"));
			jsonObject.addProperty("name", rs.getString("name"));
			jsonObject.addProperty("age", rs.getInt("age"));
			jsonObject.addProperty("phoneNum", rs.getString("phoneNum"));
			jsonObject.addProperty("email", rs.getString("email"));
			jsonObject.addProperty("sex", rs.getInt("sex"));
			jsonObject.addProperty("orgName", rs.getString("orgName"));
			
			jsonArray.add(jsonObject);
		}
		
		DBUtil.close(conn, pstmt);
		
		return jsonArray;
	}

	@Override
	public int delete(String id) throws Exception {
		Connection conn = DBUtil.getConnection();
		
		String sql = "DELETE FROM t_employ WHERE id = " + id;
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int count = pstmt.executeUpdate();
		
		DBUtil.close(conn, pstmt);
		return count;
	}

	@Override
	public int add(Employ employ) throws Exception {
		Connection conn = DBUtil.getConnection();
		
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("INSERT INTO t_employ(id, name, age, sex, phoneNum, email, photo, pidNo, orgId, salary, companyId, role, state, contractStartTime, password) ");
		sBuffer.append("VALUES(null, ?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
		PreparedStatement pstmt = conn.prepareStatement(sBuffer.toString());
		pstmt.setString(1, employ.getName());
		pstmt.setInt(2, employ.getAge());
		pstmt.setInt(3, employ.getSex());
		pstmt.setString(4, employ.getPhoneNum());
		pstmt.setString(5, employ.getEmail());
		pstmt.setString(6, employ.getPhoto());
		pstmt.setString(7, employ.getPidNo());
		pstmt.setInt(8, employ.getOrgId());
		pstmt.setBigDecimal(9, employ.getSalary());
		pstmt.setInt(10, employ.getCompanyId());
		pstmt.setInt(11, employ.getRole());
		pstmt.setInt(12, employ.getState());
		pstmt.setDate(13, new Date(System.currentTimeMillis()));
		pstmt.setString(14, employ.getPassword());
		
		int count = pstmt.executeUpdate();
		DBUtil.close(conn, pstmt);
		
		return count;
	}

}
