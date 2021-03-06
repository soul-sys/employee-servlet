package com.lemon1234.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lemon1234.entity.Company;
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
			if(type.equals("delete")) {
				try {
					this.delete(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(type.equals("detail")) {
				try {
					this.detail(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// 获取单个公司
	private void detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String employ = request.getParameter("employ");
		
		JsonObject jsonObject = new JsonObject();
		
		if(StringUtil.isEmpty(employ)) {
			jsonObject.addProperty("code", 500);
			jsonObject.addProperty("msg", "无效用户");
		} else {
			Company company = service.detail(employ);
			if(company != null) {
				jsonObject.addProperty("code", 200);
				jsonObject.addProperty("data", new Gson().toJson(company));
			} else {
				jsonObject.addProperty("code", 500);
				jsonObject.addProperty("msg", "错误请求");
			}
		}
		PrintUtil.write(jsonObject.toString(), response);
	}
	
	// 删除
	private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		
		JsonObject jsonObject = new JsonObject();
		
		if(StringUtil.isEmpty(id)) {
			jsonObject.addProperty("code", 500);
			jsonObject.addProperty("msg", "无效id");
		} else {
			int count = service.delete(id);
			if(count > 0) {
				jsonObject.addProperty("code", 200);
			} else {
				jsonObject.addProperty("code", 500);
				jsonObject.addProperty("msg", "删除失败");
			}
		}
		
		PrintUtil.write(jsonObject.toString(), response);
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
		jsonObject.addProperty("code", 0);
		
		JsonArray jsonArray = service.list(name, PageUtil.getPageStart(page, limit), limit);
		
		jsonObject.add("data", jsonArray);
		jsonObject.addProperty("count", jsonArray.size());
		
		PrintUtil.write(jsonObject.toString(), response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if(StringUtil.isEmpty(type)) {
			super.doGet(request, response);
		} else {
			if(type.equals("add")) {
				try {
					this.add(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String companyName = request.getParameter("companyName");
		String introduce = request.getParameter("introduce");
		
		JsonObject jsonObject = new JsonObject();
		
		if(StringUtil.isEmpty(companyName)) {
			jsonObject.addProperty("code", 500);
			jsonObject.addProperty("msg", "请输入公司名称");
			PrintUtil.write(jsonObject.toString(), response);
			return;
		}
		if(introduce.length() > 300) {
			jsonObject.addProperty("code", 500);
			jsonObject.addProperty("msg", "简介不能超过300个字哦~");
			PrintUtil.write(jsonObject.toString(), response);
			return;
		}
		
		int count = service.add(companyName, introduce);
		if(count > 0) {
			jsonObject.addProperty("code", 200);
		} else {
			jsonObject.addProperty("code", 500);
			jsonObject.addProperty("msg", "删除失败");
		}
		PrintUtil.write(jsonObject.toString(), response);
	}
	
}
