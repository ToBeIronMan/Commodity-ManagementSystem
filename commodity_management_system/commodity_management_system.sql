/*
Navicat MySQL Data Transfer

Source Server         : ip
Source Server Version : 80015
Source Host           : 39.108.219.151:3306
Source Database       : commodity_management_system

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2020-03-11 14:00:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `number` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `price` varchar(14) DEFAULT NULL,
  `quantity` bigint(20) NOT NULL,
  `image_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES ('1236821318610665473', 'pen', '文具', '153', '11', '/file/image/pen.jpg');
INSERT INTO `commodity` VALUES ('1236822464163168258', 'booknote', 'book', '20', '12', '/file/image/book.jpg');
INSERT INTO `commodity` VALUES ('1236823286376775681', 'watch', 'watch', '13', '13', '/file/image/watch.jpg');
INSERT INTO `commodity` VALUES ('1236823704272060417', 'blackbook', 'blackbook', '23', '23', '/file/image/book.jpg');
INSERT INTO `commodity` VALUES ('1236823878025297922', 'blackwatch', 'blackwatch', '88', '990', '/file/image/watch.jpg');
INSERT INTO `commodity` VALUES ('1236824163955195905', 'whitepen', 'whitepen', '13', '13', '/file/image/pen.jpg');
INSERT INTO `commodity` VALUES ('1236824334587871233', 'whitepen', 'whitepen', '11', '11', '/file/image/pen.jpg');
INSERT INTO `commodity` VALUES ('1236824626180079618', 'readbook', 'readlook', '88', '11', '/file/image/book.jpg');
INSERT INTO `commodity` VALUES ('1236856053407924226', 'hellowatch', '4', '88', '11', '/file/image/watch.jpg');
INSERT INTO `commodity` VALUES ('1236868722312708097', '东坡肉', '食物', '4', '5', '/file/image/1.jpg');
INSERT INTO `commodity` VALUES ('1236895391659266049', 'redbook', 'readlook', '88', '11', '/file/image/book.jpg');
INSERT INTO `commodity` VALUES ('1237167236847198209', 'bluepen', '11', '88', '11', '/file/image/pen.jpg');
INSERT INTO `commodity` VALUES ('1237250540555788289', '东坡肉', '食物', '80', '1', '/file/image/1.jpg');
INSERT INTO `commodity` VALUES ('1237316384070275073', 'biao', '12', '12', '12', '/file/image/watch.jpg');
INSERT INTO `commodity` VALUES ('1237388353805434882', 'books', '文具', '212', '12', '/file/image/book.jpg');

-- ----------------------------
-- Table structure for super_user
-- ----------------------------
DROP TABLE IF EXISTS `super_user`;
CREATE TABLE `super_user` (
  `super_user_id` bigint(20) NOT NULL,
  `super_user_name` varchar(255) NOT NULL,
  `super_user_password` varchar(255) NOT NULL,
  `super_user_phone` varchar(11) NOT NULL,
  `super_user_head_image_adress` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`super_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of super_user
-- ----------------------------
INSERT INTO `super_user` VALUES ('1', 'admin', '1', '110', null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `user_phone` varchar(11) NOT NULL,
  `user_head_image_adress` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', '2', '2', '1111111111', null);
INSERT INTO `user` VALUES ('9', '1', '1', '1234124', null);
INSERT INTO `user` VALUES ('10', 'yoneh', '123', '15727252593', null);
INSERT INTO `user` VALUES ('12', '123', '123', '15727252593', null);
INSERT INTO `user` VALUES ('13', '122', '1', '11111', null);
INSERT INTO `user` VALUES ('14', '177', '111', '111', null);
INSERT INTO `user` VALUES ('19', '90', '1', '18995743388', null);
INSERT INTO `user` VALUES ('21', 'qqq', '123', '123', null);
INSERT INTO `user` VALUES ('22', '45', '45', '111', null);
INSERT INTO `user` VALUES ('23', '109', '109', '18995743388', null);
INSERT INTO `user` VALUES ('24', 'eee', 'qwe', '15727252593', null);
INSERT INTO `user` VALUES ('25', 'rrr', '123', '15727252593', null);
INSERT INTO `user` VALUES ('31', '121', '121', '18995743388', null);
INSERT INTO `user` VALUES ('32', '888', '888', '18995743388', null);
INSERT INTO `user` VALUES ('33', 'yanyihui', '123', '15727252593', null);
INSERT INTO `user` VALUES ('34', '21', '12', '121212', null);

-- ----------------------------
-- Table structure for user_permission
-- ----------------------------
DROP TABLE IF EXISTS `user_permission`;
CREATE TABLE `user_permission` (
  `user_id` bigint(20) NOT NULL,
  `add_permission` int(11) unsigned DEFAULT '0',
  `delete_permission` int(11) unsigned DEFAULT '0',
  `update_permission` int(11) unsigned DEFAULT '0',
  `look_permission` int(11) unsigned DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_permission
-- ----------------------------
INSERT INTO `user_permission` VALUES ('2', '1', '1', '1', '1');
INSERT INTO `user_permission` VALUES ('3', '0', '0', '0', '0');
INSERT INTO `user_permission` VALUES ('6', '0', '0', '0', '0');
INSERT INTO `user_permission` VALUES ('7', '0', '0', '0', '0');
INSERT INTO `user_permission` VALUES ('8', '0', '0', '0', '0');
INSERT INTO `user_permission` VALUES ('9', '0', '0', '0', '0');
INSERT INTO `user_permission` VALUES ('10', '0', '0', '0', '0');
INSERT INTO `user_permission` VALUES ('11', '0', '0', '0', '0');
INSERT INTO `user_permission` VALUES ('12', '0', '0', '0', '0');
INSERT INTO `user_permission` VALUES ('23', '0', '0', '0', '0');
INSERT INTO `user_permission` VALUES ('29', '0', '0', '0', '0');
INSERT INTO `user_permission` VALUES ('31', '0', '0', '0', '0');
INSERT INTO `user_permission` VALUES ('32', '0', '0', '0', '0');
INSERT INTO `user_permission` VALUES ('33', '0', '0', '0', '0');
