package com.lemon1234.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 工资表
 */
public class Salary {

	private int id;
	
	// 员工id
	private int employId;
	
	// 发薪年
	private int year;
	
	// 发薪月份
	private int month;
	
	// 支付日期
	private Date panDt;
	
	// 发放金额（每天工资 * 实际上班天数）
	private BigDecimal price;
}
