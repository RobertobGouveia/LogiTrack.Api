# LogiTrack Pro

API REST para gestao de frota, viagens e manutencoes, com painel de indicadores e persistencia em PostgreSQL.

## 1) Como configurar e rodar localmente

### Pre-requisitos
- Java 17+
- PostgreSQL em execucao
- Git (opcional)

> O projeto usa Maven Wrapper (`mvnw`/`mvnw.cmd`), entao nao e necessario instalar Maven globalmente.

### Passo a passo
1. Clone o repositorio e entre na pasta do projeto.
2. Crie um arquivo `.env` na raiz com base em `.env.example`.
3. Se estiver usando um PostgreSQL local, crie um banco chamado `logitrack` (ou ajuste as variaveis para outro nome).
4. Execute a aplicacao.

```powershell
.\mvnw.cmd spring-boot:run
```

5. Execute os testes.

```powershell
.\mvnw.cmd test
```

### Variaveis de ambiente locais

Arquivo `.env.example`:

```env
DB_URL=jdbc:postgresql://localhost:5432/logitrack
DB_USERNAME=postgres
DB_PASSWORD=sua_senha
```

### Configuracao atual da aplicacao
No arquivo `src/main/resources/application.properties`:
- `spring.config.import=optional:file:.env[.properties]`
- `server.port=${PORT:8080}` para suportar porta dinamica em cloud
- `spring.datasource.url=${DB_URL:jdbc:postgresql://${PGHOST:localhost}:${PGPORT:5432}/${PGDATABASE:logitrack}}`
- `spring.datasource.username=${DB_USERNAME:${PGUSER:postgres}}`
- `spring.datasource.password=${DB_PASSWORD:${PGPASSWORD:postgres}}`
- `spring.jpa.hibernate.ddl-auto=none`
- `spring.flyway.baseline-on-migrate=true`

Com isso, o projeto funciona tanto localmente com `.env` quanto em provedores como Railway usando variaveis `PGHOST`, `PGPORT`, `PGDATABASE`, `PGUSER` e `PGPASSWORD`.

## 2) Decisoes tecnicas, ferramentas e arquitetura

### Stack principal
- **Java 17**
- **Spring Boot 3.5.11**
- **Spring Web** para API REST
- **Spring Data JPA (Hibernate)** para persistencia
- **Flyway** para versionamento e inicializacao do schema
- **Spring Boot Actuator** para health checks e observabilidade minima
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
- `Actuator` (`/actuator/health`): health check para deploy e monitoramento

### Observacoes de design
- A API utiliza update parcial via `PATCH` nos modulos principais.
- Algumas validacoes (ex.: entidade nao encontrada, placa duplicada) estao na camada de servico.
- O dashboard usa consultas agregadas customizadas nos repositories.
- Foi criado um perfil `prod` em `src/main/resources/application-prod.properties` para desabilitar SQL detalhado e expor apenas endpoints de health/info no deploy.

## 3) Banco de dados e scripts

### Alteracoes de banco nesta entrega
Foi adicionada a migracao `src/main/resources/db/migration/V1__init.sql`.

### Justificativa
Antes, o projeto dependia de um schema previamente existente porque `spring.jpa.hibernate.ddl-auto=none` impede criacao automatica de tabelas. Em um ambiente novo como Railway, isso exigiria criacao manual do banco antes do primeiro boot. A migracao Flyway resolve esse problema com rastreabilidade e reproducibilidade.

### Script novo
Arquivo: `src/main/resources/db/migration/V1__init.sql`

Esse script cria:
- tabela `veiculos`
- tabela `manutencoes`
- tabela `viagens`
- chaves estrangeiras para `veiculo_id`
- `UNIQUE` para `placa`
- indices basicos para consultas frequentes

### Importante para evolucao
- Em banco vazio, o Flyway executa a migracao automaticamente no startup.
- Em banco ja existente sem historico do Flyway, `spring.flyway.baseline-on-migrate=true` evita conflito ao criar o baseline.
- Novas alteracoes estruturais devem ser adicionadas como novas migracoes (`V2__...sql`, `V3__...sql`, etc.).

## 4) Deploy no Railway

### O que o projeto ja possui para deploy
- Porta dinamica via `server.port=${PORT:8080}`
- Compatibilidade com variaveis do Railway (`PGHOST`, `PGPORT`, `PGDATABASE`, `PGUSER`, `PGPASSWORD`)
- Migracao automatica com Flyway para bootstrap do schema
- Endpoint de health em `/actuator/health`

### Passo a passo
1. Suba o projeto para um repositorio GitHub.
2. No Railway, crie um novo projeto a partir do repositorio.
3. Adicione um servico PostgreSQL no mesmo projeto.
4. No servico da aplicacao, defina a variavel:

```env
SPRING_PROFILES_ACTIVE=prod
```

5. Se preferir, voce tambem pode definir manualmente estas variaveis no servico da aplicacao:

```env
DB_URL=jdbc:postgresql://host:5432/database
DB_USERNAME=usuario
DB_PASSWORD=senha
```

Se nao definir `DB_*`, a aplicacao consegue usar as variaveis `PG*` do ambiente.

### Build e start sugeridos
Build command:

```bash
./mvnw clean package -DskipTests
```

Start command:

```bash
java -jar target/logitrack-pro-0.0.1-SNAPSHOT.jar
```

### Validacao apos deploy
Teste os endpoints abaixo:
- `GET /actuator/health`
- `GET /veiculos`
- `GET /manutencoes`
- `GET /viagens`
- `GET /dashboard`

Exemplo:

```powershell
Invoke-RestMethod https://SEU-DOMINIO.up.railway.app/actuator/health
```

