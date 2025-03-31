# Projeto de Transformação e Disponibilização de Dados

Este projeto foi desenvolvido para demonstrar habilidades em automação, processamento de dados, integração de sistemas e desenvolvimento de interfaces web. Ele combina diversas tecnologias para criar uma solução completa que inclui:

✅ **Web Scraping** automatizado de documentos regulatórios  
🔄 **Transformação de dados** de PDF para CSV estruturado  
🗃️ **Armazenamento** em banco de dados MySQL  
🌐 **API RESTful** para consulta dos dados   
🖥️ **Interface web** interativa para busca de operadoras  

### 🛠️ Tecnologias Principais:
- **Backend:** Java, PDFBox, Python (Flask)
- **Banco de Dados:** MySQL
- **API:** Python Flask, Postman
- **Frontend:** Vue.js 3, Axios
- **Testes:** JUnit 5, Mockito

---

## 📂 Estrutura do Projeto
```
projeto-teste-nivelamento/
├── scraping-and-transformation/  (Web Scraping + Transformação de Dados)
│   ├── src/                      (Código-fonte em Java)
│   │   ├── main/                 (Implementação principal)
│   │   └── test/                 (Testes unitários e de integração)
│   ├── anexos/                   (Resultados gerados: arquivos ZIP, CSV, etc.)
│   ├── pom.xml                   (Configuração Maven)
│   └── .gitignore
├── database/                     (Scripts SQL para criar tabelas, importar dados e realizar consultas)
├── api/                          (Backend e Frontend)
│   ├── backend/                  (API RESTful em Python)
│   ├── frontend/                 (Interface web em Vue.js)
├── postman/                  (Coleção Postman para testes da API)
│       └── busca-operadoras.postman_collection.json
└── README.md                     (Documentação do projeto)
```

---

## 🚀 Funcionalidades Implementadas

### ✅ **Web Scraping de Documentos Regulatórios**
- Realiza o download automático dos anexos I e II do site alvo.
- Compacta os arquivos baixados em um arquivo ZIP (`anexos.zip`).

### ✅ **Transformação de Dados**
- Extrai tabelas de PDFs baixados usando a biblioteca **PDFBox**.
- Gera um arquivo **CSV estruturado** (`dados_processados.csv`) a partir das tabelas extraídas.
- Compacta o CSV gerado em um arquivo ZIP (`Teste_{seu_nome}.zip`).

### ✅ **Armazenamento em Banco de Dados**
- Scripts SQL para criar tabelas no banco de dados **MySQL**.
- Importa os dados do CSV gerado para o banco de dados.
- Executa consultas analíticas para análise dos dados.

### ✅ **API RESTful**
- Backend desenvolvido em **Python (Flask)** para fornecer endpoints RESTful.
- **Status:** Implementada e funcional. Os endpoints estão disponíveis e integrados ao frontend, permitindo a comunicação entre os módulos.

### ✅ **Interface Web**
- Frontend desenvolvido em **Vue.js** para interação com o usuário.
- **Status:** Concluído e conectado à API. A interface consome os endpoints RESTful para exibir dados dinamicamente.

### ✅ **Coleção Postman**
- Arquivo `busca-operadoras.postman_collection.json` contém uma coleção de requisições pré-configuradas para testar os endpoints da API.

---

## 📊 **Status Atual do Projeto**

| Módulo                      | Status |
|-----------------------------|--------|
| Web Scraping e Transformação de Dados | ✅ Finalizados e funcionais |
| Banco de Dados              | ✅ Funcional, mas depende de dados válidos |
| API RESTful                 | ✅ Implementada e funcional |
| Interface Web               | ✅ Concluída e conectada à API |
| Integração Geral            | ❌ Ainda não completamente integrada |

- **Observação:** Os testes apresentam falhas relacionadas ao processamento de PDFs malformados (`Error: End-of-File, expected line at offset 0`). Validações iniciais foram adicionadas, mas ainda há melhorias a serem feitas.

---

## 🔄 **Fluxo do Processamento**

1️⃣ **Início**  
2️⃣ **Download PDFs**  
3️⃣ **Validação de Arquivos**  
4️⃣ **Extrair Tabelas**  
5️⃣ **Converter para CSV**  
6️⃣ **Processar Abreviações**  
7️⃣ **Gerar ZIP Final**  
8️⃣ **Armazenar no Banco**  
9️⃣ **Disponibilizar via API**  
🔟 **Interface Web Interativa**

> **Nota:** O fluxo acima representa o objetivo final do projeto. Atualmente, as etapas de disponibilização via API e interface web interativa estão implementadas e funcionais, permitindo a comunicação entre o frontend e o backend.
---
## 🌟 Contribuição para o Meu Desenvolvimento

Este projeto foi uma excelente oportunidade para consolidar e expandir meus conhecimentos em diversas áreas da tecnologia. A seguir, destaco os principais aprendizados e como eles impactaram meu crescimento técnico:

### 1️⃣ Web Scraping
- **O que aprendi:** Utilização de bibliotecas como Jsoup para automatizar o download de arquivos de sites.
- **Impacto:** Experiência prática em lidar com páginas web dinâmicas e extração eficiente de informações.

### 2️⃣ Processamento de PDFs
- **O que aprendi:** Uso da biblioteca PDFBox para extrair tabelas e transformá-las em dados estruturados (CSV).
- **Desafios superados:** Tratamento de arquivos malformados (erro End-of-File) com validações robustas.
- **Impacto:** Aprofundamento em manipulação de arquivos e tratamento de erros.

### 3️⃣ Banco de Dados
- **O que aprendi:** Criação de scripts SQL para modelagem e otimização de consultas no MySQL.
- **Impacto:** Melhoria na habilidade de modelagem de dados e otimização de consultas.

### 4️⃣ API RESTful
- **O que aprendi:** Desenvolvimento de endpoints RESTful usando Python Flask.
- **Impacto:** Aprendizado na projeção de APIs escaláveis e documentação eficiente.

### 5️⃣ Frontend Interativo
- **O que aprendi:** Desenvolvimento de uma interface responsiva com Vue.js.
- **Impacto:** Aprimoramento em frameworks modernos de frontend e integração com backends.

### 6️⃣ Integração de Módulos
- **O que aprendi:** Conexão entre Web Scraping, Transformação de Dados, Banco de Dados, API e Frontend.
- **Impacto:** Experiência em gerenciar fluxos complexos e resolver problemas de integração.

### 7️⃣ Testes Automatizados
- **O que aprendi:** Implementação de testes unitários e de integração com JUnit e Mockito.
- **Impacto:** Maior compreensão da importância dos testes para a confiabilidade do sistema.

### 8️⃣ Trabalho com Git/GitHub
- **O que aprendi:** Versionamento de código e colaboração eficiente com Git.
- **Impacto:** Melhoria na organização de commits e branches de forma profissional.

### 🏁 Reflexão Final

Este projeto não apenas me permitiu aplicar conceitos teóricos em um cenário real, mas também me desafiou a resolver problemas complexos e integrar diferentes tecnologias. A experiência adquirida reforçou minha paixão por desenvolvimento de software e me preparou para enfrentar novos desafios técnicos no futuro.

