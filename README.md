# 系统设计

	 本系统为一个简单的 Servlet系统，属于刚刚开始学习 Java 的一个练手项目，项目不是很难，并且项目并未开发完成，可以后期自己再去加一些内容，做延伸。
	 
	 本项目前后端通讯使用的是异步通讯。
	 
	 后端使用 Servlet、JDBC，前端使用Layui、ECharts
	 
	 系统使用 jdk版本为 1.8，mysql版本 8.0.22。
	 
	 github地址：https://github.com/soul-sys/employee-servlet
	 
	 当然，上面也说了，这个系统不是一个完整的系统，我后期会慢慢维护的，但是并不上线~~


# 数据库表

	-- 管理员表，账号：123 密码：123
	DROP TABLE IF EXISTS `t_admin`;
	-- 公司表
	DROP TABLE IF EXISTS `t_company`;
	-- 员工表，权限不同可以登录看到的东西也不同
	DROP TABLE IF EXISTS `t_employ`;
	-- 公司组织表
	DROP TABLE IF EXISTS `t_org`;

	-- t_admin 表
	CREATE TABLE `t_admin`  (
	  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
	  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
	  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
	  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
	  `img` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
	  `createDt` datetime NULL DEFAULT NULL COMMENT '创建日期',
	  PRIMARY KEY (`id`) USING BTREE
	) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
	
	-- t_company 表
	CREATE TABLE `t_company`  (
	  `id` int NOT NULL AUTO_INCREMENT,
	  `companyName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
	  `introduce` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
	  `createDt` datetime NULL DEFAULT NULL,
	  PRIMARY KEY (`id`) USING BTREE
	) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

	-- 	t_employ 表
	CREATE TABLE `t_employ`  (
	  `id` int NOT NULL AUTO_INCREMENT,
	  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
	  `age` int NULL DEFAULT NULL,
	  `sex` int NULL DEFAULT NULL,
	  `phoneNum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
	  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
	  `photo` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
	  `pidNo` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
	  `orgId` int NULL DEFAULT NULL,
	  `salary` decimal(10, 2) NULL DEFAULT NULL,
	  `companyId` int NULL DEFAULT NULL,
	  `role` int NULL DEFAULT NULL,
	  `state` int NULL DEFAULT NULL,
	  `contractStartTime` datetime NULL DEFAULT NULL,
	  `contractLeaveTime` datetime NULL DEFAULT NULL,
	  `contractLeaveCause` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
	  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
	  PRIMARY KEY (`id`) USING BTREE
	) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
	
	-- t_org 表
	CREATE TABLE `t_org`  (
	  `id` int NOT NULL AUTO_INCREMENT,
	  `orgName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
	  `companyId` int NULL DEFAULT NULL,
	  `createDt` datetime NULL DEFAULT NULL,
	  PRIMARY KEY (`id`) USING BTREE
	) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
	
	
	-- 添加一些数据
	INSERT INTO `t_org` VALUES (1, '大哥组织', 1, '2021-03-04 13:47:17');
	INSERT INTO `t_org` VALUES (2, '二哥组织', 1, '2021-03-04 13:47:27');
	INSERT INTO `t_org` VALUES (3, '三弟大刀会', 1, '2021-03-04 13:47:40');
	
	INSERT INTO `t_employ` VALUES (1, '111', NULL, NULL, '123', NULL, NULL, NULL, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, '123');
	INSERT INTO `t_employ` VALUES (2, '333', NULL, NULL, '333', NULL, NULL, NULL, 1, NULL, 2, NULL, NULL, NULL, NULL, NULL, '333');
	INSERT INTO `t_employ` VALUES (3, NULL, NULL, NULL, '444', NULL, NULL, NULL, 1, NULL, 3, NULL, NULL, NULL, NULL, NULL, '444');
	INSERT INTO `t_employ` VALUES (4, NULL, NULL, NULL, '222', NULL, NULL, NULL, 1, NULL, 2, NULL, NULL, NULL, NULL, NULL, '222');
	INSERT INTO `t_employ` VALUES (5, '123', NULL, NULL, '555', NULL, NULL, NULL, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, '555');
	INSERT INTO `t_employ` VALUES (6, '1', 25, 1, '777', '777', '/image/20210305110855.jpg', '777', 1, 7.00, 1, 0, 0, '2021-03-05 00:00:00', NULL, NULL, '!}e$em4W4Xua-xnPrX');

	INSERT INTO `t_admin` VALUES (1, '123', '123', '123', NULL, '2021-03-02 17:44:53');

	INSERT INTO `t_company` VALUES (1, '腾讯', '腾讯是一个好公司', '2021-03-03 17:06:29');
	INSERT INTO `t_company` VALUES (2, '小米', '小米也是一个好公司', '2021-03-03 17:07:01');
	INSERT INTO `t_company` VALUES (3, '戴尔', '戴尔isgood', '2021-03-03 17:07:45');
	INSERT INTO `t_company` VALUES (5, '新浪', '新浪微博有点甜。', '2021-03-04 11:06:38');

# 使用

访问：http://localhost:8080/login.html 是员工登录界面
访问：http://localhost:8080/adminlogin.html 是系统管理员登录界面

系统管理员功能：
	
	1. 系统管理员可以进行对企业进行维护
	2. 自己的一些个人信息维护
	3. 添加系统管理员
	
员工功能：

	权限为 0 的用户
		1. 只能查看一下自己的信息
	
	权限为 1 的用户
		1. 维护当前所在的企业信息
		2. 维护当前所在企业的部门信息
		3. 维护当前所在企业的员工信息
		4. 查看报表
	
# 版本

	v1.0：基本开发一下，慢慢维护。