-- Quais as 10 operadoras com maiores despesas em "EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR" no último trimestre?
SELECT 
    o.razao_social,
    o.nome_fantasia,
    d.reg_ans AS registro_ans,
    SUM(d.vl_saldo_final) AS total_despesa,
    COUNT(*) AS quantidade_eventos,
    ROUND(SUM(d.vl_saldo_final) / COUNT(*), 2) AS valor_medio_por_evento
FROM demonstracoes_contabeis d
JOIN operadoras_ans o ON o.registro_ans = d.reg_ans
WHERE 
    d.descricao = 'EVENTOS/SINISTROS CONHECIDOS OU AVISADOS DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR'
    AND d.data BETWEEN 
        DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 3 MONTH), '%Y-%m-01')
        AND LAST_DAY(DATE_SUB(CURDATE(), INTERVAL 1 MONTH))
GROUP BY 
    d.reg_ans, 
    o.razao_social,
    o.nome_fantasia
ORDER BY 
    total_despesa DESC
LIMIT 10;

-- Quais as 10 operadoras com maiores despesas na categoria "EVENTOS/SINISTROS CONHECIDOS OU AVISADOS DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR" no último ano?

SELECT 
    o.razao_social,
    o.nome_fantasia,
    d.reg_ans AS registro_ans,
    SUM(d.vl_saldo_final) AS total_despesa,
    COUNT(*) AS quantidade_eventos,
    ROUND(SUM(d.vl_saldo_final) / COUNT(*), 2) AS valor_medio_por_evento
FROM demonstracoes_contabeis d
JOIN operadoras_ans o ON o.registro_ans = d.reg_ans
WHERE 
    d.descricao = 'EVENTOS/SINISTROS CONHECIDOS OU AVISADOS DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR'
    AND d.data BETWEEN 
        DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 YEAR), '%Y-%m-01')
        AND LAST_DAY(CURDATE())
GROUP BY 
    d.reg_ans, 
    o.razao_social,
    o.nome_fantasia
ORDER BY 
    total_despesa DESC
LIMIT 10;

