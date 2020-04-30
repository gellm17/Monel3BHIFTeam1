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


-- Exportiere Datenbank Struktur für monel
DROP DATABASE IF EXISTS `monel`;
CREATE DATABASE IF NOT EXISTS `monel` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `monel`;

-- Exportiere Struktur von Tabelle monel.aktivitaet
DROP TABLE IF EXISTS `aktivitaet`;
CREATE TABLE IF NOT EXISTS `aktivitaet` (
  `id` int(11) NOT NULL,
  `datum` date DEFAULT NULL,
  `aktivitätsbezeichnung` varchar(50) DEFAULT NULL,
  `kategorie` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Daten Export vom Benutzer nicht ausgewählt

-- Exportiere Struktur von Tabelle monel.aktivitaetsprotokoll
DROP TABLE IF EXISTS `aktivitaetsprotokoll`;
CREATE TABLE IF NOT EXISTS `aktivitaetsprotokoll` (
  `id` int(11) NOT NULL,
  `aktivitaet` int(11) NOT NULL,
  `mitarbeiter` int(11) DEFAULT NULL,
  `klient` int(11) DEFAULT NULL,
  `rechnung` int(11) DEFAULT NULL,
  `startzeit` time DEFAULT NULL,
  `endzeit` time DEFAULT NULL,
  `jahr_Monat` date DEFAULT NULL COMMENT 'ist wichtig für die Rechnung',
  `stundensatz` int(11) DEFAULT NULL,
  `fahrtkosten` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaktivitaet` (`aktivitaet`),
  KEY `FKmitarbeiter` (`mitarbeiter`),
  KEY `FKklient_aktivitaetsprotokoll` (`klient`),
  KEY `FKrechnung` (`rechnung`),
  CONSTRAINT `FKaktivitaet` FOREIGN KEY (`aktivitaet`) REFERENCES `aktivitaet` (`id`),
  CONSTRAINT `FKklient_aktivitaetsprotokoll` FOREIGN KEY (`klient`) REFERENCES `klient` (`id`),
  CONSTRAINT `FKmitarbeiter` FOREIGN KEY (`mitarbeiter`) REFERENCES `mirarbeiter` (`id`),
  CONSTRAINT `FKrechnung` FOREIGN KEY (`rechnung`) REFERENCES `rechnung` (`rechnungsnummmer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Daten Export vom Benutzer nicht ausgewählt

-- Exportiere Struktur von Tabelle monel.klient
DROP TABLE IF EXISTS `klient`;
CREATE TABLE IF NOT EXISTS `klient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `esv` int(11) DEFAULT NULL,
  `notfallkontakt1` int(11) DEFAULT NULL,
  `notfallkontakt2` int(11) DEFAULT NULL,
  `anrede` varchar(50) DEFAULT NULL,
  `titel` varchar(50) DEFAULT NULL,
  `vorname` varchar(50) DEFAULT NULL,
  `nachname` varchar(50) DEFAULT NULL,
  `strasse_hausnummer` varchar(50) DEFAULT NULL,
  `plz` int(11) DEFAULT NULL,
  `ort` varchar(50) DEFAULT NULL,
  `telefonnummer` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `svnr` int(11) DEFAULT NULL,
  `diagnose` varchar(500) DEFAULT NULL,
  `beschaeftigung` varchar(50) DEFAULT NULL,
  `allergien` varchar(500) DEFAULT NULL,
  `sonstiges` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKesv` (`esv`),
  KEY `FKnotfallkontakt1` (`notfallkontakt1`),
  KEY `FKnotfallkontatk2` (`notfallkontakt2`),
  CONSTRAINT `FKesv` FOREIGN KEY (`esv`) REFERENCES `person` (`id`),
  CONSTRAINT `FKnotfallkontakt1` FOREIGN KEY (`notfallkontakt1`) REFERENCES `person` (`id`),
  CONSTRAINT `FKnotfallkontatk2` FOREIGN KEY (`notfallkontakt2`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Daten Export vom Benutzer nicht ausgewählt

-- Exportiere Struktur von Tabelle monel.mirarbeiter
DROP TABLE IF EXISTS `mirarbeiter`;
CREATE TABLE IF NOT EXISTS `mirarbeiter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `anrede` varchar(50) DEFAULT NULL,
  `titel` varchar(50) DEFAULT NULL,
  `vorname` varchar(50) DEFAULT NULL,
  `nachname` varchar(50) DEFAULT NULL,
  `strasse_hausnummer` varchar(50) DEFAULT NULL,
  `plz` int(4) DEFAULT NULL,
  `ort` varchar(50) DEFAULT NULL,
  `telefonnummer` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `svnr` varchar(50) DEFAULT NULL,
  `ehrenamt` varchar(50) DEFAULT NULL COMMENT 'bei NULL Hauptamt',
  `verwendungsgruppe` varchar(50) DEFAULT NULL,
  `gehaltsstufe` int(11) DEFAULT NULL,
  `wochenstunden` int(11) DEFAULT NULL,
  `vorrückungsdatum` date DEFAULT NULL,
  `bankverbindung` varchar(50) DEFAULT NULL,
  `einstelldatum` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Daten Export vom Benutzer nicht ausgewählt

-- Exportiere Struktur von Tabelle monel.person
DROP TABLE IF EXISTS `person`;
CREATE TABLE IF NOT EXISTS `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notfallkontakt` int(11) DEFAULT NULL,
  `anrede` varchar(50) DEFAULT NULL,
  `titel` varchar(50) DEFAULT NULL,
  `vorname` varchar(50) DEFAULT NULL,
  `nachname` varchar(50) DEFAULT NULL,
  `strasse_hausnummer` varchar(50) DEFAULT NULL,
  `plz` int(11) DEFAULT NULL,
  `ort` varchar(50) DEFAULT NULL,
  `telefonnummer` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Daten Export vom Benutzer nicht ausgewählt

-- Exportiere Struktur von Tabelle monel.rechnung
DROP TABLE IF EXISTS `rechnung`;
CREATE TABLE IF NOT EXISTS `rechnung` (
  `rechnungsnummmer` int(11) NOT NULL,
  `klient` int(11) NOT NULL,
  `ausstellungsdatum` date DEFAULT NULL,
  `verwendungszweck` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`rechnungsnummmer`),
  KEY `FKklient_rechnung` (`klient`),
  CONSTRAINT `FKklient_rechnung` FOREIGN KEY (`klient`) REFERENCES `klient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Daten Export vom Benutzer nicht ausgewählt

-- Exportiere Struktur von Tabelle monel.sponsor
DROP TABLE IF EXISTS `sponsor`;
CREATE TABLE IF NOT EXISTS `sponsor` (
  `id` int(11) NOT NULL,
  `anrede` varchar(50) DEFAULT NULL,
  `titel` varchar(50) DEFAULT NULL,
  `vorname` varchar(50) DEFAULT NULL,
  `nachname` varchar(50) DEFAULT NULL,
  `strasse_hausnummer` varchar(50) DEFAULT NULL,
  `plz` int(4) DEFAULT NULL,
  `ort` varchar(50) DEFAULT NULL,
  `telefonnummer` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `firmenname` varchar(50) DEFAULT NULL,
  `firmentelefonnummer` varchar(50) DEFAULT NULL,
  `firmenemail` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Daten Export vom Benutzer nicht ausgewählt

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
