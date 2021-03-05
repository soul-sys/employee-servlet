package com.lemon1234.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lemon1234.entity.Employ;
import com.lemon1234.service.EmployService;
import com.lemon1234.service.impl.EmployServiceImpl;
import com.lemon1234.util.PageUtil;
import com.lemon1234.util.PasswordUtil;
import com.lemon1234.util.PrintUtil;
import com.lemon1234.util.StringUtil;

public class EmployServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private EmployService service = new EmployServiceImpl();

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
			if(type.equals("sexEcharts")) {
				try {
					this.sexEcharts(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void sexEcharts(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String companyId = request.getParameter("companyId");
		JsonObject jsonObject = new JsonObject();
		if(StringUtil.isEmpty(companyId)) {
			jsonObject.addProperty("code", 500);
			jsonObject.addProperty("msg", "无效请求");
		} else {
			Map<Integer, Integer> result = service.sexEcharts(Integer.parseInt(companyId));
			// 未知
			Integer x = result.get(0) == null ? 0 : result.get(0);
			// woman
			Integer w = result.get(2) == null ? 0 : result.get(2);
			// man
			Integer m = result.get(1) == null ? 0 : result.get(1);
			
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("sex", new Gson().toJson(new Integer[] {1, 2, 0}));
			jsonObject.addProperty("count", new Gson().toJson(new Integer[] {m, w, x}));
		}
		PrintUtil.write(jsonObject.toString(), response);
	}

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

	private void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String employ = request.getParameter("employ");
		String name = request.getParameter("name");
		
		JsonObject jsonObject = new JsonObject();
		
		if(StringUtil.isEmpty(employ)) {
			jsonObject.addProperty("code", 500);
			jsonObject.addProperty("msg", "无效用户");
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
			
			JsonArray jsonArray = service.list(employ, PageUtil.getPageStart(page, limit), limit, name);
			jsonObject.addProperty("count", jsonArray.size());
			jsonObject.addProperty("code", 0);
			jsonObject.add("data", jsonArray);
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

	private void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String age =request.getParameter("age");
		String sex = request.getParameter("sex");
		String phoneNum = request.getParameter("phoneNum");
		String email = request.getParameter("email");
		String pidNo = request.getParameter("pidNo");
		String orgId = request.getParameter("orgId");
		String role = request.getParameter("role");
		String photo = request.getParameter("photo");
		String companyId = request.getParameter("companyId");
		String salary = request.getParameter("salary");
		
		Employ employ = new Employ();
		employ.setName(name);
		employ.setAge(Integer.parseInt(age));
		employ.setSex(Integer.parseInt(sex));
		employ.setPhoneNum(phoneNum);
		employ.setEmail(email);
		employ.setPidNo(pidNo);
		employ.setOrgId(Integer.parseInt(orgId));
		employ.setRole(Integer.parseInt(role));
		employ.setPhoto(photo);
		employ.setCompanyId(Integer.parseInt(companyId));
		
		employ.setPassword(PasswordUtil.randomCode());
		employ.setState(0);
		employ.setSalary(new BigDecimal(salary));
		
		int count = service.add(employ);
		
		JsonObject jsonObject = new JsonObject();
		
		if(count > 0) {
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
