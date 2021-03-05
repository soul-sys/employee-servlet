package com.lemon1234.service;

import com.google.gson.JsonArray;
import com.lemon1234.entity.Company;

public interface CompanyService {

	JsonArray list(String name, Integer page, String limit) throws Exception;
	
	int delete(String id) throws Exception;
	
	int add(String companyName, String introduce) throws Exception;

	Company detail(String employ) throws Exception;
}
