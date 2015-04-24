/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50622
Source Host           : localhost:3306
Source Database       : lc4e

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2015-04-24 12:58:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for l4_sys_area
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_area`;
CREATE TABLE `l4_sys_area` (
  `intAreaId` int(11) NOT NULL AUTO_INCREMENT,
  `intParentAreaId` int(11) NOT NULL,
  `strAreaAbbr` varchar(255) COLLATE utf8_bin NOT NULL,
  `strAreaName` varchar(255) COLLATE utf8_bin NOT NULL,
  `strAreaCss` varchar(255) COLLATE utf8_bin NOT NULL,
  `strAreaIcon` varchar(255) COLLATE utf8_bin NOT NULL,
  `isShow` int(11) NOT NULL DEFAULT '1',
  `dateCreateTime` datetime NOT NULL,
  `intUserId` int(11) NOT NULL,
  PRIMARY KEY (`intAreaId`),
  UNIQUE KEY `areaabbr` (`strAreaAbbr`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_area
-- ----------------------------

-- ----------------------------
-- Table structure for l4_sys_area_blocked
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_area_blocked`;
CREATE TABLE `l4_sys_area_blocked` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intBlockedAreaId` int(11) NOT NULL,
  `intUserGroupId` int(11) NOT NULL,
  `dateCreateTime` datetime NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_area_blocked
-- ----------------------------

-- ----------------------------
-- Table structure for l4_sys_area_closed
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_area_closed`;
CREATE TABLE `l4_sys_area_closed` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intAreaId` int(11) NOT NULL,
  `intUserGroupId` int(11) NOT NULL COMMENT 'operate person',
  `dateCreateTime` datetime NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_area_closed
-- ----------------------------

-- ----------------------------
-- Table structure for l4_sys_area_followed
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_area_followed`;
CREATE TABLE `l4_sys_area_followed` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intUserId` int(11) NOT NULL,
  `intFollowedAreaId` int(11) NOT NULL,
  `dateCreateTime` datetime NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_area_followed
-- ----------------------------

-- ----------------------------
-- Table structure for l4_sys_article
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_article`;
CREATE TABLE `l4_sys_article` (
  `intArticleId` int(11) NOT NULL AUTO_INCREMENT,
  `intAreaId` int(11) NOT NULL,
  `intUserId` int(11) NOT NULL,
  `strArticleTitle` varchar(50) COLLATE utf8_bin NOT NULL,
  `strArticleContent` varchar(3000) COLLATE utf8_bin NOT NULL,
  `isHide` int(11) NOT NULL DEFAULT '0',
  `isDeleted` int(11) NOT NULL DEFAULT '0',
  `dateCreateTime` datetime NOT NULL,
  `dateModified` datetime NOT NULL,
  PRIMARY KEY (`intArticleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_article
-- ----------------------------

-- ----------------------------
-- Table structure for l4_sys_article_blocked
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_article_blocked`;
CREATE TABLE `l4_sys_article_blocked` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intUserId` int(11) NOT NULL,
  `intBlockedArticleId` int(11) NOT NULL,
  `dateCreateTime` datetime NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_article_blocked
-- ----------------------------

-- ----------------------------
-- Table structure for l4_sys_article_followed
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_article_followed`;
CREATE TABLE `l4_sys_article_followed` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intUserId` int(11) NOT NULL,
  `intFollowedArticleId` int(11) NOT NULL,
  `dateCreateTime` date NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_article_followed
-- ----------------------------

-- ----------------------------
-- Table structure for l4_sys_article_record
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_article_record`;
CREATE TABLE `l4_sys_article_record` (
  `intId` int(11) NOT NULL,
  `intArticleId` int(11) NOT NULL,
  `intHotCount` int(11) NOT NULL,
  `intViewCount` int(11) NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_article_record
-- ----------------------------

-- ----------------------------
-- Table structure for l4_sys_article_topdown
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_article_topdown`;
CREATE TABLE `l4_sys_article_topdown` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intArticleId` int(11) NOT NULL,
  `intUserId` int(11) NOT NULL,
  `intTopDown` int(11) NOT NULL DEFAULT '0',
  `dateCreate` datetime NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_article_topdown
-- ----------------------------

-- ----------------------------
-- Table structure for l4_sys_comment
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_comment`;
CREATE TABLE `l4_sys_comment` (
  `intCommentId` int(11) NOT NULL AUTO_INCREMENT,
  `intArticleId` varchar(255) COLLATE utf8_bin NOT NULL,
  `intCommentTitle` varchar(255) COLLATE utf8_bin NOT NULL,
  `intCommentContent` varchar(255) COLLATE utf8_bin NOT NULL,
  `intUserId` int(11) NOT NULL,
  `isHide` int(11) NOT NULL DEFAULT '0',
  `isDeleted` int(11) NOT NULL DEFAULT '0',
  `dateCreateTime` datetime NOT NULL,
  `dateModifiedTime` datetime NOT NULL,
  PRIMARY KEY (`intCommentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_comment
-- ----------------------------

-- ----------------------------
-- Table structure for l4_sys_comment_topdown
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_comment_topdown`;
CREATE TABLE `l4_sys_comment_topdown` (
  `intCommentId` int(11) NOT NULL AUTO_INCREMENT,
  `intUserId` int(11) NOT NULL,
  `intTopDown` int(11) NOT NULL,
  `dateCreateTime` datetime NOT NULL,
  PRIMARY KEY (`intCommentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_comment_topdown
-- ----------------------------

-- ----------------------------
-- Table structure for l4_sys_locations
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_locations`;
CREATE TABLE `l4_sys_locations` (
  `intLocationId` int(11) NOT NULL AUTO_INCREMENT,
  `intProvinceId` int(11) NOT NULL,
  `intCityId` int(11) NOT NULL,
  `intAreaId` int(11) NOT NULL,
  PRIMARY KEY (`intLocationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_locations
-- ----------------------------

-- ----------------------------
-- Table structure for l4_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_log`;
CREATE TABLE `l4_sys_log` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intLogNameId` int(11) NOT NULL,
  `intUserId` int(11) NOT NULL,
  `dateCreateTime` datetime NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for l4_sys_logname
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_logname`;
CREATE TABLE `l4_sys_logname` (
  `intLogNameId` int(11) NOT NULL AUTO_INCREMENT,
  `strLogNameAbbr` varchar(255) COLLATE utf8_bin NOT NULL,
  `strLogName` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`intLogNameId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_logname
-- ----------------------------

-- ----------------------------
-- Table structure for l4_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_menu`;
CREATE TABLE `l4_sys_menu` (
  `intMenuId` int(11) NOT NULL AUTO_INCREMENT,
  `intParentMenuId` int(11) NOT NULL,
  `intMenuLocation` int(11) NOT NULL DEFAULT '0' COMMENT '0 left 1 right',
  `intMenuOrderId` int(11) NOT NULL,
  `strMenuPath` varchar(255) COLLATE utf8_bin NOT NULL,
  `strMenuName` varchar(255) COLLATE utf8_bin NOT NULL,
  `strMenuCss` varchar(255) COLLATE utf8_bin NOT NULL,
  `strMenuIcon` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`intMenuId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_menu
-- ----------------------------
INSERT INTO `l4_sys_menu` VALUES ('1', '0', '0', '1', 'Menu', 'Menu', 'red', 'browser');
INSERT INTO `l4_sys_menu` VALUES ('2', '1', '1', '5', 'user/signin', 'SignIn', 'basic teal  button', 'user');

-- ----------------------------
-- Table structure for l4_sys_message
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_message`;
CREATE TABLE `l4_sys_message` (
  `intMessageId` int(11) NOT NULL AUTO_INCREMENT,
  `intUserId` int(11) NOT NULL COMMENT '0 system',
  `intDestUserId` int(11) NOT NULL COMMENT '0 to all',
  `intRead` int(11) NOT NULL COMMENT '0 no read 1 has read',
  `strMessageTitle` varchar(255) COLLATE utf8_bin NOT NULL,
  `strMessageContent` varchar(255) COLLATE utf8_bin NOT NULL,
  `dateCreateTime` datetime NOT NULL,
  `dateModifiedTime` datetime NOT NULL,
  PRIMARY KEY (`intMessageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_message
-- ----------------------------

-- ----------------------------
-- Table structure for l4_sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_permission`;
CREATE TABLE `l4_sys_permission` (
  `intPermissionId` int(11) NOT NULL AUTO_INCREMENT,
  `strPermissionAbbr` varchar(255) COLLATE utf8_bin NOT NULL,
  `strPermissionName` varchar(255) COLLATE utf8_bin NOT NULL,
  `strPermissionDescription` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `intAvaliable` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`intPermissionId`),
  UNIQUE KEY `permissionname` (`strPermissionAbbr`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_permission
-- ----------------------------
INSERT INTO `l4_sys_permission` VALUES ('1', 'login', 'Login', '0', '0');

-- ----------------------------
-- Table structure for l4_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_role`;
CREATE TABLE `l4_sys_role` (
  `intRoleId` int(11) NOT NULL AUTO_INCREMENT,
  `strRoleAbbr` varchar(255) COLLATE utf8_bin NOT NULL,
  `strRoleName` varchar(255) COLLATE utf8_bin NOT NULL,
  `strRoleDesciption` varchar(255) COLLATE utf8_bin NOT NULL,
  `intAvailable` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`intRoleId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_role
-- ----------------------------
INSERT INTO `l4_sys_role` VALUES ('1', 'visitor', 'Vistor', '', '0');
INSERT INTO `l4_sys_role` VALUES ('2', 'banned', 'Banned', '', '0');
INSERT INTO `l4_sys_role` VALUES ('3', 'regular', 'Regular', '', '0');
INSERT INTO `l4_sys_role` VALUES ('4', 'system', 'System', '', '0');
INSERT INTO `l4_sys_role` VALUES ('5', 'admin', 'Administrator', '', '0');

-- ----------------------------
-- Table structure for l4_sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_role_permission`;
CREATE TABLE `l4_sys_role_permission` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intRoleId` int(11) NOT NULL,
  `intPermissionId` int(11) NOT NULL,
  `dateCreated` datetime NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for l4_user
-- ----------------------------
DROP TABLE IF EXISTS `l4_user`;
CREATE TABLE `l4_user` (
  `intUserId` int(11) NOT NULL AUTO_INCREMENT,
  `strUserName` varchar(255) COLLATE utf8_bin NOT NULL,
  `strUserMail` varchar(255) COLLATE utf8_bin NOT NULL,
  `strUserNick` varchar(255) COLLATE utf8_bin NOT NULL,
  `strUserPass` varchar(255) COLLATE utf8_bin NOT NULL,
  `strUserPassSalt` varchar(255) COLLATE utf8_bin NOT NULL,
  `intLocked` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '1',
  PRIMARY KEY (`intUserId`),
  UNIQUE KEY `username` (`strUserName`) USING HASH,
  UNIQUE KEY `useremail` (`strUserMail`) USING HASH,
  UNIQUE KEY `usernick` (`strUserNick`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_user
-- ----------------------------

-- ----------------------------
-- Table structure for l4_user_basicinfo
-- ----------------------------
DROP TABLE IF EXISTS `l4_user_basicinfo`;
CREATE TABLE `l4_user_basicinfo` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intUserId` int(11) NOT NULL,
  `strPhoneNumber` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `strSign` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'personal sign',
  `strAvatar` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT 'default.png',
  `intLocationId` int(11) DEFAULT '0',
  `dateBirthDay` datetime DEFAULT NULL,
  `dateCreate` datetime NOT NULL,
  `dateModified` datetime NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_user_basicinfo
-- ----------------------------
INSERT INTO `l4_user_basicinfo` VALUES ('1', '1', '10000', 'lc4e.com@robot!##!.banned', 'default.png', '0', '2015-04-14 15:03:39', '2015-04-14 15:03:43', '2015-04-14 15:03:53');

-- ----------------------------
-- Table structure for l4_user_blocked
-- ----------------------------
DROP TABLE IF EXISTS `l4_user_blocked`;
CREATE TABLE `l4_user_blocked` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intUserId` int(11) NOT NULL,
  `intBlockedUserId` int(11) NOT NULL,
  `dateCreateTime` datetime NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_user_blocked
-- ----------------------------

-- ----------------------------
-- Table structure for l4_user_followed
-- ----------------------------
DROP TABLE IF EXISTS `l4_user_followed`;
CREATE TABLE `l4_user_followed` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intUserId` int(11) NOT NULL,
  `intFollowedUserId` int(11) NOT NULL,
  `dateCreateTime` datetime NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_user_followed
-- ----------------------------

-- ----------------------------
-- Table structure for l4_user_money
-- ----------------------------
DROP TABLE IF EXISTS `l4_user_money`;
CREATE TABLE `l4_user_money` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intUserId` int(11) NOT NULL,
  `douMoney` decimal(10,0) NOT NULL,
  `dateModifiedTime` date NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_user_money
-- ----------------------------

-- ----------------------------
-- Table structure for l4_user_role
-- ----------------------------
DROP TABLE IF EXISTS `l4_user_role`;
CREATE TABLE `l4_user_role` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intUserId` int(11) NOT NULL,
  `intRoleId` int(11) NOT NULL,
  `dateCreated` date NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_user_role
-- ----------------------------
