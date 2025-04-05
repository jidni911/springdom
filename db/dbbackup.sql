/*
SQLyog Community v12.4.0 (64 bit)
MySQL - 8.0.40 : Database - departmentofmathematics
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`departmentofmathematics` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `departmentofmathematics`;

/*Table structure for table `class_room` */

DROP TABLE IF EXISTS `class_room`;

CREATE TABLE `class_room` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `class_room` */

/*Table structure for table `class_room_invited_students` */

DROP TABLE IF EXISTS `class_room_invited_students`;

CREATE TABLE `class_room_invited_students` (
  `class_room_id` bigint NOT NULL,
  `invited_students_id` bigint NOT NULL,
  KEY `FKgbxk0ne43l6ey9c8e6795xlw` (`invited_students_id`),
  KEY `FKqtud8kblnftu79is6bkwmdwrj` (`class_room_id`),
  CONSTRAINT `FKgbxk0ne43l6ey9c8e6795xlw` FOREIGN KEY (`invited_students_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKqtud8kblnftu79is6bkwmdwrj` FOREIGN KEY (`class_room_id`) REFERENCES `class_room` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `class_room_invited_students` */

/*Table structure for table `class_room_requesting_students` */

DROP TABLE IF EXISTS `class_room_requesting_students`;

CREATE TABLE `class_room_requesting_students` (
  `class_room_id` bigint NOT NULL,
  `requesting_students_id` bigint NOT NULL,
  KEY `FKo4fht86my0xrr5tvf8cf6s96l` (`requesting_students_id`),
  KEY `FKjyu89xyeb3fxobiu9r9guvt8p` (`class_room_id`),
  CONSTRAINT `FKjyu89xyeb3fxobiu9r9guvt8p` FOREIGN KEY (`class_room_id`) REFERENCES `class_room` (`id`),
  CONSTRAINT `FKo4fht86my0xrr5tvf8cf6s96l` FOREIGN KEY (`requesting_students_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `class_room_requesting_students` */

/*Table structure for table `class_room_students` */

DROP TABLE IF EXISTS `class_room_students`;

CREATE TABLE `class_room_students` (
  `class_room_id` bigint NOT NULL,
  `students_id` bigint NOT NULL,
  KEY `FKtq7d54j5f3qtiomphrj1lhayr` (`students_id`),
  KEY `FKk9u3w234de1ohbqk18pnshsqf` (`class_room_id`),
  CONSTRAINT `FKk9u3w234de1ohbqk18pnshsqf` FOREIGN KEY (`class_room_id`) REFERENCES `class_room` (`id`),
  CONSTRAINT `FKtq7d54j5f3qtiomphrj1lhayr` FOREIGN KEY (`students_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `class_room_students` */

/*Table structure for table `image_data` */

DROP TABLE IF EXISTS `image_data`;

CREATE TABLE `image_data` (
  `id` bigint NOT NULL,
  `data` varbinary(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `owner_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl6namesr27vvgfswd4t4v04gv` (`owner_id`),
  CONSTRAINT `FKl6namesr27vvgfswd4t4v04gv` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `image_data` */

/*Table structure for table `notice` */

DROP TABLE IF EXISTS `notice`;

CREATE TABLE `notice` (
  `id` bigint NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `notice_type` tinyint DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `notifier_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1i6vlt07miqao3fmsfyrju57c` (`notifier_id`),
  CONSTRAINT `FK1i6vlt07miqao3fmsfyrju57c` FOREIGN KEY (`notifier_id`) REFERENCES `user` (`id`),
  CONSTRAINT `notice_chk_1` CHECK ((`notice_type` between 0 and 3))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `notice` */

/*Table structure for table `notice_seq` */

DROP TABLE IF EXISTS `notice_seq`;

CREATE TABLE `notice_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `notice_seq` */

insert  into `notice_seq`(`next_val`) values 
(1);

/*Table structure for table `period` */

DROP TABLE IF EXISTS `period`;

CREATE TABLE `period` (
  `id` bigint NOT NULL,
  `date_time` datetime(6) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `taker_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrsx0pw1sh1p4m51mqoqxgc01d` (`taker_id`),
  CONSTRAINT `FKrsx0pw1sh1p4m51mqoqxgc01d` FOREIGN KEY (`taker_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `period` */

/*Table structure for table `period_class_rooms` */

DROP TABLE IF EXISTS `period_class_rooms`;

CREATE TABLE `period_class_rooms` (
  `period_id` bigint NOT NULL,
  `class_rooms_id` bigint NOT NULL,
  KEY `FKa9flb8seoq76cww5rd2tos3ic` (`class_rooms_id`),
  KEY `FKri7kffxla54dk9fveofsjujgo` (`period_id`),
  CONSTRAINT `FKa9flb8seoq76cww5rd2tos3ic` FOREIGN KEY (`class_rooms_id`) REFERENCES `class_room` (`id`),
  CONSTRAINT `FKri7kffxla54dk9fveofsjujgo` FOREIGN KEY (`period_id`) REFERENCES `period` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `period_class_rooms` */

/*Table structure for table `period_present_students` */

DROP TABLE IF EXISTS `period_present_students`;

CREATE TABLE `period_present_students` (
  `period_id` bigint NOT NULL,
  `present_students_id` bigint NOT NULL,
  KEY `FK57xllmweqjgodirob9s1nthae` (`present_students_id`),
  KEY `FKp5t113c751cm19rc75jsv17xl` (`period_id`),
  CONSTRAINT `FK57xllmweqjgodirob9s1nthae` FOREIGN KEY (`present_students_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKp5t113c751cm19rc75jsv17xl` FOREIGN KEY (`period_id`) REFERENCES `period` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `period_present_students` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `role` */

insert  into `role`(`name`) values 
('ROLE_USER');

/*Table structure for table `role_seq` */

DROP TABLE IF EXISTS `role_seq`;

CREATE TABLE `role_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `role_seq` */

insert  into `role_seq`(`next_val`) values 
(1);

/*Table structure for table `task` */

DROP TABLE IF EXISTS `task`;

CREATE TABLE `task` (
  `id` int NOT NULL,
  `date_time` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_done` bit(1) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `task_type` enum('OFFICIAL','PRIVATE') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2hsytmxysatfvt0p1992cw449` (`user_id`),
  CONSTRAINT `FK2hsytmxysatfvt0p1992cw449` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `task` */

insert  into `task`(`id`,`date_time`,`description`,`is_done`,`title`,`user_id`,`task_type`) values 
(7,'2025-04-04 23:07:00.000000','continue working','','continue',1,'PRIVATE'),
(8,'2025-04-04 23:12:00.000000','123','','Hello',1,'PRIVATE'),
(9,'2025-04-04 23:15:00.000000','123','','continue',1,'PRIVATE'),
(102,'2025-04-05 02:02:00.000000','Task Description','\0','Task title',1,'PRIVATE'),
(103,'2025-04-23 02:05:00.000000','okk','\0','testtt',1,'PRIVATE');

/*Table structure for table `task_seq` */

DROP TABLE IF EXISTS `task_seq`;

CREATE TABLE `task_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `task_seq` */

insert  into `task_seq`(`next_val`) values 
(251);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `profile_image_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKs0si3k8ykyxoo5toc39b29oqf` (`profile_image_id`),
  CONSTRAINT `FKc7ht4pwh5w7rpqwdcxuc0gng1` FOREIGN KEY (`profile_image_id`) REFERENCES `image_data` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`id`,`email`,`full_name`,`password`,`username`,`profile_image_id`) values 
(1,'demo@mail.com','Demo User','$2a$10$Qon5k10ZPRcTGdqb/0U8duPWcIXFb6TuevOGUDExHfmIznKv7abkW','demoUser',NULL),
(2,'demo@mail.com2','Demo User','$2a$10$DNp0kozBm5djmw6p9QHwsejyQtdFYvd4QxDSN6Twrd6bSG2OU3Loe','demoUser2',NULL);

/*Table structure for table `user_roles` */

DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
  `user_id` bigint NOT NULL,
  `roles_name` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`,`roles_name`),
  KEY `FK6pmbiap985ue1c0qjic44pxlc` (`roles_name`),
  CONSTRAINT `FK55itppkw3i07do3h7qoclqd4k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK6pmbiap985ue1c0qjic44pxlc` FOREIGN KEY (`roles_name`) REFERENCES `role` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_roles` */

insert  into `user_roles`(`user_id`,`roles_name`) values 
(1,'ROLE_USER'),
(2,'ROLE_USER');

/*Table structure for table `user_seq` */

DROP TABLE IF EXISTS `user_seq`;

CREATE TABLE `user_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_seq` */

insert  into `user_seq`(`next_val`) values 
(101);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
