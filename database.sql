-- Base de Datos
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-07-2017 a las 17:18:29
-- Versión del servidor: 10.1.21-MariaDB
-- Autor: Daniel Gomez Jaramillo

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `subastas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `idCategoria` varchar(10) NOT NULL,
  `nombreCategoria` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`idCategoria`, `nombreCategoria`) VALUES
('01Tecn', 'Tecnologia'),
('02Elec', 'Electrodomesticos'),
('03Mec', 'Mecanica'),
('04Coc', 'Cocina'),
('05Otr', 'Otros');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `oferta`
--

CREATE TABLE `oferta` (
  `idOferta` varchar(10) NOT NULL,
  `idUsuario` varchar(15) NOT NULL,
  `idSubasta` varchar(10) NOT NULL,
  `valorPuja` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `oferta`
--

INSERT INTO `oferta` (`idOferta`, `idUsuario`, `idSubasta`, `valorPuja`) VALUES
('1', '01', '1', 999),
('2', '01', '1', 1000),
('3', '01', '3', 99999),
('4', '01', '2', 9999),
('5', '01', '3', 999999999);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `subasta`
--

CREATE TABLE `subasta` (
  `idSubasta` varchar(10) NOT NULL,
  `precioInicial` int(11) NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  `idUsuario` varchar(15) NOT NULL,
  `idCategoria` varchar(10) NOT NULL,
  `valorPuja` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `subasta`
--

INSERT INTO `subasta` (`idSubasta`, `precioInicial`, `descripcion`, `idUsuario`, `idCategoria`, `valorPuja`) VALUES
('1', 100, 'Celular', '01', '01Tecn', 1000),
('2', 50, 'Mochila', '10', '05Otr', 9999),
('3', 1000, 'Libro de programación ', '01', '05Otr', 999999999),
('4', 1, 'hhj', '010281', '01Tecn', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idUsuario` varchar(15) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `Nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idUsuario`, `correo`, `Nombre`) VALUES
('1', 'dan@ucu.ec', 'Daniel'),
('010281', 'juana@hotmail.com', 'Juana'),
('2', 'edu@ucue.ec', 'Eduardo');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`idCategoria`);

--
-- Indices de la tabla `oferta`
--
ALTER TABLE `oferta`
  ADD PRIMARY KEY (`idOferta`),
  ADD KEY `fk_Oferta_Usuario1_idx` (`idUsuario`),
  ADD KEY `fk_Oferta_Subasta1_idx` (`idSubasta`);

--
-- Indices de la tabla `subasta`
--
ALTER TABLE `subasta`
  ADD PRIMARY KEY (`idSubasta`),
  ADD KEY `fk_Subasta_Usuario1_idx` (`idUsuario`),
  ADD KEY `fk_Subasta_Categoria1_idx` (`idCategoria`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idUsuario`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `oferta`
--
ALTER TABLE `oferta`
  ADD CONSTRAINT `fk_Oferta_Subasta1` FOREIGN KEY (`idSubasta`) REFERENCES `subasta` (`idSubasta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Oferta_Usuario1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `subasta`
--
ALTER TABLE `subasta`
  ADD CONSTRAINT `fk_Subasta_Categoria1` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`idCategoria`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Subasta_Usuario1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
