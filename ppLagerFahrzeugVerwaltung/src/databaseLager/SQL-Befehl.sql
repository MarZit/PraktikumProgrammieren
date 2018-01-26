/*@author Julian */

-- DROP DATABASE IF EXISTS `mydb`; 

CREATE DATABASE IF NOT EXISTS `mydb`;


CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `user_id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(240) NOT NULL,
  `role` INTEGER UNSIGNED NOT NULL,
  `email` VARCHAR(120),
  `first_name` VARCHAR(45),
  `last_name` VARCHAR(45),
  `new_user` TINYINT,
  PRIMARY KEY (`user_id`),
  CONSTRAINT UC_User UNIQUE(`username`)
);



CREATE TABLE IF NOT EXISTS `mydb`.`role` (
  `role_id` INTEGER UNSIGNED NOT NULL,
  `role_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`),
  CONSTRAINT UC_Role UNIQUE (`role_name`)
);


CREATE TABLE IF NOT EXISTS `mydb`.`item_reservation` (
  `reservation_id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `item_id` INTEGER UNSIGNED NOT NULL,
  `user_id` INTEGER UNSIGNED NOT NULL,
  `startdate` DATE NOT NULL,
  `enddate` DATE NOT NULL,
  `open` TINYINT,
  `overrun` TINYINT,
  `kilometer` INTEGER UNSIGNED,
  PRIMARY KEY (`reservation_id`)
);


CREATE TABLE IF NOT EXISTS `mydb`.`item` (
  `item_id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `itemName` VARCHAR(45) NOT NULL,
  `lent` TINYINT,
  `isOut` TINYINT,
  `entrydate` DATE,
  `description` VARCHAR(240),
  `item_picture` VARCHAR(120),
  `type_id` INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY (`item_id`)
);



CREATE TABLE IF NOT EXISTS `mydb`.`item_used` (
  `item_id` INTEGER UNSIGNED NOT NULL,
  `user_id` INTEGER UNSIGNED NOT NULL,
  `type_id` INTEGER UNSIGNED NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`item_id`)
);



CREATE TABLE  IF NOT EXISTS `mydb`.`item_type` (
  `type_id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `type_name` VARCHAR(45) NOT NULL,
  `type_kind` INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY (`type_id`)
);



CREATE TABLE IF NOT EXISTS `mydb`.`item_type_role_relation` (
  `type_id` INTEGER UNSIGNED NOT NULL,
  `role_id` INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY (`type_id`, `role_id`)
);

REPLACE INTO `mydb`.`role`(`role_id`, `role_name`) VALUES (1, 'admin');
REPLACE INTO `mydb`.`role`(`role_id`, `role_name`) VALUES (2, 'lagerverwalter');
REPLACE INTO `mydb`.`role`(`role_id`, `role_name`) VALUES (3, 'user');

INSERT IGNORE INTO `mydb`.`user`(`username`, `password`, `role`, `email`, `new_user`) VALUES ('Admin', 'admin', '1', 'zu_ersetzen', '1') 



