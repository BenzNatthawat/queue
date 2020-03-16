-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 16, 2020 at 08:31 AM
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
(1, '1', 'comment2', '2020-02-27 10:41:19', '2020-02-27 10:41:19', 12, 'Dont have', 'completed', '15951951959', 11),
(2, '2', 'comment2', '2020-02-25 08:54:20', '2020-02-25 08:54:20', 13, 'Dont have', 'wait', '15951951959', 11),
(3, '3', 'comment2', '2020-02-25 08:54:22', '2020-02-25 08:54:22', 15, 'Dont have', 'wait', '15951951959', 11),
(4, '4', 'comment2', '2020-02-25 08:56:21', '2020-02-25 08:56:21', 12, 'Dont have', 'completed', '15951951959', 11),
(5, '5', 'comment2', '2020-02-25 08:55:22', '2020-02-25 08:55:22', 13, 'Dont have', 'wait', '15951951959', 11),
(6, '6', 'comment2', '2020-02-25 08:55:29', '2020-02-25 08:55:29', 15, 'Dont have', 'wait', '15951951959', 11),
(7, '7', 'comment2', '2020-02-27 10:41:01', '2020-02-27 10:41:01', 12, 'Dont have', 'completed', '15951951959', 11),
(8, '8', 'comment2', '2020-02-25 08:56:26', '2020-02-25 08:56:26', 13, 'Dont have', 'wait', '15951951959', 11),
(9, '9', 'comment2', '2020-02-25 10:11:35', '2020-02-25 10:11:35', 12, 'Dont have', 'completed', '15951951959', 11),
(10, '10', 'comment2', '2020-02-27 10:41:12', '2020-02-27 10:41:12', 12, 'Dont have', 'completed', '15951951959', 11),
(11, '11', 'comment2', '2020-02-27 10:40:55', '2020-02-27 10:40:55', 12, 'Dont have', 'completed', '15951951959', 11),
(12, '12', 'comment2', '2020-02-25 08:57:37', '2020-02-25 08:57:37', 15, 'Dont have', 'wait', '15951951959', 11),
(13, '13', 'comment2', '2020-02-25 10:09:22', '2020-02-25 10:09:22', 12, 'have', 'completed', '15951951959', 11),
(14, '14', 'comment2', '2020-02-25 08:57:39', '2020-02-25 08:57:39', 13, 'Dont have', 'wait', '15951951959', 11),
(15, '15', 'comment2', '2020-02-25 08:57:40', '2020-02-25 08:57:40', 15, 'Dont have', 'wait', '15951951959', 11),
(16, '16', 'comment2', '2020-02-27 10:34:10', '2020-02-27 10:34:10', 12, 'Dont have', 'completed', '15951951959', 11),
(17, '17', 'comment2', '2020-02-25 09:01:49', '2020-02-25 09:01:49', 13, 'Dont have', 'wait', '15951951959', 11),
(18, '18', 'comment2', '2020-02-25 09:01:50', '2020-02-25 09:01:50', 15, 'Dont have', 'wait', '15951951959', 11),
(19, '19', 'comment2', '2020-02-27 10:40:46', '2020-02-27 10:40:46', 12, 'have', 'completed', '15951951959', 11),
(20, '20', 'comment2', '2020-02-25 09:03:05', '2020-02-25 09:03:05', 13, 'Dont have', 'wait', '15951951959', 11),
(21, '21', 'comment2', '2020-02-25 09:03:07', '2020-02-25 09:03:07', 15, 'Dont have', 'wait', '15951951959', 11),
(26, '22', '', '2020-02-26 08:58:50', '2020-02-26 08:58:50', 13, 'Dont have', 'wait', '', 11),
(27, '23', 'fefwefwe', '2020-02-26 08:58:52', '2020-02-26 08:58:52', 15, 'Dont have', 'wait', '', 11),
(28, '24', 'wefwefwefefe', '2020-02-26 08:58:54', '2020-02-26 08:58:54', 13, 'Dont have', 'wait', '', 11),
(29, '25', 'broke', '2020-02-25 16:59:36', '2020-02-25 16:59:36', 15, 'have', 'wait', '156251', 11),
(30, '26', '', '2020-02-25 16:59:40', '2020-02-25 16:59:40', 13, 'Dont have', 'wait', '', 11),
(31, '1', '', '2020-02-25 17:00:45', '2020-02-25 17:00:45', 15, 'have', 'wait', '', 11),
(32, '2', '', '2020-02-25 17:00:47', '2020-02-25 17:00:47', 13, 'Dont have', 'wait', '', 11),
(33, '3', 'undefined', '2020-02-26 08:58:57', '2020-02-26 08:58:57', 12, 'Dont have', 'completed', '', 12),
(34, '4', 'undefined', '2020-02-26 09:46:46', '2020-02-26 09:46:46', 12, 'Dont have', 'completed', '', 12),
(35, '5', 'undefined', '2020-02-26 09:46:59', '2020-02-26 09:46:59', 12, 'Dont have', 'completed', '', 12),
(36, '6', 'undefined', '2020-02-27 10:34:14', '2020-02-27 10:34:14', 12, 'Dont have', 'completed', '', 12),
(37, '7', 'undefined', '2020-02-27 10:34:21', '2020-02-27 10:34:21', 12, 'Dont have', 'completed', '', 12),
(38, '8', 'fbf', '2020-02-27 10:41:57', '2020-02-27 10:41:57', 15, 'Dont have', 'wait', '45656756', 11),
(39, '9', '45678', '2020-02-27 10:42:05', '2020-02-27 10:42:05', 13, 'Dont have', 'wait', '56789876', 11),
(40, '10', 'rgsrg', '2020-02-27 10:42:11', '2020-02-27 10:42:11', 15, 'Dont have', 'wait', '', 11),
(41, '11', 'rgsergreg', '2020-02-27 10:42:14', '2020-02-27 10:42:14', 13, 'Dont have', 'wait', '', 11),
(42, '12', 'rgsrgsrgsrg', '2020-02-27 10:42:17', '2020-02-27 10:42:17', 15, 'Dont have', 'wait', '', 11),
(43, '13', 'srdgsrgsreg', '2020-02-27 10:42:20', '2020-02-27 10:42:20', 13, 'Dont have', 'wait', '', 11),
(44, '14', 'wefwefwefe', '2020-02-27 10:43:54', '2020-02-27 10:43:54', 15, 'Dont have', 'wait', '', 11),
(45, '15', 'rerververv', '2020-02-27 10:55:07', '2020-02-27 10:55:07', 13, 'Dont have', 'wait', '', 11),
(46, '16', 'rvrvrvrv', '2020-02-27 10:55:10', '2020-02-27 10:55:10', 15, 'Dont have', 'wait', '', 11),
(47, '17', 'rvrvr', '2020-02-27 10:55:20', '2020-02-27 10:55:20', 13, 'have', 'wait', 'rvrvrv', 11),
(48, '18', 'ededr', '2020-02-27 10:58:04', '2020-02-27 10:58:04', 15, 'Dont have', 'wait', 'ddd', 11),
(49, '19', '', '2020-02-27 10:58:05', '2020-02-27 10:58:05', 13, 'Dont have', 'wait', '', 11),
(50, '20', '', '2020-02-27 10:58:05', '2020-02-27 10:58:05', 15, 'Dont have', 'wait', '', 11),
(51, '21', '', '2020-02-27 10:58:06', '2020-02-27 10:58:06', 13, 'Dont have', 'wait', '', 11),
(52, '22', '', '2020-02-27 10:58:06', '2020-02-27 10:58:06', 15, 'Dont have', 'wait', '', 11),
(53, '23', '', '2020-02-27 10:58:06', '2020-02-27 10:58:06', 13, 'Dont have', 'wait', '', 11),
(54, '24', '', '2020-02-27 10:58:06', '2020-02-27 10:58:06', 15, 'Dont have', 'wait', '', 11),
(55, '25', '', '2020-02-27 10:58:07', '2020-02-27 10:58:07', 13, 'Dont have', 'wait', '', 11),
(56, '26', '', '2020-02-27 10:58:07', '2020-02-27 10:58:07', 15, 'Dont have', 'wait', '', 11),
(57, '27', '', '2020-02-27 10:58:09', '2020-02-27 10:58:09', 13, 'Dont have', 'wait', '', 11),
(58, '28', '', '2020-02-27 10:58:09', '2020-02-27 10:58:09', 15, 'Dont have', 'wait', '', 11),
(59, '29', '', '2020-02-27 10:58:10', '2020-02-27 10:58:10', 13, 'Dont have', 'wait', '', 11),
(60, '30', '', '2020-02-27 10:58:10', '2020-02-27 10:58:10', 15, 'Dont have', 'wait', '', 11),
(61, '31', '', '2020-02-27 10:58:10', '2020-02-27 10:58:10', 13, 'Dont have', 'wait', '', 11),
(62, '32', '', '2020-02-27 10:58:10', '2020-02-27 10:58:10', 15, 'Dont have', 'wait', '', 11),
(63, '33', '', '2020-02-27 10:58:11', '2020-02-27 10:58:11', 13, 'Dont have', 'wait', '', 11),
(64, '34', '', '2020-02-27 10:58:11', '2020-02-27 10:58:11', 15, 'Dont have', 'wait', '', 11),
(65, '35', '', '2020-02-27 10:58:11', '2020-02-27 10:58:11', 13, 'Dont have', 'wait', '', 11),
(66, '36', '', '2020-02-27 10:58:17', '2020-02-27 10:58:17', 15, 'have', 'wait', '', 11);

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
(11, 're1', '$2b$10$2U6dPyms1ppqU9TlUpJXL.SF43rRjE4vUbCiOdu0.T6vpxAf4XwSm', 'receptionist1', 'receptionist', 1, '2020-02-25 16:09:31', '2020-02-25 16:09:31'),
(12, 'te1', '$2b$10$2U6dPyms1ppqU9TlUpJXL.SF43rRjE4vUbCiOdu0.T6vpxAf4XwSm', 'technician1', 'technician', 0, '2020-02-27 10:41:35', '2020-02-27 10:41:35'),
(13, 'te2', '$2b$10$2U6dPyms1ppqU9TlUpJXL.SF43rRjE4vUbCiOdu0.T6vpxAf4XwSm', 'technician2', 'technician', 1, '2020-02-25 16:09:49', '2020-02-25 16:09:49'),
(14, 're2', '$2b$10$2U6dPyms1ppqU9TlUpJXL.SF43rRjE4vUbCiOdu0.T6vpxAf4XwSm', 'receptionist2', 'receptionist', 1, '2020-02-25 16:09:35', '2020-02-25 16:09:35'),
(15, 'te3', '$2b$10$2U6dPyms1ppqU9TlUpJXL.SF43rRjE4vUbCiOdu0.T6vpxAf4XwSm', 'technician3', 'technician', 1, '2020-02-25 16:09:46', '2020-02-25 16:09:46'),
(16, 're3', '$2b$10$eyts9f9t9mBxNI8RwJEHZuyJPqzIEgWDeJyFZEOni1cnbVoDSvHoy', 'receptionist4', 'receptionist', 1, '2020-02-25 16:09:40', '2020-02-25 16:09:40');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;

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
