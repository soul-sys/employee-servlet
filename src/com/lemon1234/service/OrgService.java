package com.lemon1234.service;

import com.google.gson.JsonArray;

public interface OrgService {

	JsonArray list(String phoneNum, Integer page, String limit) throws Exception;

	int delete(String org) throws Exception;

	int add(String orgName, String id) throws Exception;
}
