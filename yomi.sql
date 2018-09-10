/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : yomi

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2018-09-11 11:47:47
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
) ENGINE=InnoDB AUTO_INCREMENT=450 DEFAULT CHARSET=utf8;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of branch_product
-- ----------------------------

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
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`default_from_address_id`) REFERENCES `customer_from_address` (`id`),
  CONSTRAINT `customer_ibfk_2` FOREIGN KEY (`default_to_address_id`) REFERENCES `customer_to_address` (`id`),
  CONSTRAINT `customer_ibfk_3` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', 'test@test.com', 'leo', null, '0221828358', 'e10adc3949ba59abbe56e057f20f883e', '1', '0', null, '2018-05-21 14:13:01', '2018-08-06 17:16:57', null, null, null, null, null, null, null, null, '0221828358', null, 'leo', '0.00', null, null, 'sales', null, null, null, null);
INSERT INTO `customer` VALUES ('2', '13482211133', null, null, '13482211133', 'e10adc3949ba59abbe56e057f20f883e', '1', '0', null, '2018-06-30 15:57:34', '2018-06-30 15:57:34', null, null, null, null, null, null, null, null, null, null, null, '0.00', null, null, 'customer', null, null, null, null);
INSERT INTO `customer` VALUES ('3', '253825496', null, null, '253825496', '529100264c3e5c72bc868dbea7f5a7b8', '1', '0', null, '2018-07-09 18:40:53', '2018-07-09 18:40:53', null, null, null, null, null, null, null, null, null, null, null, '0.00', null, null, 'customer', null, null, null, null);
INSERT INTO `customer` VALUES ('4', 'leo@test.com', null, null, '2538254961', 'e10adc3949ba59abbe56e057f20f883e', '1', '0', null, '2018-07-09 19:54:29', '2018-07-24 18:27:15', null, null, null, null, null, null, null, null, null, null, null, '0.00', null, null, 'sales', null, null, null, null);
INSERT INTO `customer` VALUES ('5', 'vivienaj@hotmail.com', '安结', null, '13901327689', 'e10adc3949ba59abbe56e057f20f883e', '1', '0', null, '2018-07-30 04:22:12', '2018-08-01 02:50:06', null, null, null, null, null, null, null, null, null, null, '安结', '0.00', '北京市朝阳区武胜东里50号楼1602', null, 'customer', null, null, null, null);
INSERT INTO `customer` VALUES ('6', 'gansocc@gmail.com', null, null, 'gansocc@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '1', '0', null, '2018-08-03 06:32:18', '2018-08-03 06:32:18', null, null, null, null, null, null, null, null, null, null, null, '0.00', null, null, 'customer', null, null, null, null);
INSERT INTO `customer` VALUES ('7', 'anjie2702@gmail.com', null, null, 'anjie2702@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '1', '0', null, '2018-08-06 11:34:40', '2018-08-06 11:34:40', null, null, null, null, null, null, null, null, null, null, null, '0.00', null, null, 'customer', null, null, null, null);
INSERT INTO `customer` VALUES ('8', '253825499@qq.com', null, null, '253825499@qq.com', '518ca748df210979c4c71c849a7e11b0', '1', '0', null, '2018-08-24 10:05:42', '2018-08-24 10:05:42', null, null, null, null, null, null, null, null, null, null, null, '0.00', null, null, 'customer', null, null, null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=100023 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_item
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of page_view
-- ----------------------------
INSERT INTO `page_view` VALUES ('150', '2018-09-10', '37');
INSERT INTO `page_view` VALUES ('151', '2018-09-11', '63');

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
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=344 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

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
