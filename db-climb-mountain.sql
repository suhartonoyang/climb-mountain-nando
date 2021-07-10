-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 05, 2021 at 01:04 PM
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
  `climber_id` int(11) NOT NULL,
  `is_need_tour_guide` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `booking_dtl`
--

INSERT INTO `booking_dtl` (`id`, `booking_hdr_id`, `climber_id`, `is_need_tour_guide`) VALUES
(13, 7, 19, 1),
(14, 7, 20, 1),
(15, 7, 21, 1),
(16, 8, 22, 0),
(17, 8, 23, 0);

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
  `total_price` bigint(20) NOT NULL,
  `is_need_tour_guide` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `booking_hdr`
--

INSERT INTO `booking_hdr` (`id`, `booking_number`, `invoice_number`, `climbing_schedule_id`, `date_return`, `total_price`, `is_need_tour_guide`) VALUES
(7, 'CMT-20210607-00007', 'INV-20210630-00007', 1, '2021-06-08', 75000, 1),
(8, 'CMT-20210607-00008', 'INV-20210630-00008', 1, '2021-06-09', 50000, 0);

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

--
-- Dumping data for table `climber`
--

INSERT INTO `climber` (`id`, `name`, `dob`, `gender`, `address`, `province`, `city`, `identity_type`, `identity_number`, `phone_number`, `family_phone_number`, `occupation`, `email`, `age`, `has_ever_climb`, `number_of_climbs`, `is_leader`) VALUES
(19, 'Cin', '1995-06-27', 'Perempuan', 'Jl. hj senin no 10', 'DKI Jakarta', 'Jakarta', 'KTP', '12345678901234567890', '081290908989', '081209097878', 'Karyawan Swasta', 'cincin@haha.com', 26, 0, 0, 1),
(20, 'Dave', '1995-01-27', 'Laki Laki', 'Jl. hj senin no 11', 'DKI Jakarta', 'Jakarta', 'KTP', '09876543211234567890', '081290909090', '081209097979', 'Karyawan Swasta', 'dave@haha.com', 26, 0, 0, 0),
(21, 'Edward', '1995-03-27', 'Laki Laki', 'Jl. hj senin no 12', 'DKI Jakarta', 'Jakarta', 'KTP', '09876543210987654321', '081290909191', '081209098080', 'Karyawan Swasta', 'edward@haha.com', 26, 0, 0, 0),
(22, 'Roy', '1995-06-27', 'Perempuan', 'Jl. hj senin no 10', 'DKI Jakarta', 'Jakarta', 'KTP', '12345678901234567890', '081290908989', '081209097878', 'Karyawan Swasta', 'roy@haha.com', 26, 0, 0, 1),
(23, 'Suryo', '1995-01-27', 'Laki Laki', 'Jl. hj senin no 11', 'DKI Jakarta', 'Jakarta', 'KTP', '09876543211234567890', '081290909090', '081209097979', 'Karyawan Swasta', 'suryo@haha.com', 26, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `climber_disease`
--

CREATE TABLE `climber_disease` (
  `id` int(11) NOT NULL,
  `climber_id` int(11) NOT NULL,
  `disease_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `climber_disease`
--

INSERT INTO `climber_disease` (`id`, `climber_id`, `disease_name`) VALUES
(25, 19, 'demam'),
(26, 19, 'batuk'),
(27, 20, 'diare'),
(28, 20, 'mual'),
(29, 22, 'demam'),
(30, 22, 'batuk'),
(31, 23, 'diare'),
(32, 23, 'mual');

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

-- --------------------------------------------------------

--
-- Table structure for table `data_bayes_climber`
--

CREATE TABLE `data_bayes_climber` (
  `id` int(11) NOT NULL,
  `age` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `has_ever_climb` tinyint(1) NOT NULL,
  `has_disease` tinyint(1) NOT NULL,
  `result` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `data_bayes_climber`
--

INSERT INTO `data_bayes_climber` (`id`, `age`, `gender`, `has_ever_climb`, `has_disease`, `result`) VALUES
(1, '20', 'L', 1, 0, 'Tidak Butuh'),
(2, '25', 'P', 0, 0, 'Butuh'),
(3, '35', 'L', 0, 1, 'Butuh'),
(4, '30', 'L', 0, 1, 'Butuh');

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
-- Indexes for table `data_bayes_climber`
--
ALTER TABLE `data_bayes_climber`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking_dtl`
--
ALTER TABLE `booking_dtl`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `booking_hdr`
--
ALTER TABLE `booking_hdr`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `climber`
--
ALTER TABLE `climber`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `climber_disease`
--
ALTER TABLE `climber_disease`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `climbing_schedule`
--
ALTER TABLE `climbing_schedule`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `data_bayes_climber`
--
ALTER TABLE `data_bayes_climber`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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
