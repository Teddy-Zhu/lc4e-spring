/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50622
Source Host           : localhost:3306
Source Database       : lc4e

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2015-04-16 15:30:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for l4_areablock
-- ----------------------------
DROP TABLE IF EXISTS `l4_areablock`;
CREATE TABLE `l4_areablock` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intBlockedAreaId` int(11) NOT NULL,
  `intUserGroupId` int(11) NOT NULL,
  `dateCreateTime` datetime NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_areablock
-- ----------------------------

-- ----------------------------
-- Table structure for l4_areaclose
-- ----------------------------
DROP TABLE IF EXISTS `l4_areaclose`;
CREATE TABLE `l4_areaclose` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intAreaId` int(11) NOT NULL,
  `intUserGroupId` int(11) NOT NULL COMMENT 'operate person',
  `dateCreateTime` datetime NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_areaclose
-- ----------------------------

-- ----------------------------
-- Table structure for l4_areafollow
-- ----------------------------
DROP TABLE IF EXISTS `l4_areafollow`;
CREATE TABLE `l4_areafollow` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intUserId` int(11) NOT NULL,
  `intFollowedAreaId` int(11) NOT NULL,
  `dateCreateTime` datetime NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_areafollow
-- ----------------------------

-- ----------------------------
-- Table structure for l4_areas
-- ----------------------------
DROP TABLE IF EXISTS `l4_areas`;
CREATE TABLE `l4_areas` (
  `intAreaId` int(11) NOT NULL AUTO_INCREMENT,
  `intParentAreaId` int(11) NOT NULL,
  `strAreaAbbr` varchar(255) COLLATE utf8_bin NOT NULL,
  `strAreaName` varchar(255) COLLATE utf8_bin NOT NULL,
  `strAreaCss` varchar(255) COLLATE utf8_bin NOT NULL,
  `strAreaIcon` varchar(255) COLLATE utf8_bin NOT NULL,
  `isShow` int(11) NOT NULL DEFAULT '1',
  `dateCreateTime` datetime NOT NULL,
  `intUserId` int(11) NOT NULL,
  PRIMARY KEY (`intAreaId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_areas
-- ----------------------------

-- ----------------------------
-- Table structure for l4_articleblock
-- ----------------------------
DROP TABLE IF EXISTS `l4_articleblock`;
CREATE TABLE `l4_articleblock` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intUserId` int(11) NOT NULL,
  `intBlockedArticleId` int(11) NOT NULL,
  `dateCreateTime` datetime NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_articleblock
-- ----------------------------

-- ----------------------------
-- Table structure for l4_articlefollow
-- ----------------------------
DROP TABLE IF EXISTS `l4_articlefollow`;
CREATE TABLE `l4_articlefollow` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intUserId` int(11) NOT NULL,
  `intFollowedArticleId` int(11) NOT NULL,
  `dateCreateTime` date NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_articlefollow
-- ----------------------------

-- ----------------------------
-- Table structure for l4_articles
-- ----------------------------
DROP TABLE IF EXISTS `l4_articles`;
CREATE TABLE `l4_articles` (
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
-- Records of l4_articles
-- ----------------------------

-- ----------------------------
-- Table structure for l4_articletopdown
-- ----------------------------
DROP TABLE IF EXISTS `l4_articletopdown`;
CREATE TABLE `l4_articletopdown` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intArticleId` int(11) NOT NULL,
  `intUserId` int(11) NOT NULL,
  `intTopDown` int(11) NOT NULL DEFAULT '0',
  `dateCreate` datetime NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_articletopdown
-- ----------------------------

-- ----------------------------
-- Table structure for l4_comments
-- ----------------------------
DROP TABLE IF EXISTS `l4_comments`;
CREATE TABLE `l4_comments` (
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
-- Records of l4_comments
-- ----------------------------

-- ----------------------------
-- Table structure for l4_commenttopdown
-- ----------------------------
DROP TABLE IF EXISTS `l4_commenttopdown`;
CREATE TABLE `l4_commenttopdown` (
  `intCommentId` int(11) NOT NULL AUTO_INCREMENT,
  `intUserId` int(11) NOT NULL,
  `intTopDown` int(11) NOT NULL,
  `dateCreateTime` datetime NOT NULL,
  PRIMARY KEY (`intCommentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_commenttopdown
-- ----------------------------

-- ----------------------------
-- Table structure for l4_locations
-- ----------------------------
DROP TABLE IF EXISTS `l4_locations`;
CREATE TABLE `l4_locations` (
  `intLocationId` int(11) NOT NULL AUTO_INCREMENT,
  `intProvinceId` int(11) NOT NULL,
  `intCityId` int(11) NOT NULL,
  `intAreaId` int(11) NOT NULL,
  PRIMARY KEY (`intLocationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_locations
-- ----------------------------

-- ----------------------------
-- Table structure for l4_logname
-- ----------------------------
DROP TABLE IF EXISTS `l4_logname`;
CREATE TABLE `l4_logname` (
  `intLogNameId` int(11) NOT NULL AUTO_INCREMENT,
  `strLogNameAbbr` varchar(255) COLLATE utf8_bin NOT NULL,
  `strLogName` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`intLogNameId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_logname
-- ----------------------------

-- ----------------------------
-- Table structure for l4_logs
-- ----------------------------
DROP TABLE IF EXISTS `l4_logs`;
CREATE TABLE `l4_logs` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intLogNameId` int(11) NOT NULL,
  `intUserId` int(11) NOT NULL,
  `dateCreateTime` datetime NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_logs
-- ----------------------------

-- ----------------------------
-- Table structure for l4_menus
-- ----------------------------
DROP TABLE IF EXISTS `l4_menus`;
CREATE TABLE `l4_menus` (
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
-- Records of l4_menus
-- ----------------------------
INSERT INTO `l4_menus` VALUES ('1', '0', '0', '1', 'Menu', 'Menu', 'red', 'browser');
INSERT INTO `l4_menus` VALUES ('2', '1', '1', '5', 'user/signin', 'SignIn', 'basic teal  button', 'user');

-- ----------------------------
-- Table structure for l4_messages
-- ----------------------------
DROP TABLE IF EXISTS `l4_messages`;
CREATE TABLE `l4_messages` (
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
-- Records of l4_messages
-- ----------------------------

-- ----------------------------
-- Table structure for l4_userauthorization
-- ----------------------------
DROP TABLE IF EXISTS `l4_userauthorization`;
CREATE TABLE `l4_userauthorization` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intUserAuthorizationId` int(11) NOT NULL,
  `strUserAuthorizationAbbr` varchar(255) COLLATE utf8_bin NOT NULL,
  `strUserAuthorizationName` varchar(255) COLLATE utf8_bin NOT NULL,
  `intUserAuthorizationValue` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '0',
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_userauthorization
-- ----------------------------
INSERT INTO `l4_userauthorization` VALUES ('1', '1', 'login', 'Login', '0');

-- ----------------------------
-- Table structure for l4_userbasicinfo
-- ----------------------------
DROP TABLE IF EXISTS `l4_userbasicinfo`;
CREATE TABLE `l4_userbasicinfo` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intUserId` int(11) NOT NULL,
  `strUserName` varchar(255) COLLATE utf8_bin NOT NULL,
  `strUserNick` varchar(255) COLLATE utf8_bin NOT NULL,
  `strEmail` varchar(255) COLLATE utf8_bin NOT NULL,
  `strPassword` varchar(255) COLLATE utf8_bin NOT NULL,
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
-- Records of l4_userbasicinfo
-- ----------------------------
INSERT INTO `l4_userbasicinfo` VALUES ('1', '1', 'Robot', 'Robot', 'robot@lc4e.com', 'bf1414ea2c125354e4c8f41951637827', '10000', 'lc4e.com@robot!##!.banned', 'default.png', '0', '2015-04-14 15:03:39', '2015-04-14 15:03:43', '2015-04-14 15:03:53');

-- ----------------------------
-- Table structure for l4_userblock
-- ----------------------------
DROP TABLE IF EXISTS `l4_userblock`;
CREATE TABLE `l4_userblock` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intUserId` int(11) NOT NULL,
  `intBlockedUserId` int(11) NOT NULL,
  `dateCreateTime` datetime NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_userblock
-- ----------------------------

-- ----------------------------
-- Table structure for l4_userfollow
-- ----------------------------
DROP TABLE IF EXISTS `l4_userfollow`;
CREATE TABLE `l4_userfollow` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intUserId` int(11) NOT NULL,
  `intFollowedUserId` int(11) NOT NULL,
  `dateCreateTime` datetime NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_userfollow
-- ----------------------------

-- ----------------------------
-- Table structure for l4_usergroups
-- ----------------------------
DROP TABLE IF EXISTS `l4_usergroups`;
CREATE TABLE `l4_usergroups` (
  `intUserGroupId` int(11) NOT NULL AUTO_INCREMENT,
  `strUserGroupAbbr` varchar(255) COLLATE utf8_bin NOT NULL,
  `strUserGroupName` varchar(255) COLLATE utf8_bin NOT NULL,
  `intUserAuthorizationId` int(11) NOT NULL DEFAULT '1',
  `intAuthorizationLevel` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`intUserGroupId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_usergroups
-- ----------------------------
INSERT INTO `l4_usergroups` VALUES ('1', 'visitor', 'Vistor', '1', '0');
INSERT INTO `l4_usergroups` VALUES ('2', 'banned', 'Banned', '1', '0');
INSERT INTO `l4_usergroups` VALUES ('3', 'regular', 'Regular', '1', '1');
INSERT INTO `l4_usergroups` VALUES ('4', 'system', 'System', '1', '100');
INSERT INTO `l4_usergroups` VALUES ('5', 'admin', 'Administrator', '1', '50');

-- ----------------------------
-- Table structure for l4_usermoney
-- ----------------------------
DROP TABLE IF EXISTS `l4_usermoney`;
CREATE TABLE `l4_usermoney` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intUserId` int(11) NOT NULL,
  `douMoney` decimal(10,0) NOT NULL,
  `dateModifiedTime` date NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_usermoney
-- ----------------------------

-- ----------------------------
-- Table structure for l4_users
-- ----------------------------
DROP TABLE IF EXISTS `l4_users`;
CREATE TABLE `l4_users` (
  `intUserId` int(11) NOT NULL AUTO_INCREMENT,
  `intUserGroupId` int(11) NOT NULL DEFAULT '1',
  `intUserAuthorizationId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`intUserId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_users
-- ----------------------------
INSERT INTO `l4_users` VALUES ('1', '4', '0');
