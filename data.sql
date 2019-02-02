
SET FOREIGN_KEY_CHECKS=0
;

/* Drop Tables */

DROP TABLE IF EXISTS `Item` CASCADE
;

/* Create Tables */

CREATE TABLE `Item`
(
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `count` INT NOT NULL,
  `state` VARCHAR(50) NOT NULL,
  CONSTRAINT `PK_Item` PRIMARY KEY (`id` ASC)
)

;
