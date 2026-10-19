# Treen Challenge — API REST Java (Sprint 4)

API REST para o Treen Challenge. Preserva a ideia da Sprint 3: ações sustentáveis geram pontos, XP e TreenTokens. Evolução para Spring Boot e endpoints REST.

## Tecnologias
Java 21, Spring Boot 3.5, Spring Web, Spring Data JPA (**Repository, não DAO**), Bean Validation, H2 local, PostgreSQL para deploy e Docker.

## Estrutura
- `src/main/java/br/com/treenchallenge/model`: `Usuario` e `AcaoSustentavel`
- `repository`: interfaces Spring Data JPA
- `service`: regras de negócio (BO)
- `controller`: recursos/endpoints REST
- `config/CorsConfig.java`: CORS por variável de ambiente
- `src/legacy-java`: fontes originais da Sprint 3 preservadas como referência; não são compiladas pelo Spring
- `Dockerfile`: build e execução no Render

## Executar localmente
Requer JDK 21. No PowerShell: `mvnw.cmd spring-boot:run`. No Linux/macOS: `./mvnw spring-boot:run`. API: `http://localhost:8080`. H2 Console: `http://localhost:8080/h2-console`, JDBC URL `jdbc:h2:mem:treendb`, usuário `sa`, senha vazia.

## Endpoints
| Método | URI | Resultado |
|---|---|---|
| GET | `/api/usuarios` | Lista usuários — 200 |
| GET | `/api/usuarios/{id}` | Detalha usuário — 200/404 |
| POST | `/api/usuarios` | Cria usuário — 201 |
| PUT | `/api/usuarios/{id}` | Atualiza usuário — 200/400/404 |
| DELETE | `/api/usuarios/{id}` | Exclui usuário — 204/404 |
| GET | `/api/acoes` | Lista ações — 200 |
| GET | `/api/acoes/{id}` | Detalha ação — 200/404 |
| GET | `/api/acoes/usuario/{usuarioId}` | Ações de um usuário — 200/404 |
| POST | `/api/acoes?usuarioId=1` | Registra ação — 201 |
| PUT | `/api/acoes/{id}?usuarioId=1` | Atualiza ação — 200/400/404 |
| DELETE | `/api/acoes/{id}` | Exclui ação — 204/404 |

POST `/api/usuarios` exemplo: `{"nome":"Ana Silva","email":"ana@example.com","cidade":"São Paulo"}`

POST `/api/acoes?usuarioId=1` exemplo: `{"descricao":"Reciclagem de papel","quantidade":5,"unidade":"kg","data":"2026-10-19","categoria":"RECICLAGEM"}`. Categorias aceitas: `ENERGIA` e `RECICLAGEM`; quantidade deve ser positiva. Pontos, XP e tokens são calculados pelo backend.

## Deploy no Render
1. Publique a pasta em um repositório GitHub.
2. Render → New → Web Service → conecte o repositório → runtime Docker.
3. Para persistência, crie PostgreSQL no Render e configure `DB_URL=jdbc:postgresql://HOST:5432/DB`, `DB_USERNAME`, `DB_PASSWORD`, `DB_DRIVER=org.postgresql.Driver`, `DDL_AUTO=update` e `CORS_ALLOWED_ORIGINS=https://SEU-FRONTEND.vercel.app`.
4. Após deploy, teste `https://SUA-API.onrender.com/api/usuarios`.
Sem PostgreSQL, o perfil padrão usa H2 em memória e perde dados ao reiniciar.

## Integração front-end
Use `fetch` e concentre chamadas em um service TypeScript; Axios não é necessário. Configure `CORS_ALLOWED_ORIGINS` com a origem exata publicada na Vercel. O repositório do front-end não estava dentro do ZIP Java fornecido, então a integração visual ainda precisa ser conectada e validada.

## Checklist Sprint 4
- [x] Código da Sprint 3 preservado como referência.
- [x] Spring Boot e API REST.
- [x] Model, Repository (sem DAO), Service/BO e Controller/Resource.
- [x] CRUD de usuários e ações.
- [x] Validação de entrada e tratamento global de erros.
- [x] CORS configurável.
- [x] Dockerfile alinhado ao tutorial Render/Java 21.
- [x] README com endpoints e instruções.
- [ ] Build/testes Maven executados com dependências disponíveis.
- [ ] PostgreSQL real configurado no Render e persistência validada.
- [ ] Repositório GitHub publicado e URL do deploy confirmada.
- [ ] URL real da Vercel configurada em CORS.
- [ ] Integração testada com o front-end real.
- [ ] Prints reais de execução/deploy incluídos na documentação final.


## Organização do código Java

O projeto segue a estrutura padrão do Maven/Spring Boot:

- `src/main/java/br/com/treenchallenge/`: código principal da API Spring Boot.
- `config/`: configurações, incluindo CORS.
- `controller/`: endpoints REST.
- `model/`: entidades/modelos.
- `repository/`: acesso aos dados.
- `service/`: regras de negócio.
- `exception/`: tratamento de exceções.
- `legacy/`: classes da versão Java de console, mantidas separadas da API para evitar conflito com classes de mesmo nome, como `Usuario` e `AcaoSustentavel`.
- `src/main/resources/`: configurações da aplicação.
- `src/test/java/`: testes.

As classes antigas foram movidas de `src/legacy-java/` para o pacote `br.com.treenchallenge.legacy`, com declaração de pacote adicionada. Isso coloca os arquivos Java dentro da raiz de código-fonte reconhecida pelo Maven/IntelliJ.
