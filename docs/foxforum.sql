-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: database:3306
-- Tiempo de generación: 18-12-2023 a las 11:26:54
-- Versión del servidor: 8.1.0
-- Versión de PHP: 8.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `foxforum`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reply`
--

CREATE TABLE `reply` (
  `id` bigint NOT NULL,
  `title` varchar(2048) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `body` text CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `id_user` bigint NOT NULL DEFAULT '1',
  `id_thread` bigint NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `thread`
--

CREATE TABLE `thread` (
  `id` bigint NOT NULL,
  `title` varchar(2048) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `id_user` bigint NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `name` varchar(255) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `surname` varchar(255) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `lastname` varchar(255) CHARACTER SET utf16 COLLATE utf16_unicode_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `password` varchar(512) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `role` tinyint(1) NOT NULL,
  `reset_password_token` varchar(500) COLLATE utf16_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `name`, `surname`, `lastname`, `email`, `username`, `password`, `role`, `reset_password_token`) VALUES
(1, 'Pedro', 'Picapiedra', 'Roca', 'pedropicapiedra@ausiasmarch.net', 'pedropicapiedra', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 0, NULL),
(2, 'Pablo', 'Mármol', 'Granito', 'pablomarmol@ausiasmarch.net', 'pablomarmol', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 1, NULL),
(1416, 'Eva', 'Lara', 'Velarte', 'evalaravel@gmail.com', 'evalara', '5819b005d5c142ae151889bcbe0872bbbdbeecc26c4785a48e65b04abd7a6926', 1, 'eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJkMWEyN2U0NC01NzE0LTQyNWMtYjg3ZC0yZmMwNjAzZjQ4M2MiLCJpc3MiOiJQSE9TUEhPUlVNIERBVyBBVVNJQVMgTUFSQ0giLCJpYXQiOjE3MDI4OTcwNDcsImV4cCI6MTcwMjg5ODU0NywibmFtZSI6ImV2YWxhcmEifQ.riPbuO7IRBo6Vv60Mrnb8xQVgNI4aGI3Afrd6dK6xPaN9NLcTH3tw5wmbPW5UHKLc2YknRG9uNPJpzH-nHO0EA');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `reply`
--
ALTER TABLE `reply`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `thread`
--
ALTER TABLE `thread`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `reply`
--
ALTER TABLE `reply`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `thread`
--
ALTER TABLE `thread`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1417;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
