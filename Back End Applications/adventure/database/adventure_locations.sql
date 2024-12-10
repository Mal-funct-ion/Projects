-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: adventure
-- ------------------------------------------------------
-- Server version	8.0.36

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

--
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locations` (
  `locationid` varchar(45) NOT NULL,
  `location_name` varchar(45) DEFAULT NULL,
  `item` varchar(45) DEFAULT NULL,
  `north` varchar(45) DEFAULT NULL,
  `south` varchar(45) DEFAULT NULL,
  `east` varchar(45) DEFAULT NULL,
  `west` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`locationid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locations`
--

LOCK TABLES `locations` WRITE;
/*!40000 ALTER TABLE `locations` DISABLE KEYS */;
INSERT INTO `locations` VALUES ('dd7f111b-1dcc-11ef-81f8-644ed79c0d15','Zero','key3',NULL,'dd7fd4bb-1dcc-11ef-81f8-644ed79c0d15','dd7fcfda-1dcc-11ef-81f8-644ed79c0d15',NULL),('dd7fcfda-1dcc-11ef-81f8-644ed79c0d15','One',NULL,NULL,'dd7fd57d-1dcc-11ef-81f8-644ed79c0d15','dd7fd39d-1dcc-11ef-81f8-644ed79c0d15','dd7fcfda-1dcc-11ef-81f8-644ed79c0d15'),('dd7fd39d-1dcc-11ef-81f8-644ed79c0d15','Two','chest2',NULL,'dd7fd64d-1dcc-11ef-81f8-644ed79c0d15',NULL,'dd7fcfda-1dcc-11ef-81f8-644ed79c0d15'),('dd7fd4bb-1dcc-11ef-81f8-644ed79c0d15','Three','key1','dd7f111b-1dcc-11ef-81f8-644ed79c0d15','dd7fd717-1dcc-11ef-81f8-644ed79c0d15','dd7fd57d-1dcc-11ef-81f8-644ed79c0d15',NULL),('dd7fd57d-1dcc-11ef-81f8-644ed79c0d15','Four','chest1','dd7fcfda-1dcc-11ef-81f8-644ed79c0d15','dd7fd7d2-1dcc-11ef-81f8-644ed79c0d15','dd7fd64d-1dcc-11ef-81f8-644ed79c0d15','dd7fd4bb-1dcc-11ef-81f8-644ed79c0d15'),('dd7fd64d-1dcc-11ef-81f8-644ed79c0d15','Five',NULL,'dd7fd39d-1dcc-11ef-81f8-644ed79c0d15','dd7fd851-1dcc-11ef-81f8-644ed79c0d15',NULL,'dd7fd57d-1dcc-11ef-81f8-644ed79c0d15'),('dd7fd717-1dcc-11ef-81f8-644ed79c0d15','Six',NULL,'dd7fd4bb-1dcc-11ef-81f8-644ed79c0d15',NULL,'dd7fd7d2-1dcc-11ef-81f8-644ed79c0d15',NULL),('dd7fd7d2-1dcc-11ef-81f8-644ed79c0d15','Seven','key2','dd7fd57d-1dcc-11ef-81f8-644ed79c0d15',NULL,'dd7fd851-1dcc-11ef-81f8-644ed79c0d15','dd7fd717-1dcc-11ef-81f8-644ed79c0d15'),('dd7fd851-1dcc-11ef-81f8-644ed79c0d15','Eight','chest3','dd7fd64d-1dcc-11ef-81f8-644ed79c0d15',NULL,NULL,'dd7fd7d2-1dcc-11ef-81f8-644ed79c0d15');
/*!40000 ALTER TABLE `locations` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-29 19:21:12
