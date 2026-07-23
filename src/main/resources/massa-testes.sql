INSERT INTO tb_endereco (logradouro, numero, cidade, estado,cep) VALUES ('Rua das Flores', '123', 'São Paulo', 'SP', '01234-567');
INSERT INTO tb_endereco (logradouro, numero, cidade, estado, cep) VALUES ('Avenida Paulista', '1500', 'São Paulo', 'SP', '01310-100');
INSERT INTO tb_endereco (logradouro, numero, cidade, estado, cep) VALUES ('Rua XV de Novembro', '450', 'Curitiba', 'PR', '80020-310');
INSERT INTO tb_endereco (logradouro, numero, cidade, estado, cep) VALUES ('Avenida Atlântica', '2000', 'Rio de Janeiro', 'RJ', '22021-001');
INSERT INTO tb_endereco (logradouro, numero, cidade, estado, cep) VALUES ('Rua dos Andradas', '88', 'Porto Alegre', 'RS', '90020-000');
INSERT INTO tb_endereco (logradouro, numero, cidade, estado, cep) VALUES ('Avenida Afonso Pena', '3130', 'Belo Horizonte', 'MG', '30130-009');
INSERT INTO tb_endereco (logradouro, numero, cidade, estado, cep) VALUES ('Rua da Aurora', '521', 'Recife', 'PE', '50050-000');
INSERT INTO tb_endereco (logradouro, numero, cidade, estado, cep) VALUES ('Avenida Sete de Setembro', '1200', 'Salvador', 'BA', '40060-001');
INSERT INTO tb_endereco (logradouro, numero, cidade, estado, cep) VALUES ('Rua das Palmeiras', '74', 'Campinas', 'SP', '13010-110');
INSERT INTO tb_endereco (logradouro, numero, cidade, estado, cep) VALUES ('Avenida Brasil', '4300', 'Maringá', 'PR', '87013-000');

INSERT INTO tb_usuario (nome, email, login, senha, data_ultima_alteracao, tipo_usuario, id_endereco) VALUES ('João Silva', 'joao.silva@email.com', 'joao.silva', '$2a$10$4ZhuaxbHbKpVDCSe6YmRaullmIM33C7bYhvp065somBsjGRydd5.m',NOW(),2,1);
INSERT INTO tb_usuario (nome, email, login, senha, data_ultima_alteracao, tipo_usuario, id_endereco) VALUES ('Maria Oliveira', 'maria.oliveira@email.com', 'maria.oliveira', '$2a$10$4ZhuaxbHbKpVDCSe6YmRaullmIM33C7bYhvp065somBsjGRydd5.m', NOW(), 2, 2);
INSERT INTO tb_usuario (nome, email, login, senha, data_ultima_alteracao, tipo_usuario, id_endereco) VALUES ('Carlos Souza', 'carlos.souza@email.com', 'carlos.souza', '$2a$10$4ZhuaxbHbKpVDCSe6YmRaullmIM33C7bYhvp065somBsjGRydd5.m', NOW(), 1, 3);
INSERT INTO tb_usuario (nome, email, login, senha, data_ultima_alteracao, tipo_usuario, id_endereco) VALUES ('Ana Santos', 'ana.santos@email.com', 'ana.santos', '$2a$10$4ZhuaxbHbKpVDCSe6YmRaullmIM33C7bYhvp065somBsjGRydd5.m', NOW(), 2, 4);
INSERT INTO tb_usuario (nome, email, login, senha, data_ultima_alteracao, tipo_usuario, id_endereco) VALUES ('Lucas Lima', 'lucas.lima@email.com', 'lucas.lima', '$2a$10$4ZhuaxbHbKpVDCSe6YmRaullmIM33C7bYhvp065somBsjGRydd5.m', NOW(), 2, 5);
INSERT INTO tb_usuario (nome, email, login, senha, data_ultima_alteracao, tipo_usuario, id_endereco) VALUES ('Fernanda Pereira', 'fernanda.pereira@email.com', 'fernanda.pereira', '$2a$10$4ZhuaxbHbKpVDCSe6YmRaullmIM33C7bYhvp065somBsjGRydd5.m', NOW(), 1, 6);
INSERT INTO tb_usuario (nome, email, login, senha, data_ultima_alteracao, tipo_usuario, id_endereco) VALUES ('Rafael Ferreira', 'rafael.ferreira@email.com', 'rafael.ferreira', '$2a$10$4ZhuaxbHbKpVDCSe6YmRaullmIM33C7bYhvp065somBsjGRydd5.m', NOW(), 2, 7);
INSERT INTO tb_usuario (nome, email, login, senha, data_ultima_alteracao, tipo_usuario, id_endereco) VALUES ('Beatriz Costa', 'beatriz.costa@email.com', 'beatriz.costa', '$2a$10$4ZhuaxbHbKpVDCSe6YmRaullmIM33C7bYhvp065somBsjGRydd5.m', NOW(), 2, 8);
INSERT INTO tb_usuario (nome, email, login, senha, data_ultima_alteracao, tipo_usuario, id_endereco) VALUES ('Guilherme Alves', 'guilherme.alves@email.com', 'guilherme.alves', '$2a$10$4ZhuaxbHbKpVDCSe6YmRaullmIM33C7bYhvp065somBsjGRydd5.m', NOW(), 1, 9);
INSERT INTO tb_usuario (nome, email, login, senha, data_ultima_alteracao, tipo_usuario, id_endereco) VALUES ('Juliana Rocha', 'juliana.rocha@email.com', 'juliana.rocha', '$2a$10$4ZhuaxbHbKpVDCSe6YmRaullmIM33C7bYhvp065somBsjGRydd5.m', NOW(), 2, 10);

--SENHA PADRAO PARA TODOS OS USUARIOS AQUI => 12345