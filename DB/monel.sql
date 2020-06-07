-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 07. Jun 2020 um 18:43
-- Server-Version: 10.4.11-MariaDB
-- PHP-Version: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `monel`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `aktivitaet`
--

CREATE TABLE `aktivitaet` (
  `id` int(11) NOT NULL,
  `datum` date DEFAULT NULL,
  `aktivitaetsbezeichnung` varchar(50) DEFAULT NULL,
  `kategorie` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `aktivitaet`
--

INSERT INTO `aktivitaet` (`id`, `datum`, `aktivitaetsbezeichnung`, `kategorie`) VALUES
(1, '2020-05-12', 'Einzelaktivität', 0),
(2, '2020-05-24', 'Gruppenaktivität', 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `aktivitaetsprotokoll`
--

CREATE TABLE `aktivitaetsprotokoll` (
  `id` int(11) NOT NULL,
  `aktivitaet` int(11) DEFAULT NULL,
  `mitarbeiter` int(11) DEFAULT NULL,
  `klient` int(11) DEFAULT NULL,
  `rechnung` int(11) DEFAULT NULL,
  `startzeit` time DEFAULT NULL,
  `endzeit` time DEFAULT NULL,
  `jahr_Monat` varchar(7) DEFAULT NULL COMMENT 'YYYY-MM',
  `stundensatz` decimal(4,2) DEFAULT NULL,
  `fahrtkosten` decimal(4,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `aktivitaetsprotokoll`
--

INSERT INTO `aktivitaetsprotokoll` (`id`, `aktivitaet`, `mitarbeiter`, `klient`, `rechnung`, `startzeit`, `endzeit`, `jahr_Monat`, `stundensatz`, `fahrtkosten`) VALUES
(1, 1, 4, 3, NULL, '16:30:00', '18:30:00', '05/2020', '12.25', '13.50'),
(2, 2, 4, 3, NULL, '14:15:00', '18:00:00', '05/2020', '12.25', '13.50'),
(3, 2, 4, 7, NULL, '14:15:00', '18:00:00', '05/2020', '12.25', '13.50');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `dokument`
--

CREATE TABLE `dokument` (
  `id` int(11) NOT NULL,
  `besitzerIdPerson` int(11) DEFAULT NULL,
  `besitzerIdAktivitaet` int(11) DEFAULT NULL,
  `pfad` varchar(50) NOT NULL,
  `dokumentenart` varchar(4) DEFAULT NULL,
  `besitzer` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `person`
--

CREATE TABLE `person` (
  `id` int(11) NOT NULL,
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
  `geloescht` tinyint(1) DEFAULT NULL COMMENT '1 = true, 0 = false'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `person`
--

INSERT INTO `person` (`id`, `esv`, `notfallkontakt1`, `notfallkontakt2`, `personentyp`, `anrede`, `titel`, `vorname`, `nachname`, `strasse_hausnummer`, `plz`, `ort`, `telefonnummer`, `email`, `geburtsdatum`, `svnr`, `diagnose`, `allergien`, `sonstiges`, `beschaeftigung`, `amt`, `verwendungsgruppe`, `gehaltsstufe`, `wochenstunden`, `iban`, `bic`, `vorrueckdatum`, `einstelldatum`, `firmenname`, `firmentelefonnummer`, `firmenemail`, `geloescht`) VALUES
(1, NULL, NULL, NULL, 'SONSTIGES', 'Sonstige', 'Mag.', 'esvTest', 'esvTest', 'Südtiroler Straße 1', 1234, 'Sillian', '01234567890', 'test@test.at', '2000-01-01', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, NULL, NULL, NULL, 'SONSTIGES', 'Herr', 'Mag.', 'notfallTest', 'notfall1Test', 'Südtiroler Straße 2', 1234, 'Sillian', '01234567890', 'test@test.at', '2000-01-01', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(3, 1, 2, NULL, 'KLIENT', 'Herr', 'Mag.', 'klient1Test', 'klient1Test', 'Südtiroler Straße 3', 1234, 'Sillian', '01234567890', 'test@test.at', '2000-01-01', 1234567890, 'Alter', 'Nussalergie', 'test', 'in Rente', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(4, NULL, NULL, NULL, 'MITARBEITER', 'Frau', 'Mag.', 'mitarbeiterTest', 'mitarbeiterTest', 'Südtiroler Straße 4', 1234, 'Sillian', '01234567890', 'test@test.at', '2000-01-01', 1234567890, NULL, NULL, NULL, NULL, 1, 'VG1', 'GS8', 40, 'AT123456789012345678', 'ABCDEFGH', '2020-06-10', '2016-01-01', NULL, NULL, NULL, NULL),
(5, NULL, NULL, NULL, 'SPONSOR', 'Frau', 'Mag.', 'FirmaTest', 'FirmaTest', 'Südtiroler Straße 5', 1234, 'Sillian', '01234567890', 'test@test.at', '2000-01-01', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'TEST GmbH.', '01234567890', 'test@test.at', NULL),
(6, NULL, NULL, NULL, 'SONSTIGES', 'Sonstige', 'Mag.', 'notfall2Test', 'notfall2Test', 'Südtiroler Straße 6', 1234, 'Sillian', '01234567890', 'test@test.at', '2000-01-01', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(7, NULL, 2, 6, 'KLIENT', 'Herr', 'Mag.', 'klient2Test', 'klient2Test', 'Südtiroler Straße 7', 1234, 'Sillian', '01234567890', 'test@test.at', '2000-01-01', 1234567890, 'Alter', 'Heuschnupfen', 'test', 'in Rente', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `rechnung`
--

CREATE TABLE `rechnung` (
  `rechnungsnummer` int(11) NOT NULL,
  `klient` int(11) NOT NULL,
  `ausstellungsdatum` date DEFAULT NULL,
  `verwendungszweck` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `rechnung`
--

INSERT INTO `rechnung` (`rechnungsnummer`, `klient`, `ausstellungsdatum`, `verwendungszweck`) VALUES
(1, 3, '2020-06-07', 'Monatsrechnung 05/2020 für klient1Test klient1Test'),
(2, 3, '2020-06-07', 'Monatsrechnung 05/2020 für klient1Test klient1Test'),
(3, 3, '2020-06-07', 'Monatsrechnung 05/2020 für klient1Test klient1Test'),
(4, 3, '2020-06-07', 'Monatsrechnung 06/2020 für klient1Test klient1Test'),
(5, 3, '2020-06-07', 'Monatsrechnung 05/2020 für klient1Test klient1Test'),
(6, 3, '2020-06-07', 'Monatsrechnung 05/2020 für klient1Test klient1Test'),
(7, 3, '2020-06-07', 'Monatsrechnung 05/2020 für klient1Test klient1Test');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `aktivitaet`
--
ALTER TABLE `aktivitaet`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `aktivitaetsprotokoll`
--
ALTER TABLE `aktivitaetsprotokoll`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKaktivitaet` (`aktivitaet`),
  ADD KEY `FKrechnung` (`rechnung`),
  ADD KEY `FKklient_aktivitaetsprotokoll` (`klient`),
  ADD KEY `FKmitarbeiter` (`mitarbeiter`);

--
-- Indizes für die Tabelle `dokument`
--
ALTER TABLE `dokument`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbesitzerPerson` (`besitzerIdPerson`),
  ADD KEY `FKbesitzerEvent` (`besitzerIdAktivitaet`);

--
-- Indizes für die Tabelle `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKesv` (`esv`),
  ADD KEY `FKnotfallkontakt1` (`notfallkontakt1`),
  ADD KEY `FKnotfallkontakt2` (`notfallkontakt2`);

--
-- Indizes für die Tabelle `rechnung`
--
ALTER TABLE `rechnung`
  ADD PRIMARY KEY (`rechnungsnummer`),
  ADD KEY `FKklient_rechnung` (`klient`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `aktivitaet`
--
ALTER TABLE `aktivitaet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT für Tabelle `aktivitaetsprotokoll`
--
ALTER TABLE `aktivitaetsprotokoll`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT für Tabelle `dokument`
--
ALTER TABLE `dokument`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `person`
--
ALTER TABLE `person`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT für Tabelle `rechnung`
--
ALTER TABLE `rechnung`
  MODIFY `rechnungsnummer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `aktivitaetsprotokoll`
--
ALTER TABLE `aktivitaetsprotokoll`
  ADD CONSTRAINT `FKaktivitaet` FOREIGN KEY (`aktivitaet`) REFERENCES `aktivitaet` (`id`),
  ADD CONSTRAINT `FKklient_aktivitaetsprotokoll` FOREIGN KEY (`klient`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `FKmitarbeiter` FOREIGN KEY (`mitarbeiter`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `FKrechnung` FOREIGN KEY (`rechnung`) REFERENCES `rechnung` (`rechnungsnummer`);

--
-- Constraints der Tabelle `dokument`
--
ALTER TABLE `dokument`
  ADD CONSTRAINT `FKbesitzerEvent` FOREIGN KEY (`besitzerIdAktivitaet`) REFERENCES `aktivitaetsprotokoll` (`id`),
  ADD CONSTRAINT `FKbesitzerPerson` FOREIGN KEY (`besitzerIdPerson`) REFERENCES `person` (`id`);

--
-- Constraints der Tabelle `person`
--
ALTER TABLE `person`
  ADD CONSTRAINT `FKesv` FOREIGN KEY (`esv`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `FKnotfallkontakt1` FOREIGN KEY (`notfallkontakt1`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `FKnotfallkontakt2` FOREIGN KEY (`notfallkontakt2`) REFERENCES `person` (`id`);

--
-- Constraints der Tabelle `rechnung`
--
ALTER TABLE `rechnung`
  ADD CONSTRAINT `FKklient_rechnung` FOREIGN KEY (`klient`) REFERENCES `person` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
