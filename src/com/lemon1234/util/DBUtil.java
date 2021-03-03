package com.lemon1234.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * 数据库连接
 */
public class DBUtil {

	private static Connection connection;
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String url = "jdbc:mysql://localhost:3306/employee?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
		String user = "root";
		String password = "123456";
		
		connection = DriverManager.getConnection(url, user, password);
		
		return connection;
	}
	
	public static void close(Connection connection, PreparedStatement pstmt) throws Exception {
		if(connection != null) {
			connection.close();
		}
		if(pstmt != null) {
			pstmt.close();
		}
	}
}
