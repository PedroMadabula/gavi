-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 22, 2019 at 08:41 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gavi`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `InserirAnuncio` (`idAnuncio` INT(10), `bi` VARCHAR(13), `idImovel` INT(10))  BEGIN 
if(bi in (select uu.bi from usuario UU)) then 
       if(idImovel in(select kk.idimovel from imovel kk)) then
              insert into anuncio values(idAnuncio,bi,idImovel,now());
       else 
              Select 'O email introduzido nao esta registado' as 'ATENCAO!';  
       end if;  
 
else 
      Select 'O nome de usuario nao foi registado'as 'ATENCAO!';  
end if;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `InserirImovel` (`idImovel` INT(11), `preco` DECIMAL(6,2), `nrQuartos` INT(2), `rua` VARCHAR(45), `bairro` VARCHAR(45), `quarteirao` INT(2), `tipoImovel` VARCHAR(15), `dataInicio` DATE, `bi` VARCHAR(13))  BEGIN 
If (preco> 0) then 

if (bi in (select bi from usuario)) then
     insert into Imovel values(IdImovel, preco, nrQuartos, bairro, rua, quarteirao,tipoImovel);
     insert into usuario_Imovel values(bi,idImovel,dataInicio); 
else
select 'Somente usurios podem publicar imoveis!' as 'ATENCAO!';
end if;

else 
      Select 'O preco de um imovel tem que ser maior que zero' as 'ATENCAO!';  
end if;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `InserirUsuario` (IN `BI` VARCHAR(13), IN `nome` VARCHAR(45), IN `apelido` VARCHAR(45), IN `celular` INT(9), IN `bairro` VARCHAR(45), IN `rua` VARCHAR(45), IN `quarteirao` INT(2), IN `nrConta` INT(4), IN `nomeUsuario` VARCHAR(45), IN `senha` INT(4), IN `email` VARCHAR(45))  BEGIN 
If (BI not in (select U.bi from usuario U)) then   
  if (nrConta not in(select C.nrConta from conta C)) then
    if(nomeUsuario not in (select C.nomeUsuario from conta C)) then 
       if(email not in(select CC.email from conta CC)) then
           insert into conta values(nrConta, nomeUsuario, senha, email); 
           insert into usuario values(bi, nome, apelido,celular,bairro, rua, quarteirao,0, nrConta);
       else 
              Select 'O email introduzido ja esta registado' as 'ATENCAO!';  
       end if;  
    else 
       Select 'O nome de usuario Ja foi registado' as 'ATENCAO!';  
    end if;
  else 
    Select 'O nr da conta introduzido ja foi usado' as 'ATENCAO!';  
  end if; 
else 
  Select 'O BI que pretende registar Ja foi registado' as 'ATENCAO!';  
end if;
END$$

--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `dataPublicacao` (`idAnuncio` INT(10)) RETURNS DATE BEGIN
declare resposta date;
 
SELECT dataPublicacao FROM anuncio i  WHERE i.idAnuncio = idAnuncio into resposta;   
return resposta;
    
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `mediaPreco` () RETURNS DECIMAL(4,1) BEGIN
declare Media_Precos numeric(4,1);
 
select AVG(preco) from imovel into media_precos;  
return media_precos;
    
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `nrConta` int(4) NOT NULL,
  `NomeUsuario` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`nrConta`, `NomeUsuario`) VALUES
(1, 'reverse_tcp');

-- --------------------------------------------------------

--
-- Table structure for table `anuncio`
--

CREATE TABLE `anuncio` (
  `idAnuncio` int(4) NOT NULL,
  `BI` varchar(13) DEFAULT NULL,
  `idImovel` int(4) DEFAULT NULL,
  `dataPublicacao` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bairro`
--

CREATE TABLE `bairro` (
  `id` int(11) NOT NULL,
  `Bairro` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bairro`
--

INSERT INTO `bairro` (`id`, `Bairro`) VALUES
(1, 'BAIRRO'),
(2, 'MAHOTAS'),
(3, 'COOP'),
(4, 'HULENE'),
(5, 'LAULANE'),
(6, 'TRIUNFU'),
(7, 'BAGAMOIO'),
(8, 'POLANA'),
(9, 'MAVALANE'),
(10, 'Costa do Sol');

-- --------------------------------------------------------

--
-- Table structure for table `conta`
--

CREATE TABLE `conta` (
  `nrConta` int(4) NOT NULL,
  `nomeUsuario` varchar(45) NOT NULL,
  `senha` int(4) NOT NULL,
  `email` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `conta`
--

INSERT INTO `conta` (`nrConta`, `nomeUsuario`, `senha`, `email`) VALUES
(1, 'reverse_tcp', 3672, 'manuelnovela48@gmail.com'),
(1111, 'Grupo01Feng', 1234, 'Feng01@gmail.com'),
(1121, 'Pedro', 1221, 'pedro@gmail.com'),
(2212, 'Helio', 2212, 'helio@gmail.com'),
(6653, 'Edson', 2211, 'edson@gmail.com'),
(6654, 'nsadh_sad', 1234, '1234'),
(6655, 'sdfjashf', 4545, 'sakdjfgashd'),
(6656, 'dasdsadas', 1, 'asd@dsad.casdc');

-- --------------------------------------------------------

--
-- Stand-in structure for view `imoveiscontacto`
-- (See below for the actual view)
--
CREATE TABLE `imoveiscontacto` (
`nome` varchar(45)
,`Bi` varchar(13)
,`celular` int(12)
,`idImovel` int(4)
);

-- --------------------------------------------------------

--
-- Table structure for table `imovel`
--

CREATE TABLE `imovel` (
  `idImovel` int(4) NOT NULL,
  `preco` int(12) NOT NULL,
  `bairro` varchar(45) NOT NULL,
  `nrCasa` int(11) NOT NULL,
  `quarteirao` int(3) DEFAULT NULL,
  `tipoImovel` varchar(15) NOT NULL,
  `url` varchar(250) NOT NULL,
  `url1` varchar(250) DEFAULT NULL,
  `url2` varchar(250) DEFAULT NULL,
  `url3` varchar(250) DEFAULT NULL,
  `titulo` varchar(250) NOT NULL,
  `descricao` varchar(250) NOT NULL,
  `tipoAnuncio` varchar(11) NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `imovel`
--

INSERT INTO `imovel` (`idImovel`, `preco`, `bairro`, `nrCasa`, `quarteirao`, `tipoImovel`, `url`, `url1`, `url2`, `url3`, `titulo`, `descricao`, `tipoAnuncio`, `activo`) VALUES
(9994, 54545, 'COOP', 4, 4, 'CASA', '7.jpg', '20.jpg', '8.jpg', '13.jpg', 'Casa Com 4 Quartos', 'Sem moveis incluidos', 'VENDA', 1);

-- --------------------------------------------------------

--
-- Stand-in structure for view `totais`
-- (See below for the actual view)
--
CREATE TABLE `totais` (
`totalUsuarios` bigint(21)
,`totalImoveis` bigint(21)
);

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `BI` varchar(13) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `Apelido` varchar(45) NOT NULL,
  `celular` int(12) NOT NULL,
  `bairro` varchar(45) NOT NULL,
  `rua` varchar(45) DEFAULT NULL,
  `quarteirao` int(3) DEFAULT NULL,
  `totalAnuncioPublicado` int(3) DEFAULT NULL,
  `nrConta` int(4) DEFAULT NULL,
  `Url` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`BI`, `nome`, `Apelido`, `celular`, `bairro`, `rua`, `quarteirao`, `totalAnuncioPublicado`, `nrConta`, `Url`) VALUES
('100292392122K', 'Edson', 'Tamele', 821221290, 'Machaquene', 'rua do bagamoio', 8, 5, 6653, ''),
('1105261103A', 'Manuel', 'Novela', 842511481, 'MAHOTAS', 'R4201', 24, 9, 1, '10.jpg'),
('111111111111A', 'Grupo01', 'Feng01', 841111111, 'Choupa01', 'Pombinho01', 11, 10, 1111, ''),
('1112221321A', 'Man', 'MAn', 845121245, 'HULENE', 'cddabs', 4, 0, 6655, '10.jpg'),
('112210021233H', 'Helio', 'Carlos', 829912201, 'Mahotas', 'rua das andorinhas', 9, 5, 2212, ''),
('112345612421A', 'asdasd', 'dsaasd', 842511481, 'MAHOTAS', 'sadafs', 1, 0, 6656, '10.jpg'),
('112771728391M', 'Pedro', 'Madabula', 866233790, 'mahotas', 'rua 9', 21, 7, 1121, '');

-- --------------------------------------------------------

--
-- Table structure for table `usuario_imovel`
--

CREATE TABLE `usuario_imovel` (
  `BI` varchar(13) NOT NULL,
  `idImovel` int(4) NOT NULL,
  `dataInicio` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario_imovel`
--

INSERT INTO `usuario_imovel` (`BI`, `idImovel`, `dataInicio`) VALUES
('111111111111A', 9994, '2019-11-19');

--
-- Triggers `usuario_imovel`
--
DELIMITER $$
CREATE TRIGGER `AnuncioAfterDelete` AFTER DELETE ON `usuario_imovel` FOR EACH ROW BEGIN
update usuario u set u.totalAnuncioPublicado = u.totalAnuncioPublicado -1
where old.bi = u.bi;   
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `AnuncioAfterInsert` AFTER INSERT ON `usuario_imovel` FOR EACH ROW BEGIN
update usuario u set u.totalAnuncioPublicado = u.totalAnuncioPublicado +1
where new.bi = u.bi;   
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure for view `imoveiscontacto`
--
DROP TABLE IF EXISTS `imoveiscontacto`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `imoveiscontacto`  AS  select `a`.`nome` AS `nome`,`a`.`BI` AS `Bi`,`a`.`celular` AS `celular`,`b`.`idImovel` AS `idImovel` from (`usuario` `a` join `anuncio` `b`) where (`a`.`BI` = `b`.`BI`) ;

-- --------------------------------------------------------

--
-- Structure for view `totais`
--
DROP TABLE IF EXISTS `totais`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `totais`  AS  select count(`a`.`BI`) AS `totalUsuarios`,count(`b`.`idImovel`) AS `totalImoveis` from (`usuario` `a` join `imovel` `b`) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD KEY `FK_nrConta` (`nrConta`);

--
-- Indexes for table `anuncio`
--
ALTER TABLE `anuncio`
  ADD PRIMARY KEY (`idAnuncio`),
  ADD KEY `BI` (`BI`),
  ADD KEY `idImovel` (`idImovel`);

--
-- Indexes for table `bairro`
--
ALTER TABLE `bairro`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `conta`
--
ALTER TABLE `conta`
  ADD PRIMARY KEY (`nrConta`),
  ADD UNIQUE KEY `nomeUsuario` (`nomeUsuario`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `idx_USUARIO_NOMEUSUARIO` (`nomeUsuario`);

--
-- Indexes for table `imovel`
--
ALTER TABLE `imovel`
  ADD PRIMARY KEY (`idImovel`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`BI`),
  ADD KEY `nrConta` (`nrConta`),
  ADD KEY `idx_USUARIO_NOME` (`nome`),
  ADD KEY `idx_USUARIO_NOME_APELIDO` (`nome`,`Apelido`);

--
-- Indexes for table `usuario_imovel`
--
ALTER TABLE `usuario_imovel`
  ADD PRIMARY KEY (`BI`,`idImovel`),
  ADD KEY `idImovel` (`idImovel`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `anuncio`
--
ALTER TABLE `anuncio`
  MODIFY `idAnuncio` int(4) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `bairro`
--
ALTER TABLE `bairro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `conta`
--
ALTER TABLE `conta`
  MODIFY `nrConta` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6657;

--
-- AUTO_INCREMENT for table `imovel`
--
ALTER TABLE `imovel`
  MODIFY `idImovel` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9997;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `FK_nrConta` FOREIGN KEY (`nrConta`) REFERENCES `conta` (`nrConta`);

--
-- Constraints for table `anuncio`
--
ALTER TABLE `anuncio`
  ADD CONSTRAINT `anuncio_ibfk_1` FOREIGN KEY (`BI`) REFERENCES `usuario` (`BI`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `anuncio_ibfk_2` FOREIGN KEY (`idImovel`) REFERENCES `imovel` (`idImovel`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`nrConta`) REFERENCES `conta` (`nrConta`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `usuario_imovel`
--
ALTER TABLE `usuario_imovel`
  ADD CONSTRAINT `usuario_imovel_ibfk_1` FOREIGN KEY (`BI`) REFERENCES `usuario` (`BI`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `usuario_imovel_ibfk_2` FOREIGN KEY (`idImovel`) REFERENCES `imovel` (`idImovel`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
