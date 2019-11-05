-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 05, 2019 at 06:26 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

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
  `createdAt` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `updatedAt` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `technicians__id` int(11) NOT NULL,
  `users__id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `queues`
--

INSERT INTO `queues` (`id`, `queueNumber`, `comment`, `createdAt`, `updatedAt`, `technicians__id`, `users__id`) VALUES
(6, '1', 'comment', '2019-11-05 16:55:35', '2019-11-05 16:55:35', 12, 11),
(7, '2', 'comment', '2019-11-05 17:04:31', '2019-11-05 17:04:31', 12, 11),
(8, '3', 'comment', '2019-11-05 17:04:35', '2019-11-05 17:04:35', 12, 11),
(9, '4', 'comment', '2019-11-05 17:04:39', '2019-11-05 17:04:39', 12, 11),
(10, '5', 'comment', '2019-11-05 17:04:52', '2019-11-05 17:04:52', 12, 11),
(11, '6', 'comment', '2019-11-05 17:04:55', '2019-11-05 17:04:55', 12, 11),
(12, '7', 'comment', '2019-11-05 17:04:57', '2019-11-05 17:04:57', 12, 11),
(13, '8', 'comment', '2019-11-05 17:05:38', '2019-11-05 17:05:38', 13, 11),
(14, '9', 'comment', '2019-11-05 17:05:39', '2019-11-05 17:05:39', 15, 11),
(15, '10', 'comment', '2019-11-05 17:06:21', '2019-11-05 17:06:21', 12, 11),
(16, '11', 'comment', '2019-11-05 17:06:22', '2019-11-05 17:06:22', 13, 11),
(17, '12', 'comment', '2019-11-05 17:06:23', '2019-11-05 17:06:23', 15, 11),
(18, '13', 'comment', '2019-11-05 17:06:24', '2019-11-05 17:06:24', 12, 11),
(19, '14', 'comment', '2019-11-05 17:06:25', '2019-11-05 17:06:25', 13, 11),
(20, '15', 'comment', '2019-11-05 17:06:25', '2019-11-05 17:06:25', 15, 11),
(21, '16', 'comment', '2019-11-05 17:06:26', '2019-11-05 17:06:26', 12, 11),
(22, '17', 'comment', '2019-11-05 17:06:27', '2019-11-05 17:06:27', 13, 11);

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
  `createdAt` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `updatedAt` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `name`, `role`, `status`, `createdAt`, `updatedAt`) VALUES
(11, 'receptionist1', '$2b$10$2U6dPyms1ppqU9TlUpJXL.SF43rRjE4vUbCiOdu0.T6vpxAf4XwSm', 'receptionist1', 'receptionist', 1, '2019-11-05 16:28:09', '2019-11-05 16:28:09'),
(12, 'technician1', '$2b$10$2U6dPyms1ppqU9TlUpJXL.SF43rRjE4vUbCiOdu0.T6vpxAf4XwSm', 'technician1', 'technician', 1, '2019-11-05 16:28:12', '2019-11-05 16:28:12'),
(13, 'technician2', '$2b$10$2U6dPyms1ppqU9TlUpJXL.SF43rRjE4vUbCiOdu0.T6vpxAf4XwSm', 'technician2', 'technician', 1, '2019-11-05 16:28:15', '2019-11-05 16:28:15'),
(14, 'receptionist2', '$2b$10$2U6dPyms1ppqU9TlUpJXL.SF43rRjE4vUbCiOdu0.T6vpxAf4XwSm', 'receptionist2', 'receptionist', 1, '2019-11-05 16:28:18', '2019-11-05 16:28:18'),
(15, 'technician3', '$2b$10$2U6dPyms1ppqU9TlUpJXL.SF43rRjE4vUbCiOdu0.T6vpxAf4XwSm', 'technician3', 'technician', 1, '2019-11-05 16:28:21', '2019-11-05 16:28:21');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

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
