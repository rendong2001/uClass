/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.19 : Database - uedu
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`uedu` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `uedu`;

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `cid` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程编号',
  `courseName` varchar(64) DEFAULT NULL COMMENT '课程名称',
  `descs` varchar(255) DEFAULT NULL COMMENT '课程简介',
  `courseType` int(11) DEFAULT NULL COMMENT '课程类型',
  `courseImage` varchar(255) DEFAULT NULL COMMENT '课程图片地址',
  `courseVideo` varchar(255) DEFAULT NULL COMMENT '课程视频地址',
  `coursePrice` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `createTime` datetime DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 COMMENT='课程表';

/*Data for the table `course` */

insert  into `course`(`cid`,`courseName`,`descs`,`courseType`,`courseImage`,`courseVideo`,`coursePrice`,`status`,`createTime`) values (11,'JavaWeb课程','这个阶段杜老师可以',1,'cddca9ac-8cd3-4951-bbc7-a31565c18973javaWeb.jpg','14cac959-99eb-4ec0-a9ad-e79efebad832111.wmv','999.00',0,'2021-02-08 23:58:27'),(12,'mysql课程','这门课程非常重要',2,'aed5631c-b643-41cf-bf4e-58b2fed79070mysql数据库.jpg','760b6f12-0127-4b2b-aed2-8f4d0c015e09111.wmv','1200.00',1,'2021-01-29 23:21:05'),(13,'html课程','做网页最基本的语言',3,'426ffb69-b2cc-4c90-b6e9-b07206eb108fhtml书.jpg','fabb397f-a992-4163-b168-a13ec131db47111.wmv','300.00',2,'2021-01-29 23:23:08'),(14,'oracle课程','这个课程非常牛逼',2,'66ac769b-e416-4efd-9a8e-fdde3ed8d0b6oracle书.jpg','14cac959-99eb-4ec0-a9ad-e79efebad832111.wmv','1600.00',1,'2021-01-29 23:23:55'),(15,'vue课程','这个课程作者是叶倩如',3,'20e39954-cafe-48ac-af12-0b9873d1f38dvue.jpg','02266ae1-018a-46fd-be7b-ae251ffda942haileshe.001.mp4','1200.00',0,'2021-02-05 02:55:39'),(16,'js课程','程序员的看家手段',3,'b67cb06e-794e-4026-8fa3-347ed5c339a3js课程.jpg','','666.00',1,'2021-02-09 00:26:56'),(17,'java从入门','很好学',1,'68f96845-cc7d-4974-8ebd-95bacdbc807711.jpg','539afcaf-e208-4a2e-9051-c831f23df8691.mp4','998.00',1,'2022-08-23 09:08:55'),(18,'java简单的很啊1','简单1',1,'61a9e6b4-8c8a-4533-9b19-8869bd73c7edcts-210819104613071.png','321af60a-aab2-43bf-87eb-ace1b6bffc1c3.mp4','998.00',1,'2022-08-23 10:37:25'),(19,'JAVA到精通1','很好学1',1,'1.jpg','2.mp4','998.00',1,'2022-08-24 14:34:37'),(20,'JAVA到精通2','很好学2',1,'1.jpg','2.mp4','998.00',1,'2022-08-24 14:34:37'),(21,'JAVA到精通3','很好学3',1,'1.jpg','2.mp4','998.00',1,'2022-08-24 14:34:37'),(22,'JAVA到精通4','很好学4',1,'1.jpg','2.mp4','998.00',1,'2022-08-24 14:34:37'),(23,'JAVA到精通5','很好学5',1,'1.jpg','2.mp4','998.00',1,'2022-08-24 14:34:37'),(24,'JAVA到精通6','很好学6',1,'1.jpg','2.mp4','998.00',1,'2022-08-24 14:34:37'),(25,'JAVA到精通7','很好学7',1,'1.jpg','2.mp4','998.00',1,'2022-08-24 14:34:37'),(26,'JAVA到精通8','很好学8',1,'1.jpg','2.mp4','998.00',1,'2022-08-24 14:34:37'),(27,'JAVA从入门到精通9','很好学9',1,'1.jpg','2.mp4','998.00',1,'2022-08-24 14:34:37'),(28,'mysql到精通1','很好学1',2,'123.jpg','1.mp4','9981.00',1,'2022-08-24 14:34:37'),(29,'mysql到精通2','很好学2',2,'123.jpg','1.mp4','9981.00',1,'2022-08-24 14:34:37'),(30,'mysql到精通3','很好学3',2,'123.jpg','1.mp4','9981.00',1,'2022-08-24 14:34:37'),(31,'mysql到精通4','很好学4',2,'123.jpg','1.mp4','9981.00',1,'2022-08-24 14:34:37'),(32,'mysql到精通5','很好学5',2,'123.jpg','1.mp4','9981.00',1,'2022-08-24 14:34:37'),(33,'mysql到精通6','很好学6',2,'123.jpg','1.mp4','9981.00',1,'2022-08-24 14:34:37'),(34,'mysql到精通7','很好学7',2,'123.jpg','1.mp4','9981.00',1,'2022-08-24 14:34:37'),(35,'html到精通1','很好学1',3,'11.jpg','1.mp4','981.00',1,'2022-08-24 14:34:37'),(36,'html到精通2','很好学2',3,'11.jpg','1.mp4','981.00',1,'2022-08-24 14:34:37'),(37,'html到精通3','很好学3',3,'11.jpg','1.mp4','981.00',1,'2022-08-24 14:34:37'),(38,'html到精通4','很好学4',3,'11.jpg','1.mp4','981.00',1,'2022-08-24 14:34:37'),(39,'html到精通5','很好学5',3,'11.jpg','1.mp4','981.00',1,'2022-08-24 14:34:37'),(40,'html到精通6','很好学6',3,'11.jpg','1.mp4','981.00',1,'2022-08-24 14:34:37'),(49,'node.js','运行在服务端的 JavaScript',3,'b693357b-1611-4a46-ba0a-9be815a6f15e1.jpg','508efabd-bf97-4f34-bb8b-31c1b4db7c38明梦爽.mp4','999.00',1,'2022-09-22 22:47:51'),(50,'vue','渐进式的JavaScript框架',3,'4ac64076-ec1a-49c8-ab1f-b4e221bfb2f12.jpg','afbceddf-db30-49c1-a869-4a63ae2204e0平顶山学院.mp4','999.00',0,'2022-09-23 09:57:08'),(51,'React','React',3,'844ef1f7-6e44-4e50-97c8-681b86868d302.jpg','bf5ede05-d6d4-416c-8ec1-e8c9f26c3b54平顶山学院.mp4','999.00',1,'2022-09-27 10:04:51'),(52,'React','React',3,'e8a82aed-aaeb-4431-8436-78f65be091a12.jpg','aaebcd6c-fe26-4800-88a8-b882faf766b2平顶山学院.mp4','999.00',1,'2022-09-27 10:04:52');

/*Table structure for table `course_user` */

DROP TABLE IF EXISTS `course_user`;

CREATE TABLE `course_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `course_user` */

insert  into `course_user`(`id`,`cid`,`uid`) values (6,13,18),(7,12,19),(8,13,19),(9,16,18),(11,11,20),(12,15,20),(13,13,20),(14,51,19),(15,52,20);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '学员编号',
  `name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `username` varchar(32) DEFAULT NULL COMMENT '账号',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `createtime` datetime DEFAULT NULL COMMENT '注册时间',
  `role` int(11) DEFAULT NULL COMMENT '角色',
  `picture` varchar(255) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `user` */

insert  into `user`(`uid`,`name`,`phone`,`age`,`sex`,`username`,`password`,`status`,`createtime`,`role`,`picture`) values (1,'张三','18638938271',32,1,'root','123456',1,'2020-12-04 16:30:23',1,NULL),(18,'王岩','15777777777',24,0,'wangy','123456',1,'2022-09-16 16:59:34',3,NULL),(19,'胡玉涛1','18239775134',30,0,'huyt','123456',1,'2022-09-20 09:17:28',1,NULL),(20,'明梦爽','18239775134',20,0,'mingms','123456',1,'2022-09-20 10:33:57',1,NULL),(21,'秦人杰','15003788555',31,0,'qinrj','123456',1,'2022-09-20 19:51:35',1,NULL),(22,'崔文博','15753155624',34,0,'cuiwb','123456',1,'2022-09-23 22:14:46',1,NULL),(23,NULL,'15003788721',NULL,NULL,'白冉','123456',1,'2022-09-27 10:27:49',3,NULL),(24,NULL,'15003788722',NULL,NULL,'陈帅涛','123456',1,'2022-09-27 17:04:00',3,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
