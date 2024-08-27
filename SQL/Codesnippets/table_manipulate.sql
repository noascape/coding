-- UPDATE `kurs`.`teilnehmer` SET `vorname`='Günter' WHERE  `vorname`='Hans' AND `nachname`='Krauter' LIMIT 1;  -- Limit 1 macht, dass nur ein Datensatz verändert wirdc
-- INSERT INTO `kurs`.`teilnehmer` (`vorname`, `nachname`) VALUES ('Peter', 'Huber');
DELETE FROM `kurs`.`teilnehmer` WHERE `id`=3;