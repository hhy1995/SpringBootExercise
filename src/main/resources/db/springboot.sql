/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : springboot

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 23/06/2019 08:47:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hhy_mood
-- ----------------------------
DROP TABLE IF EXISTS `hhy_mood`;
CREATE TABLE `hhy_mood`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `praise_num` int(11) DEFAULT NULL,
  `publish_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hhy_mood
-- ----------------------------
INSERT INTO `hhy_mood` VALUES ('1', '一条测试说说', '1', 2, '2019-06-22 13:26:17');

-- ----------------------------
-- Table structure for hhy_role
-- ----------------------------
DROP TABLE IF EXISTS `hhy_role`;
CREATE TABLE `hhy_role`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hhy_role
-- ----------------------------
INSERT INTO `hhy_role` VALUES ('1', 'ADMIN');
INSERT INTO `hhy_role` VALUES ('2', 'USER');

-- ----------------------------
-- Table structure for hhy_user
-- ----------------------------
DROP TABLE IF EXISTS `hhy_user`;
CREATE TABLE `hhy_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hhy_user
-- ----------------------------
INSERT INTO `hhy_user` VALUES ('1', 'hhy', 'hhy');
INSERT INTO `hhy_user` VALUES ('2', 'chen', 'chen');
INSERT INTO `hhy_user` VALUES ('3', 'xue', 'xue');
INSERT INTO `hhy_user` VALUES ('4', 'lalala', 'lalala');

-- ----------------------------
-- Table structure for hhy_user_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `hhy_user_role_rel`;
CREATE TABLE `hhy_user_role_rel`  (
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hhy_user_role_rel
-- ----------------------------
INSERT INTO `hhy_user_role_rel` VALUES ('2', '2');
INSERT INTO `hhy_user_role_rel` VALUES ('1', '1');

SET FOREIGN_KEY_CHECKS = 1;
