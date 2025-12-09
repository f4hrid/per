-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: bd_gea
-- ------------------------------------------------------
-- Server version	9.5.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '7d072524-ce1f-11f0-a9fd-6cf6daac2ca3:1-199';

--
-- Table structure for table `participaciones`
--

DROP TABLE IF EXISTS `participaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `participaciones` (
  `id_participacion` int NOT NULL,
  `fecha_asistencia` date DEFAULT NULL,
  `hora_asistencia` time DEFAULT NULL,
  `calificacion_aprobacion` decimal(3,2) NOT NULL,
  `id_estudiante` int NOT NULL,
  `id_docente` int NOT NULL,
  `id_oferta` int NOT NULL,
  PRIMARY KEY (`id_participacion`),
  KEY `id_estudiante` (`id_estudiante`),
  KEY `id_docente` (`id_docente`),
  KEY `id_oferta` (`id_oferta`),
  CONSTRAINT `participaciones_ibfk_1` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiante` (`id_estudiante`),
  CONSTRAINT `participaciones_ibfk_2` FOREIGN KEY (`id_docente`) REFERENCES `docente` (`id_docente`),
  CONSTRAINT `participaciones_ibfk_3` FOREIGN KEY (`id_oferta`) REFERENCES `ofertas` (`id_oferta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participaciones`
--

LOCK TABLES `participaciones` WRITE;
/*!40000 ALTER TABLE `participaciones` DISABLE KEYS */;
INSERT INTO `participaciones` VALUES (5001,'2025-01-20','09:00:00',3.50,1,1,1001),(5002,'2025-02-05','14:30:00',4.00,2,2,1002),(5003,'2025-03-10','10:15:00',2.25,3,1,1003),(5004,'2025-04-12','11:00:00',1.00,1,2,1004);
/*!40000 ALTER TABLE `participaciones` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-09 10:04:51
