# DeliverIT Challenge - Sistema de Contas a Pagar

## Visão Geral

Este projeto é uma API REST desenvolvida em Java com Spring Boot para gerenciar contas a pagar, incluindo cálculo automático de multas e juros para pagamentos em atraso. O sistema permite cadastrar contas, listar todas as contas pagas e calcula automaticamente o valor ajustado conforme as regras de atraso.

### Funcionalidades
- Cadastro de contas a pagar
- Listagem de contas pagas
- Cálculo automático de multa e juros conforme dias de atraso
- Persistência em banco de dados relacional
- Testes unitários (não há testes de integração implementados)

## Tecnologias Utilizadas
- Java 17+
- Spring Boot
- Spring Data JPA
- H2 Database (testes)
- PostgreSQL (produção)
- Lombok
- JUnit 5 & Mockito
- Docker & Docker Compose

## Estrutura do Projeto
```
back-end/
├── src/
│   ├── main/
│   │   ├── java/br/com/deliverit/...
│   │   └── resources/
│   │       ├── application.yml
│   │       └── db/migration/
│   └── test/
│       └── java/br/com/deliverit/...
├── Dockerfile
├── docker-compose.yaml
├── pom.xml
└── README.md
```

## Como Funciona o Cálculo de Multa e Juros
- **Até 0 dias de atraso:** multa 0%, juros 0%
- **1 a 3 dias de atraso:** multa 2%, juros 0,1% ao dia
- **4 a 5 dias de atraso:** multa 3%, juros 0,2% ao dia
- **Mais de 5 dias de atraso:** multa 5%, juros 0,3% ao dia

O valor ajustado é calculado automaticamente ao cadastrar uma conta paga em atraso.

## Perfis de Execução (application.yml)
O projeto possui três profiles principais:

- **docker**: Usado para rodar a aplicação junto com o banco PostgreSQL via Docker Compose.
- **dev**: Usado para rodar localmente na sua máquina, conectando a um banco PostgreSQL local.
- **test**: Usado para rodar a aplicação com banco H2 em memória, ideal para testes rápidos e automáticos.

### Como escolher o profile
- Por padrão, o profile ativo é `docker` (veja em `application.yml`).
- Para trocar o profile, defina a variável de ambiente `SPRING_PROFILES_ACTIVE` **ou** altere diretamente a linha `spring.profiles.active` no arquivo `application.yml`.

## Como Rodar Localmente

### Pré-requisitos
- Java 17+
- Maven 3.8+
- Docker (opcional, para rodar banco PostgreSQL)

### 1. Clonar o repositório
```bash
git clone https://github.com/gabrielmelim/DeliverIT-Challange.git
cd back-end
```

### 2. Rodar com banco H2 (profile `test`)
Ideal para testar rapidamente sem precisar de banco instalado.

```bash
SPRING_PROFILES_ACTIVE=test ./mvnw spring-boot:run
```
Ou altere a linha no `application.yml` para:
```yaml
spring:
  profiles:
    active: test
```
E então rode:
```bash
./mvnw spring-boot:run
```
A aplicação estará disponível em: http://localhost:8080

### 3. Rodar com banco PostgreSQL local (profile `dev`)
Certifique-se de ter um banco PostgreSQL rodando localmente com o usuário e senha configurados no `application.yml`.

```bash
SPRING_PROFILES_ACTIVE=dev ./mvnw spring-boot:run
```
Ou altere a linha no `application.yml` para:
```yaml
spring:
  profiles:
    active: dev
```
E então rode:
```bash
./mvnw spring-boot:run
```

### 4. Rodar com Docker Compose (profile `docker`)
Este modo sobe o banco PostgreSQL em container e conecta a aplicação automaticamente.

```bash
docker-compose up -d
SPRING_PROFILES_ACTIVE=docker ./mvnw spring-boot:run
```
Ou altere a linha no `application.yml` para:
```yaml
spring:
  profiles:
    active: docker
```
E então rode:
```bash
./mvnw spring-boot:run
```

### 5. Endpoints principais
- `POST /api/pay-accounts` — Cadastrar conta a pagar
- `GET /api/pay-accounts` — Listar contas pagas

### 6. Rodar os testes unitários
```bash
./mvnw test
```

> **Atenção:** Atualmente só existem testes unitários para a camada de serviço. Não há testes de integração implementados.

## Observações
- O projeto utiliza migrações Flyway para criar as tabelas automaticamente.
- O banco H2 é usado apenas para testes e desenvolvimento local.
- O banco PostgreSQL é recomendado para produção e homologação.
