CREATE DATABASE  IF NOT EXISTS `railway` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `railway`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: db_yasuki
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `cart_items`
--

DROP TABLE IF EXISTS `cart_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt5ytj44vpktaa6t9o4906gi2d` (`product_id`),
  KEY `FKsy01evyog4nc8vgw1769u5tyg` (`user_id`),
  CONSTRAINT `FKsy01evyog4nc8vgw1769u5tyg` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKt5ytj44vpktaa6t9o4906gi2d` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_items`
--

LOCK TABLES `cart_items` WRITE;
/*!40000 ALTER TABLE `cart_items` DISABLE KEYS */;
INSERT INTO `cart_items` (`id`, `quantity`, `product_id`, `user_id`) VALUES (1,1,2,2),(2,1,5,2),(3,2,4,2),(4,1,4,1);
/*!40000 ALTER TABLE `cart_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_categories`
--

DROP TABLE IF EXISTS `my_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `my_categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `is_active` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `group_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_oul14ho7bctbefv8jywp5v3i2` (`slug`),
  KEY `FK21ivx95fkb6ij09276drmxmcv` (`group_id`),
  CONSTRAINT `FK21ivx95fkb6ij09276drmxmcv` FOREIGN KEY (`group_id`) REFERENCES `group_categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_categories`
--

LOCK TABLES `my_categories` WRITE;
/*!40000 ALTER TABLE `my_categories` DISABLE KEYS */;
INSERT INTO `my_categories` (`id`, `is_active`, `name`, `slug`, `group_id`) VALUES (1,_binary '\0','Chăm Sóc Da Mặt Cao Cấp','cham-soc-da-mat-cao-cap',1),(2,_binary '\0','Trang Điểm Cao Cấp','trang-iem-cao-cap',1),(3,_binary '\0','Tẩy Trang','tay-trang',2),(4,_binary '\0','Sửa Rửa Mặt','sua-rua-mat',2),(5,_binary '\0','Tẩy Tế Bào Chết','tay-te-bao-chet',2),(6,_binary '\0','Khẩu trang','khau-trang',3),(7,_binary '\0','Mặt Nạ Xông Hơi','mat-na-xong-hoi',3),(8,_binary '\0','Chống Muỗi','chong-muoi',3),(9,_binary '\0','Bàn Chải Đánh Răng','ban-chai-anh-rang',4);
/*!40000 ALTER TABLE `my_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_categories`
--

DROP TABLE IF EXISTS `group_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `is_active` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4a72esch5swkscj6uojccx6ff` (`slug`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_categories`
--

LOCK TABLES `group_categories` WRITE;
/*!40000 ALTER TABLE `group_categories` DISABLE KEYS */;
INSERT INTO `group_categories` (`id`, `is_active`, `name`, `slug`) VALUES (1,_binary '\0','Mỹ Phẩm','my-pham'),(2,_binary '','Chăm Sóc Da Mặt','cham-soc-da-mat'),(3,_binary '','Chăm Sóc Cơ thể','cham-soc-co-the'),(4,_binary '','Chăm Sóc Cá Nhân','cham-soc-ca-nhan'),(5,_binary '','Nước Hoa','nuoc-hoa'),(6,_binary '\0','Giảm Béo','giam-beo');
/*!40000 ALTER TABLE `group_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news_app`
--

DROP TABLE IF EXISTS `news_app`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `news_app` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(1024) DEFAULT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news_app`
--

LOCK TABLES `news_app` WRITE;
/*!40000 ALTER TABLE `news_app` DISABLE KEYS */;
INSERT INTO `news_app` (`id`, `content`, `create_at`, `image`, `is_active`, `title`) VALUES (1,'House giá hạt dẻ, chất miễn đùa Không phải là những item makeup mới. Thậm chí nếu không nói là lâu đời. Nhưng ở thời điểm hiện tại','2023-08-01 15:04:59.299000','https://www.kosmebox.com/image/cache/data/BLOG/Nhung-item-makeup-nha-etude-house-gia-hat-de/Nhung-item-makeup-nha-etude-house-gia-hat-de-7-9-225x225.jpg',_binary '','Những Item makeup nhà Etude House giá hạt dẻ'),(2,'House giá hạt dẻ, chất miễn đùa Không phải là những item makeup mới. Thậm chí nếu không nói là lâu đời. Nhưng ở thời điểm hiện tại','2023-08-01 15:04:59.312000','https://www.kosmebox.com/image/cache/data/BLOG/Nhung-item-makeup-nha-etude-house-gia-hat-de/Nhung-item-makeup-nha-etude-house-gia-hat-de-7-9-225x225.jpg',_binary '','Những Item makeup nhà Etude House giá hạt dẻ'),(3,'House giá hạt dẻ, chất miễn đùa Không phải là những item makeup mới. Thậm chí nếu không nói là lâu đời. Nhưng ở thời điểm hiện tại','2023-08-01 15:04:59.319000','https://www.kosmebox.com/image/cache/data/BLOG/Nhung-item-makeup-nha-etude-house-gia-hat-de/Nhung-item-makeup-nha-etude-house-gia-hat-de-7-9-225x225.jpg',_binary '','Những Item makeup nhà Etude House giá hạt dẻ');
/*!40000 ALTER TABLE `news_app` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(1024) DEFAULT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total_payment` decimal(19,2) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3b2ji759qk04mk7iiglb0rcq6` (`user_id`),
  CONSTRAINT `FK3b2ji759qk04mk7iiglb0rcq6` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` (`id`, `address`, `create_at`, `email`, `name`, `note`, `phone_number`, `status`, `total_payment`, `user_id`) VALUES (1,'799 Quanrg Tring 79234 Go vap ','2023-08-01 15:04:59.264000','luctuankietkg@gmail.com','Tuan Kiet',NULL,'093404566','Đặt hàng',1995468979.00,NULL),(2,'799 Quanrg Tring 79234 Go vap ','2023-08-01 15:04:59.276000','luctuankietkg@gmail.com','Tuan Kiet',NULL,'093404566','Đặt hàng',1995468979.00,NULL),(3,'799 Quanrg Tring 79234 Go vap ','2023-08-01 15:04:59.286000','luctuankietkg@gmail.com','Tuan Kiet',NULL,'093404566','Đặt hàng',1995468979.00,NULL);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7pv97udw3cbgddveqn6eoosng` (`order_id`),
  CONSTRAINT `FK7pv97udw3cbgddveqn6eoosng` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_images`
--

DROP TABLE IF EXISTS `product_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb4ct19237ff3iusxp73vsdcp2` (`product_id`),
  CONSTRAINT `FKb4ct19237ff3iusxp73vsdcp2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_images`
--

LOCK TABLES `product_images` WRITE;
/*!40000 ALTER TABLE `product_images` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `brand` varchar(255) DEFAULT NULL,
  `date_release` datetime(6) DEFAULT NULL,
  `full_description` varchar(4096) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `main_image` varchar(4096) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `percent_discount` double DEFAULT NULL,
  `price` decimal(12,3) DEFAULT NULL,
  `quantity_left` int DEFAULT NULL,
  `quantity_sold` int DEFAULT NULL,
  `short_description` varchar(1024) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm6bbequyfyarhud9himrk9cm4` (`category_id`),
  CONSTRAINT `FKm6bbequyfyarhud9himrk9cm4` FOREIGN KEY (`category_id`) REFERENCES `my_categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` (`id`, `brand`, `date_release`, `full_description`, `is_active`, `main_image`, `name`, `percent_discount`, `price`, `quantity_left`, `quantity_sold`, `short_description`, `slug`, `category_id`) VALUES (1,'SHU','2023-08-01 15:04:58.086000','FullDes',NULL,'https://media.hasaki.vn/catalog/product/p/h/phan-nuoc-gilaa-kiem-dau-va-duong-da-2-natural-beige-13g-10_img_358x358_843626_fit_center.jpg','Phấn nước',12,999.000,10,90,'Short des','phan-nuoc',1),(2,'C','2023-08-01 15:04:58.123000',NULL,_binary '','https://media.hasaki.vn/catalog/product/p/r/promotions-auto-sua-rua-mat-cetaphil-diu-nhe-khong-xa-phong-125ml-moi_A9AYTmiVPjkRo29X_img_358x358_843626_fit_center.png','Cetapil',1,1998979.000,20,20,NULL,'cetapil',1),(3,'C','2023-08-01 15:04:58.156000',NULL,_binary '','https://media.hasaki.vn/catalog/product/g/o/google-shopping-mat-na-ngu-moi-laneige-huong-qua-mong-mini-8g-1_img_358x358_843626_fit_center.jpg','Lanegie',7,3387983.000,3,32,NULL,'lanegie',1),(4,'C','2023-08-01 15:04:58.175000',NULL,_binary '','https://media.hasaki.vn/catalog/product/g/o/google-shopping-mat-na-kiehl-s-nghe-hat-viet-quat-lam-sang-da-28ml-1686566767_img_358x358_843626_fit_center.jpg','Kiehl',8,899777.000,440,0,NULL,'kiehl',1),(5,'C','2023-08-01 15:04:58.192000',NULL,_binary '','https://media.hasaki.vn/catalog/product/t/o/top_fb_ads_100550094_310523-1685529668_img_358x358_843626_fit_center.jpg','MAC',10,222676.000,44,11,NULL,'mac',2),(6,'C','2023-08-01 15:04:58.209000',NULL,_binary '','https://media.hasaki.vn/catalog/product/p/r/promotions-auto-nuoc-tay-trang-tuoi-mat-l-oreal-3-in-1-danh-cho-da-dau-da-hon-hop-400ml_hT9R6HqHaZS1omAq_img_358x358_843626_fit_center.png','Nước Tẩy Trang',1,1998979.000,20,20,NULL,'nuoc-tay-trang',3),(7,'C','2023-08-01 15:04:58.224000',NULL,_binary '','https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-bo-3-hop-bong-tay-trang-co-ban-silcot-82-mieng-hop-1684396744_img_358x358_843626_fit_center.jpg','Bông Tẩy Trang',7,3387983.000,3,32,NULL,'bong-tay-trang',3),(8,'C','2023-08-01 15:04:58.239000',NULL,_binary '','','Sữa Rửa Mặt Cetaphil Dịu Lành Cho Da Nhạy Cảm 500ml',8,899777.000,440,0,NULL,'sua-rua-mat-cetaphil-diu-lanh-cho-da-nhay-cam-500ml',4),(9,'C','2023-08-01 15:04:58.254000',NULL,_binary '','https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-422208882-1689915355_img_358x358_843626_fit_center.jpg','Túi Refill Tẩy Tế Bào Chết Toàn Thân',10,222676.000,44,11,NULL,'tui-refill-tay-te-bao-chet-toan-than',5),(10,'C','2023-08-01 15:04:58.269000',NULL,_binary '','https://media.hasaki.vn/catalog/product/p/r/promotions-auto-bo-2-ban-chai-danh-rang-colgate-long-chai-khang-khuan_vZEzJ2ih9fWzpgut_img_358x358_843626_fit_center.png','Ban Chai danh rangg',10,222676.000,44,11,NULL,'ban-chai-danh-rangg',9);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`id`, `name`) VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `email`, `enabled`, `full_name`, `password`) VALUES (1,'luctuankietkg@gmail.com',_binary '','Tuan Kiet','$2a$10$ly98kbSg8G70gtA6lPKziupIiM79PwRqe83SbesFGUNj.A4U1JmBq'),(2,'stellaprimo99@gmail.com',_binary '','Duc Cong','$2a$10$onx3vt7OzOe9PNOXVQSxhey1NwX184BGxPJYh5rE9Gxc9tGHOMIry');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKnp0dqq44iawoqn80pjdsul85i` (`role_id`),
  CONSTRAINT `FKdxdy3w4bd1qmgc4ei96as8q7m` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKnp0dqq44iawoqn80pjdsul85i` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-01 15:14:45
