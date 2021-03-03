package com.lemon1234.util;

public class PageUtil {

	public static int getPageStart(Integer page, String limit) {
		return (page-1) * Integer.parseInt(limit);
	}
}
