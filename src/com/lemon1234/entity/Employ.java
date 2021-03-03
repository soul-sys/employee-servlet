package com.lemon1234.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 员工信息
 */
public class Employ {

	private int id;
	
	// 员工姓名
	private String name;
	
	// 年龄
	private int age;
	
	// 性别
	private int sex;
	
	// 手机号
	private String phoneNum;
	
	// 员工邮箱
	private String email;
	
	// 员工照片
	private String photo;
	
	// 身份证号码
	private String pidNo;
	
	// 员工所在部门
	private int orgId;
	
	// 每天工资
	private BigDecimal salary;
	
	// 公司 id
	private int companyId;
	
	// 管理权限 0 没有权限 1有权限
	private int role;
	
	// 员工状态：0在职、1离职
	private int state;
	
	// 入职日期
	private Date contractStartTime;
	
	// 离职日期
	private Date contractLeaveTime;
	
	// 离职原因
	private String contractLeaveCause;
	
	// 密码
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPidNo() {
		return pidNo;
	}

	public void setPidNo(String pidNo) {
		this.pidNo = pidNo;
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getContractStartTime() {
		return contractStartTime;
	}

	public void setContractStartTime(Date contractStartTime) {
		this.contractStartTime = contractStartTime;
	}

	public Date getContractLeaveTime() {
		return contractLeaveTime;
	}

	public void setContractLeaveTime(Date contractLeaveTime) {
		this.contractLeaveTime = contractLeaveTime;
	}

	public String getContractLeaveCause() {
		return contractLeaveCause;
	}

	public void setContractLeaveCause(String contractLeaveCause) {
		this.contractLeaveCause = contractLeaveCause;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
