INSERT INTO `mydb`.`Role`(`role_name`) VALUES ('admin');
INSERT INTO `mydb`.`Role`(`role_name`) VALUES ('lagerverwalter');
INSERT INTO `mydb`.`Role`(`role_name`) VALUES ('user');

INSERT INTO `mydb`.`User`(`username`, `password`, `role`, `email`, `first_name`, `last_name`, `new_user`) VALUES ('MZitzelsberger', '123456', '1', 'marcus.zitzelsberger@hof-university.de', 'Marcus', 'Zitzelsberger', '1');
INSERT INTO `mydb`.`User`(`username`, `password`, `role`, `email`, `first_name`, `last_name`, `new_user`) VALUES ('MExner', '123456', '1', 'markus.exner@hof-university.de', 'Markus', 'Exner', '1');
INSERT INTO `mydb`.`User`(`username`, `password`, `role`, `email`, `first_name`, `last_name`, `new_user`) VALUES ('ASkowasch', '123456', '1', 'anja.skowasch@hof-university.de', 'Anja', 'Skowasch', '1');
INSERT INTO `mydb`.`User`(`username`, `password`, `role`, `email`, `first_name`, `last_name`, `new_user`) VALUES ('JUnsleber', '123456', '1', 'julian.unsleber@hof-university.de', 'Julian', 'Unsleber', '1');
INSERT INTO `mydb`.`User`(`username`, `password`, `role`, `email`, `new_user`) VALUES ('Admin', 'admin', '1', 'zu_ersetzen', '1');
INSERT INTO `mydb`.`User`(`username`, `password`, `role`, `email`, `new_user`) VALUES ('User', 'user', '3', 'zu_ersetzen', '1');


INSERT INTO `mydb`.`Item_type`(`type_name`, `type_kind`) VALUES ('PKW', '1');
INSERT INTO `mydb`.`Item_type`(`type_name`, `type_kind`) VALUES ('LKW', '1');
INSERT INTO `mydb`.`Item_type`(`type_name`, `type_kind`) VALUES ('Beamer', '2');
INSERT INTO `mydb`.`Item_type`(`type_name`, `type_kind`) VALUES ('Laptop', '2');
INSERT INTO `mydb`.`Item_type`(`type_name`, `type_kind`) VALUES ('PC', '2');
INSERT INTO `mydb`.`Item_type`(`type_name`, `type_kind`) VALUES ('Bueroklammern', '3');
INSERT INTO `mydb`.`Item_type`(`type_name`, `type_kind`) VALUES ('Bloecke', '3');


INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('VW Golf 7', '1', '0', '0', '2017-11-09', 'Farbe: Weiss Hubraum: 1968 ccm Sitzplaetze 5 Tueren 4/5 Innenausstattung: Stoff/schwarz');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('VW Golf 7', '1', '0', '0', '2016-08-04', 'Farbe: Grau Hubraum: 1968 ccm Sitzplaetze 5 Tueren 4/5 Innenausstattung: Stoff/schwarz');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('Audi A3', '1', '0', '0', '2015-10-07', 'Farbe: Grau Hubraum: 1968 ccm Sitzplaetze 5 Tueren 4/5 Innenausstattung: Teilleder/schwarz');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('Audi A3', '1', '0', '0', '2017-11-09', 'Farbe: Blau Hubraum: 1968 ccm Sitzplaetze 5 Tueren 4/5 Innenausstattung: Leder/schwarz');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('Audi S8', '1', '0', '0', '2016-12-18', 'Farbe: Silber Hubraum: 3993 ccm Sitzplaetze 5 Tueren 4/5 Innenausstattung: Leder/schwarz');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('Audi A3', '1', '0', '0', '2015-10-07', 'Farbe: Grau Hubraum: 1968 ccm Sitzplaetze 5 Tueren 4/5 Innenausstattung: Teilleder/schwarz');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('MAN TGA', '2', '0', '0', '2015-02-12', 'Farbe: Blau Leistung: 324 Kw (441 PS)');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('MAN F2000', '2', '0', '0', '2017-01-23', 'Farbe: Weiss Leistung: 301 Kw (409 PS)');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('MAN TGM', '2', '0', '0', '2016-05-16', 'Farbe: Weiss Leistung: 206 Kw (280 PS)');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('Panasonic PTRQ13KE', '0', '3', '0', '2017-11-09', 'Helligkeit: 10000 ANSI Aufloesung: 4K  5120 x 3200 Kontrast: 20000:1 DICOM Simulation 24/7 Dauerbetrieb Digital Link Anschluesse: Ethernet, HDMI, RS232');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('Panasonic PTRQ13KE', '0', '3', '0', '2017-11-09', 'Helligkeit: 10000 ANSI Aufloesung: 4K  5120 x 3200 Kontrast: 20000:1 DICOM Simulation 24/7 Dauerbetrieb Digital Link Anschluesse: Ethernet, HDMI, RS232');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('SONY LSPX-W1S', '3', '0', '0', '2017-11-09', 'Helligkeit: 10000 ANSI Aufloesung: 4K  5120 x 3200 Kontrast: 20000:1 DICOM Simulation 24/7 Dauerbetrieb Digital Link Anschluesse: Ethernet, HDMI, RS232');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('SONY LSPX-W1S', '3', '0', '0', '2017-11-09', 'Helligkeit: 10000 ANSI Aufloesung: 4K  5120 x 3200 Kontrast: 20000:1 DICOM Simulation 24/7 Dauerbetrieb Digital Link Anschluesse: Ethernet, HDMI, RS232');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('MSI GT83VR-7RF', '4', '0', '0', '2016-12-08', 'MSI GT83VR-7RF Gaming Notebook 18,4" Full HD, Core i7-7920HQ, 64GB RAM, 1512GB Speicher, 2x GTX1080, Win 10');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('MSI GT83VR-7RF', '4', '0', '0', '2015-11-07', 'MSI GT83VR-7RF Gaming Notebook 18,4" Full HD, Core i7-7920HQ, 64GB RAM, 1512GB Speicher, 2x GTX1080, Win 10');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('MSI GT75VR-7RF', '4', '0', '0', '2017-08-21', 'MSI GT75VR 7RF-012 Titan Pro 17,3" Full-HD 120Hz, Core i7-7820HK, 32GB, 1TB HDD + 512GB SSD, GTX 1080 8GB, Win 10');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('Fujitsu Primergy TX2540 M1', '5', '0', '0', '2016-11-10', 'Fujitsu Primergy TX2540 M1, 2x Intel Xeon E5-2420 v2, 64GB RAM, 5x 600GB HDD');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('Fujitsu Primergy TX2540 M1', '5', '0', '0', '2017-05-26', 'Fujitsu Primergy TX2540 M1, 2x Intel Xeon E5-2420 v2, 64GB RAM, 5x 600GB HDD');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('HP EliteDesk 800 G3 TWR 1NE25EA', '5', '0', '0', '2015-12-23', 'HP EliteDesk 800 G3 TWR 1NE25EA Intel Core i7-7700 4x 3,60GHz, 16GB RAM, 256GB SSD + 1TB HDD, NVIDIA GTX1080, Win10');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('HP EliteDesk 800 G3 TWR 1NE25EA', '5', '0', '0', '2017-10-11', 'HP EliteDesk 800 G3 TWR 1NE25EA Intel Core i7-7700 4x 3,60GHz, 16GB RAM, 256GB SSD + 1TB HDD, NVIDIA GTX1080, Win10');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('MSI Aegis Ti3 VR7RE SLI-002DE', '5', '0', '0', '2016-11-12', 'MSI Aegis Ti3 VR7RE SLI-002DE Intel Core i7-7700K, 64GB RAM, 1TB SSD + 3TB HDD, 2x MSI GeForce GTX 1080 GAMING 8G SLI, Win 10');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('Bueroklammern Klein', '6', '0', '0', '2017-11-09', 'Bueroklammern Klein');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('Bueroklammern Klein', '6', '0', '0', '2017-11-09', 'Bueroklammern Klein');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('Bueroklammern Klein', '6', '0', '0', '2017-11-09', 'Bueroklammern Klein');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('Bueroklammern Gross', '6', '0', '0', '2017-11-09', 'Bueroklammern Gross');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('Bueroklammern Gross', '6', '0', '0', '2017-11-09', 'Bueroklammern Gross');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('Bloecke liniert DinA4', '7', '0', '0', '2017-11-09', 'Bloecke liniert DinA4');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('Bloecke liniert DinA4', '7', '0', '0', '2017-11-09', 'Bloecke liniert DinA4');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('Bloecke kariert DinA4', '7', '0', '0', '2017-11-09', 'Bloecke kariert DinA4');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('Bloecke kariert DinA4', '7', '0', '0', '2017-11-09', 'Bloecke kariert DinA4');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('Bloecke Weiss DinA3', '7', '0', '0', '2017-11-09', 'Bloecke Weiss DinA3');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('Bloecke Weiss DinA3', '7', '0', '0', '2017-11-09', 'Bloecke Weiss DinA3');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('Bloecke Weiss DinA3', '7', '0', '0', '2017-11-09', 'Bloecke Weiss DinA3');
INSERT INTO `mydb`.`Item`(`itemName`, `type_id`, `isOut`, `lent`, `entrydate`, `description`) VALUES ('Bloecke Weiss DinA3', '7', '0', '0', '2017-11-09', 'Bloecke Weiss DinA3')


