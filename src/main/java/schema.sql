CREATE DATABASE `demo` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `demo`;

DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` varchar(255) NOT NULL,
  `message` tinytext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
