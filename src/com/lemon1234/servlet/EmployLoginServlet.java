package com.lemon1234.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.lemon1234.entity.Employ;
import com.lemon1234.service.EmployService;
import com.lemon1234.service.impl.EmployServiceImpl;
import com.lemon1234.util.PrintUtil;

public class EmployLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private EmployService service = new EmployServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 这个其实是手机号
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		JsonObject jsonObject = new JsonObject();
		
		try {
			Employ employ = service.login(username);
			if(employ != null && employ.getPassword() != null) {
				if(employ.getState() == 1) {
					jsonObject.addProperty("msg", "抱歉，员工已离职，无法登录");
					jsonObject.addProperty("code", 500);
				}
				if(!employ.getPassword().equals(password)) {
					// 密码错误
					jsonObject.addProperty("msg", "密码错误");
					jsonObject.addProperty("code", 500);
				} else {
					jsonObject.addProperty("code", 200);
				}
			} else {
				jsonObject.addProperty("msg", "查无此人");
				jsonObject.addProperty("code", 500);
			}
			
			request.getSession().setAttribute(employ.getPhoneNum(), employ);
			PrintUtil.write(jsonObject.toString(), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
