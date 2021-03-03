package com.lemon1234.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.lemon1234.util.PrintUtil;
import com.lemon1234.util.StringUtil;

public class CompanyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取 type
		String type = request.getParameter("type");
		JsonObject jsonObject = new JsonObject();
		if(StringUtil.isEmpty(type)) {
			jsonObject.addProperty("msg", "错误请求");
			jsonObject.addProperty("code", 500);
			try {
				PrintUtil.write(jsonObject.toString(), response);
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}
	
}
