/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.10 : Database - l1
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`l1` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `l1`;

/*Table structure for table `tb_billstat` */

DROP TABLE IF EXISTS `tb_billstat`;

CREATE TABLE `tb_billstat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `text` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tb_billstat` */

insert  into `tb_billstat`(`id`,`name`,`text`) values (1,'待审批','待审批'),(2,'已完成','已完成');

/*Table structure for table `tb_brand` */

DROP TABLE IF EXISTS `tb_brand`;

CREATE TABLE `tb_brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `stat` varchar(10) DEFAULT '使用',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `tb_brand` */

insert  into `tb_brand`(`id`,`name`,`code`,`stat`,`create_by`,`create_time`,`update_by`,`update_time`) values (4,'Nike','Nike','使用',NULL,'2016-04-12 14:19:04',NULL,'2016-04-12 14:41:24'),(5,'Adidas','Adi','使用',NULL,'2016-04-12 14:41:45',NULL,NULL),(6,'123','123','停用',NULL,'2016-05-08 13:24:00',NULL,'2016-05-08 13:24:08');

/*Table structure for table `tb_color` */

DROP TABLE IF EXISTS `tb_color`;

CREATE TABLE `tb_color` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `stat` varchar(10) DEFAULT '使用',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `text` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `tb_color` */

insert  into `tb_color`(`id`,`name`,`code`,`stat`,`create_by`,`create_time`,`update_by`,`update_time`,`text`) values (4,'红色','red','停用',NULL,'2016-04-12 12:38:49',NULL,NULL,NULL),(5,'蓝色','blue','停用',NULL,'2016-04-12 12:38:49',NULL,NULL,NULL),(6,'白','white','使用',NULL,'2016-04-12 12:45:01',NULL,'2016-04-14 00:00:30','白'),(7,'绿色','green','使用',NULL,'2016-04-12 12:54:49',NULL,'2016-04-14 00:00:44','绿色'),(8,'123','123','使用',NULL,'2016-05-08 09:30:44',NULL,'2016-05-08 11:22:45','123'),(9,'123','123','停用',NULL,'2016-05-08 11:22:36',NULL,NULL,'123');

/*Table structure for table `tb_dic` */

DROP TABLE IF EXISTS `tb_dic`;

CREATE TABLE `tb_dic` (
  `id` bigint(100) NOT NULL AUTO_INCREMENT,
  `keyWord` varchar(127) NOT NULL,
  `textField` varchar(127) NOT NULL,
  `valueField` int(10) NOT NULL,
  `ord` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `tb_dic` */

insert  into `tb_dic`(`id`,`keyWord`,`textField`,`valueField`,`ord`) values (1,'warehouseState','停用',0,0),(2,'warehouseState','使用',1,1),(3,'rentStatus','已取消',-1,1),(4,'rentStatus','已下单,未支付',1,2),(5,'rentStatus','已支付',2,3),(6,'rentStatus','已发货',3,4),(7,'rentStatus','已收货,待退还',4,5),(8,'rentStatus','已退还,待收货',5,6),(9,'rentStatus','已完成',6,7);

/*Table structure for table `tb_inventory` */

DROP TABLE IF EXISTS `tb_inventory`;

CREATE TABLE `tb_inventory` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `skuId` bigint(20) DEFAULT NULL,
  `colorId` bigint(20) DEFAULT NULL,
  `sizeId` bigint(20) DEFAULT NULL,
  `style` varchar(100) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `warehouseId` bigint(20) DEFAULT NULL,
  `locationId` bigint(20) DEFAULT NULL,
  `stat` bigint(20) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_inventory` */

/*Table structure for table `tb_item` */

DROP TABLE IF EXISTS `tb_item`;

CREATE TABLE `tb_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `stat` varchar(10) DEFAULT '使用',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `brandId` bigint(20) DEFAULT NULL,
  `color` varchar(250) DEFAULT NULL,
  `brandName` varchar(50) DEFAULT NULL,
  `primaryCategoryId` bigint(20) DEFAULT NULL,
  `minorCategoryId` bigint(20) DEFAULT NULL,
  `primaryCategoryName` varchar(50) DEFAULT NULL,
  `minorCategoryName` varchar(50) DEFAULT NULL,
  `rental1` decimal(12,2) DEFAULT NULL,
  `rental2` decimal(12,2) DEFAULT NULL,
  `deposit` decimal(12,2) DEFAULT NULL,
  `style` varchar(50) DEFAULT NULL,
  `colorname` varchar(250) DEFAULT NULL,
  `size` varchar(250) DEFAULT NULL,
  `sizeName` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `tb_item` */

insert  into `tb_item`(`id`,`name`,`code`,`stat`,`create_by`,`create_time`,`update_by`,`update_time`,`brandId`,`color`,`brandName`,`primaryCategoryId`,`minorCategoryId`,`primaryCategoryName`,`minorCategoryName`,`rental1`,`rental2`,`deposit`,`style`,`colorname`,`size`,`sizeName`) values (4,'西服','xifu','使用',NULL,NULL,NULL,'2016-04-14 10:31:36',5,'6,7','Adidas',4,5,'西裤','西裤22','29.00','1.00','1.00','kuanhao','白,绿色',NULL,NULL),(5,'sss','sa','使用',NULL,'2016-04-18 22:00:51',NULL,'2016-04-19 13:07:39',4,'6','Nike',4,4,'西裤','牛仔裤11','11.00','11.00','11.00','33','白','8,11','165S,175XX'),(6,'xxx','xx','使用',NULL,'2016-04-19 00:23:48',NULL,NULL,5,'6,7','Adidas',4,4,'西裤','牛仔裤11','12.00','1222.00','112.00',NULL,'白,绿色','8,10,11','165S,180L,175XX'),(7,'gg','gg','使用',NULL,'2016-04-19 00:28:45',NULL,'2016-04-19 13:06:15',4,'6,7','Nike',4,5,'西裤','西裤22','33.00','33.00','322.00','gggggg','白,绿色','8,10,11','165S,180L,175XX'),(8,'234','234','停用',NULL,'2016-05-08 11:31:20',NULL,'2016-05-08 11:31:29',4,'6','Nike',4,5,'西裤34234','西裤22','234.00','234.00','234.00','234','白','8','165S'),(9,'123','213','使用',NULL,'2016-05-09 18:38:48',NULL,NULL,NULL,'6,7','',4,4,'西裤34234','牛仔裤11','123.00','123.00','123.00','123','白,绿色','8,9','165S,170');

/*Table structure for table `tb_kctz` */

DROP TABLE IF EXISTS `tb_kctz`;

CREATE TABLE `tb_kctz` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `billNo` varchar(20) DEFAULT NULL,
  `warehouseId` bigint(20) DEFAULT NULL,
  `stat` int(11) DEFAULT '0',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `warehouseName` varchar(50) DEFAULT NULL,
  `statName` varchar(50) DEFAULT NULL,
  `billDate` date DEFAULT NULL,
  `makerId` bigint(20) DEFAULT NULL,
  `makerName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tb_kctz` */

insert  into `tb_kctz`(`id`,`billNo`,`warehouseId`,`stat`,`create_by`,`create_time`,`update_by`,`update_time`,`warehouseName`,`statName`,`billDate`,`makerId`,`makerName`) values (1,'e88900',1,1,NULL,'2016-04-28 11:06:26',NULL,'2016-04-28 11:55:53','浦东仓库','待审批','2016-04-28',NULL,NULL);

/*Table structure for table `tb_kctzdtl` */

DROP TABLE IF EXISTS `tb_kctzdtl`;

CREATE TABLE `tb_kctzdtl` (
  `dtlid` bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) DEFAULT NULL,
  `skuId` bigint(20) DEFAULT NULL,
  `warehouseId` bigint(20) DEFAULT NULL,
  `locationId` bigint(20) DEFAULT NULL,
  `tzLocationId` bigint(20) DEFAULT NULL,
  `skuAmount` int(11) DEFAULT NULL,
  `tzAmount` decimal(12,2) DEFAULT NULL,
  PRIMARY KEY (`dtlid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tb_kctzdtl` */

insert  into `tb_kctzdtl`(`dtlid`,`id`,`skuId`,`warehouseId`,`locationId`,`tzLocationId`,`skuAmount`,`tzAmount`) values (1,1,21,1,4,5,55,'6.00');

/*Table structure for table `tb_minor_category` */

DROP TABLE IF EXISTS `tb_minor_category`;

CREATE TABLE `tb_minor_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `stat` varchar(10) DEFAULT '使用',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `tb_minor_category` */

insert  into `tb_minor_category`(`id`,`name`,`code`,`stat`,`create_by`,`create_time`,`update_by`,`update_time`) values (4,'牛仔裤11','niuzai','使用',NULL,'2016-04-13 21:35:21',NULL,NULL),(5,'西裤22','xiku','使用',NULL,'2016-04-13 21:35:40',NULL,'2016-05-08 11:31:54');

/*Table structure for table `tb_primary_category` */

DROP TABLE IF EXISTS `tb_primary_category`;

CREATE TABLE `tb_primary_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `stat` varchar(10) DEFAULT '使用',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `sizeid` bigint(20) DEFAULT NULL,
  `sizename` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `tb_primary_category` */

insert  into `tb_primary_category`(`id`,`name`,`code`,`stat`,`create_by`,`create_time`,`update_by`,`update_time`,`sizeid`,`sizename`) values (4,'西裤34234','001','使用',NULL,'2016-04-12 14:54:51',NULL,'2016-05-08 09:04:02',NULL,NULL),(5,'牛仔裤','002','使用',NULL,'2016-04-12 14:55:12',NULL,'2016-04-12 15:01:58',NULL,NULL),(6,'123','123','使用',NULL,'2016-05-08 08:58:41',NULL,'2016-05-08 11:31:47',NULL,NULL);

/*Table structure for table `tb_purchase` */

DROP TABLE IF EXISTS `tb_purchase`;

CREATE TABLE `tb_purchase` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `billNo` varchar(20) DEFAULT NULL,
  `supplierId` bigint(20) DEFAULT NULL,
  `warehouseId` bigint(20) DEFAULT NULL,
  `stat` int(11) DEFAULT '0',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `warehouseName` varchar(50) DEFAULT NULL,
  `supplierName` varchar(50) DEFAULT NULL,
  `statName` varchar(50) DEFAULT NULL,
  `billDate` date DEFAULT NULL,
  `total` decimal(12,2) DEFAULT NULL,
  `makerId` bigint(20) DEFAULT NULL,
  `makerName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `tb_purchase` */

insert  into `tb_purchase`(`id`,`billNo`,`supplierId`,`warehouseId`,`stat`,`create_by`,`create_time`,`update_by`,`update_time`,`warehouseName`,`supplierName`,`statName`,`billDate`,`total`,`makerId`,`makerName`) values (2,'222',3,2,2,NULL,'2016-04-27 22:27:51',NULL,'2016-04-28 10:30:31','浦西仓库',NULL,'已完成','2016-04-12','55.00',0,NULL),(3,'22',2,3,1,NULL,'2016-04-28 10:32:20',NULL,NULL,'嘉定仓库',NULL,'待审批','2016-04-28','22.00',0,NULL);

/*Table structure for table `tb_purchasedtl` */

DROP TABLE IF EXISTS `tb_purchasedtl`;

CREATE TABLE `tb_purchasedtl` (
  `dtlid` bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) DEFAULT NULL,
  `skuId` bigint(20) DEFAULT NULL,
  `itemName` varchar(50) DEFAULT NULL,
  `itemPrice` decimal(12,2) DEFAULT NULL,
  `itemAmount` int(11) DEFAULT NULL,
  `itemTotal` int(11) DEFAULT NULL,
  PRIMARY KEY (`dtlid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tb_purchasedtl` */

insert  into `tb_purchasedtl`(`dtlid`,`id`,`skuId`,`itemName`,`itemPrice`,`itemAmount`,`itemTotal`) values (1,2,16,'gggggg','55.00',563,30965);

/*Table structure for table `tb_rent` */

DROP TABLE IF EXISTS `tb_rent`;

CREATE TABLE `tb_rent` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `billNo` varchar(20) DEFAULT NULL,
  `customerName` varchar(50) DEFAULT NULL,
  `warehouseId` bigint(20) DEFAULT NULL,
  `stat` int(11) DEFAULT '0',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `rentDay` int(11) DEFAULT NULL,
  `rentMoney` decimal(12,2) DEFAULT NULL,
  `repoMoney` decimal(12,2) DEFAULT NULL,
  `customerPhone` varchar(20) DEFAULT NULL,
  `customerAddr` varchar(100) DEFAULT NULL,
  `customerCard` varchar(50) DEFAULT NULL,
  `logisticsCompany` varchar(50) DEFAULT NULL,
  `expressBillNo` varchar(50) DEFAULT NULL,
  `returnBillNo` varchar(50) DEFAULT NULL,
  `beginDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `warehouseName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;

/*Data for the table `tb_rent` */

insert  into `tb_rent`(`id`,`billNo`,`customerName`,`warehouseId`,`stat`,`create_by`,`create_time`,`update_by`,`update_time`,`rentDay`,`rentMoney`,`repoMoney`,`customerPhone`,`customerAddr`,`customerCard`,`logisticsCompany`,`expressBillNo`,`returnBillNo`,`beginDate`,`endDate`,`warehouseName`) values (69,'CZ201605090023',NULL,NULL,6,NULL,'2016-05-09 21:11:27',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70,'CZ201605090024',NULL,NULL,6,NULL,'2016-05-09 21:11:31',NULL,NULL,NULL,NULL,NULL,'','','','','','',NULL,NULL,NULL),(71,'CZ201605090025',NULL,NULL,6,NULL,'2016-05-09 21:11:40',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(72,'CZ201605090026',NULL,NULL,6,NULL,'2016-05-09 21:13:45',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(74,'CZ201605090028',NULL,NULL,6,NULL,'2016-05-09 21:18:41',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `tb_rentdtl` */

DROP TABLE IF EXISTS `tb_rentdtl`;

CREATE TABLE `tb_rentdtl` (
  `dtlid` bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) DEFAULT NULL,
  `skuId` bigint(20) DEFAULT NULL,
  `itemName` varchar(50) DEFAULT NULL,
  `itemPrice` decimal(12,2) DEFAULT NULL,
  `itemAmount` int(11) DEFAULT NULL,
  `itemRepo` decimal(12,2) DEFAULT NULL,
  `itemRent` decimal(12,2) DEFAULT NULL,
  `warehouseId` bigint(20) DEFAULT NULL,
  `stat` bigint(20) DEFAULT NULL,
  `statName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`dtlid`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

/*Data for the table `tb_rentdtl` */

insert  into `tb_rentdtl`(`dtlid`,`id`,`skuId`,`itemName`,`itemPrice`,`itemAmount`,`itemRepo`,`itemRent`,`warehouseId`,`stat`,`statName`) values (27,34,16,'234','234.00',324,'234.00','75816.00',NULL,-1,NULL),(28,34,16,'213','123.00',123,'123.00','15129.00',NULL,1,NULL),(29,35,16,'123','123.00',123,'123.00','15129.00',NULL,1,NULL),(30,36,15,'123','123.00',123,'123.00','15129.00',NULL,0,NULL),(31,37,16,'123','123.00',123,NULL,'15129.00',NULL,0,NULL),(36,38,16,'123','123.00',123,NULL,'15129.00',NULL,0,NULL),(37,38,15,'123','123.00',123,'123.00','15129.00',NULL,0,NULL);

/*Table structure for table `tb_return` */

DROP TABLE IF EXISTS `tb_return`;

CREATE TABLE `tb_return` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `billNo` varchar(20) DEFAULT NULL,
  `customerName` varchar(50) DEFAULT NULL,
  `customerPhone` varchar(20) DEFAULT NULL,
  `customerAddr` varchar(100) DEFAULT NULL,
  `customerCard` varchar(50) DEFAULT NULL,
  `logisticsCompany` varchar(50) DEFAULT NULL,
  `expressBillNo` varchar(50) DEFAULT NULL,
  `rentBillNo` varchar(50) DEFAULT NULL,
  `warehouseId` bigint(20) DEFAULT NULL,
  `warehouseName` varchar(50) DEFAULT NULL,
  `stat` int(11) DEFAULT '0',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `rentDay` int(11) DEFAULT NULL,
  `rentMoney` decimal(12,2) DEFAULT NULL,
  `repoMoney` decimal(12,2) DEFAULT NULL,
  `damageMoney` decimal(12,2) DEFAULT NULL,
  `beginDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `statName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tb_return` */

insert  into `tb_return`(`id`,`billNo`,`customerName`,`customerPhone`,`customerAddr`,`customerCard`,`logisticsCompany`,`expressBillNo`,`rentBillNo`,`warehouseId`,`warehouseName`,`stat`,`create_by`,`create_time`,`update_by`,`update_time`,`rentDay`,`rentMoney`,`repoMoney`,`damageMoney`,`beginDate`,`endDate`,`statName`) values (1,'3344','所发生','22','第三方','345566','22 第三方','的4444','222',2,'浦西仓库',2,NULL,'2016-04-27 23:17:32',NULL,'2016-04-27 23:24:00',8,'33.00','33.00','333.00','2016-04-20','2016-04-27','已完成');

/*Table structure for table `tb_returndtl` */

DROP TABLE IF EXISTS `tb_returndtl`;

CREATE TABLE `tb_returndtl` (
  `dtlid` bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) DEFAULT NULL,
  `skuId` bigint(20) DEFAULT NULL,
  `itemName` varchar(50) DEFAULT NULL,
  `itemPrice` decimal(12,2) DEFAULT NULL,
  `itemAmount` int(11) DEFAULT NULL,
  `itemRepo` decimal(12,2) DEFAULT NULL,
  `itemRent` decimal(12,2) DEFAULT NULL,
  `itemDamage` decimal(12,2) DEFAULT NULL,
  `warehouseId` bigint(20) DEFAULT NULL,
  `warehouseName` varchar(50) DEFAULT NULL,
  `stat` bigint(20) DEFAULT NULL,
  `statName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`dtlid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tb_returndtl` */

insert  into `tb_returndtl`(`dtlid`,`id`,`skuId`,`itemName`,`itemPrice`,`itemAmount`,`itemRepo`,`itemRent`,`itemDamage`,`warehouseId`,`warehouseName`,`stat`,`statName`) values (1,1,21,'33','44.00',44,'44.00','1936.00','4444.00',NULL,NULL,1,'待审批');

/*Table structure for table `tb_seq` */

DROP TABLE IF EXISTS `tb_seq`;

CREATE TABLE `tb_seq` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `prefix` varchar(10) NOT NULL,
  `day` date NOT NULL,
  `seq` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

/*Data for the table `tb_seq` */

insert  into `tb_seq`(`id`,`prefix`,`day`,`seq`) values (14,'CZ','2016-05-09',1),(15,'CZ','2016-05-09',2),(16,'CZ','2016-05-09',3),(17,'CZ','2016-05-09',4),(18,'CZ','2016-05-09',5),(19,'CZ','2016-05-09',6),(20,'CZ','2016-05-09',7),(21,'CZ','2016-05-09',8),(22,'CZ','2016-05-09',9),(23,'CZ','2016-05-09',10),(24,'CZ','2016-05-09',11),(25,'CZ','2016-05-09',12),(26,'CZ','2016-05-09',13),(27,'CZ','2016-05-09',14),(28,'CZ','2016-05-09',15),(29,'CZ','2016-05-09',16),(30,'CZ','2016-05-09',17),(31,'CZ','2016-05-09',18),(32,'CZ','2016-05-09',19),(33,'CZ','2016-05-09',20),(34,'CZ','2016-05-09',21),(35,'CZ','2016-05-09',22),(36,'CZ','2016-05-09',23),(37,'CZ','2016-05-09',24),(38,'CZ','2016-05-09',25),(39,'CZ','2016-05-09',26),(40,'CZ','2016-05-09',27),(41,'CZ','2016-05-09',28);

/*Table structure for table `tb_size` */

DROP TABLE IF EXISTS `tb_size`;

CREATE TABLE `tb_size` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `stat` varchar(10) DEFAULT '使用',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `text` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `tb_size` */

insert  into `tb_size`(`id`,`name`,`code`,`stat`,`create_by`,`create_time`,`update_by`,`update_time`,`text`) values (8,'S001','S001','使用',NULL,'2016-04-14 15:00:11',NULL,'2016-04-14 15:03:30','S001'),(9,'S002','S002','使用',NULL,'2016-04-14 15:43:58',NULL,'2016-05-08 13:07:03','S002'),(10,'123','123','停用',NULL,'2016-05-08 13:06:59',NULL,NULL,'123');

/*Table structure for table `tb_sizedtl` */

DROP TABLE IF EXISTS `tb_sizedtl`;

CREATE TABLE `tb_sizedtl` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sizeId` bigint(20) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `stat` varchar(10) DEFAULT '使用',
  `text` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `tb_sizedtl` */

insert  into `tb_sizedtl`(`id`,`sizeId`,`name`,`code`,`stat`,`text`) values (8,8,'165S','001','使用','165S'),(9,8,'170','002','使用','170M'),(10,9,'180L','XXX','使用','180L'),(11,9,'175XX','XL','使用','175XX');

/*Table structure for table `tb_sku` */

DROP TABLE IF EXISTS `tb_sku`;

CREATE TABLE `tb_sku` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `itemId` bigint(20) DEFAULT NULL,
  `colorId` bigint(20) DEFAULT NULL,
  `sizeDtlId` bigint(20) DEFAULT NULL,
  `itemName` varchar(100) DEFAULT NULL,
  `sizeDtlName` varchar(100) DEFAULT NULL,
  `colorName` varchar(100) DEFAULT NULL,
  `text` varchar(20) DEFAULT NULL,
  `hasImage` tinyint(1) DEFAULT NULL,
  `imgSuffix` varchar(64) DEFAULT NULL,
  `comment` varchar(127) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Data for the table `tb_sku` */

insert  into `tb_sku`(`id`,`itemId`,`colorId`,`sizeDtlId`,`itemName`,`sizeDtlName`,`colorName`,`text`,`hasImage`,`imgSuffix`,`comment`) values (15,7,6,8,'15','165S','白','15',NULL,'.jpg',''),(16,7,6,10,'16','180L','白','16',NULL,'.jpg',''),(17,7,6,11,'17','175XX','白','17',NULL,'.png',''),(18,7,7,8,'18','165S','绿色','18',1,'.png','w'),(19,7,7,10,'19','180L','绿色','19',1,'.jpg','2134'),(20,7,7,11,'20','175XX','绿色','20',NULL,NULL,NULL),(21,5,6,8,'21','165S','白','21',NULL,NULL,NULL),(22,5,6,11,'22','175XX','白','22',NULL,NULL,NULL),(23,8,6,8,'234','165S','白','234',NULL,NULL,NULL),(24,9,6,8,'123','165S','白','123',NULL,NULL,NULL),(25,9,6,9,'123','170','白','123',NULL,NULL,NULL),(26,9,7,8,'123','165S','绿色','123',NULL,NULL,NULL),(27,9,7,9,'123','170','绿色','123',NULL,NULL,NULL);

/*Table structure for table `tb_supplier` */

DROP TABLE IF EXISTS `tb_supplier`;

CREATE TABLE `tb_supplier` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `supplierName` varchar(50) DEFAULT NULL,
  `supplierCode` varchar(50) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `stat` varchar(10) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `tb_supplier` */

insert  into `tb_supplier`(`id`,`supplierName`,`supplierCode`,`mobile`,`type`,`stat`,`create_by`,`create_time`,`update_time`,`update_by`) values (1,'test001','test001','189999','供货','使用',NULL,NULL,'2016-05-08 09:28:30',NULL),(2,'test002','test002','189999','委外','停用',NULL,NULL,NULL,NULL),(3,'test003','test003','189999','委外','使用',NULL,NULL,'2016-05-08 09:28:36',NULL),(4,'test004','test004','189999','委外','停用',NULL,NULL,NULL,NULL),(5,'test005','test005','189999','委外','停用',NULL,NULL,NULL,NULL),(6,'test006','test006','189999','委外','停用',NULL,NULL,NULL,NULL),(7,'test007','test007','189999','委外','停用',NULL,NULL,NULL,NULL),(8,'test008','test008','189999','委外','停用',NULL,NULL,NULL,NULL),(10,'test009','test009','189999','委外','停用',NULL,NULL,NULL,NULL),(11,'test010','test010','189999','委外','停用',NULL,NULL,'2016-05-08 11:32:33',NULL),(12,'test011','test011','189999','委外','停用',NULL,NULL,NULL,NULL),(14,'45654','123','123','委外','使用',NULL,'2016-05-08 10:48:56','2016-05-08 10:49:11',NULL);

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `true_name` varchar(50) DEFAULT NULL,
  `mobile` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `stat` varchar(10) DEFAULT NULL,
  `create_by` datetime DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `user_code` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户';

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`user_name`,`password`,`true_name`,`mobile`,`email`,`stat`,`create_by`,`last_login_time`,`create_time`,`update_by`,`user_code`) values (1,'admin','admin','sky11',NULL,NULL,'1',NULL,NULL,NULL,NULL,NULL),(2,'test','test','test',NULL,NULL,'1',NULL,NULL,NULL,NULL,NULL),(3,'xiaohong','111','xiaohong',NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `tb_warehouse` */

DROP TABLE IF EXISTS `tb_warehouse`;

CREATE TABLE `tb_warehouse` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `stat` varchar(10) DEFAULT '使用',
  `address` varchar(50) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `text` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `tb_warehouse` */

insert  into `tb_warehouse`(`id`,`name`,`stat`,`address`,`phone`,`create_by`,`create_time`,`update_by`,`update_time`,`code`,`text`) values (1,'浦东仓库','使用','手动复位','2222',NULL,NULL,NULL,NULL,'001','浦东仓库'),(2,'浦西仓库','停用','徐家汇001号','12344',NULL,NULL,NULL,NULL,'002','浦西仓库'),(3,'嘉定仓库','停用','嘉定城','22222222',NULL,NULL,NULL,NULL,'003','嘉定仓库'),(4,'123','使用','123','123',NULL,'2016-05-08 09:32:32',NULL,NULL,'123','123'),(5,'324','使用','123','123',NULL,'2016-05-08 09:36:11',NULL,'2016-05-08 09:36:28','123','324');

/*Table structure for table `tb_warehouse_location` */

DROP TABLE IF EXISTS `tb_warehouse_location`;

CREATE TABLE `tb_warehouse_location` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `warehouseId` bigint(20) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `stat` varchar(10) DEFAULT '使用',
  `code` varchar(20) DEFAULT NULL,
  `text` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `tb_warehouse_location` */

insert  into `tb_warehouse_location`(`id`,`warehouseId`,`name`,`stat`,`code`,`text`) values (4,1,'塘桥1号仓库11',NULL,'001','塘桥1号仓库'),(5,1,'高科2号仓库22',NULL,'002','高科2号仓库'),(6,1,'sswww',NULL,'003','ss'),(7,5,'123',NULL,'123','123'),(8,5,'123',NULL,'123','123');

/*Table structure for table `tb_ycck` */

DROP TABLE IF EXISTS `tb_ycck`;

CREATE TABLE `tb_ycck` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `billNo` varchar(20) DEFAULT NULL,
  `outWarehouseId` bigint(20) DEFAULT NULL,
  `inWarehouseId` bigint(20) DEFAULT NULL,
  `makerId` bigint(20) DEFAULT NULL,
  `total` decimal(20,2) DEFAULT NULL,
  `stat` int(11) DEFAULT '0',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `billdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tb_ycck` */

insert  into `tb_ycck`(`id`,`billNo`,`outWarehouseId`,`inWarehouseId`,`makerId`,`total`,`stat`,`create_by`,`create_time`,`update_by`,`update_time`,`billdate`) values (1,'e88900',2,3,NULL,'230.00',2,NULL,'2016-04-28 16:20:02',NULL,'2016-04-28 16:22:26','2016-04-06 00:00:00');

/*Table structure for table `tb_ycckdtl` */

DROP TABLE IF EXISTS `tb_ycckdtl`;

CREATE TABLE `tb_ycckdtl` (
  `dtlid` bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) DEFAULT NULL,
  `skuId` bigint(20) DEFAULT NULL,
  `skuAmount` int(11) DEFAULT NULL,
  `outAmount` int(11) DEFAULT NULL,
  `warehouseId` bigint(20) DEFAULT NULL,
  `locationId` bigint(20) DEFAULT NULL,
  `stat` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`dtlid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tb_ycckdtl` */

insert  into `tb_ycckdtl`(`dtlid`,`id`,`skuId`,`skuAmount`,`outAmount`,`warehouseId`,`locationId`,`stat`) values (1,1,16,333,589,1,5,NULL);

/*Table structure for table `tb_ycrk` */

DROP TABLE IF EXISTS `tb_ycrk`;

CREATE TABLE `tb_ycrk` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `billNo` varchar(20) DEFAULT NULL,
  `outBillNo` varchar(20) DEFAULT NULL,
  `outWarehouseId` bigint(20) DEFAULT NULL,
  `inWarehouseId` bigint(20) DEFAULT NULL,
  `makerId` bigint(20) DEFAULT NULL,
  `total` decimal(20,2) DEFAULT NULL,
  `stat` int(11) DEFAULT '0',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `billdate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tb_ycrk` */

insert  into `tb_ycrk`(`id`,`billNo`,`outBillNo`,`outWarehouseId`,`inWarehouseId`,`makerId`,`total`,`stat`,`create_by`,`create_time`,`update_by`,`update_time`,`billdate`) values (1,'222',NULL,2,3,1,'32.00',1,NULL,'2016-04-28 14:45:38',NULL,'2016-04-28 15:07:18','2016-04-27'),(2,'e88900',NULL,1,3,NULL,'23.00',1,NULL,'2016-04-28 16:17:48',NULL,NULL,'2016-04-28');

/*Table structure for table `tb_ycrkdtl` */

DROP TABLE IF EXISTS `tb_ycrkdtl`;

CREATE TABLE `tb_ycrkdtl` (
  `dtlid` bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) DEFAULT NULL,
  `skuId` bigint(20) DEFAULT NULL,
  `skuAmount` int(11) DEFAULT NULL,
  `inAmount` int(11) DEFAULT NULL,
  `warehouseId` bigint(20) DEFAULT NULL,
  `locationId` bigint(20) DEFAULT NULL,
  `stat` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`dtlid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tb_ycrkdtl` */

insert  into `tb_ycrkdtl`(`dtlid`,`id`,`skuId`,`skuAmount`,`inAmount`,`warehouseId`,`locationId`,`stat`) values (2,1,21,660,56,2,5,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
