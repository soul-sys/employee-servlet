package com.lemon1234.entity;

import java.util.Date;

/**
 * 考勤表
 */
public class Attendance {

	private int id;
	
	// 员工id
	private int employId;
	
	// 打卡开始日期 6点以后重置时间
	private Date firstDate;
	
	// 最后打卡时间
	private Date lastDate;
	
}
