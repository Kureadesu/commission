-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 13, 2024 at 01:26 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `enrollmentsys`
--

-- --------------------------------------------------------

--
-- Table structure for table `application_development_and_emerging_technologies__android_`
--

CREATE TABLE `application_development_and_emerging_technologies__android_` (
  `StudentID` varchar(50) NOT NULL,
  `SName` varchar(255) DEFAULT NULL,
  `Section` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `information_management`
--

CREATE TABLE `information_management` (
  `StudentID` varchar(50) NOT NULL,
  `SName` varchar(255) DEFAULT NULL,
  `Section` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `logindataadmin`
--

CREATE TABLE `logindataadmin` (
  `ID` int(11) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `logindataadmin`
--

INSERT INTO `logindataadmin` (`ID`, `Name`, `Email`, `Password`) VALUES
(1, 'Admin', NULL, 'Admin123');

-- --------------------------------------------------------

--
-- Table structure for table `logindatafaculty`
--

CREATE TABLE `logindatafaculty` (
  `FacultyID` int(11) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Department` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `Address` text DEFAULT NULL,
  `Course` varchar(255) DEFAULT NULL,
  `YrSec` varchar(255) DEFAULT NULL,
  `ContactNum` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `logindatafaculty`
--

INSERT INTO `logindatafaculty` (`FacultyID`, `Name`, `Email`, `Department`, `Password`, `DOB`, `Address`, `Course`, `YrSec`, `ContactNum`, `id`) VALUES
(NULL, 'kurea', 'test1@domain.com', NULL, '1234', NULL, NULL, NULL, NULL, NULL, 1),
(NULL, 'faculty2', 't2@domain.com', NULL, '1234', NULL, NULL, NULL, NULL, NULL, 2),
(NULL, 'kurea', 'test@email', NULL, '1234', NULL, NULL, NULL, NULL, NULL, 3),
(1111, NULL, 'test@email', 'Institute of Engineering Technology', NULL, '1999-01-01', '1234', NULL, NULL, '1234', 4),
(NULL, 'meow', 'test2@email', NULL, '1234', NULL, NULL, NULL, NULL, NULL, 5);

-- --------------------------------------------------------

--
-- Table structure for table `logindatastudent`
--

CREATE TABLE `logindatastudent` (
  `StudentID` varchar(50) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `Address` text DEFAULT NULL,
  `Course` varchar(255) DEFAULT NULL,
  `YrSec` varchar(255) DEFAULT NULL,
  `ContactNum` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `logindatastudent`
--

INSERT INTO `logindatastudent` (`StudentID`, `Name`, `Email`, `Password`, `DOB`, `Address`, `Course`, `YrSec`, `ContactNum`, `id`) VALUES
('12223MN-000540', 'claire balaba', 'balaba.claire2003@gmail.com', 'kUreadesu3', '0000-00-00', '', '', '', '', 2),
(NULL, 'john', 'stud1@email', '1234', NULL, NULL, NULL, NULL, NULL, 4);

-- --------------------------------------------------------

--
-- Table structure for table `subject_list`
--

CREATE TABLE `subject_list` (
  `ID` int(11) NOT NULL,
  `SubjectID` varchar(50) NOT NULL,
  `SubjectName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `subject_list`
--

INSERT INTO `subject_list` (`ID`, `SubjectID`, `SubjectName`) VALUES
(1, 'IS 311', 'Information Management'),
(2, 'IS 313', 'Application Development and Emerging Technologies (Android)');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `application_development_and_emerging_technologies__android_`
--
ALTER TABLE `application_development_and_emerging_technologies__android_`
  ADD PRIMARY KEY (`StudentID`);

--
-- Indexes for table `information_management`
--
ALTER TABLE `information_management`
  ADD PRIMARY KEY (`StudentID`);

--
-- Indexes for table `logindataadmin`
--
ALTER TABLE `logindataadmin`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `logindatafaculty`
--
ALTER TABLE `logindatafaculty`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FacultyID` (`FacultyID`),
  ADD KEY `Name` (`Name`),
  ADD KEY `Department` (`Department`);

--
-- Indexes for table `logindatastudent`
--
ALTER TABLE `logindatastudent`
  ADD PRIMARY KEY (`id`),
  ADD KEY `StudentID` (`StudentID`,`Name`),
  ADD KEY `Course` (`Course`),
  ADD KEY `YrSec` (`YrSec`);

--
-- Indexes for table `subject_list`
--
ALTER TABLE `subject_list`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `logindatafaculty`
--
ALTER TABLE `logindatafaculty`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `logindatastudent`
--
ALTER TABLE `logindatastudent`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `subject_list`
--
ALTER TABLE `subject_list`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
