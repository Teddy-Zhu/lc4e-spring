/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : lc4e

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2015-05-21 11:01:10
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
  `strAreaDescription` varchar(255) COLLATE utf8_bin NOT NULL,
  `strAreaCss` varchar(255) COLLATE utf8_bin NOT NULL,
  `strAreaIcon` varchar(255) COLLATE utf8_bin NOT NULL,
  `isShow` int(11) NOT NULL DEFAULT '1',
  `intUserId` int(11) NOT NULL,
  `dateCreateTime` datetime NOT NULL,
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
-- Table structure for l4_sys_area_collected
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_area_collected`;
CREATE TABLE `l4_sys_area_collected` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intUserId` int(11) NOT NULL,
  `intCollectedAreaId` int(11) NOT NULL,
  `dateCreateTime` datetime NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_area_collected
-- ----------------------------

-- ----------------------------
-- Table structure for l4_sys_comment
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_comment`;
CREATE TABLE `l4_sys_comment` (
  `intCommentId` int(11) NOT NULL AUTO_INCREMENT,
  `intTopicId` varchar(255) COLLATE utf8_bin NOT NULL,
  `intCommentTitle` varchar(255) COLLATE utf8_bin NOT NULL,
  `intCommentBody` varchar(255) COLLATE utf8_bin NOT NULL,
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
-- Table structure for l4_sys_comment_like
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_comment_like`;
CREATE TABLE `l4_sys_comment_like` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intCommentId` int(11) NOT NULL,
  `intUserId` int(11) NOT NULL,
  `dateCreateTime` datetime NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_comment_like
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
-- Table structure for l4_sys_log_name
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_log_name`;
CREATE TABLE `l4_sys_log_name` (
  `intLogNameId` int(11) NOT NULL AUTO_INCREMENT,
  `strLogNameAbbr` varchar(255) COLLATE utf8_bin NOT NULL,
  `strLogName` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`intLogNameId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_log_name
-- ----------------------------

-- ----------------------------
-- Table structure for l4_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_menu`;
CREATE TABLE `l4_sys_menu` (
  `intMenuId` int(11) NOT NULL AUTO_INCREMENT,
  `intParentMenuId` int(11) NOT NULL,
  `intMenuOrderId` int(11) NOT NULL,
  `strMenuPath` varchar(255) COLLATE utf8_bin NOT NULL,
  `strMenuName` varchar(255) COLLATE utf8_bin NOT NULL,
  `strMenuCss` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT 'basic',
  `strMenuIcon` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`intMenuId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_menu
-- ----------------------------
INSERT INTO `l4_sys_menu` VALUES ('1', '0', '1', 'Menu', 'Menu', 'basic', 'browser');
INSERT INTO `l4_sys_menu` VALUES ('2', '1', '2', '/', 'Home', 'basic', 'home');
INSERT INTO `l4_sys_menu` VALUES ('3', '1', '3', '/', 'Message', 'basic', 'mail');
INSERT INTO `l4_sys_menu` VALUES ('4', '1', '4', '/', 'Friends', 'basic', 'user');
INSERT INTO `l4_sys_menu` VALUES ('5', '1', '9', '/', 'Language', 'basic', 'font');
INSERT INTO `l4_sys_menu` VALUES ('6', '5', '1', '/', 'C/C++', 'basic', '');
INSERT INTO `l4_sys_menu` VALUES ('7', '5', '2', '/', 'Java', 'basic', '');
INSERT INTO `l4_sys_menu` VALUES ('8', '5', '3', '/', 'Javascript', 'basic', '');
INSERT INTO `l4_sys_menu` VALUES ('9', '5', '4', '/', 'Script', 'basic', '');
INSERT INTO `l4_sys_menu` VALUES ('10', '9', '1', '/', 'Python', 'basic', '');
INSERT INTO `l4_sys_menu` VALUES ('11', '9', '2', '/', 'Ruby', 'basic', '');
INSERT INTO `l4_sys_menu` VALUES ('12', '10', '2', '/', 'Python1', 'basic', 'user');
INSERT INTO `l4_sys_menu` VALUES ('13', '10', '4', '/', 'Python2', 'basic', '');

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
INSERT INTO `l4_sys_permission` VALUES ('1', 'user:login', 'Login', '0', '1');

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
INSERT INTO `l4_sys_role` VALUES ('1', 'visitor', 'Vistor', '', '1');
INSERT INTO `l4_sys_role` VALUES ('2', 'banned', 'Banned', '', '1');
INSERT INTO `l4_sys_role` VALUES ('3', 'regular', 'Regular', '', '1');
INSERT INTO `l4_sys_role` VALUES ('4', 'system', 'System', '', '1');
INSERT INTO `l4_sys_role` VALUES ('5', 'admin', 'Administrator', '', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_role_permission
-- ----------------------------
INSERT INTO `l4_sys_role_permission` VALUES ('1', '1', '1', '2015-04-25 10:33:30');

-- ----------------------------
-- Table structure for l4_sys_topic
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_topic`;
CREATE TABLE `l4_sys_topic` (
  `intTopicId` int(11) NOT NULL AUTO_INCREMENT,
  `intAreaId` int(11) NOT NULL,
  `intUserId` int(11) NOT NULL,
  `strTopicTitle` varchar(50) COLLATE utf8_bin NOT NULL,
  `strTopicBody` varchar(3000) COLLATE utf8_bin NOT NULL,
  `isHide` int(11) NOT NULL DEFAULT '0',
  `isDeleted` int(11) NOT NULL DEFAULT '0',
  `dateCreateTime` datetime NOT NULL,
  `dateModified` datetime NOT NULL,
  PRIMARY KEY (`intTopicId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_topic
-- ----------------------------

-- ----------------------------
-- Table structure for l4_sys_topic_blocked
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_topic_blocked`;
CREATE TABLE `l4_sys_topic_blocked` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intUserId` int(11) NOT NULL,
  `intBlockedTopicId` int(11) NOT NULL,
  `dateCreateTime` datetime NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_topic_blocked
-- ----------------------------

-- ----------------------------
-- Table structure for l4_sys_topic_collected
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_topic_collected`;
CREATE TABLE `l4_sys_topic_collected` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intUserId` int(11) NOT NULL,
  `intCollectedTopicId` int(11) NOT NULL,
  `dateCreateTime` date NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_topic_collected
-- ----------------------------

-- ----------------------------
-- Table structure for l4_sys_topic_like
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_topic_like`;
CREATE TABLE `l4_sys_topic_like` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intTopicId` int(11) NOT NULL,
  `intUserId` int(11) NOT NULL,
  `dateCreate` datetime NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_topic_like
-- ----------------------------

-- ----------------------------
-- Table structure for l4_sys_topic_status
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_topic_status`;
CREATE TABLE `l4_sys_topic_status` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intTopicId` int(11) NOT NULL,
  `intStatusId` int(11) NOT NULL,
  `dateCreateTime` date NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_topic_status
-- ----------------------------

-- ----------------------------
-- Table structure for l4_sys_topic_status_name
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_topic_status_name`;
CREATE TABLE `l4_sys_topic_status_name` (
  `intStatusId` int(11) NOT NULL AUTO_INCREMENT,
  `strStatusAbbr` varchar(255) COLLATE utf8_bin NOT NULL,
  `strStatusName` varchar(255) COLLATE utf8_bin NOT NULL,
  `strStatusDesription` varchar(255) COLLATE utf8_bin NOT NULL,
  `strStatusIcon` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`intStatusId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_topic_status_name
-- ----------------------------

-- ----------------------------
-- Table structure for l4_sys_topic_view
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_topic_view`;
CREATE TABLE `l4_sys_topic_view` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intTopicId` int(11) NOT NULL,
  `intViewCount` int(11) NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_topic_view
-- ----------------------------

-- ----------------------------
-- Table structure for l4_sys_user_locations
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_user_locations`;
CREATE TABLE `l4_sys_user_locations` (
  `intLocationId` int(11) NOT NULL AUTO_INCREMENT,
  `intProvinceId` int(11) NOT NULL,
  `intCityId` int(11) NOT NULL,
  `intAreaId` int(11) NOT NULL,
  PRIMARY KEY (`intLocationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_user_locations
-- ----------------------------

-- ----------------------------
-- Table structure for l4_sys_user_message
-- ----------------------------
DROP TABLE IF EXISTS `l4_sys_user_message`;
CREATE TABLE `l4_sys_user_message` (
  `intMessageId` int(11) NOT NULL AUTO_INCREMENT,
  `intUserId` int(11) NOT NULL COMMENT '0 system',
  `intDestUserId` int(11) NOT NULL COMMENT '0 to all',
  `intRead` int(11) NOT NULL COMMENT '0 no read 1 has read',
  `strMessageTitle` varchar(255) COLLATE utf8_bin NOT NULL,
  `strMessageBody` varchar(255) COLLATE utf8_bin NOT NULL,
  `dateCreateTime` datetime NOT NULL,
  `dateModifiedTime` datetime NOT NULL,
  PRIMARY KEY (`intMessageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_sys_user_message
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
  `intLocked` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`intUserId`),
  UNIQUE KEY `username` (`strUserName`) USING HASH,
  UNIQUE KEY `useremail` (`strUserMail`) USING HASH,
  UNIQUE KEY `usernick` (`strUserNick`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_user
-- ----------------------------
INSERT INTO `l4_user` VALUES ('1', 'teddy', '', '', '6c38b2badf41b9434c4f268c6e68e2b8', 'e00cf29975db54821b157756b527ebab', '0');

-- ----------------------------
-- Table structure for l4_user_balance
-- ----------------------------
DROP TABLE IF EXISTS `l4_user_balance`;
CREATE TABLE `l4_user_balance` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intUserId` int(11) NOT NULL,
  `douBalance` decimal(10,0) NOT NULL,
  `dateModifiedTime` date NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_user_balance
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_user_basicinfo
-- ----------------------------

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
-- Table structure for l4_user_role
-- ----------------------------
DROP TABLE IF EXISTS `l4_user_role`;
CREATE TABLE `l4_user_role` (
  `intId` int(11) NOT NULL AUTO_INCREMENT,
  `intUserId` int(11) NOT NULL,
  `intRoleId` int(11) NOT NULL,
  `dateCreated` date NOT NULL,
  PRIMARY KEY (`intId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of l4_user_role
-- ----------------------------
INSERT INTO `l4_user_role` VALUES ('1', '1', '1', '2015-04-25');

-- ----------------------------
-- View structure for vw_user_role_permission
-- ----------------------------
DROP VIEW IF EXISTS `vw_user_role_permission`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `vw_user_role_permission` AS select `l4_user`.`intUserId` AS `intUserId`,`l4_user`.`strUserName` AS `strUserName`,`l4_user`.`strUserMail` AS `strUserMail`,`l4_user`.`strUserNick` AS `strUserNick`,`l4_user`.`strUserPass` AS `strUserPass`,`l4_user`.`strUserPassSalt` AS `strUserPassSalt`,`l4_user`.`intLocked` AS `intLocked`,`l4_user_role`.`dateCreated` AS `userRoleCreated`,`l4_sys_role_permission`.`dateCreated` AS `rolePermissionCreated`,`l4_sys_permission`.`strPermissionAbbr` AS `strPermissionAbbr`,`l4_sys_permission`.`strPermissionName` AS `strPermissionName`,`l4_sys_permission`.`strPermissionDescription` AS `strPermissionDescription`,`l4_sys_permission`.`intAvaliable` AS `permissionAvaliable`,`l4_sys_permission`.`intPermissionId` AS `intPermissionId`,`l4_sys_role`.`strRoleAbbr` AS `strRoleAbbr`,`l4_sys_role`.`strRoleName` AS `strRoleName`,`l4_sys_role`.`strRoleDesciption` AS `strRoleDesciption`,`l4_sys_role`.`intAvailable` AS `roleAvailable`,`l4_sys_role`.`intRoleId` AS `intRoleId` from ((((`l4_user` left join `l4_user_role` on((`l4_user`.`intUserId` = `l4_user_role`.`intUserId`))) left join `l4_sys_role` on((`l4_user_role`.`intRoleId` = `l4_sys_role`.`intRoleId`))) left join `l4_sys_role_permission` on((`l4_sys_role`.`intRoleId` = `l4_sys_role_permission`.`intRoleId`))) left join `l4_sys_permission` on((`l4_sys_role_permission`.`intPermissionId` = `l4_sys_permission`.`intPermissionId`))) ;
