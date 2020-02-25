-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 25, 2020 at 11:17 AM
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
(1, '1', 'comment2', '2020-02-25 08:55:50', '2020-02-25 08:55:50', 12, 'Dont have', 'completed', '15951951959', 11),
(2, '2', 'comment2', '2020-02-25 08:54:20', '2020-02-25 08:54:20', 13, 'Dont have', 'wait', '15951951959', 11),
(3, '3', 'comment2', '2020-02-25 08:54:22', '2020-02-25 08:54:22', 15, 'Dont have', 'wait', '15951951959', 11),
(4, '4', 'comment2', '2020-02-25 08:56:21', '2020-02-25 08:56:21', 12, 'Dont have', 'completed', '15951951959', 11),
(5, '5', 'comment2', '2020-02-25 08:55:22', '2020-02-25 08:55:22', 13, 'Dont have', 'wait', '15951951959', 11),
(6, '6', 'comment2', '2020-02-25 08:55:29', '2020-02-25 08:55:29', 15, 'Dont have', 'wait', '15951951959', 11),
(7, '7', 'comment2', '2020-02-25 08:57:30', '2020-02-25 08:57:30', 12, 'Dont have', 'completed', '15951951959', 11),
(8, '8', 'comment2', '2020-02-25 08:56:26', '2020-02-25 08:56:26', 13, 'Dont have', 'wait', '15951951959', 11),
(9, '9', 'comment2', '2020-02-25 10:11:35', '2020-02-25 10:11:35', 12, 'Dont have', 'completed', '15951951959', 11),
(10, '10', 'comment2', '2020-02-25 08:57:34', '2020-02-25 08:57:34', 12, 'Dont have', 'wait', '15951951959', 11),
(11, '11', 'comment2', '2020-02-25 08:57:35', '2020-02-25 08:57:35', 12, 'Dont have', 'wait', '15951951959', 11),
(12, '12', 'comment2', '2020-02-25 08:57:37', '2020-02-25 08:57:37', 15, 'Dont have', 'wait', '15951951959', 11),
(13, '13', 'comment2', '2020-02-25 10:09:22', '2020-02-25 10:09:22', 12, 'have', 'completed', '15951951959', 11),
(14, '14', 'comment2', '2020-02-25 08:57:39', '2020-02-25 08:57:39', 13, 'Dont have', 'wait', '15951951959', 11),
(15, '15', 'comment2', '2020-02-25 08:57:40', '2020-02-25 08:57:40', 15, 'Dont have', 'wait', '15951951959', 11),
(16, '16', 'comment2', '2020-02-25 09:01:48', '2020-02-25 09:01:48', 12, 'Dont have', 'wait', '15951951959', 11),
(17, '17', 'comment2', '2020-02-25 09:01:49', '2020-02-25 09:01:49', 13, 'Dont have', 'wait', '15951951959', 11),
(18, '18', 'comment2', '2020-02-25 09:01:50', '2020-02-25 09:01:50', 15, 'Dont have', 'wait', '15951951959', 11),
(19, '19', 'comment2', '2020-02-25 09:01:51', '2020-02-25 09:01:51', 12, 'Dont have', 'wait', '15951951959', 11),
(20, '20', 'comment2', '2020-02-25 09:03:05', '2020-02-25 09:03:05', 13, 'Dont have', 'wait', '15951951959', 11),
(21, '21', 'comment2', '2020-02-25 09:03:07', '2020-02-25 09:03:07', 15, 'Dont have', 'wait', '15951951959', 11);

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
(12, 'technician1', '$2b$10$2U6dPyms1ppqU9TlUpJXL.SF43rRjE4vUbCiOdu0.T6vpxAf4XwSm', 'technician1', 'technician', 1, '2020-02-25 08:54:00', '2020-02-25 08:54:00'),
(13, 'technician2', '$2b$10$2U6dPyms1ppqU9TlUpJXL.SF43rRjE4vUbCiOdu0.T6vpxAf4XwSm', 'technician2', 'technician', 1, '2020-02-25 07:53:51', '2020-02-25 07:53:51'),
(14, 'receptionist2', '$2b$10$2U6dPyms1ppqU9TlUpJXL.SF43rRjE4vUbCiOdu0.T6vpxAf4XwSm', 'receptionist2', 'receptionist', 1, '2019-11-05 16:28:18', '2019-11-05 16:28:18'),
(15, 'technician3', '$2b$10$2U6dPyms1ppqU9TlUpJXL.SF43rRjE4vUbCiOdu0.T6vpxAf4XwSm', 'technician3', 'technician', 1, '2020-02-25 07:53:48', '2020-02-25 07:53:48'),
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

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
