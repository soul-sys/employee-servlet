package com.lemon1234.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lemon1234.service.CompanyService;
import com.lemon1234.service.impl.CompanyServiceImpl;
import com.lemon1234.util.PageUtil;
import com.lemon1234.util.PrintUtil;
import com.lemon1234.util.StringUtil;

public class CompanyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private CompanyService service = new CompanyServiceImpl();

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
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			if(type.equals("list")) {
				try {
					this.list(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// 查看所有的用户
	private void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 公司名称
		String name = request.getParameter("name");
		
		String param1 = request.getParameter("page");
		Integer page = 0;
		if(StringUtil.isNotEmpty(param1)) {
			page = Integer.parseInt(param1); 
		}
		String limit = request.getParameter("limit");
		if(StringUtil.isEmpty(limit)) {
			limit = "15";
		}
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", 200);
		
		JsonArray jsonArray = service.list(name, PageUtil.getPageStart(page, limit), limit);
		
		jsonObject.add("data", jsonArray);
		
		PrintUtil.write(jsonObject.toString(), response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}
	
}
