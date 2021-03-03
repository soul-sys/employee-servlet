# 数据库表

	DROP TABLE IF EXISTS `t_admin`;
	CREATE TABLE `t_admin`  (
	  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
	  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
	  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
	  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
	  `img` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
	  `createDt` datetime NULL DEFAULT NULL COMMENT '创建日期',
	  PRIMARY KEY (`id`) USING BTREE
	) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
	
	-- ----------------------------
	-- Records of t_admin
	-- ----------------------------
	INSERT INTO `t_admin` VALUES (1, '123', '123', '123', NULL, '2021-03-02 17:44:53');
	
	-- ----------------------------
	-- Table structure for t_company
	-- ----------------------------
	DROP TABLE IF EXISTS `t_company`;
	CREATE TABLE `t_company`  (
	  `id` int NOT NULL,
	  `companyName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
	  `introduce` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
	  `createDt` datetime NULL DEFAULT NULL,
	  PRIMARY KEY (`id`) USING BTREE
	) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
	
	-- ----------------------------
	-- Records of t_company
	-- ----------------------------
	
	-- ----------------------------
	-- Table structure for t_employ
	-- ----------------------------
	DROP TABLE IF EXISTS `t_employ`;
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
	) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
	
	-- ----------------------------
	-- Records of t_employ
	-- ----------------------------
	INSERT INTO `t_employ` VALUES (1, '111', NULL, NULL, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '123');


