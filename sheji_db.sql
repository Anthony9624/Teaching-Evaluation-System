/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50067
Source Host           : localhost:3306
Source Database       : sheji_db

Target Server Type    : MYSQL
Target Server Version : 50067
File Encoding         : 65001

Date: 2020-06-22 17:46:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_jilu`
-- ----------------------------
DROP TABLE IF EXISTS `t_jilu`;
CREATE TABLE `t_jilu` (
  `id` int(11) NOT NULL auto_increment,
  `denfen` int(11) NOT NULL,
  `leixing` varchar(255) default NULL,
  `shijian` varchar(255) default NULL,
  `fromuserid` int(11) default NULL,
  `touserid` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKCB501C13AF5B973A` (`fromuserid`),
  KEY `FKCB501C13C4D16D4B` (`touserid`),
  CONSTRAINT `FKCB501C13C4D16D4B` FOREIGN KEY (`touserid`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FKCB501C13AF5B973A` FOREIGN KEY (`fromuserid`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_jilu
-- ----------------------------
INSERT INTO `t_jilu` VALUES ('1', '0', '教师自评', '2020-06-22 16:34:45', '3', '3');
INSERT INTO `t_jilu` VALUES ('2', '0', '学生评教', '2020-06-22 16:37:57', '4', '3');
INSERT INTO `t_jilu` VALUES ('3', '38', '教师自评', '2020-06-22 17:17:27', '8', '8');
INSERT INTO `t_jilu` VALUES ('4', '24', '教师互评', '2020-06-22 17:19:12', '8', '10');

-- ----------------------------
-- Table structure for `t_jiluitem`
-- ----------------------------
DROP TABLE IF EXISTS `t_jiluitem`;
CREATE TABLE `t_jiluitem` (
  `id` int(11) NOT NULL auto_increment,
  `defen` int(11) NOT NULL,
  `dengji` varchar(255) default NULL,
  `shijian` varchar(255) default NULL,
  `jiluid` int(11) default NULL,
  `neirongid` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK2F1F55A6AFA1424A` (`jiluid`),
  KEY `FK2F1F55A619932C2C` (`neirongid`),
  CONSTRAINT `FK2F1F55A619932C2C` FOREIGN KEY (`neirongid`) REFERENCES `t_neirong` (`id`),
  CONSTRAINT `FK2F1F55A6AFA1424A` FOREIGN KEY (`jiluid`) REFERENCES `t_jilu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_jiluitem
-- ----------------------------
INSERT INTO `t_jiluitem` VALUES ('1', '10', '优秀', '2020-06-22 17:17:28', '3', '4');
INSERT INTO `t_jiluitem` VALUES ('2', '8', '良好', '2020-06-22 17:17:28', '3', '3');
INSERT INTO `t_jiluitem` VALUES ('3', '10', '优秀', '2020-06-22 17:17:28', '3', '2');
INSERT INTO `t_jiluitem` VALUES ('4', '10', '优秀', '2020-06-22 17:17:28', '3', '1');
INSERT INTO `t_jiluitem` VALUES ('5', '8', '良好', '2020-06-22 17:19:12', '4', '4');
INSERT INTO `t_jiluitem` VALUES ('6', '0', '不及格', '2020-06-22 17:19:12', '4', '3');
INSERT INTO `t_jiluitem` VALUES ('7', '6', '及格', '2020-06-22 17:19:12', '4', '2');
INSERT INTO `t_jiluitem` VALUES ('8', '10', '优秀', '2020-06-22 17:19:12', '4', '1');

-- ----------------------------
-- Table structure for `t_neirong`
-- ----------------------------
DROP TABLE IF EXISTS `t_neirong`;
CREATE TABLE `t_neirong` (
  `id` int(11) NOT NULL auto_increment,
  `deletestatus` int(11) NOT NULL,
  `neirong` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_neirong
-- ----------------------------
INSERT INTO `t_neirong` VALUES ('1', '0', '备课质量');
INSERT INTO `t_neirong` VALUES ('2', '0', '上课质量');
INSERT INTO `t_neirong` VALUES ('3', '0', '师生互动');
INSERT INTO `t_neirong` VALUES ('4', '0', '课后作业');

-- ----------------------------
-- Table structure for `t_tongji`
-- ----------------------------
DROP TABLE IF EXISTS `t_tongji`;
CREATE TABLE `t_tongji` (
  `id` int(11) NOT NULL auto_increment,
  `huping` int(11) NOT NULL,
  `pingjiao` int(11) NOT NULL,
  `pingjun1` double NOT NULL,
  `pingjun2` double NOT NULL,
  `shuliang1` int(11) NOT NULL,
  `shuliang2` int(11) NOT NULL,
  `ziping` int(11) NOT NULL,
  `userid` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK491F27FEC2F56710` (`userid`),
  CONSTRAINT `FK491F27FEC2F56710` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_tongji
-- ----------------------------
INSERT INTO `t_tongji` VALUES ('3', '0', '0', '0', '0', '0', '1', '0', '3');
INSERT INTO `t_tongji` VALUES ('4', '0', '0', '0', '0', '0', '0', '0', '6');
INSERT INTO `t_tongji` VALUES ('5', '0', '0', '0', '0', '0', '0', '0', '7');
INSERT INTO `t_tongji` VALUES ('6', '0', '0', '0', '0', '0', '0', '0', '8');
INSERT INTO `t_tongji` VALUES ('7', '0', '0', '0', '0', '0', '0', '0', '9');
INSERT INTO `t_tongji` VALUES ('8', '0', '0', '0', '0', '0', '0', '0', '10');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL auto_increment,
  `banji` varchar(255) default NULL,
  `createtime` datetime default NULL,
  `deletestatus` int(11) NOT NULL,
  `password` varchar(255) default NULL,
  `role` int(11) NOT NULL,
  `shouji` varchar(255) default NULL,
  `truename` varchar(255) default NULL,
  `username` varchar(255) default NULL,
  `xueyuan` varchar(255) default NULL,
  `zhichen` varchar(255) default NULL,
  `zhuanye` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', null, null, '0', '111111', '1', null, 'admin', 'admin', null, null, null);
INSERT INTO `t_user` VALUES ('2', '01', '2020-06-22 16:17:49', '0', '123456', '3', '13679398765', '张三', '1', '信息系', null, '计算机');
INSERT INTO `t_user` VALUES ('3', null, '2020-06-22 16:18:33', '0', '123456', '2', '13545679087', '老杨', '01', '信息系', 'JSP教师', null);
INSERT INTO `t_user` VALUES ('4', '01', '2020-06-22 16:36:24', '0', '123456', '3', '13645678909', '一', '2', '信息系', null, '计算机');
INSERT INTO `t_user` VALUES ('5', '01', '2020-06-22 16:50:31', '0', '123456', '3', '13423454321', '33', '3', '信息系', null, '计算机');
INSERT INTO `t_user` VALUES ('6', null, '2020-06-22 16:51:11', '0', '123456', '2', '13621232345', '老张', '02', '信息系', '教师', null);
INSERT INTO `t_user` VALUES ('7', null, '2020-06-22 16:54:28', '0', '123456', '2', '13643567890', '老胡', '03', '信息系', '教师', null);
INSERT INTO `t_user` VALUES ('8', null, '2020-06-22 16:55:02', '0', '123456', '2', '13543213456', '老郭', '04', '信息系', '教师', null);
INSERT INTO `t_user` VALUES ('9', null, '2020-06-22 16:55:36', '0', '123456', '2', '13654322345', '老彭', '05', '信息系', '教师', null);
INSERT INTO `t_user` VALUES ('10', null, '2020-06-22 16:56:11', '0', '123456', '2', '13743567898', '老王', '06', '信息系', '教师', null);
INSERT INTO `t_user` VALUES ('11', null, '2020-06-22 17:28:07', '0', '123456', '2', '13432123456', '老李', '07', '信息系', '教师', null);
INSERT INTO `t_user` VALUES ('12', null, '2020-06-22 17:28:49', '0', '123456', '2', '13234595690', '老齐', '08', '信息系', '教师', null);
INSERT INTO `t_user` VALUES ('13', null, '2020-06-22 17:31:52', '0', '123456', '2', '13232323232', '老迪', '09', '信息系', '教师', null);
INSERT INTO `t_user` VALUES ('14', null, '2020-06-22 17:32:47', '0', '123456', '2', '13554322345', '老曲', '10', '信息系', '教师', null);
INSERT INTO `t_user` VALUES ('15', '02', '2020-06-22 17:34:01', '0', '123456', '3', '13643215543', '4', '4', '信息系', null, '计算机');
INSERT INTO `t_user` VALUES ('16', '3', '2020-06-22 17:37:17', '0', '123456', '3', '13934668900', '小二', '5', '信息系', null, '计算机');
