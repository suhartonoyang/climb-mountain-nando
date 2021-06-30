-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 30, 2021 at 03:19 AM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 7.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `climb-mountain`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking_dtl`
--

CREATE TABLE `booking_dtl` (
  `id` int(11) NOT NULL,
  `booking_hdr_id` int(11) NOT NULL,
  `climber_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `booking_hdr`
--

CREATE TABLE `booking_hdr` (
  `id` int(11) NOT NULL,
  `booking_number` varchar(255) DEFAULT NULL,
  `invoice_number` varchar(255) DEFAULT NULL,
  `climbing_schedule_id` int(11) NOT NULL,
  `date_return` date NOT NULL,
  `total_price` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `climber`
--

CREATE TABLE `climber` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `dob` date NOT NULL,
  `gender` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `province` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `identity_type` varchar(255) NOT NULL,
  `identity_number` varchar(255) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `family_phone_number` varchar(15) DEFAULT NULL,
  `occupation` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `age` int(11) NOT NULL,
  `has_ever_climb` tinyint(1) NOT NULL,
  `number_of_climbs` int(11) NOT NULL DEFAULT 0,
  `is_leader` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `climber_disease`
--

CREATE TABLE `climber_disease` (
  `id` int(11) NOT NULL,
  `climber_id` int(11) NOT NULL,
  `disease_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `climbing_schedule`
--

CREATE TABLE `climbing_schedule` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `quota` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `climbing_schedule`
--

INSERT INTO `climbing_schedule` (`id`, `date`, `quota`) VALUES
(1, '2021-06-07', 100),
(2, '2021-06-08', 100),
(3, '2021-06-09', 0),
(4, '2021-05-07', 100),
(5, '2021-05-08', 12),
(6, '2021-07-07', 100),
(7, '2021-07-08', 12),
(8, '2021-08-07', 100),
(9, '2021-08-08', 12);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking_dtl`
--
ALTER TABLE `booking_dtl`
  ADD PRIMARY KEY (`id`),
  ADD KEY `booking_dtl_fk01` (`climber_id`),
  ADD KEY `booking_dtl_fk02` (`booking_hdr_id`);

--
-- Indexes for table `booking_hdr`
--
ALTER TABLE `booking_hdr`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `booking_number_2` (`booking_number`),
  ADD KEY `booking_hdr_fk01` (`climbing_schedule_id`),
  ADD KEY `booking_number` (`booking_number`);

--
-- Indexes for table `climber`
--
ALTER TABLE `climber`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `climber_disease`
--
ALTER TABLE `climber_disease`
  ADD PRIMARY KEY (`id`),
  ADD KEY `climber_disease` (`climber_id`);

--
-- Indexes for table `climbing_schedule`
--
ALTER TABLE `climbing_schedule`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking_dtl`
--
ALTER TABLE `booking_dtl`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `booking_hdr`
--
ALTER TABLE `booking_hdr`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `climber`
--
ALTER TABLE `climber`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `climber_disease`
--
ALTER TABLE `climber_disease`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `climbing_schedule`
--
ALTER TABLE `climbing_schedule`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking_dtl`
--
ALTER TABLE `booking_dtl`
  ADD CONSTRAINT `booking_dtl_fk01` FOREIGN KEY (`climber_id`) REFERENCES `climber` (`id`),
  ADD CONSTRAINT `booking_dtl_fk02` FOREIGN KEY (`booking_hdr_id`) REFERENCES `booking_hdr` (`id`);

--
-- Constraints for table `booking_hdr`
--
ALTER TABLE `booking_hdr`
  ADD CONSTRAINT `booking_hdr_fk01` FOREIGN KEY (`climbing_schedule_id`) REFERENCES `climbing_schedule` (`id`);

--
-- Constraints for table `climber_disease`
--
ALTER TABLE `climber_disease`
  ADD CONSTRAINT `climber_disease` FOREIGN KEY (`climber_id`) REFERENCES `climber` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
