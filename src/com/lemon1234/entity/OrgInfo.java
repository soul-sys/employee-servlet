package com.lemon1234.entity;

import java.util.Date;

/**
 * 部门
 */
public class OrgInfo {

	private int id;

	private String orgName;
	
	// 公司id
	private int companyId;
	
	private Date createDt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	
}
