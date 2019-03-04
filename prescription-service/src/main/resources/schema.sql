DROP TABLE IF EXISTS `prescription`;

CREATE TABLE `prescription`
(
  id              BIGINT(20) PRIMARY KEY NOT NULL,
  created_at      BIGINT(20),
  accountId   BIGINT(20) NOT NULL,
  medicineId     BIGINT(20) NOT NULL,
);
