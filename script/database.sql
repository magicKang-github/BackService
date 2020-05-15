SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_action
-- ----------------------------
DROP TABLE IF EXISTS `sys_action`;
CREATE TABLE `sys_action`  (
                               `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
                               `ip` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
                               `page` int(11) NULL DEFAULT NULL COMMENT '访问页面',
                               `createtime` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
                               PRIMARY KEY (`id`) USING BTREE,
                               INDEX `fk_sys_action_page`(`page`) USING BTREE,
                               CONSTRAINT `fk_sys_action_page` FOREIGN KEY (`page`) REFERENCES `sys_action_page` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 267 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_action_page
-- ----------------------------
DROP TABLE IF EXISTS `sys_action_page`;
CREATE TABLE `sys_action_page`  (
                                    `id` int(11) NOT NULL AUTO_INCREMENT,
                                    `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `type` int(11) NULL DEFAULT NULL,
                                    `status` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `createtime` datetime(0) NULL DEFAULT NULL,
                                    `updatetime` datetime(0) NULL DEFAULT NULL,
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_images
-- ----------------------------
DROP TABLE IF EXISTS `sys_images`;
CREATE TABLE `sys_images`  (
                               `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
                               `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片名称',
                               `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
                               `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片类型',
                               `status` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
                               `createtime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                               `updatetime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_login
-- ----------------------------
DROP TABLE IF EXISTS `sys_login`;
CREATE TABLE `sys_login`  (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `uid` int(11) NULL DEFAULT NULL,
                              `cip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              `cname` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              `cid` int(11) NULL DEFAULT NULL,
                              `createtime` datetime(0) NULL DEFAULT NULL,
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `icon` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `path` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `pid` int(11) NULL DEFAULT NULL,
                             `pname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `component` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `status` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `createtime` datetime(0) NULL DEFAULT NULL,
                             `updatetime` datetime(0) NULL DEFAULT NULL,
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', '系统管理', 'md-cog', '/sysManage', 0, '菜单', 'main', '1', '2019-03-08 17:44:44', '2019-03-08 17:44:47');
INSERT INTO `sys_menu` VALUES (2, '用户管理', '用户管理', 'md-person', 'userManage', 1, '系统管理', 'userManage', '1', '2019-03-08 17:45:49', '2019-03-08 17:45:52');
INSERT INTO `sys_menu` VALUES (3, '角色管理', '角色管理', 'ios-barcode', 'permissionManage', 1, '系统管理', 'permissionManage', '1', '2019-03-08 17:46:46', '2019-03-08 17:46:49');
INSERT INTO `sys_menu` VALUES (4, '功能管理', '功能管理', 'ios-infinite', 'functionManage', 1, '系统管理', 'functionManage', '1', '2019-03-08 17:47:13', '2019-03-08 17:47:16');
INSERT INTO `sys_menu` VALUES (5, '消息中心', '消息中心', 'md-notifications', 'message_page', 1, '系统管理', 'message', '1', '2019-03-08 17:48:20', '2019-04-25 05:45:54');
INSERT INTO `sys_menu` VALUES (6, '系统属性', '系统属性', 'ios-basketball', 'property', 1, '系统管理', 'property', '1', '2019-03-12 10:19:44', '2019-03-12 10:19:48');
INSERT INTO `sys_menu` VALUES (7, '产品中心', '产品中心', 'md-cog', '/product', 0, '菜单', 'main', '1', '2019-03-12 10:20:00', '2019-03-12 10:19:56');
INSERT INTO `sys_menu` VALUES (8, '产品管理', '产品管理', 'md-notifications', 'productManage', 7, '产品中心', 'productManage', '1', '2019-03-12 10:22:40', '2019-03-12 10:22:43');
INSERT INTO `sys_menu` VALUES (9, '登录日志', '登录日志', 'ios-analytics', 'loginLog', 1, '系统管理', 'loginLog', '1', '2019-03-12 17:56:43', '2019-03-12 17:56:47');
INSERT INTO `sys_menu` VALUES (10, 'test', 'test', 'ios-albums', 'test', 1, '系统管理', 'userManage', '0', '2019-03-13 01:13:30', '2019-03-13 01:13:30');
INSERT INTO `sys_menu` VALUES (11, 'test', 'test', 'md-add-circle', '/test', 0, '菜单', 'main', '0', '2019-03-13 01:14:25', '2019-03-13 01:14:25');
INSERT INTO `sys_menu` VALUES (12, 'tt', 'tt', 'md-albums', 'tt', 1, '系统管理', 'userManage', '0', '2019-03-13 01:17:11', '2019-03-13 01:17:11');
INSERT INTO `sys_menu` VALUES (13, 'tt', 'tt', 'md-albums', '/tt', 0, '菜单', 'main', '0', '2019-03-13 01:20:59', '2019-03-13 01:20:59');
INSERT INTO `sys_menu` VALUES (14, 'tt', 'tt', 'md-albums', '/tt', 0, '菜单', 'main', '0', '2019-03-13 01:23:06', '2019-03-13 01:23:06');
INSERT INTO `sys_menu` VALUES (15, '测试', '测试', 'ios-add', '/tt1', 0, '菜单', 'main', '0', '2019-03-13 01:23:23', '2019-03-13 01:23:23');
INSERT INTO `sys_menu` VALUES (16, '测试', '测试', 'ios-add', 'test', 15, 'tt1', 'sysManage', '0', '2019-03-13 01:23:23', '2019-03-13 01:23:23');
INSERT INTO `sys_menu` VALUES (17, '123', '123', 'md-add', '123', 15, 'tt1', 'sysManage', '0', '2019-03-13 01:36:25', '2019-03-13 01:36:25');
INSERT INTO `sys_menu` VALUES (18, '标签管理', '标签管理', 'md-aperture', 'productTag', 7, '产品中心', 'productTag', '1', '2019-03-19 03:21:38', '2019-03-19 03:21:38');
INSERT INTO `sys_menu` VALUES (19, '类型管理', '类型管理', 'ios-bookmark', 'productType', 7, '产品中心', 'productType', '1', '2019-03-19 03:22:25', '2019-03-19 03:22:25');
INSERT INTO `sys_menu` VALUES (20, '配件管理', '配件管理', 'ios-archive', 'productPart', 7, '产品中心', 'productPart', '1', '2019-04-24 22:50:00', '2019-04-25 00:21:53');
INSERT INTO `sys_menu` VALUES (21, '纱网管理', '纱网管理', 'md-apps', 'productGauze', 7, '产品中心', 'productGauze', '1', '2019-04-24 22:51:14', '2019-04-25 00:22:00');
INSERT INTO `sys_menu` VALUES (22, '图片管理', '图片管理', 'md-images', 'sysImages', 1, '系统管理', 'sysImages', '1', '2019-04-24 23:19:22', '2019-04-25 00:22:13');
INSERT INTO `sys_menu` VALUES (23, '新闻中心', '新闻中心', 'ios-analytics', '/news', 0, '菜单', 'main', '1', '2019-04-24 23:28:00', '2019-04-24 23:28:31');
INSERT INTO `sys_menu` VALUES (24, '新闻管理', '新闻管理', 'ios-book', 'newsManage', 23, '新闻中心', 'newsManage', '1', '2019-04-24 23:28:00', '2019-04-25 00:21:35');
INSERT INTO `sys_menu` VALUES (25, '新闻类型管理', '新闻类型管理', 'md-bonfire', 'newsType', 23, '新闻中心', 'newsType', '1', '2019-04-24 23:29:39', '2019-04-25 00:21:43');
INSERT INTO `sys_menu` VALUES (26, '统计中心', '统计中心', 'md-bulb', '/statistics', 0, '菜单', 'main', '1', '2019-04-24 23:31:39', '2019-04-24 23:31:39');
INSERT INTO `sys_menu` VALUES (27, '统计管理', '统计管理', 'md-bulb', 'statisticsManage', 26, '统计中心', 'statisticsManage', '1', '2019-04-24 23:31:39', '2019-04-25 00:21:26');

-- ----------------------------
-- Table structure for sys_message
-- ----------------------------
DROP TABLE IF EXISTS `sys_message`;
CREATE TABLE `sys_message`  (
                                `id` int(11) NOT NULL AUTO_INCREMENT,
                                `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                `type` int(10) NULL DEFAULT NULL,
                                `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                `phone` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                `message` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
                                `status` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                `createtime` datetime(0) NULL DEFAULT NULL,
                                `updatetime` datetime(0) NULL DEFAULT NULL,
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_news
-- ----------------------------
DROP TABLE IF EXISTS `sys_news`;
CREATE TABLE `sys_news`  (
                             `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
                             `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
                             `type` int(11) NULL DEFAULT NULL COMMENT '类型',
                             `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
                             `keyword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关键字',
                             `record` int(11) NULL DEFAULT NULL COMMENT '浏览数',
                             `images` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
                             `summary` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '摘要',
                             `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
                             `status` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
                             `createtime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                             `updatetime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                             PRIMARY KEY (`id`) USING BTREE,
                             INDEX `fk_news_type_id`(`type`) USING BTREE,
                             CONSTRAINT `fk_news_type_id` FOREIGN KEY (`type`) REFERENCES `sys_news_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_news_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_news_type`;
CREATE TABLE `sys_news_type`  (
                                  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
                                  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型名称',
                                  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
                                  `status` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
                                  `createtime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                                  `updatetime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_properties
-- ----------------------------
DROP TABLE IF EXISTS `sys_properties`;
CREATE TABLE `sys_properties`  (
                                   `id` int(11) NOT NULL AUTO_INCREMENT,
                                   `sys_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                   `sys_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                   `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                   `status` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                   `createtime` datetime(0) NULL DEFAULT NULL,
                                   `updatetime` datetime(0) NULL DEFAULT NULL,
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_properties
-- ----------------------------
INSERT INTO `sys_properties` VALUES (1, 'test', 'test', 'test1', '0', '2019-03-15 13:42:51', '2019-03-15 00:59:25');
INSERT INTO `sys_properties` VALUES (2, 'test2', 'tse2', 'ceshi', '0', '2019-03-15 00:50:28', '2019-03-15 00:54:26');
INSERT INTO `sys_properties` VALUES (3, 'mapKey-Web', '65226e96e2ef9c4573ce0bcbde2b83da', '高德地图API密钥之Web端', '1', '2019-05-08 09:51:36', '2019-05-08 09:51:38');
INSERT INTO `sys_properties` VALUES (4, 'mapKey-WeChat', '9ce067ecea7e04cacc86e90494f971c5', '高德地图API密钥之微信小程序', '1', '2019-05-08 09:52:46', '2019-05-08 09:52:49');
INSERT INTO `sys_properties` VALUES (5, 'mapCoordinate', '116.082411,39.545898', '地图初始坐标', '1', '2019-05-08 09:55:05', '2019-05-08 09:55:07');
INSERT INTO `sys_properties` VALUES (6, 'mapZoom', '16', '地图初始化层级,范围是(3-18)', '1', '2019-05-08 10:50:07', '2019-05-08 10:50:34');
INSERT INTO `sys_properties` VALUES (7, 'indexNotice', '五一大特惠，全场商品全部九点八折，快来选购吧，还有更多优惠等着你哦！', '微信小程序通知信息', '1', '2019-05-09 09:02:40', '2019-05-09 09:02:42');
INSERT INTO `sys_properties` VALUES (8, 'indexImage', 'https://www.fsxhs.com/img/ind_pn2_bg.jpg', '微信小程序首页广告图片', '1', '2019-05-09 09:51:30', '2019-05-09 09:51:32');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `introduce` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `status` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `createtime` datetime(0) NULL DEFAULT NULL,
                             `updatetime` datetime(0) NULL DEFAULT NULL,
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'admin', 'admin', '1', '2019-03-08 17:13:53', '2019-03-13 17:13:56');


-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
                                  `rid` int(11) NOT NULL,
                                  `mid` int(11) NOT NULL,
                                  INDEX `fk_role`(`rid`) USING BTREE,
                                  INDEX `fk_menu`(`mid`) USING BTREE,
                                  CONSTRAINT `fk_menu` FOREIGN KEY (`mid`) REFERENCES `sys_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                  CONSTRAINT `fk_role` FOREIGN KEY (`rid`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 4);
INSERT INTO `sys_role_menu` VALUES (1, 5);
INSERT INTO `sys_role_menu` VALUES (1, 6);
INSERT INTO `sys_role_menu` VALUES (1, 7);
INSERT INTO `sys_role_menu` VALUES (1, 8);
INSERT INTO `sys_role_menu` VALUES (1, 9);
INSERT INTO `sys_role_menu` VALUES (1, 10);
INSERT INTO `sys_role_menu` VALUES (1, 11);
INSERT INTO `sys_role_menu` VALUES (1, 12);
INSERT INTO `sys_role_menu` VALUES (1, 13);
INSERT INTO `sys_role_menu` VALUES (1, 14);
INSERT INTO `sys_role_menu` VALUES (1, 15);
INSERT INTO `sys_role_menu` VALUES (1, 16);
INSERT INTO `sys_role_menu` VALUES (1, 17);
INSERT INTO `sys_role_menu` VALUES (1, 18);
INSERT INTO `sys_role_menu` VALUES (1, 19);
INSERT INTO `sys_role_menu` VALUES (1, 20);
INSERT INTO `sys_role_menu` VALUES (1, 21);
INSERT INTO `sys_role_menu` VALUES (1, 22);
INSERT INTO `sys_role_menu` VALUES (1, 23);
INSERT INTO `sys_role_menu` VALUES (1, 24);
INSERT INTO `sys_role_menu` VALUES (1, 25);
INSERT INTO `sys_role_menu` VALUES (1, 26);
INSERT INTO `sys_role_menu` VALUES (1, 27);


-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `password` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `avator` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `status` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `createtime` datetime(0) NULL DEFAULT NULL,
                             `updatetime` datetime(0) NULL DEFAULT NULL,
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'admin', '123', 'https://file.iviewui.com/dist/a0e88e83800f138b94d2414621bd9704.png', '1', '2019-03-08 11:31:29', '2019-03-20 11:31:40');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
                                  `uid` int(11) NOT NULL,
                                  `rid` int(11) NOT NULL,
                                  INDEX `fk_user`(`uid`) USING BTREE,
                                  INDEX `fk_role_user`(`rid`) USING BTREE,
                                  CONSTRAINT `fk_role_user` FOREIGN KEY (`rid`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                  CONSTRAINT `fk_user` FOREIGN KEY (`uid`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);

-- ----------------------------
-- Table structure for view_user
-- ----------------------------
DROP TABLE IF EXISTS `view_user`;
CREATE TABLE `view_user`  (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              `status` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              `createtime` datetime(0) NULL DEFAULT NULL,
                              `updatetime` datetime(0) NULL DEFAULT NULL,
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
