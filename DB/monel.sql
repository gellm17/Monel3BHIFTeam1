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
  `id` int(11) NOT NULL,
  `salutation` varchar(50) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `street_housenr` varchar(50) DEFAULT NULL,
  `postcode` int(11) DEFAULT NULL,
  `place` varchar(50) DEFAULT NULL,
  `phonenumber` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `ssnr` int(11) DEFAULT NULL,
  `diagnosis` varchar(50) DEFAULT NULL,
  `employment` varchar(50) DEFAULT NULL,
  `allergies` varchar(50) DEFAULT NULL,
  `other` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Daten Export vom Benutzer nicht ausgewählt

-- Exportiere Struktur von Tabelle monel.person
DROP TABLE IF EXISTS `person`;
CREATE TABLE IF NOT EXISTS `person` (
  `id` int(11) NOT NULL,
  `salutation` varchar(50) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `street_housenr` varchar(50) DEFAULT NULL,
  `postcode` int(11) DEFAULT NULL,
  `place` varchar(50) DEFAULT NULL,
  `phonenumber` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Daten Export vom Benutzer nicht ausgewählt

-- Exportiere Struktur von Tabelle monel.responsible_for
DROP TABLE IF EXISTS `responsible_for`;
CREATE TABLE IF NOT EXISTS `responsible_for` (
  `idc` int(11) NOT NULL,
  `idp` int(11) NOT NULL,
  `flag` varchar(50) NOT NULL DEFAULT '' COMMENT 'ESV/Notfallkontakt',
  KEY `FKclient` (`idc`),
  KEY `FKperson` (`idp`),
  CONSTRAINT `FKclient` FOREIGN KEY (`idc`) REFERENCES `client` (`id`),
  CONSTRAINT `FKperson` FOREIGN KEY (`idp`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Daten Export vom Benutzer nicht ausgewählt

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
