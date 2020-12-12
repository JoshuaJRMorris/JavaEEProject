/*
	Name: Josh Morris
	Date: November 12 2020
	Purpose: Final Java SQL Script
	Teammate: Jacob Shannon
*/

DROP DATABASE IF EXISTS `ShanMorr`;
CREATE DATABASE `ShanMorr`;
USE `ShanMorr`;

DROP TABLE IF EXISTS `ShanMorr.Employee`;
CREATE TABLE ShanMorr.Employee(

	`EmployeeID`		INT						NOT NULL AUTO_INCREMENT,
	`FirstName`			VARCHAR(255)			NOT NULL,
	`LastName`			VARCHAR(255)					,
	`SIN`				VARCHAR(9) NOT NULL UNIQUE, CHECK(LEN(SIN)=9), CHECK(SIN NOT LIKE '[A-Z][A-Z][A-Z][A-Z][A-Z][A-Z][A-Z][A-Z][A-Z]'),
	`HourlyPay`			DOUBLE(19,4)			NOT NULL,
	`DateOfCreation`	DATE					 NULL,
    PRIMARY KEY (`EmployeeID`)

)ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `ShanMorr.Job`;
CREATE TABLE ShanMorr.Job(
	
	`JobID`				INT						NOT NULL AUTO_INCREMENT,
	`ClientName`		VARCHAR(255)			NOT NULL,
	`Description`		VARCHAR(255)					,

    PRIMARY KEY (`JobID`)

)ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `ShanMorr.Task`;
CREATE TABLE ShanMorr.Task(
	
	`TaskID`			INT						NOT NULL AUTO_INCREMENT,
	`Name`				VARCHAR(255)			NOT NULL,
	`Description`		VARCHAR(255)					,
	`Duration`			INT(7)							,
	`JobID`				INT								,
    PRIMARY KEY (`TaskID`)								,
    KEY `JobID` (`JobID`),
    CONSTRAINT `task_fk_1` FOREIGN KEY (`JobID`) REFERENCES `Job`(`JobID`)

)ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `ShanMorr.Team`;
CREATE TABLE ShanMorr.Team(
	
	`TeamID`		INT				NOT NULL AUTO_INCREMENT,
    `Name`			VARCHAR(255)	NOT NULL,
	`JobID`			INT									   ,
    PRIMARY KEY (`TeamID`)   							   ,
    KEY `JobID` (`JobID`),
    CONSTRAINT `team_fk_1` FOREIGN KEY (`JobID`) REFERENCES `Job`(`JobID`)

)ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `ShanMorr.EmployeeSkillSet`;
CREATE TABLE ShanMorr.EmployeeSkillSet(
	
	`SkillSetID`		INT				NOT NULL AUTO_INCREMENT,
    `EmployeeID`		INT				,
	`Java`				TINYINT,
    `C++`				TINYINT,
    `C#`				TINYINT,
    `SQL`				TINYINT,
    `HTML`				TINYINT,
    `CSS`				TINYINT,
    `JavaScript`		TINYINT,
    `Others`			VARCHAR(105),
    PRIMARY KEY (`SkillSetID`)   							   ,
    KEY `EmployeeID` (`EmployeeID`),
    CONSTRAINT `Sk_fk_1` FOREIGN KEY (`EmployeeID`) REFERENCES `Employee`(`EmployeeID`)

)ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=latin1;


