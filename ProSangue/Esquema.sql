
DROP DATABASE IF EXISTS prosangue;

CREATE DATABASE IF NOT EXISTS prosangue;

USE prosangue;

CREATE TABLE doador (
    nome VARCHAR(100),
    endereco VARCHAR(100),
    data_nascimento DATE,
    nome_pai VARCHAR(100),
    nome_mae VARCHAR(100),
    rg VARCHAR(20),
    tipo_sangue VARCHAR(3),
    ultima_doacao DATETIME,
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    sexo VARCHAR(10)
);

CREATE TABLE doacao (
    hepatite_b BOOLEAN,
    hepatite_c BOOLEAN,
    chagas BOOLEAN,
    sifilis BOOLEAN,
    aids BOOLEAN,
    htlv BOOLEAN,
    teste_anemia BOOLEAN,
    triagem_clinica BOOLEAN,
    horario DATETIME,
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    fk_doador_id BIGINT
);
 
ALTER TABLE doacao ADD CONSTRAINT FK_doacao_2
    FOREIGN KEY (fk_doador_id)
    REFERENCES doador (id)
    ON DELETE CASCADE;

INSERT INTO doador (nome, endereco, data_nascimento, nome_pai, nome_mae, rg, tipo_sangue, ultima_doacao, sexo) 
VALUES ('Felipe', 'Jesuino', '1990-02-16', 'Everaldo', 'Miriane', '9998789564', 'A+', '2019-06-06 09:00:00', 'Masculino');

INSERT INTO doador (nome, endereco, data_nascimento, nome_pai, nome_mae, rg, tipo_sangue, ultima_doacao, sexo) 
VALUES ('Thiago', 'Oficinas', '1998-09-02', 'Juca', 'Joana', '999321564', 'A-', '2019-06-02 10:00:00', 'Masculino');

INSERT INTO doador (nome, endereco, data_nascimento, nome_pai, nome_mae, rg, tipo_sangue, ultima_doacao, sexo) 
VALUES ('Kleber', 'Santa Mônica', '1991-02-09', 'Kaio', 'Jennifer', '903221564', 'AB-', '2019-06-02 11:00:00', 'Masculino');


INSERT INTO doacao (hepatite_b, hepatite_c, chagas, sifilis, aids, htlv, teste_anemia, triagem_clinica, horario, fk_doador_id) 
VALUES (TRUE, TRUE, FALSE, TRUE, FALSE, FALSE, TRUE, TRUE, '2019-06-02 11:00:00', 1);

INSERT INTO doacao (hepatite_b, hepatite_c, chagas, sifilis, aids, htlv, teste_anemia, triagem_clinica, horario, fk_doador_id) 
VALUES (FALSE, TRUE, FALSE, TRUE, FALSE, FALSE, FALSE, TRUE, '2019-06-02 11:00:00', 2);