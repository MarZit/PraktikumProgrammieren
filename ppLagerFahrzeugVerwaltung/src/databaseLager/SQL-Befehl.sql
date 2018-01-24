/*@author Julian */

DROP DATABASE IF EXISTS `mydb`;

CREATE DATABASE `mydb`;


CREATE TABLE `mydb`.`User` (
  `user_id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(240) NOT NULL,
  `role` INTEGER UNSIGNED NOT NULL,
  `email` VARCHAR(120),
  `first_name` VARCHAR(45),
  `last_name` VARCHAR(45),
  `new_user` TINYINT,
  PRIMARY KEY (`user_id`)
);



CREATE TABLE `mydb`.`Role` (
  `role_id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`)
);


CREATE TABLE `mydb`.`Item_reservation` (
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


CREATE TABLE `mydb`.`Item` (
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



CREATE TABLE `mydb`.`Item_used` (
  `item_id` INTEGER UNSIGNED NOT NULL,
  `user_id` INTEGER UNSIGNED NOT NULL,
  `type_id` INTEGER UNSIGNED NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`item_id`)
);



CREATE TABLE `mydb`.`Item_type` (
  `type_id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `type_name` VARCHAR(45) NOT NULL,
  `type_kind` INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY (`type_id`)
);



CREATE TABLE `mydb`.`Item_type_role_relation` (
  `type_id` INTEGER UNSIGNED NOT NULL,
  `role_id` INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY (`type_id`, `role_id`)
)





