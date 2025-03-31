USE planos_saude;

-- Criação da Tabela
CREATE TABLE operadoras_ans (
    id INT PRIMARY KEY AUTO_INCREMENT,
    registro_ans VARCHAR(10) NOT NULL,
    cnpj VARCHAR(14) NOT NULL,
    razao_social VARCHAR(200) NOT NULL,
    nome_fantasia VARCHAR(200),
    modalidade VARCHAR(100) NOT NULL,
    logradouro VARCHAR(150) NOT NULL,
    numero VARCHAR(20),
    complemento VARCHAR(200),
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    uf CHAR(2) NOT NULL,
    cep CHAR(8) NOT NULL,
    ddd CHAR(2),
    telefone VARCHAR(20),
    fax VARCHAR(20),
    endereco_eletronico VARCHAR(150),
    representante VARCHAR(150),
    cargo_representante VARCHAR(150),
    regiao_comercializacao INT,
    data_registro_ans DATE NOT NULL,
    
    -- Constraints para garantir valores únicos
    CONSTRAINT uk_registro_ans UNIQUE (registro_ans),
    CONSTRAINT uk_cnpj UNIQUE (cnpj)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Índices para facilitar consultas
CREATE INDEX idx_operadoras_uf ON operadoras_ans(uf);
CREATE INDEX idx_operadoras_cidade ON operadoras_ans(cidade);
CREATE INDEX idx_operadoras_modalidade ON operadoras_ans(modalidade);
CREATE INDEX idx_uf_cidade ON operadoras_ans(uf, cidade);