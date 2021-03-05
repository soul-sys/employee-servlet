package com.lemon1234.service;

import com.google.gson.JsonArray;
import com.lemon1234.entity.Employ;

public interface EmployService {

	Employ login(String username) throws Exception;
	
	JsonArray list(String employ, Integer page, String limit, String name) throws Exception;

	int delete(String id) throws Exception;
	
	int add(Employ employ) throws Exception;
}
