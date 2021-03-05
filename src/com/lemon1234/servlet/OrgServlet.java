package com.lemon1234.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lemon1234.service.OrgService;
import com.lemon1234.service.impl.OrgServiceImpl;
import com.lemon1234.util.PageUtil;
import com.lemon1234.util.PrintUtil;
import com.lemon1234.util.StringUtil;

public class OrgServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private OrgService service = new OrgServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			if(type.equals("orgs")) {
				try {
					this.orgs(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void orgs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String employ = request.getParameter("employ");
		
		JsonObject jsonObject = new JsonObject();
		
		if(StringUtil.isEmpty(employ)) {
			jsonObject.addProperty("msg", "数据不正确");
			jsonObject.addProperty("code", 500);
		} else {
			JsonArray jsonArray = service.list(employ, 0, null);
			jsonObject.addProperty("code", 200);
			jsonObject.add("data", jsonArray);
		}
		PrintUtil.write(jsonObject.toString(), response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String org = request.getParameter("org");
		JsonObject jsonObject = new JsonObject();
		
		if(StringUtil.isEmpty("org")) {
			jsonObject.addProperty("msg", "错误请求");
			jsonObject.addProperty("code", 500);
		} else {
			int count = service.delete(org);
			if(count > 0) {
				jsonObject.addProperty("code", 200);
			} else {
				jsonObject.addProperty("code", 500);
				jsonObject.addProperty("msg", "删除失败");
			}
		}
		PrintUtil.write(jsonObject.toString(), response);
	}
	
	// 查询公司所有的部门
	private void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String phoneNum = request.getParameter("employ");
		
		JsonObject jsonObject = new JsonObject();
		
		if(StringUtil.isEmpty(phoneNum)) {
			jsonObject.addProperty("msg", "错误请求");
			jsonObject.addProperty("code", 500);
		} else {
			String param1 = request.getParameter("page");
			Integer page = 0;
			if(StringUtil.isNotEmpty(param1)) {
				page = Integer.parseInt(param1); 
			}
			String limit = request.getParameter("limit");
			if(StringUtil.isEmpty(limit)) {
				limit = "15";
			}
			
			JsonArray jsonArray = service.list(phoneNum, PageUtil.getPageStart(page, limit), limit);
			
			jsonObject.addProperty("code", 0);
			jsonObject.add("data", jsonArray);
			jsonObject.addProperty("count", jsonArray.size());
		}
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
	
	// 添加部门
	private void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orgName = request.getParameter("orgName");
		String id = request.getParameter("id");
		
		JsonObject jsonObject = new JsonObject();
		
		if(StringUtil.isEmpty(orgName)) {
			jsonObject.addProperty("msg", "错误请求");
			jsonObject.addProperty("code", 500);
		} else {
			int count = service.add(orgName, id);
			if(count > 0) {
				jsonObject.addProperty("code", 200);
			} else {
				jsonObject.addProperty("code", 500);
				jsonObject.addProperty("msg", "删除失败");
			}
		}
		PrintUtil.write(jsonObject.toString(), response);
	}
}
