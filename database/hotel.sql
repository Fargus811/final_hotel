CREATE DATABASE  IF NOT EXISTS `hotel` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hotel`;
-- MySQL dump 10.13  Distrib 8.0.21, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: hotel
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `account_statuses`
--

DROP TABLE IF EXISTS `account_statuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_statuses` (
  `id` int NOT NULL,
  `account_status_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_statuses`
--

LOCK TABLES `account_statuses` WRITE;
/*!40000 ALTER TABLE `account_statuses` DISABLE KEYS */;
INSERT INTO `account_statuses` VALUES (0,'Активный'),(1,'Заблокированный'),(2,'Удалённый');
/*!40000 ALTER TABLE `account_statuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking_statuses`
--

DROP TABLE IF EXISTS `booking_statuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking_statuses` (
  `id` int NOT NULL,
  `booking_status_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_statuses`
--

LOCK TABLES `booking_statuses` WRITE;
/*!40000 ALTER TABLE `booking_statuses` DISABLE KEYS */;
INSERT INTO `booking_statuses` VALUES (0,'Обрабатывается'),(1,'Ждёт оплаты'),(2,'Отказ'),(3,'Оплачен');
/*!40000 ALTER TABLE `booking_statuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookings` (
  `id` int NOT NULL AUTO_INCREMENT,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `cost` decimal(10,2) NOT NULL,
  `max_persons` int DEFAULT NULL,
  `number_of_beds` int DEFAULT NULL,
  `grade_id` int DEFAULT NULL,
  `has_Wifi` tinyint(1) DEFAULT NULL,
  `has_TV` tinyint(1) DEFAULT NULL,
  `has_bathroom` tinyint(1) DEFAULT NULL,
  `number_of_rooms` int DEFAULT NULL,
  `user_id` int NOT NULL,
  `room_id` int DEFAULT NULL,
  `booking_status_id` int NOT NULL DEFAULT '0',
  `creation_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_idx` (`room_id`),
  KEY `grade_id_idx` (`grade_id`),
  KEY `status_id_idx` (`booking_status_id`),
  CONSTRAINT `grade_id` FOREIGN KEY (`grade_id`) REFERENCES `grades` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `id` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `status_id` FOREIGN KEY (`booking_status_id`) REFERENCES `booking_statuses` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (2,'2020-11-01','2020-11-29',0.00,2,1,1,0,0,0,1,1,NULL,2,NULL),(3,'2020-11-22','2020-11-26',0.00,2,1,2,1,1,0,1,1,NULL,2,NULL),(4,'2020-11-27','2020-11-29',1800.00,2,1,2,0,0,0,1,1,7,3,NULL),(5,'2020-11-26','2020-12-05',0.00,2,1,3,1,1,0,1,1,NULL,2,NULL),(6,'2020-11-30','2020-12-06',7200.00,4,2,3,1,1,0,2,11,11,3,NULL),(7,'2020-11-30','2020-12-06',0.00,2,1,1,0,0,0,1,11,NULL,0,NULL),(8,'2020-12-28','2021-01-04',8400.00,4,2,3,1,1,1,2,11,11,3,NULL);
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grades`
--

DROP TABLE IF EXISTS `grades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grades` (
  `id` int NOT NULL,
  `grade_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grades`
--

LOCK TABLES `grades` WRITE;
/*!40000 ALTER TABLE `grades` DISABLE KEYS */;
INSERT INTO `grades` VALUES (0,'Эконом'),(1,'Стандарт'),(2,'Люкс'),(3,'Премьер'),(4,'Премиум');
/*!40000 ALTER TABLE `grades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int NOT NULL,
  `role_name` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name_UNIQUE` (`role_name`),
  UNIQUE KEY `role_id_UNIQUE` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Администратор'),(0,'Клиент');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `number_of_rooms` int NOT NULL,
  `max_persons` int NOT NULL,
  `grade_id` int NOT NULL,
  `cost` decimal(10,2) NOT NULL,
  `has_Wifi` tinyint(1) NOT NULL,
  `has_TV` tinyint(1) NOT NULL,
  `has_bathroom` tinyint(1) DEFAULT NULL,
  `number_of_beds` int NOT NULL,
  `room_description` varchar(300) NOT NULL,
  `photo_path` varchar(300) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `grade_id_idx` (`grade_id`),
  CONSTRAINT `grade` FOREIGN KEY (`grade_id`) REFERENCES `grades` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (2,'Комфорт',2,4,2,599.99,1,0,1,4,'Комфорт описание','../resources/images/сomfort.jpg'),(3,'Семейный',2,6,2,699.99,1,1,1,3,'Семейный отдых','../resources/images/family-room.jpeg'),(4,'Эконом',1,2,1,399.99,0,1,0,1,'Лучше не засиживаться в номере','../resources/images/deluxe.jpg'),(6,'Люкс для влюблённых',1,2,2,688.00,1,1,0,1,'Уютное тёплое гнёздышко','../resources/images/hotellux1.jpg'),(7,'Прекрасный люкс',2,4,2,900.00,1,1,0,2,'Баланс цены и качества','../resources/images/hotellux2.jpg'),(8,'Премьер апартаменты',2,4,4,1500.00,1,1,1,3,'Для небедствующих','../resources/images/hotelpremier1.jpg'),(9,'Супер Премьер',3,8,4,2000.00,1,1,1,3,'Это вам не шуточки','../resources/images/hotelpremier2.jpg'),(10,'Премиум А',1,2,3,1000.00,1,1,0,2,'Для двоих и ничего более','../resources/images/hotelpremium1.jpeg'),(11,'Премиум Б',2,4,3,1200.00,1,1,1,2,'Всё что пожелаешь','../resources/images/hotelpremium2.jpeg'),(12,'Стандарт А',2,4,1,400.00,1,1,0,2,'Ничего лишнего','../resources/images/hotelstandart1.jpeg');
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(129) NOT NULL,
  `password` varchar(200) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `balance` decimal(10,2) DEFAULT '0.00',
  `role_id` int NOT NULL,
  `account_status_id` int DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `id_idx` (`account_status_id`),
  CONSTRAINT `status_account_id` FOREIGN KEY (`account_status_id`) REFERENCES `account_statuses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'ggas@gmail.com','$2a$12$Lh4MNiFWwu46mc175XkTaO/N0NLDTaluBvyOz1/zbBF9B8RAFhRpG','Daniil','Sergeev',3200.00,0,0),(2,'admin@gmail.com','$2a$12$8BDy8AwxK3mLAlNGjRHWReGLSi3Equ05pgmB1sTEEpsTzPXd4AyuO','Luck','Rockhold',0.00,1,0),(11,'tiktonik-97@mail.ru','$2a$12$/R8n7hH7hHEVCaNdtx3flugbiUGDh4YzUEbHBAUBqNKGh4hXhT..i','Fara','Victory',1900.00,0,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-01 19:09:08
