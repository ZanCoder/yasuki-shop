CREATE DATABASE  IF NOT EXISTS `db_yasuki` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_yasuki`;
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_items`
--

LOCK TABLES `cart_items` WRITE;
/*!40000 ALTER TABLE `cart_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluate`
--

DROP TABLE IF EXISTS `evaluate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evaluate` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(1024) DEFAULT NULL,
  `name_user` varchar(255) DEFAULT NULL,
  `num_star` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsqmn2cvgnxcqlmdt7f1eumq6e` (`product_id`),
  CONSTRAINT `FKsqmn2cvgnxcqlmdt7f1eumq6e` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluate`
--

LOCK TABLES `evaluate` WRITE;
/*!40000 ALTER TABLE `evaluate` DISABLE KEYS */;
/*!40000 ALTER TABLE `evaluate` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_categories`
--

LOCK TABLES `group_categories` WRITE;
/*!40000 ALTER TABLE `group_categories` DISABLE KEYS */;
INSERT INTO `group_categories` (`id`, `is_active`, `name`, `slug`) VALUES (1,_binary '\0','Mỹ Phẩm','my-pham'),(8,_binary '','Chăm sóc da mặt','cham-soc-da-mat'),(9,_binary '','Trang điểm ','trang-iem-'),(10,_binary '','Chăm sóc cơ thể','cham-soc-co-the'),(11,_binary '','Chăm sóc tóc','cham-soc-toc'),(12,_binary '','Thực phẩm chức năng','thuc-pham-chuc-nang'),(13,_binary '','Nước hoa','nuoc-hoa');
/*!40000 ALTER TABLE `group_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_categories`
--

DROP TABLE IF EXISTS `my_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `my_categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `image` varchar(1024) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `group_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_tnkxxbb4tbac3ben5t50ilgia` (`slug`),
  KEY `FKgypydypfbbyltiganwuftqb57` (`group_id`),
  CONSTRAINT `FKgypydypfbbyltiganwuftqb57` FOREIGN KEY (`group_id`) REFERENCES `group_categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_categories`
--

LOCK TABLES `my_categories` WRITE;
/*!40000 ALTER TABLE `my_categories` DISABLE KEYS */;
INSERT INTO `my_categories` (`id`, `image`, `is_active`, `name`, `slug`, `group_id`) VALUES (1,'//theme.hstatic.net/1000006063/1000748098/14/home_category_3_medium.png?v=13260',_binary '\0','Chăm Sóc Da Mặt Cao Cấp','cham-soc-da-mat-cao-cap',1),(2,'//theme.hstatic.net/1000006063/1000748098/14/home_category_3_medium.png?v=13260',_binary '\0','Trang Điểm Cao Cấp','trang-iem-cao-cap',1),(10,'https://theme.hstatic.net/1000006063/1000748098/14/home_category_6_medium.png?v=13260',_binary '\0','Nước Hoa Hồng','nuoc-hoa-hong',13),(11,'https://media.hasaki.vn/catalog/product/p/r/promotions-auto-nuoc-hoa-nam-laura-anne-diamond-homme-45ml_Gib9jmeP5H6mShC7_img_358x358_843626_fit_center.png',_binary '\0','Nước hoa nam','nuoc-hoa-nam',1),(12,'https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-nuoc-hoa-de-memoria-eau-de-parfum-lampang-09-30ml-1649995637_img_358x358_843626_fit_center.jpg',_binary '\0','Nước hoa nữ','nuoc-hoa-nu',1),(13,'https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-nuoc-tay-trang-tuoi-mat-l-oreal-3-in-1-danh-cho-da-dau-da-hon-hop-400ml-1684995866_img_300x300_b798dd_fit_center.jpg',_binary '\0','Tẩy trang','tay-trang',8),(14,'https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic_422209322-1685341808_img_300x300_b798dd_fit_center.jpg',_binary '\0','Sữa rữa mặt','sua-rua-mat',8),(15,'https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-gel-tay-te-bao-chet-bio-essence-duong-da-tuoi-tre-60g-1645002880_img_358x358_843626_fit_center.jpg',_binary '\0','Tẩy tế bào chết','tay-te-bao-chet',8),(16,'https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-kem-nen-maybelline-min-nhe-kiem-dau-chong-nang-112-30ml-1684296093_img_300x300_b798dd_fit_center.jpg',_binary '\0','Kem nền','kem-nen',9),(17,'https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-kem-lot-trang-diem-maybelline-baby-skin-22ml-1688613950_img_358x358_843626_fit_center.jpg',_binary '\0','Kem lót','kem-lot',9),(18,'https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-che-khuyet-diem-maybelline-min-li-10-light-leger-6-8ml-1688371170_img_300x300_b798dd_fit_center.jpg',_binary '\0','Che khuyết điểm','che-khuyet-iem',9),(19,'https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-sua-tam-on-the-body-huong-nuoc-hoa-classic-pink-1000g-1687928482_img_300x300_b798dd_fit_center.jpg',_binary '\0','Sữa tắm','sua-tam',10),(20,'https://media.hasaki.vn/catalog/product/k/e/kem-rua-mat-hada-labo-duong-am-toi-uu-cho-moi-loai-da-80g_3_img_300x300_b798dd_fit_center.jpg',_binary '\0','Dưỡng ẩm','duong-am',10),(21,'https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-422209343-1689243046_img_300x300_b798dd_fit_center.jpg',_binary '\0','Dưỡng sáng','duong-sang',10),(22,'https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-vien-uong-dhc-bo-sung-vitamin-c-nhat-ban-goi-60-vien-30-ngay-1681989178_img_300x300_b798dd_fit_center.jpg',_binary '\0','Làm đẹp','lam-ep',12),(23,'https://media.hasaki.vn/catalog/product/g/o/google-shopping-vien-uong-dau-ca-pharmekal-omega-1000mg-100-vien_img_358x358_843626_fit_center.jpg',_binary '\0','Sức khỏe','suc-khoe',12);
/*!40000 ALTER TABLE `my_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news_app`
--

DROP TABLE IF EXISTS `news_app`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `news_app` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(4096) DEFAULT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news_app`
--

LOCK TABLES `news_app` WRITE;
/*!40000 ALTER TABLE `news_app` DISABLE KEYS */;
INSERT INTO `news_app` (`id`, `content`, `create_at`, `image`, `is_active`, `title`) VALUES (1,'Làm sao để ngăn ngừa và chữa trị hiệu quả nám da tại nhà mà không cần đến bác sĩ. Câu trả lời là Mỹ Phẩm Ngoại Nhập đã tìm thấy 5 sản phẩm viên uống \"giải cứu\" mặt tiền đầy nám tàn nhang “chất nhất quả đất”, được đánh giá tốt bởi các chuyên gia, tạp chí, các diễn đàn làm đẹp bởi người tiêu dùng.\r\n\r\nNám da gây ảnh hưởng không nhỏ đến cuộc sống của người phụ nữ, nhất là trong việc giao tiếp hàng ngày gây cản trở đến công việc, tình cảm và nhiều vấn đề cá nhân khác. Do đó trị nám là điều hết sức cần thiết đối với bất kỳ ai.\r\n\r\nVì vậy chị em tìm đủ mọi cách nhưng không thể nào loại bỏ nám một cách triệt để và nhanh chóng. Đi thẩm mỹ, sử dụng phương pháp laze nhưng hiệu quả đem lại chỉ được một thời gian ngắn và nám quay lại một cách nhanh chóng. Nhiều người cũng thử tìm mua các loại thuốc uống trị tàn nhang, thuốc uống trị nám hay các loại thuốc uống khác nhưng không được như ý. Dù đã thử mọi cách nhưng không đem lại hiệu quả lâu dài khiến cho chị em cảm thấy mệt mỏi, chán nản với làn da của mình không muốn đụng chạm gì đến nó nữa. Nhưng đó là một sai lầm mà các chị em đang mắc phải, chúng ta không nên từ bỏ chúng một cách dễ dàng như vậy. Làm đẹp cần một sự kiên trì và một phương pháp tốt.',NULL,'https://myphamngoainhap.vn/26_08_2014/files/CO/sua-rua-mat-tri-mun-cho-da-nhon-sakura-2(1).jpg',_binary '','TOP 5 VIÊN UỐNG TRỊ NÁM từ gốc SIÊU TỐC, da CĂNG MƯỚT, ĐÁNG UỐNG NHẤT 2023'),(4,'Nếu sở hữu làn da nhạy cảm, dễ kích ứng và luôn phải đối mặt với tình trạng mụn, viêm, bạn nên ghi nhớ ngay 4 bí quyết chăm sóc dưới đây\r\n\r\nMỗi người sẽ có một tính chất da riêng, do đó, chế độ chăm sóc, mỹ phẩm làm đẹp cũng khác nhau. Trong đó, làn da nhạy cảm là tính chất khó dưỡng nhất. Jessica Wu, bác sĩ da liễu ở Los Angeles chia sẻ, theo số liệu thống kê năm 1980, chỉ có 30% trong toàn dân số sở hữu làn da nhạy cảm, tuy nhiên, hiện nay con số này đã đã tăng lên 70%.\r\n\r\nMặc dù không có định nghĩa cụ thể về làn da nhạy cảm nhưng chúng thường có các dấu hiệu như mụn, đỏ, phát ban và khô ráp khi phản ứng với loại chất không phù hợp. \" Da nhạy cảm có nghĩa là bạn phải thường xuyên bị kích thích bởi nhiều thành phần phổ biến\" - Neal Schultz, MD, bác sĩ da liễu ở New York cho biết. \r\n\r\nNếu thường xuyên mắc phải một trong những dấu hiệu sau đây mỗi khi dùng phương pháp chăm sóc da hoặc thay đổi mỹ phẩm, bạn có thể đang sở hữu làn da nhạy cảm.\r\n\r\n','2023-08-17 19:48:31.895000','https://myphamngoainhap.vn/data/banner/1d565620c9b63c10da0ac4e64d01c3cf.jpg',_binary '','4 bí quyết chăm sóc từ chuyên gia cho làn da nhạy cảm sẽ không còn \'kén chọn\' và dễ kích ứng như trước'),(5,'Áp dụng quy trình chăm sóc da gồm 4 bước này mỗi buổi sáng, lão hóa sẽ bị đẩy lùi đến 10 năm giúp bạn sở hữu làn da trắng hồng, rạng rỡ thách thức thời gian.\r\n\r\nĐể đẩy lùi lão hóa da, bạn cần thực hiện các phương pháp chống quá trình oxy hóa tế bào ngay từ giai đoạn sau tuổi 20, chứ không phải đợi đến khi trên mặt bắt đầu xuất hiện nám, nếp nhăn... \r\n\r\nDưới đây là quy trình chăm sóc da góp phần đẩy lùi lão hóa hữu hiệu mà bạn có thể áp dụng ngay tại nhà mỗi buổi sáng.','2023-08-17 19:58:27.748000','https://myphamngoainhap.vn/media/tintuc/1541005200/small-815x358-mpnn.png',_binary '','KHUYẾN MÃI THÁNG 11: Tri ân Vàng - Ngàn ưu đãi');
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` (`id`, `address`, `create_at`, `email`, `name`, `note`, `phone_number`, `status`, `total_payment`, `user_id`) VALUES (4,'','2023-08-17 22:34:18.790000','','','','','Đặt hàng',277000.00,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` (`id`, `name`, `price`, `quantity`, `order_id`) VALUES (1,'Kem Nền Mỏng Nhẹ Pretty By Flormar 003 Light Ivory 30ml',277000.00,1,4);
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
  `url` varchar(1024) DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb4ct19237ff3iusxp73vsdcp2` (`product_id`),
  CONSTRAINT `FKb4ct19237ff3iusxp73vsdcp2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_images`
--

LOCK TABLES `product_images` WRITE;
/*!40000 ALTER TABLE `product_images` DISABLE KEYS */;
INSERT INTO `product_images` (`id`, `url`, `product_id`) VALUES (1,'https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-nuoc-tay-trang-tuoi-mat-l-oreal-3-in-1-danh-cho-da-dau-da-hon-hop-400ml-1684995866_img_358x358_843626_fit_center.jpg',11),(2,'https://media.hasaki.vn/catalog/product/n/u/nuoc-tay-trang-tuoi-mat-l-oreal-3-in-1-danh-cho-da-dau-da-hon-hop-400ml-1684996339_img_358x358_843626_fit_center.jpg',11),(3,'https://media.hasaki.vn/catalog/product/n/u/nuoc-tay-trang-tuoi-mat-l-oreal-3-in-1-danh-cho-da-dau-da-hon-hop-400ml-2-1684996339_img_358x358_843626_fit_center.jpg',11),(4,'https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-422204884-1689925661_img_358x358_843626_fit_center.png',12),(5,'https://media.hasaki.vn/catalog/product/g/o/google-shopping-combo-2-nuoc-tay-trang-bi-dao-cocoon-lam-sach-giam-dau-500ml-1689925610_img_358x358_843626_fit_center.jpg',12),(6,'https://media.hasaki.vn/rating/422204884168801560524490.jpg',12),(7,'https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-422204884-1689925661_img_358x358_843626_fit_center.png',NULL),(8,'https://media.hasaki.vn/catalog/product/g/o/google-shopping-combo-2-nuoc-tay-trang-bi-dao-cocoon-lam-sach-giam-dau-500ml-1689925610_img_358x358_843626_fit_center.jpg',NULL),(9,'https://media.hasaki.vn/rating/422204884168801560524490.jpg',NULL),(10,'https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-422204884-1689925661_img_358x358_843626_fit_center.png',12),(11,'https://media.hasaki.vn/catalog/product/g/o/google-shopping-combo-2-nuoc-tay-trang-bi-dao-cocoon-lam-sach-giam-dau-500ml-1689925610_img_358x358_843626_fit_center.jpg',12),(12,'https://media.hasaki.vn/rating/422204884168801560524490.jpg',12),(13,'https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-422204884-1689925661_img_358x358_843626_fit_center.png',NULL),(14,'https://media.hasaki.vn/catalog/product/g/o/google-shopping-combo-2-nuoc-tay-trang-bi-dao-cocoon-lam-sach-giam-dau-500ml-1689925610_img_358x358_843626_fit_center.jpg',NULL),(15,'https://media.hasaki.vn/rating/422204884168801560524490.jpg',NULL),(16,'https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-422204884-1689925661_img_358x358_843626_fit_center.png',12),(17,'https://media.hasaki.vn/catalog/product/g/o/google-shopping-combo-2-nuoc-tay-trang-bi-dao-cocoon-lam-sach-giam-dau-500ml-1689925610_img_358x358_843626_fit_center.jpg',12),(18,'https://media.hasaki.vn/rating/422204884168801560524490.jpg',12),(19,'https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-422204884-1689925661_img_358x358_843626_fit_center.png',NULL),(20,'https://media.hasaki.vn/catalog/product/g/o/google-shopping-combo-2-nuoc-tay-trang-bi-dao-cocoon-lam-sach-giam-dau-500ml-1689925610_img_358x358_843626_fit_center.jpg',NULL),(21,'https://media.hasaki.vn/rating/422204884168801560524490.jpg',NULL),(22,'https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-422204884-1689925661_img_358x358_843626_fit_center.png',12),(23,'https://media.hasaki.vn/catalog/product/g/o/google-shopping-combo-2-nuoc-tay-trang-bi-dao-cocoon-lam-sach-giam-dau-500ml-1689925610_img_358x358_843626_fit_center.jpg',12),(24,'https://media.hasaki.vn/rating/422204884168801560524490.jpg',12),(25,'',13),(26,'',13),(27,'',13),(28,'',14),(29,'',14),(30,'',14),(31,'',15),(32,'',15),(33,'',15),(34,'',16),(35,'',16),(36,'',16),(37,'',17),(38,'',17),(39,'',17),(40,'',18),(41,'',18),(42,'',18),(43,'',19),(44,'',19),(45,'',19),(46,'',20),(47,'',20),(48,'',20),(49,'',21),(50,'',21),(51,'',21),(52,'',22),(53,'',22),(54,'',22),(55,'',23),(56,'',23),(57,'',23),(58,'',24),(59,'',24),(60,'',24),(61,'',25),(62,'',25),(63,'',25),(64,'',NULL),(65,'',NULL),(66,'',NULL),(67,'',25),(68,'',25),(69,'',25),(70,'',26),(71,'',26),(72,'',26),(73,'',27),(74,'',27),(75,'',27),(76,'',28),(77,'',28),(78,'',28),(79,'',29),(80,'',29),(81,'',29),(82,'',30),(83,'',30),(84,'',30),(85,'',31),(86,'',31),(87,'',31),(88,'',32),(89,'',32),(90,'',32),(91,'',33),(92,'',33),(93,'',33),(94,'',34),(95,'',34),(96,'',34),(97,'',35),(98,'',35),(99,'',35),(100,'',36),(101,'',36),(102,'',36),(103,'',NULL),(104,'',NULL),(105,'',NULL),(106,'',16),(107,'',16),(108,'',16),(109,'',NULL),(110,'',NULL),(111,'',NULL),(112,'',35),(113,'',35),(114,'',35);
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
  `main_image` varchar(1024) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `percent_discount` double DEFAULT NULL,
  `price` decimal(12,3) DEFAULT NULL,
  `quantity_left` int DEFAULT NULL,
  `quantity_sold` int DEFAULT NULL,
  `short_description` varchar(4096) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6keev632w4hq6g5dfm2rpw5mo` (`category_id`),
  CONSTRAINT `FK6keev632w4hq6g5dfm2rpw5mo` FOREIGN KEY (`category_id`) REFERENCES `my_categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` (`id`, `brand`, `date_release`, `full_description`, `is_active`, `main_image`, `name`, `percent_discount`, `price`, `quantity_left`, `quantity_sold`, `short_description`, `slug`, `category_id`) VALUES (11,'L\'OREAL','2023-08-17 20:46:33.498000','<div class=\"tt_box_detail width_common\" style=\"width: 1006px; float: left; font-weight: 700; font-size: 18px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: Roboto, sans-serif;\">Thành phần sản phẩm</div><div class=\"ct_box_detail width_common\" style=\"width: 1006px; float: left; font-family: arial, helvetica, sans-serif; font-size: 13px;\"><div class=\"width_common\" style=\"width: 1006px; float: left;\"><h3 style=\"font-weight: bolder; line-height: 1.8; margin-right: 0px; margin-bottom: 11px; margin-left: 0px; font-size: 15px !important;\"><strong style=\"font-weight: bold;\">1. L\'Oréal Paris&nbsp;Micellar Water 3-in-1 Deep Cleansing Even For Sensitive Skin (Xanh dương đậm)</strong></h3><p style=\"margin-right: 0px; margin-bottom: 10px; margin-left: 0px;\"><strong style=\"font-weight: bold;\">Thành phần chính:</strong></p><ul style=\"margin-bottom: 10px;\"><li><p style=\"margin-right: 0px; margin-bottom: 10px; margin-left: 0px;\">Có&nbsp;<strong style=\"font-weight: bold;\">hai lớp chất lỏng</strong>&nbsp;giúp hòa tan chất bẩn và loại bỏ toàn bộ lớp trang điểm hiệu quả, kể cả lớp trang điểm lâu trôi và không thấm nước chỉ trong một bước.</p></li><li><p style=\"margin-right: 0px; margin-bottom: 10px; margin-left: 0px;\"><strong style=\"font-weight: bold;\">Lớp Micellar</strong>&nbsp;chứa các hạt mixen siêu nhỏ len lỏi sâu và dễ dàng lấy đi bụi bẩn, dầu thừa, lớp trang điểm và chất bẩn khác mà không làm khô da.</p></li></ul><p style=\"margin-right: 0px; margin-bottom: 10px; margin-left: 0px;\"><strong style=\"font-weight: bold;\">Thành phần đầy đủ:&nbsp;</strong>Aqua / Water, Cyclopentasiloxane, Isohexadecane, Potassium Phosphate, Sodium Chloride, Dipotassium Phosphate, Disodium Edta, Decyl Glucoside, Hexylene Glycol, Polyaminopropyl Biguanide, CI 61565 / Green 6</p><h3 style=\"font-weight: bolder; line-height: 1.8; margin-right: 0px; margin-bottom: 11px; margin-left: 0px; font-size: 15px !important;\"><strong style=\"font-weight: bold;\">2. L\'Oréal Paris&nbsp;Micellar Water 3-in-1 Refreshing Even For Sensitive Skin&nbsp;(Xanh dương nhạt)</strong></h3><p style=\"margin-right: 0px; margin-bottom: 10px; margin-left: 0px;\"><strong style=\"font-weight: bold;\">Thành phần chính:</strong></p><ul style=\"margin-bottom: 10px;\"><li><p style=\"margin-right: 0px; margin-bottom: 10px; margin-left: 0px;\"><strong style=\"font-weight: bold;\">Nước khoáng lấy từ những ngọn núi ở Pháp:</strong>&nbsp;làm dịu và giúp làn da trông tươi tắn lên sau khi tẩy trang.</p></li></ul><p style=\"margin-right: 0px; margin-bottom: 10px; margin-left: 0px;\"><strong style=\"font-weight: bold;\">Thành phần đầy đủ:&nbsp;</strong>Aqua / Water, Hexylene Glycol, Glycerin, Poloxamer 184, Disodium Cocoamphodiacetate, Disodium Edta, BHT , Polyaminopropyl Biguanide</p><h3 style=\"font-weight: bolder; line-height: 1.8; margin-right: 0px; margin-bottom: 11px; margin-left: 0px; font-size: 15px !important;\"><strong style=\"font-weight: bold;\">3. L\'Oréal Paris&nbsp;Micellar Water 3-in-1 Moisturizing Even For Sensitive Skin (Hồng)</strong></h3><p style=\"margin-right: 0px; margin-bottom: 10px; margin-left: 0px;\"><strong style=\"font-weight: bold;\">Thành phần chính:</strong></p><ul style=\"margin-bottom: 10px;\"><li><p style=\"margin-right: 0px; margin-bottom: 10px; margin-left: 0px;\"><strong style=\"font-weight: bold;\">Chiết xuất hoa hồng Pháp</strong>: cân bằng độ ẩm &amp; làm mềm mịn da, làn da vẫn đủ độ ẩm sau khi tẩy trang.</p></li></ul><p style=\"margin-right: 0px; margin-bottom: 10px; margin-left: 0px;\"><strong style=\"font-weight: bold;\">Thành phần đầy đủ:&nbsp;</strong>Aqua / Water, Hexylene Glycol, Glycerin, Rosa Gallica Flower Extract, Sorbitol, Poloxamer 184, Disodium Cocoamphodiacetate, Disodium Edta, Propylene Glycol, BHT , Polyaminopropyl Biguanide</p></div></div>',_binary '','https://media.hasaki.vn/catalog/product/p/r/promotions-auto-nuoc-tay-trang-tuoi-mat-l-oreal-3-in-1-danh-cho-da-dau-da-hon-hop-400ml_u2Qw2XBXFKR8xgUY_img_358x358_843626_fit_center.png','Nước Tẩy Trang L\'Oreal Tươi Mát Cho Da Dầu',10,159000.000,200,11,'<table class=\"tb_info_sanpham\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"border-spacing: 0px; width: 1006px; font-family: arial, helvetica, sans-serif; font-size: 13px;\"><tbody><tr><td class=\"col_tb_info_sp bg_info_sp\" style=\"padding: 7px 20px; border-color: rgb(234, 234, 234); width: 301.5px; background: rgb(247, 247, 247);\">Barcode</td><td class=\"barcode-content\" style=\"padding: 7px 20px; border-color: rgb(234, 234, 234);\">6928820030226</td></tr><tr><td class=\"col_tb_info_sp bg_info_sp\" style=\"padding: 7px 20px; border-color: rgb(234, 234, 234); width: 301.5px; background: rgb(247, 247, 247);\">Thương Hiệu</td><td style=\"padding: 7px 20px; border-color: rgb(234, 234, 234);\">L\'OREAL</td></tr><tr><td class=\"col_tb_info_sp bg_info_sp\" style=\"padding: 7px 20px; border-color: rgb(234, 234, 234); width: 301.5px; background: rgb(247, 247, 247);\">Xuất xứ thương hiệu</td><td style=\"padding: 7px 20px; border-color: rgb(234, 234, 234);\">Pháp</td></tr><tr><td class=\"col_tb_info_sp bg_info_sp\" style=\"padding: 7px 20px; border-color: rgb(234, 234, 234); width: 301.5px; background: rgb(247, 247, 247);\">Nơi sản xuất</td><td style=\"padding: 7px 20px; border-color: rgb(234, 234, 234);\">China</td></tr><tr><td class=\"col_tb_info_sp bg_info_sp\" style=\"padding: 7px 20px; border-color: rgb(234, 234, 234); width: 301.5px; background: rgb(247, 247, 247);\">Loại da</td><td style=\"padding: 7px 20px; border-color: rgb(234, 234, 234);\">Da dầu</td></tr><tr><td class=\"col_tb_info_sp bg_info_sp\" style=\"padding: 7px 20px; border-color: rgb(234, 234, 234); width: 301.5px; background: rgb(247, 247, 247);\">Giới tính</td><td style=\"padding: 7px 20px; border-color: rgb(234, 234, 234);\">Nam &amp; nữ</td></tr><tr><td class=\"col_tb_info_sp bg_info_sp\" style=\"padding: 7px 20px; border-color: rgb(234, 234, 234); width: 301.5px; background: rgb(247, 247, 247);\">Vấn đề về da</td><td style=\"padding: 7px 20px; border-color: rgb(234, 234, 234);\">Da dầu, lỗ chân lông to</td></tr></tbody></table>','nuoc-tay-trang-loreal-tuoi-mat-cho-da-dau',1),(12,'Cocoon','2023-08-17 21:05:41.726000','<div class=\"tt_box_detail width_common\" style=\"width: 1006px; float: left; font-weight: 700; font-size: 18px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: Roboto, sans-serif;\">Thành phần sản phẩm</div><div class=\"ct_box_detail width_common\" style=\"width: 1006px; float: left; font-family: arial, helvetica, sans-serif; font-size: 13px;\"><div class=\"width_common\" style=\"width: 1006px; float: left;\"><p style=\"margin-right: 0px; margin-bottom: 10px; margin-left: 0px;\"><strong style=\"font-weight: bold;\">Thành phần chính:</strong></p><ul style=\"margin-bottom: 10px;\"><li><p style=\"margin-right: 0px; margin-bottom: 10px; margin-left: 0px;\"><strong style=\"font-weight: bold;\">Bí đao:</strong>&nbsp;làm mát, làm giảm nhiệt, kháng viêm và diệt khuẩn giúp giảm tình trạng mụn trứng cá, mụn viêm.</p></li><li><p style=\"margin-right: 0px; margin-bottom: 10px; margin-left: 0px;\"><strong style=\"font-weight: bold;\">Rau má:&nbsp;</strong>tăng sinh collagen cho làn da, kháng viêm, làm dịu các vết đỏ và giảm kích ứng.</p></li><li><p style=\"margin-right: 0px; margin-bottom: 10px; margin-left: 0px;\"><strong style=\"font-weight: bold;\">Tràm trà:</strong>&nbsp;giảm mụn trứng cá, vết thương, côn trùng cắn.</p></li><li><p style=\"margin-right: 0px; margin-bottom: 10px; margin-left: 0px;\"><strong style=\"font-weight: bold;\">Betaine:</strong>&nbsp;có tác dụng bảo vệ tế bào da khỏi căng thẳng từ môi trường bên ngoài như bức xạ UV, ô nhiễm môi trường và tăng khả năng giữ ẩm cho da dưới tác động của nhiệt độ và khói bụi.</p></li><li><p style=\"margin-right: 0px; margin-bottom: 10px; margin-left: 0px;\"><strong style=\"font-weight: bold;\">o-cymen-5-ol</strong>&nbsp;và&nbsp;<strong style=\"font-weight: bold;\">cetylpyridinium chloride</strong>&nbsp;có khả năng kháng khuẩn và diệt khuẩn gây mụn.</p></li></ul><p style=\"margin-right: 0px; margin-bottom: 10px; margin-left: 0px;\"><strong style=\"font-weight: bold;\">Thành phần đầy đủ:</strong></p><p style=\"margin-right: 0px; margin-bottom: 10px; margin-left: 0px;\"><strong style=\"font-weight: bold;\"></strong>Aqua/Water, Polyglyceryl-4 Laurate/Sebacate, Polyglyceryl-4 Caprylate/Caprate, Betaine, Benincasa Cerifera Fruit Extract, Centella Asiatica Extract, o-Cymen-5-ol, Propanediol, Glycereth-26, Glycerin, Trisodium Ethylenediamine Disuccinate, Sodium Lactate, Cetylpyridinium Chloride, Melaleuca Alternifolia Leaf Oil, Lactic Acid.</p></div></div>',_binary '','https://media.hasaki.vn/catalog/product/p/r/promotions-auto-combo-2-nuoc-tay-trang-bi-dao-cocoon-lam-sach-giam-dau-500ml_UScsziNsWssqWr8A_img_358x358_843626_fit_center.png','Combo 2 Nước Tẩy Trang Bí Đao Cocoon Làm Sạch & Giảm Dầu 500ml',1,465000.000,13,23,'<table class=\"tb_info_sanpham\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"border-spacing: 0px; width: 1006px; font-family: arial, helvetica, sans-serif; font-size: 13px;\"><tbody><tr><td class=\"col_tb_info_sp bg_info_sp\" style=\"padding: 7px 20px; border-color: rgb(234, 234, 234); width: 301.5px; background: rgb(247, 247, 247);\">Thương Hiệu</td><td style=\"padding: 7px 20px; border-color: rgb(234, 234, 234);\">Cocoon</td></tr><tr><td class=\"col_tb_info_sp bg_info_sp\" style=\"padding: 7px 20px; border-color: rgb(234, 234, 234); width: 301.5px; background: rgb(247, 247, 247);\">Xuất xứ thương hiệu</td><td style=\"padding: 7px 20px; border-color: rgb(234, 234, 234);\">Việt Nam</td></tr><tr><td class=\"col_tb_info_sp bg_info_sp\" style=\"padding: 7px 20px; border-color: rgb(234, 234, 234); width: 301.5px; background: rgb(247, 247, 247);\">Nơi sản xuất</td><td style=\"padding: 7px 20px; border-color: rgb(234, 234, 234);\">Vietnam</td></tr><tr><td class=\"col_tb_info_sp bg_info_sp\" style=\"padding: 7px 20px; border-color: rgb(234, 234, 234); width: 301.5px; background: rgb(247, 247, 247);\">Loại da</td><td style=\"padding: 7px 20px; border-color: rgb(234, 234, 234);\">Da dầu</td></tr><tr><td class=\"col_tb_info_sp bg_info_sp\" style=\"padding: 7px 20px; border-color: rgb(234, 234, 234); width: 301.5px; background: rgb(247, 247, 247);\">Giới tính</td><td style=\"padding: 7px 20px; border-color: rgb(234, 234, 234);\">Nam &amp; nữ</td></tr><tr><td class=\"col_tb_info_sp bg_info_sp\" style=\"padding: 7px 20px; border-color: rgb(234, 234, 234); width: 301.5px; background: rgb(247, 247, 247);\">Vấn đề về da</td><td style=\"padding: 7px 20px; border-color: rgb(234, 234, 234);\">Da dầu, lỗ chân lông to</td></tr></tbody></table>','combo-2-nuoc-tay-trang-bi-ao-cocoon-lam-sach--giam-dau-500ml',13),(13,'Simple','2023-08-17 21:07:46.558000','<div class=\"tt_box_detail width_common\" style=\"width: 1006px; float: left; font-weight: 700; font-size: 18px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: Roboto, sans-serif;\">Thành phần sản phẩm</div><div class=\"ct_box_detail width_common\" style=\"width: 1006px; float: left; font-family: arial, helvetica, sans-serif; font-size: 13px;\"><div class=\"width_common\" style=\"width: 1006px; float: left;\"><p style=\"margin-right: 0px; margin-bottom: 10px; margin-left: 0px;\"><strong style=\"font-weight: bold;\">Thành phần chính:&nbsp;</strong></p><ul style=\"margin-bottom: 10px;\"><li><p style=\"margin-right: 0px; margin-bottom: 10px; margin-left: 0px;\"><strong style=\"font-weight: bold;\">Công nghệ làm sạch Micellar:</strong>&nbsp;với hàng triệu phân tử Micelles thông minh giúp nhẹ nhàng làm sạch bụi bẩn, dầu thừa trên da, lớp trang điểm kể cả sản phẩm chống thấm nước.</p></li><li><p style=\"margin-right: 0px; margin-bottom: 10px; margin-left: 0px;\"><strong style=\"font-weight: bold;\">Hoa cúc La Mã Chamomile</strong>&nbsp;giúp làm dịu và phục hồi da hiệu quả.</p></li><li><p style=\"margin-right: 0px; margin-bottom: 10px; margin-left: 0px;\"><strong style=\"font-weight: bold;\">Nước tinh khiết được lọc 3 lần:&nbsp;</strong>cực kì dịu nhẹ và an toàn cho làn da nhạy cảm, hỗ trợ làm dịu da.</p></li><li><p style=\"margin-right: 0px; margin-bottom: 10px; margin-left: 0px;\"><strong style=\"font-weight: bold;\">Vitamin B3 và Vitamin C:</strong>&nbsp;có khả năng chống oxy hóa, làm dịu da, giảm viêm sưng và dưỡng da sáng mịn màng.</p></li></ul><p style=\"margin-right: 0px; margin-bottom: 10px; margin-left: 0px;\"><strong style=\"font-weight: bold;\">Thành phần đầy đủ:</strong></p><p style=\"margin-right: 0px; margin-bottom: 10px; margin-left: 0px;\">Aqua, Hexylene Glycol, Glycerin, PEG-6 Caprylic/Capric Glycerides, Phenoxyethanol, Cetrimonium Chloride, Tetrasodium EDTA,Propylene Glycol, Citric Acid, Cetylpyridinium Chloride, Sodium Chloride, Niacinamide, Sodium Ascorbyl, Phosphate, Potassium chloride, Panthenol.</p></div></div>',_binary '','https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-nuoc-tay-trang-simple-lam-sach-trang-diem-va-cap-am-400ml-1676455797_img_358x358_843626_fit_center.jpg','Nước Tẩy Trang Simple Làm Sạch Trang Điểm Vượt Trội 400ml',12,131000.000,2,2,'<table class=\"tb_info_sanpham\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"border-spacing: 0px; width: 1006px; font-family: arial, helvetica, sans-serif; font-size: 13px;\"><tbody><tr><td class=\"col_tb_info_sp bg_info_sp\" style=\"padding: 7px 20px; border-color: rgb(234, 234, 234); width: 301.5px; background: rgb(247, 247, 247);\">Barcode</td><td class=\"barcode-content\" style=\"padding: 7px 20px; border-color: rgb(234, 234, 234);\">8710908371509</td></tr><tr><td class=\"col_tb_info_sp bg_info_sp\" style=\"padding: 7px 20px; border-color: rgb(234, 234, 234); width: 301.5px; background: rgb(247, 247, 247);\">Thương Hiệu</td><td style=\"padding: 7px 20px; border-color: rgb(234, 234, 234);\">Simple</td></tr><tr><td class=\"col_tb_info_sp bg_info_sp\" style=\"padding: 7px 20px; border-color: rgb(234, 234, 234); width: 301.5px; background: rgb(247, 247, 247);\">Xuất xứ thương hiệu</td><td style=\"padding: 7px 20px; border-color: rgb(234, 234, 234);\">Anh</td></tr><tr><td class=\"col_tb_info_sp bg_info_sp\" style=\"padding: 7px 20px; border-color: rgb(234, 234, 234); width: 301.5px; background: rgb(247, 247, 247);\">Nơi sản xuất</td><td style=\"padding: 7px 20px; border-color: rgb(234, 234, 234);\">Poland</td></tr><tr><td class=\"col_tb_info_sp bg_info_sp\" style=\"padding: 7px 20px; border-color: rgb(234, 234, 234); width: 301.5px; background: rgb(247, 247, 247);\">Loại da</td><td style=\"padding: 7px 20px; border-color: rgb(234, 234, 234);\">Da nhạy cảm</td></tr><tr><td class=\"col_tb_info_sp bg_info_sp\" style=\"padding: 7px 20px; border-color: rgb(234, 234, 234); width: 301.5px; background: rgb(247, 247, 247);\">Đặc Tính</td><td style=\"padding: 7px 20px; border-color: rgb(234, 234, 234);\">Không Cồn</td></tr><tr><td class=\"col_tb_info_sp bg_info_sp\" style=\"padding: 7px 20px; border-color: rgb(234, 234, 234); width: 301.5px; background: rgb(247, 247, 247);\">Giới tính</td><td style=\"padding: 7px 20px; border-color: rgb(234, 234, 234);\">Nam &amp; nữ</td></tr><tr><td class=\"col_tb_info_sp bg_info_sp\" style=\"padding: 7px 20px; border-color: rgb(234, 234, 234); width: 301.5px; background: rgb(247, 247, 247);\">Vấn đề về da</td><td style=\"padding: 7px 20px; border-color: rgb(234, 234, 234);\">Da nhạy cảm, kích ứng, mẩn đỏ</td></tr></tbody></table>','nuoc-tay-trang-simple-lam-sach-trang-iem-vuot-troi-400ml',14),(14,'Cetaphil','2023-08-17 21:09:28.349000','',_binary '','https://media.hasaki.vn/catalog/product/p/r/promotions-auto-sua-rua-mat-cetaphil-diu-nhe-khong-xa-phong-500ml-moi_aWPSWjgzd9nf8sqD_img_358x358_843626_fit_center.png','Sữa Rửa Mặt Cetaphil Dịu Lành Cho Da Nhạy Cảm 500ml (Mới)',7,339000.000,23,23,'','sua-rua-mat-cetaphil-diu-lanh-cho-da-nhay-cam-500ml-moi',13),(15,' Hada Labo','2023-08-17 21:12:08.543000','',_binary '','https://media.hasaki.vn/catalog/product/k/e/kem-rua-mat-hada-labo-duong-am-toi-uu-80g-1-1668589283_img_358x358_843626_fit_center.jpg','Kem Rửa Mặt Hada Labo Sạch Sâu Dưỡng Ẩm 80g Advanced Nourish Hyaluronic Acid Cleanser',0,66000.000,1,2,'','kem-rua-mat-hada-labo-sach-sau-duong-am-80g-advanced-nourish-hyaluronic-acid-cleanser',14),(16,'Cocoon','2023-08-17 21:13:34.352000','',_binary '','https://media.hasaki.vn/catalog/product/t/o/top_fb_ads_422209262_030823-1691030244_img_358x358_843626_fit_center.jpg','Tẩy Tế Bào Chết Da Mặt Cocoon Cà Phê Đắk Lắk 150ml',44,148000.000,41,45,'','tay-te-bao-chet-da-mat-cocoon-ca-phe-ak-lak-150ml',15),(17,'Pretty by Flormar','2023-08-17 21:14:38.892000','',_binary '','https://media.hasaki.vn/catalog/product/k/e/kem-nen-pretty-by-flormar-003-light-ivory-30ml_img_358x358_843626_fit_center.jpg','Kem Nền Mỏng Nhẹ Pretty By Flormar 003 Light Ivory 30ml',0,277000.000,2,35,'','kem-nen-mong-nhe-pretty-by-flormar-003-light-ivory-30ml',16),(18,'Silkygirl','2023-08-17 21:15:39.444000','',_binary '','https://media.hasaki.vn/catalog/product/g/o/google-shopping-kem-lot-che-lo-chan-long-silkygirl-mau-01-natural-15ml_img_358x358_843626_fit_center.jpg','Kem Lót Che Lỗ Chân Lông SILKYGIRL Màu 01 Natural 15ml',0,149000.000,2,2,'','kem-lot-che-lo-chan-long-silkygirl-mau-01-natural-15ml',17),(19,'L\'OREAL','2023-08-17 21:16:26.548000','',_binary '','https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-kem-lot-l-oreal-infallible-kiem-dau-ben-mau-lau-troi-30ml-1689310686_img_358x358_843626_fit_center.jpg','Kem Lót L\'Oréal Infallible Kiềm Dầu Bền Màu Lâu Trôi 30ml',0,99000.000,2,2,'','kem-lot-loreal-infallible-kiem-dau-ben-mau-lau-troi-30ml',17),(20,'HATOMUGI','2023-08-17 21:17:12.826000','',_binary '','https://media.hasaki.vn/catalog/product/g/o/google-shopping-sua-tam-hatomugi-duong-sang-da-800ml-1683085255_img_358x358_843626_fit_center.jpg','Sữa Tắm Hatomugi Dưỡng Ẩm, Sáng Da Chiết Xuất Ý Dĩ 800ml',2,93000.000,45,54,'','sua-tam-hatomugi-duong-am-sang-da-chiet-xuat-y-di-800ml',19),(21,'ON:THE BODY','2023-08-17 21:17:53.163000','',_binary '','https://media.hasaki.vn/catalog/product/p/r/promotions-auto-sua-tam-on-the-body-huong-nuoc-hoa-violet-dream-1000g_CswDmfgG8GbsZCB5_img_358x358_843626_fit_center.png','Sữa Tắm On: The Body Dưỡng Ẩm Hương Quyến Rũ 1000g',10,222000.000,3,3,'','sua-tam-on-the-body-duong-am-huong-quyen-ru-1000g',1),(22,'Lifebuoy','2023-08-17 21:18:34.734000','',_binary '','https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-sua-tam-lifebuoy-detox-matcha-kho-qua-800g-1676617700_img_358x358_843626_fit_center.jpg','Sữa Tắm Lifebuoy Detox Matcha & Khổ Qua 800g',0,445000.000,2,2,'','sua-tam-lifebuoy-detox-matcha--kho-qua-800g',19),(23,'THE BODY','2023-08-17 21:19:24.579000','',_binary '','https://media.hasaki.vn/catalog/product/p/r/promotions-auto-sua-tam-on-the-body-huong-nuoc-hoa-white-pearl-1000g_kGoCGdFLWJat6CEg_img_358x358_843626_fit_center.png','Sữa Tắm On: The Body Dưỡng Ẩm Hương Thư Giãn 1000g',0,533000.000,2,2,'','sua-tam-on-the-body-duong-am-huong-thu-gian-1000g',1),(24,'DHC','2023-08-17 21:20:22.153000','',_binary '','https://media.hasaki.vn/catalog/product/p/r/promotions-auto-collagen-nuoc-dhc-collagen-beauty-7000-plus-50mlx10_ajq7gJaSYW6xKxxw_img_358x358_843626_fit_center.png','Nước Uống Collagen DHC Dưỡng Da Căng Mịn, Ngừa Lão Hoá 10 Lọ',2,220700.000,2,2,'','nuoc-uong-collagen-dhc-duong-da-cang-min-ngua-lao-hoa-10-lo',10),(25,'Tesori d\'Oriente','2023-08-17 21:21:02.132000','',_binary '','https://media.hasaki.vn/catalog/product/p/r/promotions-auto-sua-tam-nuoc-hoa-tesori-d-oriente-huong-hoa-sen-500ml_fH6xGuRper8B9CP6_img_358x358_843626_fit_center.png','Sữa Tắm Nước Hoa Tesori d\'Oriente Hương Hoa Sen 500ml',0,466000.000,2,2,'','sua-tam-nuoc-hoa-tesori-doriente-huong-hoa-sen-500ml',19),(26,'DHC','2023-08-17 21:22:46.295000','',_binary '','https://media.hasaki.vn/catalog/product/p/r/promotions-auto-vien-uong-dhc-zinc-bo-sung-kem-goi-30-vien-30-ngay_hWNNpSbC7z3UScKn_img_358x358_843626_fit_center.png','Viên Uống DHC Zinc Bổ Sung Kẽm Gói 30 Viên 30 Ngày',0,333000.000,1,3,'','vien-uong-dhc-zinc-bo-sung-kem-goi-30-vien-30-ngay',22),(27,'ITOH KANPO','2023-08-17 21:23:25.819000','',_binary '','https://media.hasaki.vn/catalog/product/g/o/google-shopping-nuoc-uong-bo-sung-collagen-naris-itoh-50ml-x-10-lo-1_img_358x358_843626_fit_center.jpg','Nước Uống Bổ Sung Collagen Naris ITOH 50ml x 10 Lọ',20,666000.000,2,2,'','nuoc-uong-bo-sung-collagen-naris-itoh-50ml-x-10-lo',1),(28,'BLACKMORES','2023-08-17 21:24:16.024000','',_binary '','https://media.hasaki.vn/catalog/product/p/r/promotions-auto-vien-uong-dau-ca-blackmores-90-vien_7jTSWKvwr4KpQLTN_img_358x358_843626_fit_center.png','Viên Uống Blackmores Dầu Cá Bổ Sung Omega Lọ 90 Viên',0,666.000,2,2,'','vien-uong-blackmores-dau-ca-bo-sung-omega-lo-90-vien',23),(29,'Nước Hoa Nam Versace Eros EDP 100ml','2023-08-17 21:25:32.085000','',_binary '','https://media.hasaki.vn/catalog/product/n/u/nuoc-hoa-nam-versace-eros-eau-de-parfum-100ml-1-1654917811_img_358x358_843626_fit_center.jpg','Nước Hoa Nam Versace Eros EDP 100ml',0,999000.000,2,2,'','nuoc-hoa-nam-versace-eros-edp-100ml',11),(30,'Diamond','2023-08-17 21:26:07.361000','',_binary '','https://media.hasaki.vn/catalog/product/p/r/promotions-auto-nuoc-hoa-nam-laura-anne-diamond-homme-45ml_Gib9jmeP5H6mShC7_img_358x358_843626_fit_center.png',' Nước Hoa Nam Diamond Homme 45ml (Đen)',0,666000.000,2,2,'','-nuoc-hoa-nam-diamond-homme-45ml-en',1),(31,'AXE','2023-08-17 21:26:51.422000','',_binary '','https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-xit-nuoc-hoa-toan-than-axe-cho-nam-gold-temptation-135ml-1676876863_img_358x358_843626_fit_center.jpg','Xịt Khử Mùi AXE Cho Nam Hương Nước Hoa Gold Temptation 135ml',0,666000.000,2,2,'','xit-khu-mui-axe-cho-nam-huong-nuoc-hoa-gold-temptation-135ml',1),(32,'Milaganics','2023-08-17 21:27:59.711000','',_binary '','https://media.hasaki.vn/catalog/product/p/r/promotions-auto-bot-dau-do-milaganics-100g_7BH77F2LZJUkbqb6_img_358x358_843626_fit_center.png','Bột Đậu Đỏ Milaganics Ngừa Mụn, Dưỡng Sáng Da 100g (Túi)',9,666000.000,2,2,'','bot-au-o-milaganics-ngua-mun-duong-sang-da-100g-tui',15),(33,'Derladie','2023-08-17 21:28:49.445000','',_binary '','https://media.hasaki.vn/catalog/product/p/r/promotions-auto-kem-duong-derladie-giam-mun-va-kiem-dau-12-gio-50ml_285FEKVqYct4TQ71_img_358x358_843626_fit_center.png','Kem Dưỡng Derladie Giảm Mụn Và Kiềm Dầu 12 Giờ 50ml',0,666000.000,3,3,'','kem-duong-derladie-giam-mun-va-kiem-dau-12-gio-50ml',15),(34,'ST.IVES','2023-08-17 21:29:35.706000','',_binary '','https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-sua-rua-mat-tay-te-bao-chet-qua-mo-st-ives-170g-1679385292_img_358x358_843626_fit_center.jpg','Sữa Rửa Mặt St.Ives Tẩy Tế Bào Chết Trái Mơ Tươi Mát 170g',0,3.000,3,5,'','sua-rua-mat-stives-tay-te-bao-chet-trai-mo-tuoi-mat-170g',1),(35,'Arrahan','2023-08-17 21:30:07.704000','',_binary '','https://media.hasaki.vn/catalog/product/g/o/google-shopping-gel-tay-te-bao-chet-arrahan-chiet-xuat-thao-moc-180ml_img_358x358_843626_fit_center.jpg','Gel tẩy tế bào chết 903',0,344000.000,34,34,'','gel-tay-te-bao-chet-903',1),(36,'ST.IVES','2023-08-17 21:30:57.484000','',_binary '','https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-tay-te-bao-chet-st-ives-chiet-xuat-bo-va-mat-ong-170g-1679383751_img_358x358_843626_fit_center.jpg','Sữa Rửa Mặt St.Ives Tẩy Tế Bào Chết Bơ & Mật Ong 170g',10,440000.000,234,2,'','sua-rua-mat-stives-tay-te-bao-chet-bo--mat-ong-170g',1);
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
INSERT INTO `users` (`id`, `email`, `enabled`, `full_name`, `password`) VALUES (1,'luctuankietkg@gmail.com',_binary '','Tuan Kiet','$2a$10$oA/QjlhApJbmh1fGE1unXOjs/neT2kNdbU5xUkJOVWU/rOWYiiuIe'),(2,'stellaprimo99@gmail.com',_binary '','Duc Cong','$2a$10$.kGwpEi8yEl/7xLHsBeof.NkufmOwRLehM/t9AwImAznPdHlXkJDC');
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

-- Dump completed on 2023-08-17 22:56:18
