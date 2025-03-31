USE planos_saude;

LOAD DATA LOCAL INFILE 'C:/teste_nivelamento/Relatorio_cadop.csv'
INTO TABLE operadoras_ans
CHARACTER SET utf8mb4
FIELDS TERMINATED BY ';' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(registro_ans, cnpj, razao_social, nome_fantasia, modalidade, 
 logradouro, numero, complemento, bairro, cidade, uf, cep,
 ddd, telefone, fax, endereco_eletronico, representante,
 cargo_representante, @regiao, data_registro_ans)
SET regiao_comercializacao = NULLIF(@regiao, '');