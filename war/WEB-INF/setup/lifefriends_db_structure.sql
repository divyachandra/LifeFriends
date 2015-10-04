/*
SQLyog Community v12.14 (64 bit)
MySQL - 5.6.23-log : Database - lifefriends
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`lifefriends` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `lifefriends`;

/*Table structure for table `Event` */

DROP TABLE IF EXISTS `Event`;

CREATE TABLE `Event` (
  `EventID` int(11) NOT NULL AUTO_INCREMENT,
  `Type` varchar(20) NOT NULL,
  `Title` varchar(50) NOT NULL,
  `Description` text NOT NULL,
  `StartDate` datetime NOT NULL,
  `EndDate` datetime DEFAULT NULL,
  `Address` varchar(30) NOT NULL,
  `City` varchar(30) NOT NULL,
  `State` varchar(30) NOT NULL,
  `Zip` varchar(15) NOT NULL,
  `Country` varchar(30) NOT NULL,
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Active` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`EventID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Table structure for table `Role` */

DROP TABLE IF EXISTS `Role`;

CREATE TABLE `Role` (
  `RoleID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) NOT NULL,
  `Active` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`RoleID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `Role` */

insert  into `Role`(`RoleID`,`Name`,`Active`) values 
(1,'Admin',''),
(2,'User','');

/*Table structure for table `User` */

DROP TABLE IF EXISTS `User`;

CREATE TABLE `User` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `Email` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `FirstName` varchar(30) NOT NULL,
  `MiddleName` varchar(30) DEFAULT NULL,
  `LastName` varchar(30) NOT NULL,
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UpdatedDate` datetime DEFAULT NULL,
  `DOB` date NOT NULL,
  `Phone` varchar(15) NOT NULL,
  `Address` varchar(30) NOT NULL,
  `City` varchar(30) NOT NULL,
  `State` varchar(30) NOT NULL,
  `Zip` varchar(15) NOT NULL,
  `Country` varchar(30) NOT NULL,
  `BloodGroup` varchar(3) NOT NULL,
  `Gender` varchar(6) NOT NULL,
  `RoleID` int(11) NOT NULL,
  `Active` bit(1) NOT NULL DEFAULT b'1',
  `IsDonor` bit(1) NOT NULL DEFAULT b'1',
  `LifeCertified` bit(1) NOT NULL DEFAULT b'0',
  `OfficialCertified` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`UserID`),
  KEY `User_Role_RoleID` (`RoleID`),
  CONSTRAINT `User_Role_RoleID` FOREIGN KEY (`RoleID`) REFERENCES `Role` (`RoleID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Table structure for table `UserQuiz` */

DROP TABLE IF EXISTS `UserQuiz`;

CREATE TABLE `UserQuiz` (
  `UserQuizID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) DEFAULT NULL,
  `BloodTransfusion` bit(1) NOT NULL,
  `Smoking` bit(1) NOT NULL,
  `Drugs` bit(1) NOT NULL,
  `Alcohol` bit(1) NOT NULL,
  `Tattoo` bit(1) NOT NULL,
  `Weight` int(11) NOT NULL COMMENT 'pounds',
  `Height` int(11) DEFAULT NULL COMMENT 'cms',
  `Active` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`UserQuizID`),
  KEY `Donor_User_UserID` (`UserID`),
  CONSTRAINT `UserQuiz_User_UserID` FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Table structure for table `UserStory` */

DROP TABLE IF EXISTS `UserStory`;

CREATE TABLE `UserStory` (
  `UserStoryID` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(50) NOT NULL,
  `Description` text NOT NULL,
  `UserID` int(11) NOT NULL,
  `Active` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`UserStoryID`),
  KEY `UserStory_User_UserID` (`UserID`),
  CONSTRAINT `UserStory_User_UserID` FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
