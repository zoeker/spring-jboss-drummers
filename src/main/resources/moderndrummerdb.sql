﻿-- Database Design by Conny Pemfors 2015
-- Script was generated by Devart dbForge Studio for MySQL, Version 6.3.323.0
-- Product home page: http://www.devart.com/dbforge/mysql/studio
-- Script date 2015-08-11 06:57:02
-- Server version: 6.0.0-alpha-community-nt-debug
-- Client version: 4.1
--


--
-- Definition for database moderndrummerdb
--
DROP DATABASE IF EXISTS moderndrummerdb;
CREATE DATABASE IF NOT EXISTS moderndrummerdb
	CHARACTER SET utf8
	COLLATE utf8_general_ci;

-- 
-- Disable foreign keys
-- 
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;

-- 
-- Set SQL mode
-- 
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 
-- Set character set the client will use to send SQL statements to the server
--
SET NAMES 'utf8';

-- 
-- Set default database
--
USE moderndrummerdb;

--
-- Definition for table hibernate_sequence
--
CREATE TABLE IF NOT EXISTS hibernate_sequence (
  next_val BIGINT(20) DEFAULT NULL
)
ENGINE = INNODB
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Definition for table member
--
CREATE TABLE IF NOT EXISTS member (
  MemberId INT(11) NOT NULL AUTO_INCREMENT,
  Name VARCHAR(50) NOT NULL DEFAULT '',
  Email VARCHAR(50) NOT NULL,
  PhoneNumber VARCHAR(30) DEFAULT NULL,
  Password VARCHAR(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  CreatedDate TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  MemberStory VARCHAR(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (MemberId),
  UNIQUE INDEX Email (Email),
  UNIQUE INDEX UK_9qv6yhjqm8iafto8qk452gx8h (Email),
  UNIQUE INDEX UK_a96e1uemhcj8nrc9p9eld5281 (Email)
)
ENGINE = INNODB
AUTO_INCREMENT = 17
AVG_ROW_LENGTH = 1365
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Definition for table memberbasic
--
CREATE TABLE IF NOT EXISTS memberbasic (
  id BIGINT(20) NOT NULL,
  email VARCHAR(255) NOT NULL,
  name VARCHAR(25) NOT NULL,
  phone_number VARCHAR(12) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX UK_ix94ex1325a9dg221mvbx3t2o (email)
)
ENGINE = INNODB
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci;





--
-- Definition for table topic
--
CREATE TABLE IF NOT EXISTS topic (
  topicId INT(11) NOT NULL AUTO_INCREMENT,
  TopicDescription VARCHAR(255) DEFAULT NULL,
  TopicName VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (topicId)
)
ENGINE = INNODB
AUTO_INCREMENT = 11
AVG_ROW_LENGTH = 1638
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Definition for table memberblogpost
--
CREATE TABLE IF NOT EXISTS memberblogpost (
  blogPostId INT(11) NOT NULL AUTO_INCREMENT,
  BlogPostBody VARCHAR(255) DEFAULT NULL,
  BlogPostTitle VARCHAR(255) DEFAULT NULL,
  DatePosted DATETIME DEFAULT NULL,
  MemberAuthorId BIGINT(20) NOT NULL,
  TopicId INT(11) NOT NULL,
  PRIMARY KEY (blogPostId),
  CONSTRAINT FK_5inikk8j7ckg06lkv3dvkrlvs FOREIGN KEY (TopicId)
    REFERENCES topic(topicId) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 2
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci;



--
-- Definition for table memberblogpostimages
--
CREATE TABLE IF NOT EXISTS memberblogpostimages (
  memberBlogPostImageId INT(11) NOT NULL AUTO_INCREMENT,
  FileName VARCHAR(255) NOT NULL,
  BlogPostId INT(11) NOT NULL,
  PRIMARY KEY (memberBlogPostImageId),
  UNIQUE INDEX FileName (FileName),
  CONSTRAINT FK_f6wu26348blwxqn4gsrx78hvi FOREIGN KEY (BlogPostId)
    REFERENCES memberblogpost(blogPostId) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Definition for table memberpostcomments
--
CREATE TABLE IF NOT EXISTS memberpostcomments (
  commentId INT(11) NOT NULL AUTO_INCREMENT,
  CommentBody VARCHAR(255) NOT NULL,
  DatePosted DATETIME DEFAULT NULL,
  BlogPostId INT(11) NOT NULL,
  MemberId BIGINT(20) NOT NULL,
  PRIMARY KEY (commentId),
  CONSTRAINT FK_lwi7u4ansh66xj4lykmrwwrrr FOREIGN KEY (BlogPostId)
    REFERENCES memberblogpost(blogPostId) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 3
AVG_ROW_LENGTH = 8192
CHARACTER SET utf8
COLLATE utf8_general_ci;


CREATE TABLE IF NOT EXISTS moderndrummerdb.memberimages (
  MemberImageId BIGINT(20) NOT NULL AUTO_INCREMENT,
  FileName VARCHAR(255) NOT NULL,
  MemberId INT(11) NOT NULL,
  PRIMARY KEY (MemberImageId),
  CONSTRAINT FK_58x9kummrl01gfiegei4psn51 FOREIGN KEY (MemberId)
    REFERENCES moderndrummerdb.member(MemberId) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci;


CREATE TABLE IF NOT EXISTS moderndrummerdb.memberprofileimage (
  MemberProfileImageId INT(11) NOT NULL AUTO_INCREMENT,
  MemberId INT(11) NOT NULL,
  MemberImageId BIGINT(20) NOT NULL ,
  PRIMARY KEY (MemberProfileImageId),
  CONSTRAINT FK_member FOREIGN KEY (MemberId)
    REFERENCES moderndrummerdb.member(MemberId) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT FK_memberimage FOREIGN KEY (MemberImageId)
    REFERENCES moderndrummerdb.memberimages(MemberImageId) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci;

-- 
-- Dumping data for table member
--

-- 
-- Dumping data for table memberbasic
--


-- 
-- Dumping data for table status
--

-- Table moderndrummerdb.status does not contain any data (it is empty)

-- 
-- Dumping data for table ticketflowtypes
--

-- Table moderndrummerdb.ticketflowtypes does not contain any data (it is empty)

-- 
-- Dumping data for table topic
--

INSERT INTO topic VALUES
(1, 'Topic about drumvideos and drummers', 'Drumvideos'),
(2, 'Topic about gigs for drummers', 'Gigs'),
(3, 'Bands looking for drummers', 'Bands'),
(4, 'Concerts with drummers', 'Concerts'),
(5, 'Drummers sharing drumlessons', 'Drum lessons');
;

-- 
-- Dumping data for table memberblogpost
--


-- 
-- Dumping data for table ticket
--

-- Table moderndrummerdb.ticket does not contain any data (it is empty)

-- 
-- Dumping data for table memberblogpostimages
--

-- Table moderndrummerdb.memberblogpostimages does not contain any data (it is empty)

-- 
-- Dumping data for table memberpostcomments
--


-- 
-- Dumping data for table statusonticket
--

-- Table moderndrummerdb.statusonticket does not contain any data (it is empty)

-- 
-- Dumping data for table ticketflow
--

-- Table moderndrummerdb.ticketflow does not contain any data (it is empty)

-- 
-- Restore previous SQL mode
-- 
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;

-- 
-- Enable foreign keys
-- 
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;