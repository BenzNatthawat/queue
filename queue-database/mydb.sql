-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 24, 2020 at 11:28 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mydb`
--

-- --------------------------------------------------------

--
-- Table structure for table `queues`
--

CREATE TABLE `queues` (
  `id` int(11) NOT NULL,
  `queueNumber` varchar(45) DEFAULT NULL,
  `comment` varchar(45) DEFAULT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `technicians__id` int(11) NOT NULL,
  `insurance` enum('have','Dont have') NOT NULL DEFAULT 'Dont have',
  `status` enum('wait','proceed','completed') NOT NULL DEFAULT 'wait',
  `jobnumber` varchar(20) NOT NULL,
  `users__id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `queues`
--

INSERT INTO `queues` (`id`, `queueNumber`, `comment`, `createdAt`, `updatedAt`, `technicians__id`, `insurance`, `status`, `jobnumber`, `users__id`) VALUES
(5, '1', 'hu', '2019-11-09 13:44:15', '2019-11-09 13:44:15', 12, '', 'wait', '', 11),
(6, '2', 'hu', '2019-11-09 13:44:17', '2019-11-09 13:44:17', 13, '', 'wait', '', 11);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `role` enum('receptionist','technician') DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `name`, `role`, `status`, `createdAt`, `updatedAt`) VALUES
(11, 'receptionist1', '$2b$10$2U6dPyms1ppqU9TlUpJXL.SF43rRjE4vUbCiOdu0.T6vpxAf4XwSm', 'receptionist1', 'receptionist', 1, '2019-11-05 16:28:09', '2019-11-05 16:28:09'),
(12, 'technician1', '$2b$10$2U6dPyms1ppqU9TlUpJXL.SF43rRjE4vUbCiOdu0.T6vpxAf4XwSm', 'technician1', 'technician', 0, '2020-01-31 06:08:53', '2020-01-31 06:08:53'),
(13, 'technician2', '$2b$10$2U6dPyms1ppqU9TlUpJXL.SF43rRjE4vUbCiOdu0.T6vpxAf4XwSm', 'technician2', 'technician', 0, '2020-01-31 04:20:33', '2020-01-31 04:20:33'),
(14, 'receptionist2', '$2b$10$2U6dPyms1ppqU9TlUpJXL.SF43rRjE4vUbCiOdu0.T6vpxAf4XwSm', 'receptionist2', 'receptionist', 1, '2019-11-05 16:28:18', '2019-11-05 16:28:18'),
(15, 'technician3', '$2b$10$2U6dPyms1ppqU9TlUpJXL.SF43rRjE4vUbCiOdu0.T6vpxAf4XwSm', 'technician3', 'technician', 0, '2020-01-31 04:20:36', '2020-01-31 04:20:36'),
(16, 'receptionist4', '$2b$10$eyts9f9t9mBxNI8RwJEHZuyJPqzIEgWDeJyFZEOni1cnbVoDSvHoy', 'receptionist4', 'receptionist', 1, '2020-01-31 04:20:46', '2020-01-31 04:20:46');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `queues`
--
ALTER TABLE `queues`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_queues_users1_idx` (`users__id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username_UNIQUE` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `queues`
--
ALTER TABLE `queues`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `queues`
--
ALTER TABLE `queues`
  ADD CONSTRAINT `fk_queues_users1` FOREIGN KEY (`users__id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
