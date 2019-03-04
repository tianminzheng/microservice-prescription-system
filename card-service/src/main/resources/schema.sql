DROP TABLE IF EXISTS `card`;

CREATE TABLE `card` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_code` varchar(255) DEFAULT NULL,
  `card_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `card` VALUES ('1', 'card1', 'card1');
INSERT INTO `card` VALUES ('2', 'card2', 'card2');