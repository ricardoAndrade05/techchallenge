SELECT 1;

INSERT INTO tb_endereco (logradouro, numero, cidade, estado,cep) VALUES ('Rua das Flores', '123', 'São Paulo', 'SP', '01234-567');

INSERT INTO tb_usuario (nome, email, login, senha, data_ultima_alteracao, tipo_usuario, id_endereco) VALUES ('João Silva', 'joao.silva@email.com', 'joao.silva', 'senha_criptografada_aqui',NOW(),2,1);