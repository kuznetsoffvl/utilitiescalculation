-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Авг 29 2022 г., 10:50
-- Версия сервера: 10.4.24-MariaDB
-- Версия PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `utilities`
--
CREATE DATABASE IF NOT EXISTS `utilities` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `utilities`;

-- --------------------------------------------------------

--
-- Структура таблицы `counter_data`
--

CREATE TABLE `counter_data` (
  `id` int(11) NOT NULL,
  `accrued_amount` double DEFAULT NULL,
  `current_meters` double DEFAULT NULL,
  `period` date DEFAULT NULL,
  `rate_id` int(11) DEFAULT NULL,
  `utility_id` varchar(25) DEFAULT NULL,
  `consumption` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `counter_data`
--

INSERT INTO `counter_data` (`id`, `accrued_amount`, `current_meters`, `period`, `rate_id`, `utility_id`, `consumption`) VALUES
(30, 0, 28071, '2021-12-01', 2, 'Свет', 0),
(32, 0, 2040, '2021-12-01', 9, 'Вода', 0),
(33, 208.8, 28216, '2022-01-01', 2, 'Свет', 145),
(34, 0, 8784, '2021-12-01', 3, 'Газ', 0),
(35, 1821.72, 9012, '2022-01-01', 3, 'Газ', 228),
(36, 70.13, 2044, '2022-01-01', 9, 'Вода', 4),
(37, 137.8, 0, '2022-01-01', 8, 'Доставка газа', 0),
(38, 110, 0, '2022-01-01', 7, 'Вывоз мусора', 0),
(39, 165, 0, '2022-01-01', 6, 'Интернет', 0),
(40, 262.08, 28398, '2022-02-01', 2, 'Свет', 182),
(41, 1510.11, 9201, '2022-02-01', 3, 'Газ', 189),
(42, 122.85, 2049, '2022-02-01', 10, 'Вода', 5),
(43, 137.8, 0, '2022-02-01', 8, 'Доставка газа', 0),
(44, 110, 0, '2022-02-01', 7, 'Вывоз мусора', 0),
(45, 165, 0, '2022-02-01', 6, 'Интернет', 0),
(46, 282.24, 28594, '2022-03-01', 2, 'Свет', 196),
(47, 1893.63, 9438, '2022-03-01', 3, 'Газ', 237),
(48, 105.32, 2053, '2022-03-01', 10, 'Вода', 4),
(49, 137.8, 0, '2022-03-01', 8, 'Доставка газа', 0),
(50, 110, 0, '2022-03-01', 7, 'Вывоз мусора', 0),
(51, 165, 0, '2022-03-01', 6, 'Интернет', 0),
(52, 306.72, 28807, '2022-04-01', 2, 'Свет', 213),
(53, 567.29, 9509, '2022-04-01', 3, 'Газ', 71),
(54, 52.72, 2054, '2022-04-01', 10, 'Вода', 1),
(55, 137.8, 0, '2022-04-01', 8, 'Доставка газа', 0),
(56, 110, 0, '2022-04-01', 7, 'Вывоз мусора', 0),
(57, 165, 0, '2022-04-01', 6, 'Интернет', 0),
(58, 234.72, 28970, '2022-05-01', 2, 'Свет', 163),
(59, 79.57, 9519, '2022-05-01', 5, 'Газ', 10),
(60, 70.25, 2056, '2022-05-01', 10, 'Вода', 2),
(61, 137.8, 0, '2022-05-01', 8, 'Доставка газа', 0),
(62, 110, 0, '2022-05-01', 7, 'Вывоз мусора', 0),
(63, 165, 0, '2022-05-01', 6, 'Интернет', 0);

-- --------------------------------------------------------

--
-- Структура таблицы `rate`
--

CREATE TABLE `rate` (
  `id` int(11) NOT NULL,
  `formula` varchar(100) DEFAULT NULL,
  `limit1` double DEFAULT NULL,
  `period` date NOT NULL,
  `subscription_fee` double DEFAULT NULL,
  `tariff1` double DEFAULT NULL,
  `tariff2` double DEFAULT NULL,
  `utility_id` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `rate`
--

INSERT INTO `rate` (`id`, `formula`, `limit1`, `period`, `subscription_fee`, `tariff1`, `tariff2`, `utility_id`) VALUES
(2, 'consumption*(useTariff1*tariff1 + (1-useTariff1)*tariff2) + subscriptionFee', 250, '2021-12-01', 0, 1.44, 1.68, 'Свет'),
(3, 'consumption*tariff1', 0, '2021-12-01', 0, 7.99, 0, 'Газ'),
(5, 'consumption*tariff1', 0, '2022-05-01', 0, 7.95689, 0, 'Газ'),
(6, 'subscriptionFee', 0, '2021-12-01', 165, 0, 0, 'Интернет'),
(7, 'subscriptionFee', 0, '2021-12-01', 110, 0, 0, 'Вывоз мусора'),
(8, 'subscriptionFee', 0, '2021-12-01', 137.8, 0, 0, 'Доставка газа'),
(9, 'consumption*tariff1', 0, '2021-12-01', 0, 17.532, 0, 'Вода'),
(10, 'consumption*tariff1 + subscriptionFee', 0, '2022-02-01', 35.19, 17.532, 0, 'Вода');

-- --------------------------------------------------------

--
-- Структура таблицы `utility`
--

CREATE TABLE `utility` (
  `id` varchar(25) NOT NULL,
  `name` varchar(25) DEFAULT NULL,
  `use_counter` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `utility`
--

INSERT INTO `utility` (`id`, `name`, `use_counter`) VALUES
('Вода', 'Вода', b'1'),
('Вывоз мусора', 'Вывоз мусора', b'0'),
('Газ', 'Газ', b'1'),
('Доставка газа', 'Доставка газа', b'0'),
('Интернет', 'Интернет', b'0'),
('Свет', 'Свет', b'1');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `counter_data`
--
ALTER TABLE `counter_data`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8ejosthdsrgpj33u9on0tnth8` (`rate_id`),
  ADD KEY `FK6djhwxwmc604s703lfa6qixxc` (`utility_id`);

--
-- Индексы таблицы `rate`
--
ALTER TABLE `rate`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKalfk6twlhi7ikt6jy7a5rcvx9` (`utility_id`);

--
-- Индексы таблицы `utility`
--
ALTER TABLE `utility`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `counter_data`
--
ALTER TABLE `counter_data`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT для таблицы `rate`
--
ALTER TABLE `rate`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `counter_data`
--
ALTER TABLE `counter_data`
  ADD CONSTRAINT `FK6djhwxwmc604s703lfa6qixxc` FOREIGN KEY (`utility_id`) REFERENCES `utility` (`id`),
  ADD CONSTRAINT `FK8ejosthdsrgpj33u9on0tnth8` FOREIGN KEY (`rate_id`) REFERENCES `rate` (`id`);

--
-- Ограничения внешнего ключа таблицы `rate`
--
ALTER TABLE `rate`
  ADD CONSTRAINT `FKalfk6twlhi7ikt6jy7a5rcvx9` FOREIGN KEY (`utility_id`) REFERENCES `utility` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
