package com.lemon1234.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lemon1234.entity.Employ;
import com.lemon1234.service.EmployService;
import com.lemon1234.util.DBUtil;

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

}
