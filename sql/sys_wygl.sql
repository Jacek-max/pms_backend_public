/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : localhost:3306
 Source Schema         : sys_wygl

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 07/11/2023 23:43:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dispatch_order
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_order`;
CREATE TABLE `dispatch_order`  (
  `dispatch_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '派工表id',
  `type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '派工类型 0：维修 1：投诉',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '业主id',
  `order_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工单号',
  `order_source` int(11) NULL DEFAULT NULL COMMENT '工单来源',
  `dispatch_user_id` int(11) NULL DEFAULT NULL COMMENT '处理人员id',
  `dispatch_time` datetime NULL DEFAULT NULL COMMENT '派工时间',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工单状态 0：未处理 1：已处理 2：已完成',
  `remark` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
  `operator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作人(中文)',
  `dispatch_num` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '派单工单号',
  PRIMARY KEY (`dispatch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for fee_park
-- ----------------------------
DROP TABLE IF EXISTS `fee_park`;
CREATE TABLE `fee_park`  (
  `park_fee_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '业主id',
  `park_id` int(11) NULL DEFAULT NULL COMMENT '车位id',
  `pay_park_month` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '缴费年月',
  `pay_park_money` decimal(18, 2) NULL DEFAULT NULL COMMENT '缴费金额',
  `pay_park_status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '0:未缴费  1：已缴费',
  `pay_park_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`park_fee_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for fee_power
-- ----------------------------
DROP TABLE IF EXISTS `fee_power`;
CREATE TABLE `fee_power`  (
  `power_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `house_id` int(11) NULL DEFAULT NULL COMMENT '房屋id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '业主id',
  `pay_power_month` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '缴费年月',
  `pay_power_money` decimal(18, 2) NULL DEFAULT NULL COMMENT '缴费金额',
  `power_num` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '表显',
  `pay_power_status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '0:未缴费  1：已缴费',
  `pay_power_time` datetime NULL DEFAULT NULL COMMENT '缴费时间',
  PRIMARY KEY (`power_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for fee_water
-- ----------------------------
DROP TABLE IF EXISTS `fee_water`;
CREATE TABLE `fee_water`  (
  `water_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `house_id` int(11) NULL DEFAULT NULL COMMENT '房屋id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '业主id',
  `pay_water_month` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '缴费年月',
  `pay_water_money` decimal(18, 2) NULL DEFAULT NULL COMMENT '缴费金额',
  `waterr_num` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '表显',
  `pay_water_status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '0:未缴费  1：已缴费',
  `pay_water_time` datetime NULL DEFAULT NULL COMMENT '缴费时间',
  PRIMARY KEY (`water_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for house_building
-- ----------------------------
DROP TABLE IF EXISTS `house_building`;
CREATE TABLE `house_building`  (
  `build_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '栋数id',
  `type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '栋数类型 0：普通房 1：电梯房',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '栋数名称',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`build_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for house_list
-- ----------------------------
DROP TABLE IF EXISTS `house_list`;
CREATE TABLE `house_list`  (
  `house_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '房屋id',
  `unit_id` int(11) NULL DEFAULT NULL COMMENT '单元id',
  `house_num` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '房屋编号',
  `house_area` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '房屋面积',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '使用状态 0:未使用 1：已使用',
  PRIMARY KEY (`house_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for house_unit
-- ----------------------------
DROP TABLE IF EXISTS `house_unit`;
CREATE TABLE `house_unit`  (
  `unit_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '单元id',
  `build_id` int(11) NULL DEFAULT NULL COMMENT '栋数id',
  `unit_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '单元名称',
  PRIMARY KEY (`unit_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for live_house
-- ----------------------------
DROP TABLE IF EXISTS `live_house`;
CREATE TABLE `live_house`  (
  `live_house_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `house_id` int(11) NULL DEFAULT NULL COMMENT '房屋id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '业主id',
  `use_status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '0:使用中 1： 解绑、退房',
  PRIMARY KEY (`live_house_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for live_park
-- ----------------------------
DROP TABLE IF EXISTS `live_park`;
CREATE TABLE `live_park`  (
  `live_park_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `park_id` int(11) NULL DEFAULT NULL COMMENT '车位id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '业主id',
  `live_statue` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '0:使用 1：解绑、退车位状态',
  PRIMARY KEY (`live_park_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for live_role
-- ----------------------------
DROP TABLE IF EXISTS `live_role`;
CREATE TABLE `live_role`  (
  `liv_role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '业主id',
  PRIMARY KEY (`liv_role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of live_role
-- ----------------------------
INSERT INTO `live_role` VALUES (3, 3, 3);
INSERT INTO `live_role` VALUES (6, 3, 4);
INSERT INTO `live_role` VALUES (7, 3, 5);
INSERT INTO `live_role` VALUES (8, 3, 6);
INSERT INTO `live_role` VALUES (9, 3, 7);

-- ----------------------------
-- Table structure for live_user
-- ----------------------------
DROP TABLE IF EXISTS `live_user`;
CREATE TABLE `live_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '业主id',
  `username` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '登录账号',
  `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '电话',
  `sex` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '性别 0：男 1：女',
  `login_name` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '登录密码',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT ' 0：启用 1：禁用',
  `is_account_non_expired` int(255) NULL DEFAULT NULL COMMENT '帐户是否过期(1 未过期，0已过期)',
  `is_account_non_locked` int(255) NULL DEFAULT NULL COMMENT '帐户是否被锁定(1 未过期，0已过期)',
  `is_credentials_non_expired` int(255) NULL DEFAULT NULL COMMENT '密码是否过期(1 未过期，0已过期)',
  `is_enabled` int(255) NULL DEFAULT NULL COMMENT '帐户是否可用(1 可用，0 删除用户)',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of live_user
-- ----------------------------
INSERT INTO `live_user` VALUES (6, 'zs666', '13145678900', '0', '张五', 'b630a2dd1ec4718d8f915f8c5477574a', '0', NULL, NULL, NULL, NULL);
INSERT INTO `live_user` VALUES (7, 'lm123', '13247689013', '1', '刘明', '$2a$10$A6RQnNJhmQVFD0pVrVU3NemEN9UtxIUb75e3JxKM2JIH9DX29xxk2', '0', 1, 1, 1, 1);

-- ----------------------------
-- Table structure for parking_list
-- ----------------------------
DROP TABLE IF EXISTS `parking_list`;
CREATE TABLE `parking_list`  (
  `park_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '车位id',
  `park_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '车位类型 0：地上 1：地下',
  `park_name` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '车位名称',
  `park_status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '车位使用状态 0：未使用 1：已使用',
  `park_num` int(11) NULL DEFAULT NULL COMMENT '车位序号',
  PRIMARY KEY (`park_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for sys_carousel
-- ----------------------------
DROP TABLE IF EXISTS `sys_carousel`;
CREATE TABLE `sys_carousel`  (
  `carousel_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `img_url` varchar(524) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '轮播图',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '状态（0：启用 1：禁用）',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`carousel_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_carousel
-- ----------------------------

-- ----------------------------
-- Table structure for sys_material_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_material_type`;
CREATE TABLE `sys_material_type`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `type_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型名称',
  `deleted` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '软删除（0：未删除 1：已删除）',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_material_type
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父级菜单id',
  `menu_label` varchar(56) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '菜单名称',
  `menu_code` varchar(56) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '权限字段',
  `name` varchar(56) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '路由名称',
  `path` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '路由地址',
  `url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '组件路由',
  `type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '类型 0：目录 1：菜单 2：按钮',
  `icon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '图标',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '序号',
  `remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  `parent_name` varchar(56) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '上级菜单名称',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 102 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', 'sys:system', '', '/system', '', '0', 'el-icon-menu', 2, '', '顶级菜单');
INSERT INTO `sys_menu` VALUES (2, 1, '员工管理', 'sys:sysUserList', 'sysUserList', '/sysUserList', '/system/sysUserList', '1', 'el-icon-user', 1, '', '系统管理');
INSERT INTO `sys_menu` VALUES (3, 2, '新增', 'sys:user:add', '', '', '', '2', '', 1, '', '员工管理');
INSERT INTO `sys_menu` VALUES (4, 2, '编辑', 'sys:user:edit', '', '', '', '2', '', 2, '', '员工管理');
INSERT INTO `sys_menu` VALUES (5, 2, '删除', 'sys:user:delete', '', '', '', '2', '', 3, '', '员工管理');
INSERT INTO `sys_menu` VALUES (6, 1, '角色管理', 'sys:sysRoleList', 'sysRoleList', '/sysRoleList', '/system/sysRoleList', '1', 'el-icon-s-operation', 2, '', '系统管理');
INSERT INTO `sys_menu` VALUES (7, 1, '权限管理', 'sys:sysMenuList', 'sysMenuList', '/sysMenuList', '/system/sysMenuList', '1', 'tree', 3, '', '系统管理');
INSERT INTO `sys_menu` VALUES (8, 6, '新增', 'sys:role:add', '', '', '', '2', '', 1, '', '角色管理');
INSERT INTO `sys_menu` VALUES (9, 6, '编辑', 'sys:role:edit', '', '', '', '2', '', 2, '', '角色管理');
INSERT INTO `sys_menu` VALUES (10, 6, '删除', 'sys:role:delete', '', '', '', '2', '', 3, '', '角色管理');
INSERT INTO `sys_menu` VALUES (11, 7, '新增', 'sys:menu:add', '', '', '', '2', '', 1, '', '权限管理');
INSERT INTO `sys_menu` VALUES (12, 7, '编辑', 'sys:menu:edit', '', '', '', '2', '', 2, '', '权限管理');
INSERT INTO `sys_menu` VALUES (13, 7, '删除', 'sys:menu:delete', '', '', '', '2', '', 3, '', '权限管理');
INSERT INTO `sys_menu` VALUES (14, 0, '房屋管理', 'sys:home', '', '/sysHouse', '', '0', 'el-icon-s-home', 3, '', '顶级菜单');
INSERT INTO `sys_menu` VALUES (15, 14, '房屋列表', 'sys:houseList', 'houseList', '/houseList', '/house/houseList', '1', 'el-icon-house', 3, '', '房屋管理');
INSERT INTO `sys_menu` VALUES (16, 15, '新增', 'sys:houseList:add', '', '', '', '2', '', 1, '', '房屋列表');
INSERT INTO `sys_menu` VALUES (17, 15, '编辑', 'sys:houseList:edit', '', '', '', '2', '', 2, '', '房屋列表');
INSERT INTO `sys_menu` VALUES (18, 15, '删除', 'sys:houseList:delete', '', '', '', '2', '', 3, '', '房屋列表');
INSERT INTO `sys_menu` VALUES (19, 14, '栋数管理', 'sys:houseBuilding', 'houseBuilding', '/houseBuilding', '/house/houseBuilding', '1', 'el-icon-office-building', 1, '', '房屋管理');
INSERT INTO `sys_menu` VALUES (20, 14, '单元管理', 'sys:houseUnit', 'houseUnit', '/houseUnit', '/house/houseUnit', '1', 'el-icon-school', 2, '', '房屋管理');
INSERT INTO `sys_menu` VALUES (21, 19, '新增', 'sys:houseBuilding:add', '', '', '', '2', '', 1, '', '栋数管理');
INSERT INTO `sys_menu` VALUES (22, 19, '编辑', 'sys:houseBuilding:edit', '', '', '', '2', '', 2, '', '栋数管理');
INSERT INTO `sys_menu` VALUES (23, 19, '删除', 'sys:houseBuilding:delete', '', '', '', '2', '', 3, '', '栋数管理');
INSERT INTO `sys_menu` VALUES (24, 20, '新增', 'sys:houseUnit:add', '', '', '', '2', '', 1, '', '单元管理');
INSERT INTO `sys_menu` VALUES (25, 20, '编辑', 'sys:houseUnit:edit', '', '', '', '2', '', 2, '', '单元管理');
INSERT INTO `sys_menu` VALUES (27, 20, '删除', 'sys:houseUnit:delete', '', '', '', '2', '', 3, '', '单元管理');
INSERT INTO `sys_menu` VALUES (28, 0, '车位管理', 'sys:sysPark', '', '/sysPark', '', '0', 'el-icon-s-promotion', 4, '', '顶级菜单');
INSERT INTO `sys_menu` VALUES (29, 28, '车位列表', 'sys:parkList', 'parkList', '/parkList', '/park/parkList', '1', 'el-icon-truck', 1, '', '车位管理');
INSERT INTO `sys_menu` VALUES (30, 29, '新增', 'sys:parkList:add', '', '', '', '2', '', 1, '', '车位列表');
INSERT INTO `sys_menu` VALUES (31, 29, '编辑', 'sys:parkList:edit', '', '', '', '2', '', 2, '', '车位列表');
INSERT INTO `sys_menu` VALUES (32, 29, '删除', 'sys:parkList:delete', '', '', '', '2', '', 3, '', '车位列表');
INSERT INTO `sys_menu` VALUES (33, 0, '业主管理', 'sys:live', '', '/live', '', '0', 'el-icon-s-custom', 5, '', '顶级菜单');
INSERT INTO `sys_menu` VALUES (34, 33, '业主列表', 'sys:liveUser', 'liveUser', '/liveUser', '/live/liveUser', '1', 'table', 1, '', '业主管理');
INSERT INTO `sys_menu` VALUES (35, 34, '新增', 'sys:liveUser:add', '', '', '', '2', '', 1, '', '业主列表');
INSERT INTO `sys_menu` VALUES (36, 34, '编辑', 'sys:liveUser:edit', '', '', '', '2', '', 2, '', '业主列表');
INSERT INTO `sys_menu` VALUES (37, 34, '删除', 'sys:liveUser:delete', '', '', '', '2', '', 3, '', '业主列表');
INSERT INTO `sys_menu` VALUES (38, 34, '分配房屋', 'sys:liveUser:assignHome', '', '', '', '2', '', 4, '', '业主列表');
INSERT INTO `sys_menu` VALUES (39, 34, '分配车位', 'sys:liveUser:assignCar', '', '', '', '2', '', 5, '', '业主列表');
INSERT INTO `sys_menu` VALUES (40, 34, '退房', 'sys:liveUser:returnHome', '', '', '', '2', '', 6, '', '业主列表');
INSERT INTO `sys_menu` VALUES (41, 34, '退车位', 'sys:liveUser:returnCar', '', '', '', '2', '', 7, '', '业主列表');
INSERT INTO `sys_menu` VALUES (42, 0, '收费管理', 'sys:fee', '', '/fee', '', '0', 'el-icon-s-finance', 6, '', '顶级菜单');
INSERT INTO `sys_menu` VALUES (43, 42, '电费管理', 'sys:feePower', 'feePower', '/feePower', '/fee/feePower', '1', 'el-icon-s-opportunity', 1, '', '收费管理');
INSERT INTO `sys_menu` VALUES (44, 42, '水费管理', 'sys:feeWater', 'feeWater', '/feeWater', '/fee/feeWater', '1', 'el-icon-s-data', 2, '', '收费管理');
INSERT INTO `sys_menu` VALUES (45, 42, '停车管理', 'sys:feePark', 'feePark', '/feePark', '/fee/feePark', '1', 'el-icon-s-marketing', 3, '', '收费管理');
INSERT INTO `sys_menu` VALUES (46, 43, '新增', 'sys:feePower:add', '', '', '', '2', '', 1, '', '电费管理');
INSERT INTO `sys_menu` VALUES (47, 43, '编辑', 'sys:feePower:edit', '', '', '', '2', '', 2, '', '电费管理');
INSERT INTO `sys_menu` VALUES (48, 43, '删除', 'sys:feePower:delete', '', '', '', '2', '', 3, '', '电费管理');
INSERT INTO `sys_menu` VALUES (49, 43, '缴费', 'sys:feePower:pay', '', '', '', '2', '', 4, '', '电费管理');
INSERT INTO `sys_menu` VALUES (50, 44, '新增', 'sys:feeWater:add', '', '', '', '2', '', 1, '', '水费管理');
INSERT INTO `sys_menu` VALUES (51, 44, '编辑', 'sys:feeWater:edit', '', '', '', '2', '', 2, '', '水费管理');
INSERT INTO `sys_menu` VALUES (52, 44, '删除', 'sys:feeWater:delete', '', '', '', '2', '', 3, '', '水费管理');
INSERT INTO `sys_menu` VALUES (53, 44, '缴费', 'sys:feeWater:pay', '', '', '', '2', '', 4, '', '水费管理');
INSERT INTO `sys_menu` VALUES (54, 45, '新增', 'sys:feePark:add', '', '', '', '2', '', 1, '', '停车管理');
INSERT INTO `sys_menu` VALUES (55, 45, '编辑', 'sys:feePark:edit', '', '', '', '2', '', 2, '', '停车管理');
INSERT INTO `sys_menu` VALUES (56, 45, '删除', 'sys:feePark:delete', '', '', '', '2', '', 3, '', '停车管理');
INSERT INTO `sys_menu` VALUES (57, 45, '缴费', 'sys:feePark:pay', '', '', '', '2', '', 4, '', '停车管理');
INSERT INTO `sys_menu` VALUES (58, 0, '投诉管理', 'sys:userComplaint', '', '/userComplaint', '', '0', 'el-icon-s-release', 7, '', '顶级菜单');
INSERT INTO `sys_menu` VALUES (59, 58, '投诉列表', 'sys:userComplaintList', 'userComplaintList', '/userComplaintList', '/userComplaint/userComplaint', '1', 'el-icon-edit-outline', 1, '', '投诉管理');
INSERT INTO `sys_menu` VALUES (60, 58, '我的投诉', 'sys:myUserComplaint', 'myUserComplaint', '/myUserComplaint', '/userComplaint/myUserComplaint', '1', 'el-icon-chat-dot-round', 2, '', '投诉管理');
INSERT INTO `sys_menu` VALUES (61, 59, '处理', 'sys:userComplaintList:do', '', '', '', '2', '', 1, '', '投诉列表');
INSERT INTO `sys_menu` VALUES (62, 59, '删除', 'sys:userComplaintList:delete', '', '', '', '2', '', 2, '', '投诉列表');
INSERT INTO `sys_menu` VALUES (63, 60, '新增', 'sys:userComplaintList:add', '', '', '', '2', '', 1, '', '我的投诉');
INSERT INTO `sys_menu` VALUES (64, 60, '编辑', 'sys:userComplaintList:edit', '', '', '', '2', '', 2, '', '我的投诉');
INSERT INTO `sys_menu` VALUES (65, 60, '删除', 'sys:userComplaintList:delete', '', '', '', '2', '', 3, '', '我的投诉');
INSERT INTO `sys_menu` VALUES (66, 0, '维修管理', 'sys:repairModel', '', '/repairModel', '', '0', 'el-icon-s-cooperation', 8, '', '顶级菜单');
INSERT INTO `sys_menu` VALUES (67, 66, '维修列表', 'sys:repairList', 'repairList', '/repairList', '/repair/repairList', '1', 'el-icon-cpu', 1, '', '维修管理');
INSERT INTO `sys_menu` VALUES (68, 66, '我的维修', 'sys:myRepair', 'myRepair', '/myRepair', '/repair/myRepair', '1', 'el-icon-postcard', 2, '', '维修管理');
INSERT INTO `sys_menu` VALUES (69, 68, '新增', 'sys:myRepair:add', '', '', '', '2', '', 1, '', '我的维修');
INSERT INTO `sys_menu` VALUES (70, 68, '编辑', 'sys:myRepair:edit', '', '', '', '2', '', 2, '', '我的维修');
INSERT INTO `sys_menu` VALUES (71, 68, '删除', 'sys:myRepair:delete', '', '', '', '2', '', 3, '', '我的维修');
INSERT INTO `sys_menu` VALUES (72, 0, '公告管理', 'sys:notice', '', '/notice', '', '0', 'el-icon-message-solid', 9, '', '顶级菜单');
INSERT INTO `sys_menu` VALUES (73, 72, '公告列表', 'sys:noticeList', 'noticeList', '/noticeList', '/notice/noticeList', '1', 'el-icon-news', 1, '', '公告管理');
INSERT INTO `sys_menu` VALUES (74, 73, '新增', 'sys:noticeList:add', '', '', '', '2', '', 1, '', '公告列表');
INSERT INTO `sys_menu` VALUES (75, 73, '编辑', 'sys:noticeList:edit', '', '', '', '2', '', 2, '', '公告列表');
INSERT INTO `sys_menu` VALUES (76, 73, '删除', 'sys:noticeList:delete', '', '', '', '2', '', 3, '', '公告列表');
INSERT INTO `sys_menu` VALUES (77, 73, '查看', 'sys:noticeList:look', '', '', '', '2', '', 4, '', '公告列表');
INSERT INTO `sys_menu` VALUES (78, 2, '分配权限', 'sys:user:assignRole', '', '', '', '2', '', 4, '', '员工管理');
INSERT INTO `sys_menu` VALUES (79, 6, '分配权限', 'sys:role:assignRole', '', '', '', '2', '', 4, '', '角色管理');
INSERT INTO `sys_menu` VALUES (80, 67, '处理', 'sys:repairList:do', '', '', '', '2', '', 1, '', '维修列表');
INSERT INTO `sys_menu` VALUES (81, 67, '删除', 'sys:myRepair:delete', '', '', '', '2', '', 2, '', '维修列表');
INSERT INTO `sys_menu` VALUES (82, 0, '缴费记录', 'sys:feeList', '', '/feeList', '', '0', 'el-icon-s-order', 10, '', '顶级菜单');
INSERT INTO `sys_menu` VALUES (83, 82, '我的电费', 'sys:myPowerFee', 'myPowerFee', '/myPowerFee', '/feeList/myPowerFee', '1', 'el-icon-attract', 1, '', '缴费记录');
INSERT INTO `sys_menu` VALUES (84, 82, '我的水费', 'sys:myWaterFee', 'myWaterFee', '/myWaterFee', '/feeList/myWaterFee', '1', 'el-icon-tickets', 2, '', '缴费记录');
INSERT INTO `sys_menu` VALUES (85, 82, '我的停车', 'sys:myParkFee', 'myParkFee', '/myParkFee', '/feeList/myParkFee', '1', 'el-icon-time', 3, '', '缴费记录');
INSERT INTO `sys_menu` VALUES (89, 0, '首页', 'sys:/', '', '/', '', '0', 'dashboard', 1, '', '顶级菜单');
INSERT INTO `sys_menu` VALUES (91, 89, '控制台', 'sys:adminIndex', 'adminIndex', '/adminIndex', '/dashboard/adminIndex', '1', 'dashboard', 2, '', '首页');
INSERT INTO `sys_menu` VALUES (92, 91, '物业数据', 'sys:adminIndex:user', '', '', '', '2', '', 1, '', '控制台');
INSERT INTO `sys_menu` VALUES (93, 91, '通知看板', 'sys:adminIndex:notice', '', '', '', '2', '', 2, '', '控制台');
INSERT INTO `sys_menu` VALUES (94, 91, '用户数据', 'sys:adminIndex:userDate', '', '', '', '2', '', 3, '', '控制台');
INSERT INTO `sys_menu` VALUES (95, 91, '用户看板', 'sys:adminIndex:userLook', '', '', '', '2', '', 4, '', '控制台');
INSERT INTO `sys_menu` VALUES (96, 73, '置顶', 'sys:noticeList:top', '', '', '', '2', '', 5, '', '公告列表');
INSERT INTO `sys_menu` VALUES (97, 67, '派工', 'sys:repairList:dispatch', '', '', '', '2', '', 3, '', '维修列表');
INSERT INTO `sys_menu` VALUES (98, 68, '确认', 'sys:repairList:confirm', '', '', '', '2', '', 4, '', '我的维修');
INSERT INTO `sys_menu` VALUES (99, 66, '维修工单', 'sys:repairOrder', 'repairOrder', '/repairOrder', '/repair/repairOrder', '1', 'el-icon-s-order el-icon-s-platform', 3, '', '维修管理');
INSERT INTO `sys_menu` VALUES (100, 99, '处理', 'sys:repairOrder:do', '', '', '', '2', '', 1, '', '维修工单');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标题',
  `notice_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '内容',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `is_top` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '是否置顶（0：否 1：是 默认为0）',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(56) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(56) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', '物业公司管理员');
INSERT INTO `sys_role` VALUES (2, '收费员', '物业公司水/电费收费员');
INSERT INTO `sys_role` VALUES (3, '业主', '小区住户');
INSERT INTO `sys_role` VALUES (4, '维修员', '维修人员');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` int(11) NULL DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3334 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2878, 2, 42);
INSERT INTO `sys_role_menu` VALUES (2879, 2, 43);
INSERT INTO `sys_role_menu` VALUES (2880, 2, 46);
INSERT INTO `sys_role_menu` VALUES (2881, 2, 47);
INSERT INTO `sys_role_menu` VALUES (2882, 2, 48);
INSERT INTO `sys_role_menu` VALUES (2883, 2, 49);
INSERT INTO `sys_role_menu` VALUES (2884, 2, 44);
INSERT INTO `sys_role_menu` VALUES (2885, 2, 50);
INSERT INTO `sys_role_menu` VALUES (2886, 2, 51);
INSERT INTO `sys_role_menu` VALUES (2887, 2, 52);
INSERT INTO `sys_role_menu` VALUES (2888, 2, 53);
INSERT INTO `sys_role_menu` VALUES (2889, 2, 45);
INSERT INTO `sys_role_menu` VALUES (2890, 2, 54);
INSERT INTO `sys_role_menu` VALUES (2891, 2, 55);
INSERT INTO `sys_role_menu` VALUES (2892, 2, 56);
INSERT INTO `sys_role_menu` VALUES (2893, 2, 57);
INSERT INTO `sys_role_menu` VALUES (2894, 2, 77);
INSERT INTO `sys_role_menu` VALUES (2895, 2, 93);
INSERT INTO `sys_role_menu` VALUES (2896, 2, 72);
INSERT INTO `sys_role_menu` VALUES (2897, 2, 73);
INSERT INTO `sys_role_menu` VALUES (2898, 2, 89);
INSERT INTO `sys_role_menu` VALUES (2899, 2, 91);
INSERT INTO `sys_role_menu` VALUES (2900, 3, 60);
INSERT INTO `sys_role_menu` VALUES (2901, 3, 63);
INSERT INTO `sys_role_menu` VALUES (2902, 3, 64);
INSERT INTO `sys_role_menu` VALUES (2903, 3, 65);
INSERT INTO `sys_role_menu` VALUES (2904, 3, 68);
INSERT INTO `sys_role_menu` VALUES (2905, 3, 69);
INSERT INTO `sys_role_menu` VALUES (2906, 3, 70);
INSERT INTO `sys_role_menu` VALUES (2907, 3, 71);
INSERT INTO `sys_role_menu` VALUES (2908, 3, 98);
INSERT INTO `sys_role_menu` VALUES (2909, 3, 77);
INSERT INTO `sys_role_menu` VALUES (2910, 3, 82);
INSERT INTO `sys_role_menu` VALUES (2911, 3, 83);
INSERT INTO `sys_role_menu` VALUES (2912, 3, 84);
INSERT INTO `sys_role_menu` VALUES (2913, 3, 85);
INSERT INTO `sys_role_menu` VALUES (2914, 3, 94);
INSERT INTO `sys_role_menu` VALUES (2915, 3, 95);
INSERT INTO `sys_role_menu` VALUES (2916, 3, 58);
INSERT INTO `sys_role_menu` VALUES (2917, 3, 66);
INSERT INTO `sys_role_menu` VALUES (2918, 3, 72);
INSERT INTO `sys_role_menu` VALUES (2919, 3, 73);
INSERT INTO `sys_role_menu` VALUES (2920, 3, 89);
INSERT INTO `sys_role_menu` VALUES (2921, 3, 91);
INSERT INTO `sys_role_menu` VALUES (3168, 4, 99);
INSERT INTO `sys_role_menu` VALUES (3169, 4, 100);
INSERT INTO `sys_role_menu` VALUES (3170, 4, 77);
INSERT INTO `sys_role_menu` VALUES (3171, 4, 95);
INSERT INTO `sys_role_menu` VALUES (3172, 4, 66);
INSERT INTO `sys_role_menu` VALUES (3173, 4, 72);
INSERT INTO `sys_role_menu` VALUES (3174, 4, 73);
INSERT INTO `sys_role_menu` VALUES (3175, 4, 89);
INSERT INTO `sys_role_menu` VALUES (3176, 4, 91);
INSERT INTO `sys_role_menu` VALUES (3255, 1, 1);
INSERT INTO `sys_role_menu` VALUES (3256, 1, 2);
INSERT INTO `sys_role_menu` VALUES (3257, 1, 3);
INSERT INTO `sys_role_menu` VALUES (3258, 1, 4);
INSERT INTO `sys_role_menu` VALUES (3259, 1, 5);
INSERT INTO `sys_role_menu` VALUES (3260, 1, 78);
INSERT INTO `sys_role_menu` VALUES (3261, 1, 6);
INSERT INTO `sys_role_menu` VALUES (3262, 1, 8);
INSERT INTO `sys_role_menu` VALUES (3263, 1, 9);
INSERT INTO `sys_role_menu` VALUES (3264, 1, 10);
INSERT INTO `sys_role_menu` VALUES (3265, 1, 79);
INSERT INTO `sys_role_menu` VALUES (3266, 1, 7);
INSERT INTO `sys_role_menu` VALUES (3267, 1, 11);
INSERT INTO `sys_role_menu` VALUES (3268, 1, 12);
INSERT INTO `sys_role_menu` VALUES (3269, 1, 13);
INSERT INTO `sys_role_menu` VALUES (3270, 1, 101);
INSERT INTO `sys_role_menu` VALUES (3271, 1, 14);
INSERT INTO `sys_role_menu` VALUES (3272, 1, 15);
INSERT INTO `sys_role_menu` VALUES (3273, 1, 16);
INSERT INTO `sys_role_menu` VALUES (3274, 1, 17);
INSERT INTO `sys_role_menu` VALUES (3275, 1, 18);
INSERT INTO `sys_role_menu` VALUES (3276, 1, 19);
INSERT INTO `sys_role_menu` VALUES (3277, 1, 21);
INSERT INTO `sys_role_menu` VALUES (3278, 1, 22);
INSERT INTO `sys_role_menu` VALUES (3279, 1, 23);
INSERT INTO `sys_role_menu` VALUES (3280, 1, 20);
INSERT INTO `sys_role_menu` VALUES (3281, 1, 24);
INSERT INTO `sys_role_menu` VALUES (3282, 1, 25);
INSERT INTO `sys_role_menu` VALUES (3283, 1, 27);
INSERT INTO `sys_role_menu` VALUES (3284, 1, 28);
INSERT INTO `sys_role_menu` VALUES (3285, 1, 29);
INSERT INTO `sys_role_menu` VALUES (3286, 1, 30);
INSERT INTO `sys_role_menu` VALUES (3287, 1, 31);
INSERT INTO `sys_role_menu` VALUES (3288, 1, 32);
INSERT INTO `sys_role_menu` VALUES (3289, 1, 33);
INSERT INTO `sys_role_menu` VALUES (3290, 1, 34);
INSERT INTO `sys_role_menu` VALUES (3291, 1, 35);
INSERT INTO `sys_role_menu` VALUES (3292, 1, 36);
INSERT INTO `sys_role_menu` VALUES (3293, 1, 37);
INSERT INTO `sys_role_menu` VALUES (3294, 1, 38);
INSERT INTO `sys_role_menu` VALUES (3295, 1, 39);
INSERT INTO `sys_role_menu` VALUES (3296, 1, 40);
INSERT INTO `sys_role_menu` VALUES (3297, 1, 41);
INSERT INTO `sys_role_menu` VALUES (3298, 1, 42);
INSERT INTO `sys_role_menu` VALUES (3299, 1, 43);
INSERT INTO `sys_role_menu` VALUES (3300, 1, 46);
INSERT INTO `sys_role_menu` VALUES (3301, 1, 47);
INSERT INTO `sys_role_menu` VALUES (3302, 1, 48);
INSERT INTO `sys_role_menu` VALUES (3303, 1, 49);
INSERT INTO `sys_role_menu` VALUES (3304, 1, 44);
INSERT INTO `sys_role_menu` VALUES (3305, 1, 50);
INSERT INTO `sys_role_menu` VALUES (3306, 1, 51);
INSERT INTO `sys_role_menu` VALUES (3307, 1, 52);
INSERT INTO `sys_role_menu` VALUES (3308, 1, 53);
INSERT INTO `sys_role_menu` VALUES (3309, 1, 45);
INSERT INTO `sys_role_menu` VALUES (3310, 1, 54);
INSERT INTO `sys_role_menu` VALUES (3311, 1, 55);
INSERT INTO `sys_role_menu` VALUES (3312, 1, 56);
INSERT INTO `sys_role_menu` VALUES (3313, 1, 57);
INSERT INTO `sys_role_menu` VALUES (3314, 1, 59);
INSERT INTO `sys_role_menu` VALUES (3315, 1, 61);
INSERT INTO `sys_role_menu` VALUES (3316, 1, 62);
INSERT INTO `sys_role_menu` VALUES (3317, 1, 67);
INSERT INTO `sys_role_menu` VALUES (3318, 1, 80);
INSERT INTO `sys_role_menu` VALUES (3319, 1, 81);
INSERT INTO `sys_role_menu` VALUES (3320, 1, 97);
INSERT INTO `sys_role_menu` VALUES (3321, 1, 72);
INSERT INTO `sys_role_menu` VALUES (3322, 1, 73);
INSERT INTO `sys_role_menu` VALUES (3323, 1, 74);
INSERT INTO `sys_role_menu` VALUES (3324, 1, 75);
INSERT INTO `sys_role_menu` VALUES (3325, 1, 76);
INSERT INTO `sys_role_menu` VALUES (3326, 1, 77);
INSERT INTO `sys_role_menu` VALUES (3327, 1, 96);
INSERT INTO `sys_role_menu` VALUES (3328, 1, 92);
INSERT INTO `sys_role_menu` VALUES (3329, 1, 93);
INSERT INTO `sys_role_menu` VALUES (3330, 1, 58);
INSERT INTO `sys_role_menu` VALUES (3331, 1, 66);
INSERT INTO `sys_role_menu` VALUES (3332, 1, 89);
INSERT INTO `sys_role_menu` VALUES (3333, 1, 91);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `login_name` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '登录密码',
  `username` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '登录账号',
  `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '电话',
  `sex` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '性别 0：女 1：男',
  `id_card` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '身份证号码',
  `is_admin` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否是管理员 0：不是 1：是',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '0：在职  1：离职',
  `is_used` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '0：启用 1：禁用',
  `is_account_non_expired` int(255) NULL DEFAULT NULL COMMENT '帐户是否过期(1 未过期，0已过期)',
  `is_account_non_locked` int(255) NULL DEFAULT NULL COMMENT '帐户是否被锁定(1 未过期，0已过期)',
  `is_credentials_non_expired` int(255) NULL DEFAULT NULL COMMENT '密码是否过期(1 未过期，0已过期)',
  `is_enabled` int(255) NULL DEFAULT NULL COMMENT '帐户是否可用(1 可用，0 删除用户)',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '张三', '$2a$10$g958uVKEfYmqAwCOBXWp1uCqHF0KWQi65EIoPgzxm.x77ENQ/n.Zq', 'zhangsan', '18160965123', '1', '350543199810171401', '1', '0', '0', 1, 1, 1, 1);
INSERT INTO `sys_user` VALUES (3, '李四', '$2a$10$tR98kG3hdWqpeHcSuiwSyO3dmH5ecsqYCEyHpJ67tnNJQMt25YfmO', 'ls123', '17860933369', '0', '12345678900000', NULL, '0', '1', 1, 1, 1, 1);
INSERT INTO `sys_user` VALUES (4, '王五', '$2a$10$g958uVKEfYmqAwCOBXWp1uCqHF0KWQi65EIoPgzxm.x77ENQ/n.Zq', 'ww666', '18178907653', '1', '356789199207192413', NULL, '0', '0', 1, 1, 1, 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`user_role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2, 4);
INSERT INTO `sys_user_role` VALUES (3, 4, 3);

-- ----------------------------
-- Table structure for user_complaint
-- ----------------------------
DROP TABLE IF EXISTS `user_complaint`;
CREATE TABLE `user_complaint`  (
  `complaint_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '业主id',
  `title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标题',
  `complaint_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '投诉内容',
  `create_time` datetime NULL DEFAULT NULL COMMENT '投诉时间',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '处理状态 0：未处理 1：已处理',
  PRIMARY KEY (`complaint_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for user_repair
-- ----------------------------
DROP TABLE IF EXISTS `user_repair`;
CREATE TABLE `user_repair`  (
  `repair_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '业主id',
  `phone` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '联系电话',
  `repair_address` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '维修地址',
  `repair_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '维修内容',
  `commit_time` datetime NULL DEFAULT NULL COMMENT '报修时间',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '维修状态 0：未维修 1：维修中 2：待确认 3：已维修',
  `order_num` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '工单号',
  PRIMARY KEY (`repair_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = COMPACT;

SET FOREIGN_KEY_CHECKS = 1;
