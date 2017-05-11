-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: petshop
-- ------------------------------------------------------
-- Server version	5.5.51-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `visitas`
--

DROP TABLE IF EXISTS `visitas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visitas` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_Pet` int(10) NOT NULL,
  `id_Veterinario` int(10) NOT NULL,
  `dataVisita` date DEFAULT NULL,
  `anaminese` text,
  `medicamentos` text,
  PRIMARY KEY (`id`),
  KEY `id_Pet` (`id_Pet`),
  KEY `id_Veterinario` (`id_Veterinario`),
  CONSTRAINT `visitas_ibfk_1` FOREIGN KEY (`id_Pet`) REFERENCES `pets` (`id`),
  CONSTRAINT `visitas_ibfk_2` FOREIGN KEY (`id_Veterinario`) REFERENCES `veterinarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visitas`
--

LOCK TABLES `visitas` WRITE;
/*!40000 ALTER TABLE `visitas` DISABLE KEYS */;
INSERT INTO `visitas` VALUES (1,5,2,'2017-05-10','anaminese','dipirona 500ml e raio x'),(4,5,2,'2017-05-11','animal com febre','dipirona 500mg 4 vezes ao dia'),(5,5,3,'2017-05-11','teste	','teste'),(6,4,3,'2017-05-11','sdfsdfsdf','sfsdfsdfsd');
/*!40000 ALTER TABLE `visitas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-11 20:56:42
