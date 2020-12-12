/*
	Name: Josh Morris
	Date: November 12 2020
	Purpose: Final Java, Testing inserting into tables
	Teammate: Jacob Shannon
*/

INSERT INTO ShanMorr.dbo.Employee
VALUES
--('Josh', 'Morris', 999999999, 25.36, '2020-11-11'),
('Jacob', 'Shannon', 256999875, 25.36, '2020-11-11'),
('Ronald', 'Reagan', 125458785, 25.36, '2020-11-11');


--Testing to make sure the SIN constraints work
--by length 
INSERT INTO ShanMorr.dbo.Employee
VALUES
('Jacob', 'Shannon', 12345678, 25.36, '2020-11-11');

--by number only
INSERT INTO ShanMorr.dbo.Employee
VALUES
('Jacob', 'Shannon', 'aaaaaaaaa', 25.36, '2020-11-11');

--by length and number
INSERT INTO ShanMorr.dbo.Employee
VALUES
('Jacob', 'Shannon', 'aaaaaaaa', 25.36, '2020-11-11');



--SELECT * FROM ShanMorr.dbo.Employee

--TRUNCATE TABLE ShanMorr.dbo.Employee

--DROP TABLE ShanMorr.dbo.Employee

--initial testing of constraints work - cannot insert duplicate SINs, SINs containing letters, or SINs that are too short.
--have to check on the SIN lengths, they may have changed over time, I'm not 100% sure 