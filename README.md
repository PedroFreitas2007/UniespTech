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
