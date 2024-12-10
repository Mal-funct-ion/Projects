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
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `players` (
  `playerid` varchar(64) NOT NULL,
  `position` int DEFAULT NULL,
  `zero` tinyint DEFAULT NULL,
  `one` tinyint DEFAULT NULL,
  `two` tinyint DEFAULT NULL,
  `three` tinyint DEFAULT NULL,
  `four` tinyint DEFAULT NULL,
  `five` tinyint DEFAULT NULL,
  `six` tinyint DEFAULT NULL,
  `seven` tinyint DEFAULT NULL,
  `eight` tinyint DEFAULT NULL,
  `all_pos` tinyint DEFAULT NULL,
  PRIMARY KEY (`playerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `players`
--

LOCK TABLES `players` WRITE;
/*!40000 ALTER TABLE `players` DISABLE KEYS */;
INSERT INTO `players` VALUES ('08b9faa8-9ee5-47c9-84ab-48753bb1e28d',5,0,0,0,0,1,0,0,0,0,0),('0ebe15bb-4e6f-43da-8c06-b7fee7696e83',5,0,0,0,0,1,0,0,0,0,0),('1f5cc4dd-1287-4bc1-8b1c-d5e0b88d0c25',5,0,0,0,0,1,0,0,0,0,0),('86253168-6b05-44c7-b529-a834b3c301be',5,0,0,0,0,1,0,0,0,0,0),('a9d3ba49-d44c-4643-b6d2-eb9e9d2df8ce',5,0,0,0,0,0,1,0,0,0,0),('bd76ac6f-dd45-4fb5-8855-6a6c0cb8dfb3',4,0,0,0,0,1,0,0,0,0,0),('d299e504-e9d2-48a1-bc3c-a6f759e0a366',5,0,0,0,0,0,1,0,0,0,0),('d7896a84-815e-4b37-879b-272145030b37',5,0,0,0,0,1,0,0,0,0,0),('db7ed8be-3c09-412d-a7d6-a8464d519596',2,1,1,1,1,1,1,1,1,1,1),('df448099-269c-4e07-ae8d-a73641a6a9de',5,0,0,0,0,1,1,0,0,0,0);
/*!40000 ALTER TABLE `players` ENABLE KEYS */;
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
