/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : scores

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 08/12/2019 23:39:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `weight_unit` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '平均重量  千克/个',
  `weight_unit_price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '单价 元/千克',
  `amount_unit_price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '单价 元/个',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '苹果', 0.30, 10.00, 4.00);
INSERT INTO `product` VALUES (2, '香蕉', 0.10, 6.00, 2.00);
INSERT INTO `product` VALUES (3, '胡萝卜', 0.15, 4.00, 2.00);
INSERT INTO `product` VALUES (4, '卷心菜', 1.00, 3.00, 3.00);
INSERT INTO `product` VALUES (5, '西兰花', 0.40, 10.00, 6.00);
INSERT INTO `product` VALUES (6, '西红柿', 0.30, 8.00, 6.00);
INSERT INTO `product` VALUES (7, '芒果', 0.80, 15.00, 10.00);
INSERT INTO `product` VALUES (8, '橘子', 0.20, 5.00, 3.00);
INSERT INTO `product` VALUES (9, '杏鲍菇', 0.10, 10.00, 5.00);

SET FOREIGN_KEY_CHECKS = 1;
