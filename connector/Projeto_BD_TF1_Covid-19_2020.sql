-- ----------------     << COVID-19 >>     --------------------
--
--                    SCRIPT DO TRABALHO FINAL
--
-- Data Criacao ...........: 20/11/2020
-- Autor(es) ..............: Vandor Rissoli
-- Banco de Dados .........: MySQL 8
-- Banco de Dados(nome) ...: covid19
--
--
-- PROJETO => 01 Base de Dados
--         => 01 Tabela
-- ------------------------------------------------------------

-- BASE DE DADOS
CREATE DATABASE
 IF NOT EXISTS covid19;

USE covid19;


-- TABELA
CREATE TABLE pessoa (
  idPessoa INT               NOT NULL AUTO_INCREMENT,
	nome     VARCHAR(90)       NOT NULL,
	genero   ENUM('M','F')     NOT NULL,
  saude    ENUM('C','E','F') DEFAULT NULL,
  idade    INT               DEFAULT NULL,
 CONSTRAINT PESSOA_PK PRIMARY KEY (idPessoa)
)ENGINE InnoDB AUTO_INCREMENT = 1;



-- AJUDA COM LINGUAGEM SQL PARA MYSQL
-- ==================================

-- Instrucao que APAGA a estrutura de cadastrar PESSOA,
-- mas precisa retirar o indicador de comentario (--)
-- da linha abaixo
--   DROP TABLE pessoa;


-- Instrucao que INSERI UM cadastro de PESSOA,mas que
-- precisa retirar o indicador de comentario das linhas
--   INSERT PESSOA (nome, genero, saude, idade) VALUES
--    ('Karla Silva','F','E',null);


-- Instrucao que REMOVE todos os cadastros de PESSOA,mas
-- que precisa retirar o indicador de comentario da linha
--   DELETE FROM PESSOA;


-- Instrucao que RECUPERA todos os cadastros de PESSOA,
-- mas precisa retirar o indicador de comentario da Linha
--   SELECT * FROM PESSOA;


-- Instrucao que CONSULTA uma UNICA PESSOA cadastrada,
-- por seu numero IDENTIFICADOR, mas precisa retirar o
-- indicador de comentario das linhas e fornecer o
-- identificador desejado que como exemplo esta 3
--   SELECT * FROM PESSOA
--    WHERE idPessoa = 3;

