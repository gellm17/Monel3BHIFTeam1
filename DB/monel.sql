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

-- Exportiere Struktur von Tabelle monel.client
DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `esv` int(11) DEFAULT NULL,
  `anrede` varchar(50) DEFAULT NULL,
  `titel` varchar(50) DEFAULT NULL,
  `vorname` varchar(50) DEFAULT NULL,
  `nachname` varchar(50) DEFAULT NULL,
  `strasse_hausnr` varchar(50) DEFAULT NULL,
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
  CONSTRAINT `FKesv` FOREIGN KEY (`esv`) REFERENCES `person` (`id`)
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
  `strasse_hausnr` varchar(50) DEFAULT NULL,
  `plz` int(11) DEFAULT NULL,
  `ort` varchar(50) DEFAULT NULL,
  `telefonnummer` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKclient` (`notfallkontakt`),
  CONSTRAINT `FKclient` FOREIGN KEY (`notfallkontakt`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Daten Export vom Benutzer nicht ausgewählt

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
