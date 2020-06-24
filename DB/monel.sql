-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server Version:               10.4.12-MariaDB - mariadb.org binary distribution
-- Server Betriebssystem:        Win64
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Exportiere Datenbank Struktur fuer monel
DROP DATABASE IF EXISTS `monel`;
CREATE DATABASE IF NOT EXISTS `monel` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `monel`;

-- Exportiere Struktur von Tabelle monel.person
DROP TABLE IF EXISTS `person`;
CREATE TABLE IF NOT EXISTS `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `esv` int(11) DEFAULT NULL,
  `notfallkontakt1` int(11) DEFAULT NULL,
  `notfallkontakt2` int(11) DEFAULT NULL,
  `personentyp` varchar(11) NOT NULL,
  `anrede` varchar(9) DEFAULT NULL,
  `titel` varchar(50) DEFAULT NULL,
  `vorname` varchar(50) NOT NULL,
  `nachname` varchar(50) NOT NULL,
  `strasse_hausnummer` varchar(50) NOT NULL,
  `plz` int(4) NOT NULL,
  `ort` varchar(50) NOT NULL,
  `telefonnummer` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `geburtsdatum` date NOT NULL,
  `svnr` bigint(10) DEFAULT NULL,
  `diagnose` varchar(500) DEFAULT NULL,
  `allergien` varchar(500) DEFAULT NULL,
  `sonstiges` varchar(50) DEFAULT NULL,
  `beschaeftigung` varchar(50) DEFAULT NULL,
  `amt` tinyint(1) DEFAULT NULL COMMENT '1 = ehrenamt, 0 = hauptamt',
  `verwendungsgruppe` varchar(4) DEFAULT NULL,
  `gehaltsstufe` varchar(4) DEFAULT NULL,
  `wochenstunden` tinyint(2) DEFAULT NULL,
  `iban` varchar(20) DEFAULT NULL,
  `bic` varchar(8) DEFAULT NULL,
  `vorrueckdatum` date DEFAULT NULL,
  `einstelldatum` date DEFAULT NULL,
  `firmenname` varchar(50) DEFAULT NULL,
  `firmentelefonnummer` varchar(50) DEFAULT NULL,
  `firmenemail` varchar(50) DEFAULT NULL,
  `geloescht` tinyint(1) DEFAULT NULL COMMENT '1 = true, 0 = false',
  PRIMARY KEY (`id`),
  KEY `FKesv` (`esv`),
  KEY `FKnotfallkontakt1` (`notfallkontakt1`),
  KEY `FKnotfallkontakt2` (`notfallkontakt2`),
  CONSTRAINT `FKesv` FOREIGN KEY (`esv`) REFERENCES `person` (`id`),
  CONSTRAINT `FKnotfallkontakt1` FOREIGN KEY (`notfallkontakt1`) REFERENCES `person` (`id`),
  CONSTRAINT `FKnotfallkontakt2` FOREIGN KEY (`notfallkontakt2`) REFERENCES `person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle monel.person: ~8 rows (ungefaehr)
DELETE FROM `person`;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` (`id`, `esv`, `notfallkontakt1`, `notfallkontakt2`, `personentyp`, `anrede`, `titel`, `vorname`, `nachname`, `strasse_hausnummer`, `plz`, `ort`, `telefonnummer`, `email`, `geburtsdatum`, `svnr`, `diagnose`, `allergien`, `sonstiges`, `beschaeftigung`, `amt`, `verwendungsgruppe`, `gehaltsstufe`, `wochenstunden`, `iban`, `bic`, `vorrueckdatum`, `einstelldatum`, `firmenname`, `firmentelefonnummer`, `firmenemail`, `geloescht`) VALUES
	(1, NULL, NULL, NULL, 'SONSTIGES', 'Sonstige', 'Mag.', 'Oli', 'Dübler', 'Pogöriacherstraße 97', 9500, 'Villach', '01234567890', 'test@test.at', '2000-01-01', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(2, NULL, NULL, NULL, 'SONSTIGES', 'Frau', 'Mag.', 'Inge', 'Kaufmann', 'Bismarckstraße 6', 9800, 'Spittal an der Drau', '01234567890', 'test@test.at', '2000-01-01', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(3, 1, 2, NULL, 'KLIENT', 'Herr', 'Mag.', 'Hugo', 'Hinteregger', 'Litzelhofenstraße 45', 9800, 'Spittal an der Drau', '01234567890', 'test@test.at', '2000-01-01', 1234567890, 'Alter', 'Nussalergie', 'test', 'in Rente', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(4, NULL, NULL, NULL, 'MITARBEITER', 'Herr', 'Mag.', 'Mike', 'Gell', 'Südtiroler Straße 4', 5582, 'St. Michael im Lungau', '01234567890', 'test@test.at', '2000-01-01', 1234567890, NULL, NULL, NULL, NULL, 1, 'VG1', 'GS8', 40, 'AT123456789012345678', 'ABCDEFGH', '2022-06-08', '2020-06-08', NULL, NULL, NULL, NULL),
	(5, NULL, NULL, NULL, 'SPONSOR', 'Herr', 'Mag.', 'Uwe', 'Inter', 'Aussichtsstraße 4', 9524, 'St. Magdalen', '01234567890', 'test@test.at', '2000-01-01', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'INT GmbH.', '01234567890', 'INT@INT.at', NULL),
	(6, NULL, NULL, NULL, 'SONSTIGES', 'Sonstige', 'Mag.', 'Hons', 'Petutschnig', 'Haberweg 14', 9580, 'Drobollach am Faaker See', '01234567890', 'test@test.at', '2000-01-01', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(7, NULL, 2, 6, 'KLIENT', 'Herr', 'Mag.', 'Gernot', 'Kulis', 'Dorfstraße 56', 9584, 'Finkenstein am Faaker See ', '01234567890', 'test@test.at', '2000-01-01', 1234567890, 'Alter', 'Heuschnupfen', 'test', 'in Rente', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle monel.rechnung
DROP TABLE IF EXISTS `rechnung`;
CREATE TABLE IF NOT EXISTS `rechnung` (
  `rechnungsnummer` int(11) NOT NULL AUTO_INCREMENT,
  `klient` int(11) NOT NULL,
  `ausstellungsdatum` date DEFAULT NULL,
  `verwendungszweck` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`rechnungsnummer`),
  KEY `FKklient_rechnung` (`klient`),
  CONSTRAINT `FKklient_rechnung` FOREIGN KEY (`klient`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle monel.rechnung: ~1 rows (ungefaehr)
DELETE FROM `rechnung`;
/*!40000 ALTER TABLE `rechnung` DISABLE KEYS */;
/*!40000 ALTER TABLE `rechnung` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle monel.aktivitaet
DROP TABLE IF EXISTS `aktivitaet`;
CREATE TABLE IF NOT EXISTS `aktivitaet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datum` date DEFAULT NULL,
  `aktivitaetsbezeichnung` varchar(50) DEFAULT NULL,
  `kategorie` tinyint(1) DEFAULT NULL,
  `notiz` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle monel.aktivitaet: ~3 rows (ungefaehr)
DELETE FROM `aktivitaet`;
/*!40000 ALTER TABLE `aktivitaet` DISABLE KEYS */;
INSERT INTO `aktivitaet` (`id`, `datum`, `aktivitaetsbezeichnung`, `kategorie`, `notiz`) VALUES
	(1, '2020-05-12', 'Schwimmen', 0, 'test'),
	(2, '2020-05-24', 'Essengehen', 1, 'test');
/*!40000 ALTER TABLE `aktivitaet` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle monel.aktivitaetsprotokoll
DROP TABLE IF EXISTS `aktivitaetsprotokoll`;
CREATE TABLE IF NOT EXISTS `aktivitaetsprotokoll` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aktivitaet` int(11) DEFAULT NULL,
  `mitarbeiter` int(11) DEFAULT NULL,
  `klient` int(11) DEFAULT NULL,
  `rechnung` int(11) DEFAULT NULL,
  `startzeit` time DEFAULT NULL,
  `endzeit` time DEFAULT NULL,
  `jahr_Monat` varchar(7) DEFAULT NULL COMMENT 'YYYY-MM',
  `stundensatz` decimal(4,2) DEFAULT NULL,
  `kilometersatz` decimal(4,2) DEFAULT NULL,
  `notiz` varchar(500) DEFAULT NULL,
  `km` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaktivitaet` (`aktivitaet`),
  KEY `FKrechnung` (`rechnung`),
  KEY `FKklient_aktivitaetsprotokoll` (`klient`),
  KEY `FKmitarbeiter` (`mitarbeiter`),
  CONSTRAINT `FKaktivitaet` FOREIGN KEY (`aktivitaet`) REFERENCES `aktivitaet` (`id`),
  CONSTRAINT `FKklient_aktivitaetsprotokoll` FOREIGN KEY (`klient`) REFERENCES `person` (`id`),
  CONSTRAINT `FKmitarbeiter` FOREIGN KEY (`mitarbeiter`) REFERENCES `person` (`id`),
  CONSTRAINT `FKrechnung` FOREIGN KEY (`rechnung`) REFERENCES `rechnung` (`rechnungsnummer`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle monel.aktivitaetsprotokoll: ~3 rows (ungefaehr)
DELETE FROM `aktivitaetsprotokoll`;
/*!40000 ALTER TABLE `aktivitaetsprotokoll` DISABLE KEYS */;
INSERT INTO `aktivitaetsprotokoll` (`id`, `aktivitaet`, `mitarbeiter`, `klient`, `rechnung`, `startzeit`, `endzeit`, `jahr_Monat`, `stundensatz`, `kilometersatz`, `notiz`, `km`) VALUES
	(1, 1, 4, 3, NULL, '16:30:00', '18:30:00', '05/2020', 12.25, 3.50, 'test', 5),
	(2, 2, 4, 3, NULL, '14:15:00', '18:00:00', '05/2020', 12.25, 3.50, 'test', 7),
	(3, 2, 4, 7, NULL, '14:15:00', '18:00:00', '05/2020', 12.25, 3.50, 'test', 3);
/*!40000 ALTER TABLE `aktivitaetsprotokoll` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle monel.dokument
DROP TABLE IF EXISTS `dokument`;
CREATE TABLE IF NOT EXISTS `dokument` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `besitzerIdPerson` int(11) DEFAULT NULL,
  `besitzerIdAktivitaet` int(11) DEFAULT NULL,
  `pfad` varchar(50) NOT NULL,
  `dokumentenart` varchar(4) DEFAULT NULL,
  `besitzer` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbesitzerPerson` (`besitzerIdPerson`),
  KEY `FKbesitzerEvent` (`besitzerIdAktivitaet`),
  CONSTRAINT `FKbesitzerEvent` FOREIGN KEY (`besitzerIdAktivitaet`) REFERENCES `aktivitaetsprotokoll` (`id`),
  CONSTRAINT `FKbesitzerPerson` FOREIGN KEY (`besitzerIdPerson`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle monel.dokument: ~0 rows (ungefaehr)
DELETE FROM `dokument`;
/*!40000 ALTER TABLE `dokument` DISABLE KEYS */;
/*!40000 ALTER TABLE `dokument` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle monel.kosten
DROP TABLE IF EXISTS `kosten`;
CREATE TABLE IF NOT EXISTS `kosten` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aktivitaetsprotokoll` int(11) NOT NULL,
  `bezeichnung` varchar(50) DEFAULT '',
  `betrag` decimal(6,2) DEFAULT 0.00,
  `steuersatz` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaktivitaetsprotokoll` (`aktivitaetsprotokoll`),
  CONSTRAINT `FKaktivitaetsprotokoll` FOREIGN KEY (`aktivitaetsprotokoll`) REFERENCES `aktivitaetsprotokoll` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle monel.kosten: ~0 rows (ungefaehr)
DELETE FROM `kosten`;
/*!40000 ALTER TABLE `kosten` DISABLE KEYS */;
/*!40000 ALTER TABLE `kosten` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
