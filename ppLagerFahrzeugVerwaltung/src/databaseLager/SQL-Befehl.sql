DROP DATABASE IF EXISTS `database`;

CREATE DATABASE `database`;


CREATE TABLE `database`.`user` (
  `user_id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(240) NOT NULL,
  `role` INTEGER UNSIGNED NOT NULL,
  `email` VARCHAR(120) NOT NULL,
  `first_name` VARCHAR(45),
  `last_name` VARCHAR(45),
  `new_user` BOOLEAN NOT NULL,
  PRIMARY KEY (`user_id`)
);



CREATE TABLE `database`.`role` (
  `role_id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`)
);


CREATE TABLE `database`.`item_reservation` (
  `reservation_id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `item_id` INTEGER UNSIGNED NOT NULL,
  `user_id` INTEGER UNSIGNED NOT NULL,
  `startdate` DATE NOT NULL,
  `enddate` DATE NOT NULL,
  `open` BOOLEAN,
  `overrun` BOOLEAN,
  `kilometer` INTEGER UNSIGNED,
  PRIMARY KEY (`reservation_id`)
);


CREATE TABLE `database`.`item` (
  `item_id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `itemName` VARCHAR(45) NOT NULL,
  `lent` BOOLEAN,
  `isOut` BOOLEAN NOT NULL,
  `entrydate` DATE,
  `description` VARCHAR(240),
  `item_picture` VARCHAR(120),
  `type_id` INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY (`item_id`)
);



CREATE TABLE `database`.`item_used` (
  `item_id` INTEGER UNSIGNED NOT NULL,
  `user_id` INTEGER UNSIGNED NOT NULL,
  `type_id` INTEGER UNSIGNED NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`item_id`)
);



CREATE TABLE `database`.`item_type` (
  `type_id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `type_name` VARCHAR(45) NOT NULL,
  `type_kind` INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY (`type_id`)
);



CREATE TABLE `database`.`item_type_role_relation` (
  `type_id` INTEGER UNSIGNED NOT NULL,
  `role_id` INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY (`type_id`, `role_id`)
);



-- CREATE VIEW `database`.`user_role`
-- AS SELECT `r`.`role_name`, `u`.`username`
-- FROM `database`.`role` as `r` join `database`.`user` as `u` on (`r`.`role_id` = `u`.`role`)




