/*
Navicat MySQL Data Transfer

Source Server         : hsfeng
Source Server Version : 50559
Source Host           : 123.207.28.113:3306
Source Database       : scene

Target Server Type    : MYSQL
Target Server Version : 50559
File Encoding         : 65001

Date: 2018-06-17 09:42:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL COMMENT '管理员',
  `account` varchar(255) NOT NULL COMMENT '账号',
  `password` varchar(255) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for advertise
-- ----------------------------
DROP TABLE IF EXISTS `advertise`;
CREATE TABLE `advertise` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '轮播图id',
  `img` varchar(255) NOT NULL COMMENT '轮播图id',
  `level` int(11) NOT NULL COMMENT '层级，显示的先后顺序',
  `is_del` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1-存在，0-删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for data_detail
-- ----------------------------
DROP TABLE IF EXISTS `data_detail`;
CREATE TABLE `data_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据信息id',
  `scene_id` int(11) NOT NULL COMMENT '场景id',
  `guest` varchar(50) NOT NULL COMMENT '活动参加人名字',
  `is_attend` tinyint(1) NOT NULL COMMENT '是否参加 ，0-不参加 1-参加',
  `content` varchar(255) DEFAULT NULL COMMENT '备注',
  `ip` varchar(15) NOT NULL COMMENT '填写信息的ip',
  `times` date NOT NULL COMMENT '填写时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jifen_record
-- ----------------------------
DROP TABLE IF EXISTS `jifen_record`;
CREATE TABLE `jifen_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户积分记录id',
  `user_id` int(11) NOT NULL,
  `jifen` int(11) NOT NULL COMMENT '变动积分',
  `type` char(1) NOT NULL COMMENT '变动类型，1-充值。2-兑换',
  `descirbe` varchar(50) NOT NULL COMMENT '变动描述',
  `times` date NOT NULL COMMENT '变动时间',
  `is_del` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1-存在，0-删除',
  PRIMARY KEY (`id`),
  KEY `jifen_user_id` (`user_id`),
  KEY `jifen_del` (`is_del`) USING BTREE,
  CONSTRAINT `jifen_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息id',
  `user_id` int(11) NOT NULL,
  `content` varchar(255) NOT NULL COMMENT '消息内容',
  `times` date NOT NULL COMMENT '发布时间',
  `is_read` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1-已读 0–未读',
  `is_del` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1-存在 0-删除',
  PRIMARY KEY (`id`),
  KEY `message_user_id` (`user_id`),
  CONSTRAINT `message_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for music
-- ----------------------------
DROP TABLE IF EXISTS `music`;
CREATE TABLE `music` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '音乐库id',
  `name` varchar(50) NOT NULL COMMENT '音乐的名字',
  `url` varchar(255) NOT NULL COMMENT '音乐的链接',
  `length` varchar(10) NOT NULL COMMENT '时间长度',
  `is_del` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1-存在，0-删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6997 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for scene
-- ----------------------------
DROP TABLE IF EXISTS `scene`;
CREATE TABLE `scene` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '场景id',
  `user_id` int(11) NOT NULL,
  `code` varchar(50) NOT NULL COMMENT '访问码',
  `cover` varchar(255) DEFAULT NULL COMMENT '封面url',
  `music` varchar(255) DEFAULT '0' COMMENT '音乐id',
  `m_title` varchar(50) DEFAULT NULL COMMENT '音乐名字',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `describes` varchar(255) DEFAULT NULL COMMENT '描述',
  `times` date NOT NULL COMMENT '制作时间',
  `types` int(11) DEFAULT NULL COMMENT '场景类型',
  `hit_count` int(11) NOT NULL DEFAULT '1' COMMENT '点击次数，阅读量',
  `jifen` int(11) NOT NULL DEFAULT '0' COMMENT '积分',
  `from_scene` int(11) NOT NULL DEFAULT '0' COMMENT '不是原创显示父场景的id，原创显示0',
  `counts` int(11) NOT NULL DEFAULT '1' COMMENT '使用人数',
  `is_issue` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1-上架，默认为0',
  `is_delicate` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否精选，0-否，1-是',
  `is_del` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1-存在，0-删除',
  PRIMARY KEY (`id`),
  KEY `scene_user_id` (`user_id`),
  KEY `scene_del` (`is_del`) USING BTREE,
  KEY `scene_types` (`types`),
  KEY `scene_issue` (`is_issue`) USING BTREE,
  CONSTRAINT `scene_types` FOREIGN KEY (`types`) REFERENCES `scene_type` (`id`),
  CONSTRAINT `scene_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for scene_page
-- ----------------------------
DROP TABLE IF EXISTS `scene_page`;
CREATE TABLE `scene_page` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '场景详情页',
  `scene_id` int(11) NOT NULL,
  `current_page` int(11) NOT NULL COMMENT '当前页',
  `times` date NOT NULL COMMENT '创建时间',
  `background` varchar(255) DEFAULT NULL COMMENT '背景图片',
  `bgColor` varchar(50) DEFAULT NULL COMMENT '背景色',
  `content` text NOT NULL COMMENT '页面显示的内容',
  `is_example` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否为单页例子，1-是 0-否',
  `is_del` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1-存在，0-删除',
  PRIMARY KEY (`id`),
  KEY `page_scene_id` (`scene_id`),
  KEY `page_del` (`is_del`) USING BTREE,
  CONSTRAINT `page_scene_id` FOREIGN KEY (`scene_id`) REFERENCES `scene` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for scene_report
-- ----------------------------
DROP TABLE IF EXISTS `scene_report`;
CREATE TABLE `scene_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '举报记录id',
  `scene_id` int(11) NOT NULL COMMENT '被举报场景id',
  `ip` varchar(15) NOT NULL COMMENT '举报ip',
  `reason` varchar(50) NOT NULL COMMENT '举报理由',
  `is_dispose` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1-已处理，0-未处理',
  PRIMARY KEY (`id`),
  KEY `report_scene` (`scene_id`),
  CONSTRAINT `report_scene` FOREIGN KEY (`scene_id`) REFERENCES `scene` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for scene_type
-- ----------------------------
DROP TABLE IF EXISTS `scene_type`;
CREATE TABLE `scene_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '场景类型id',
  `title` varchar(50) NOT NULL COMMENT '场景标题',
  `is_del` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1-存在，0-删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sysfile
-- ----------------------------
DROP TABLE IF EXISTS `sysfile`;
CREATE TABLE `sysfile` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统素材id',
  `type` char(1) NOT NULL COMMENT '文件类型，0-背景，1-图片',
  `url` varchar(255) NOT NULL COMMENT '文件url',
  `is_del` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=329 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for upfile
-- ----------------------------
DROP TABLE IF EXISTS `upfile`;
CREATE TABLE `upfile` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '场景素材id',
  `user_id` int(11) NOT NULL,
  `type` char(1) NOT NULL COMMENT '上传文件类型，0-背景，1-图片',
  `url` varchar(255) NOT NULL COMMENT '文件访问路径',
  `times` date NOT NULL COMMENT '上传时间',
  `is_del` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1-存在，0-删除',
  PRIMARY KEY (`id`),
  KEY `file_user_id` (`user_id`),
  CONSTRAINT `file_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表id',
  `name` varchar(50) NOT NULL COMMENT '昵称',
  `photo` varchar(255) NOT NULL COMMENT '头像',
  `jifen` int(11) NOT NULL DEFAULT '5' COMMENT '积分，新用户有5积分',
  `is_member` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否会员，0-不是，1-是',
  `start_time` date DEFAULT NULL COMMENT '会员身份开始时间',
  `al_numbers` int(11) DEFAULT '0' COMMENT '已兑换',
  `numbers` int(11) DEFAULT NULL COMMENT '可兑换次数，成为会员为10次',
  `is_read` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0-无未读消息，1-有未读消息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_auth
-- ----------------------------
DROP TABLE IF EXISTS `user_auth`;
CREATE TABLE `user_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户授权表id',
  `user_id` int(11) NOT NULL,
  `account` varchar(20) NOT NULL COMMENT '账户',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `token` varchar(255) NOT NULL COMMENT '激活码',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '激活状态，0-未激活，1-激活',
  `times` bigint(20) NOT NULL COMMENT '有效时间戳',
  `type` char(1) NOT NULL COMMENT '1-邮箱',
  PRIMARY KEY (`id`),
  KEY `auth_user_id` (`user_id`),
  CONSTRAINT `auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
