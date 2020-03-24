-- MySQL dump 10.13  Distrib 5.7.29, for Linux (x86_64)
--
-- Host: localhost    Database: cursospring
-- ------------------------------------------------------
-- Server version	5.7.29-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categoria_entity`
--

DROP TABLE IF EXISTS `categoria_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria_entity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_entity`
--

LOCK TABLES `categoria_entity` WRITE;
/*!40000 ALTER TABLE `categoria_entity` DISABLE KEYS */;
INSERT INTO `categoria_entity` VALUES (1,'Informática'),(2,'Escritório'),(3,'Cama, mesa e banho'),(4,'Eletrônicos'),(5,'Jardinagem'),(6,'Decoração'),(7,'Perfumaria'),(8,'Informática'),(9,'Escritório'),(10,'Cama, mesa e banho'),(11,'Eletrônicos'),(12,'Jardinagem'),(13,'Decoração'),(14,'Perfumaria'),(15,'Informática'),(16,'Escritório'),(17,'Cama, mesa e banho'),(18,'Eletrônicos'),(19,'Jardinagem'),(20,'Decoração'),(21,'Perfumaria');
/*!40000 ALTER TABLE `categoria_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cidade_entity`
--

DROP TABLE IF EXISTS `cidade_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cidade_entity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `fk_estado` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3hn5o6dt8b1inlr48abfrqop4` (`fk_estado`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cidade_entity`
--

LOCK TABLES `cidade_entity` WRITE;
/*!40000 ALTER TABLE `cidade_entity` DISABLE KEYS */;
INSERT INTO `cidade_entity` VALUES (1,'Uberlândia',1),(2,'São Paulo',2),(3,'Campinas',2),(4,'Uberlândia',3),(5,'São Paulo',4),(6,'Campinas',4),(7,'Uberlândia',5),(8,'São Paulo',6),(9,'Campinas',6);
/*!40000 ALTER TABLE `cidade_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente_entity`
--

DROP TABLE IF EXISTS `cliente_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente_entity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `documento` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `tipo_cliente` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ml0ovvboxenuj1osotcrdl7t7` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente_entity`
--

LOCK TABLES `cliente_entity` WRITE;
/*!40000 ALTER TABLE `cliente_entity` DISABLE KEYS */;
INSERT INTO `cliente_entity` VALUES (1,'37762756850','m@gmail.com','Matheus Lemes',1);
/*!40000 ALTER TABLE `cliente_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco_entity`
--

DROP TABLE IF EXISTS `endereco_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endereco_entity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bairro` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `fk_cidade` int(11) DEFAULT NULL,
  `fk_cliente` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbkpubrfhu6cucl104dwuf7eg1` (`fk_cidade`),
  KEY `FK8yp82p65dytqcbxibdmx8740v` (`fk_cliente`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco_entity`
--

LOCK TABLES `endereco_entity` WRITE;
/*!40000 ALTER TABLE `endereco_entity` DISABLE KEYS */;
INSERT INTO `endereco_entity` VALUES (1,'Padre Bruno Ricco','0323850',NULL,'Padre Bruno Ricco','458',2,1),(2,'Costa Oeste','12365478','Casa B','Avenida Matos','105',1,1);
/*!40000 ALTER TABLE `endereco_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_entity`
--

DROP TABLE IF EXISTS `estado_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado_entity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_entity`
--

LOCK TABLES `estado_entity` WRITE;
/*!40000 ALTER TABLE `estado_entity` DISABLE KEYS */;
INSERT INTO `estado_entity` VALUES (1,'Minas Gerais'),(2,'São Paulo'),(3,'Minas Gerais'),(4,'São Paulo'),(5,'Minas Gerais'),(6,'São Paulo');
/*!40000 ALTER TABLE `estado_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_pedido_entity`
--

DROP TABLE IF EXISTS `item_pedido_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_pedido_entity` (
  `desconto` double DEFAULT NULL,
  `preco` double DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `fk_produto` int(11) NOT NULL,
  `fk_pedido` int(11) NOT NULL,
  PRIMARY KEY (`fk_pedido`,`fk_produto`),
  KEY `FK3k6mtq13pcdopgp0sc6nf3w3t` (`fk_produto`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_pedido_entity`
--

LOCK TABLES `item_pedido_entity` WRITE;
/*!40000 ALTER TABLE `item_pedido_entity` DISABLE KEYS */;
INSERT INTO `item_pedido_entity` VALUES (0,2000,1,1,1),(0,80,2,3,1),(100,800,1,2,2);
/*!40000 ALTER TABLE `item_pedido_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamento_boleto_entity`
--

DROP TABLE IF EXISTS `pagamento_boleto_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagamento_boleto_entity` (
  `dt_pagamento` datetime DEFAULT NULL,
  `dt_vencimento` datetime DEFAULT NULL,
  `fk_pedido` int(11) NOT NULL,
  PRIMARY KEY (`fk_pedido`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamento_boleto_entity`
--

LOCK TABLES `pagamento_boleto_entity` WRITE;
/*!40000 ALTER TABLE `pagamento_boleto_entity` DISABLE KEYS */;
INSERT INTO `pagamento_boleto_entity` VALUES (NULL,'2020-03-31 02:59:59',2);
/*!40000 ALTER TABLE `pagamento_boleto_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamento_cartao_entity`
--

DROP TABLE IF EXISTS `pagamento_cartao_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagamento_cartao_entity` (
  `num_parcelas` int(11) DEFAULT NULL,
  `fk_pedido` int(11) NOT NULL,
  PRIMARY KEY (`fk_pedido`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamento_cartao_entity`
--

LOCK TABLES `pagamento_cartao_entity` WRITE;
/*!40000 ALTER TABLE `pagamento_cartao_entity` DISABLE KEYS */;
INSERT INTO `pagamento_cartao_entity` VALUES (6,1);
/*!40000 ALTER TABLE `pagamento_cartao_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamento_entity`
--

DROP TABLE IF EXISTS `pagamento_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagamento_entity` (
  `fk_pedido` int(11) NOT NULL,
  `estado` int(11) DEFAULT NULL,
  PRIMARY KEY (`fk_pedido`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamento_entity`
--

LOCK TABLES `pagamento_entity` WRITE;
/*!40000 ALTER TABLE `pagamento_entity` DISABLE KEYS */;
INSERT INTO `pagamento_entity` VALUES (1,2),(2,1);
/*!40000 ALTER TABLE `pagamento_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_entity`
--

DROP TABLE IF EXISTS `pedido_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido_entity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `instante` datetime DEFAULT NULL,
  `fk_cliente` int(11) DEFAULT NULL,
  `fk_entrega` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK85cmsvhxgv3sdjv61e98py6bd` (`fk_cliente`),
  KEY `FKo4qoyketxbilecxsybontdrvo` (`fk_entrega`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_entity`
--

LOCK TABLES `pedido_entity` WRITE;
/*!40000 ALTER TABLE `pedido_entity` DISABLE KEYS */;
INSERT INTO `pedido_entity` VALUES (1,'2017-09-30 21:02:30',1,1),(2,'2020-07-04 23:12:40',1,1);
/*!40000 ALTER TABLE `pedido_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto_categoria`
--

DROP TABLE IF EXISTS `produto_categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto_categoria` (
  `fk_produto` int(11) NOT NULL,
  `fk_categoria` int(11) NOT NULL,
  KEY `FK7hk9g4ukspuwdwkwe9152xx8v` (`fk_categoria`),
  KEY `FKgh51kaugp9ghs6tifwcex0ye3` (`fk_produto`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto_categoria`
--

LOCK TABLES `produto_categoria` WRITE;
/*!40000 ALTER TABLE `produto_categoria` DISABLE KEYS */;
INSERT INTO `produto_categoria` VALUES (1,1),(1,4),(2,1),(2,2),(2,4),(3,1),(3,4),(4,2),(5,3),(6,3),(7,4),(8,5),(9,6),(10,6),(11,7),(12,8),(12,11),(13,8),(13,9),(13,11),(14,8),(14,11),(15,9),(16,10),(17,10),(18,11),(19,12),(20,13),(21,13),(22,14),(23,15),(23,18),(24,15),(24,16),(24,18),(25,15),(25,18),(26,16),(27,17),(28,17),(29,18),(30,19),(31,20),(32,20),(33,21);
/*!40000 ALTER TABLE `produto_categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto_entity`
--

DROP TABLE IF EXISTS `produto_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto_entity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `preco` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto_entity`
--

LOCK TABLES `produto_entity` WRITE;
/*!40000 ALTER TABLE `produto_entity` DISABLE KEYS */;
INSERT INTO `produto_entity` VALUES (1,'Computador',2000.5),(2,'Impressora',800),(3,'Mouse',80.1),(4,'Mesa de escritório',80.1),(5,'Toalha',80.1),(6,'Colcha',80.1),(7,'Tv true color',80.1),(8,'Roçadeira',80.1),(9,'Abajour',80.1),(10,'Condicionador',80.1),(11,'Shampoo',80.1),(12,'Computador',2000.5),(13,'Impressora',800),(14,'Mouse',80.1),(15,'Mesa de escritório',80.1),(16,'Toalha',80.1),(17,'Colcha',80.1),(18,'Tv true color',80.1),(19,'Roçadeira',80.1),(20,'Abajour',80.1),(21,'Condicionador',80.1),(22,'Shampoo',80.1),(23,'Computador',2000.5),(24,'Impressora',800),(25,'Mouse',80.1),(26,'Mesa de escritório',80.1),(27,'Toalha',80.1),(28,'Colcha',80.1),(29,'Tv true color',80.1),(30,'Roçadeira',80.1),(31,'Abajour',80.1),(32,'Condicionador',80.1),(33,'Shampoo',80.1);
/*!40000 ALTER TABLE `produto_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefone`
--

DROP TABLE IF EXISTS `telefone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefone` (
  `cliente_entity_id` int(11) NOT NULL,
  `telefones` varchar(255) DEFAULT NULL,
  KEY `FKh869hrmh34cdbx80c7prus2xf` (`cliente_entity_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefone`
--

LOCK TABLES `telefone` WRITE;
/*!40000 ALTER TABLE `telefone` DISABLE KEYS */;
INSERT INTO `telefone` VALUES (1,'11977202265'),(1,'1123012143');
/*!40000 ALTER TABLE `telefone` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-24 16:51:44
