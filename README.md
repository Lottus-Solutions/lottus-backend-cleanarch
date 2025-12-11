# Lottus Library - Backend

Backend da aplicação Lottus, uma API de gerenciamento de biblioteca desenvolvida com Spring Boot, seguindo os princípios da Clean Architecture e com foco em alta performance e escalabilidade.

## ⚠️ Licença de Uso - Importante

Este projeto foi desenvolvido pela equipe **Lottus Solutions** como uma iniciativa beneficente e seu código é disponibilizado publicamente **exclusivamente para fins de consulta, estudo e inspiração**.

Conforme a [LICENÇA DE USO](LICENSE.md), é **expressamente proibido**:
- Redistribuir, republicar ou clonar o projeto para uso próprio (seja acadêmico, comercial ou não comercial).
- Modificar, adaptar ou criar obras derivadas com base neste código.
- Utilizar o nome "Lottus Solutions" sem consentimento prévio.

Para qualquer outra finalidade, por favor, entre em contato com a equipe.

---

## Tecnologias e Suas Funções

| Tecnologia | Função Principal |
| :--- | :--- |
| **Java 21** | Linguagem de programação principal, oferecendo recursos modernos e performance. |
| **Spring Boot 3** | Framework base para a construção da API, simplificando a configuração e o desenvolvimento. |
| **Spring Data JPA** | Facilita a persistência de dados e a comunicação com o banco de dados relacional. |
| **Spring Security** | Gerencia a autenticação e autorização, protegendo os endpoints da API. |
| **MySQL** | Banco de dados relacional principal, responsável pela persistência definitiva dos dados (usuários, livros, etc.). |
| **Redis** | Utilizado para duas finalidades críticas: (1) **cache de alta velocidade** para consultas frequentes, reduzindo a latência, e (2) **armazenamento temporário (com TTL)** de tokens JWT, controlando sessões de forma eficiente. |
| **RabbitMQ** | **Message Broker** que desacopla processos assíncronos. É usado para enfileirar tarefas pesadas, como o processamento de arquivos de upload, que são consumidas por um serviço separado (`uploader`). |
| **Maven** | Gerenciador de dependências e automação de build do projeto. |
| **Docker** | Permite a criação de ambientes consistentes e isolados para os serviços (MySQL, Redis, etc.). |
| **JWT** | Padrão de token para a autenticação segura e stateless entre o cliente e o servidor. |
| **Springdoc OpenAPI**| Gera automaticamente a documentação interativa da API (Swagger UI). |

## Arquitetura

O projeto segue os princípios da **Clean Architecture**, garantindo um código desacoplado, testável e de fácil manutenção. A regra principal é que as dependências sempre apontam para dentro, do mais externo (frameworks, UI) para o mais interno (regras de negócio).

```mermaid
graph TD;
    classDef outer fill:#D1E8FF,stroke:#333,stroke-width:2px;
    classDef middle fill:#A8D5FF,stroke:#333,stroke-width:2px;
    classDef inner fill:#7FC1FF,stroke:#333,stroke-width:2px;

    subgraph Infrastructure
        direction LR
        A[API Controllers]
        B[Spring Data / JPA]
        C[Redis Client]
        D[RabbitMQ Client]
    end

    subgraph Application
        direction LR
        E[Use Cases (Lógica de Negócio)]
        F[Interfaces / Ports (Contratos)]
    end

    subgraph Domain
        direction LR
        G[Entidades]
        H[Regras de Negócio Puras]
    end

    A -- Chama --> E;
    E -- Usa --> F;
    B -- Implementa --> F;
    C -- Implementa --> F;
    D -- Implementa --> F;
    E -- Manipula --> G;
    H -- Pertence a --> G;

    class A,B,C,D outer;
    class E,F middle;
    class G,H inner;
```

- **Domain**: Camada mais interna. Contém as entidades de negócio (`Livro`, `Aluno`) e as regras que não dependem de nenhuma tecnologia externa.
- **Application**: Orquestra os fluxos de negócio (Casos de Uso) e define as interfaces (Ports) que a camada de infraestrutura deve implementar.
- **Infrastructure**: Camada mais externa. Contém os detalhes de implementação, como os Controllers da API, a conexão com o banco de dados, o cache e o message broker.

## Fluxo de Dados e Comunicação

O diagrama abaixo ilustra como as tecnologias interagem em diferentes cenários, como autenticação, consulta de dados (com e sem cache) e processamento assíncrono.

```mermaid
sequenceDiagram
    actor User
    participant API
    participant Redis
    participant MySQL
    participant RabbitMQ
    participant UploaderService

    box rgb(255, 250, 240) Autenticação
        User->>+API: POST /auth/login (user, pass)
        API->>+MySQL: Validar credenciais
        MySQL-->>-API: Usuário OK
        API->>+Redis: Armazenar JWT com TTL (expiração)
        Redis-->>-API: OK
        API-->>-User: Retorna JWT Token
    end

    box rgb(240, 255, 245) Consulta de Dados
        User->>+API: GET /livros/{id} (com JWT)
        API->>API: Validar JWT
        API->>+Redis: Buscar "livro:{id}" no cache
        alt Cache Hit (Dado encontrado)
            Redis-->>-API: Dados do livro (cache)
            API-->>-User: Resposta rápida com dados do livro
        else Cache Miss (Dado não encontrado)
            Redis-->>-API: Nulo
            API->>+MySQL: SELECT * FROM livros WHERE id = ?
            MySQL-->>-API: Dados do livro (banco)
            API->>+Redis: Salvar "livro:{id}" no cache
            Redis-->>-API: OK
            API-->>-User: Resposta com dados do livro
        end
    end

    box rgb(240, 245, 255) Processamento Assíncrono
        User->>+API: POST /uploads/processar (arquivo.xlsx)
        API->>+RabbitMQ: Enfileirar job {caminho_arquivo}
        RabbitMQ-->>-API: Job recebido
        API-->>-User: { "mensagem": "Seu arquivo está sendo processado." }
        note right of RabbitMQ: O UploaderService consome a fila de forma independente.
        RabbitMQ->>+UploaderService: Consumir job da fila
        UploaderService->>UploaderService: Processar arquivo e salvar no banco
        UploaderService-->>-RabbitMQ: ACK (Confirmação)
    end
```

## Pré-requisitos

- **Java 21 ou superior**
- **Maven 3.6 ou superior**
- **Docker e Docker Compose**

## Como Configurar e Iniciar o Projeto

1.  **Clone o Repositório**
    ```bash
    git clone https://github.com/Lottus-Solutions/lottus-backend-cleanarch.git
    cd lottus-backend-cleanarch
    ```

2.  **Configure as Variáveis de Ambiente**
    O projeto utiliza o `compose.yaml` para orquestrar os serviços. As credenciais padrão estão definidas nele e no `application.properties`. Certifique-se de que as portas `3306` (MySQL) e `6379` (Redis) estejam livres em sua máquina.

3.  **Inicie os Contêineres Docker**
    Para iniciar o MySQL e o Redis, use o Docker Compose:
    ```bash
    docker compose up -d
    ```

4.  **Execute a Aplicação Spring Boot**
    Use o Maven Wrapper para compilar e executar o projeto:
    ```bash
    ./mvnw spring-boot:run
    ```
    Alternativamente, gere o pacote e execute-o:
    ```bash
    ./mvnw clean install
    java -jar target/library-0.0.1-SNAPSHOT.jar
    ```

5.  **Acesse a Aplicação**
    - **API**: `http://localhost:8080`
    - **Documentação (Swagger UI)**: `http://localhost:8080/swagger-ui.html`

## Endpoints da API

A API fornece um conjunto completo de endpoints para o gerenciamento da biblioteca. Abaixo estão os principais, agrupados por recurso.

-   **Autenticação**
    -   `POST /auth/login`: Autenticação de usuário e obtenção de token JWT.
    -   `POST /auth/logout`: Invalida o token JWT do usuário.

-   **Usuários (`/usuarios`)**
    -   `POST /`: Cadastra um novo usuário.
    -   `GET /`: Lista todos os usuários.
    -   `GET /me`: Retorna os dados do usuário logado.
    -   `PUT /`: Edita os dados do usuário logado.

-   **Alunos (`/alunos`)**
    -   `POST /`: Cadastra um novo aluno.
    -   `GET /`: Lista todos os alunos.
    -   `GET /matricula/{matricula}`: Busca um aluno por matrícula.
    -   `PUT /`: Edita um aluno existente.
    -   `DELETE /{id}`: Remove um aluno.

-   **Livros (`/livros`)**
    -   `POST /`: Cadastra um novo livro.
    -   `GET /`: Lista e filtra todos os livros.
    -   `GET /historico/{id}`: Exibe o histórico de empréstimos de um livro.
    -   `PUT /`: Atualiza um livro existente.
    -   `DELETE /{id}`: Remove um livro.

-   **Categorias (`/categorias`)**
    -   `POST /`: Cadastra uma nova categoria.
    -   `GET /`: Lista todas as categorias.
    -   `DELETE /{id}`: Remove uma categoria.

-   **Turmas (`/turmas`)**
    -   `POST /`: Cadastra uma nova turma.
    -   `GET /`: Lista todas as turmas.
    -   `PUT /`: Edita uma turma existente.
    -   `DELETE /{id}`: Remove uma turma.

-   **Empréstimos (`/emprestimos`)**
    -   `POST /`: Realiza um novo empréstimo de livro para um aluno.
    -   `GET /`: Lista todos os empréstimos (com filtros).
    -   `GET /atrasados`: Lista os empréstimos em atraso.
    -   `POST /renovar/{id}`: Renova um empréstimo existente.
    -   `POST /finalizar/{id}`: Finaliza (devolve) um empréstimo.

-   **Uploads (`/uploads`)**
    -   `POST /processar-arquivo`: Envia um arquivo (ex: `.xlsx`) para processamento assíncrono via RabbitMQ.

> Para detalhes completos sobre os parâmetros e corpos de requisição/resposta, consulte a **[Documentação do Swagger](http://localhost:8080/swagger-ui.html)**.
