package com.lemon1234.entity;

import java.util.Date;

/**
 * 公司
 */
public class Company {

	private int id;
	
	// 公司名称
	private String companyName;
	
	// 公司介绍
	private String introduce;
	
	// 创建日期
	private Date createDt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	
}
