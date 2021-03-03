package com.lemon1234.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lemon1234.entity.Admin;
import com.lemon1234.service.AdminService;
import com.lemon1234.util.DBUtil;

public class AdminServiceImpl implements AdminService {

	@Override
	public Admin login(String username) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "SELECT * FROM t_admin WHERE username = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, username);
		
		ResultSet rs = pstmt.executeQuery();
		Admin admin = null;
		while(rs.next()) {
			admin = new Admin();
			admin.setId(rs.getInt("id"));
			admin.setUsername(rs.getString("username"));
			admin.setPassword(rs.getString("password"));
			admin.setName(rs.getString("name"));
			admin.setImg(rs.getString("img"));
		}
		
		DBUtil.close(conn, pstmt);
		
		return admin;
	}

}
