-- MySQL dump 10.13  Distrib 5.7.24, for osx11.1 (x86_64)
--
-- Host: localhost    Database: pmt_db
-- ------------------------------------------------------
-- Server version	9.0.1

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
-- Table structure for table `invitation`
--

DROP TABLE IF EXISTS `invitation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invitation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `project_id` bigint DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invitation`
--

LOCK TABLES `invitation` WRITE;
/*!40000 ALTER TABLE `invitation` DISABLE KEYS */;
INSERT INTO `invitation` VALUES (109,'stevetadesse0@gmail.com',459,'membre'),(110,'jayson@outlook.fr',459,'observer'),(111,'member@example.com',1,'Member'),(112,'member@example.com',1,'Member'),(113,'member@example.com',1,'Member'),(114,'member@example.com',1,'Member'),(115,'member@example.com',1,'Member'),(116,'member@example.com',1,'Member'),(117,'member@example.com',1,'Member'),(118,'member@example.com',1,'Member'),(119,'member@example.com',1,'Member'),(120,'member@example.com',1,'Member'),(121,'member@example.com',1,'Member'),(122,'member@example.com',1,'Member'),(123,'member@example.com',1,'Member'),(124,'member@example.com',1,'Member'),(125,'member@example.com',1,'Member'),(126,'member@example.com',1,'Member'),(127,'member@example.com',1,'Member'),(128,'member@example.com',1,'Member'),(129,'member@example.com',1,'Member'),(130,'member@example.com',1,'Member'),(131,'member@example.com',1,'Member'),(132,'member@example.com',1,'Member'),(133,'member@example.com',1,'Member'),(134,'member@example.com',1,'Member'),(135,'member@example.com',1,'Member'),(136,'member@example.com',1,'Member'),(137,'member@example.com',1,'Member'),(138,'member@example.com',1,'Member'),(139,'member@example.com',1,'Member'),(140,'member@example.com',1,'Member'),(141,'member@example.com',1,'Member'),(142,'member@example.com',1,'Member'),(143,'member@example.com',1,'Member'),(144,'member@example.com',1,'Member'),(145,'member@example.com',1,'Member');
/*!40000 ALTER TABLE `invitation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `admin_id` bigint DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `creator_user_id` bigint DEFAULT NULL,
  `creator_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_admin` (`admin_id`),
  CONSTRAINT `fk_admin` FOREIGN KEY (`admin_id`) REFERENCES `users` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=726 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (459,'Projet 1','ceci est un test','2024-11-30',NULL,'2024-11-04 11:05:20',305,'steveduchateau@outlook.fr'),(460,'Nouveau Projet',NULL,NULL,NULL,'2024-11-05 02:25:44',NULL,NULL),(461,'Test Project',NULL,NULL,NULL,'2024-11-05 02:25:45',308,NULL),(462,'Test Project','A project for testing tasks.','2024-11-05',NULL,'2024-11-05 02:25:45',310,'admin_1730773545758@example.com'),(467,'Nouveau Projet',NULL,NULL,NULL,'2024-11-05 11:51:14',NULL,NULL),(468,'Test Project',NULL,NULL,NULL,'2024-11-05 11:51:15',313,NULL),(469,'Test Project','A project for testing tasks.','2024-11-05',NULL,'2024-11-05 11:51:16',315,'admin_1730807476027@example.com'),(474,'Nouveau Projet',NULL,NULL,NULL,'2024-11-05 12:02:47',NULL,NULL),(475,'Test Project',NULL,NULL,NULL,'2024-11-05 12:02:48',318,NULL),(476,'Test Project','A project for testing tasks.','2024-11-05',NULL,'2024-11-05 12:02:49',320,'admin_1730808169043@example.com'),(487,'Nouveau Projet',NULL,NULL,NULL,'2024-11-05 12:12:33',NULL,NULL),(488,'Test Project',NULL,NULL,NULL,'2024-11-05 12:12:35',323,NULL),(489,'Test Project','A project for testing tasks.','2024-11-05',NULL,'2024-11-05 12:12:35',325,'admin_1730808755586@example.com'),(499,'Nouveau Projet',NULL,NULL,NULL,'2024-11-05 12:20:54',NULL,NULL),(500,'Test Project',NULL,NULL,NULL,'2024-11-05 12:20:55',328,NULL),(501,'Test Project','A project for testing tasks.','2024-11-05',NULL,'2024-11-05 12:20:55',330,'admin_1730809255937@example.com'),(511,'Nouveau Projet',NULL,NULL,NULL,'2024-11-05 12:48:46',NULL,NULL),(512,'Test Project',NULL,NULL,NULL,'2024-11-05 12:48:48',333,NULL),(513,'Test Project','A project for testing tasks.','2024-11-05',NULL,'2024-11-05 12:48:48',335,'admin_1730810928208@example.com'),(523,'Nouveau Projet',NULL,NULL,NULL,'2024-11-05 12:53:47',NULL,NULL),(524,'Test Project',NULL,NULL,NULL,'2024-11-05 12:53:49',338,NULL),(525,'Test Project','A project for testing tasks.','2024-11-05',NULL,'2024-11-05 12:53:49',340,'admin_1730811229060@example.com'),(530,'Nouveau Projet',NULL,NULL,NULL,'2024-11-05 13:00:15',NULL,NULL),(531,'Test Project',NULL,NULL,NULL,'2024-11-05 13:00:16',343,NULL),(532,'Test Project','A project for testing tasks.','2024-11-05',NULL,'2024-11-05 13:00:16',345,'admin_1730811616647@example.com'),(537,'Nouveau Projet',NULL,NULL,NULL,'2024-11-05 13:06:16',NULL,NULL),(538,'Test Project',NULL,NULL,NULL,'2024-11-05 13:06:18',348,NULL),(539,'Test Project','A project for testing tasks.','2024-11-05',NULL,'2024-11-05 13:06:18',350,'admin_1730811978383@example.com'),(544,'Nouveau Projet',NULL,NULL,NULL,'2024-11-05 13:17:10',NULL,NULL),(545,'Test Project',NULL,NULL,NULL,'2024-11-05 13:17:11',353,NULL),(546,'Test Project','A project for testing tasks.','2024-11-05',NULL,'2024-11-05 13:17:11',355,'admin_1730812631928@example.com'),(551,'Nouveau Projet',NULL,NULL,NULL,'2024-11-05 13:20:39',NULL,NULL),(552,'Test Project',NULL,NULL,NULL,'2024-11-05 13:20:41',358,NULL),(553,'Test Project','A project for testing tasks.','2024-11-05',NULL,'2024-11-05 13:20:41',360,'admin_1730812841173@example.com'),(558,'Nouveau Projet',NULL,NULL,NULL,'2024-11-05 14:56:15',NULL,NULL),(559,'Test Project',NULL,NULL,NULL,'2024-11-05 14:56:16',363,NULL),(560,'Test Project','A project for testing tasks.','2024-11-05',NULL,'2024-11-05 14:56:16',365,'admin_1730818576857@example.com'),(565,'Nouveau Projet',NULL,NULL,NULL,'2024-11-06 12:39:58',NULL,NULL),(566,'Test Project',NULL,NULL,NULL,'2024-11-06 12:39:59',368,NULL),(567,'Test Project','A project for testing tasks.','2024-11-06',NULL,'2024-11-06 12:39:59',370,'admin_1730896799711@example.com'),(572,'Nouveau Projet',NULL,NULL,NULL,'2024-11-06 12:43:52',NULL,NULL),(573,'Test Project',NULL,NULL,NULL,'2024-11-06 12:43:53',373,NULL),(574,'Test Project','A project for testing tasks.','2024-11-06',NULL,'2024-11-06 12:43:53',375,'admin_1730897033744@example.com'),(579,'Nouveau Projet',NULL,NULL,NULL,'2024-11-06 13:49:58',NULL,NULL),(580,'Test Project',NULL,NULL,NULL,'2024-11-06 13:50:00',378,NULL),(581,'Test Project','A project for testing tasks.','2024-11-06',NULL,'2024-11-06 13:50:00',380,'admin_1730901000256@example.com'),(586,'Nouveau Projet',NULL,NULL,NULL,'2024-11-06 13:51:13',NULL,NULL),(587,'Test Project',NULL,NULL,NULL,'2024-11-06 13:51:15',383,NULL),(588,'Test Project','A project for testing tasks.','2024-11-06',NULL,'2024-11-06 13:51:15',385,'admin_1730901075728@example.com'),(593,'Nouveau Projet',NULL,NULL,NULL,'2024-11-06 13:54:13',NULL,NULL),(594,'Test Project',NULL,NULL,NULL,'2024-11-06 13:54:15',388,NULL),(595,'Test Project','A project for testing tasks.','2024-11-06',NULL,'2024-11-06 13:54:15',390,'admin_1730901255748@example.com'),(600,'Nouveau Projet',NULL,NULL,NULL,'2024-11-07 13:23:09',NULL,NULL),(601,'Test Project',NULL,NULL,NULL,'2024-11-07 13:23:10',393,NULL),(602,'Test Project','A project for testing tasks.','2024-11-07',NULL,'2024-11-07 13:23:10',395,'admin_1730985790886@example.com'),(607,'Nouveau Projet',NULL,NULL,NULL,'2024-11-07 13:29:37',NULL,NULL),(608,'Test Project',NULL,NULL,NULL,'2024-11-07 13:29:38',398,NULL),(609,'Test Project','A project for testing tasks.','2024-11-07',NULL,'2024-11-07 13:29:38',400,'admin_1730986178445@example.com'),(614,'Nouveau Projet',NULL,NULL,NULL,'2024-11-07 13:32:26',NULL,NULL),(615,'Test Project',NULL,NULL,NULL,'2024-11-07 13:32:27',403,NULL),(616,'Test Project','A project for testing tasks.','2024-11-07',NULL,'2024-11-07 13:32:27',405,'admin_1730986347385@example.com'),(621,'Nouveau Projet',NULL,NULL,NULL,'2024-11-07 13:36:54',NULL,NULL),(622,'Test Project',NULL,NULL,NULL,'2024-11-07 13:36:56',408,NULL),(623,'Test Project','A project for testing tasks.','2024-11-07',NULL,'2024-11-07 13:36:56',410,'admin_1730986616836@example.com'),(628,'Nouveau Projet',NULL,NULL,NULL,'2024-11-07 13:57:57',NULL,NULL),(629,'Test Project',NULL,NULL,NULL,'2024-11-07 13:58:00',413,NULL),(630,'Test Project','A project for testing tasks.','2024-11-07',NULL,'2024-11-07 13:58:00',415,'admin_1730987880217@example.com'),(635,'Nouveau Projet',NULL,NULL,NULL,'2024-11-07 14:18:30',NULL,NULL),(636,'Test Project',NULL,NULL,NULL,'2024-11-07 14:18:33',418,NULL),(637,'Test Project','A project for testing tasks.','2024-11-07',NULL,'2024-11-07 14:18:33',420,'admin_1730989113419@example.com'),(642,'Nouveau Projet',NULL,NULL,NULL,'2024-11-09 14:47:19',NULL,NULL),(643,'Test Project',NULL,NULL,NULL,'2024-11-09 14:47:22',423,NULL),(644,'Test Project','A project for testing tasks.','2024-11-09',NULL,'2024-11-09 14:47:22',425,'admin_1731163642691@example.com'),(649,'Nouveau Projet',NULL,NULL,NULL,'2024-11-09 14:48:57',NULL,NULL),(650,'Test Project',NULL,NULL,NULL,'2024-11-09 14:48:58',428,NULL),(651,'Test Project','A project for testing tasks.','2024-11-09',NULL,'2024-11-09 14:48:59',430,'admin_1731163739030@example.com'),(656,'Nouveau Projet',NULL,NULL,NULL,'2024-11-09 14:56:56',NULL,NULL),(657,'Test Project',NULL,NULL,NULL,'2024-11-09 14:56:58',433,NULL),(658,'Test Project','A project for testing tasks.','2024-11-09',NULL,'2024-11-09 14:56:58',435,'admin_1731164218920@example.com'),(663,'Nouveau Projet',NULL,NULL,NULL,'2024-11-09 15:17:40',NULL,NULL),(664,'Test Project',NULL,NULL,NULL,'2024-11-09 15:17:44',438,NULL),(665,'Test Project','A project for testing tasks.','2024-11-09',NULL,'2024-11-09 15:17:44',440,'admin_1731165464213@example.com'),(670,'Nouveau Projet',NULL,NULL,NULL,'2024-11-09 15:22:39',NULL,NULL),(671,'Test Project',NULL,NULL,NULL,'2024-11-09 15:22:42',443,NULL),(672,'Test Project','A project for testing tasks.','2024-11-09',NULL,'2024-11-09 15:22:42',445,'admin_1731165762234@example.com'),(677,'Nouveau Projet',NULL,NULL,NULL,'2024-11-09 15:37:25',NULL,NULL),(678,'Test Project',NULL,NULL,NULL,'2024-11-09 15:37:26',448,NULL),(679,'Test Project','A project for testing tasks.','2024-11-09',NULL,'2024-11-09 15:37:26',450,'admin_1731166646920@example.com'),(684,'Nouveau Projet',NULL,NULL,NULL,'2024-11-09 15:41:29',NULL,NULL),(685,'Test Project',NULL,NULL,NULL,'2024-11-09 15:41:31',453,NULL),(686,'Test Project','A project for testing tasks.','2024-11-09',NULL,'2024-11-09 15:41:31',455,'admin_1731166891398@example.com'),(691,'Nouveau Projet',NULL,NULL,NULL,'2024-11-09 16:00:13',NULL,NULL),(692,'Test Project',NULL,NULL,NULL,'2024-11-09 16:00:16',458,NULL),(693,'Test Project','A project for testing tasks.','2024-11-09',NULL,'2024-11-09 16:00:16',460,'admin_1731168016589@example.com'),(698,'Nouveau Projet',NULL,NULL,NULL,'2024-11-09 16:06:56',NULL,NULL),(699,'Test Project',NULL,NULL,NULL,'2024-11-09 16:06:57',463,NULL),(700,'Test Project','A project for testing tasks.','2024-11-09',NULL,'2024-11-09 16:06:58',465,'admin_1731168418049@example.com'),(705,'Nouveau Projet',NULL,NULL,NULL,'2024-11-09 16:33:21',NULL,NULL),(706,'Test Project',NULL,NULL,NULL,'2024-11-09 16:33:23',468,NULL),(707,'Test Project','A project for testing tasks.','2024-11-09',NULL,'2024-11-09 16:33:23',470,'admin_1731170003277@example.com'),(712,'Nouveau Projet',NULL,NULL,NULL,'2024-11-09 20:22:17',NULL,NULL),(713,'Test Project',NULL,NULL,NULL,'2024-11-09 20:22:20',473,NULL),(714,'Test Project','A project for testing tasks.','2024-11-09',NULL,'2024-11-09 20:22:20',475,'admin_1731183740487@example.com'),(719,'Nouveau Projet',NULL,NULL,NULL,'2024-11-09 22:20:44',NULL,NULL),(720,'Test Project',NULL,NULL,479,'2024-11-09 22:20:46',479,NULL),(721,'Test Project','A project for testing tasks.','2024-11-09',481,'2024-11-09 22:20:47',481,'admin_1731190847036@example.com');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_member`
--

DROP TABLE IF EXISTS `project_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_member` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `is_admin` bit(1) DEFAULT NULL,
  `project_id` bigint DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK103dwxad12nbaxtmnwus4eft2` (`project_id`),
  CONSTRAINT `FK103dwxad12nbaxtmnwus4eft2` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=616 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_member`
--

LOCK TABLES `project_member` WRITE;
/*!40000 ALTER TABLE `project_member` DISABLE KEYS */;
INSERT INTO `project_member` VALUES (380,NULL,_binary '\0',500,'admin',328),(381,'member@example.com',_binary '\0',459,'Observer',2),(382,NULL,_binary '\0',512,'admin',333),(383,'member@example.com',_binary '\0',459,'Observer',2),(384,NULL,_binary '\0',524,'admin',338),(385,'member@example.com',_binary '\0',459,'Observer',2),(392,NULL,_binary '\0',531,'admin',343),(393,'member@example.com',_binary '\0',459,'Observer',2),(400,NULL,_binary '\0',538,'admin',348),(401,'member@example.com',_binary '\0',459,'Observer',2),(408,NULL,_binary '\0',545,'admin',353),(409,'member@example.com',_binary '\0',459,'Observer',2),(416,NULL,_binary '\0',552,'admin',358),(417,'member@example.com',_binary '\0',459,'Observer',2),(424,NULL,_binary '\0',559,'admin',363),(425,'member@example.com',_binary '\0',459,'Observer',2),(432,NULL,_binary '\0',566,'admin',368),(433,'member@example.com',_binary '\0',459,'Observer',2),(440,NULL,_binary '\0',573,'admin',373),(441,'member@example.com',_binary '\0',459,'Observer',2),(448,NULL,_binary '\0',580,'admin',378),(449,'member@example.com',_binary '\0',459,'Observer',2),(456,NULL,_binary '\0',587,'admin',383),(457,'member@example.com',_binary '\0',459,'Observer',2),(464,NULL,_binary '\0',594,'admin',388),(465,'member@example.com',_binary '\0',459,'Observer',2),(472,NULL,_binary '\0',601,'admin',393),(473,'member@example.com',_binary '\0',459,'Observer',2),(480,NULL,_binary '\0',608,'admin',398),(481,'member@example.com',_binary '\0',459,'Observer',2),(488,NULL,_binary '\0',615,'admin',403),(489,'member@example.com',_binary '\0',459,'Observer',2),(496,NULL,_binary '\0',622,'admin',408),(497,'member@example.com',_binary '\0',459,'Observer',2),(504,NULL,_binary '\0',629,'admin',413),(505,'member@example.com',_binary '\0',459,'Observer',2),(512,NULL,_binary '\0',636,'admin',418),(513,'member@example.com',_binary '\0',459,'Observer',2),(520,NULL,_binary '\0',643,'admin',423),(521,'member@example.com',_binary '\0',459,'Observer',2),(528,NULL,_binary '\0',650,'admin',428),(529,'member@example.com',_binary '\0',459,'Observer',2),(536,NULL,_binary '\0',657,'admin',433),(537,'member@example.com',_binary '\0',459,'Observer',2),(544,NULL,_binary '\0',664,'admin',438),(545,'member@example.com',_binary '\0',459,'Observer',2),(552,NULL,_binary '\0',671,'admin',443),(553,'member@example.com',_binary '\0',459,'Observer',2),(560,NULL,_binary '\0',678,'admin',448),(561,'member@example.com',_binary '\0',459,'Observer',2),(568,NULL,_binary '\0',685,'admin',453),(569,'member@example.com',_binary '\0',459,'Observer',2),(576,NULL,_binary '\0',692,'admin',458),(577,'member@example.com',_binary '\0',459,'Observer',2),(584,NULL,_binary '\0',699,'admin',463),(585,'member@example.com',_binary '\0',459,'Observer',2),(592,NULL,_binary '\0',706,'admin',468),(593,'member@example.com',_binary '\0',459,'Observer',2),(600,NULL,_binary '\0',713,'admin',473),(601,'member@example.com',_binary '\0',459,'Observer',2),(608,NULL,_binary '\0',720,'admin',479),(609,'member@example.com',_binary '\0',459,'Observer',2);
/*!40000 ALTER TABLE `project_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projects` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `admin_id` bigint DEFAULT NULL,
  `creator_email` varchar(255) DEFAULT NULL,
  `creator_user_id` bigint DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK8sewwnpamngi6b1dwaa88askk` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=767 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (766,'Admin'),(765,'Member');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_history`
--

DROP TABLE IF EXISTS `task_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_history` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `task_id` bigint NOT NULL,
  `action` varchar(255) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modification_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `old_description` varchar(255) DEFAULT NULL,
  `new_description` varchar(255) DEFAULT NULL,
  `old_status` varchar(255) DEFAULT NULL,
  `new_status` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK5tdd1nleareui8d4pft0w8snx` (`task_id`,`action`,`modified_by`,`modified_at`),
  UNIQUE KEY `UKmc94vwu05l50ijowkvujwqeds` (`task_id`,`action`,`modified_at`),
  CONSTRAINT `fk_task_id` FOREIGN KEY (`task_id`) REFERENCES `tasks` (`id`),
  CONSTRAINT `task_history_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `tasks` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=678 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_history`
--

LOCK TABLES `task_history` WRITE;
/*!40000 ALTER TABLE `task_history` DISABLE KEYS */;
INSERT INTO `task_history` VALUES (428,390,'Créée','steveduchateau@outlook.fr','2024-11-04 11:06:17','2024-11-04 11:06:17',NULL,'ceci est un test',NULL,NULL,NULL,NULL,NULL),(429,391,'Créée','steveduchateau@outlook.fr','2024-11-04 11:24:29','2024-11-04 11:24:29',NULL,'ceci est un test',NULL,NULL,NULL,NULL,NULL),(430,390,'Modifiée','steveduchateau@outlook.fr','2024-11-04 11:28:42','2024-11-04 11:28:42','ceci est un test','ceci est un test','Non démarrée','En cours',NULL,NULL,NULL),(431,391,'Modifiée','steveduchateau@outlook.fr','2024-11-04 16:01:32','2024-11-04 16:01:32','ceci est un test','ceci est un test','Non démarrée','En cours',NULL,NULL,NULL),(432,391,'Modifiée','steveduchateau@outlook.fr','2024-11-05 00:30:21','2024-11-05 00:30:20','ceci est un test','ceci est un test','En cours','Terminée',NULL,NULL,NULL),(433,392,'Créée','user@example.com','2024-11-05 02:25:44','2024-11-05 02:25:44',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(434,394,'CREATED','modifier@example.com','2024-11-05 02:25:46','2024-11-05 02:25:45',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-05 03:25:45.791675',NULL,NULL),(440,399,'Créée','user@example.com','2024-11-05 11:51:14','2024-11-05 11:51:14',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(441,401,'CREATED','modifier@example.com','2024-11-05 11:51:16','2024-11-05 11:51:16',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-05 12:51:16.056516',NULL,NULL),(447,406,'Créée','user@example.com','2024-11-05 12:02:47','2024-11-05 12:02:47',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(448,408,'CREATED','modifier@example.com','2024-11-05 12:02:49','2024-11-05 12:02:49',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-05 13:02:49.074281',NULL,NULL),(454,413,'Créée','user@example.com','2024-11-05 12:12:34','2024-11-05 12:12:33',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(455,415,'CREATED','modifier@example.com','2024-11-05 12:12:36','2024-11-05 12:12:35',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-05 13:12:35.615860',NULL,NULL),(461,420,'Créée','user@example.com','2024-11-05 12:20:54','2024-11-05 12:20:54',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(462,422,'CREATED','modifier@example.com','2024-11-05 12:20:56','2024-11-05 12:20:55',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-05 13:20:55.979378',NULL,NULL),(468,427,'Créée','user@example.com','2024-11-05 12:48:47','2024-11-05 12:48:46',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(469,429,'CREATED','modifier@example.com','2024-11-05 12:48:48','2024-11-05 12:48:48',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-05 13:48:48.240862',NULL,NULL),(475,434,'Créée','user@example.com','2024-11-05 12:53:47','2024-11-05 12:53:47',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(476,436,'CREATED','modifier@example.com','2024-11-05 12:53:49','2024-11-05 12:53:49',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-05 13:53:49.095792',NULL,NULL),(482,441,'Créée','user@example.com','2024-11-05 13:00:15','2024-11-05 13:00:15',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(483,443,'CREATED','modifier@example.com','2024-11-05 13:00:17','2024-11-05 13:00:16',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-05 14:00:16.676062',NULL,NULL),(489,448,'Créée','user@example.com','2024-11-05 13:06:17','2024-11-05 13:06:16',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(490,450,'CREATED','modifier@example.com','2024-11-05 13:06:18','2024-11-05 13:06:18',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-05 14:06:18.424430',NULL,NULL),(496,455,'Créée','user@example.com','2024-11-05 13:17:10','2024-11-05 13:17:10',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(497,457,'CREATED','modifier@example.com','2024-11-05 13:17:12','2024-11-05 13:17:11',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-05 14:17:11.956418',NULL,NULL),(503,462,'Créée','user@example.com','2024-11-05 13:20:40','2024-11-05 13:20:39',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(504,464,'CREATED','modifier@example.com','2024-11-05 13:20:41','2024-11-05 13:20:41',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-05 14:20:41.199894',NULL,NULL),(510,469,'Créée','user@example.com','2024-11-05 14:56:15','2024-11-05 14:56:15',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(511,471,'CREATED','modifier@example.com','2024-11-05 14:56:17','2024-11-05 14:56:16',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-05 15:56:16.887779',NULL,NULL),(517,476,'Créée','user@example.com','2024-11-06 12:39:58','2024-11-06 12:39:58',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(518,478,'CREATED','modifier@example.com','2024-11-06 12:40:00','2024-11-06 12:39:59',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-06 13:39:59.739272',NULL,NULL),(524,483,'Créée','user@example.com','2024-11-06 12:43:52','2024-11-06 12:43:52',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(525,485,'CREATED','modifier@example.com','2024-11-06 12:43:54','2024-11-06 12:43:53',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-06 13:43:53.773316',NULL,NULL),(531,490,'Créée','user@example.com','2024-11-06 13:49:58','2024-11-06 13:49:58',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(532,492,'CREATED','modifier@example.com','2024-11-06 13:50:00','2024-11-06 13:50:00',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-06 14:50:00.317013',NULL,NULL),(538,497,'Créée','user@example.com','2024-11-06 13:51:14','2024-11-06 13:51:14',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(539,499,'CREATED','modifier@example.com','2024-11-06 13:51:16','2024-11-06 13:51:15',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-06 14:51:15.766783',NULL,NULL),(545,504,'Créée','user@example.com','2024-11-06 13:54:14','2024-11-06 13:54:13',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(546,506,'CREATED','modifier@example.com','2024-11-06 13:54:16','2024-11-06 13:54:15',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-06 14:54:15.774755',NULL,NULL),(552,511,'Créée','user@example.com','2024-11-07 13:23:10','2024-11-07 13:23:09',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(553,513,'CREATED','modifier@example.com','2024-11-07 13:23:11','2024-11-07 13:23:10',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-07 14:23:10.927942',NULL,NULL),(559,518,'Créée','user@example.com','2024-11-07 13:29:37','2024-11-07 13:29:37',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(560,520,'CREATED','modifier@example.com','2024-11-07 13:29:38','2024-11-07 13:29:38',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-07 14:29:38.475188',NULL,NULL),(566,525,'Créée','user@example.com','2024-11-07 13:32:26','2024-11-07 13:32:26',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(567,527,'CREATED','modifier@example.com','2024-11-07 13:32:27','2024-11-07 13:32:27',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-07 14:32:27.416627',NULL,NULL),(573,532,'Créée','user@example.com','2024-11-07 13:36:55','2024-11-07 13:36:54',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(574,534,'CREATED','modifier@example.com','2024-11-07 13:36:57','2024-11-07 13:36:56',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-07 14:36:56.865106',NULL,NULL),(580,539,'Créée','user@example.com','2024-11-07 13:57:57','2024-11-07 13:57:57',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(581,541,'CREATED','modifier@example.com','2024-11-07 13:58:00','2024-11-07 13:58:00',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-07 14:58:00.249575',NULL,NULL),(587,546,'Créée','user@example.com','2024-11-07 14:18:31','2024-11-07 14:18:30',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(588,548,'CREATED','modifier@example.com','2024-11-07 14:18:33','2024-11-07 14:18:33',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-07 15:18:33.450083',NULL,NULL),(594,553,'Créée','user@example.com','2024-11-09 14:47:20','2024-11-09 14:47:19',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(595,555,'CREATED','modifier@example.com','2024-11-09 14:47:23','2024-11-09 14:47:22',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-09 15:47:22.722244',NULL,NULL),(601,560,'Créée','user@example.com','2024-11-09 14:48:57','2024-11-09 14:48:57',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(602,562,'CREATED','modifier@example.com','2024-11-09 14:48:59','2024-11-09 14:48:59',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-09 15:48:59.127721',NULL,NULL),(608,567,'Créée','user@example.com','2024-11-09 14:56:56','2024-11-09 14:56:56',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(609,569,'CREATED','modifier@example.com','2024-11-09 14:56:59','2024-11-09 14:56:58',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-09 15:56:58.959913',NULL,NULL),(615,574,'Créée','user@example.com','2024-11-09 15:17:40','2024-11-09 15:17:40',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(616,576,'CREATED','modifier@example.com','2024-11-09 15:17:44','2024-11-09 15:17:44',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-09 16:17:44.246934',NULL,NULL),(622,581,'Créée','user@example.com','2024-11-09 15:22:40','2024-11-09 15:22:39',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(623,583,'CREATED','modifier@example.com','2024-11-09 15:22:42','2024-11-09 15:22:42',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-09 16:22:42.265807',NULL,NULL),(629,588,'Créée','user@example.com','2024-11-09 15:37:25','2024-11-09 15:37:25',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(630,590,'CREATED','modifier@example.com','2024-11-09 15:37:27','2024-11-09 15:37:26',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-09 16:37:26.952783',NULL,NULL),(636,595,'Créée','user@example.com','2024-11-09 15:41:30','2024-11-09 15:41:29',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(637,597,'CREATED','modifier@example.com','2024-11-09 15:41:32','2024-11-09 15:41:31',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-09 16:41:31.513205',NULL,NULL),(643,602,'Créée','user@example.com','2024-11-09 16:00:14','2024-11-09 16:00:13',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(644,604,'CREATED','modifier@example.com','2024-11-09 16:00:17','2024-11-09 16:00:16',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-09 17:00:16.618622',NULL,NULL),(650,609,'Créée','user@example.com','2024-11-09 16:06:56','2024-11-09 16:06:56',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(651,611,'CREATED','modifier@example.com','2024-11-09 16:06:58','2024-11-09 16:06:58',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-09 17:06:58.083305',NULL,NULL),(657,616,'Créée','user@example.com','2024-11-09 16:33:21','2024-11-09 16:33:21',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(658,618,'CREATED','modifier@example.com','2024-11-09 16:33:23','2024-11-09 16:33:23',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-09 17:33:23.361033',NULL,NULL),(664,623,'Créée','user@example.com','2024-11-09 20:22:17','2024-11-09 20:22:17',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(665,625,'CREATED','modifier@example.com','2024-11-09 20:22:21','2024-11-09 20:22:20',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-09 21:22:20.570374',NULL,NULL),(671,630,'Créée','user@example.com','2024-11-09 22:20:44','2024-11-09 22:20:44',NULL,'Ceci est une description de la tâche.',NULL,NULL,NULL,NULL,NULL),(672,632,'CREATED','modifier@example.com','2024-11-09 22:20:47','2024-11-09 22:20:47',NULL,'Initial description',NULL,'IN_PROGRESS','2024-11-09 23:20:47.072259',NULL,NULL);
/*!40000 ALTER TABLE `task_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_users`
--

DROP TABLE IF EXISTS `task_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_users` (
  `task_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `project_id` bigint DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `assigned_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`task_id`,`user_id`),
  KEY `user_id` (`user_id`),
  KEY `project_id` (`project_id`),
  CONSTRAINT `task_users_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `tasks` (`id`),
  CONSTRAINT `task_users_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `task_users_ibfk_3` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_users`
--

LOCK TABLES `task_users` WRITE;
/*!40000 ALTER TABLE `task_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `task_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasks` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `priority` varchar(255) DEFAULT NULL,
  `project_id` bigint DEFAULT NULL,
  `assigned_by` varchar(255) DEFAULT NULL,
  `assigned_to` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKekeburt7kaq2chv97jybdxnfm` (`project_id`),
  CONSTRAINT `FKekeburt7kaq2chv97jybdxnfm` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=637 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` VALUES (390,'ceci est un test','2024-11-30','Frontend','low',459,'steveduchateau@outlook.fr','stevetadesse0@gmail.com','En cours','2024-11-04 12:06:17.200326','steveduchateau@outlook.fr','2024-11-04 12:28:42.080725'),(391,'ceci est un test','2024-11-30','Frontend','low',459,'steveduchateau@outlook.fr','stevetadesse0@gmail.com','Terminée','2024-11-04 12:24:29.293682','steveduchateau@outlook.fr','2024-11-05 01:30:20.549319'),(392,'Ceci est une description de la tâche.','2024-11-12','Nouvelle Tâche','HIGH',460,'user@example.com','assigned@example.com','IN_PROGRESS','2024-11-05 03:25:44.111644','user@example.com','2024-11-05 03:25:44.111662'),(393,'Description of the test task.','2024-11-12','Test Task','HIGH',462,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-05 03:25:45.765458','admin_1730773545758@example.com','2024-11-05 03:25:45.774214'),(394,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-05 03:25:45.786862','creator@example.com','2024-11-05 03:25:45.786876'),(399,'Ceci est une description de la tâche.','2024-11-12','Nouvelle Tâche','HIGH',467,'user@example.com','assigned@example.com','IN_PROGRESS','2024-11-05 12:51:14.472193','user@example.com','2024-11-05 12:51:14.472204'),(400,'Description of the test task.','2024-11-12','Test Task','HIGH',469,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-05 12:51:16.034741','admin_1730807476027@example.com','2024-11-05 12:51:16.042705'),(401,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-05 12:51:16.054039','creator@example.com','2024-11-05 12:51:16.054047'),(406,'Ceci est une description de la tâche.','2024-11-12','Nouvelle Tâche','HIGH',474,'user@example.com','assigned@example.com','IN_PROGRESS','2024-11-05 13:02:47.422227','user@example.com','2024-11-05 13:02:47.422236'),(407,'Description of the test task.','2024-11-12','Test Task','HIGH',476,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-05 13:02:49.050982','admin_1730808169043@example.com','2024-11-05 13:02:49.058883'),(408,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-05 13:02:49.070316','creator@example.com','2024-11-05 13:02:49.070321'),(413,'Ceci est une description de la tâche.','2024-11-12','Nouvelle Tâche','HIGH',487,'user@example.com','assigned@example.com','IN_PROGRESS','2024-11-05 13:12:33.680195','user@example.com','2024-11-05 13:12:33.680205'),(414,'Description of the test task.','2024-11-12','Test Task','HIGH',489,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-05 13:12:35.593316','admin_1730808755586@example.com','2024-11-05 13:12:35.601439'),(415,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-05 13:12:35.613383','creator@example.com','2024-11-05 13:12:35.613391'),(420,'Ceci est une description de la tâche.','2024-11-12','Nouvelle Tâche','HIGH',499,'user@example.com','assigned@example.com','IN_PROGRESS','2024-11-05 13:20:54.115009','user@example.com','2024-11-05 13:20:54.115015'),(421,'Description of the test task.','2024-11-12','Test Task','HIGH',501,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-05 13:20:55.943555','admin_1730809255937@example.com','2024-11-05 13:20:55.953665'),(422,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-05 13:20:55.968692','creator@example.com','2024-11-05 13:20:55.968698'),(427,'Ceci est une description de la tâche.','2024-11-12','Nouvelle Tâche','HIGH',511,'user@example.com','assigned@example.com','IN_PROGRESS','2024-11-05 13:48:46.608714','user@example.com','2024-11-05 13:48:46.608724'),(428,'Description of the test task.','2024-11-12','Test Task','HIGH',513,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-05 13:48:48.217373','admin_1730810928208@example.com','2024-11-05 13:48:48.226017'),(429,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-05 13:48:48.237015','creator@example.com','2024-11-05 13:48:48.237021'),(434,'Ceci est une description de la tâche.','2024-11-12','Nouvelle Tâche','HIGH',523,'user@example.com','assigned@example.com','IN_PROGRESS','2024-11-05 13:53:47.477910','user@example.com','2024-11-05 13:53:47.477917'),(435,'Description of the test task.','2024-11-12','Test Task','HIGH',525,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-05 13:53:49.072353','admin_1730811229060@example.com','2024-11-05 13:53:49.081104'),(436,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-05 13:53:49.093583','creator@example.com','2024-11-05 13:53:49.093587'),(441,'Ceci est une description de la tâche.','2024-11-12','Nouvelle Tâche','HIGH',530,'user@example.com','assigned@example.com','IN_PROGRESS','2024-11-05 14:00:15.019986','user@example.com','2024-11-05 14:00:15.019997'),(442,'Description of the test task.','2024-11-12','Test Task','HIGH',532,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-05 14:00:16.654313','admin_1730811616647@example.com','2024-11-05 14:00:16.662356'),(443,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-05 14:00:16.673846','creator@example.com','2024-11-05 14:00:16.673852'),(448,'Ceci est une description de la tâche.','2024-11-12','Nouvelle Tâche','HIGH',537,'user@example.com','assigned@example.com','IN_PROGRESS','2024-11-05 14:06:16.922686','user@example.com','2024-11-05 14:06:16.922696'),(449,'Description of the test task.','2024-11-12','Test Task','HIGH',539,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-05 14:06:18.402989','admin_1730811978383@example.com','2024-11-05 14:06:18.411326'),(450,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-05 14:06:18.422238','creator@example.com','2024-11-05 14:06:18.422244'),(455,'Ceci est une description de la tâche.','2024-11-12','Nouvelle Tâche','HIGH',544,'user@example.com','steveduchateau@outlook.fr','IN_PROGRESS','2024-11-05 14:17:10.355716','user@example.com','2024-11-05 14:17:10.355724'),(456,'Description of the test task.','2024-11-12','Test Task','HIGH',546,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-05 14:17:11.934836','admin_1730812631928@example.com','2024-11-05 14:17:11.942554'),(457,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-05 14:17:11.953875','creator@example.com','2024-11-05 14:17:11.953881'),(462,'Ceci est une description de la tâche.','2024-11-12','Nouvelle Tâche','HIGH',551,'user@example.com','steveduchateau@outlook.fr','IN_PROGRESS','2024-11-05 14:20:39.504290','user@example.com','2024-11-05 14:20:39.504298'),(463,'Description of the test task.','2024-11-12','Test Task','HIGH',553,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-05 14:20:41.178942','admin_1730812841173@example.com','2024-11-05 14:20:41.186484'),(464,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-05 14:20:41.197675','creator@example.com','2024-11-05 14:20:41.197680'),(469,'Ceci est une description de la tâche.','2024-11-12','Nouvelle Tâche','HIGH',558,'user@example.com','steveduchateau@outlook.fr','IN_PROGRESS','2024-11-05 15:56:15.240058','user@example.com','2024-11-05 15:56:15.240073'),(470,'Description of the test task.','2024-11-12','Test Task','HIGH',560,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-05 15:56:16.864612','admin_1730818576857@example.com','2024-11-05 15:56:16.872888'),(471,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-05 15:56:16.885248','creator@example.com','2024-11-05 15:56:16.885257'),(476,'Ceci est une description de la tâche.','2024-11-13','Nouvelle Tâche','HIGH',565,'user@example.com','steveduchateau@outlook.fr','IN_PROGRESS','2024-11-06 13:39:58.021424','user@example.com','2024-11-06 13:39:58.021436'),(477,'Description of the test task.','2024-11-13','Test Task','HIGH',567,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-06 13:39:59.717697','admin_1730896799711@example.com','2024-11-06 13:39:59.725206'),(478,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-06 13:39:59.736757','creator@example.com','2024-11-06 13:39:59.736763'),(483,'Ceci est une description de la tâche.','2024-11-13','Nouvelle Tâche','HIGH',572,'user@example.com','steveduchateau@outlook.fr','IN_PROGRESS','2024-11-06 13:43:52.191419','user@example.com','2024-11-06 13:43:52.191424'),(484,'Description of the test task.','2024-11-13','Test Task','HIGH',574,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-06 13:43:53.749726','admin_1730897033744@example.com','2024-11-06 13:43:53.756968'),(485,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-06 13:43:53.771213','creator@example.com','2024-11-06 13:43:53.771217'),(490,'Ceci est une description de la tâche.','2024-11-13','Nouvelle Tâche','HIGH',579,'user@example.com','steveduchateau@outlook.fr','IN_PROGRESS','2024-11-06 14:49:58.297867','user@example.com','2024-11-06 14:49:58.297881'),(491,'Description of the test task.','2024-11-13','Test Task','HIGH',581,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-06 14:50:00.267611','admin_1730901000256@example.com','2024-11-06 14:50:00.297042'),(492,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-06 14:50:00.313685','creator@example.com','2024-11-06 14:50:00.313691'),(497,'Ceci est une description de la tâche.','2024-11-13','Nouvelle Tâche','HIGH',586,'user@example.com','steveduchateau@outlook.fr','IN_PROGRESS','2024-11-06 14:51:14.028135','user@example.com','2024-11-06 14:51:14.028153'),(498,'Description of the test task.','2024-11-13','Test Task','HIGH',588,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-06 14:51:15.734723','admin_1730901075728@example.com','2024-11-06 14:51:15.750810'),(499,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-06 14:51:15.764278','creator@example.com','2024-11-06 14:51:15.764286'),(504,'Ceci est une description de la tâche.','2024-11-13','Nouvelle Tâche','HIGH',593,'user@example.com','steveduchateau@outlook.fr','IN_PROGRESS','2024-11-06 14:54:13.672823','user@example.com','2024-11-06 14:54:13.672836'),(505,'Description of the test task.','2024-11-13','Test Task','HIGH',595,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-06 14:54:15.753833','admin_1730901255748@example.com','2024-11-06 14:54:15.761460'),(506,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-06 14:54:15.771910','creator@example.com','2024-11-06 14:54:15.771917'),(511,'Ceci est une description de la tâche.','2024-11-14','Nouvelle Tâche','HIGH',600,'user@example.com','steveduchateau@outlook.fr','IN_PROGRESS','2024-11-07 14:23:09.702527','user@example.com','2024-11-07 14:23:09.702535'),(512,'Description of the test task.','2024-11-14','Test Task','HIGH',602,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-07 14:23:10.894870','admin_1730985790886@example.com','2024-11-07 14:23:10.911355'),(513,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-07 14:23:10.925835','creator@example.com','2024-11-07 14:23:10.925845'),(518,'Ceci est une description de la tâche.','2024-11-14','Nouvelle Tâche','HIGH',607,'user@example.com','steveduchateau@outlook.fr','IN_PROGRESS','2024-11-07 14:29:37.141206','user@example.com','2024-11-07 14:29:37.141218'),(519,'Description of the test task.','2024-11-14','Test Task','HIGH',609,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-07 14:29:38.451378','admin_1730986178445@example.com','2024-11-07 14:29:38.459415'),(520,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-07 14:29:38.472910','creator@example.com','2024-11-07 14:29:38.472959'),(525,'Ceci est une description de la tâche.','2024-11-14','Nouvelle Tâche','HIGH',614,'user@example.com','steveduchateau@outlook.fr','IN_PROGRESS','2024-11-07 14:32:26.186858','user@example.com','2024-11-07 14:32:26.186869'),(526,'Description of the test task.','2024-11-14','Test Task','HIGH',616,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-07 14:32:27.391932','admin_1730986347385@example.com','2024-11-07 14:32:27.400681'),(527,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-07 14:32:27.414634','creator@example.com','2024-11-07 14:32:27.414643'),(532,'Ceci est une description de la tâche.','2024-11-14','Nouvelle Tâche','HIGH',621,'user@example.com','steveduchateau@outlook.fr','IN_PROGRESS','2024-11-07 14:36:54.786165','user@example.com','2024-11-07 14:36:54.786173'),(533,'Description of the test task.','2024-11-14','Test Task','HIGH',623,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-07 14:36:56.841621','admin_1730986616836@example.com','2024-11-07 14:36:56.849038'),(534,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-07 14:36:56.862579','creator@example.com','2024-11-07 14:36:56.862585'),(539,'Ceci est une description de la tâche.','2024-11-14','Nouvelle Tâche','HIGH',628,'user@example.com','steveduchateau@outlook.fr','IN_PROGRESS','2024-11-07 14:57:57.378070','user@example.com','2024-11-07 14:57:57.378086'),(540,'Description of the test task.','2024-11-14','Test Task','HIGH',630,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-07 14:58:00.223522','admin_1730987880217@example.com','2024-11-07 14:58:00.231962'),(541,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-07 14:58:00.247413','creator@example.com','2024-11-07 14:58:00.247419'),(546,'Ceci est une description de la tâche.','2024-11-14','Nouvelle Tâche','HIGH',635,'user@example.com','steveduchateau@outlook.fr','IN_PROGRESS','2024-11-07 15:18:30.919539','user@example.com','2024-11-07 15:18:30.919549'),(547,'Description of the test task.','2024-11-14','Test Task','HIGH',637,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-07 15:18:33.426299','admin_1730989113419@example.com','2024-11-07 15:18:33.434377'),(548,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-07 15:18:33.447849','creator@example.com','2024-11-07 15:18:33.447854'),(553,'Ceci est une description de la tâche.','2024-11-16','Nouvelle Tâche','HIGH',642,'user@example.com','steveduchateau@outlook.fr','IN_PROGRESS','2024-11-09 15:47:19.982326','user@example.com','2024-11-09 15:47:19.982339'),(554,'Description of the test task.','2024-11-16','Test Task','HIGH',644,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-09 15:47:22.698244','admin_1731163642691@example.com','2024-11-09 15:47:22.706425'),(555,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-09 15:47:22.719666','creator@example.com','2024-11-09 15:47:22.719674'),(560,'Ceci est une description de la tâche.','2024-11-16','Nouvelle Tâche','HIGH',649,'user@example.com','steveduchateau@outlook.fr','IN_PROGRESS','2024-11-09 15:48:57.295154','user@example.com','2024-11-09 15:48:57.295176'),(561,'Description of the test task.','2024-11-16','Test Task','HIGH',651,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-09 15:48:59.076019','admin_1731163739030@example.com','2024-11-09 15:48:59.100936'),(562,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-09 15:48:59.122280','creator@example.com','2024-11-09 15:48:59.122295'),(567,'Ceci est une description de la tâche.','2024-11-16','Nouvelle Tâche','HIGH',656,'user@example.com','steveduchateau@outlook.fr','IN_PROGRESS','2024-11-09 15:56:56.264576','user@example.com','2024-11-09 15:56:56.264639'),(568,'Description of the test task.','2024-11-16','Test Task','HIGH',658,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-09 15:56:58.927579','admin_1731164218920@example.com','2024-11-09 15:56:58.936706'),(569,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-09 15:56:58.954557','creator@example.com','2024-11-09 15:56:58.954570'),(574,'Ceci est une description de la tâche.','2024-11-16','Nouvelle Tâche','HIGH',663,'user@example.com','steveduchateau@outlook.fr','IN_PROGRESS','2024-11-09 16:17:40.337972','user@example.com','2024-11-09 16:17:40.337985'),(575,'Description of the test task.','2024-11-16','Test Task','HIGH',665,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-09 16:17:44.219345','admin_1731165464213@example.com','2024-11-09 16:17:44.227758'),(576,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-09 16:17:44.244288','creator@example.com','2024-11-09 16:17:44.244297'),(581,'Ceci est une description de la tâche.','2024-11-16','Nouvelle Tâche','HIGH',670,'user@example.com','steveduchateau@outlook.fr','IN_PROGRESS','2024-11-09 16:22:39.680601','user@example.com','2024-11-09 16:22:39.680614'),(582,'Description of the test task.','2024-11-16','Test Task','HIGH',672,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-09 16:22:42.244521','admin_1731165762234@example.com','2024-11-09 16:22:42.252470'),(583,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-09 16:22:42.262870','creator@example.com','2024-11-09 16:22:42.262883'),(588,'Ceci est une description de la tâche.','2024-11-16','Nouvelle Tâche','HIGH',677,'user@example.com','steveduchateau@outlook.fr','IN_PROGRESS','2024-11-09 16:37:25.143249','user@example.com','2024-11-09 16:37:25.143264'),(589,'Description of the test task.','2024-11-16','Test Task','HIGH',679,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-09 16:37:26.927460','admin_1731166646920@example.com','2024-11-09 16:37:26.936964'),(590,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-09 16:37:26.949848','creator@example.com','2024-11-09 16:37:26.949863'),(595,'Ceci est une description de la tâche.','2024-11-16','Nouvelle Tâche','HIGH',684,'user@example.com','steveduchateau@outlook.fr','IN_PROGRESS','2024-11-09 16:41:29.519678','user@example.com','2024-11-09 16:41:29.519692'),(596,'Description of the test task.','2024-11-16','Test Task','HIGH',686,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-09 16:41:31.415609','admin_1731166891398@example.com','2024-11-09 16:41:31.428680'),(597,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-09 16:41:31.485016','creator@example.com','2024-11-09 16:41:31.485032'),(602,'Ceci est une description de la tâche.','2024-11-16','Nouvelle Tâche','HIGH',691,'user@example.com','steveduchateau@outlook.fr','IN_PROGRESS','2024-11-09 17:00:13.817345','user@example.com','2024-11-09 17:00:13.817361'),(603,'Description of the test task.','2024-11-16','Test Task','HIGH',693,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-09 17:00:16.595244','admin_1731168016589@example.com','2024-11-09 17:00:16.603845'),(604,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-09 17:00:16.615932','creator@example.com','2024-11-09 17:00:16.615939'),(609,'Ceci est une description de la tâche.','2024-11-16','Nouvelle Tâche','HIGH',698,'user@example.com','steveduchateau@outlook.fr','IN_PROGRESS','2024-11-09 17:06:56.456693','user@example.com','2024-11-09 17:06:56.456707'),(610,'Description of the test task.','2024-11-16','Test Task','HIGH',700,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-09 17:06:58.056150','admin_1731168418049@example.com','2024-11-09 17:06:58.064967'),(611,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-09 17:06:58.079963','creator@example.com','2024-11-09 17:06:58.079982'),(616,'Ceci est une description de la tâche.','2024-11-16','Nouvelle Tâche','HIGH',705,'user@example.com','steveduchateau@outlook.fr','IN_PROGRESS','2024-11-09 17:33:21.414283','user@example.com','2024-11-09 17:33:21.414296'),(617,'Description of the test task.','2024-11-16','Test Task','HIGH',707,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-09 17:33:23.306016','admin_1731170003277@example.com','2024-11-09 17:33:23.320221'),(618,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-09 17:33:23.357273','creator@example.com','2024-11-09 17:33:23.357289'),(623,'Ceci est une description de la tâche.','2024-11-16','Nouvelle Tâche','HIGH',712,'user@example.com','steveduchateau@outlook.fr','IN_PROGRESS','2024-11-09 21:22:17.475617','user@example.com','2024-11-09 21:22:17.475634'),(624,'Description of the test task.','2024-11-16','Test Task','HIGH',714,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-09 21:22:20.515764','admin_1731183740487@example.com','2024-11-09 21:22:20.531521'),(625,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-09 21:22:20.564346','creator@example.com','2024-11-09 21:22:20.564366'),(630,'Ceci est une description de la tâche.','2024-11-16','Nouvelle Tâche','HIGH',719,'user@example.com','steveduchateau@outlook.fr','IN_PROGRESS','2024-11-09 23:20:44.129407','user@example.com','2024-11-09 23:20:44.129422'),(631,'Description of the test task.','2024-11-16','Test Task','HIGH',721,'assigner@example.com','assignee@example.com','COMPLETED','2024-11-09 23:20:47.042574','admin_1731190847036@example.com','2024-11-09 23:20:47.053145'),(632,'Initial description',NULL,'Sample Task','MEDIUM',NULL,'assigner@example.com','assignee@example.com','IN_PROGRESS','2024-11-09 23:20:47.068701','creator@example.com','2024-11-09 23:20:47.068719');
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=483 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (479,'2024-11-09 23:20:46.961490','testuser1731190846961@example.com','securePassword','Test User'),(481,'2024-11-09 23:20:47.036433','admin_1731190847036@example.com','securePassword','Admin User 1731190847036');
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

-- Dump completed on 2024-11-09 23:28:11
