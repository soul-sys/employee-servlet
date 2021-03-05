package com.lemon1234.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import com.google.gson.JsonObject;
import com.lemon1234.util.PrintUtil;
import com.lemon1234.util.StringUtil;

public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File("/"));
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		// 通过 new ServletRequestContext 去获取 RequestContext
		List<FileItem> list = upload.parseRequest(new ServletRequestContext(request));
		
		FileItem file = list.get(0);
		String oldFileName = file.getName();
		String fileName = StringUtil.getFileName() + oldFileName.substring(oldFileName.lastIndexOf("."));
		// 这里上传到 C 盘 image 下面 
		// 配置一下 tomcat server.xml <Context docBase="D:\image" path="/image" reloadable="true"/>
		FileUtils.copyInputStreamToFile(file.getInputStream(), new File("C://image//" + fileName));
		
		JsonObject jsonObject = new JsonObject();
		
		jsonObject.addProperty("code", 0);
		jsonObject.addProperty("msg", "上传成功");
		JsonObject data = new JsonObject();
		data.addProperty("title", fileName);
		data.addProperty("src", "/image/" + fileName);
		jsonObject.add("data",data);
		
		try {
			PrintUtil.write(jsonObject.toString(), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
