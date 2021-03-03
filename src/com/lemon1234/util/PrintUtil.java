package com.lemon1234.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class PrintUtil {

	public static void write(String msg, HttpServletResponse response) throws Exception {
		// 设置返回值类型，这里我们使用 json 格式（后期我们会介绍 json）
		response.setContentType("application/json; charset=utf-8");
		// 创建输出流
		PrintWriter out = response.getWriter();
		
		out.write(msg);
		
		out.flush();
        out.close();
	}
}
