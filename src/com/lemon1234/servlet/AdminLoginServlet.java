package com.lemon1234.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lemon1234.entity.Admin;
import com.lemon1234.service.AdminService;
import com.lemon1234.service.impl.AdminServiceImpl;
import com.lemon1234.util.PrintUtil;

/**
 * 登录 Servlet
 */
public class AdminLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private AdminService service = new AdminServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		JsonObject jsonObject = new JsonObject();
		
		try {
			Admin admin = service.login(username);
			if(admin != null) {
				if(!admin.getPassword().equals(password)) {
					// 密码错误
					jsonObject.addProperty("msg", "密码错误");
					jsonObject.addProperty("code", 500);
				} else {
					// 返回 ok
					jsonObject.addProperty("code", 200);
					// 需要置空密码
					admin.setPassword("");
					jsonObject.addProperty("info", new Gson().toJson(admin));
				}
			} else {
				// 查无此人
				jsonObject.addProperty("msg", "查无此人");
				jsonObject.addProperty("code", 500);
			}

			PrintUtil.write(jsonObject.toString(), response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
