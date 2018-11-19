/*
SQLyog Ultimate v12.5.1 (64 bit)
MySQL - 10.1.30-MariaDB : Database - catering
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`catering` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `catering`;

/*Table structure for table `tb_admin` */

DROP TABLE IF EXISTS `tb_admin`;

CREATE TABLE `tb_admin` (
  `kd_user` varchar(4) NOT NULL,
  `user_name` varchar(12) NOT NULL,
  `user_pass` varchar(12) NOT NULL,
  PRIMARY KEY (`kd_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tb_admin` */

/*Table structure for table `tb_kategorimakanan` */

DROP TABLE IF EXISTS `tb_kategorimakanan`;

CREATE TABLE `tb_kategorimakanan` (
  `kd_kategori` varchar(3) NOT NULL,
  `nama_kategori` varchar(25) NOT NULL,
  PRIMARY KEY (`kd_kategori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tb_kategorimakanan` */

insert  into `tb_kategorimakanan`(`kd_kategori`,`nama_kategori`) values 
('k01','Roti'),
('k02','Snack'),
('k03','Jajanan pasar');

/*Table structure for table `tb_makanan` */

DROP TABLE IF EXISTS `tb_makanan`;

CREATE TABLE `tb_makanan` (
  `kd_mkn` varchar(5) NOT NULL,
  `nm_mkn` varchar(25) NOT NULL,
  `kd_kategori` varchar(3) NOT NULL,
  `harga_jual` int(11) NOT NULL,
  `satuan` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`kd_mkn`),
  KEY `kd_kategori` (`kd_kategori`),
  CONSTRAINT `tb_makanan_ibfk_1` FOREIGN KEY (`kd_kategori`) REFERENCES `tb_kategorimakanan` (`kd_kategori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tb_makanan` */

insert  into `tb_makanan`(`kd_mkn`,`nm_mkn`,`kd_kategori`,`harga_jual`,`satuan`) values 
('mkn01','Roti nanas','k01',1500,''),
('mkn02','Susu maker','k02',2000,''),
('mkn03','Klepon Susu','k03',1500,''),
('mkn04','tahu tempe','K02',2000,NULL);

/*Table structure for table `tb_pelanggan` */

DROP TABLE IF EXISTS `tb_pelanggan`;

CREATE TABLE `tb_pelanggan` (
  `kd_plg` varchar(6) NOT NULL,
  `nm_plg` varchar(15) NOT NULL,
  `telp` varchar(15) NOT NULL,
  `alamat` varchar(25) NOT NULL,
  PRIMARY KEY (`kd_plg`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tb_pelanggan` */

insert  into `tb_pelanggan`(`kd_plg`,`nm_plg`,`telp`,`alamat`) values 
('plg01','Joko','08222','Monjali'),
('plg02','Wahyu','08222','Yogyakarta'),
('plg03','Yosa','0822','monjali');

/*Table structure for table `tb_pembelian_detil` */

DROP TABLE IF EXISTS `tb_pembelian_detil`;

CREATE TABLE `tb_pembelian_detil` (
  `kd_plg` varchar(5) NOT NULL,
  `kd_mkn` varchar(5) NOT NULL,
  `banyak` int(11) NOT NULL,
  `tanggal_kirim` date DEFAULT NULL,
  KEY `tb_pembelian_detil_ibfk_1` (`kd_plg`),
  KEY `tb_pembelian_detil_ibfk_2` (`kd_mkn`),
  CONSTRAINT `tb_pembelian_detil_ibfk_1` FOREIGN KEY (`kd_plg`) REFERENCES `tb_pelanggan` (`kd_plg`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_pembelian_detil_ibfk_2` FOREIGN KEY (`kd_mkn`) REFERENCES `tb_makanan` (`kd_mkn`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tb_pembelian_detil` */

insert  into `tb_pembelian_detil`(`kd_plg`,`kd_mkn`,`banyak`,`tanggal_kirim`) values 
('plg01','mkn01',2,'0000-00-00'),
('plg01','mkn02',2,'0000-00-00'),
('plg02','mkn01',3,'0000-00-00'),
('plg03','mkn02',2,'2018-01-01');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
