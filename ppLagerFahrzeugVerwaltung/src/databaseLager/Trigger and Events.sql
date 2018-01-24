/*@author Julian */

CREATE EVENT `NEW_DAY`
ON SCHEDULE ON SCHEDULE EVERY 1 DAY 
STARTS `2018-01-25 00:00:01`

DO BEGIN

UPDATE `mydb`.`ItemReservation`
SET `overrun` = 1
WHERE `enddate` < SYSDATE AND `isOpen` = 1;

UPDATE `mydb`.`ItemReservation`
SET `isOpen` = 1
WHERE SYSDATE BETWEEN `startdate` AND `enddate` AND `isOpen` = 0;


UPDATE `mydb`.`Item`
SET `lent` = 1
WHERE `item_id` = (SELECT `item_id` FROM `mydb`.`ItemReservation` WHERE `isOpen` = 1);

UPDATE `mydb`.`Item`
SET `lent` = 0
WHERE `item_id` = (SELECT `item_id` FROM `mydb`.`ItemReservation` WHERE `isOpen` = 0);



END;


CREATE OR REPLACE TRIGGER OUT_TRG
AFTER INSERT ON `mydb`.`ItemUsed`
BEGIN

UPDATE `mydb`.`Item`
SET `isOut` = 1
WHERE `item_id` = :NEW.`item_id`
END OUT_TRG;




CREATE OR REPLACE TRIGGER UPDATE_OUT_TRG
AFTER UPDATE ON `mydb`.`ItemUsed`
BEGIN

IF(:NEW.`item_id` != :OLD.`item_id`)
UPDATE `mydb`.`Item`
SET `isOut` = 0
WHERE `item_id` = :OLD.`item_id`;
END IF;

UPDATE `mydb`.`Item`
SET `isOut` = 1
WHERE `item_id` = :NEW.`item_id`
END UPDATE_OUT_TRG;


CREATE OR REPLACE TRIGGER DELETE_OUT_TRG
AFTER UPDATE ON `mydb`.`ItemUsed`
BEGIN

UPDATE `mydb`.`Item`
SET `isOut` = 0
WHERE `item_id` = :OLD.`item_id`;


END DELETE_OUT_TRG;