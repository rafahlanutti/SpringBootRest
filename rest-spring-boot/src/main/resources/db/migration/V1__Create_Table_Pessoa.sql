CREATE TABLE `pessoa` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `endereco` varchar(100) NOT NULL,
  `genero` varchar(10) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `sobrenome` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
)