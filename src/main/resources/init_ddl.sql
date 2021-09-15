-- shop.tb_member definition
CREATE TABLE `tb_member` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `member_id` varchar(100) DEFAULT NULL,
                             `password` varchar(100) DEFAULT NULL,
                             `authority` varchar(100) DEFAULT NULL,
                             `reg_id` varchar(100) DEFAULT NULL,
                             `reg_datetime` datetime DEFAULT NULL,
                             `mod_id` varchar(100) DEFAULT NULL,
                             `mod_datetime` datetime DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;


-- shop.tb_order definition
CREATE TABLE `tb_order` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `member_id` int(11) DEFAULT NULL,
                            `order_code` varchar(100) DEFAULT NULL,
                            `status_code` varchar(100) DEFAULT NULL,
                            `reg_id` varchar(100) NOT NULL,
                            `reg_datetime` datetime NOT NULL,
                            `mod_id` varchar(100) NOT NULL,
                            `mod_datetime` datetime NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;


-- shop.tb_order_detail definition
CREATE TABLE `tb_order_detail` (
                                   `id` int(11) NOT NULL AUTO_INCREMENT,
                                   `order_id` int(11) DEFAULT NULL,
                                   `product_id` int(11) DEFAULT NULL,
                                   `reg_id` varchar(100) NOT NULL,
                                   `reg_datetime` datetime NOT NULL,
                                   `mod_id` varchar(100) NOT NULL,
                                   `mod_datetime` datetime NOT NULL,
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;


-- shop.tb_product definition
CREATE TABLE `tb_product` (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `shop_id` int(11) DEFAULT NULL,
                              `product_code` varchar(12) DEFAULT NULL,
                              `product_name` varchar(100) DEFAULT NULL,
                              `reg_id` varchar(100) NOT NULL,
                              `reg_datetime` datetime NOT NULL,
                              `mod_id` varchar(100) NOT NULL,
                              `mod_datetime` datetime NOT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3;

-- shop.tb_shop definition
CREATE TABLE `tb_shop` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `shop_code` varchar(12) NOT NULL,
                           `shop_name` varchar(100) DEFAULT NULL,
                           `reg_id` varchar(100) NOT NULL,
                           `reg_datetime` datetime NOT NULL,
                           `mod_id` varchar(100) NOT NULL,
                           `mod_datetime` datetime NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;


-- shop.tb_tokens definition
CREATE TABLE `tb_tokens` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `member_id` int(11) DEFAULT NULL,
                             `refresh_token` varchar(100) NOT NULL,
                             `reg_id` varchar(100) NOT NULL,
                             `reg_datetime` datetime NOT NULL,
                             `mod_id` varchar(100) NOT NULL,
                             `mod_datetime` datetime NOT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;