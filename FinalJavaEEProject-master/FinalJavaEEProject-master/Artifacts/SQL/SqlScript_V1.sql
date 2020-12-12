/*
	Name: Josh Morris
	Date: November 12 2020
	Purpose: Final Java SQL Script
	Teammate: Jacob Shannon
*/
IF DB_ID('ShanMorr') IS NOT NULL
	PRINT 'Database exists'
ELSE
	BEGIN
		PRINT 'Creating database ShanMorr'
		CREATE DATABASE ShanMorr
	END

GO
USE ShanMorr;
GO

CREATE TABLE ShanMorr.dbo.Employee(

	EmployeeID		INT		PRIMARY KEY		IDENTITY,
	FirstName		VARCHAR(MAX)			NOT NULL,
	LastName		VARCHAR(MAX)					,
	SIN				VARCHAR(9) NOT NULL UNIQUE, CHECK(LEN(SIN)=9), CHECK(SIN NOT LIKE '[A-Z][A-Z][A-Z][A-Z][A-Z][A-Z][A-Z][A-Z][A-Z]'),
	HourlyPay		DECIMAL(19,4)			NOT NULL,
	DateOfCreation	DATE					NOT NULL

);

GO

CREATE TABLE ShanMorr.dbo.Job(
	
	JobID			INT		PRIMARY KEY		IDENTITY,
	Description		VARCHAR(MAX)					,
	ClientName		VARCHAR(MAX)			NOT NULL,
	StartDate		DATE					NOT NULL,
	EndDate			DATE					NOT NULL

);

GO

CREATE TABLE ShanMorr.dbo.Task(
	
	TaskID			INT		PRIMARY KEY		IDENTITY,
	Name			VARCHAR(MAX)			NOT NULL,
	Description		VARCHAR(MAX)					,
	Duration		TIME(7)							,
	JobID			INT								,
	FOREIGN KEY (JobID) REFERENCES Job(JobID)

);

GO

CREATE TABLE ShanMorr.dbo.Team(
	
	TeamID			INT		PRIMARY KEY		IDENTITY,
	JobID			INT								,
	FOREIGN KEY (JobID) REFERENCES Job(JobID)

);


PRINT 'End of Script';