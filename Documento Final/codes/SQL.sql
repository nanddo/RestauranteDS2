DROP TABLE endereco CASCADE;
DROP TABLE funcionario CASCADE;
DROP TABLE mesa CASCADE;
DROP TABLE comanda CASCADE;
DROP TABLE produto CASCADE;
DROP TABLE pedido CASCADE;
DROP TABLE item CASCADE;
DROP TABLE ingrediente CASCADE;
DROP TABLE fornecedor CASCADE;
DROP TABLE compra CASCADE;

CREATE TABLE endereco (
	id serial,
	logradouro varchar(128),
	rua varchar(255),
	numero integer,
	bairro varchar(128),
	cidade varchar(128),
	estado varchar(128),
	CEP varchar(32),
	CONSTRAINT pk_endereco PRIMARY KEY (id)
);

CREATE TABLE funcionario (
	id serial,
	tipo integer,
	cpf varchar(20),
	nome varchar(128),
	telefone varchar(20),
	id_endereco integer,
	nome_usuario varchar(64),
	CONSTRAINT pk_funcionario PRIMARY KEY (id),
	CONSTRAINT fk_id_endereco FOREIGN KEY (id_endereco) REFERENCES endereco (id),
	CONSTRAINT unique_cpf UNIQUE (cpf)
);

CREATE TABLE mesa (
	numero integer,
	CONSTRAINT pk_mesa PRIMARY KEY (numero)
);

--uma comanda esta sempre associada a uma mesa
CREATE TABLE comanda (
	id serial,
	data timestamp,
	numero_mesa integer,
	CONSTRAINT pk_comanda PRIMARY KEY (id),
	CONSTRAINT fk_comanda_mesa FOREIGN KEY (numero_mesa) REFERENCES mesa (numero)
);

CREATE TABLE produto (
	id serial,
	nome varchar(128),
	preco float,
	CONSTRAINT pk_produto PRIMARY KEY (id)
);

--um pedido pode estar em apenas uma comanda, e possui apenas um produto.
CREATE TABLE pedido (
	id serial,
	quantidade integer,
	entrege boolean,
	id_produto integer,
	id_comanda integer,
	CONSTRAINT pk_pedido PRIMARY KEY (id),
	CONSTRAINT fk_pedido_comanda FOREIGN KEY (id_comanda) REFERENCES comanda (id),
	CONSTRAINT fk_pedido_produto FOREIGN KEY (id_produto) REFERENCES produto (id)
);

CREATE TABLE item (
	id serial,
	nome varchar(128),
	quantidade integer,
	limite integer,
	unidade varchar(3),
	CONSTRAINT pk_item PRIMARY KEY (id)
);

CREATE TABLE ingrediente (
	id_item integer,
	id_produto integer,
	quantidade integer,
	CONSTRAINT pk_item_produto PRIMARY KEY (id_item, id_produto),
	CONSTRAINT fk_item FOREIGN KEY (id_item) REFERENCES item (id),
	CONSTRAINT fk_produto FOREIGN KEY (id_produto) REFERENCES produto (id)
);

CREATE TABLE fornecedor (
	id serial,
	nome varchar(128),
	telefone varchar(20),
	cnpj varchar(25),
	email varchar(128),
	id_endereco integer,
	CONSTRAINT pk_fornecedor PRIMARY KEY (id),	
	CONSTRAINT fk_id_endereco FOREIGN KEY (id_endereco) REFERENCES endereco (id),
	CONSTRAINT unique_cnpj UNIQUE (cnpj)
);

CREATE TABLE compra (
	id serial,
	id_item integer,
	id_fornecedor integer,
	quantidade integer,
	preco float,
	data timestamp,
	CONSTRAINT pk_compra PRIMARY KEY (id),
	CONSTRAINT fk_compra_item FOREIGN KEY (id_item) REFERENCES item (id),
	CONSTRAINT fk_compra_fornecedor FOREIGN KEY (id_fornecedor) REFERENCES fornecedor (id)
);