
SET FOREIGN_KEY_CHECKS=0
;

/* Drop Tables */

DROP TABLE IF EXISTS `Item` CASCADE;
DROP TABLE IF EXISTS `Role` CASCADE;
DROP TABLE IF EXISTS `User` CASCADE;
DROP TABLE IF EXISTS `Logged_user` CASCADE;

/* Create Tables */

CREATE TABLE `Item`
(
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `count` INT NOT NULL,
  `state` VARCHAR(50) NOT NULL,
  CONSTRAINT `PK_Item` PRIMARY KEY (`id` ASC)
);

CREATE TABLE `Role`
(
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  CONSTRAINT `PK_Role` PRIMARY KEY (`id` ASC)
);

CREATE TABLE `User`
(
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(250) NOT NULL,
  `roleID` int,
  CONSTRAINT `PK_User` PRIMARY KEY (`id` ASC)
);

CREATE TABLE `Logged_user`
(
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  CONSTRAINT `PK_logged_user` PRIMARY KEY (`id` ASC)
);


insert into role (id, name) values (1, 'user');

;
