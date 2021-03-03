package com.lemon1234.service;

import com.google.gson.JsonArray;

public interface CompanyService {

	JsonArray list(String name, Integer page, String limit) throws Exception;
}
