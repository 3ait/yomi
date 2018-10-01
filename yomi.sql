/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : yomi

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2018-10-01 14:49:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `menu_id` bigint(11) DEFAULT NULL,
  `product_attr_value_id` bigint(11) DEFAULT NULL,
  `title` varchar(1024) NOT NULL,
  `sub_title` varchar(1024) DEFAULT NULL,
  `url_title` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `content` longtext,
  `keywords` varchar(255) DEFAULT NULL,
  `default_src` varchar(1024) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1' COMMENT '0 不显示 1显示',
  `click_num` int(11) DEFAULT '0',
  `hot` tinyint(4) DEFAULT '0',
  `recommend` tinyint(4) DEFAULT '0',
  `position` int(11) DEFAULT '1' COMMENT '文章顺序',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `seo_keywords` varchar(1024) DEFAULT NULL,
  `seo_desc` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `menu_id` (`menu_id`),
  CONSTRAINT `article_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', '141', null, 'Frequently Asked Questions', 'Sub title', 'Questions', 'Author ', null, 'keywords', null, '1', '0', null, null, '0', '2017-12-04 16:15:59', '2018-08-24 18:02:01', '', '');
INSERT INTO `article` VALUES ('2', '143', null, 'Terms & Conditions', 'Terms & Conditions', 'Terms', '', null, '', null, '1', '0', null, null, '0', '2017-12-13 12:15:24', '2018-05-03 11:25:15', '', '');
INSERT INTO `article` VALUES ('3', '144', null, 'Shipping Policy', '', 'Shipping', '', null, '', null, '1', '0', null, null, '0', '2017-12-13 12:15:45', '2018-05-03 11:23:23', '', '');
INSERT INTO `article` VALUES ('4', '145', null, 'About Us', '', 'About Us', '', null, '', null, '1', '0', null, null, '0', '2017-12-13 12:16:10', '2018-05-22 11:35:27', '', '');
INSERT INTO `article` VALUES ('5', '155', null, '联系我们', '', 'Contact Us', 'author', null, 'contact us', null, '0', '0', null, null, '0', '2017-12-13 12:16:21', '2018-07-03 12:13:00', '', '');
INSERT INTO `article` VALUES ('6', '149', null, 'Index', 'slider-top', 'index-ad', '', null, '', null, '1', '0', null, null, '0', '2017-12-20 14:48:38', '2018-09-10 13:25:07', '', '');
INSERT INTO `article` VALUES ('9', '156', null, '品牌活动title', '品牌活动 Subtitle', 'brand activity', '', null, '品牌活动 keywords', null, '1', '0', null, null, '0', '2018-05-18 21:46:32', '2018-07-03 18:01:10', '', '');
INSERT INTO `article` VALUES ('10', '154', null, '促销产品', '线上体验 subtitle', 'onlineshop', '', null, '线上体验 keywords', null, '1', '0', null, null, '0', '2018-05-18 21:51:20', '2018-09-11 11:46:45', '', '');
INSERT INTO `article` VALUES ('11', '155', null, '联系我们', '联系我们 subtitle', '', '', null, '联系我们 keywords', null, '1', '0', null, null, '0', '2018-05-18 21:53:10', '2018-09-11 11:30:58', '', '');
INSERT INTO `article` VALUES ('35', '223', null, '溯源流程', 'productprocess', '', '', '', '', null, '1', '0', null, null, '0', '2018-08-24 14:59:17', '2018-09-11 11:26:28', '', '');

-- ----------------------------
-- Table structure for attachment
-- ----------------------------
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `menu_id` bigint(11) DEFAULT NULL COMMENT '二级分类Id',
  `article_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `title` varchar(512) DEFAULT NULL,
  `file_name` varchar(1024) NOT NULL,
  `file_path` varchar(1024) NOT NULL,
  `position` int(11) DEFAULT '0',
  `description` text,
  `create_time` datetime DEFAULT NULL,
  `href` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `menu_id` (`menu_id`),
  KEY `article_id` (`article_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `attachment_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `attachment_ibfk_2` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `attachment_ibfk_3` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attachment
-- ----------------------------

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(1024) DEFAULT '',
  `img_src` varchar(1024) DEFAULT NULL,
  `file_path` varchar(1024) DEFAULT NULL,
  `position` int(255) DEFAULT '1' COMMENT '1 首页大图切换',
  `create_time` datetime DEFAULT NULL,
  `description` varchar(1024) DEFAULT '',
  `type` varchar(255) DEFAULT NULL COMMENT '分类',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES ('50', '', 'resources/banner/banner1481748322.jpg', '', null, null, null, 'index_1');
INSERT INTO `banner` VALUES ('51', '', 'resources/banner/banner1481748386.jpg', '', null, null, null, 'index_1');
INSERT INTO `banner` VALUES ('52', '', 'resources/banner/banner1481748421.jpg', '', null, null, null, 'index_1');
INSERT INTO `banner` VALUES ('53', '', 'resources/banner/banner1481748452.jpg', '', null, null, null, 'index_1');
INSERT INTO `banner` VALUES ('54', '', 'resources/banner/banner1481748497.jpg', '', null, null, null, 'index_1');

-- ----------------------------
-- Table structure for branch
-- ----------------------------
DROP TABLE IF EXISTS `branch`;
CREATE TABLE `branch` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_name` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `address` varchar(1024) DEFAULT NULL,
  `description` text,
  `type` varchar(255) DEFAULT NULL,
  `status` tinyint(255) NOT NULL DEFAULT '1',
  `contact_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of branch
-- ----------------------------
INSERT INTO `branch` VALUES ('1', 'b1', 'leo@3a.co.nz', '0221828384', '0221828358', '', '', null, '1', 'b1');

-- ----------------------------
-- Table structure for branch_product
-- ----------------------------
DROP TABLE IF EXISTS `branch_product`;
CREATE TABLE `branch_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `stock` int(11) DEFAULT '0',
  `price1` double(10,2) DEFAULT '0.00',
  `price2` double(10,2) DEFAULT '0.00',
  PRIMARY KEY (`id`),
  KEY `branch_id` (`branch_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `branch_product_ibfk_1` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`),
  CONSTRAINT `branch_product_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=575 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of branch_product
-- ----------------------------
INSERT INTO `branch_product` VALUES ('1', '1', '16', '0', null, null);
INSERT INTO `branch_product` VALUES ('2', '1', '17', '0', null, null);
INSERT INTO `branch_product` VALUES ('3', '1', '18', '0', null, null);
INSERT INTO `branch_product` VALUES ('4', '1', '19', '0', null, null);
INSERT INTO `branch_product` VALUES ('5', '1', '20', '0', null, null);
INSERT INTO `branch_product` VALUES ('6', '1', '21', '0', null, null);
INSERT INTO `branch_product` VALUES ('7', '1', '22', '0', null, null);
INSERT INTO `branch_product` VALUES ('8', '1', '23', '0', null, null);
INSERT INTO `branch_product` VALUES ('9', '1', '24', '0', null, null);
INSERT INTO `branch_product` VALUES ('10', '1', '25', '0', null, null);
INSERT INTO `branch_product` VALUES ('11', '1', '26', '0', null, null);
INSERT INTO `branch_product` VALUES ('12', '1', '27', '0', null, null);
INSERT INTO `branch_product` VALUES ('13', '1', '28', '0', null, null);
INSERT INTO `branch_product` VALUES ('14', '1', '29', '0', null, null);
INSERT INTO `branch_product` VALUES ('15', '1', '30', '0', null, null);
INSERT INTO `branch_product` VALUES ('16', '1', '31', '0', null, null);
INSERT INTO `branch_product` VALUES ('17', '1', '32', '0', null, null);
INSERT INTO `branch_product` VALUES ('18', '1', '33', '0', null, null);
INSERT INTO `branch_product` VALUES ('19', '1', '34', '0', null, null);
INSERT INTO `branch_product` VALUES ('20', '1', '35', '0', null, null);
INSERT INTO `branch_product` VALUES ('21', '1', '36', '0', null, null);
INSERT INTO `branch_product` VALUES ('22', '1', '37', '0', null, null);
INSERT INTO `branch_product` VALUES ('23', '1', '38', '0', null, null);
INSERT INTO `branch_product` VALUES ('24', '1', '39', '0', null, null);
INSERT INTO `branch_product` VALUES ('25', '1', '40', '0', null, null);
INSERT INTO `branch_product` VALUES ('26', '1', '41', '0', null, null);
INSERT INTO `branch_product` VALUES ('27', '1', '42', '0', null, null);
INSERT INTO `branch_product` VALUES ('28', '1', '43', '0', null, null);
INSERT INTO `branch_product` VALUES ('29', '1', '44', '0', null, null);
INSERT INTO `branch_product` VALUES ('30', '1', '45', '0', null, null);
INSERT INTO `branch_product` VALUES ('31', '1', '46', '0', null, null);
INSERT INTO `branch_product` VALUES ('32', '1', '47', '0', null, null);
INSERT INTO `branch_product` VALUES ('33', '1', '48', '0', null, null);
INSERT INTO `branch_product` VALUES ('34', '1', '49', '0', null, null);
INSERT INTO `branch_product` VALUES ('35', '1', '50', '0', null, null);
INSERT INTO `branch_product` VALUES ('36', '1', '51', '0', null, null);
INSERT INTO `branch_product` VALUES ('37', '1', '52', '0', null, null);
INSERT INTO `branch_product` VALUES ('38', '1', '53', '0', null, null);
INSERT INTO `branch_product` VALUES ('39', '1', '54', '0', null, null);
INSERT INTO `branch_product` VALUES ('40', '1', '55', '0', null, null);
INSERT INTO `branch_product` VALUES ('41', '1', '56', '0', null, null);
INSERT INTO `branch_product` VALUES ('42', '1', '57', '0', null, null);
INSERT INTO `branch_product` VALUES ('43', '1', '58', '0', null, null);
INSERT INTO `branch_product` VALUES ('44', '1', '59', '0', null, null);
INSERT INTO `branch_product` VALUES ('45', '1', '60', '0', null, null);
INSERT INTO `branch_product` VALUES ('46', '1', '61', '0', null, null);
INSERT INTO `branch_product` VALUES ('47', '1', '62', '0', null, null);
INSERT INTO `branch_product` VALUES ('48', '1', '63', '0', null, null);
INSERT INTO `branch_product` VALUES ('49', '1', '64', '0', null, null);
INSERT INTO `branch_product` VALUES ('50', '1', '65', '0', null, null);
INSERT INTO `branch_product` VALUES ('51', '1', '66', '0', null, null);
INSERT INTO `branch_product` VALUES ('52', '1', '67', '0', null, null);
INSERT INTO `branch_product` VALUES ('53', '1', '68', '0', null, null);
INSERT INTO `branch_product` VALUES ('54', '1', '69', '0', null, null);
INSERT INTO `branch_product` VALUES ('55', '1', '70', '0', null, null);
INSERT INTO `branch_product` VALUES ('56', '1', '71', '0', null, null);
INSERT INTO `branch_product` VALUES ('57', '1', '72', '0', null, null);
INSERT INTO `branch_product` VALUES ('58', '1', '73', '0', null, null);
INSERT INTO `branch_product` VALUES ('59', '1', '74', '0', null, null);
INSERT INTO `branch_product` VALUES ('60', '1', '75', '0', null, null);
INSERT INTO `branch_product` VALUES ('61', '1', '76', '0', null, null);
INSERT INTO `branch_product` VALUES ('62', '1', '77', '0', null, null);
INSERT INTO `branch_product` VALUES ('63', '1', '78', '0', null, null);
INSERT INTO `branch_product` VALUES ('64', '1', '79', '0', null, null);
INSERT INTO `branch_product` VALUES ('65', '1', '80', '0', null, null);
INSERT INTO `branch_product` VALUES ('66', '1', '81', '0', null, null);
INSERT INTO `branch_product` VALUES ('67', '1', '82', '0', null, null);
INSERT INTO `branch_product` VALUES ('68', '1', '83', '0', null, null);
INSERT INTO `branch_product` VALUES ('69', '1', '84', '0', null, null);
INSERT INTO `branch_product` VALUES ('70', '1', '85', '0', null, null);
INSERT INTO `branch_product` VALUES ('71', '1', '86', '0', null, null);
INSERT INTO `branch_product` VALUES ('72', '1', '87', '0', null, null);
INSERT INTO `branch_product` VALUES ('73', '1', '88', '0', null, null);
INSERT INTO `branch_product` VALUES ('74', '1', '89', '0', null, null);
INSERT INTO `branch_product` VALUES ('75', '1', '90', '0', null, null);
INSERT INTO `branch_product` VALUES ('76', '1', '91', '0', null, null);
INSERT INTO `branch_product` VALUES ('77', '1', '92', '0', null, null);
INSERT INTO `branch_product` VALUES ('78', '1', '93', '0', null, null);
INSERT INTO `branch_product` VALUES ('79', '1', '94', '0', null, null);
INSERT INTO `branch_product` VALUES ('80', '1', '95', '0', null, null);
INSERT INTO `branch_product` VALUES ('81', '1', '96', '0', null, null);
INSERT INTO `branch_product` VALUES ('82', '1', '97', '0', null, null);
INSERT INTO `branch_product` VALUES ('83', '1', '98', '0', null, null);
INSERT INTO `branch_product` VALUES ('84', '1', '99', '0', null, null);
INSERT INTO `branch_product` VALUES ('85', '1', '100', '0', null, null);
INSERT INTO `branch_product` VALUES ('86', '1', '101', '0', null, null);
INSERT INTO `branch_product` VALUES ('87', '1', '102', '0', null, null);
INSERT INTO `branch_product` VALUES ('88', '1', '103', '0', null, null);
INSERT INTO `branch_product` VALUES ('89', '1', '104', '0', null, null);
INSERT INTO `branch_product` VALUES ('90', '1', '105', '0', null, null);
INSERT INTO `branch_product` VALUES ('91', '1', '106', '0', null, null);
INSERT INTO `branch_product` VALUES ('92', '1', '107', '0', null, null);
INSERT INTO `branch_product` VALUES ('93', '1', '108', '0', null, null);
INSERT INTO `branch_product` VALUES ('94', '1', '109', '0', null, null);
INSERT INTO `branch_product` VALUES ('95', '1', '110', '0', null, null);
INSERT INTO `branch_product` VALUES ('96', '1', '111', '0', null, null);
INSERT INTO `branch_product` VALUES ('97', '1', '112', '0', null, null);
INSERT INTO `branch_product` VALUES ('98', '1', '113', '0', null, null);
INSERT INTO `branch_product` VALUES ('99', '1', '114', '0', null, null);
INSERT INTO `branch_product` VALUES ('100', '1', '115', '0', null, null);
INSERT INTO `branch_product` VALUES ('101', '1', '116', '0', null, null);
INSERT INTO `branch_product` VALUES ('102', '1', '117', '0', null, null);
INSERT INTO `branch_product` VALUES ('103', '1', '118', '0', null, null);
INSERT INTO `branch_product` VALUES ('104', '1', '119', '0', null, null);
INSERT INTO `branch_product` VALUES ('105', '1', '120', '0', null, null);
INSERT INTO `branch_product` VALUES ('106', '1', '121', '0', null, null);
INSERT INTO `branch_product` VALUES ('107', '1', '122', '0', null, null);
INSERT INTO `branch_product` VALUES ('108', '1', '123', '0', null, null);
INSERT INTO `branch_product` VALUES ('109', '1', '124', '0', null, null);
INSERT INTO `branch_product` VALUES ('110', '1', '125', '0', null, null);
INSERT INTO `branch_product` VALUES ('111', '1', '126', '0', null, null);
INSERT INTO `branch_product` VALUES ('112', '1', '127', '0', null, null);
INSERT INTO `branch_product` VALUES ('113', '1', '128', '0', null, null);
INSERT INTO `branch_product` VALUES ('114', '1', '129', '0', null, null);
INSERT INTO `branch_product` VALUES ('115', '1', '130', '0', null, null);
INSERT INTO `branch_product` VALUES ('116', '1', '131', '0', null, null);
INSERT INTO `branch_product` VALUES ('117', '1', '132', '0', null, null);
INSERT INTO `branch_product` VALUES ('118', '1', '133', '0', null, null);
INSERT INTO `branch_product` VALUES ('119', '1', '134', '0', null, null);
INSERT INTO `branch_product` VALUES ('120', '1', '135', '0', null, null);
INSERT INTO `branch_product` VALUES ('121', '1', '136', '0', null, null);
INSERT INTO `branch_product` VALUES ('122', '1', '137', '0', null, null);
INSERT INTO `branch_product` VALUES ('123', '1', '138', '0', null, null);
INSERT INTO `branch_product` VALUES ('124', '1', '139', '0', null, null);
INSERT INTO `branch_product` VALUES ('125', '1', '140', '0', null, null);
INSERT INTO `branch_product` VALUES ('126', '1', '141', '0', null, null);
INSERT INTO `branch_product` VALUES ('127', '1', '142', '0', null, null);
INSERT INTO `branch_product` VALUES ('128', '1', '143', '0', null, null);
INSERT INTO `branch_product` VALUES ('129', '1', '144', '0', null, null);
INSERT INTO `branch_product` VALUES ('130', '1', '145', '0', null, null);
INSERT INTO `branch_product` VALUES ('131', '1', '146', '0', null, null);
INSERT INTO `branch_product` VALUES ('132', '1', '147', '0', null, null);
INSERT INTO `branch_product` VALUES ('133', '1', '148', '0', null, null);
INSERT INTO `branch_product` VALUES ('134', '1', '149', '0', null, null);
INSERT INTO `branch_product` VALUES ('135', '1', '150', '0', null, null);
INSERT INTO `branch_product` VALUES ('136', '1', '151', '0', null, null);
INSERT INTO `branch_product` VALUES ('137', '1', '152', '0', null, null);
INSERT INTO `branch_product` VALUES ('138', '1', '153', '0', null, null);
INSERT INTO `branch_product` VALUES ('139', '1', '154', '0', null, null);
INSERT INTO `branch_product` VALUES ('140', '1', '155', '0', null, null);
INSERT INTO `branch_product` VALUES ('141', '1', '156', '0', null, null);
INSERT INTO `branch_product` VALUES ('142', '1', '157', '0', null, null);
INSERT INTO `branch_product` VALUES ('143', '1', '158', '0', null, null);
INSERT INTO `branch_product` VALUES ('144', '1', '159', '0', null, null);
INSERT INTO `branch_product` VALUES ('145', '1', '160', '0', null, null);
INSERT INTO `branch_product` VALUES ('146', '1', '161', '0', null, null);
INSERT INTO `branch_product` VALUES ('147', '1', '162', '0', null, null);
INSERT INTO `branch_product` VALUES ('148', '1', '163', '0', null, null);
INSERT INTO `branch_product` VALUES ('149', '1', '164', '0', null, null);
INSERT INTO `branch_product` VALUES ('150', '1', '165', '0', null, null);
INSERT INTO `branch_product` VALUES ('151', '1', '166', '0', null, null);
INSERT INTO `branch_product` VALUES ('152', '1', '167', '0', null, null);
INSERT INTO `branch_product` VALUES ('153', '1', '168', '0', null, null);
INSERT INTO `branch_product` VALUES ('154', '1', '169', '0', null, null);
INSERT INTO `branch_product` VALUES ('155', '1', '170', '0', null, null);
INSERT INTO `branch_product` VALUES ('156', '1', '171', '0', null, null);
INSERT INTO `branch_product` VALUES ('157', '1', '172', '0', null, null);
INSERT INTO `branch_product` VALUES ('158', '1', '173', '0', null, null);
INSERT INTO `branch_product` VALUES ('159', '1', '174', '0', null, null);
INSERT INTO `branch_product` VALUES ('160', '1', '175', '0', null, null);
INSERT INTO `branch_product` VALUES ('161', '1', '176', '0', null, null);
INSERT INTO `branch_product` VALUES ('162', '1', '177', '0', null, null);
INSERT INTO `branch_product` VALUES ('163', '1', '178', '0', null, null);
INSERT INTO `branch_product` VALUES ('164', '1', '179', '0', null, null);
INSERT INTO `branch_product` VALUES ('165', '1', '180', '0', null, null);
INSERT INTO `branch_product` VALUES ('166', '1', '181', '0', null, null);
INSERT INTO `branch_product` VALUES ('167', '1', '182', '0', null, null);
INSERT INTO `branch_product` VALUES ('168', '1', '183', '0', null, null);
INSERT INTO `branch_product` VALUES ('169', '1', '184', '0', null, null);
INSERT INTO `branch_product` VALUES ('170', '1', '185', '0', null, null);
INSERT INTO `branch_product` VALUES ('171', '1', '186', '0', null, null);
INSERT INTO `branch_product` VALUES ('172', '1', '187', '0', null, null);
INSERT INTO `branch_product` VALUES ('173', '1', '188', '0', null, null);
INSERT INTO `branch_product` VALUES ('174', '1', '189', '0', null, null);
INSERT INTO `branch_product` VALUES ('175', '1', '190', '0', null, null);
INSERT INTO `branch_product` VALUES ('176', '1', '191', '0', null, null);
INSERT INTO `branch_product` VALUES ('177', '1', '192', '0', null, null);
INSERT INTO `branch_product` VALUES ('178', '1', '193', '0', null, null);
INSERT INTO `branch_product` VALUES ('179', '1', '194', '0', null, null);
INSERT INTO `branch_product` VALUES ('180', '1', '195', '0', null, null);
INSERT INTO `branch_product` VALUES ('181', '1', '196', '0', null, null);
INSERT INTO `branch_product` VALUES ('182', '1', '197', '0', null, null);
INSERT INTO `branch_product` VALUES ('183', '1', '198', '0', null, null);
INSERT INTO `branch_product` VALUES ('184', '1', '199', '0', null, null);
INSERT INTO `branch_product` VALUES ('185', '1', '200', '0', null, null);
INSERT INTO `branch_product` VALUES ('186', '1', '201', '0', null, null);
INSERT INTO `branch_product` VALUES ('187', '1', '202', '0', null, null);
INSERT INTO `branch_product` VALUES ('188', '1', '203', '0', null, null);
INSERT INTO `branch_product` VALUES ('189', '1', '204', '0', null, null);
INSERT INTO `branch_product` VALUES ('190', '1', '205', '0', null, null);
INSERT INTO `branch_product` VALUES ('191', '1', '206', '0', null, null);
INSERT INTO `branch_product` VALUES ('192', '1', '207', '0', null, null);
INSERT INTO `branch_product` VALUES ('193', '1', '208', '0', null, null);
INSERT INTO `branch_product` VALUES ('194', '1', '209', '0', null, null);
INSERT INTO `branch_product` VALUES ('195', '1', '210', '0', null, null);
INSERT INTO `branch_product` VALUES ('196', '1', '211', '0', null, null);
INSERT INTO `branch_product` VALUES ('197', '1', '212', '0', null, null);
INSERT INTO `branch_product` VALUES ('198', '1', '213', '0', null, null);
INSERT INTO `branch_product` VALUES ('199', '1', '214', '0', null, null);
INSERT INTO `branch_product` VALUES ('200', '1', '215', '0', null, null);
INSERT INTO `branch_product` VALUES ('201', '1', '216', '0', null, null);
INSERT INTO `branch_product` VALUES ('202', '1', '217', '0', null, null);
INSERT INTO `branch_product` VALUES ('203', '1', '218', '0', null, null);
INSERT INTO `branch_product` VALUES ('204', '1', '219', '0', null, null);
INSERT INTO `branch_product` VALUES ('205', '1', '220', '0', null, null);
INSERT INTO `branch_product` VALUES ('206', '1', '221', '0', null, null);
INSERT INTO `branch_product` VALUES ('207', '1', '222', '0', null, null);
INSERT INTO `branch_product` VALUES ('208', '1', '223', '0', null, null);
INSERT INTO `branch_product` VALUES ('209', '1', '224', '0', null, null);
INSERT INTO `branch_product` VALUES ('210', '1', '225', '0', null, null);
INSERT INTO `branch_product` VALUES ('211', '1', '226', '0', null, null);
INSERT INTO `branch_product` VALUES ('212', '1', '227', '0', null, null);
INSERT INTO `branch_product` VALUES ('213', '1', '228', '0', null, null);
INSERT INTO `branch_product` VALUES ('214', '1', '229', '0', null, null);
INSERT INTO `branch_product` VALUES ('215', '1', '230', '0', null, null);
INSERT INTO `branch_product` VALUES ('216', '1', '231', '0', null, null);
INSERT INTO `branch_product` VALUES ('217', '1', '232', '0', null, null);
INSERT INTO `branch_product` VALUES ('218', '1', '233', '0', null, null);
INSERT INTO `branch_product` VALUES ('219', '1', '234', '0', null, null);
INSERT INTO `branch_product` VALUES ('220', '1', '235', '0', null, null);
INSERT INTO `branch_product` VALUES ('221', '1', '236', '0', null, null);
INSERT INTO `branch_product` VALUES ('222', '1', '237', '0', null, null);
INSERT INTO `branch_product` VALUES ('223', '1', '238', '0', null, null);
INSERT INTO `branch_product` VALUES ('224', '1', '239', '0', null, null);
INSERT INTO `branch_product` VALUES ('225', '1', '240', '0', null, null);
INSERT INTO `branch_product` VALUES ('226', '1', '241', '0', null, null);
INSERT INTO `branch_product` VALUES ('227', '1', '242', '0', null, null);
INSERT INTO `branch_product` VALUES ('228', '1', '243', '0', null, null);
INSERT INTO `branch_product` VALUES ('229', '1', '244', '0', null, null);
INSERT INTO `branch_product` VALUES ('230', '1', '245', '0', null, null);
INSERT INTO `branch_product` VALUES ('231', '1', '246', '0', null, null);
INSERT INTO `branch_product` VALUES ('232', '1', '247', '0', null, null);
INSERT INTO `branch_product` VALUES ('233', '1', '248', '0', null, null);
INSERT INTO `branch_product` VALUES ('234', '1', '249', '0', null, null);
INSERT INTO `branch_product` VALUES ('235', '1', '250', '0', null, null);
INSERT INTO `branch_product` VALUES ('236', '1', '251', '0', null, null);
INSERT INTO `branch_product` VALUES ('237', '1', '252', '0', null, null);
INSERT INTO `branch_product` VALUES ('238', '1', '253', '0', null, null);
INSERT INTO `branch_product` VALUES ('239', '1', '254', '0', null, null);
INSERT INTO `branch_product` VALUES ('240', '1', '255', '0', null, null);
INSERT INTO `branch_product` VALUES ('241', '1', '256', '0', null, null);
INSERT INTO `branch_product` VALUES ('242', '1', '257', '0', null, null);
INSERT INTO `branch_product` VALUES ('243', '1', '258', '0', null, null);
INSERT INTO `branch_product` VALUES ('244', '1', '259', '0', null, null);
INSERT INTO `branch_product` VALUES ('245', '1', '260', '0', null, null);
INSERT INTO `branch_product` VALUES ('246', '1', '261', '0', null, null);
INSERT INTO `branch_product` VALUES ('247', '1', '262', '0', null, null);
INSERT INTO `branch_product` VALUES ('248', '1', '263', '0', null, null);
INSERT INTO `branch_product` VALUES ('249', '1', '264', '0', null, null);
INSERT INTO `branch_product` VALUES ('250', '1', '265', '0', null, null);
INSERT INTO `branch_product` VALUES ('251', '1', '266', '0', null, null);
INSERT INTO `branch_product` VALUES ('252', '1', '267', '0', null, null);
INSERT INTO `branch_product` VALUES ('253', '1', '268', '0', null, null);
INSERT INTO `branch_product` VALUES ('254', '1', '269', '0', null, null);
INSERT INTO `branch_product` VALUES ('255', '1', '270', '0', null, null);
INSERT INTO `branch_product` VALUES ('256', '1', '271', '0', null, null);
INSERT INTO `branch_product` VALUES ('257', '1', '272', '0', null, null);
INSERT INTO `branch_product` VALUES ('258', '1', '273', '0', null, null);
INSERT INTO `branch_product` VALUES ('259', '1', '274', '0', null, null);
INSERT INTO `branch_product` VALUES ('260', '1', '275', '0', null, null);
INSERT INTO `branch_product` VALUES ('261', '1', '276', '0', null, null);
INSERT INTO `branch_product` VALUES ('262', '1', '277', '0', null, null);
INSERT INTO `branch_product` VALUES ('263', '1', '278', '0', null, null);
INSERT INTO `branch_product` VALUES ('264', '1', '279', '0', null, null);
INSERT INTO `branch_product` VALUES ('265', '1', '280', '0', null, null);
INSERT INTO `branch_product` VALUES ('266', '1', '281', '0', null, null);
INSERT INTO `branch_product` VALUES ('267', '1', '282', '0', null, null);
INSERT INTO `branch_product` VALUES ('268', '1', '283', '0', null, null);
INSERT INTO `branch_product` VALUES ('269', '1', '284', '0', null, null);
INSERT INTO `branch_product` VALUES ('270', '1', '285', '0', null, null);
INSERT INTO `branch_product` VALUES ('271', '1', '286', '0', null, null);
INSERT INTO `branch_product` VALUES ('272', '1', '287', '0', null, null);
INSERT INTO `branch_product` VALUES ('273', '1', '288', '0', null, null);
INSERT INTO `branch_product` VALUES ('274', '1', '289', '0', null, null);
INSERT INTO `branch_product` VALUES ('275', '1', '290', '0', null, null);
INSERT INTO `branch_product` VALUES ('276', '1', '291', '0', null, null);
INSERT INTO `branch_product` VALUES ('277', '1', '292', '0', null, null);
INSERT INTO `branch_product` VALUES ('278', '1', '293', '0', null, null);
INSERT INTO `branch_product` VALUES ('279', '1', '294', '0', null, null);
INSERT INTO `branch_product` VALUES ('280', '1', '295', '0', null, null);
INSERT INTO `branch_product` VALUES ('281', '1', '296', '0', null, null);
INSERT INTO `branch_product` VALUES ('282', '1', '297', '0', null, null);
INSERT INTO `branch_product` VALUES ('283', '1', '298', '0', null, null);
INSERT INTO `branch_product` VALUES ('284', '1', '299', '0', null, null);
INSERT INTO `branch_product` VALUES ('285', '1', '300', '0', null, null);
INSERT INTO `branch_product` VALUES ('286', '1', '301', '0', null, null);
INSERT INTO `branch_product` VALUES ('287', '1', '302', '0', null, null);
INSERT INTO `branch_product` VALUES ('288', '1', '303', '0', null, null);
INSERT INTO `branch_product` VALUES ('289', '1', '304', '0', null, null);
INSERT INTO `branch_product` VALUES ('290', '1', '305', '0', null, null);
INSERT INTO `branch_product` VALUES ('291', '1', '306', '0', null, null);
INSERT INTO `branch_product` VALUES ('292', '1', '307', '0', null, null);
INSERT INTO `branch_product` VALUES ('293', '1', '308', '0', null, null);
INSERT INTO `branch_product` VALUES ('294', '1', '309', '0', null, null);
INSERT INTO `branch_product` VALUES ('295', '1', '310', '0', null, null);
INSERT INTO `branch_product` VALUES ('296', '1', '311', '0', null, null);
INSERT INTO `branch_product` VALUES ('297', '1', '312', '0', null, null);
INSERT INTO `branch_product` VALUES ('298', '1', '313', '0', null, null);
INSERT INTO `branch_product` VALUES ('299', '1', '314', '0', null, null);
INSERT INTO `branch_product` VALUES ('300', '1', '315', '0', null, null);
INSERT INTO `branch_product` VALUES ('301', '1', '316', '0', null, null);
INSERT INTO `branch_product` VALUES ('302', '1', '317', '0', null, null);
INSERT INTO `branch_product` VALUES ('303', '1', '318', '0', null, null);
INSERT INTO `branch_product` VALUES ('304', '1', '319', '0', null, null);
INSERT INTO `branch_product` VALUES ('305', '1', '320', '0', null, null);
INSERT INTO `branch_product` VALUES ('306', '1', '321', '0', null, null);
INSERT INTO `branch_product` VALUES ('307', '1', '322', '0', null, null);
INSERT INTO `branch_product` VALUES ('308', '1', '323', '0', null, null);
INSERT INTO `branch_product` VALUES ('309', '1', '324', '0', null, null);
INSERT INTO `branch_product` VALUES ('310', '1', '325', '0', null, null);
INSERT INTO `branch_product` VALUES ('311', '1', '326', '0', null, null);
INSERT INTO `branch_product` VALUES ('312', '1', '327', '0', null, null);
INSERT INTO `branch_product` VALUES ('313', '1', '328', '0', null, null);
INSERT INTO `branch_product` VALUES ('314', '1', '329', '0', null, null);
INSERT INTO `branch_product` VALUES ('315', '1', '330', '0', null, null);
INSERT INTO `branch_product` VALUES ('316', '1', '331', '0', null, null);
INSERT INTO `branch_product` VALUES ('317', '1', '332', '0', null, null);
INSERT INTO `branch_product` VALUES ('318', '1', '333', '0', null, null);
INSERT INTO `branch_product` VALUES ('319', '1', '334', '0', null, null);
INSERT INTO `branch_product` VALUES ('320', '1', '335', '0', null, null);
INSERT INTO `branch_product` VALUES ('321', '1', '336', '0', null, null);
INSERT INTO `branch_product` VALUES ('322', '1', '337', '0', null, null);
INSERT INTO `branch_product` VALUES ('323', '1', '338', '0', null, null);
INSERT INTO `branch_product` VALUES ('324', '1', '339', '0', null, null);
INSERT INTO `branch_product` VALUES ('325', '1', '340', '0', null, null);
INSERT INTO `branch_product` VALUES ('326', '1', '341', '0', null, null);
INSERT INTO `branch_product` VALUES ('327', '1', '342', '0', null, null);
INSERT INTO `branch_product` VALUES ('328', '1', '343', '0', null, null);
INSERT INTO `branch_product` VALUES ('329', '1', '344', '0', null, null);
INSERT INTO `branch_product` VALUES ('330', '1', '345', '0', null, null);
INSERT INTO `branch_product` VALUES ('331', '1', '346', '0', null, null);
INSERT INTO `branch_product` VALUES ('332', '1', '347', '0', null, null);
INSERT INTO `branch_product` VALUES ('333', '1', '348', '0', null, null);
INSERT INTO `branch_product` VALUES ('334', '1', '349', '0', null, null);
INSERT INTO `branch_product` VALUES ('335', '1', '350', '0', null, null);
INSERT INTO `branch_product` VALUES ('336', '1', '351', '0', null, null);
INSERT INTO `branch_product` VALUES ('337', '1', '352', '0', null, null);
INSERT INTO `branch_product` VALUES ('338', '1', '353', '0', null, null);
INSERT INTO `branch_product` VALUES ('339', '1', '354', '0', null, null);
INSERT INTO `branch_product` VALUES ('340', '1', '355', '0', null, null);
INSERT INTO `branch_product` VALUES ('341', '1', '356', '0', null, null);
INSERT INTO `branch_product` VALUES ('342', '1', '357', '0', null, null);
INSERT INTO `branch_product` VALUES ('343', '1', '358', '0', null, null);
INSERT INTO `branch_product` VALUES ('344', '1', '359', '0', null, null);
INSERT INTO `branch_product` VALUES ('345', '1', '360', '0', null, null);
INSERT INTO `branch_product` VALUES ('346', '1', '361', '0', null, null);
INSERT INTO `branch_product` VALUES ('347', '1', '362', '0', null, null);
INSERT INTO `branch_product` VALUES ('348', '1', '363', '0', null, null);
INSERT INTO `branch_product` VALUES ('349', '1', '364', '0', null, null);
INSERT INTO `branch_product` VALUES ('350', '1', '365', '0', null, null);
INSERT INTO `branch_product` VALUES ('351', '1', '366', '0', null, null);
INSERT INTO `branch_product` VALUES ('352', '1', '367', '0', null, null);
INSERT INTO `branch_product` VALUES ('353', '1', '368', '0', null, null);
INSERT INTO `branch_product` VALUES ('354', '1', '369', '0', null, null);
INSERT INTO `branch_product` VALUES ('355', '1', '370', '0', null, null);
INSERT INTO `branch_product` VALUES ('356', '1', '371', '0', null, null);
INSERT INTO `branch_product` VALUES ('357', '1', '372', '0', null, null);
INSERT INTO `branch_product` VALUES ('358', '1', '373', '0', null, null);
INSERT INTO `branch_product` VALUES ('359', '1', '374', '0', null, null);
INSERT INTO `branch_product` VALUES ('360', '1', '375', '0', null, null);
INSERT INTO `branch_product` VALUES ('361', '1', '376', '0', null, null);
INSERT INTO `branch_product` VALUES ('362', '1', '377', '0', null, null);
INSERT INTO `branch_product` VALUES ('363', '1', '378', '0', null, null);
INSERT INTO `branch_product` VALUES ('364', '1', '379', '0', null, null);
INSERT INTO `branch_product` VALUES ('365', '1', '380', '0', null, null);
INSERT INTO `branch_product` VALUES ('366', '1', '381', '0', null, null);
INSERT INTO `branch_product` VALUES ('367', '1', '382', '0', null, null);
INSERT INTO `branch_product` VALUES ('368', '1', '383', '0', null, null);
INSERT INTO `branch_product` VALUES ('369', '1', '384', '0', null, null);
INSERT INTO `branch_product` VALUES ('370', '1', '385', '0', null, null);
INSERT INTO `branch_product` VALUES ('371', '1', '386', '0', null, null);
INSERT INTO `branch_product` VALUES ('372', '1', '387', '0', null, null);
INSERT INTO `branch_product` VALUES ('373', '1', '388', '0', null, null);
INSERT INTO `branch_product` VALUES ('374', '1', '389', '0', null, null);
INSERT INTO `branch_product` VALUES ('375', '1', '390', '0', null, null);
INSERT INTO `branch_product` VALUES ('376', '1', '391', '0', null, null);
INSERT INTO `branch_product` VALUES ('377', '1', '392', '0', null, null);
INSERT INTO `branch_product` VALUES ('378', '1', '393', '0', null, null);
INSERT INTO `branch_product` VALUES ('379', '1', '394', '0', null, null);
INSERT INTO `branch_product` VALUES ('380', '1', '395', '0', null, null);
INSERT INTO `branch_product` VALUES ('381', '1', '396', '0', null, null);
INSERT INTO `branch_product` VALUES ('382', '1', '397', '0', null, null);
INSERT INTO `branch_product` VALUES ('383', '1', '398', '0', null, null);
INSERT INTO `branch_product` VALUES ('384', '1', '399', '0', null, null);
INSERT INTO `branch_product` VALUES ('385', '1', '400', '0', null, null);
INSERT INTO `branch_product` VALUES ('386', '1', '401', '0', null, null);
INSERT INTO `branch_product` VALUES ('387', '1', '402', '0', null, null);
INSERT INTO `branch_product` VALUES ('388', '1', '403', '0', null, null);
INSERT INTO `branch_product` VALUES ('389', '1', '404', '0', null, null);
INSERT INTO `branch_product` VALUES ('390', '1', '405', '0', null, null);
INSERT INTO `branch_product` VALUES ('391', '1', '406', '0', null, null);
INSERT INTO `branch_product` VALUES ('392', '1', '407', '0', null, null);
INSERT INTO `branch_product` VALUES ('393', '1', '408', '0', null, null);
INSERT INTO `branch_product` VALUES ('394', '1', '409', '0', null, null);
INSERT INTO `branch_product` VALUES ('395', '1', '410', '0', null, null);
INSERT INTO `branch_product` VALUES ('396', '1', '411', '0', null, null);
INSERT INTO `branch_product` VALUES ('397', '1', '412', '0', null, null);
INSERT INTO `branch_product` VALUES ('398', '1', '413', '0', null, null);
INSERT INTO `branch_product` VALUES ('399', '1', '414', '0', null, null);
INSERT INTO `branch_product` VALUES ('400', '1', '415', '0', null, null);
INSERT INTO `branch_product` VALUES ('401', '1', '416', '0', null, null);
INSERT INTO `branch_product` VALUES ('402', '1', '417', '0', null, null);
INSERT INTO `branch_product` VALUES ('403', '1', '418', '0', null, null);
INSERT INTO `branch_product` VALUES ('404', '1', '419', '0', null, null);
INSERT INTO `branch_product` VALUES ('405', '1', '420', '0', null, null);
INSERT INTO `branch_product` VALUES ('406', '1', '421', '0', null, null);
INSERT INTO `branch_product` VALUES ('407', '1', '422', '0', null, null);
INSERT INTO `branch_product` VALUES ('408', '1', '423', '0', null, null);
INSERT INTO `branch_product` VALUES ('409', '1', '424', '0', null, null);
INSERT INTO `branch_product` VALUES ('410', '1', '425', '0', null, null);
INSERT INTO `branch_product` VALUES ('411', '1', '426', '0', null, null);
INSERT INTO `branch_product` VALUES ('412', '1', '427', '0', null, null);
INSERT INTO `branch_product` VALUES ('413', '1', '428', '0', null, null);
INSERT INTO `branch_product` VALUES ('414', '1', '429', '0', null, null);
INSERT INTO `branch_product` VALUES ('415', '1', '430', '0', null, null);
INSERT INTO `branch_product` VALUES ('416', '1', '431', '0', null, null);
INSERT INTO `branch_product` VALUES ('417', '1', '432', '0', null, null);
INSERT INTO `branch_product` VALUES ('418', '1', '433', '0', null, null);
INSERT INTO `branch_product` VALUES ('419', '1', '434', '0', null, null);
INSERT INTO `branch_product` VALUES ('420', '1', '435', '0', null, null);
INSERT INTO `branch_product` VALUES ('421', '1', '436', '0', null, null);
INSERT INTO `branch_product` VALUES ('422', '1', '437', '0', null, null);
INSERT INTO `branch_product` VALUES ('423', '1', '438', '0', null, null);
INSERT INTO `branch_product` VALUES ('424', '1', '439', '0', null, null);
INSERT INTO `branch_product` VALUES ('425', '1', '440', '0', null, null);
INSERT INTO `branch_product` VALUES ('426', '1', '441', '0', null, null);
INSERT INTO `branch_product` VALUES ('427', '1', '442', '0', null, null);
INSERT INTO `branch_product` VALUES ('428', '1', '443', '0', null, null);
INSERT INTO `branch_product` VALUES ('429', '1', '444', '0', null, null);
INSERT INTO `branch_product` VALUES ('430', '1', '445', '0', null, null);
INSERT INTO `branch_product` VALUES ('431', '1', '446', '0', null, null);
INSERT INTO `branch_product` VALUES ('432', '1', '447', '0', null, null);
INSERT INTO `branch_product` VALUES ('433', '1', '448', '0', null, null);
INSERT INTO `branch_product` VALUES ('434', '1', '449', '0', null, null);
INSERT INTO `branch_product` VALUES ('435', '1', '450', '0', null, null);
INSERT INTO `branch_product` VALUES ('436', '1', '451', '0', null, null);
INSERT INTO `branch_product` VALUES ('437', '1', '452', '0', null, null);
INSERT INTO `branch_product` VALUES ('438', '1', '453', '0', null, null);
INSERT INTO `branch_product` VALUES ('439', '1', '454', '0', null, null);
INSERT INTO `branch_product` VALUES ('440', '1', '455', '0', null, null);
INSERT INTO `branch_product` VALUES ('441', '1', '456', '0', null, null);
INSERT INTO `branch_product` VALUES ('442', '1', '457', '0', null, null);
INSERT INTO `branch_product` VALUES ('443', '1', '458', '0', null, null);
INSERT INTO `branch_product` VALUES ('444', '1', '459', '0', null, null);
INSERT INTO `branch_product` VALUES ('445', '1', '460', '0', null, null);
INSERT INTO `branch_product` VALUES ('446', '1', '461', '0', null, null);
INSERT INTO `branch_product` VALUES ('447', '1', '462', '0', null, null);
INSERT INTO `branch_product` VALUES ('448', '1', '463', '0', null, null);
INSERT INTO `branch_product` VALUES ('449', '1', '464', '0', null, null);
INSERT INTO `branch_product` VALUES ('450', '1', '465', '0', null, null);
INSERT INTO `branch_product` VALUES ('451', '1', '466', '0', null, null);
INSERT INTO `branch_product` VALUES ('452', '1', '467', '0', null, null);
INSERT INTO `branch_product` VALUES ('453', '1', '468', '0', null, null);
INSERT INTO `branch_product` VALUES ('454', '1', '469', '0', null, null);
INSERT INTO `branch_product` VALUES ('455', '1', '470', '0', null, null);
INSERT INTO `branch_product` VALUES ('456', '1', '471', '0', null, null);
INSERT INTO `branch_product` VALUES ('457', '1', '472', '0', null, null);
INSERT INTO `branch_product` VALUES ('458', '1', '473', '0', null, null);
INSERT INTO `branch_product` VALUES ('459', '1', '474', '0', null, null);
INSERT INTO `branch_product` VALUES ('460', '1', '475', '0', null, null);
INSERT INTO `branch_product` VALUES ('461', '1', '476', '0', null, null);
INSERT INTO `branch_product` VALUES ('462', '1', '477', '0', null, null);
INSERT INTO `branch_product` VALUES ('463', '1', '478', '0', null, null);
INSERT INTO `branch_product` VALUES ('464', '1', '479', '0', null, null);
INSERT INTO `branch_product` VALUES ('465', '1', '480', '0', null, null);
INSERT INTO `branch_product` VALUES ('466', '1', '481', '0', null, null);
INSERT INTO `branch_product` VALUES ('467', '1', '482', '0', null, null);
INSERT INTO `branch_product` VALUES ('468', '1', '483', '0', null, null);
INSERT INTO `branch_product` VALUES ('469', '1', '484', '0', null, null);
INSERT INTO `branch_product` VALUES ('470', '1', '485', '0', null, null);
INSERT INTO `branch_product` VALUES ('471', '1', '486', '0', null, null);
INSERT INTO `branch_product` VALUES ('472', '1', '487', '0', null, null);
INSERT INTO `branch_product` VALUES ('473', '1', '488', '0', null, null);
INSERT INTO `branch_product` VALUES ('474', '1', '489', '0', null, null);
INSERT INTO `branch_product` VALUES ('475', '1', '490', '0', null, null);
INSERT INTO `branch_product` VALUES ('476', '1', '491', '0', null, null);
INSERT INTO `branch_product` VALUES ('477', '1', '492', '0', null, null);
INSERT INTO `branch_product` VALUES ('478', '1', '493', '0', null, null);
INSERT INTO `branch_product` VALUES ('479', '1', '494', '0', null, null);
INSERT INTO `branch_product` VALUES ('480', '1', '495', '0', null, null);
INSERT INTO `branch_product` VALUES ('481', '1', '496', '0', null, null);
INSERT INTO `branch_product` VALUES ('482', '1', '497', '0', null, null);
INSERT INTO `branch_product` VALUES ('483', '1', '498', '0', null, null);
INSERT INTO `branch_product` VALUES ('484', '1', '499', '0', null, null);
INSERT INTO `branch_product` VALUES ('485', '1', '500', '0', null, null);
INSERT INTO `branch_product` VALUES ('486', '1', '501', '0', null, null);
INSERT INTO `branch_product` VALUES ('487', '1', '502', '0', null, null);
INSERT INTO `branch_product` VALUES ('488', '1', '503', '0', null, null);
INSERT INTO `branch_product` VALUES ('489', '1', '504', '0', null, null);
INSERT INTO `branch_product` VALUES ('490', '1', '505', '0', null, null);
INSERT INTO `branch_product` VALUES ('491', '1', '506', '0', null, null);
INSERT INTO `branch_product` VALUES ('492', '1', '507', '0', null, null);
INSERT INTO `branch_product` VALUES ('493', '1', '508', '0', null, null);
INSERT INTO `branch_product` VALUES ('494', '1', '509', '0', null, null);
INSERT INTO `branch_product` VALUES ('495', '1', '510', '0', null, null);
INSERT INTO `branch_product` VALUES ('496', '1', '511', '0', null, null);
INSERT INTO `branch_product` VALUES ('497', '1', '512', '0', null, null);
INSERT INTO `branch_product` VALUES ('498', '1', '513', '0', null, null);
INSERT INTO `branch_product` VALUES ('499', '1', '514', '0', null, null);
INSERT INTO `branch_product` VALUES ('500', '1', '515', '0', null, null);
INSERT INTO `branch_product` VALUES ('501', '1', '516', '0', null, null);
INSERT INTO `branch_product` VALUES ('502', '1', '517', '0', null, null);
INSERT INTO `branch_product` VALUES ('503', '1', '518', '0', null, null);
INSERT INTO `branch_product` VALUES ('504', '1', '519', '0', null, null);
INSERT INTO `branch_product` VALUES ('505', '1', '520', '0', null, null);
INSERT INTO `branch_product` VALUES ('506', '1', '521', '0', null, null);
INSERT INTO `branch_product` VALUES ('507', '1', '522', '0', null, null);
INSERT INTO `branch_product` VALUES ('508', '1', '523', '0', null, null);
INSERT INTO `branch_product` VALUES ('509', '1', '524', '0', null, null);
INSERT INTO `branch_product` VALUES ('510', '1', '525', '0', null, null);
INSERT INTO `branch_product` VALUES ('511', '1', '526', '0', null, null);
INSERT INTO `branch_product` VALUES ('512', '1', '527', '0', null, null);
INSERT INTO `branch_product` VALUES ('513', '1', '528', '0', null, null);
INSERT INTO `branch_product` VALUES ('514', '1', '529', '0', null, null);
INSERT INTO `branch_product` VALUES ('515', '1', '530', '0', null, null);
INSERT INTO `branch_product` VALUES ('516', '1', '531', '0', null, null);
INSERT INTO `branch_product` VALUES ('517', '1', '532', '0', null, null);
INSERT INTO `branch_product` VALUES ('518', '1', '533', '0', null, null);
INSERT INTO `branch_product` VALUES ('519', '1', '534', '0', null, null);
INSERT INTO `branch_product` VALUES ('520', '1', '535', '0', null, null);
INSERT INTO `branch_product` VALUES ('521', '1', '536', '0', null, null);
INSERT INTO `branch_product` VALUES ('522', '1', '537', '0', null, null);
INSERT INTO `branch_product` VALUES ('523', '1', '538', '0', null, null);
INSERT INTO `branch_product` VALUES ('524', '1', '539', '0', null, null);
INSERT INTO `branch_product` VALUES ('525', '1', '540', '0', null, null);
INSERT INTO `branch_product` VALUES ('526', '1', '541', '0', null, null);
INSERT INTO `branch_product` VALUES ('527', '1', '542', '0', null, null);
INSERT INTO `branch_product` VALUES ('528', '1', '543', '0', null, null);
INSERT INTO `branch_product` VALUES ('529', '1', '544', '0', null, null);
INSERT INTO `branch_product` VALUES ('530', '1', '545', '0', null, null);
INSERT INTO `branch_product` VALUES ('531', '1', '546', '0', null, null);
INSERT INTO `branch_product` VALUES ('532', '1', '547', '0', null, null);
INSERT INTO `branch_product` VALUES ('533', '1', '548', '0', null, null);
INSERT INTO `branch_product` VALUES ('534', '1', '549', '0', null, null);
INSERT INTO `branch_product` VALUES ('535', '1', '550', '0', null, null);
INSERT INTO `branch_product` VALUES ('536', '1', '551', '0', null, null);
INSERT INTO `branch_product` VALUES ('537', '1', '552', '0', null, null);
INSERT INTO `branch_product` VALUES ('538', '1', '553', '0', null, null);
INSERT INTO `branch_product` VALUES ('539', '1', '554', '0', null, null);
INSERT INTO `branch_product` VALUES ('540', '1', '555', '0', null, null);
INSERT INTO `branch_product` VALUES ('541', '1', '556', '0', null, null);
INSERT INTO `branch_product` VALUES ('542', '1', '557', '0', null, null);
INSERT INTO `branch_product` VALUES ('543', '1', '558', '0', null, null);
INSERT INTO `branch_product` VALUES ('544', '1', '559', '0', null, null);
INSERT INTO `branch_product` VALUES ('545', '1', '560', '0', null, null);
INSERT INTO `branch_product` VALUES ('546', '1', '561', '0', null, null);
INSERT INTO `branch_product` VALUES ('547', '1', '562', '0', null, null);
INSERT INTO `branch_product` VALUES ('548', '1', '563', '0', null, null);
INSERT INTO `branch_product` VALUES ('549', '1', '564', '0', null, null);
INSERT INTO `branch_product` VALUES ('550', '1', '565', '0', null, null);
INSERT INTO `branch_product` VALUES ('551', '1', '566', '0', null, null);
INSERT INTO `branch_product` VALUES ('552', '1', '567', '0', null, null);
INSERT INTO `branch_product` VALUES ('553', '1', '568', '0', null, null);
INSERT INTO `branch_product` VALUES ('554', '1', '569', '0', null, null);
INSERT INTO `branch_product` VALUES ('555', '1', '570', '0', null, null);
INSERT INTO `branch_product` VALUES ('556', '1', '571', '0', null, null);
INSERT INTO `branch_product` VALUES ('557', '1', '572', '0', null, null);
INSERT INTO `branch_product` VALUES ('558', '1', '573', '0', null, null);
INSERT INTO `branch_product` VALUES ('559', '1', '574', '0', null, null);
INSERT INTO `branch_product` VALUES ('560', '1', '575', '0', null, null);
INSERT INTO `branch_product` VALUES ('561', '1', '576', '0', null, null);
INSERT INTO `branch_product` VALUES ('562', '1', '577', '0', null, null);
INSERT INTO `branch_product` VALUES ('563', '1', '578', '0', null, null);
INSERT INTO `branch_product` VALUES ('564', '1', '579', '0', null, null);
INSERT INTO `branch_product` VALUES ('565', '1', '580', '0', null, null);
INSERT INTO `branch_product` VALUES ('566', '1', '581', '0', null, null);
INSERT INTO `branch_product` VALUES ('567', '1', '582', '0', null, null);
INSERT INTO `branch_product` VALUES ('568', '1', '583', '0', null, null);
INSERT INTO `branch_product` VALUES ('569', '1', '584', '0', null, null);
INSERT INTO `branch_product` VALUES ('570', '1', '585', '0', null, null);
INSERT INTO `branch_product` VALUES ('571', '1', '586', '0', null, null);
INSERT INTO `branch_product` VALUES ('572', '1', '587', '0', null, null);
INSERT INTO `branch_product` VALUES ('573', '1', '588', '0', null, null);
INSERT INTO `branch_product` VALUES ('574', '1', '589', '0', null, null);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(11) DEFAULT NULL,
  `article_id` bigint(20) DEFAULT NULL,
  `comment_content` text,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  KEY `article_id` (`article_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET latin1 NOT NULL DEFAULT '',
  `reg` int(11) DEFAULT '0' COMMENT '优惠券规则最小值',
  `type` enum('discount','cash') CHARACTER SET latin1 DEFAULT NULL,
  `value` decimal(10,2) DEFAULT NULL,
  `status` int(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `expired_time` datetime DEFAULT NULL,
  `sales_id` bigint(20) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  KEY `sales_id` (`sales_id`),
  CONSTRAINT `coupon_ibfk_1` FOREIGN KEY (`sales_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of coupon
-- ----------------------------
INSERT INTO `coupon` VALUES ('23', 'test001', '60', 'cash', '10.00', '1', '2018-07-10 11:58:00', '2018-07-31 00:00:00', null, '', '测试优惠券');

-- ----------------------------
-- Table structure for coupon_customer
-- ----------------------------
DROP TABLE IF EXISTS `coupon_customer`;
CREATE TABLE `coupon_customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(20) DEFAULT NULL,
  `coupon_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `customer_id` (`customer_id`,`coupon_id`),
  KEY `coupon_id` (`coupon_id`),
  CONSTRAINT `coupon_customer_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `coupon_customer_ibfk_2` FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of coupon_customer
-- ----------------------------
INSERT INTO `coupon_customer` VALUES ('29', '2', '23', null, '1');
INSERT INTO `coupon_customer` VALUES ('30', '1', '23', null, '1');

-- ----------------------------
-- Table structure for coupon_order
-- ----------------------------
DROP TABLE IF EXISTS `coupon_order`;
CREATE TABLE `coupon_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL,
  `coupon_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  KEY `coupon_id` (`coupon_id`),
  CONSTRAINT `coupon_order_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`),
  CONSTRAINT `coupon_order_ibfk_2` FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of coupon_order
-- ----------------------------

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `rank_customer_id` bigint(20) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `name` varchar(128) DEFAULT NULL,
  `company_email` varchar(255) DEFAULT NULL,
  `phone` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '1',
  `discount` double NOT NULL DEFAULT '1' COMMENT '折扣：0.95=原价*0.95',
  `logo_src` varchar(1024) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `default_from_address_id` bigint(20) DEFAULT NULL,
  `default_to_address_id` bigint(20) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `gst_no` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `address` varchar(1024) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `bank_account` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `balance` double(255,2) DEFAULT NULL,
  `shipping_address` varchar(1024) DEFAULT NULL,
  `identity` varchar(255) DEFAULT NULL,
  `customer_type` enum('sales','customer') DEFAULT NULL,
  `memo` varchar(1024) DEFAULT NULL,
  `member_point` int(10) DEFAULT NULL,
  `member_point_expired_date` datetime DEFAULT NULL,
  `branch_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_ibfk_2` (`default_to_address_id`),
  KEY `customer_ibfk_1` (`default_from_address_id`),
  KEY `branch_id` (`branch_id`),
  KEY `rank_customer_id` (`rank_customer_id`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`default_from_address_id`) REFERENCES `customer_from_address` (`id`),
  CONSTRAINT `customer_ibfk_2` FOREIGN KEY (`default_to_address_id`) REFERENCES `customer_to_address` (`id`),
  CONSTRAINT `customer_ibfk_3` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`) ON DELETE SET NULL,
  CONSTRAINT `customer_ibfk_4` FOREIGN KEY (`rank_customer_id`) REFERENCES `rank_customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', '7', 'test@test.com', 'leo', null, '0221828358', 'e10adc3949ba59abbe56e057f20f883e', '1', '0', null, '2018-05-21 14:13:01', '2018-08-06 17:16:57', null, null, null, null, null, null, null, null, '0221828358', null, 'leo', '0.00', null, null, 'sales', null, null, null, null);
INSERT INTO `customer` VALUES ('2', '7', '13482211133', null, null, '13482211133', 'e10adc3949ba59abbe56e057f20f883e', '1', '1', null, '2018-06-30 15:57:34', '2018-10-01 14:27:55', null, null, null, null, null, null, null, null, null, null, null, '0.00', null, null, 'customer', null, null, null, null);
INSERT INTO `customer` VALUES ('3', '7', '253825496', null, null, '0221828358', '529100264c3e5c72bc868dbea7f5a7b8', '1', '1', null, '2018-07-09 18:40:53', '2018-10-01 14:27:52', null, null, null, null, null, null, null, null, null, null, 'leo', '0.00', null, null, 'customer', null, null, null, null);
INSERT INTO `customer` VALUES ('4', null, 'leo@test.com', null, null, '2538254961', 'e10adc3949ba59abbe56e057f20f883e', '1', '0', null, '2018-07-09 19:54:29', '2018-07-24 18:27:15', null, null, null, null, null, null, null, null, null, null, null, '0.00', null, null, 'sales', null, null, null, null);
INSERT INTO `customer` VALUES ('5', '8', 'vivienaj@hotmail.com', '安结', null, '13901327689', 'e10adc3949ba59abbe56e057f20f883e', '1', '1', null, '2018-07-30 04:22:12', '2018-10-01 14:27:49', null, null, null, null, null, null, null, null, null, null, '安结', '0.00', '北京市朝阳区武胜东里50号楼1602', null, 'customer', null, null, null, null);
INSERT INTO `customer` VALUES ('6', '7', 'gansocc@gmail.com', 'test', null, 'gansocc@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '1', '1', null, '2018-08-03 06:32:18', '2018-10-01 14:27:47', null, null, null, null, null, null, null, null, null, null, 'test', '0.00', null, null, 'customer', null, null, null, null);
INSERT INTO `customer` VALUES ('7', '7', 'anjie2702@gmail.com', null, null, 'anjie2702@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '1', '1', null, '2018-08-06 11:34:40', '2018-10-01 14:27:45', null, null, null, null, null, null, null, null, null, null, 'test', '0.00', null, null, 'customer', null, null, null, null);
INSERT INTO `customer` VALUES ('8', '7', '253825499@qq.com', null, null, '253825499@qq.com', '518ca748df210979c4c71c849a7e11b0', '1', '1', null, '2018-08-24 10:05:42', '2018-10-01 14:34:44', null, null, null, null, null, null, null, null, null, null, 'testname', '0.00', null, null, 'customer', null, null, null, null);

-- ----------------------------
-- Table structure for customer_commission
-- ----------------------------
DROP TABLE IF EXISTS `customer_commission`;
CREATE TABLE `customer_commission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sales_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `commission` double NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `memo` text,
  `payment_method` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `customer_commission_ibfk_1` (`sales_id`),
  CONSTRAINT `customer_commission_ibfk_1` FOREIGN KEY (`sales_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `customer_commission_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_commission
-- ----------------------------

-- ----------------------------
-- Table structure for customer_from_address
-- ----------------------------
DROP TABLE IF EXISTS `customer_from_address`;
CREATE TABLE `customer_from_address` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(11) NOT NULL,
  `from_address` varchar(1024) DEFAULT NULL,
  `from_city` varchar(1024) DEFAULT '',
  `from_province` varchar(1024) DEFAULT '',
  `from_country` varchar(1024) DEFAULT '',
  `from_phone` varchar(1024) DEFAULT NULL,
  `from_name` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `customer_from_address_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_from_address
-- ----------------------------

-- ----------------------------
-- Table structure for customer_payment_history
-- ----------------------------
DROP TABLE IF EXISTS `customer_payment_history`;
CREATE TABLE `customer_payment_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(20) DEFAULT NULL,
  `invoice_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `payment` double NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `memo` text,
  `payment_method` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `invoice_id` (`invoice_id`),
  KEY `customer_id` (`customer_id`),
  KEY `user_id` (`user_id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `customer_payment_history_ibfk_1` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`),
  CONSTRAINT `customer_payment_history_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `customer_payment_history_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `customer_payment_history_ibfk_4` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_payment_history
-- ----------------------------

-- ----------------------------
-- Table structure for customer_to_address
-- ----------------------------
DROP TABLE IF EXISTS `customer_to_address`;
CREATE TABLE `customer_to_address` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(11) NOT NULL,
  `to_address` varchar(1024) DEFAULT NULL,
  `to_city` varchar(1024) DEFAULT '',
  `to_province` varchar(1024) DEFAULT '',
  `to_country` varchar(1024) DEFAULT '',
  `to_phone` varchar(1024) DEFAULT NULL,
  `to_name` varchar(1024) DEFAULT NULL,
  `to_email` varchar(1024) DEFAULT NULL,
  `to_postcode` varchar(255) DEFAULT NULL,
  `to_district` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `customer_to_address_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_to_address
-- ----------------------------
INSERT INTO `customer_to_address` VALUES ('1', '1', 'null', '北京城区', '北京市', null, '0221828358', 'leo', null, null, '东城区');
INSERT INTO `customer_to_address` VALUES ('2', '5', 'null', '北京城区', '北京市', null, '13901327689', '安结', null, null, '朝阳区');
INSERT INTO `customer_to_address` VALUES ('3', '5', 'null', '北京城区', '北京市', null, '13901007663', '测试', null, null, '西城区');
INSERT INTO `customer_to_address` VALUES ('4', '2', '', '石家庄市', '河北省', null, '13482211133', '好好说', null, null, '桥西区');
INSERT INTO `customer_to_address` VALUES ('5', '2', '', '北京市', '北京市', null, '13482211133', '哈哈哈哈哈', null, null, '朝阳区');
INSERT INTO `customer_to_address` VALUES ('6', '2', '', '上海市', '上海市', null, '13482221113', '而柔软', null, null, '黄浦区');
INSERT INTO `customer_to_address` VALUES ('7', '6', '', '上海市', '上海市', null, '13482211133', '哈哈哈哈哈家', null, null, '静安区');
INSERT INTO `customer_to_address` VALUES ('8', '6', '', '太原市', '山西省', null, '13482211136', 'uiu', null, null, '小店区');
INSERT INTO `customer_to_address` VALUES ('9', '6', '', '哈尔滨市', '黑龙江省', null, '13482211133', '会', null, null, '道里区');
INSERT INTO `customer_to_address` VALUES ('10', '6', '', '上海市', '上海市', null, '13482122233', '哈哈哈哈h', null, null, '黄浦区');
INSERT INTO `customer_to_address` VALUES ('11', '6', '', '南昌市', '江西省', null, '13482211133', '呼呼哈哈', null, null, '东湖区');
INSERT INTO `customer_to_address` VALUES ('12', '6', '', '北京市', '北京市', null, '13482211133', '哈哈哈哈哈必须', null, null, '东城区');
INSERT INTO `customer_to_address` VALUES ('13', '6', '', '北京市', '北京市', null, '13452299911', '有意义', null, null, '东城区');
INSERT INTO `customer_to_address` VALUES ('14', '5', '', '北京市', '北京市', null, '13901327689', '安结', null, null, '朝阳区');
INSERT INTO `customer_to_address` VALUES ('15', '6', '', '沈阳市', '辽宁省', null, '13458822236', '会好好', null, null, '和平区');
INSERT INTO `customer_to_address` VALUES ('16', '2', '', '太原市', '山西省', null, '13482211133', '好的', null, null, '小店区');
INSERT INTO `customer_to_address` VALUES ('17', '3', '', '北京市', '北京市', null, '13482211133', '1231', null, null, '东城区');
INSERT INTO `customer_to_address` VALUES ('18', '3', '', '北京市', '北京市', null, '13482211133', '成晨', null, null, '东城区');
INSERT INTO `customer_to_address` VALUES ('19', '3', '', '杭州市', '浙江省', null, '13482211133', '煮成尘', null, null, '上城区');
INSERT INTO `customer_to_address` VALUES ('20', '3', '', '北京市', '北京市', null, '13482211133', '猪', null, null, '东城区');

-- ----------------------------
-- Table structure for invoice
-- ----------------------------
DROP TABLE IF EXISTS `invoice`;
CREATE TABLE `invoice` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `alias_id` varchar(11) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `customer_email` varchar(255) DEFAULT NULL,
  `customer_company_name` varchar(1024) DEFAULT NULL,
  `customer_company_address` varchar(1024) DEFAULT NULL,
  `customer_company_phone` varchar(255) DEFAULT NULL,
  `customer_company_fax` varchar(255) DEFAULT NULL,
  `customer_company_mobile` varchar(255) DEFAULT NULL,
  `user_company_id` bigint(11) DEFAULT NULL,
  `user_company_name` varchar(255) DEFAULT NULL,
  `user_company_address` varchar(1024) DEFAULT NULL,
  `user_company_website` varchar(255) DEFAULT NULL,
  `user_company_email` varchar(255) DEFAULT NULL,
  `user_company_tel` varchar(255) DEFAULT NULL,
  `user_company_fax` varchar(255) DEFAULT NULL,
  `user_company_mobile` varchar(255) DEFAULT NULL,
  `user_company_bank_account` varchar(255) DEFAULT NULL,
  `user_company_gst_no` varchar(255) DEFAULT NULL,
  `subtotal` double(10,2) DEFAULT NULL,
  `gst` double(10,2) DEFAULT NULL,
  `total` double(10,2) DEFAULT NULL,
  `balance` double(10,2) DEFAULT NULL,
  `paid` tinyint(10) DEFAULT NULL COMMENT '{value: -1, text: ''--Select--''},\r\n{value: 1, text: ''Unpaid''},\r\n{value: 2, text: ''PaidInPart''},\r\n{value: 3, text: ''Paid''},\r\n{value: 0, text: ''Cancelled''},',
  `status` tinyint(4) DEFAULT '0' COMMENT '{value: -1, text: ''--Select--''},\r\n{value: 1, text: ''New Invoice''},\r\n{value: 2, text: ''Emailed''},\r\n{value: 3, text: ''Completed''},',
  `admin_info` text,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `invoice_terms_conditions` longtext,
  `discount` double(255,0) DEFAULT '1',
  `email_info` text,
  `freight` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_company_id` (`user_company_id`),
  KEY `user_id` (`user_id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`user_company_id`) REFERENCES `user_company` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `invoice_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `invoice_ibfk_3` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of invoice
-- ----------------------------

-- ----------------------------
-- Table structure for invoice_item
-- ----------------------------
DROP TABLE IF EXISTS `invoice_item`;
CREATE TABLE `invoice_item` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `invoice_id` bigint(11) DEFAULT NULL,
  `quantity` int(10) DEFAULT NULL,
  `unit_price` double(10,2) DEFAULT NULL,
  `discount` double(10,2) DEFAULT NULL,
  `amount` double(10,2) DEFAULT NULL,
  `title` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `invoice_id` (`invoice_id`),
  CONSTRAINT `invoice_item_ibfk_1` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of invoice_item
-- ----------------------------

-- ----------------------------
-- Table structure for log_url
-- ----------------------------
DROP TABLE IF EXISTS `log_url`;
CREATE TABLE `log_url` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `url` varchar(2048) NOT NULL,
  `number` int(11) DEFAULT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log_url
-- ----------------------------

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `name_alias` varchar(128) DEFAULT NULL,
  `level` int(11) NOT NULL DEFAULT '0' COMMENT '第几级菜单',
  `position` int(11) DEFAULT '0',
  `father_id` bigint(11) DEFAULT NULL COMMENT '父级菜单',
  `unite_price` double(10,2) DEFAULT '0.00',
  `box_weight` double DEFAULT '0',
  `other_charge` double DEFAULT '0',
  `default_src` varchar(1024) DEFAULT NULL,
  `menu_type` enum('article','brand','product') DEFAULT NULL,
  `status` enum('bottom','hidden','top','other','default','disable') DEFAULT 'default',
  PRIMARY KEY (`id`),
  KEY `father_id` (`father_id`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`father_id`) REFERENCES `menu` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=224 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('108', '产品分类', '产品分类', '1', '81', null, null, null, null, null, 'product', 'top');
INSERT INTO `menu` VALUES ('138', '公司信息', 'topa', '1', '88', null, null, null, null, null, 'article', 'bottom');
INSERT INTO `menu` VALUES ('139', '条款', 'Policy', '1', '87', null, null, null, null, null, 'article', 'bottom');
INSERT INTO `menu` VALUES ('140', '服务支持', 'Services & Support', '1', '86', null, null, null, null, null, 'article', 'bottom');
INSERT INTO `menu` VALUES ('141', '常见问题', 'Frequently Asked Questions', '2', '83', '140', null, null, null, null, 'article', null);
INSERT INTO `menu` VALUES ('143', '购买须知', 'Terms & Conditions', '2', '0', '139', null, null, null, null, 'article', null);
INSERT INTO `menu` VALUES ('144', '运费说明', 'Shipping', '2', '0', '139', null, null, null, null, 'article', null);
INSERT INTO `menu` VALUES ('145', '关于我们', 'About Us', '2', '0', '138', null, null, null, null, 'article', null);
INSERT INTO `menu` VALUES ('146', '联系我们', 'Contact Us', '2', '0', '138', null, null, null, null, 'article', null);
INSERT INTO `menu` VALUES ('149', 'index', 'index', '1', '85', null, null, null, null, null, 'article', 'hidden');
INSERT INTO `menu` VALUES ('154', '促销产品', '促销产品', '1', '82', null, null, null, null, null, 'article', 'top');
INSERT INTO `menu` VALUES ('155', '联系我们', '联系我们', '1', '84', null, null, null, null, null, 'article', 'top');
INSERT INTO `menu` VALUES ('156', '促销活动', '促销活动', '1', '83', null, null, null, null, null, 'article', 'top');
INSERT INTO `menu` VALUES ('157', '乳制品', '乳制品', '2', '41', '108', null, null, null, 'attachment/img/2018-07-26/Vanilla With Flakes Of Chocolate Ice Cream.jpg', 'product', null);
INSERT INTO `menu` VALUES ('158', '蜂产品', '蜂产品', '2', '42', '108', null, null, null, 'attachment/img/2018-07-27/500.jpg', 'product', null);
INSERT INTO `menu` VALUES ('159', '高端食品', '高端食品', '2', '43', '108', null, null, null, 'attachment/img/2018-08-13/500g-vanilla.jpg', 'product', null);
INSERT INTO `menu` VALUES ('160', '保健品', '保健品', '2', '45', '108', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('161', '护肤品', '护肤品', '2', '46', '108', null, null, null, 'attachment/img/2018-07-24/Manuka+Day+Cream+-+Clear+Cut.jpg', 'product', null);
INSERT INTO `menu` VALUES ('162', '日用品', '日用品', '2', '47', '108', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('163', '酒水', '酒水', '2', '48', '108', null, null, null, 'attachment/img/2018-07-27/ulife-Macadamia Liqueur-product-01.jpg', 'product', null);
INSERT INTO `menu` VALUES ('164', '矿泉水', '矿泉水', '2', '49', '108', null, null, null, 'attachment/img/2018-08-15/750still.jpg', 'product', null);
INSERT INTO `menu` VALUES ('165', '产品', '产品', '1', '80', null, null, null, null, null, 'article', 'hidden');
INSERT INTO `menu` VALUES ('166', '婴儿奶粉', '婴儿奶粉', '3', '35', '157', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('167', '成人奶粉（全脂）', '成人奶粉', '3', '36', '157', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('168', '羊奶粉', '羊奶粉', '3', '38', '157', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('169', '成人奶粉（脱脂）', '成人奶粉（脱脂）', '3', '37', '157', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('170', '液态奶', '液态奶', '3', '39', '157', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('171', '芝士（马苏）', '芝士（马苏）', '3', '40', '157', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('172', '冰淇淋', '冰淇淋', '3', '44', '157', null, null, null, 'attachment/img/2018-07-26/Vanilla With Flakes Of Chocolate Ice Cream.jpg', 'product', null);
INSERT INTO `menu` VALUES ('173', '奶片', '奶片', '3', '41', '157', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('174', '酸奶粉', '酸奶粉', '3', '43', '157', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('175', '奶酪', '奶酪', '3', '42', '157', null, null, null, 'attachment/img/2018-08-31/1kg.jpg', 'product', null);
INSERT INTO `menu` VALUES ('176', '麦卢卡蜂蜜', '麦卢卡蜂蜜', '3', '0', '158', null, null, null, 'attachment/img/2018-07-27/500.jpg', 'product', null);
INSERT INTO `menu` VALUES ('177', '水果蜜', '水果蜜', '3', '0', '158', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('178', '花蜜', '花蜜', '3', '0', '158', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('180', '蜂蜜糖', '蜂蜜糖', '3', '0', '158', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('181', '蜂蜜饮品', '蜂蜜饮品', '3', '0', '158', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('182', '健康饮品', '健康饮品', '2', '44', '108', null, null, null, 'attachment/img/2018-07-26/p_milk_iced_coffee2-.jpg', 'product', null);
INSERT INTO `menu` VALUES ('183', '水果干', '水果干', '3', '61', '159', null, null, null, 'attachment/img/2018-08-30/noimage.png', 'product', null);
INSERT INTO `menu` VALUES ('184', '水果粉', '水果粉', '3', '62', '159', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('185', '麦片', '麦片', '3', '56', '159', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('186', '巧克力', '巧克力', '3', '57', '159', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('187', '布丁', '布丁', '3', '58', '159', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('188', '甜品', '甜品', '3', '60', '159', null, null, null, 'attachment/img/2018-08-03/tiramisu.jpg', 'product', null);
INSERT INTO `menu` VALUES ('189', '儿童辅食', '儿童辅食', '3', '63', '159', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('190', '干果', '干果', '3', '64', '159', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('191', '黄油', '黄油', '3', '65', '159', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('192', '果酱', '果酱', '3', '66', '159', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('193', '橄榄油', '橄榄油', '3', '67', '159', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('194', '食用酱', '食用油', '3', '69', '159', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('195', '稻米油', '稻米油', '3', '68', '159', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('196', '果冻', '果冻', '3', '59', '159', null, null, null, 'attachment/img/2018-08-03/vanilla.jpg', 'product', null);
INSERT INTO `menu` VALUES ('197', '沙拉酱', '沙拉酱', '3', '70', '159', null, null, null, 'attachment/img/2018-08-13/500g-vanilla.jpg', 'product', null);
INSERT INTO `menu` VALUES ('198', '男士保健品', '男士', '3', '58', '160', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('199', '女士保健品', '女士保健品', '3', '59', '160', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('200', '儿童保健品', '儿童保健品', '3', '0', '160', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('201', '老人保健品', '老人保健品', '3', '0', '160', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('202', '面膜', '面膜', '3', '69', '161', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('203', '精华', '精华', '3', '68', '161', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('204', '精油', '精油', '3', '67', '161', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('205', '晚霜', '晚霜', '3', '70', '161', null, null, null, 'attachment/img/2018-07-24/Harakeke+Seed+Oil+Night+Cream+-+Clear+Cut.jpg', 'product', null);
INSERT INTO `menu` VALUES ('206', '日霜', '日霜', '3', '71', '161', null, null, null, 'attachment/img/2018-07-24/Manuka+Day+Cream+-+Clear+Cut.jpg', 'product', null);
INSERT INTO `menu` VALUES ('207', '护肤乳', '护肤乳', '3', '72', '161', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('208', '洗面奶', '洗面奶', '3', '73', '161', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('209', '口罩', '口罩', '3', '0', '162', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('210', '洗手液', '洗手液', '3', '0', '162', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('211', '干果酒', '干果酒', '3', '76', '163', null, null, null, 'attachment/img/2018-07-27/ulife-Macadamia Liqueur-product-01.jpg', 'product', null);
INSERT INTO `menu` VALUES ('212', '水果酒', '水果酒', '3', '77', '163', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('213', '葡萄酒', '葡萄酒', '3', '78', '163', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('214', '啤酒', '啤酒', '3', '79', '163', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('215', '烈酒', '烈酒', '3', '80', '163', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('216', '蜂蜜酒', '蜂蜜酒', '3', '75', '163', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('217', '汽泡水', '汽泡水', '3', '0', '164', null, null, null, 'attachment/img/2018-08-15/750 sparkling.jpg', 'product', null);
INSERT INTO `menu` VALUES ('218', '矿泉水', '矿泉水', '3', '0', '164', null, null, null, 'attachment/img/2018-08-15/750still.jpg', 'product', null);
INSERT INTO `menu` VALUES ('219', '苏打精华', '苏打精华', '3', '0', '182', null, null, null, 'attachment/img/2018-07-26/500ml-Orange-Dandelion.jpg', 'product', null);
INSERT INTO `menu` VALUES ('220', '奶昔', '奶昔', '3', '0', '182', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('221', '混合饮品', '混合饮品', '3', '0', '182', null, null, null, 'attachment/img/2018-07-26/p_milk_iced_coffee2-.jpg', 'product', null);
INSERT INTO `menu` VALUES ('222', '果汁', '果汁', '3', '0', '182', null, null, null, null, 'product', null);
INSERT INTO `menu` VALUES ('223', '溯源流程', '溯源流程', '2', '82', '140', null, null, null, null, 'article', null);

-- ----------------------------
-- Table structure for menu_attr
-- ----------------------------
DROP TABLE IF EXISTS `menu_attr`;
CREATE TABLE `menu_attr` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `menu_attr_name` varchar(128) NOT NULL,
  `menu_id` bigint(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `menu_id` (`menu_id`),
  CONSTRAINT `menu_attr_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu_attr
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(11) NOT NULL,
  `user_id` bigint(11) DEFAULT NULL,
  `tracking_num` varchar(128) DEFAULT NULL,
  `pay_type` varchar(45) DEFAULT NULL,
  `is_paid` int(11) DEFAULT '0',
  `total_product_price` double(10,2) NOT NULL,
  `total_freight` double(10,2) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '订单状态（0：下单，1：已付款，2：已发货，3：已收货，4：已完成 5：已取消）',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `from_name` varchar(1024) DEFAULT NULL,
  `from_phone` varchar(1024) DEFAULT NULL,
  `from_address` varchar(1024) DEFAULT NULL,
  `to_customer_name` varchar(1024) DEFAULT NULL,
  `to_customer_company_name` varchar(1024) DEFAULT NULL,
  `to_tel` varchar(255) DEFAULT NULL,
  `to_phone` varchar(255) DEFAULT NULL,
  `to_address` varchar(1024) DEFAULT NULL,
  `to_shipping_address` varchar(1024) DEFAULT NULL,
  `to_province` varchar(255) DEFAULT NULL,
  `to_city` varchar(255) DEFAULT NULL,
  `to_district` varchar(255) DEFAULT NULL,
  `gst` double(10,2) DEFAULT '0.00',
  `to_email` varchar(255) DEFAULT NULL,
  `customer_msg` varchar(1024) DEFAULT NULL,
  `admin_msg` varchar(1024) DEFAULT NULL,
  `settled` int(11) DEFAULT NULL,
  `invoiced` tinyint(4) DEFAULT '0',
  `sales_id` bigint(20) DEFAULT NULL,
  `sales_commission_percentage` double(10,2) DEFAULT NULL,
  `sales_commission_status` int(255) DEFAULT '0' COMMENT '提成支付状态： 0 未支付，1支付',
  `discount` double(10,2) DEFAULT NULL,
  `branch_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  KEY `from_name` (`from_name`(255)),
  KEY `from_phone` (`from_phone`(255)),
  KEY `to_name` (`to_customer_name`(255)),
  KEY `to_phone` (`to_phone`),
  KEY `order_ibfk_2` (`user_id`),
  KEY `sales_id` (`sales_id`),
  KEY `branch_id` (`branch_id`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON UPDATE NO ACTION,
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `order_ibfk_3` FOREIGN KEY (`sales_id`) REFERENCES `customer` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `order_ibfk_4` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('1', '8', null, null, null, '0', '2.00', '0.00', '1', '2018-10-01 14:31:17', '2018-10-01 14:31:17', 'Yomi', null, '', null, 'testname', '253825499@qq.com', '253825499@qq.com', null, null, null, null, null, '0.00', null, null, 'aaaaaa', '0', null, null, null, null, '1.00', null);
INSERT INTO `order` VALUES ('2', '8', null, null, null, '0', '3.00', '0.00', '1', '2018-10-01 14:34:10', '2018-10-01 14:34:10', 'Yomi', null, '', null, 'testname', '253825499@qq.com', '253825499@qq.com', null, null, null, null, null, '0.00', null, null, '', '0', null, null, null, null, '1.00', null);

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` bigint(11) NOT NULL,
  `product_id` bigint(11) DEFAULT NULL,
  `num` int(11) NOT NULL,
  `product_price` double(10,2) NOT NULL,
  `product_name_cn` varchar(1024) DEFAULT NULL,
  `product_name_en` varchar(1024) DEFAULT NULL,
  `product_default_src` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  KEY `order_item_ibfk_2` (`product_id`),
  CONSTRAINT `order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`),
  CONSTRAINT `order_item_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES ('1', '1', '589', '1', '1.00', ' Unichi 牛油果精华胶囊 60粒', 'Unichi Avocado Extract Complex 60c', '');
INSERT INTO `order_item` VALUES ('2', '1', '588', '1', '1.00', 'Ecostore 去屑护发素 220ml', 'Ecostore dandruff control conditioner 220ml', '');
INSERT INTO `order_item` VALUES ('3', '2', '589', '1', '2.00', ' Unichi 牛油果精华胶囊 60粒', 'Unichi Avocado Extract Complex 60c', '');
INSERT INTO `order_item` VALUES ('4', '2', '588', '1', '1.00', 'Ecostore 去屑护发素 220ml', 'Ecostore dandruff control conditioner 220ml', '');

-- ----------------------------
-- Table structure for page_view
-- ----------------------------
DROP TABLE IF EXISTS `page_view`;
CREATE TABLE `page_view` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `view_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `date` (`date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of page_view
-- ----------------------------
INSERT INTO `page_view` VALUES ('150', '2018-09-10', '37');
INSERT INTO `page_view` VALUES ('151', '2018-09-11', '63');
INSERT INTO `page_view` VALUES ('152', '2018-09-27', '2');
INSERT INTO `page_view` VALUES ('153', '2018-09-28', '1');
INSERT INTO `page_view` VALUES ('154', '2018-10-01', '1');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `menu_id` bigint(11) DEFAULT NULL,
  `mpn` varchar(128) DEFAULT NULL,
  `product_name` varchar(256) DEFAULT NULL,
  `product_name_alias` varchar(256) DEFAULT NULL,
  `default_src` varchar(1024) DEFAULT NULL,
  `cost` double DEFAULT '0',
  `price1` double NOT NULL DEFAULT '0.99',
  `price2` double DEFAULT '0',
  `price3` double DEFAULT '0',
  `price4` double DEFAULT '0',
  `status` tinyint(4) DEFAULT '1' COMMENT '0 下线 1 上线 2缺货',
  `hot` tinyint(4) DEFAULT '0' COMMENT '热销产品',
  `promote` tinyint(4) DEFAULT '0' COMMENT '促销产品',
  `front_page` tinyint(4) DEFAULT '0' COMMENT '首页显示',
  `recommend` tinyint(4) DEFAULT '0' COMMENT '推荐产品',
  `sold_num` int(11) DEFAULT '0',
  `stock` int(11) DEFAULT '0',
  `weight` double DEFAULT '0',
  `click_num` int(11) DEFAULT '0',
  `summary` text,
  `description` longtext,
  `seo_keywords` varchar(1024) DEFAULT NULL,
  `seo_desc` varchar(1024) DEFAULT NULL,
  `create_time` datetime DEFAULT '0000-00-00 00:00:00',
  `modify_time` datetime DEFAULT '0000-00-00 00:00:00',
  `location` varchar(1024) DEFAULT NULL,
  `label` varchar(1024) DEFAULT NULL,
  `position` int(11) DEFAULT '0',
  `mobile_default_src` varchar(1024) DEFAULT NULL,
  `mobile_default_desc` text,
  `norms` text,
  PRIMARY KEY (`id`),
  KEY `menu_id` (`menu_id`),
  KEY `mpn` (`mpn`),
  KEY `product_name_cn` (`product_name`(255)),
  KEY `product_name_en` (`product_name_alias`(255)),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=590 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('16', null, '9421901167004', '诺丽酵素原浆750ml', 'Life Health Noni Juice  750ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:44', '2018-09-18 11:05:44', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('17', null, '9421008702740', '龙血小银瓶30ml', 'drangon‘s blood sculpting serum 30ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:44', '2018-09-18 11:05:44', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('18', null, '4210201146575', '电动牙刷(多角度)', 'oral-b electric toothbrush （cross action）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:44', '2018-09-18 11:05:44', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('19', null, '9310297005628', '洛神红酒750ml', 'Rawson‘s retreat 750ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:44', '2018-09-18 11:05:44', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('20', null, '9311770598170', 'Swisse 大钙 澳洲版本150粒', 'Swisse Calcium and Vitamin D 150c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:44', '2018-09-18 11:05:44', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('21', null, '9311770593632', 'Swisse 蔓越莓30粒', 'Swisse high strength Cranberry 25000mg  30c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:44', '2018-09-18 11:05:44', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('22', null, '9312146000174', '星期四 祛痘凝胶25g', 'Thursday tea tree Blemish Gel 25g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:44', '2018-09-18 11:05:44', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('23', null, '94183986', 'Antipodes 眼霜30ml', 'Antipodes Kiwi Seed Oil Eye Cream 30ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:44', '2018-09-18 11:05:44', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('24', null, '9416967911531', 'FEAT 足裂霜75g', ' Neat Feat Heel Balm  Health Care Foot Creams Treatments 75g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:44', '2018-09-18 11:05:44', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('25', null, '9310320002099', 'femfresh 女性洗液250ml', 'Femfresh intimate hygiene lady wash 250ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:45', '2018-09-18 11:05:45', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('26', null, '9415998201017', 'Merino 芦荟胶250ml', 'Merino 97% pure Aloe Vera Gel 250ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:45', '2018-09-18 11:05:45', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('27', null, '9421900569786', 'Antipodes 补水精华30ml', 'Antipodes Hosanna H2O Intensive Skin-Plumping Serum 30ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:45', '2018-09-18 11:05:45', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('28', null, '9350634008189', 'unichi深海四十鲟面霜50ml', 'Unichi Forty Fathoms Skin Regenerator 50ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:45', '2018-09-18 11:05:45', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('29', null, '4987176002822', 'meta膳食纤维粉（柠檬）425g', 'MetaMucil Fibre Powder （lemon）425g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:45', '2018-09-18 11:05:45', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('30', null, '4987176601414', 'meta膳食纤维粉（自然颗粒）425g', 'MetaMucil Fibre Powder （original）425g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:45', '2018-09-18 11:05:45', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('31', null, '4987176005465', 'meta膳食纤维粉（鲜莓）425g', 'MetaMucil Fibre Powder （strawberry）425g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:45', '2018-09-18 11:05:45', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('32', null, '4987176000767', 'meta膳食纤维粉（橙子）425g', 'MetaMucil Fibre Powder （orange）425g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:45', '2018-09-18 11:05:45', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('33', null, '9400501003738', '康维他麦奴卡蜂蜜UMF10+500g', 'Comvita Manuka Honey umf10+ 500g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:45', '2018-09-18 11:05:45', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('34', null, '9400097035502', 'Healtheries slim 贺寿利代餐粉香草味 500g', 'Healtheries Slim （vanilla）500g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:45', '2018-09-18 11:05:45', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('35', null, '9421900325672', '皇家蜂毒眼霜   15ml', 'Royal Nectar Eye Cream 15ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:45', '2018-09-18 11:05:45', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('36', null, '9400581039641', '纽乐蓝莓护眼胶囊60粒', 'NutraLife Bilberry 22000mg 60c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:45', '2018-09-18 11:05:45', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('37', null, '4004148059018', 'Floradix 铁元片剂84粒', 'Floradix iron 84c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:46', '2018-09-18 11:05:46', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('38', null, '9348107001652', '水光针第五代10ml', 'eaoron hyaluronic acid Collagen essence 10ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:46', '2018-09-18 11:05:46', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('39', null, '9310297651481', '奔富 8 750ml', 'bin 8   750ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:46', '2018-09-18 11:05:46', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('40', null, '9310297011261', '奔富 389 750ml', 'bin 389   750ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:46', '2018-09-18 11:05:46', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('41', null, '7290011521011', 'MOROCCANIL 摩洛哥发油 100ml', 'Moroccan hair oil 100ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:46', '2018-09-18 11:05:46', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('42', null, '9331927003326', 'Lifespace 孕妇益生菌60粒', 'Lifespace spectrum probiotic powder for pregnancy 60c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:46', '2018-09-18 11:05:46', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('43', null, '9331927003678', 'Lifespace 儿童益生菌60g', 'LifeSpace spectrum probiotic powder for children 60g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:46', '2018-09-18 11:05:46', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('44', null, '9331927003333', 'Lifespace baby益生菌60g', 'LifeSpace spectrum probiotic powder for baby 60g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:46', '2018-09-18 11:05:46', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('45', null, '9331927003784', 'Lifespace 新生儿益生菌60g', 'LifeSpace spectrum probiotic powder for infant 60g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:46', '2018-09-18 11:05:46', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('46', null, '9331927003241', 'Life space 成人益生菌60粒', 'LifeSpace spectrum probiotic powder for infant 60g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:46', '2018-09-18 11:05:46', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('47', null, '9400575001616', 'THOMPSONS 汤普森鱼油 400粒', 'LifeSpace broad Spectrum Probiotic 60c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:46', '2018-09-18 11:05:46', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('48', null, '9400575001999', '汤普森卵磷脂200粒', 'Thompson high potency Super Lecithin 200c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:46', '2018-09-18 11:05:46', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('49', null, '9310297009169', '奔富 128 750ml', 'bin 128 750ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:46', '2018-09-18 11:05:46', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('50', null, '1000000000017', '鳕鱼花胶8-9片', 'isinglass of cod  500g 8-9s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:47', '2018-09-18 11:05:47', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('51', null, '1000000000018', '鳕鱼花胶30片', 'isinglass of cod  500g 30s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:47', '2018-09-18 11:05:47', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('52', null, '1000000000019', '鳕鱼花胶6片', 'isinglass of cod  500g 6s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:47', '2018-09-18 11:05:47', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('53', null, '9311770600118', 'swisse玻尿酸保湿面膜 50g', 'Swisse Hyaluro-Natural Hydrating Facial Mask 50g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:47', '2018-09-18 11:05:47', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('54', null, '9331927003616', 'lifespace女性益生菌 60粒', 'LifeSpace spectrum probiotic powder for women 60c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:47', '2018-09-18 11:05:47', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('55', null, '9400581037852', '纽乐蔓越莓50粒', 'NutraLife Cranberry 50000 50c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:47', '2018-09-18 11:05:47', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('56', null, '9400581032383', '纽乐蔓越莓100粒', 'NutraLife Cranberry 50000 100c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:48', '2018-09-18 11:05:48', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('57', null, '6009827630081', 'B.O.N祛疤凝胶15ml', 'B.O.N  silicone gel 15ML', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:48', '2018-09-18 11:05:48', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('58', null, '3614271678447', '兰蔻小黑瓶20ml', 'Lancome Advanced Genifique Sensitive Serum?20ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:48', '2018-09-18 11:05:48', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('59', null, '9421027312180', '祖马龙沐浴露400ml', 'glow lab blackberry&bay body wash', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:48', '2018-09-18 11:05:48', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('60', null, '9421904600034', 'supreme health 虾青素护眼片60粒', 'Supreme Health Advanced Vision Care 60c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:48', '2018-09-18 11:05:48', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('61', null, '1000000000020', '黑刺皇30头', 'sea cucumber 500g 30+s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:48', '2018-09-18 11:05:48', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('62', null, '1000000000021', '黑刺皇 20头', 'sea cucumber 500g 30+s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:48', '2018-09-18 11:05:48', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('63', null, '9311770598279', 'Swisse麦奴卡蜂蜜排毒面膜70g', 'Swisse Manuka Honey Detoxifying Facial Mask 70g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:48', '2018-09-18 11:05:48', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('64', null, '9311770593120', 'Swisse 胶原蛋白液 500ml', 'Swisse Hair Skin and Nail 500ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:48', '2018-09-18 11:05:48', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('65', null, '9310297007417', '奔富 28  750ml', 'bin 28  750ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:48', '2018-09-18 11:05:48', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('66', null, '9334518005273', '香娜露儿桃颜霜  50g', 'Chantelle Sydney-Pink Advanced Radiance Source Skin Brightener 50g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:48', '2018-09-18 11:05:48', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('67', null, '9300807287323', 'BLACKMORES 月见草  190粒', 'blackmores evening primrose oil 190c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:48', '2018-09-18 11:05:48', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('68', null, '9348107001676', 'eaoron 瘦身霜  150ml', 'eaoron slim shapes anti sellulite cream 150ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:49', '2018-09-18 11:05:49', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('69', null, '9348107001256', 'EAORON 素颜霜 50ml', 'EAORON Crystal White Brightening Cream 50ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:49', '2018-09-18 11:05:49', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('70', null, '9348107001492', 'eaoron 乳液  120ml', 'EAORON Hyaluronic Lotion 120ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:49', '2018-09-18 11:05:49', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('71', null, '9319629742439', '莱文医生八胜肽面膜3片/盒', 'Dr Lewinn‘s Line Smoothing Complex High Potency Treatment Mask 3pk', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:49', '2018-09-18 11:05:49', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('72', null, '9400569017500', '好健康辅酶Q10 150mg 90粒', 'Goodhealth opti CoQ10 150mg 90c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:49', '2018-09-18 11:05:49', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('73', null, '9300807205211', 'blackmoresm天然维生素E', 'Blackmores Natural E 500IU 150s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:49', '2018-09-18 11:05:49', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('74', null, '94575507', 'THOMPSONS 生物素', 'Thompson Biotin 150mcg 100t', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:49', '2018-09-18 11:05:49', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('75', null, '9300807220115', 'BLACKMORES 维生素B  125粒', 'Blackmores Executive B 125s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:49', '2018-09-18 11:05:49', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('76', null, '9322316001108', 'GM 澳芝曼绵羊油  ', 'G&M Lanolin Cream 250G', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:49', '2018-09-18 11:05:49', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('77', null, '9400575001258', 'Thompsons 汤普森 VE胶囊 100粒', 'thompsons Vitamin E 500IU 100c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:49', '2018-09-18 11:05:49', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('78', null, '499064', 'blackmore B族75粒', 'Blackmores Mega B Complex (75 tab)', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:49', '2018-09-18 11:05:49', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('79', null, '9334518001756', 'Chantelle棕色羊胎素精华 10ml*6', 'Chantelle Bio-Placenta Sheep Placenta Extract 10ml*6', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:50', '2018-09-18 11:05:50', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('80', null, '9421903455307', 'Streamland 奇异果蜂蜜 500g', 'Streamland Kiwifruit Honey 500g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:50', '2018-09-18 11:05:50', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('81', null, '9311770599566', 'Swiss 卵磷脂—150粒', 'Swisse Lecithin 1200mg 150c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:50', '2018-09-18 11:05:50', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('82', null, '9369998006427', 'CEMOY 安瓶 5ml*2', 'Cemoy Hyaluronic Acid Bio-Placenta Serum 2*5ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:50', '2018-09-18 11:05:50', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('83', null, '9369998006861', 'Lellure冻膜面膜 3ml*10袋', 'Lellure illumination gel mask 3ml*10', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:50', '2018-09-18 11:05:50', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('84', null, '9421901167110', 'lifehealth 诺丽果汁 350ml', 'Life Health Noni Juice 350ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:50', '2018-09-18 11:05:50', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('85', null, '5011025047005', '屁屁霜  125g', 'Sudocrem Healing Cream for Nappy Rash 125g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:50', '2018-09-18 11:05:50', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('86', null, '9300657150075', 'Farex 磨牙棒  100g', 'Farex teething rusks  100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:50', '2018-09-18 11:05:50', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('87', null, '9400581032987', '纽乐 更年期片 60粒', 'N/L Meno Life day& night 60c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:50', '2018-09-18 11:05:50', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('88', null, '9326939005031', 'Nutriyion Care 养胃粉  150g', 'Nutrition Care Gut Relief 150g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:50', '2018-09-18 11:05:50', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('89', null, '9314807025267', '佳思敏钙+VD软糖  60粒', 'KIDS SMART VITA GUMMIES CALCIUM + VITAMIN D 60S', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:50', '2018-09-18 11:05:50', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('90', null, '9314807022525', 'Nature‘s way kids smart 三色鱼油  180粒', 'KIDS SMART BURSTLETS OMEGA-3 FISH OIL TRIO 180S', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:50', '2018-09-18 11:05:50', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('91', null, '9314807025526', '佳思敏挑食开胃软糖 60粒', 'Nature way multi-vitamin fussy eaters ', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:50', '2018-09-18 11:05:50', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('92', null, '9344949001263', 'Bioisland 儿童 DHA  60粒', 'Bioisland  DHA for kids', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:51', '2018-09-18 11:05:51', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('93', null, '9342905000381', 'freeze frame 眼霜  15ml', 'Freeze Frame revitalEyes Eye Cream 15ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:51', '2018-09-18 11:05:51', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('94', null, '9314807022853', '佳思敏儿童维生素糖（omega-3+复合维生素）60粒', 'Nature‘s Way Kids Smart Vita Gummies Omega 3 Fish Oil  60c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:51', '2018-09-18 11:05:51', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('95', null, '9349254004053', 'goat 羊奶皂（儿童） 100g', 'Goat Soap Kids 100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:51', '2018-09-18 11:05:51', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('96', null, '9349254002042', 'goat 羊奶皂（红） 麦卢卡蜂蜜味 100g', 'Goat Soap With Manuka Honey 100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:51', '2018-09-18 11:05:51', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('97', null, '9421904228313', 'Maxcural绿青口关节膏   100g', ' MAXCURAL GREEN LIPPED MUSSEL OIL NATURAL JOINT RELIEF BALM  100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:51', '2018-09-18 11:05:51', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('98', null, '9421901881160', 'Pic‘s 花生酱有盐幼滑380g', 'Pic‘s Smooth Peanut Butter with salt 380g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:51', '2018-09-18 11:05:51', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('99', null, '9400569020555', '好健康葡萄籽55000mg 120粒', 'good healthy grape seed 55000mg  120c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:51', '2018-09-18 11:05:51', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('100', null, '9421903364418', 'Antipides 抗氧化精华 30ml', 'ANTIPODES Worship Superfruit Antioxidant Serum 30ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:51', '2018-09-18 11:05:51', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('101', null, '9421001820021', '老奶奶臭脚粉（花香） 50g', 'Grans Remedy foot powder scented 50g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:51', '2018-09-18 11:05:51', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('102', null, '9421901881061', 'PIC‘S 花生酱—无盐颗粒 380g', 'Pic‘s Crunchy Peanut Butter with No Salt 380g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:51', '2018-09-18 11:05:51', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('103', null, '9421901881177', 'Pic‘s 花生酱 无盐幼滑-380g', 'Pic‘s Smooth Peanut Butter with no salt 380g ', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:52', '2018-09-18 11:05:52', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('104', null, '9421900325719', 'Royal Nector 蜂毒精华 20ml', 'Royal Nectar Serum 20ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:52', '2018-09-18 11:05:52', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('105', null, '4210201156727', 'Oral-b女童牙刷', 'oral-b electric toothbrush （girl）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:52', '2018-09-18 11:05:52', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('106', null, '4210201156673', 'oral-b男童牙刷', 'oral-b electric toothbrush （boy）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:52', '2018-09-18 11:05:52', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('107', null, '9400514010587', 'prolife 儿童生长素 500粒', 'Prolife Colostrum milk Strawberry 500t', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:52', '2018-09-18 11:05:52', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('108', null, '9421001820014', '老奶奶臭脚粉--原味 50g', 'Grans Remedy foot powder original 50g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:52', '2018-09-18 11:05:52', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('109', null, '9400501002939', '康维他PFL30蜂胶250粒', 'Comvita Propolis PFL30 250c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:52', '2018-09-18 11:05:52', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('110', null, '9400569013182', '好健康 生蚝精60粒', 'Good health Oyster Extract 60s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:52', '2018-09-18 11:05:52', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('111', null, '9311096023233', 'Paw patrol汪汪队饼干 200g', 'paw patrol character cookies 200g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:52', '2018-09-18 11:05:52', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('112', null, '9311096021840', 'Peppa pig小猪佩琦饼干 200g', 'paw patrol character cookies 200g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:52', '2018-09-18 11:05:52', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('113', null, '9421031780159', 'Mitoq 补水胶囊 60粒', 'MitoQ Skin Support Complex 60c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:52', '2018-09-18 11:05:52', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('114', null, '9400501002892', '康维他蜂胶PFL15 365粒', 'Comvita Propolis PFL15 365c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:52', '2018-09-18 11:05:52', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('115', null, '9413000026238', 'Olivia小熊营养奶粉480g', 'Olivia Formulated Milk Powder 480g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:52', '2018-09-18 11:05:52', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('116', null, '5012616262180', 'XLS酵素粉 500g', 'Bio-E Probiotic Super Green Powder 500g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:52', '2018-09-18 11:05:52', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('117', null, '9421901881009', 'Pic‘s 花生酱 有盐有颗粒 380g', 'Pic‘s Crunchy Peanut Butter with Salt 380g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:53', '2018-09-18 11:05:53', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('118', null, '4210201856160', 'Oral-B成人牙刷（牙线）', 'oral-b electric toothbrush （Floss Action）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:53', '2018-09-18 11:05:53', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('119', null, '9400514011300', 'Prolife蓝莓护眼 60粒', 'Prolife Junior Bilberry Chews 60s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:53', '2018-09-18 11:05:53', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('120', null, '9400097041602', 'Healtheries milk bites vanilla 奶片香草味 50片', 'Healtheries milk bites vanilla 50c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:53', '2018-09-18 11:05:53', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('121', null, '9400097041923', 'Healtheries 贺寿利奶片香蕉味 30粒', 'Healtheries milk bites banana 50c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:53', '2018-09-18 11:05:53', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('122', null, '9400097041619', 'Healtheries 奶片草莓味30片', 'Healtheries milk bites strawberry 50c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:53', '2018-09-18 11:05:53', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('123', null, '9400097041930', 'Healtheries 蜂蜜奶片', 'Healtheries milk bites honey 50c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:53', '2018-09-18 11:05:53', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('124', null, '9421900665891', 'Artemis百里香250ml', 'Artemis Thyme Lemon Tonic 250ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:53', '2018-09-18 11:05:53', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('125', null, '9421900569076', 'Antipodes麦奴卡蜂蜜面膜75ml', 'Antipodes Aura Manuka Honey Mask 75ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:53', '2018-09-18 11:05:53', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('126', null, '9317376730129', 'Daktarin灰指甲水30ml', 'Daktarin Tincture liquid fungal nail infections 30ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:53', '2018-09-18 11:05:53', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('127', null, '9331134927798', 'Pamol感冒药1-12岁橙子口味250ml', 'pamol relieves children‘s pain&fever 1-12years orange 200ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:53', '2018-09-18 11:05:53', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('128', null, '9319733001903', 'Anthisan蚊虫止痒药25g', 'Anthisan cream 2% mepyramine maleate25g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:53', '2018-09-18 11:05:53', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('129', null, '768990027239', '挪威小鱼 儿童鱼油237 ml', 'Nordic Natural Child DHA 237ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:53', '2018-09-18 11:05:53', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('130', null, '4004148047503', '红印铁元液500ml', 'Floradix liquid iron Formula 500ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:54', '2018-09-18 11:05:54', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('131', null, '9400581033809', '纽乐鱼油1500mg 180粒', 'NutraLife Fish Oil 1500mg 180s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:54', '2018-09-18 11:05:54', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('132', null, '9316254891419', 'Healthy care 葡萄籽 300粒', 'Healthy Care Grape Seed 12000 300c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:54', '2018-09-18 11:05:54', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('133', null, '9400581041606', '纽乐羊胎素胶囊 60粒', 'NutraLife sheep placenta 34000 60c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:54', '2018-09-18 11:05:54', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('134', null, '9400569010976', '好健康牛初乳—-香草味 150粒', 'Good Health Colostrum 150 tabs (Vanilla)', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:54', '2018-09-18 11:05:54', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('135', null, '9400569011539', '好健康蜂王浆 365粒', 'Good Health Royal jelly 1000mg 365c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:54', '2018-09-18 11:05:54', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('136', null, '9369999068776', 'Herbsense 暖宫片 45粒', 'Herbsense Curcumin Period Pain 45t', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:54', '2018-09-18 11:05:54', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('137', null, '9420026574636', 'Clinicians 科立纯过敏片 30粒', 'Clinicians AllerStop 30c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:55', '2018-09-18 11:05:55', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('138', null, '9421023620197', '蜜纽康 黑蜂胶—120粒', 'Manuka Health BIO30 Propolis Cap 500mg 120s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:55', '2018-09-18 11:05:55', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('139', null, '9403067122011', 'Lifestearm 乳腺宝 200g', 'lifestream cc flax 200g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:55', '2018-09-18 11:05:55', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('140', null, '4004148057076', 'Redseal 铁元液—250ml', 'Floradix liquid iron Formula 250ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:55', '2018-09-18 11:05:55', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('141', null, '9400569013465', '好健康鱼油1000mg 400粒', 'Good Health fish oil 1000mg 400c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:55', '2018-09-18 11:05:55', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('142', null, '9300807220092', 'Blacokmores 维生素B 175粒', 'Blackmores Executive B 175s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:55', '2018-09-18 11:05:55', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('143', null, '93807432', 'Blackmores 圣洁莓 40粒', 'Blackmores Vitex 40t', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:55', '2018-09-18 11:05:55', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('144', null, '93894616', 'Blacmkores 叶酸片', 'Blackmores Folic Acid 90t', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:55', '2018-09-18 11:05:55', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('145', null, '9421031471736', 'OneOne 奶片 蓝莓味—100粒', 'OneOne Milk Tablets Blueberry 100t', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:55', '2018-09-18 11:05:55', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('146', null, '9421031471729', 'OneOne奶片—原味 100粒', 'OneOne Milk Tablets Natural 100t', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:55', '2018-09-18 11:05:55', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('147', null, '9350634004815', 'Unichi 生蚝精—60粒 （金装）', 'Unichi Oyster Extract （new） 60s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:55', '2018-09-18 11:05:55', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('148', null, '9421017762131', 'Trilogy火山泥面膜 60ml', 'Trilogy Mineral Radiance Mask (60ml)', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:55', '2018-09-18 11:05:55', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('149', null, '9314807018450', 'Redwin VE 霜 300g', 'Redwin VE cream 300g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:56', '2018-09-18 11:05:56', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('150', null, '9415293229600', 'Wildferns蜂毒面膜 50ml', 'Wild Ferns Bee Venom Face Mask With Active Manuka Honey - 50ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:56', '2018-09-18 11:05:56', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('151', null, '9421013060491', 'Kiwiherb儿童止咳200ml（麦卢卡蜂蜜）', 'Kiwiherb Children‘s Chest Syrup 200ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:56', '2018-09-18 11:05:56', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('152', null, '9327693000539', 'Sukin眼霜30ml', 'Sukin Antioxidant Eye Serum 30ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:56', '2018-09-18 11:05:56', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('153', null, '9342905007199', 'FreezeframeBB霜', 'Freezeframe BB blur 30ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:56', '2018-09-18 11:05:56', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('154', null, '9421904155008', 'Fernz麦奴卡蜂蜜面霜50g', 'Fernz smoothing cream active manuka honey 50g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:56', '2018-09-18 11:05:56', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('155', null, '9413000024111', 'Fernz蜂毒面膜50g', 'Fernz Bee Venom Face Mask 50g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:56', '2018-09-18 11:05:56', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('156', null, '9417657588255', 'AlpineSilk绵羊油保湿面霜100g', 'Alpine Silk Moisture Creme 100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:56', '2018-09-18 11:05:56', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('157', null, '9310320000699', 'Dencorub关节膏100g', 'Dencorub Arthritis Cream 100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:56', '2018-09-18 11:05:56', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('158', null, '9421900569052', 'Antipodes磨砂膏 75ml', 'Antipodes Reincarnation Facial Exfoliator 75ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:56', '2018-09-18 11:05:56', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('159', null, '9421028148917', 'Kiwigold蜂胶2000mg365粒', 'Kiwigold Propolis 2000mg 365c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:56', '2018-09-18 11:05:56', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('160', null, '5015672004923', 'Mumomega 孕妇深海鱼油30粒', 'Equazen MomOmega Pregnancy 30s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:56', '2018-09-18 11:05:56', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('161', null, '9419521885583', 'Nature Beauty火山泥面膜200g', 'Nature‘s Beauty Mineral Radiance Mask 200g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:56', '2018-09-18 11:05:56', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('162', null, '9421902515026', 'Savar 奢华洗面奶180ml', 'Savar facial cleanser 180ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:57', '2018-09-18 11:05:57', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('163', null, '93807937', 'Blackmores VC片1000mg 150粒', 'Blackmore Bio C 1000mg 150t', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:57', '2018-09-18 11:05:57', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('164', null, '9331134927828', 'Pamol感冒药1-12岁草莓味100ml', 'pamol relieves children‘s pain&fever 1-12years strawberry 200ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:57', '2018-09-18 11:05:57', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('165', null, '9400569001660', 'Goodhealth supercal 超级钙 150粒', 'Good Health Super Cal 150s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:57', '2018-09-18 11:05:57', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('166', null, '9421017764975', 'Trilogy限量版果油30m?l', 'trilogy NO.15 edition beaty oil', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:57', '2018-09-18 11:05:57', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('167', null, '9319629741067', 'Dr Lewinns抗皱眼霜15g', 'Dr Lewinn‘s Eternal Youth Day & Night Eye Cream 15g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:57', '2018-09-18 11:05:57', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('168', null, '9400571300256', 'HomeEssentials玫瑰水200ml', 'HomeEssentials rose water 200ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:57', '2018-09-18 11:05:57', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('169', null, '9415293215962', 'Wildferns 小黄瓜撕拉面膜80ml', 'Parrs Rotorua Mud Mask Aloe Vera & Cucumber 80ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:57', '2018-09-18 11:05:57', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('170', null, '9415293216037', 'Wildferns火山泥面膜青柠130ml', 'Parrs Rotorua Mud Facial Wash with Lime Blossom', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:57', '2018-09-18 11:05:57', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('171', null, '9334518005228', 'Chantelle羊胎素面膜 7片装', 'Chantelle facial mask 7sheets', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:57', '2018-09-18 11:05:57', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('172', null, '9414015002910', 'Arataki蜂巢340g', 'Arataki 100% pure natural comb honey 340g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:57', '2018-09-18 11:05:57', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('173', null, '9416967912132', 'Neat Feat脚裂膏75gx2', ' Neat Feat Heel Balm  Health Care Foot Creams Treatments 75g*2', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:57', '2018-09-18 11:05:57', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('174', null, '9312146001300', 'ThursdayPlantation茶树油25ml', 'Thursday Tea Tree Oil 25ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:57', '2018-09-18 11:05:57', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('175', null, '9421903137067', 'Fresco羊奶粉450g', 'Fresco goat milk450g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:58', '2018-09-18 11:05:58', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('176', null, '8710428004376', 'PediaSure小安素营养粉850g', 'Pediasure', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:58', '2018-09-18 11:05:58', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('177', null, '9400501100321', 'Comvita麦奴卡蜂蜜奶粉900g', 'Comvita Manukaup Milkdrink 900g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:58', '2018-09-18 11:05:58', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('178', null, '9421017764425', 'Trilogy 圣诞套装（野玫瑰果油抗氧化剂 30ml +有机玫瑰果油洁面乳 30ml +玫瑰果晚霜 25ml）', 'Trilogy Botanical Beauties Set', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:58', '2018-09-18 11:05:58', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('179', null, '708177114482', 'Jurlique 茱莉蔻圣诞套盒（玫瑰草本沐浴露+玫瑰衡肤爽肤水+玫瑰草本身体乳）', 'Jurlique Rose Favourite', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:58', '2018-09-18 11:05:58', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('180', null, '9421027311060', 'Earthwise 河马宝宝儿童洗发水（黄色）添加羊奶 275ml', 'Earthwise baby by earthwise bubble shampoo with goats milk 275ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:58', '2018-09-18 11:05:58', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('181', null, '9330344002240', 'Banana boat 成人防晒50+175g', 'Banana Boat Everyday SPF 50+ Sunscreen Spray 175g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:58', '2018-09-18 11:05:58', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('182', null, '9316542120504', 'Natio Ageless 面膜 100g', 'Natio Ageless Hydrating Mask 100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:58', '2018-09-18 11:05:58', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('183', null, '9400501003448', 'Comvita UMF10+  棒棒糖', 'Comvita Childrens Lemon and Honey Lollipops', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:58', '2018-09-18 11:05:58', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('184', null, '9319064003843', 'B-Box 吸管杯-荧光绿', 'B-Box sippy cup 240ml（fluorescent green）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:58', '2018-09-18 11:05:58', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('185', null, '9319064003812', 'B-Box 吸管杯 橘黄', 'B-Box sippy cup 240ml（orange）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:58', '2018-09-18 11:05:58', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('186', null, '9319064003133', 'B-Box 吸管杯 草绿', 'B-Box sippy cup 240ml（grass green）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:58', '2018-09-18 11:05:58', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('187', null, '9319064003119', 'B-Box 吸管杯 浅蓝色', 'B-Box sippy cup 240ml （light blue）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:59', '2018-09-18 11:05:59', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('188', null, '9319064003140', 'B-Box 吸管杯 深紫色', 'B-Box sippy cup 240ml （deep purple）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:59', '2018-09-18 11:05:59', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('189', null, '9319064003805', 'B-Box 吸管杯 深蓝色', 'B-Box sippy cup 240ml （deep blue）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:59', '2018-09-18 11:05:59', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('190', null, '9421023620043', '蜜纽康Manuka honey 100+ 500g', 'Manuka Health MGO 100+ Manuka Honey 500g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:59', '2018-09-18 11:05:59', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('191', null, '9421023620692', 'Manuka honey 250+ 500g', 'Manuka Health Manuka Honey MGO 250+ 500g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:59', '2018-09-18 11:05:59', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('192', null, '9421023620074', 'Manuka honey 400+ 500g', 'Manuka Health Manuka Honey MGO 400+ 500g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:59', '2018-09-18 11:05:59', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('193', null, '9400501001123', 'Comvita honey 5+ 1000g', 'Comvita Manuka Honey 5+ 1000g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:59', '2018-09-18 11:05:59', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('194', null, '9421023620050', 'Manuka honey 100+ 1000g', 'Manuka Health Manuka Honey MGO 100+ 1kg', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:59', '2018-09-18 11:05:59', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('195', null, '9421023620388', 'Manuka Blend Honey 500g', 'Manuka Health Manuka Honey MGO 100+ 1kg', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:59', '2018-09-18 11:05:59', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('196', null, '9421023620012', 'Manuka Honey 30+ 500g', 'Manuka Health Manuka Honey Blend MGO 30+ 500g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:59', '2018-09-18 11:05:59', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('197', null, '9421023620067', 'Manuka Honey 400+ 250g', 'Manuka Health Manuka Honey MGO 400+ 250g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:05:59', '2018-09-18 11:05:59', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('198', null, '9400501003882', 'Comvita  honey 20+ 250g', 'Comvita UMF Manuka Honey 20+ 250g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:00', '2018-09-18 11:06:00', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('199', null, '9400501005558', 'Comvita honey UMF15+ 250g', 'Comvita Manuka Honey 15+ 250g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:00', '2018-09-18 11:06:00', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('200', null, '9421902628030', 'NP manukau hone mask  80ml 10支装', 'NP Manuka Honey Purifying Pack', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:00', '2018-09-18 11:06:00', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('201', null, '9312146006763', 'Thursday 泡沫洗面奶', 'Thursday Tea Tree Face Wash 150m', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:00', '2018-09-18 11:06:00', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('202', null, '010181040313', '帕玛氏身体乳250ml', 'Palmers lotion 250ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:00', '2018-09-18 11:06:00', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('203', null, '9316254017062', 'HC 羊胎素精华液', 'Healthy Care Anti-Ageing Face Serum 50ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:00', '2018-09-18 11:06:00', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('204', null, '9300705032308', 'Cenovis 圣诺天然维生素c咀嚼片 300粒', 'Cenovis Sugarless C 500mg 300t', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:00', '2018-09-18 11:06:00', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('205', null, '9421031340001', 'BioBalance 椰子油 500ml', 'biobalance coconut oil 500ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:00', '2018-09-18 11:06:00', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('206', null, '9300607420074', 'Gamophen 药皂 100g', 'Gamophen Medicated Soap 100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:00', '2018-09-18 11:06:00', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('207', null, '9420015002065', 'ECO 羊奶皂 baby 80g', 'Ecostore Goat‘s Milk&Lavender Baby Soap 80g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:00', '2018-09-18 11:06:00', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('208', null, '9420015001211', 'ECO 羊奶皂 香草 80g', 'Ecostore Vanilla Soap 80g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:00', '2018-09-18 11:06:00', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('209', null, '9420015001198', 'ECO 羊奶皂manukau honey 80g', 'Ecostore Munuka Honey & Kelp Soap 80g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:00', '2018-09-18 11:06:00', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('210', null, '9420015001228', 'ECO 羊奶皂 -椰子 80g', 'Ecostore Coconut Soap 80g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:00', '2018-09-18 11:06:00', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('211', null, '9420015001204', 'ECO 羊奶皂 lemongrass 80g ', 'Ecostore Lemongrass Soap 80g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:01', '2018-09-18 11:06:01', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('212', null, '9420015003130', 'ECO Ultra sensitive 羊奶皂 80g', 'ECO Ultra sensitive Soap 80g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:01', '2018-09-18 11:06:01', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('213', null, '9420015002089', 'ECO 葡萄柚羊奶皂', 'ECO STORE Boxed Grapefruit & Mint Soap', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:01', '2018-09-18 11:06:01', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('214', null, '9421028121910', 'Beauteous牛初乳绵羊油100g', 'Beauteous colostrum cream beauteous 100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:01', '2018-09-18 11:06:01', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('215', null, '9421028121811', 'Beauteous胶原蛋白绵羊油100g', 'Beauteous Collagen Cream Beauteous 100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:01', '2018-09-18 11:06:01', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('216', null, '9415293201897', 'Parrs宝宝按摩油90ml', 'Parrs Honey Babe Massage Oil 90ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:01', '2018-09-18 11:06:01', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('217', null, '9400533006257', 'Repel薰衣草成人驱蚊滚珠60ml', 'repel insect repellent 60ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:01', '2018-09-18 11:06:01', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('218', null, '9421027311077', 'Earthwise 河马宝宝儿童沐浴乳（蓝色） 添加羊奶 275ml', 'Earthwise baby by earthwise bubble body wash with goats milk 275ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:01', '2018-09-18 11:06:01', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('219', null, '079656652055', 'Banana boat SPF50+婴儿防晒霜90ml', 'Banana Boat sunscreean lotion （tear free） 90ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:01', '2018-09-18 11:06:01', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('220', null, '9415293201880', 'Parrs 帕氏婴儿蜂蜜润肤乳 80ml', 'Parrs Honey Babe Moisture Creme 80ml tube', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:01', '2018-09-18 11:06:01', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('221', null, '9328121046341', 'Binky bites小黄人饼干160g', 'Binky bites character cookies 160g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:01', '2018-09-18 11:06:01', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('222', null, '9415991233275', 'Red seal覆盆子茶35g', 'Red Seal Raspberry Leaf Tea 35g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:01', '2018-09-18 11:06:01', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('223', null, '9316090500605', 'Ostelin Vitamin D 250c 成人钙', 'Ostelin Calcium and Vitamin D 250c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:02', '2018-09-18 11:06:02', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('224', null, '9400575001371', 'Thompson Animals 儿童多维90粒', 'Thompson Junior Immunoforte 90s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:02', '2018-09-18 11:06:02', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('225', null, '9310555005117', 'Breath pearls 口气清新丸 50粒', 'Breath Pearls Original freshes Breath 50 soft gels', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:02', '2018-09-18 11:06:02', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('226', null, '9400575001500', 'Thompson super lecithin 卵磷脂 120粒', 'Thompson super lecithin  200粒', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:02', '2018-09-18 11:06:02', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('227', null, '9420015015171', 'Ecostore敏感无香味沐浴露 400ml', 'Ecostore body wash - fragrance free - 400ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:02', '2018-09-18 11:06:02', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('228', null, '9420015013481', 'Ecostore 丰盈护发素 350ml', 'Ecostore volumising conditioner 350ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:02', '2018-09-18 11:06:02', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('229', null, '9420015013498', 'Ecostore去屑洗发水 350ml', 'Ecostore dandruff control shampoo 350ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:02', '2018-09-18 11:06:02', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('230', null, '9420015013047', 'Ecostore 去屑洗发水 220ml', 'Ecostore dandruff control shampoo 220ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:02', '2018-09-18 11:06:02', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('231', null, '9420015013153', 'Ecostore 受损发质护发素 220ml', 'Ecostore damaged care conditioner 220ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:02', '2018-09-18 11:06:02', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('232', null, '9420015013283', 'Ecostore 敏感护发素 220ml', 'Ecostore sensitive conditioner 220ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:02', '2018-09-18 11:06:02', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('233', null, '9349254002073', 'Goat 山羊奶原味沐浴露 500ml', 'Goat wash original 500ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:02', '2018-09-18 11:06:02', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('234', null, '9400571328922', 'Home 卸妆膏 500g', 'Home essantials cigalia cream 500g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:02', '2018-09-18 11:06:02', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('235', null, '9326420133007', 'Aroma wash bliss linen spray 除螨喷雾 125ml', 'Aroma wash bliss linen spray 125ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:02', '2018-09-18 11:06:02', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('236', null, '9327693000317', 'Sukin foaming cleanser 泡沫洗面奶 125ml', 'Sukin foaming cleanser 125ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:03', '2018-09-18 11:06:03', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('237', null, '9400575003849', 'Thompsons 辅酶Q10 30粒', 'Thompsons ultra COQ10 300mg 30粒', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:03', '2018-09-18 11:06:03', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('238', null, '9400581039580', '纽乐葡萄籽精华 50000mg 120粒', 'Nutralife grape seed 50000mg 120粒', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:03', '2018-09-18 11:06:03', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('239', null, '9400575001302', 'Thompsons 三文鱼油 300粒', 'Thompsons salmon oil 1000mg 300粒', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:03', '2018-09-18 11:06:03', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('240', null, '9400575003405', 'Thompsons 月见草 300粒', 'Thompsons evening primrose oil 1000mg 300粒', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:03', '2018-09-18 11:06:03', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('241', null, '9421028960144', 'Kiwigarden 酸奶溶豆混合莓 45g', 'Kiwigarden MixedBerry Yoghurt Drops 45g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:03', '2018-09-18 11:06:03', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('242', null, '9400575003474', '汤普森硒片-60粒', 'Thompson‘s Organic Selenium 150mg 60t', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:03', '2018-09-18 11:06:03', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('243', null, '670983048766', 'jellycat 纯色奶茶色huge', 'jellycat huge bunny（brown）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:03', '2018-09-18 11:06:03', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('244', null, '670983070972', 'jellycat 混色中号兔子', 'jellycat middle bunny（mixed color）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:03', '2018-09-18 11:06:03', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('245', null, '670983106619', 'jellycat-湖蓝花耳朵-中号兔子', 'jellycat middle bunny（bright blue with colorful ears）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:03', '2018-09-18 11:06:03', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('246', null, '670983069822', 'jellycat-中号天蓝纯色兔子', 'jellycat middle bunny（azure）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:03', '2018-09-18 11:06:03', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('247', null, '670983089387', 'jellycat 中号独角兽', 'jellycat middle unicorn', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:03', '2018-09-18 11:06:03', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('248', null, '670983038569', 'jellycat 中号茶色兔子', 'jellycat middle bunny（brown）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:04', '2018-09-18 11:06:04', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('249', null, '670983094930', 'jellycat 大号独角兽', 'jellycat large unicorn', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:04', '2018-09-18 11:06:04', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('250', null, '670983097498', 'jellycat 大号郁金香花耳朵兔子', 'jellycat large bunny（with tulip flower ears）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:04', '2018-09-18 11:06:04', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('251', null, '670983046588', 'jellycat 大号纯白色兔子', 'jellycat large bunny（white）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:04', '2018-09-18 11:06:04', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('252', null, '9351419000008', 'ficcecode菲诗蔻洗发水 260ml', 'FicceCode Shampoo 260ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:04', '2018-09-18 11:06:04', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('253', null, '9421903447081', 'Rich Garden NONI果汁750ml', 'Rich Garden Noni Juice 750ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:04', '2018-09-18 11:06:04', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('254', null, '9351419000015', 'ficcecode菲诗蔻发膜 260ml', 'FicceCode Hair Mask 260ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:04', '2018-09-18 11:06:04', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('255', null, '9316542130701', 'Natio颈霜100g', 'Natio neck cream 100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:04', '2018-09-18 11:06:04', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('256', null, '802763020857', 'sunsweet 西梅400g', 'Sunsweet prunes 400g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:04', '2018-09-18 11:06:04', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('257', null, '9414972100971', 'EDMONDS 松饼粉 350g', 'EDMONDS original pancakes 350g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:04', '2018-09-18 11:06:04', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('258', null, '9414972101091', 'Edmonds 松饼粉黄油', 'EDMONDS butter pancakes 350g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:04', '2018-09-18 11:06:04', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('259', null, '9421028960113', 'Kiwigarden 蜂蜜香蕉味酸奶溶豆 45g', 'Kiwigarden Banana&Honey Yoghurt Drops 45g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:05', '2018-09-18 11:06:05', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('260', null, '9312059473027', 'Curash 痱子粉 100g', 'Curash anti-rush baby powder 100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:05', '2018-09-18 11:06:05', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('261', null, '8437010367502', '卡曼面膜-黄金', 'Casmara Luxury Algae Peel Off Mask', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:05', '2018-09-18 11:06:05', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('262', null, '670983045567', 'jellycat 纯色奶茶色-大号兔子', 'jellycat large bunny（brown）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:05', '2018-09-18 11:06:05', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('263', null, '670983069839', 'jellycat 纯色粉色中号兔子', 'jellycat middle bunny（pink）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:05', '2018-09-18 11:06:05', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('264', null, '9421903488152', 'Dr.kulsea 椰子油', 'Dr.kulsea coconut oil', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:05', '2018-09-18 11:06:05', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('265', null, '9403118002699', 'Airbone 宝宝蜂蜜 500g', 'Airbone honey for kids 500g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:06', '2018-09-18 11:06:06', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('266', null, '9421028960212', 'Kiwigarden 酸奶溶豆草莓 45g', 'Kiwigarden Strawberry Yoghurt Drops 45g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:06', '2018-09-18 11:06:06', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('267', null, '9400569017852', '好健康鱼油1500mg400粒', 'Good Health Omega 3 fish oil 1500mg 400s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:06', '2018-09-18 11:06:06', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('268', null, '9403067150021', '生命泉海藻钙 120粒', 'Lifestream Calcium 120s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:06', '2018-09-18 11:06:06', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('269', null, '9316542111472', 'natio洗面奶 100g', 'natio gentle foaming facial cleanser 100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:06', '2018-09-18 11:06:06', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('270', null, '9421028960052', 'Kiwigarden 苹果片 45g', 'Kiwigarden Crunchy Apple Slices 45g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:06', '2018-09-18 11:06:06', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('271', null, '670983080810', 'jellycat 纯色郁金香色huge', 'jellycat huge bunny（tulip）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:06', '2018-09-18 11:06:06', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('272', null, '670983070958', 'jellycat 花耳朵白色中号兔子', 'jellycat middle white bunny（with colorful ears）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:06', '2018-09-18 11:06:06', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('273', null, '670983094268', 'jellycat 纯色淡紫色 中号兔子', 'jellycat middle bunny（light purple）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:06', '2018-09-18 11:06:06', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('274', null, '670983073997', 'jellycat 纯色灰色中号兔子', 'jellycat middle bunny（gray）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:06', '2018-09-18 11:06:06', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('275', null, '9417657599725', 'Alpine蜂毒眼霜', 'ALPINE SILK BEE VENOM ULTRA REPAIR EYE CREAM 15G', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:06', '2018-09-18 11:06:06', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('276', null, '9310059000175', '打虫巧克力', 'combantrin chocolate squares for the threadworm，roundworm and hookworm', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:07', '2018-09-18 11:06:07', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('277', null, '633131633153', 'Mitoq Q10 60粒', 'MitoQ Antioxidant Supplement 60 Capsules', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:07', '2018-09-18 11:06:07', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('278', null, '9421903488145', 'Dr Kulsea 牛油果油', 'Dr Kulsea Avocado oil', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:07', '2018-09-18 11:06:07', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('279', null, '735850381015', '8分钟美白面膜（白)', 'Jema Rose 8+ Luminous Whitening Facial Mask 7 sheets', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:07', '2018-09-18 11:06:07', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('280', null, '735850381008', '8分钟补水面膜(蓝)', 'Jema Rose 8+ Replenishing Hydration Facial Mask 7 sheets', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:07', '2018-09-18 11:06:07', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('281', null, '9311770593359', 'swisse 银杏片50粒', 'Swisse Memory Focus 50t', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:07', '2018-09-18 11:06:07', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('282', null, '608274109001', '童年时光 D3滴液 （天然浆果味）29.6ml', 'ChildLife  Vitamin D3 Liquid Drops Natural Berry Flavour (29.6 ml)', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:07', '2018-09-18 11:06:07', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('283', null, '608274101500', '童年时光 第一防御液 118.5ml', 'ChildLife First Defense(118.5 ml)', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:07', '2018-09-18 11:06:07', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('284', null, '9319064003126', 'B-Box 吸管杯 玫红', 'B-Box sippy cup 240ml（rose）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:07', '2018-09-18 11:06:07', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('285', null, '9416892125034', 'Easiyo 酸奶机', 'easiyo NEW IMPROVED DESIGN EASIYO YOGURT MAKER', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:07', '2018-09-18 11:06:07', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('286', null, '9310488023141', 'Centrum Kids 200ml 儿童善存', 'Centrum Kids Incremin Iron Mixture 200ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:07', '2018-09-18 11:06:07', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('287', null, '9344949001157', 'Bioisland kangaroo 50000 袋鼠精 90粒', 'Bio Island Kangaroo 50000 90s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:07', '2018-09-18 11:06:07', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('288', null, '9414942805653', 'Weet-bix 麦片1.2kg', 'Weet-Bix Cereal 1.2kg', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:08', '2018-09-18 11:06:08', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('289', null, '9300615580708', 'mf洗洁精350ml', 'Morning fresh ultimate 350ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:08', '2018-09-18 11:06:08', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('290', null, '9310088009101', 'U with designs regular with wings 设计版日用22片', 'U with designs regular with wings 22sheets', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:08', '2018-09-18 11:06:08', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('291', null, '9421904692336', 'MEO口罩黑色S号', 'MEO Karen Walker collection，black，small', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:08', '2018-09-18 11:06:08', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('292', null, '9314839011979', 'Sunsense junioeSPF50+防晒50ml', 'EGO Sunsense Kids SPF50 ROLL 50ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:08', '2018-09-18 11:06:08', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('293', null, '9403142000869', 'Whittakers黑巧克力50%250g', 'Whittakers  dark block 50%cocoa 250g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:08', '2018-09-18 11:06:08', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('294', null, '9312657011041', 'Jack jill兔子牙刷', 'Jack N‘ Jill bunny Toothbrush', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:08', '2018-09-18 11:06:08', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('295', null, '9312657131084', 'Jack jill恐龙牙刷', 'Jack and Jill Dino Toothbrush', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:08', '2018-09-18 11:06:08', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('296', null, '9415077098200', 'Pams 藜麦 红豆 450g', 'Pams red quinoa 450g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:08', '2018-09-18 11:06:08', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('297', null, '670983097559', 'jellycat 灰色huge', 'jellycat huge bunny（gray）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:08', '2018-09-18 11:06:08', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('298', null, '9421900569793', 'antipodes juliet洗面奶 200ml', 'Antipodes Juliet Skin Brightening Gel Cleanser 200ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:08', '2018-09-18 11:06:08', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('299', null, '9349254002035', ' goat羊奶皂燕麦 100g', 'Goat soap with oatmeal 100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:08', '2018-09-18 11:06:08', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('300', null, '9400501601163', ' 康维他茶树油蜂胶牙膏 100g', 'Comvita propolis toothpaste with tea tree oil 100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:08', '2018-09-18 11:06:08', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('301', null, '9415991240150', 'red seal 成人牙膏（矿物）100g', '?Red Seal Natural SLS free Toothpaste 100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:08', '2018-09-18 11:06:08', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('302', null, '9300657160364', 'Farex小熊米粉 4+ 125g', 'farex baby rice cereal（original）4+months 125g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:08', '2018-09-18 11:06:08', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('303', null, '9300657160401', 'Farex小熊米粉 6+（香蕉梨）125g', 'farex baby rice cereal（banana&pear）6+months 125g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:09', '2018-09-18 11:06:09', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('304', null, '9369998113743', '龙珠洁面 （紫）', 'Koehl Cleansing Ball-Jacaranda(Purple)', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:09', '2018-09-18 11:06:09', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('305', null, '9327693006579', 'sukin新年套装', 'sukin new year set', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:09', '2018-09-18 11:06:09', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('306', null, '9347802000014', 'revive 手套', 'Revive gel gloves', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:09', '2018-09-18 11:06:09', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('307', null, '708177112822', '茱莉蔻 薰衣草身体乳 300ml', 'Jurlique calming body lotion lavender 300ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:09', '2018-09-18 11:06:09', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('308', null, '9414015002811', '蜂巢 340g', '100% pure natural comb honey 340g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:09', '2018-09-18 11:06:09', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('309', null, '9421017764401', 'Trilogy 圣诞套装 精华版（野玫瑰果油含抗氧化剂 30ml +有机玫瑰果油洁面乳 30ml +玫瑰果精华 30ml）', 'Trilogy Set(Rosehip Oil+Cream Cleanser+Rosapene Serum)', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:09', '2018-09-18 11:06:09', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('310', null, '9300807238837', 'BLACKMORES VE面霜 50g', 'Blackmores VE Cream 50g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:09', '2018-09-18 11:06:09', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('311', null, '9420015002409', 'ecostore 宝宝泡泡浴 200ml', 'ecostore bubble bath 200ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:09', '2018-09-18 11:06:09', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('312', null, '9311770589970', 'Swisse 儿童维生素 120li', 'Swisse Children Ultivite 120s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:09', '2018-09-18 11:06:09', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('313', null, '9331456000087', '解酒片16片', 'Hydrodol Hangover Relief 16 Capsules', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:09', '2018-09-18 11:06:09', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('314', null, '9326420133014', 'Aroma Wash 除螨喷雾 125ml', 'Aroma Wash Linen Spray Lavendar 125ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:09', '2018-09-18 11:06:09', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('315', null, '9420026572113', '科立纯鼻炎喷雾 25ml', 'Clinicians nasal clear spray 25ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:09', '2018-09-18 11:06:09', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('316', null, '708177114307', 'Jurlique 圣诞套装 玫瑰保湿喷雾 30ml+草本修复精华 30ml+活力精华神仙水 10ml+玫瑰护手霜 30ml', 'Jurlique christmas set', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:10', '2018-09-18 11:06:10', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('317', null, '94005790', 'km 口红 17号色 罂粟红色', 'Karen Murrell Lipstick 17', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:10', '2018-09-18 11:06:10', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('318', null, '94005523', 'km 口红14号色 咖啡粉色', 'Karen Murrell Lipstick 14', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:10', '2018-09-18 11:06:10', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('319', null, '9311770598460', 'Swisse 葡萄籽 180粒', 'Swisse Grape Seed 180t', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:10', '2018-09-18 11:06:10', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('320', null, '9421902960055', 'A2 三段 900g', 'A2 Premium Toddler Stage 3 900g Baby Milk Powder', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:10', '2018-09-18 11:06:10', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('321', null, '9421017760090', 'trilogy 洗面奶 200ml', 'Trilogy Rose Hip Cream Cleanser 200ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:10', '2018-09-18 11:06:10', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('322', null, '94006070', 'km口红12号色 大红色', 'Karen Murrell Lipstick 12', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:10', '2018-09-18 11:06:10', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('323', null, '9316254893482', 'HC白藜芦醇口服液200ml', 'healthy care resveratrol liquid 200ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:10', '2018-09-18 11:06:10', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('324', null, '93370134', 'BLACKMORES 血糖管理片 90粒', 'Blackmores Sugar Balance 90s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:10', '2018-09-18 11:06:10', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('325', null, '9421900569809', 'Antipodes 美白精华', 'Antipodes Apostle Skin Brightening Serum 30ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:10', '2018-09-18 11:06:10', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('326', null, '9349254002066', 'Goat 山羊皂椰子油味 100g', 'Goat Soap Coconut Oil 100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:10', '2018-09-18 11:06:10', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('327', null, '9329771000790', 'floedis 小青蛙止咳糖浆', 'Kids Prospan Chesty cough 200ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:10', '2018-09-18 11:06:10', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('328', null, '768990537875', '挪威小鱼 新生儿滴剂60ml', 'Nordic Natural Baby DHA 60ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:10', '2018-09-18 11:06:10', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('329', null, '9420015017328', 'ecostore 宝宝按摩油 125ml', 'ecostore baby body oil 125ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:11', '2018-09-18 11:06:11', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('330', null, '9310320002105', 'FF女性洗液百合味 250ml', 'Femfresh triple New 250ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:11', '2018-09-18 11:06:11', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('331', null, '8714367002950', 'Dermatix 祛疤膏 15g', 'Dermatix silicone gel 15g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:11', '2018-09-18 11:06:11', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('332', null, '9400501003639', '康维他 蜂胶糖（柠檬）500g', ' Comvita Lozenges Lemon 500g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:11', '2018-09-18 11:06:11', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('333', null, '9421023620395', '蜜纽康 马努卡混合蜂蜜1kg', 'MH Manuka Honey Blend 1kg', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:11', '2018-09-18 11:06:11', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('334', null, '9420015002072', 'ecostore 宝宝沐浴露 200ml', 'Ecostore Baby Body Wash 200ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:11', '2018-09-18 11:06:11', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('335', null, '9418783002851', '爱他美 四段（金装） 900g', 'Aptamil Gold Stage 4 900g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:11', '2018-09-18 11:06:11', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('336', null, '9418783000413', '可瑞康羊奶一段 900g', 'Karicare Goat Milk Stage 1 900g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:11', '2018-09-18 11:06:11', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('337', null, '9344949001195', 'Bioisland 助长素一段 150g', 'Bio island Lysine Starter Stage 1 Powder 150g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:11', '2018-09-18 11:06:11', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('338', null, '4210201829447', 'Oral-B 电动牙刷（美白）', 'Oral-B Vitality Plus Prowhite', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:11', '2018-09-18 11:06:11', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('339', null, '9342905001173', 'freeze frame 睫毛膏 8ml', 'Freezeframe Tint&Grow 8ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:11', '2018-09-18 11:06:11', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('340', null, '9400569019153', '好健康 蜂胶胶囊 300粒', 'Good Health Propolis 300s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:11', '2018-09-18 11:06:11', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('341', null, '9400569014554', '好健康 液体钙 150粒', 'Good Health Hi Cal 1000mg 150s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:11', '2018-09-18 11:06:11', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('342', null, '9400575001159', 'THOMPSONS 血糖管理片 60粒', 'Thompson Glucose Manager 60t', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:11', '2018-09-18 11:06:11', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('343', null, '9421025564703', 'go health 葡萄籽 120粒', 'go healthy grape seed 60000mg 120c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:12', '2018-09-18 11:06:12', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('344', null, '9316542111458', 'NATIO 乳液 125ml', 'Natio Moisturising Face Lotion 125ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:12', '2018-09-18 11:06:12', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('345', null, '9311770598156', 'Swisse 睡眠片 100粒', 'Swisse Sleep 100s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:12', '2018-09-18 11:06:12', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('346', null, '9316542110062', 'NATIO 爽肤水 250ml', 'NATIO Skin Toner 250ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:12', '2018-09-18 11:06:12', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('347', null, '94533002', 'Repel 滚珠防虫', 'Repel Junior Roll On', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:12', '2018-09-18 11:06:12', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('348', null, '9421017760076', 'trilogy 日霜 60ml', 'Trilogy Rose Vital Moisturising Cream 60ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:12', '2018-09-18 11:06:12', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('349', null, '9314839004759', 'ego 小章鱼面霜 100g', 'QV kids moisturising cream100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:12', '2018-09-18 11:06:12', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('350', null, '9314839011948', 'ego 小老虎面霜 250g', 'Ego QV baby cream 250g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:12', '2018-09-18 11:06:12', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('351', null, '9342905000121', 'freeze frame睫毛增长液 1.75ml', 'Freezeframe Lash Prescription 1.75ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:12', '2018-09-18 11:06:12', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('352', null, '9314807042516', '佳思敏软糖（复合维生素） 50粒', 'Natureway Kids Smart Gummies (omega3 + multi) 50c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:12', '2018-09-18 11:06:12', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('353', null, '9421009000012', 'GRVE 牛油果油 250g', 'GRVE Avocado oil 250g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:12', '2018-09-18 11:06:12', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('354', null, '9421001820038', '老奶奶臭脚粉（薄荷） 50g', 'Grans Remedy foot powder cooling 50g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:12', '2018-09-18 11:06:12', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('355', null, '9348107001539', 'EAORON 精华胶囊 108粒', 'Eaoron-SWF Whitening Capsules Daily Youth Restoring Serum 108 Capsules', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:12', '2018-09-18 11:06:12', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('356', null, '9334518005181', '香娜露儿 羊胎素精华（粉单只）10ml', 'Chantelle pink Sheep Placenta Extract 10ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:13', '2018-09-18 11:06:13', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('357', null, '9311770593625', 'Swisse 女性维生素 120粒', 'Swisse Women Ultivite 120s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:13', '2018-09-18 11:06:13', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('358', null, '9316090503606', 'Osielin 小恐龙钙 50粒', 'Ostelin VD + cal chew 50S', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:13', '2018-09-18 11:06:13', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('359', null, '9334518005167', '香娜露儿 羊胎素精华（粉套装）10ml*6', 'Chantelle pink Sheep Placenta Extract 10ml*6', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:13', '2018-09-18 11:06:13', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('360', null, '93232180', 'Restoria 黑发还原乳 250ml', 'Restoria Discreet Colour Restoring Cream 250ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:13', '2018-09-18 11:06:13', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('361', null, '9344949001065', 'Bioisland 儿童锌片 120粒', 'Bio Island Zinc 120s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:13', '2018-09-18 11:06:13', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('362', null, '9311770597111', 'Swisse 护肝片 200粒', 'Swisse Liver Detox 200t', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:13', '2018-09-18 11:06:13', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('363', null, '9418783003049', '可瑞康 奶粉 四段 900g', 'Karicare nourish Milk Stage 4 900g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:13', '2018-09-18 11:06:13', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('364', null, '9421902515040', 'savar 爽肤水', 'savar instant boost multi toner 240ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:13', '2018-09-18 11:06:13', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('365', null, '9344949001140', 'Bioisland 孕妇 DHA 60粒', 'Bio Island DHA for pregnancy 60s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:13', '2018-09-18 11:06:13', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('366', null, '9421900569045', 'Antipodes Resurrect爽肤水 100ml', 'Antipodes Resurrect Clarifying Facial Toner(Organic) 100ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:13', '2018-09-18 11:06:13', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('367', null, '9403092901988', '雅漾喷雾 300ml*2', 'Avene Spring Water 2*300ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:13', '2018-09-18 11:06:13', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('368', null, '9421902515378', 'SAVAR 豆腐面膜 100ml', 'Savar Gentle Hydrating Antioxidant Moisture Mask 100ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:14', '2018-09-18 11:06:14', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('369', null, '9421902960048', 'A2 二段 900G', 'A2 Premium Toddler Stage 3 900g Baby Milk Powder', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:14', '2018-09-18 11:06:14', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('370', null, '9421003260221', '康维他 蜂胶糖（低糖） 500G', 'Comvita Lemon&Honey Lozengens 90% less sugar 500g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:14', '2018-09-18 11:06:14', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('371', null, '9421017760762', 'trilogy 玫瑰果油 45ml', 'Trilogy Rose Hip Oil 45ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:14', '2018-09-18 11:06:14', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('372', null, '9414437003045', 'Comvita 康维他柠檬蜂蜜 500g', 'Comvita Sweet Meadow Lemon Honey 500g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:14', '2018-09-18 11:06:14', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('373', null, '9319629102691', '莱文医生鱼子精华 30g', 'Dr LeWinn‘s Line Smoothing Complex Triple Action Day Defence 30g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:14', '2018-09-18 11:06:14', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('374', null, '9316542111502', 'NATIO 面霜 100g', 'Natio Vitamin E Moisturising Cream 100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:14', '2018-09-18 11:06:14', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('375', null, '93304917', 'LUCAS 木瓜膏（大）75g', 'ucas Pawpaw 75g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:14', '2018-09-18 11:06:14', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('376', null, '9310214000064', 'NU-LAX 乐康膏 500g', 'Nulax Fruit Laxative 500g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:14', '2018-09-18 11:06:14', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('377', null, '5060173370824', 'TT梳子', 'Tangle Teezer Hair Brush', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:14', '2018-09-18 11:06:14', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('378', null, '9349254002011', 'goat 羊奶皂（蓝）原味100g', 'Goat Soap Original', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:14', '2018-09-18 11:06:14', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('379', null, '9300807294611', 'BLACKMORES 维骨力 180粒', 'Blackmores Glucosamine 180t', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:14', '2018-09-18 11:06:14', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('380', null, '9421023620203', '蜜纽康 黑蜂胶囊 500粒', 'Manuka Health BIO30 Propolis Cap 500mg 500s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:15', '2018-09-18 11:06:15', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('381', null, '9421017760175', 'trilogy 玫瑰平衡乳液 100ml', 'Trilogy Balancing Face Lotion 100ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:15', '2018-09-18 11:06:15', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('382', null, '708177053897', '茱莉蔻 玫瑰身体乳 300ml', 'Jurlique Body Care rose Lotion 300ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:15', '2018-09-18 11:06:15', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('383', null, '9313818170096', 'Anthogenol 月光宝盒原花青素胶囊 葡萄籽精华淡斑 100粒', 'Anthogenol 100c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:15', '2018-09-18 11:06:15', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('384', null, '9300807294536', 'BLACKMORES 鱼油mini400粒 无腥味', 'Blackmores mini Odourless Fish Oil 1000mg 400s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:15', '2018-09-18 11:06:15', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('385', null, '9311770598187', 'swiss 叶绿素 200粒', 'Swisse Chlorophyll tablet 200t', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:15', '2018-09-18 11:06:15', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('386', null, '9311770596541', 'Swisse 小黄瓜卸妆水 300ml', 'Swisse Micellar makeup remover 300ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:15', '2018-09-18 11:06:15', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('387', null, '608274107007', '童年时光钙镁锌474ml', 'Childlife Liquid Calcium and Magneisum 474ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:15', '2018-09-18 11:06:15', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('388', null, '9338598000043', 'hope‘s湿疹膏60g', 'Hope‘s Relief Cream 60g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:15', '2018-09-18 11:06:15', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('389', null, '9314807022839', '佳思敏软糖（维他命）蔬菜 60粒', 'Natureway Kids Smart Gummies (Vege Multi) 60c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:15', '2018-09-18 11:06:15', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('390', null, '933192003357', 'Life space 60+益生菌 60粒', 'Lifespace spectrum probiotic powder for 60+ 60c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:15', '2018-09-18 11:06:15', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('391', null, '93203555', 'Aerogard 防蚊喷雾175ml', 'Aerogard Odourless Protection 175ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:15', '2018-09-18 11:06:15', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('392', null, '4210201829416', 'Oral-B 电动牙刷（精密）', 'Oral-B Vitality Precision Clean', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:16', '2018-09-18 11:06:16', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('393', null, '9349254002028', 'goat 羊奶皂（绿）柠檬100g', 'Goat Soap Lemon 100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:16', '2018-09-18 11:06:16', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('394', null, '9420015017052', 'ecostore 宝宝身体乳 200ml', 'Ecostore Baby Moisture 200ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:16', '2018-09-18 11:06:16', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('395', null, '9350634002859', 'Unichi 美白丸 60粒', 'Unichi rosehip Extract Complex 60s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:16', '2018-09-18 11:06:16', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('396', null, '9331134927811', 'PAMOL 一岁以下退烧药橙子味200ml', 'pamol relieves children‘s pain&fever 1-12岁 orange 200ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:16', '2018-09-18 11:06:16', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('397', null, '9348895000141', 'bio-e 柠檬酵素 500ml', 'Bio-E Lemon Manuka juice 500ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:16', '2018-09-18 11:06:16', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('398', null, '9312146008446', 'THOMPSONS 西芹籽 60粒', 'Thompson‘s One-A-Day Celery 5000 60s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:16', '2018-09-18 11:06:16', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('399', null, '9420026574285', '科立纯胶原蛋白胶囊 60粒', 'Clinicians Rejuvenate 60s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:17', '2018-09-18 11:06:17', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('400', null, '9413000005400', 'Oasis Sun 防晒霜 250ml', 'Oasis Sun SPF30 250ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:17', '2018-09-18 11:06:17', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('401', null, '9421900569014', 'Antipodes 牛油果晚霜60ml', 'Antipodes Avocado Pear Nourishing Night Cream 60ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:17', '2018-09-18 11:06:17', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('402', null, '9300807249376', 'BLACKMORES 儿童补脑片（男）60粒', 'Blackmores Teen Multi +brain nutrient for guys 60s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:17', '2018-09-18 11:06:17', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('403', null, '9414437000495', 'SweetMeadow麦卢卡柠檬蜜500g', 'Comvita Sweet Meadow Manuka Honey With Lemon 500g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:17', '2018-09-18 11:06:17', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('404', null, '9321547004315', 'Proctosedyl 痔疮膏 30g', 'PROCTOSEDYL OINTMENT 30G', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:17', '2018-09-18 11:06:17', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('405', null, '9344949001287', 'Bioisland 助长素二段 60粒', 'Bio island Lysine Step Up Stage 2 60t', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:17', '2018-09-18 11:06:17', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('406', null, '9310130309531', 'LAMISIL 脚气膏15g', 'Lamisil Cream 15g Treatment For Tinea', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:17', '2018-09-18 11:06:17', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('407', null, '9344949000167', '美可卓 护眼奶片 300g 150粒', 'Maxigenes Chewable Milk 300g 150s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:17', '2018-09-18 11:06:17', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('408', null, '9415991230175', '红印黑糖 500g', 'Red Seal MOLASSES 500g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:17', '2018-09-18 11:06:17', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('409', null, '93537339', 'ego 防汗滚珠 80g', 'Ego QV Naked Anti-Perspirant Deodorant 80g Roll-On', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:17', '2018-09-18 11:06:17', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('410', null, '9348610000142', 'lacues cc气垫 15ml*2', 'Lacues-Avocado CC Cream 15ml x2', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:17', '2018-09-18 11:06:17', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('411', null, '9421900325740', '皇家蜂毒面膜 50ml', 'Royal Nectar Bee Venom Face Mask 50 ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:18', '2018-09-18 11:06:18', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('412', null, '93808064', 'BLACKMORES 西芹籽 50粒', 'Blackmores Celery 3000 50 Tablets', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:18', '2018-09-18 11:06:18', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('413', null, '9400569020487', '好健康葡萄籽 55000 120粒', 'Good Health Grape seed 55000 120s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:18', '2018-09-18 11:06:18', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('414', null, '9400569017838', '好健康葡萄籽 25000 120粒', 'Good Health Grape seed 25000 120s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:18', '2018-09-18 11:06:18', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('415', null, '9300807307199', 'BLACKMORES 孕妇黄金素 180粒', 'Blackmores Pregnancy and Breastfeeding Gold 180s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:18', '2018-09-18 11:06:18', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('416', null, '9421900325757', '皇家蜂毒面霜 50ml', 'Royal Nectar-Moisturising Face Lift 50ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:18', '2018-09-18 11:06:18', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('417', null, '9421017760014', 'trilogy 玫瑰果油 20ml', 'Trilogy Certified Organic Rosehip Oil 20ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:18', '2018-09-18 11:06:18', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('418', null, '9300657160418', 'Farex小熊米粉 9+ 150g', 'Farex Museli With Apple 9 Months+ 150g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:18', '2018-09-18 11:06:18', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('419', null, '9415991240204', 'red seal 成人牙膏（烟渍） 100g', 'Red Seal Smokers Toothpaste 100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:18', '2018-09-18 11:06:18', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('420', null, '9415991240617', 'red seal 儿童牙膏 100g', 'Red Seal kid Toothpaste 100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:18', '2018-09-18 11:06:18', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('421', null, '9342905000565', 'freeze frame 美白霜 30ml', 'FreezeFrame Hyper White 30 ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:18', '2018-09-18 11:06:18', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('422', null, '9400575003856', 'THOMPSONS 蓝莓护眼精华 60粒', 'thompsonsBilberry 12000 - 60 Capsules', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:18', '2018-09-18 11:06:18', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('423', null, '9400097037995', '贺寿利羊奶粉 450g', 'Healtheries Goats Milk Powder 450g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:19', '2018-09-18 11:06:19', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('424', null, '9417780000433', 'Radiance益生菌婴儿滴剂8ML', 'Radiance Pro B Baby Drop 8ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:19', '2018-09-18 11:06:19', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('425', null, '9329401000183', 'goat 羊奶皂（橙）100g', 'the goat soap with oatmeal   100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:19', '2018-09-18 11:06:19', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('426', null, '9312146000433', '星期四 茶树精油50ml', 'Thursday Tea Tree Oil 50ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:19', '2018-09-18 11:06:19', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('427', null, '9418783002837', '爱他美 二段（金装）900g', 'Aptamil Gold Stage 2 900g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:19', '2018-09-18 11:06:19', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('428', null, '9418783002844', '爱他美 三段（金装）900g', 'Aptamil Gold Stage 3 900g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:19', '2018-09-18 11:06:19', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('429', null, '9415991240273', 'red seal 成人牙膏（蜂蜜）', 'Red Seal Propolis Toothpaste 100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:19', '2018-09-18 11:06:19', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('430', null, '9413000010046', 'oasis防晒霜50ml', 'Oasis Sun SPF30 50ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:19', '2018-09-18 11:06:19', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('431', null, '9421017762056', 'trilogy 晚霜', 'Trilogy Rosapene Night Cream 60ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:19', '2018-09-18 11:06:19', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('432', null, '9349254002059', 'goat 羊奶皂（紫）摩洛哥坚果油味 100g', 'Goat soap argan oil 100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:19', '2018-09-18 11:06:19', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('433', null, '768990017483', '挪威小鱼 孕妇DHA 180粒', 'Nordic Natural Prenatal DHA 180s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:19', '2018-09-18 11:06:19', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('434', null, '708177105060', '茱莉蔻 神仙爽肤水 150ml', 'Jurlique Activating Water Essence 150ml Toners', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:19', '2018-09-18 11:06:19', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('435', null, '9314807022860', '佳思敏软糖（vc+锌）60粒', 'Natureway Kids Smart Gummies (VC+ZINC) 60c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:20', '2018-09-18 11:06:20', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('436', null, '9400581038088', '纽乐 辅酶Q10 60粒', 'NutraLife Co-Q Max 60c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:20', '2018-09-18 11:06:20', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('437', null, '9415991240174', 'red seal 成人牙膏（苏打）100g', 'Red Seal Baking Soda Toothpaste 100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:20', '2018-09-18 11:06:20', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('438', null, '9400581038224', '纽乐 植物酵素 60粒', 'Nutralife Digestive Enzymes 60 Capsules', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:20', '2018-09-18 11:06:20', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('439', null, '9344949001249', 'Bioisland 儿童鱼油 90粒', 'Bio Island Cod Liver + Fish Oil Kids 90 Capsules', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:20', '2018-09-18 11:06:20', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('440', null, '9418783000420', '可瑞康 羊奶粉 二段 900g', 'Karicare-Goats Milk Step 2 Follow On Formula From 6 Months 900g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:20', '2018-09-18 11:06:20', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('441', null, '5000174471748', 'VICKS 通鼻膏', 'VICKS Baby Balsam Soothing Baby Care Essential Oils For Comfort 50g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:20', '2018-09-18 11:06:20', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('442', null, '9348107001591', 'EAORON 蜂毒面膜 10ml*8', 'Eaoron-Propolis Facial Mask 10ml x 8pcs', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:20', '2018-09-18 11:06:20', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('443', null, '9319629102738', '莱文医生眼霜 15g', 'Dr LeWinn‘s Line Smoothing Complex S8 Eye Recovery Complex 15g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:20', '2018-09-18 11:06:20', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('444', null, '9312657008010', 'JJ牙膏 （香蕉）50g', 'Jack N Jill Banana Toothpaste - 50g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:20', '2018-09-18 11:06:20', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('445', null, '9314807038298', '佳思敏软糖（黑接谷木）60粒', 'Nature‘s Way Kids Smart Vita Gummies Immunity 60 Pastilles', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:21', '2018-09-18 11:06:21', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('446', null, '9420015002003', 'ecostore 宝宝洗发水 200ml', 'Ecostore Baby Shampoo 200ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:21', '2018-09-18 11:06:21', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('447', null, '9311770600675', 'swisse 更年期片 60粒', 'Swisse Menopause Balance 60t', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:21', '2018-09-18 11:06:21', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('448', null, '9400581021776', '纽乐 关节灵 200粒', 'NutraLife Joint Care 200s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:21', '2018-09-18 11:06:21', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('449', null, '9311770598194', 'Swisse 蜂胶胶囊 210粒', 'Swisse Propolis 210s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:21', '2018-09-18 11:06:21', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('450', null, '9316199000037', 'LUCAS 木瓜膏（小）25g', 'Lucas Pawpaw 25g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:21', '2018-09-18 11:06:21', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('451', null, '9300807261187', 'bm 叶黄素加强版 90粒', 'Blackmores Macu-vision 90c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:21', '2018-09-18 11:06:21', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('452', null, '9315195305061', 'Refresh 眼药水 30支', 'Refresh Eye Drop 0.4ml（30s）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:21', '2018-09-18 11:06:21', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('453', null, '9421017760052', 'trilogy 玫瑰爽肤水 100ml', 'Trilogy Hydrating Mist Toner 100ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:21', '2018-09-18 11:06:21', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('454', null, '9421023620319', '蜜纽康 蜂王浆胶囊365粒', 'Manuka Health Royal Jelly Cap 365s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:21', '2018-09-18 11:06:21', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('455', null, '9312657010013', 'JJ牙膏 （蓝莓）', 'Jack & Jill Blueberry Toothpaste 50g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:21', '2018-09-18 11:06:21', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('456', null, '9418783003025', '可瑞康 羊奶粉 三段 900g', 'Karicare Goat Milk Stage 3 900g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:21', '2018-09-18 11:06:21', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('457', null, '9352188001982', 'Rhea 磁石面膜 100g', 'Rhea Dead Sea Facial Mask 100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:21', '2018-09-18 11:06:21', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('458', null, '9350634008196', '深海四十浔乳液 50ml', 'Unichi Forty Fathoms Skin Regenerator Lotion 50ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:21', '2018-09-18 11:06:21', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('459', null, '94005776', 'km 10号色洋红粉色', 'Karen Murrell Lipstick 10', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:21', '2018-09-18 11:06:21', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('460', null, '9415293126671', 'Parrs 蜂胶皂 140g', 'Parrs Soap Honey & Propolis 140g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:22', '2018-09-18 11:06:22', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('461', null, '9336770010903', 'skin nutrient 洁面魔法泡泡 150ml', 'Skin Nutrient Perfect White Magic Cleanser 150ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:22', '2018-09-18 11:06:22', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('462', null, '93808804', 'BLACKMORES 维生素B群75粒', 'Blackmores Mega B Complex 75 Tablets', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:22', '2018-09-18 11:06:22', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('463', null, '9311770588843', 'swisse 深海鱼油 400c', 'Swisse Ultiboost Odourless Wild Fish Oil 1000mg Cap X 400', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:22', '2018-09-18 11:06:22', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('464', null, '9400501001116', '康维他 5+蜂蜜500g', 'Comvita Manuka Honey UMF 5+ 500g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:22', '2018-09-18 11:06:22', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('465', null, '9347802000021', 'revive 脚套', 'Revive gel socks', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:22', '2018-09-18 11:06:22', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('466', null, '6001159111368', 'Bio-Oil 百洛油', 'BIO-OIL Specialist for Scars and Stretch Marks 200ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:22', '2018-09-18 11:06:22', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('467', null, '9313139301940', '康迪克 水杯', 'CONTIGO KIDS WATER BOTTLE 400ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:22', '2018-09-18 11:06:22', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('468', null, '9421903455291', 'STREAMLAND 柠檬蜂蜜 500g', 'Streamland Lemon Honey 500g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:22', '2018-09-18 11:06:22', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('469', null, '9415262047983', '安佳脱脂奶粉 1kg', 'anchor trim milk powder 1kg', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:22', '2018-09-18 11:06:22', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('470', null, '9400575001289', 'THOMPSONS 维生素C', 'Thompson‘s Vitamin C 1000mg 150s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:22', '2018-09-18 11:06:22', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('471', null, '9310041901527', '拜耳痛经片24粒', 'Naprogesic 275 mg Tablets 24 Pack', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:22', '2018-09-18 11:06:22', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('472', null, '9421017761721', 'trilogy 玫瑰果油 30ml', 'Trilogy Rosehip Oil Antioxidant+  30ml Skincare Serum', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:22', '2018-09-18 11:06:22', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('473', null, '9312657007013', 'JJ牙膏 （树莓） 50g', 'JACK N JILL KIDS TOOTHPASTE- RASPBERRY ', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:22', '2018-09-18 11:06:22', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('474', null, '9369998212453', 'koehl 龙珠洁面球（黑）', 'Koehl Cleansing Ball-Sea Mud(Black)', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:22', '2018-09-18 11:06:22', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('475', null, '9311770598361', 'swisse 前列康 50粒', 'Swisse Prostate 50c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:22', '2018-09-18 11:06:22', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('476', null, '9418783002820', '爱他美 一段（金装）900g', 'Aptamil Gold Stage 1 900g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:23', '2018-09-18 11:06:23', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('477', null, '9400575002095', 'THOMPSONS 银杏叶片 60粒', 'Thompson‘sGinkgo 6000mg 60 Capsules', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:23', '2018-09-18 11:06:23', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('478', null, '9400569018118', '好健康 海洋胶原蛋白加强版60粒', 'Good Health Imaglow 60s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:23', '2018-09-18 11:06:23', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('479', null, '9418783000543', '可瑞康 VC滴剂 10ml', 'Karicare Vitadol C drops 10ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:23', '2018-09-18 11:06:23', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('480', null, '9400575002132', 'THOMPSONS 圣洁莓胶囊 60粒', 'Thompson Vitex 60c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:23', '2018-09-18 11:06:23', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('481', null, '9350634002842', 'unichi 葡萄籽 60粒', 'Unichi Tannat Grape Seed 26000mg 60c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:23', '2018-09-18 11:06:23', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('482', null, '9316090502104', 'Ostelin VD滴液 20ml', 'Ostelin Vitamin D Liquid Kids Strawberry 20ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:23', '2018-09-18 11:06:23', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('483', null, '9421900569892', 'Antipodes Ananda爽肤水', 'Antipodes Ananda Antioxidant Rich Gentle Toner 100ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:23', '2018-09-18 11:06:23', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('484', null, '9415262047969', '安佳全脂奶粉 1kg', 'anchor blue milk 1kg', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:23', '2018-09-18 11:06:23', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('485', null, '9413000016383', 'Oasis BB霜', 'Oasis BB Cream SPF 15 Light - 50ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:23', '2018-09-18 11:06:23', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('486', null, '9312657009017', 'JJ牙膏 草莓味', 'Jack N‘ Jill Natural Organic Toothpaste (Strawberry) - 50g?', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:23', '2018-09-18 11:06:23', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('487', null, '9323245004567', 'Duit 手膜 150g', 'DU‘IT Tough Hands 150g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:23', '2018-09-18 11:06:23', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('488', null, '9418783002745', '可瑞康 普通装奶粉二段', 'Karicare nourish Milk Stage 2 900g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:23', '2018-09-18 11:06:23', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('489', null, '9300807249383', 'BLACKMORES 儿童补脑片（女）', 'Blackmores Teen Multi +brain nutrient for girls 60s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:23', '2018-09-18 11:06:23', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('490', null, '9400575002767', '汤普森葡萄籽120粒', 'Thompson Grape Seed 19000 120s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:23', '2018-09-18 11:06:23', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('491', null, '9421902960031', 'A2 一段', 'A2 Premium Toddler Stage 3 900g Baby Milk Powder', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:23', '2018-09-18 11:06:23', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('492', null, '708177054177', '茱丽蔻玫瑰按摩油 100ml', 'jurlique rose body oil 100ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:23', '2018-09-18 11:06:23', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('493', null, '9344949001294', 'Bioisland 成人乳钙 150粒', 'Bio Island Milk Calcium Bone Care 150c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:23', '2018-09-18 11:06:23', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('494', null, '9322316001245', 'GM 澳芝曼绵羊油 ve 250g', 'G & M Australian Vitimin E Moisturising Cream - 250g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:23', '2018-09-18 11:06:23', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('495', null, '9310214000118', '乐康片剂', 'Nulax-Natural Laxative Tablets with Prebiotic 40 Tablets', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:23', '2018-09-18 11:06:23', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('496', null, '9421902358142', 'HARKER 清肺液 250ml', 'Herkul Harker Deep Lung Support 250ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:23', '2018-09-18 11:06:23', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('497', null, '9311770588232', 'Swisse 男性维生素（50+)', 'Swisse Men‘s Ultivite 50+ 90 Tablets', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:23', '2018-09-18 11:06:23', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('498', null, '9348107000488', 'eaoron 水光针面膜', 'Eaoron Hyaluronic Acid Collagen Hydrating Face Mask 25ml 5 sheets', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:24', '2018-09-18 11:06:24', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('499', null, '9348107001409', 'eaoron 肉毒杆菌面膜', 'Eaoron-Ultimate Anti-Wrinkle Therapy Mask 5 x 25g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:24', '2018-09-18 11:06:24', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('500', null, '4210201746492', 'Oral-B成人刷头三支装', 'Oral B Electric Toothbrush Precision Clean Replace Toothbrush Heads 3pc', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:24', '2018-09-18 11:06:24', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('501', null, '73528243682', 'manchkin 变色勺 ', 'manchkin white hot safety spoons', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:24', '2018-09-18 11:06:24', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('502', null, '9312146001294', '星期四茶树精油10ml', 'Thursday Tea Tree Oil 10ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:24', '2018-09-18 11:06:24', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('503', null, '9400569019146', '好健康卵磷脂200粒', 'Good Health Lecithin 1200  200c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:24', '2018-09-18 11:06:24', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('504', null, '9348107001485', 'eaoron 爽肤水 120ml', 'Eaoron-Hyaluronic Toner 120ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:24', '2018-09-18 11:06:24', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('505', null, '9342905007298', 'ff丰胸膏 100ml', 'Freezeframe Non-surgical Breast Enhancer 100ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:24', '2018-09-18 11:06:24', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('506', null, '94005301', 'km 口红 8号色 艳丽橘红色', 'Karen Murrell Lipstick 08', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:24', '2018-09-18 11:06:24', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('507', null, '9421025560156', 'go healthy 卵磷脂 120粒', 'go healthy lecithin 1200mg 120c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:24', '2018-09-18 11:06:24', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('508', null, '9311770588225', 'Swisse 女性维生素（50+） 90粒', 'Swisse Women‘s Ultivite 50+ 90 Tablets', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:24', '2018-09-18 11:06:24', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('509', null, '8436097092079', '碧昂斯卸妆水500ml', 'Byphasse Micellaire Makeup Remover Solution 500ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:24', '2018-09-18 11:06:24', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('510', null, '9400581043242', '纽乐肾宝 30粒', 'Nutra-Life Herbal Y Extreme 30 Capsules', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:24', '2018-09-18 11:06:24', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('511', null, '9415991240143', 'red seal 成人牙膏（柠檬）100g', 'Red Seal lemon Toothpaste 100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:24', '2018-09-18 11:06:24', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('512', null, '4210201856191', 'oral-B 电动牙刷（敏感）', 'Oral-B Vitality Plus sensitive', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:24', '2018-09-18 11:06:24', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('513', null, '9369998011605', 'koehl 龙珠洁面球（粉色）', 'Koehl Cleansing Ball-Lime Caviar(Pink)', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:24', '2018-09-18 11:06:24', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('514', null, '9323245003065', 'Duit 脚膜 50g', 'DU‘IT Foot&Heel balm 50g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:24', '2018-09-18 11:06:24', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('515', null, '9311770593618', 'Swisse 男性维生素 120粒', 'Swisse Men Ultivite 120s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:24', '2018-09-18 11:06:24', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('516', null, '9400514011423', 'prolife 儿童蓝莓护眼精华 240粒', 'Prolife Junior Bilberry Chews 240s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:24', '2018-09-18 11:06:24', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('517', null, '9415991240211', 'red seal 成人牙膏（草本）100g', 'Red Seal Herbal Toothpaste 100g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:25', '2018-09-18 11:06:25', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('518', null, '9312657011010', 'JJ牙膏 （黑加仑）50g', 'Jack N‘ Jill Kids Natural Toothpaste Blackcurrant 50g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:25', '2018-09-18 11:06:25', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('519', null, '708177098218', '茱莉蔻 乳液 100ml', 'Jurlique Nutri-Define Essential Conditioning Lotion 100ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:25', '2018-09-18 11:06:25', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('520', null, '9403142001385', '惠特克 黑巧+杏仁 250g', 'Whittakers  chocolate（dark almond）250g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:25', '2018-09-18 11:06:25', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('521', null, '9310160814098', 'elevit 爱乐维男性 90g', 'Menevit 90 Capsules Supplement Male Fertility', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:25', '2018-09-18 11:06:25', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('522', null, '9300657160388', 'Farex小熊米粉 6+（多谷物） 125g', 'Farex Original Multigrain Cereal 125g 6+', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:25', '2018-09-18 11:06:25', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('523', null, '9421900569021', 'Antipodes 日霜 60ml', 'Antipodes Vanilla Pod Hydrating Day Cream 60ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:25', '2018-09-18 11:06:25', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('524', null, '9344949001256', 'Bioisland 儿童乳钙 90g', 'Bio Island Milk Calcium for Kids-(90 Capsules)', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:25', '2018-09-18 11:06:25', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('525', null, '9310320021960', 'nair 脱毛膏 75g', 'Nair Hair Remover Cream Sensitive 75g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:25', '2018-09-18 11:06:25', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('526', null, '9312146008668', 'THOMPSONS 液体钙 300粒', 'Thompson Liquid Calcium 300c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:25', '2018-09-18 11:06:25', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('527', null, '9300657160371', 'Farex小熊米粉 4+（香蕉梨） 125g', 'Farex Baby +4 Months Pear and Banana Rice Ceral 125g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:25', '2018-09-18 11:06:25', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('528', null, '9403142000814', '惠特克 榛子 250g', 'Whittakers chocolate（Hazel nut） 250g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:25', '2018-09-18 11:06:25', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('529', null, '9403142000845', '惠特克 金杏仁 250g', 'Whittakers chocolate（almond gold） 250g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:25', '2018-09-18 11:06:25', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('530', null, '9420026563821', '科立纯女性益生菌 30粒', 'Clinicians Flora Restore 30c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:25', '2018-09-18 11:06:25', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('531', null, '9421902462085', 'OXYGEN 酵素氧气面膜 200ml', 'OXYGEN purifying honey masque 200ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:25', '2018-09-18 11:06:25', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('532', null, '768990017209', '挪威小鱼儿童dha 180粒', 'Nordic Natural Nordic Child DHA 180s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:25', '2018-09-18 11:06:25', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('533', null, '9327693006227', 'sukin水乳洁面套装', 'SUKIN KIT LOVE YOUR SKIN 3 STEP FACE KIT', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:25', '2018-09-18 11:06:25', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('534', null, '9348107001072', 'eaoron 美白黑面膜 5片', 'Eaoron-Instant Whitening Face Mask 5pc', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:25', '2018-09-18 11:06:25', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('535', null, '9311770589994', 'Swisse 护肝片 120粒', 'Swisse-Liver Detox 120 Tablets', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:25', '2018-09-18 11:06:25', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('536', null, '9311770590082', 'Swisse 泡腾片 60粒', 'Swisse Ultiboost High Strength Vitamin C Effervescent (3X20 Tablets)', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:26', '2018-09-18 11:06:26', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('537', null, '9311770588218', 'Swisse 胶原蛋白片 100粒', 'Swisse Ultiboost Hair Skin Nails 100 Tablets', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:26', '2018-09-18 11:06:26', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('538', null, '085805564551', '雅顿金胶套装', 'Elizabeth Arden Ceramide Lift and Firm Youth Restoring Collection Set Anti-aging', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:26', '2018-09-18 11:06:26', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('539', null, '9311770590693', 'swisse儿童维生素 60粒', 'swisse Children‘s Ultivite - 60 Tablets', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:26', '2018-09-18 11:06:26', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('540', null, '9421904614086', '特贝优脱脂奶粉 1kg', 'Taupo Pure Premium Skim Milk Powder 1kg', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:26', '2018-09-18 11:06:26', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('541', null, '730870189016', 'GUCCI 竹韵香水 50ml', 'Gucci Bamboo EDT Eau De Toilette Spray 50ml Womens Perfume', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:26', '2018-09-18 11:06:26', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('542', null, '9400575004105', 'THOMPSONS 儿童舒缓液 150ml', 'THOMPSONS  Junior Chest Ease - 150ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:26', '2018-09-18 11:06:26', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('543', null, '13405625', '鱼尾项链', 'fishtail necklace', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:26', '2018-09-18 11:06:26', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('544', null, '9403142000852', '惠特克 牛奶 250g', 'Whittakers chocolate（creamy milk） 250g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:26', '2018-09-18 11:06:26', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('545', null, '9300615055695', 'MF洗洁精（青柠）900ml', 'moring fresh Dishwashing Liquid（lime）900ml?', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:26', '2018-09-18 11:06:26', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('546', null, '9312146010005', 'THOMPSONS 奶蓟草', 'Thompson‘s One-a-day Milk Thistle 42000mg 60 capsules', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:26', '2018-09-18 11:06:26', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('547', null, '9418783003179', '爱他美金装水解 二段', 'Aptamil Gold+ 2 AllerPro Follow-On Formula 6-12 Months step 2 900g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:26', '2018-09-18 11:06:26', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('548', null, '9421904614000', '特贝优 成人全脂', 'Taupo Pure Premium Whole Milk Powder 1kg', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:26', '2018-09-18 11:06:26', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('549', null, '94003680', 'OXYGEN 酵素精华', 'Oxygen Women Ultimate Botanical Serum 20ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:26', '2018-09-18 11:06:26', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('550', null, '9421903364456', 'Antipodes 温和洗面奶', 'Antipodes Grace gentle cream cleanser 120ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:27', '2018-09-18 11:06:27', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('551', null, '1000000000011', '鳕鱼花胶40片 500g', 'isinglass of cod  500g 40s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:27', '2018-09-18 11:06:27', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('552', null, '9418783003407', '爱他美 一段（铂金）900g', 'Aptamil-Profutura Stage 1 Infant Formula 0-6 Months 900g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:27', '2018-09-18 11:06:27', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('553', null, '9310160817723', 'elevit 爱乐维女性 100粒', 'Menevit 100 Capsules Supplement female Fertility', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:27', '2018-09-18 11:06:27', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('554', null, '9210201156888', 'Oral-B 刷头（女孩）', 'Oral B Electric Toothbrush Toothbrush Heads 2pc （girl）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:27', '2018-09-18 11:06:27', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('555', null, '9300607760026', 'Aveeno 身体乳 225ml', 'Aveeno Skin Relief Moisturising Lotion 225ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:27', '2018-09-18 11:06:27', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('556', null, '9350634002828', 'unichi 生蚝精', 'Unichi Oyster Extract  60s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:27', '2018-09-18 11:06:27', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('557', null, '9973521676517', '贝拉米有机奶粉三段 900g', 'Bellamy‘s/Bellamy Organic Milk Drink Toddler Formula Step 3  900g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:27', '2018-09-18 11:06:27', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('558', null, '085805533540', '伊丽莎白雅顿 微囊精华液 30ml', 'Elizabeth Arden Flawless Future Caplet Serum 30ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:27', '2018-09-18 11:06:27', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('559', null, '9300639602967', '德运全脂1kg', 'Devondale Instant Full Cream Milk Powder 1kg', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:27', '2018-09-18 11:06:27', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('560', null, '9421902960062', 'A2 四段', 'A2 Premium Toddler Stage 4 900g Baby Milk Powder', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:27', '2018-09-18 11:06:27', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('561', null, '9400563448638', 'NICE NATURL 饼干 192g*8', 'Nice & Natural Mixed Berry Roasted Nut Bar 192gm x 8', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:27', '2018-09-18 11:06:27', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('562', null, '14214509', '钥匙项链', 'key necklace', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:28', '2018-09-18 11:06:28', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('563', null, '085805520809', '伊丽莎白雅顿 水光霜', 'Elizabeth Arden Visible Difference Gentle Hydrating Cream 50ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:28', '2018-09-18 11:06:28', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('564', null, '9418783003414', '爱他美 二段（铂金）', 'Aptamil-Profutura Stage 2 Infant Formula 6-12 Months 900g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:28', '2018-09-18 11:06:28', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('565', null, '9300615055664', 'MF洗洁精（柠檬）900ml', 'moring fresh Dishwashing Liquid（lemon）900ml?', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:28', '2018-09-18 11:06:28', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('566', null, '4210201746249', 'Oral-B 刷头（男孩）', 'Oral B Electric Toothbrush Toothbrush Heads 2pc （boy）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:28', '2018-09-18 11:06:28', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('567', null, '1000000000008', '鳕鱼花胶10片 500g', 'isinglass of cod  500g 10s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:28', '2018-09-18 11:06:28', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('568', null, '9415293215931', 'rotorua 火山泥面膜 175ml', 'Wild Ferns Rotorua Mud Face Pack with Royal Jelly and Propolis 175ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:28', '2018-09-18 11:06:28', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('569', null, '9300615055909', 'MF洗洁精 (柠檬)400ml', 'moring fresh Dishwashing Liquid（lemon）400ml?', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:28', '2018-09-18 11:06:28', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('570', null, '737052294650', 'GUCCI 花之舞香水 50ml', 'Gucci Flora Eau de Parfum EDP 50ml for Women', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:28', '2018-09-18 11:06:28', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('571', null, '9418783003421', '爱他美 三段（铂金）', 'Aptamil-Profutura Stage 3 Infant Formula  900g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:28', '2018-09-18 11:06:28', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('572', null, '9403142002610', '惠特克 草莓夹心', 'Whittakers  chocolate（milk strawberry）250g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:28', '2018-09-18 11:06:28', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('573', null, '1000000000005', '白参 A级  500g', 'A level white sea cucumber 500g  30+s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:28', '2018-09-18 11:06:28', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('574', null, '9421904552203', 'DDMASK 面膜 7片装', 'DD mask 7sheets', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:28', '2018-09-18 11:06:28', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('575', null, '9342126002317', '荷荷巴油去黑头30ml', 'The Jojoba Company 100% Natural Australian Golden Jojoba 30ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:28', '2018-09-18 11:06:28', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('576', null, '9348610000272', 'LACUES 瘦脸仪', 'Lacues - 24K Gold Micro-Current V-Face Slimming Massager', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:28', '2018-09-18 11:06:28', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('577', null, '1000000000015', '碎花胶 500g', 'isinglass of cod in small 500g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:28', '2018-09-18 11:06:28', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('578', null, '1000000000009', '鳕鱼花胶15片 500g', 'isinglass of cod  500g 15s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:28', '2018-09-18 11:06:28', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('579', null, '9420015002058', 'ecostore 护臀霜', 'Ecostore - Nappy Balm - 60ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:28', '2018-09-18 11:06:28', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('580', null, '1000000000001', '小黑参 50-60头 500g', 'small black sea cucumber 50-60  500g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:28', '2018-09-18 11:06:28', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('581', null, '6971034338992', 'vvc 防晒衣 女款蓝色', 'vvc rash gards （female/blue）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:28', '2018-09-18 11:06:28', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('582', null, '1000000000016', '鹿筋 500g', 'deer tendon 500g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:29', '2018-09-18 11:06:29', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('583', null, '6971034338886', 'vvc 防晒帽 女款蓝色', 'vvc sun hat（female/blue）', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:29', '2018-09-18 11:06:29', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('584', null, '381370039136', 'Aveeno 面霜 140g', 'Aveeno Baby Soothing Relief Moisture Cream Fragrance Free 140g', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:29', '2018-09-18 11:06:29', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('585', null, '085805176617', '伊丽莎白雅顿 多效精华露30ml', 'Elizabeth Arden Visible Whitening Melanin Control Day Essence 30 ML?', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:29', '2018-09-18 11:06:29', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('586', null, '1000000000010', '鳕鱼花胶25片 500g', 'isinglass of cod  500g 25s', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:29', '2018-09-18 11:06:29', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('587', null, '9300615055824', 'MF洗洁精（茉莉）400ml', 'moring fresh Dishwashing Liquid（jasmine）400ml?', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', null, '', null, null, '2018-09-18 11:06:29', '2018-09-18 11:06:29', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('588', null, '', 'Ecostore 去屑护发素 220ml', 'Ecostore dandruff control conditioner 220ml', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '2', '-2', '0', '0', null, '', null, null, '2018-09-18 11:06:29', '2018-09-18 11:06:29', null, null, '0', null, null, null);
INSERT INTO `product` VALUES ('589', null, '9350634002811', ' Unichi 牛油果精华胶囊 60粒', 'Unichi Avocado Extract Complex 60c', '', '0', '1', '1', '0', '0', '1', '0', '0', '0', '0', '2', '-2', '0', '0', null, '', null, null, '2018-09-18 11:06:29', '2018-09-18 11:06:29', null, null, '0', null, null, null);

-- ----------------------------
-- Table structure for product_attr
-- ----------------------------
DROP TABLE IF EXISTS `product_attr`;
CREATE TABLE `product_attr` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `product_attr_key_id` bigint(20) DEFAULT NULL,
  `product_attr_value_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_attr_value_ibfk_1` (`product_attr_key_id`),
  KEY `product_attr_ibfk_3` (`product_id`),
  KEY `product_attr_ibfk_2` (`product_attr_value_id`),
  CONSTRAINT `product_attr_ibfk_1` FOREIGN KEY (`product_attr_key_id`) REFERENCES `product_attr_key` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `product_attr_ibfk_2` FOREIGN KEY (`product_attr_value_id`) REFERENCES `product_attr_value` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `product_attr_ibfk_3` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_attr
-- ----------------------------

-- ----------------------------
-- Table structure for product_attr_key
-- ----------------------------
DROP TABLE IF EXISTS `product_attr_key`;
CREATE TABLE `product_attr_key` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `attr_key` varchar(1024) DEFAULT NULL,
  `position` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_attr_key
-- ----------------------------

-- ----------------------------
-- Table structure for product_attr_value
-- ----------------------------
DROP TABLE IF EXISTS `product_attr_value`;
CREATE TABLE `product_attr_value` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `product_attr_key_id` bigint(20) NOT NULL,
  `attr_value` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_attr_value_ibfk_1` (`product_attr_key_id`),
  CONSTRAINT `product_attr_value_ibfk_1` FOREIGN KEY (`product_attr_key_id`) REFERENCES `product_attr_key` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_attr_value
-- ----------------------------

-- ----------------------------
-- Table structure for product_brand
-- ----------------------------
DROP TABLE IF EXISTS `product_brand`;
CREATE TABLE `product_brand` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `brand_name_cn` varchar(128) DEFAULT NULL,
  `brand_name_en` varchar(128) DEFAULT NULL,
  `brand_img_src` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_brand
-- ----------------------------

-- ----------------------------
-- Table structure for product_stock_history
-- ----------------------------
DROP TABLE IF EXISTS `product_stock_history`;
CREATE TABLE `product_stock_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL,
  `branch_id` bigint(20) DEFAULT NULL,
  `product_name_en` varchar(1024) DEFAULT NULL,
  `qty` int(11) DEFAULT '0',
  `cost` double(10,2) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  KEY `branch_id` (`branch_id`),
  CONSTRAINT `product_stock_history_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `product_stock_history_ibfk_2` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of product_stock_history
-- ----------------------------

-- ----------------------------
-- Table structure for rank_customer
-- ----------------------------
DROP TABLE IF EXISTS `rank_customer`;
CREATE TABLE `rank_customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rank_level` varchar(255) NOT NULL,
  `rank_desc` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rank_customer
-- ----------------------------
INSERT INTO `rank_customer` VALUES ('7', '等级1', 'bbbb');
INSERT INTO `rank_customer` VALUES ('8', '等级2', 'desc');

-- ----------------------------
-- Table structure for rank_product_price
-- ----------------------------
DROP TABLE IF EXISTS `rank_product_price`;
CREATE TABLE `rank_product_price` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL,
  `rank_customer_id` bigint(20) NOT NULL,
  `price` double(10,2) NOT NULL,
  `price1` double(10,2) DEFAULT '0.00',
  `description` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `product_id_rank_customer_id` (`product_id`,`rank_customer_id`),
  KEY `rank_customer_id` (`rank_customer_id`),
  CONSTRAINT `rank_product_price_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `rank_product_price_ibfk_2` FOREIGN KEY (`rank_customer_id`) REFERENCES `rank_customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1723 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rank_product_price
-- ----------------------------
INSERT INTO `rank_product_price` VALUES ('575', '16', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('576', '17', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('577', '18', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('578', '19', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('579', '20', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('580', '21', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('581', '22', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('582', '23', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('583', '24', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('584', '25', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('585', '26', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('586', '27', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('587', '28', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('588', '29', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('589', '30', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('590', '31', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('591', '32', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('592', '33', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('593', '34', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('594', '35', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('595', '36', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('596', '37', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('597', '38', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('598', '39', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('599', '40', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('600', '41', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('601', '42', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('602', '43', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('603', '44', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('604', '45', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('605', '46', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('606', '47', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('607', '48', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('608', '49', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('609', '50', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('610', '51', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('611', '52', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('612', '53', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('613', '54', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('614', '55', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('615', '56', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('616', '57', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('617', '58', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('618', '59', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('619', '60', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('620', '61', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('621', '62', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('622', '63', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('623', '64', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('624', '65', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('625', '66', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('626', '67', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('627', '68', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('628', '69', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('629', '70', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('630', '71', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('631', '72', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('632', '73', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('633', '74', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('634', '75', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('635', '76', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('636', '77', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('637', '78', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('638', '79', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('639', '80', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('640', '81', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('641', '82', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('642', '83', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('643', '84', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('644', '85', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('645', '86', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('646', '87', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('647', '88', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('648', '89', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('649', '90', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('650', '91', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('651', '92', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('652', '93', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('653', '94', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('654', '95', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('655', '96', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('656', '97', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('657', '98', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('658', '99', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('659', '100', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('660', '101', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('661', '102', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('662', '103', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('663', '104', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('664', '105', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('665', '106', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('666', '107', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('667', '108', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('668', '109', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('669', '110', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('670', '111', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('671', '112', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('672', '113', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('673', '114', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('674', '115', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('675', '116', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('676', '117', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('677', '118', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('678', '119', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('679', '120', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('680', '121', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('681', '122', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('682', '123', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('683', '124', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('684', '125', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('685', '126', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('686', '127', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('687', '128', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('688', '129', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('689', '130', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('690', '131', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('691', '132', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('692', '133', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('693', '134', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('694', '135', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('695', '136', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('696', '137', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('697', '138', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('698', '139', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('699', '140', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('700', '141', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('701', '142', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('702', '143', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('703', '144', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('704', '145', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('705', '146', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('706', '147', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('707', '148', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('708', '149', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('709', '150', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('710', '151', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('711', '152', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('712', '153', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('713', '154', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('714', '155', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('715', '156', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('716', '157', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('717', '158', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('718', '159', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('719', '160', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('720', '161', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('721', '162', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('722', '163', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('723', '164', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('724', '165', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('725', '166', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('726', '167', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('727', '168', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('728', '169', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('729', '170', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('730', '171', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('731', '172', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('732', '173', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('733', '174', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('734', '175', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('735', '176', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('736', '177', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('737', '178', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('738', '179', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('739', '180', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('740', '181', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('741', '182', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('742', '183', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('743', '184', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('744', '185', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('745', '186', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('746', '187', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('747', '188', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('748', '189', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('749', '190', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('750', '191', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('751', '192', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('752', '193', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('753', '194', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('754', '195', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('755', '196', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('756', '197', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('757', '198', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('758', '199', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('759', '200', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('760', '201', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('761', '202', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('762', '203', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('763', '204', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('764', '205', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('765', '206', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('766', '207', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('767', '208', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('768', '209', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('769', '210', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('770', '211', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('771', '212', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('772', '213', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('773', '214', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('774', '215', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('775', '216', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('776', '217', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('777', '218', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('778', '219', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('779', '220', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('780', '221', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('781', '222', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('782', '223', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('783', '224', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('784', '225', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('785', '226', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('786', '227', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('787', '228', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('788', '229', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('789', '230', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('790', '231', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('791', '232', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('792', '233', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('793', '234', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('794', '235', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('795', '236', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('796', '237', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('797', '238', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('798', '239', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('799', '240', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('800', '241', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('801', '242', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('802', '243', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('803', '244', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('804', '245', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('805', '246', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('806', '247', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('807', '248', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('808', '249', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('809', '250', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('810', '251', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('811', '252', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('812', '253', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('813', '254', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('814', '255', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('815', '256', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('816', '257', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('817', '258', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('818', '259', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('819', '260', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('820', '261', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('821', '262', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('822', '263', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('823', '264', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('824', '265', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('825', '266', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('826', '267', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('827', '268', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('828', '269', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('829', '270', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('830', '271', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('831', '272', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('832', '273', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('833', '274', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('834', '275', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('835', '276', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('836', '277', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('837', '278', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('838', '279', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('839', '280', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('840', '281', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('841', '282', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('842', '283', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('843', '284', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('844', '285', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('845', '286', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('846', '287', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('847', '288', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('848', '289', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('849', '290', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('850', '291', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('851', '292', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('852', '293', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('853', '294', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('854', '295', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('855', '296', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('856', '297', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('857', '298', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('858', '299', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('859', '300', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('860', '301', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('861', '302', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('862', '303', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('863', '304', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('864', '305', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('865', '306', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('866', '307', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('867', '308', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('868', '309', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('869', '310', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('870', '311', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('871', '312', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('872', '313', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('873', '314', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('874', '315', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('875', '316', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('876', '317', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('877', '318', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('878', '319', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('879', '320', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('880', '321', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('881', '322', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('882', '323', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('883', '324', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('884', '325', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('885', '326', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('886', '327', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('887', '328', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('888', '329', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('889', '330', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('890', '331', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('891', '332', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('892', '333', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('893', '334', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('894', '335', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('895', '336', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('896', '337', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('897', '338', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('898', '339', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('899', '340', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('900', '341', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('901', '342', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('902', '343', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('903', '344', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('904', '345', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('905', '346', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('906', '347', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('907', '348', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('908', '349', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('909', '350', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('910', '351', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('911', '352', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('912', '353', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('913', '354', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('914', '355', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('915', '356', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('916', '357', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('917', '358', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('918', '359', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('919', '360', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('920', '361', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('921', '362', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('922', '363', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('923', '364', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('924', '365', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('925', '366', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('926', '367', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('927', '368', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('928', '369', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('929', '370', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('930', '371', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('931', '372', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('932', '373', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('933', '374', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('934', '375', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('935', '376', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('936', '377', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('937', '378', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('938', '379', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('939', '380', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('940', '381', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('941', '382', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('942', '383', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('943', '384', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('944', '385', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('945', '386', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('946', '387', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('947', '388', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('948', '389', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('949', '390', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('950', '391', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('951', '392', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('952', '393', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('953', '394', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('954', '395', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('955', '396', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('956', '397', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('957', '398', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('958', '399', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('959', '400', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('960', '401', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('961', '402', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('962', '403', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('963', '404', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('964', '405', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('965', '406', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('966', '407', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('967', '408', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('968', '409', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('969', '410', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('970', '411', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('971', '412', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('972', '413', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('973', '414', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('974', '415', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('975', '416', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('976', '417', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('977', '418', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('978', '419', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('979', '420', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('980', '421', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('981', '422', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('982', '423', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('983', '424', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('984', '425', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('985', '426', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('986', '427', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('987', '428', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('988', '429', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('989', '430', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('990', '431', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('991', '432', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('992', '433', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('993', '434', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('994', '435', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('995', '436', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('996', '437', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('997', '438', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('998', '439', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('999', '440', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1000', '441', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1001', '442', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1002', '443', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1003', '444', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1004', '445', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1005', '446', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1006', '447', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1007', '448', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1008', '449', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1009', '450', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1010', '451', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1011', '452', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1012', '453', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1013', '454', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1014', '455', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1015', '456', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1016', '457', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1017', '458', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1018', '459', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1019', '460', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1020', '461', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1021', '462', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1022', '463', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1023', '464', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1024', '465', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1025', '466', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1026', '467', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1027', '468', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1028', '469', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1029', '470', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1030', '471', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1031', '472', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1032', '473', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1033', '474', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1034', '475', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1035', '476', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1036', '477', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1037', '478', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1038', '479', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1039', '480', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1040', '481', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1041', '482', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1042', '483', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1043', '484', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1044', '485', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1045', '486', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1046', '487', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1047', '488', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1048', '489', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1049', '490', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1050', '491', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1051', '492', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1052', '493', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1053', '494', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1054', '495', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1055', '496', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1056', '497', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1057', '498', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1058', '499', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1059', '500', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1060', '501', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1061', '502', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1062', '503', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1063', '504', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1064', '505', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1065', '506', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1066', '507', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1067', '508', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1068', '509', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1069', '510', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1070', '511', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1071', '512', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1072', '513', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1073', '514', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1074', '515', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1075', '516', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1076', '517', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1077', '518', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1078', '519', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1079', '520', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1080', '521', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1081', '522', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1082', '523', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1083', '524', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1084', '525', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1085', '526', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1086', '527', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1087', '528', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1088', '529', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1089', '530', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1090', '531', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1091', '532', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1092', '533', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1093', '534', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1094', '535', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1095', '536', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1096', '537', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1097', '538', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1098', '539', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1099', '540', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1100', '541', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1101', '542', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1102', '543', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1103', '544', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1104', '545', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1105', '546', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1106', '547', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1107', '548', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1108', '549', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1109', '550', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1110', '551', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1111', '552', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1112', '553', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1113', '554', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1114', '555', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1115', '556', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1116', '557', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1117', '558', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1118', '559', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1119', '560', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1120', '561', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1121', '562', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1122', '563', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1123', '564', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1124', '565', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1125', '566', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1126', '567', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1127', '568', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1128', '569', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1129', '570', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1130', '571', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1131', '572', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1132', '573', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1133', '574', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1134', '575', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1135', '576', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1136', '577', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1137', '578', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1138', '579', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1139', '580', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1140', '581', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1141', '582', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1142', '583', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1143', '584', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1144', '585', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1145', '586', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1146', '587', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1147', '588', '7', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1148', '589', '7', '2.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1149', '16', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1150', '17', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1151', '18', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1152', '19', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1153', '20', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1154', '21', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1155', '22', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1156', '23', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1157', '24', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1158', '25', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1159', '26', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1160', '27', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1161', '28', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1162', '29', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1163', '30', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1164', '31', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1165', '32', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1166', '33', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1167', '34', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1168', '35', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1169', '36', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1170', '37', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1171', '38', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1172', '39', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1173', '40', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1174', '41', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1175', '42', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1176', '43', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1177', '44', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1178', '45', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1179', '46', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1180', '47', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1181', '48', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1182', '49', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1183', '50', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1184', '51', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1185', '52', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1186', '53', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1187', '54', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1188', '55', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1189', '56', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1190', '57', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1191', '58', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1192', '59', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1193', '60', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1194', '61', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1195', '62', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1196', '63', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1197', '64', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1198', '65', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1199', '66', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1200', '67', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1201', '68', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1202', '69', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1203', '70', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1204', '71', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1205', '72', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1206', '73', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1207', '74', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1208', '75', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1209', '76', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1210', '77', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1211', '78', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1212', '79', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1213', '80', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1214', '81', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1215', '82', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1216', '83', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1217', '84', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1218', '85', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1219', '86', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1220', '87', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1221', '88', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1222', '89', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1223', '90', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1224', '91', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1225', '92', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1226', '93', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1227', '94', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1228', '95', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1229', '96', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1230', '97', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1231', '98', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1232', '99', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1233', '100', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1234', '101', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1235', '102', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1236', '103', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1237', '104', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1238', '105', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1239', '106', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1240', '107', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1241', '108', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1242', '109', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1243', '110', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1244', '111', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1245', '112', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1246', '113', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1247', '114', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1248', '115', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1249', '116', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1250', '117', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1251', '118', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1252', '119', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1253', '120', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1254', '121', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1255', '122', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1256', '123', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1257', '124', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1258', '125', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1259', '126', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1260', '127', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1261', '128', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1262', '129', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1263', '130', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1264', '131', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1265', '132', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1266', '133', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1267', '134', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1268', '135', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1269', '136', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1270', '137', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1271', '138', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1272', '139', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1273', '140', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1274', '141', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1275', '142', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1276', '143', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1277', '144', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1278', '145', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1279', '146', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1280', '147', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1281', '148', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1282', '149', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1283', '150', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1284', '151', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1285', '152', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1286', '153', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1287', '154', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1288', '155', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1289', '156', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1290', '157', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1291', '158', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1292', '159', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1293', '160', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1294', '161', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1295', '162', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1296', '163', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1297', '164', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1298', '165', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1299', '166', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1300', '167', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1301', '168', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1302', '169', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1303', '170', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1304', '171', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1305', '172', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1306', '173', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1307', '174', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1308', '175', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1309', '176', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1310', '177', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1311', '178', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1312', '179', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1313', '180', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1314', '181', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1315', '182', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1316', '183', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1317', '184', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1318', '185', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1319', '186', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1320', '187', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1321', '188', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1322', '189', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1323', '190', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1324', '191', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1325', '192', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1326', '193', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1327', '194', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1328', '195', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1329', '196', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1330', '197', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1331', '198', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1332', '199', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1333', '200', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1334', '201', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1335', '202', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1336', '203', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1337', '204', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1338', '205', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1339', '206', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1340', '207', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1341', '208', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1342', '209', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1343', '210', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1344', '211', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1345', '212', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1346', '213', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1347', '214', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1348', '215', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1349', '216', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1350', '217', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1351', '218', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1352', '219', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1353', '220', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1354', '221', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1355', '222', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1356', '223', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1357', '224', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1358', '225', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1359', '226', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1360', '227', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1361', '228', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1362', '229', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1363', '230', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1364', '231', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1365', '232', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1366', '233', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1367', '234', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1368', '235', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1369', '236', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1370', '237', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1371', '238', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1372', '239', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1373', '240', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1374', '241', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1375', '242', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1376', '243', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1377', '244', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1378', '245', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1379', '246', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1380', '247', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1381', '248', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1382', '249', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1383', '250', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1384', '251', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1385', '252', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1386', '253', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1387', '254', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1388', '255', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1389', '256', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1390', '257', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1391', '258', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1392', '259', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1393', '260', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1394', '261', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1395', '262', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1396', '263', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1397', '264', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1398', '265', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1399', '266', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1400', '267', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1401', '268', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1402', '269', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1403', '270', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1404', '271', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1405', '272', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1406', '273', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1407', '274', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1408', '275', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1409', '276', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1410', '277', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1411', '278', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1412', '279', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1413', '280', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1414', '281', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1415', '282', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1416', '283', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1417', '284', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1418', '285', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1419', '286', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1420', '287', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1421', '288', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1422', '289', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1423', '290', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1424', '291', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1425', '292', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1426', '293', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1427', '294', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1428', '295', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1429', '296', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1430', '297', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1431', '298', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1432', '299', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1433', '300', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1434', '301', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1435', '302', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1436', '303', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1437', '304', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1438', '305', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1439', '306', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1440', '307', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1441', '308', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1442', '309', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1443', '310', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1444', '311', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1445', '312', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1446', '313', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1447', '314', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1448', '315', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1449', '316', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1450', '317', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1451', '318', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1452', '319', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1453', '320', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1454', '321', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1455', '322', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1456', '323', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1457', '324', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1458', '325', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1459', '326', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1460', '327', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1461', '328', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1462', '329', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1463', '330', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1464', '331', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1465', '332', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1466', '333', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1467', '334', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1468', '335', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1469', '336', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1470', '337', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1471', '338', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1472', '339', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1473', '340', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1474', '341', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1475', '342', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1476', '343', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1477', '344', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1478', '345', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1479', '346', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1480', '347', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1481', '348', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1482', '349', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1483', '350', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1484', '351', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1485', '352', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1486', '353', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1487', '354', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1488', '355', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1489', '356', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1490', '357', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1491', '358', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1492', '359', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1493', '360', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1494', '361', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1495', '362', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1496', '363', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1497', '364', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1498', '365', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1499', '366', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1500', '367', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1501', '368', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1502', '369', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1503', '370', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1504', '371', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1505', '372', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1506', '373', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1507', '374', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1508', '375', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1509', '376', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1510', '377', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1511', '378', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1512', '379', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1513', '380', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1514', '381', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1515', '382', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1516', '383', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1517', '384', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1518', '385', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1519', '386', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1520', '387', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1521', '388', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1522', '389', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1523', '390', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1524', '391', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1525', '392', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1526', '393', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1527', '394', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1528', '395', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1529', '396', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1530', '397', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1531', '398', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1532', '399', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1533', '400', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1534', '401', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1535', '402', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1536', '403', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1537', '404', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1538', '405', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1539', '406', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1540', '407', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1541', '408', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1542', '409', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1543', '410', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1544', '411', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1545', '412', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1546', '413', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1547', '414', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1548', '415', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1549', '416', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1550', '417', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1551', '418', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1552', '419', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1553', '420', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1554', '421', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1555', '422', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1556', '423', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1557', '424', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1558', '425', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1559', '426', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1560', '427', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1561', '428', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1562', '429', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1563', '430', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1564', '431', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1565', '432', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1566', '433', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1567', '434', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1568', '435', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1569', '436', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1570', '437', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1571', '438', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1572', '439', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1573', '440', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1574', '441', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1575', '442', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1576', '443', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1577', '444', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1578', '445', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1579', '446', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1580', '447', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1581', '448', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1582', '449', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1583', '450', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1584', '451', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1585', '452', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1586', '453', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1587', '454', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1588', '455', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1589', '456', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1590', '457', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1591', '458', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1592', '459', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1593', '460', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1594', '461', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1595', '462', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1596', '463', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1597', '464', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1598', '465', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1599', '466', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1600', '467', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1601', '468', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1602', '469', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1603', '470', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1604', '471', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1605', '472', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1606', '473', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1607', '474', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1608', '475', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1609', '476', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1610', '477', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1611', '478', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1612', '479', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1613', '480', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1614', '481', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1615', '482', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1616', '483', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1617', '484', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1618', '485', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1619', '486', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1620', '487', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1621', '488', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1622', '489', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1623', '490', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1624', '491', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1625', '492', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1626', '493', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1627', '494', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1628', '495', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1629', '496', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1630', '497', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1631', '498', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1632', '499', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1633', '500', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1634', '501', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1635', '502', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1636', '503', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1637', '504', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1638', '505', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1639', '506', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1640', '507', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1641', '508', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1642', '509', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1643', '510', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1644', '511', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1645', '512', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1646', '513', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1647', '514', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1648', '515', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1649', '516', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1650', '517', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1651', '518', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1652', '519', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1653', '520', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1654', '521', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1655', '522', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1656', '523', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1657', '524', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1658', '525', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1659', '526', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1660', '527', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1661', '528', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1662', '529', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1663', '530', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1664', '531', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1665', '532', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1666', '533', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1667', '534', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1668', '535', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1669', '536', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1670', '537', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1671', '538', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1672', '539', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1673', '540', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1674', '541', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1675', '542', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1676', '543', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1677', '544', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1678', '545', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1679', '546', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1680', '547', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1681', '548', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1682', '549', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1683', '550', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1684', '551', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1685', '552', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1686', '553', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1687', '554', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1688', '555', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1689', '556', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1690', '557', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1691', '558', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1692', '559', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1693', '560', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1694', '561', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1695', '562', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1696', '563', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1697', '564', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1698', '565', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1699', '566', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1700', '567', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1701', '568', '8', '22.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1702', '569', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1703', '570', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1704', '571', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1705', '572', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1706', '573', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1707', '574', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1708', '575', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1709', '576', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1710', '577', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1711', '578', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1712', '579', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1713', '580', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1714', '581', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1715', '582', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1716', '583', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1717', '584', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1718', '585', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1719', '586', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1720', '587', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1721', '588', '8', '1.00', '1.00', null);
INSERT INTO `rank_product_price` VALUES ('1722', '589', '8', '3.00', '1.00', null);

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `comment_id` bigint(11) NOT NULL,
  `product_id` bigint(11) DEFAULT NULL,
  `article_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `comment_id` (`comment_id`),
  KEY `product_id` (`product_id`),
  KEY `article_id` (`article_id`),
  CONSTRAINT `reply_ibfk_1` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `reply_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `reply_ibfk_3` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `contact_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `website` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `memo` tinytext CHARACTER SET utf8,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `status` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of supplier
-- ----------------------------

-- ----------------------------
-- Table structure for supplier_order
-- ----------------------------
DROP TABLE IF EXISTS `supplier_order`;
CREATE TABLE `supplier_order` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `supplier_id` bigint(11) NOT NULL,
  `user_id` bigint(11) DEFAULT NULL,
  `pay_type` varchar(45) DEFAULT NULL,
  `is_paid` int(11) DEFAULT '0',
  `total_product_price` double(10,2) NOT NULL,
  `total_freight` double(10,2) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '订单状态（0：下单，1：已付款，2：已发货，3：已收货，4：已完成 5：已取消）',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `supplier_company_name` varchar(1024) DEFAULT NULL,
  `supplier_contact_name` varchar(1024) DEFAULT NULL,
  `supplier_email` varchar(1024) DEFAULT NULL,
  `supplier_phone` varchar(1024) DEFAULT NULL,
  `supplier_address` varchar(1024) DEFAULT NULL,
  `admin_msg` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`supplier_id`),
  KEY `order_ibfk_2` (`user_id`),
  CONSTRAINT `supplier_order_ibfk_1` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`),
  CONSTRAINT `supplier_order_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of supplier_order
-- ----------------------------

-- ----------------------------
-- Table structure for supplier_order_item
-- ----------------------------
DROP TABLE IF EXISTS `supplier_order_item`;
CREATE TABLE `supplier_order_item` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `supplier_order_id` bigint(11) NOT NULL,
  `product_id` bigint(11) DEFAULT NULL,
  `num` int(11) NOT NULL,
  `product_cost` double(10,2) DEFAULT NULL,
  `product_price` double(10,2) NOT NULL,
  `product_name_cn` varchar(1024) DEFAULT NULL,
  `product_name_en` varchar(1024) DEFAULT NULL,
  `product_default_src` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_item_ibfk_2` (`product_id`),
  KEY `supplier_order_id` (`supplier_order_id`),
  CONSTRAINT `supplier_order_item_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `supplier_order_item_ibfk_3` FOREIGN KEY (`supplier_order_id`) REFERENCES `supplier_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of supplier_order_item
-- ----------------------------

-- ----------------------------
-- Table structure for supplier_product
-- ----------------------------
DROP TABLE IF EXISTS `supplier_product`;
CREATE TABLE `supplier_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `supplier_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `cost` decimal(10,2) DEFAULT '0.00',
  `cost1` decimal(10,2) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `price1` decimal(10,2) DEFAULT NULL,
  `memo` varchar(1024) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `supplier_id` (`supplier_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `supplier_product_ibfk_1` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`),
  CONSTRAINT `supplier_product_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of supplier_product
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_company_id` bigint(11) NOT NULL,
  `user_group_id` bigint(11) NOT NULL,
  `name` varchar(512) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` enum('Canceled','Staff','Admin','Superadmin') DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_company_id` (`user_company_id`),
  KEY `user_group_id` (`user_group_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`user_company_id`) REFERENCES `user_company` (`id`),
  CONSTRAINT `user_ibfk_2` FOREIGN KEY (`user_group_id`) REFERENCES `user_group` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', '1', 'leo', 'leo@3a.co.nz', 'e10adc3949ba59abbe56e057f20f883e', null, '02218283588', 'Staff', '2017-12-13 14:14:17', '2018-03-16 16:23:12');
INSERT INTO `user` VALUES ('2', '1', '1', 'anjie', 'anjie@3a.co.nz', 'e10adc3949ba59abbe56e057f20f883e', null, '0221828358', 'Superadmin', '2017-12-13 14:14:17', null);
INSERT INTO `user` VALUES ('3', '1', '1', 'test', 'test@test.com', 'e10adc3949ba59abbe56e057f20f883e', null, null, 'Superadmin', '2017-12-13 14:14:17', '2017-12-15 16:59:57');

-- ----------------------------
-- Table structure for user_company
-- ----------------------------
DROP TABLE IF EXISTS `user_company`;
CREATE TABLE `user_company` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(512) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `gst_no` varchar(255) DEFAULT NULL,
  `description` longtext,
  `type` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `status` enum('non_active','cancelled','active') DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `stuff_number` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `bank_account` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `quotation_terms_conditions` longtext,
  `invoice_terms_conditions` longtext,
  `sending_mail` varchar(255) DEFAULT NULL,
  `sending_mail_smtp` varchar(255) DEFAULT NULL,
  `sending_mail_password` varchar(255) DEFAULT NULL,
  `sending_mail_template` text,
  `sending_mail_subject` varchar(500) DEFAULT NULL,
  `sending_mail_cc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_company
-- ----------------------------
INSERT INTO `user_company` VALUES ('1', 'Yomi', null, 'GST-NUM', null, null, 'www.yomi.co.nz', 'info@yomi.co.nz', '0221828358', '', null, null, null, '', null, '', '2018-09-10 13:30:24', null, null, '', '', '0221828358', '123456', '', '', '');

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(512) DEFAULT NULL,
  `creat_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_group
-- ----------------------------
INSERT INTO `user_group` VALUES ('1', 'admin', '2017-05-18 21:20:14', '2017-05-18 21:20:17');

-- ----------------------------
-- Table structure for user_privilege
-- ----------------------------
DROP TABLE IF EXISTS `user_privilege`;
CREATE TABLE `user_privilege` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_group_id` bigint(11) DEFAULT NULL,
  `privilege` enum('not_allowed','allowed') DEFAULT NULL,
  `description` longtext,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_group_id` (`user_group_id`),
  CONSTRAINT `user_privilege_ibfk_1` FOREIGN KEY (`user_group_id`) REFERENCES `user_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_privilege
-- ----------------------------
