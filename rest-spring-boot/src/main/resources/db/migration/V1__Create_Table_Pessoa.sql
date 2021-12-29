CREATE TABLE `pessoa` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `endereco` varchar(200) NOT NULL,
  `genero` varchar(20) NOT NULL,
  `nome` varchar(200) NOT NULL,
  `sobrenome` varchar(300) NOT NULL,
  PRIMARY KEY (`id`)
)