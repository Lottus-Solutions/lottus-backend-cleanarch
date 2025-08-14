# Lottus Library - Backend

Backend da aplicação Lottus, uma API de gerenciamento de biblioteca desenvolvida com Spring Boot e seguindo os princípios da Clean Architecture.

## Descrição

Esta aplicação fornece os serviços de backend para o sistema de gerenciamento de biblioteca Lottus. Ela é responsável por gerenciar usuários, livros, empréstimos e outras funcionalidades essenciais de uma biblioteca. A arquitetura do projeto é baseada em Clean Architecture, o que promove um código mais limpo, organizado, testável e de fácil manutenção.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3**
- **Spring Data JPA**
- **Spring Security**
- **MySQL**
- **Redis**
- **Maven**
- **Docker**
- **JWT (JSON Web Tokens)**
- **Springdoc OpenAPI (Swagger)**

## Arquitetura

O projeto segue os princípios da **Clean Architecture**, dividindo as responsabilidades em camadas bem definidas:

- **Domain**: Contém as entidades de negócio e as regras de negócio mais puras da aplicação.
- **Application**: Orquestra o fluxo de dados e contém a lógica dos casos de uso (Use Cases).
- **Infrastructure**: Camada mais externa, responsável por detalhes de implementação como banco de dados, serviços externos e frameworks.

## Pré-requisitos

Antes de começar, certifique-se de que você tem as seguintes ferramentas instaladas em seu ambiente de desenvolvimento:

- **Java 21 ou superior**
- **Maven 3.6 ou superior**
- **Docker e Docker Compose**

## Como Configurar e Iniciar o Projeto

Siga os passos abaixo para configurar e executar o projeto em seu ambiente local:

### 1. Clone o Repositório

```bash
git clone https://github.com/seu-usuario/lottus-backend-cleanarch.git
cd lottus-backend-cleanarch
```

### 2. Configure as Variáveis de Ambiente

O projeto utiliza um arquivo `compose.yaml` para configurar os serviços de banco de dados (MySQL) e cache (Redis). As credenciais e configurações estão definidas neste arquivo e no `application.properties`.

**`compose.yaml`**:
- **MySQL**: O serviço `mysql` está configurado com o banco de dados `biblioteca`. As credenciais de acesso são:
  - **Usuário**: `library-user`
  - **Senha**: `080808A`
  - **Senha de Root**: `40028922`
- **Redis**: O serviço `redis` está disponível na porta `6379`.

**`application.properties`**:
- As configurações de conexão com o banco de dados e Redis estão definidas para `localhost`.
- A chave secreta do JWT e o tempo de expiração também estão definidos aqui.

### 3. Inicie os Contêineres Docker

Para iniciar o banco de dados MySQL e o Redis, utilize o Docker Compose:

```bash
docker-compose up -d
```

Este comando irá baixar as imagens necessárias e iniciar os contêineres em segundo plano.

### 4. Execute a Aplicação Spring Boot

Você pode executar a aplicação de duas maneiras:

**Usando o Maven Wrapper:**

```bash
./mvnw spring-boot:run
```

**Ou compilando e executando o JAR:**

```bash
./mvnw clean install
java -jar target/library-0.0.1-SNAPSHOT.jar
```

### 5. Acesse a Aplicação

Após a inicialização, a API estará disponível em `http://localhost:8080`.

### Documentação da API (Swagger)

A documentação da API é gerada automaticamente com o Springdoc OpenAPI e pode ser acessada através do seguinte endereço:

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`

## Endpoints da API

A API fornece endpoints para as seguintes funcionalidades:

- **Autenticação**:
  - `POST /auth/login`: Autentica um usuário e retorna um token JWT.
- **Usuários**:
  - `POST /usuarios`: Cadastra um novo usuário.
  - `GET /usuarios`: Lista todos os usuários.
- **Livros**:
  - `POST /livros`: Adiciona um novo livro.
  - `GET /livros`: Lista todos os livros.

Consulte a documentação do Swagger para obter a lista completa de endpoints e detalhes sobre como utilizá-los.