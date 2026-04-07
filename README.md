# Projeto Uniesp Tech: Sistema de Gestão Acadêmica Escalonável

## Situação-Problema
A **Uniesp Tech** herdou um sistema de uma startup que faliu. O código atual é funcional, porém extremamente **frágil**:
* **Dados Voláteis:** Armazena dados apenas em memória (perde tudo ao reiniciar).
* **Sem Qualidade:** Não possui nenhum teste unitário ou de integração.
* **Deploy Artesanal:** O processo é manual (copiar o `.jar` via FTP).
* **Blindness (Cegueira):** Ninguém sabe se o sistema está online ou offline até que um usuário reclame.

---

## O Objetivo
Em **3 semanas**, vocês devem reconstruir a base deste sistema, aplicar persistência real, containerizar a aplicação e criar um fluxo de CI/CD profissional que impeça bugs de chegarem em produção.

---

## Cronograma de Desenvolvimento

### Semana 1: Refatoração, Qualidade e Governança (Plan, Code, Test)
Nesta fase, o foco é "organizar a casa" e garantir que o código seja testável e modular.

* **Desafio Java:**
    * Refatorar o código "macarrônico" original separando-o em camadas: `Controller`, `Service`, `Model` e `Repository`.
    * Implementar validações rigorosas (ex: CPF com 11 dígitos, campos obrigatórios não vazios).
* **Desafio DevOps:**
    * **Maven:** Configurar o `pom.xml` para gerenciar dependências e o ciclo de vida do projeto.
    * **Testes:** Criar os primeiros Testes Unitários com **JUnit 5** para as regras de negócio.
    * **Governança:** Criar um quadro Kanban (GitHub Projects). Proibido o Push direto na `main`; o código só entra via **Pull Request** com Code Review de um colega.

** Entrega:** Repositório organizado, código limpo e suite de testes rodando localmente.

---

### Semana 2: Persistência e Containerização (Build, Release)
Agora o sistema precisa sobreviver ao reinício e ser portável para qualquer ambiente.

* **Desafio Java:**
    * Substituir o armazenamento em `ArrayList` por persistência real.
    * Implementar **H2 Database** (em arquivo) ou **PostgreSQL** via JDBC ou Spring Data JPA.
* **Desafio DevOps:**
    * **Docker:** Criar um `Dockerfile` otimizado para a aplicação (Multi-stage build).
    * **CI (Continuous Integration):** Configurar **GitHub Actions** para que, a cada Push/PR, o sistema execute o Build e os Testes automaticamente.
    * **Artifacts:** O build bem-sucedido deve gerar uma imagem Docker ou um artefato `.jar` versionado no GitHub.

** Entrega:** Pipeline de CI configurado (build verde) e aplicação rodando dentro de um container Docker.

---

### Semana 3: Cloud, Deploy e Monitoramento (Deploy, Operate, Monitor)
Hora de colocar o sistema no mundo real e garantir que ele continue de pé e saudável.

* **Desafio Java:**
    * Implementar um endpoint de **Health Check** (ex: `/health`) que verifica a saúde do app e da conexão com o banco.
    * Implementar logs estruturados (Log4j ou SLF4J) para monitorar tentativas de cadastro ou erros críticos.
* **Desafio DevOps:**
    * **CD (Continuous Deployment):** Configurar o deploy automático para uma nuvem (Render, Railway ou Fly.io).
    * **Operação:** Realizar um "Chaos Test" (simular queda do banco e observar como a aplicação loga o erro).
    * **Documentação:** Finalizar o README com instruções de uso e evidências do monitoramento.

** Entrega:** Link da aplicação rodando em produção com fluxo de entrega contínua ativo.

---

## Semana 4: Prova Prática
Aplicação de uma *Hotfix de Emergência* em tempo real para avaliar o domínio sobre o fluxo DevOps construído.


# UniespTech - Sistema Acadêmico

Sistema de cadastro de alunos desenvolvido em Java puro com Maven, PostgreSQL e deploy na nuvem.

## 🚀 Tecnologias

- Java 21
- Maven
- PostgreSQL (JDBC puro)
- SLF4J + Logback (logs estruturados)
- Docker + Docker Compose
- GitHub Actions (CI/CD)
- Railway (deploy em produção)

## ▶️ Como rodar localmente

### Pré-requisitos
- Java 21
- PostgreSQL instalado e rodando
- Maven

### Configurar variáveis de ambiente
```bash
DB_URL=jdbc:postgresql://localhost:5432/uniesp_db
DB_USER=postgres
DB_PASSWORD=sua_senha
```

### Compilar e executar
```bash
mvn clean package
java -jar target/uniesp-tech.jar
```

### Rodar com Docker
```bash
docker-compose up --build
```

## 🏥 Health Check

Endpoint que verifica a saúde da aplicação e conexão com o banco:
GET /health

Resposta quando tudo está ok:
```json
{
  "status": "UP",
  "database": "UP"
}
```

Resposta quando o banco está fora:
```json
{
  "status": "DOWN",
  "database": "DOWN",
  "erro": "mensagem do erro"
}
```

## 🌐 Produção

Aplicação rodando em:
https://uniesptech-production.up.railway.app/health

## 💥 Chaos Test

Simulamos a queda do banco alterando a senha nas variáveis de ambiente.
A aplicação respondeu corretamente com `status: DOWN` e logou o erro.

## 🔄 CI/CD

- A cada push o GitHub Actions executa o build e os testes automaticamente
- Deploy automático no Railway a cada push na branch `feat-pedro`
