# LogiTrack Pro

API REST para gestao de frota, viagens e manutencoes, com painel de indicadores de uso.

## 1) Como configurar e rodar localmente

### Pre-requisitos
- Java 17+
- PostgreSQL em execucao
- Git (opcional)

> O projeto usa Maven Wrapper (`mvnw`/`mvnw.cmd`), entao nao e necessario instalar Maven globalmente.

### Passo a passo
1. Clone o repositorio e entre na pasta do projeto.
2. Crie o banco (exemplo):
   - nome: `logitrack`
3. Configure as variaveis de ambiente no arquivo `.env` (raiz do projeto):

```env
DB_URL=jdbc:postgresql://localhost:5432/logitrack
DB_USERNAME=postgres
DB_PASSWORD=sua_senha
```

4. Rode a aplicacao:

```powershell
.\mvnw.cmd spring-boot:run
```

5. Rode os testes:

```powershell
.\mvnw.cmd test
```

### Configuracao atual de banco
No arquivo `src/main/resources/application.properties`:
- `spring.config.import=optional:file:.env[.properties]`
- `spring.datasource.url=${DB_URL}`
- `spring.datasource.username=${DB_USERNAME}`
- `spring.datasource.password=${DB_PASSWORD}`
- `spring.jpa.hibernate.ddl-auto=none`

## 2) Decisoes tecnicas, ferramentas e arquitetura

### Stack principal
- **Java 17**
- **Spring Boot 3.5.11**
- **Spring Web** para API REST
- **Spring Data JPA (Hibernate)** para persistencia
- **PostgreSQL** como banco relacional
- **Lombok** para reduzir boilerplate
- **Maven** (com Wrapper) para build e dependencias

### Arquitetura adotada
Arquitetura em camadas, com separacao de responsabilidades:
- `controller`: camada HTTP (entrada/saida da API)
- `service`: regras de negocio
- `repository`: acesso a dados com JPA
- `entity`: modelo persistente
- `dto`: contratos de entrada para criacao/atualizacao
- `dashboard`: agregacoes e metricas para painel

### Modulos/rotas principais
- `VeiculoController` (`/veiculos`): CRUD parcial com `PATCH` e `DELETE`
- `ViagemController` (`/viagens`): CRUD com listagem paginada
- `ManutencaoController` (`/manutencoes`): criacao, listagem, `PATCH` e `DELETE`
- `DashboardController` (`/dashboard`): indicadores consolidados

### Observacoes de design
- A API utiliza update parcial via `PATCH` nos modulos principais.
- Algumas validacoes (ex.: entidade nao encontrada, placa duplicada) estao na camada de servico.
- O dashboard usa consultas agregadas customizadas nos repositories.

## 3) Banco de dados e scripts

### Alteracoes de banco nesta entrega
Nao houve alteracao de schema do banco nesta entrega.

### Script novo
Nao se aplica nesta entrega (nenhum script novo foi necessario).

### Importante para evolucao
Como `spring.jpa.hibernate.ddl-auto=none`, o schema precisa existir previamente no PostgreSQL.
Se houver futuras mudancas estruturais, recomenda-se versionar scripts SQL (ou adotar Flyway/Liquibase) para manter rastreabilidade.

