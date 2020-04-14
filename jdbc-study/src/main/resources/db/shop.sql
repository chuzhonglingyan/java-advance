
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(10)  NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '商品名称',
  `weight_unit` decimal(10,2) NOT NULL DEFAULT '0'  COMMENT '平均重量  千克/个',
  `weight_unit_price` decimal(10,2) NOT NULL DEFAULT '0'  COMMENT '单价 元/千克',
  `amount_unit_price` decimal(10,2) NOT NULL DEFAULT '0'  COMMENT '单价 元/个',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='商品表';


DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(10)  NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `user_name` varchar(50)  NOT NULL DEFAULT '' COMMENT '用户',
  `total_price` decimal(10,2) NOT NULL DEFAULT '0'  COMMENT '订单金额',
  `order_status` tinyint(4)  NOT NULL DEFAULT '0'  COMMENT '订单状态 0:待付款,1:待发货,2:已发货,3:已完成 5:已取消,6:部分发货,7:退款中 ',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='订单表';


DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details` (
  `id` int(10)  NOT NULL AUTO_INCREMENT COMMENT '详情id',
  `order_id` int(10)  NOT NULL  COMMENT '订单id',
  `product_id` int(10)  NOT NULL  COMMENT '商品id',
  `product_name` varchar(50)  NOT NULL  DEFAULT '' COMMENT '商品名称',
  `product_weight` int(10)  DEFAULT '0'   COMMENT '商品重量',
  `product_amount` int(10)  DEFAULT '0'  COMMENT '商品数量',
  `product_measure` tinyint(4)  NOT NULL DEFAULT '0'  COMMENT '商品计量单位 0:数量 1:重量',
  `product_sale` decimal(10,2)   DEFAULT '0' COMMENT '销售价格',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='订单详情表';


DROP TABLE IF EXISTS `product_stock`;
CREATE TABLE `product_stock` (
  `id` int(10)  NOT NULL AUTO_INCREMENT COMMENT '详情id',
  `product_id` int(10)  NOT NULL  COMMENT '商品id',
  `stock` int(10)  NOT NULL  DEFAULT '0' COMMENT '商品库存',
  `status` tinyint(4)  NOT NULL DEFAULT '0'  COMMENT '库存状态',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='商品库存表';