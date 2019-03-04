DROP TABLE IF EXISTS `medicine`;

CREATE TABLE `medicine` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `medicine_code` varchar(255) DEFAULT NULL,
  `medicine_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `medicine` VALUES ('1', 'New Medicine For Cancer', '100', 'code1', 'Cancer1');