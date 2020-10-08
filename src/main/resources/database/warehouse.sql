-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: Aug 23, 2020 at 08:14 PM
-- Server version: 5.7.28
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `warehouse`
--
CREATE DATABASE IF NOT EXISTS `warehouse` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `warehouse`;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
  `customer_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `customer_name` varchar(128) NOT NULL,
  `contact_person` varchar(64) NOT NULL,
  `address` varchar(128) NOT NULL,
  `city` varchar(128) NOT NULL,
  `postcode` mediumint(5) UNSIGNED ZEROFILL NOT NULL,
  `country` varchar(128) NOT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `customer_name_UNIQUE` (`customer_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customer_id`, `created_at`, `customer_name`, `contact_person`, `address`, `city`, `postcode`, `country`) VALUES
(1, '2020-07-21 15:33:16', 'customer1', 'contact1', 'address1', 'city1', 125876, 'state1'),
(2, '2020-07-22 12:27:44', 'customer2', 'contact2', 'address2', 'city2', 01150, 'state2');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `employee_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_name` varchar(64) NOT NULL,
  `first_name` varchar(64) NOT NULL,
  `birthday` varchar(128) NOT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`employee_id`, `created_at`, `last_name`, `first_name`, `birthday`) VALUES
(1, '2020-07-21 15:34:42', 'last_name1', 'first_name1', 'birthday1'),
(2, '2020-07-22 12:28:55', 'last_name2', 'first_name2', 'birthday2'),
(3, '2020-08-16 20:34:46', 'last_name2', 'first_name3', 'birthday3');

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
CREATE TABLE IF NOT EXISTS `order` (
  `order_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `order_date` varchar(128) NOT NULL,
  `customer_id` int(10) UNSIGNED NOT NULL,
  `employee_id` int(10) UNSIGNED NOT NULL,
  `shipper_id` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `fk_order_customer_id_idx` (`customer_id`),
  KEY `fk_order_employee_id_idx` (`employee_id`),
  KEY `fk_order_shipper_id_idx` (`shipper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`order_id`, `created_at`, `order_date`, `customer_id`, `employee_id`, `shipper_id`) VALUES
(2, '2020-07-22 12:31:06', '2020-07-22', 2, 1, 1),
(3, '2020-07-22 12:31:06', '2020-07-22', 1, 1, 2),
(4, '2020-08-01 22:52:42', '2020-08-20', 1, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE IF NOT EXISTS `order_detail` (
  `order_detail_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `order_id` int(10) UNSIGNED NOT NULL,
  `product_id` int(10) UNSIGNED NOT NULL,
  `quantity` bigint(20) NOT NULL DEFAULT '1',
  PRIMARY KEY (`order_detail_id`),
  KEY `fk_order_detail_order_id_idx` (`order_id`),
  KEY `fk_order_detail_product_id_idx` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_detail`
--

INSERT INTO `order_detail` (`order_detail_id`, `created_at`, `order_id`, `product_id`, `quantity`) VALUES
(4, '2020-07-22 12:33:25', 2, 2, 22),
(5, '2020-08-01 23:08:09', 4, 3, 1000);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `product_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `product_name` varchar(128) NOT NULL,
  `supplier_id` int(10) UNSIGNED NOT NULL,
  `product_category` varchar(64) DEFAULT NULL,
  `price_per_unit` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`product_id`),
  KEY `fk_product_supplier_id_idx` (`supplier_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`product_id`, `created_at`, `product_name`, `supplier_id`, `product_category`, `price_per_unit`) VALUES
(1, '2020-07-18 18:18:18', 'product1', 1, 'category1', 20),
(2, '2020-07-18 18:31:42', 'product2', 1, 'category2', 10),
(3, '2020-07-18 18:35:40', 'product3', 2, 'category3', 20),
(4, '2020-07-21 16:09:43', 'product100', 1, NULL, 20),
(5, '2020-08-01 12:12:06', 'product11', 2, 'category4', 180),
(6, '2020-08-01 22:53:24', 'product22', 1, NULL, 110);

-- --------------------------------------------------------

--
-- Table structure for table `shipper`
--

DROP TABLE IF EXISTS `shipper`;
CREATE TABLE IF NOT EXISTS `shipper` (
  `shipper_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `shipper_name` varchar(128) NOT NULL,
  `phone` varchar(32) NOT NULL,
  PRIMARY KEY (`shipper_id`),
  UNIQUE KEY `shipper_name_UNIQUE` (`shipper_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `shipper`
--

INSERT INTO `shipper` (`shipper_id`, `created_at`, `shipper_name`, `phone`) VALUES
(1, '2020-07-21 15:31:38', 'shipper_name1', '060123456'),
(2, '2020-07-22 12:26:16', 'shipper_name2', '065123400');

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
CREATE TABLE IF NOT EXISTS `supplier` (
  `supplier_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `supplier_name` varchar(128) NOT NULL,
  `contact_person` varchar(64) NOT NULL,
  `address` varchar(128) NOT NULL,
  `city` varchar(128) NOT NULL,
  `postcode` mediumint(5) UNSIGNED ZEROFILL NOT NULL,
  `country` varchar(128) NOT NULL,
  `phone` varchar(32) NOT NULL,
  PRIMARY KEY (`supplier_id`),
  UNIQUE KEY `supplier_name_UNIQUE` (`supplier_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`supplier_id`, `created_at`, `supplier_name`, `contact_person`, `address`, `city`, `postcode`, `country`, `phone`) VALUES
(1, '2020-07-11 00:48:09', 'supplier1', 'contact1', 'address1', 'city1', 01200, 'state1', '061123123'),
(2, '2020-07-22 12:24:56', 'supplier2', 'contact2', 'address2', 'city2', 12009, 'state2', '062123456'),
(3, '2020-08-16 19:46:56', 'supplier3', 'contact3', 'address3', 'city1', 11050, 'state1', '061123100');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `fk_order_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_order_employee_id` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_order_shipper_id` FOREIGN KEY (`shipper_id`) REFERENCES `shipper` (`shipper_id`) ON UPDATE CASCADE;

--
-- Constraints for table `order_detail`
--
ALTER TABLE `order_detail`
  ADD CONSTRAINT `fk_order_detail_order_id` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_order_detail_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON UPDATE CASCADE;

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `fk_product_supplier_id` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
