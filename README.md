#  TreenUp — Transformando Sustentabilidade em Jornada

##  Sobre o Projeto

O **TreenUp** é uma aplicação web voltada à sustentabilidade, com o objetivo de incentivar práticas sustentáveis por meio do registro de ações e do acompanhamento de recompensas.

O sistema permite visualizar usuários, consultar ações sustentáveis e registrar novas atividades. A aplicação integra um Front-End desenvolvido em React com uma API REST desenvolvida em Java, responsável pelo processamento e armazenamento dos dados.

##  Links do Projeto

- **Aplicação publicada (Vercel):** https://challenge-front-end-rust.vercel.app/
- **Repositório Front-End (GitHub):** https://github.com/tenorio085/ChallengeFront_End
- **API Java (Render):** https://sprint4-java-e7d9.onrender.com
- **Repositório Back-End (GitHub):** https://github.com/ThiagoTan/Sprint4-Java

##  Tecnologias Utilizadas

### Front-End
- React
- Vite
- TypeScript
- Tailwind CSS
- React Router DOM
- JavaScript
- HTML5 e CSS3
- Git e GitHub
- Vercel

### Back-End
- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Render

##  Integração com a API

O Front-End consome os endpoints da API Java por meio de requisições HTTP, utilizando a função `fetch()`.

| Método | Endpoint | Finalidade |
|---|---|---|
| GET | `/api/usuarios` | Listar usuários |
| GET | `/api/usuarios/{id}` | Consultar usuário por ID |
| POST | `/api/usuarios` | Cadastrar usuário |
| PUT | `/api/usuarios/{id}` | Atualizar usuário |
| GET | `/api/acoes` | Listar ações sustentáveis |
| GET | `/api/acoes/{id}` | Consultar ação por ID |
| POST | `/api/acoes?usuarioId={id}` | Registrar ação sustentável |
| PUT | `/api/acoes/{id}?usuarioId={id}` | Atualizar ação sustentável |
| DELETE | `/api/acoes/{id}` | Excluir ação sustentável |

**URL base da API:**

`https://sprint4-java-e7d9.onrender.com`

As chamadas à API estão centralizadas na camada de serviços do Front-End, com tipagem TypeScript para os dados recebidos e tratamento de carregamento, erros e respostas vazias.

##  Funcionalidades

- Navegação entre as páginas da aplicação.
- Consulta de usuários cadastrados.
- Exibição de ações sustentáveis.
- Visualização dos detalhes das ações.
- Cadastro de novas ações sustentáveis.
- Cálculo e apresentação de pontos, XP e tokens gerados.
- Comunicação com a API Java publicada.
- Interface com tema visual voltado à sustentabilidade.

##  Estrutura do Projeto

A estrutura principal está organizada em `src`, com componentes reutilizáveis, páginas e serviços de integração.

```text
challenge-front-end/
├── public/
├── src/
│   ├── assets/
│   ├── components/
│   ├── contexts/
│   ├── data/
│   ├── hooks/
│   ├── pages/
│   ├── services/
│   │   └── api.ts
│   ├── types/
│   ├── App.tsx
│   ├── main.tsx
│   └── index.css
├── .gitignore
├── index.html
├── package.json
├── package-lock.json
├── tsconfig.json
├── vite.config.ts
└── README.md
```

##  Como Executar Localmente

### Pré-requisitos

- Node.js e npm instalados.
- Git instalado.
- Visual Studio Code ou editor equivalente.

### 1. Clonar o repositório

```bash
git clone https://github.com/tenorio085/ChallengeFront_End.git
```

### 2. Entrar na pasta

```bash
cd ChallengeFront_End
```

### 3. Instalar as dependências

```bash
npm install
```

### 4. Configurar a URL da API

O arquivo `src/services/api.ts` utiliza a variável de ambiente `VITE_API_URL` e possui a URL publicada do Render como alternativa.

Caso deseje configurar a variável, crie um arquivo `.env` na raiz:

```env
VITE_API_URL=https://sprint4-java-e7d9.onrender.com
```

### 5. Iniciar o Front-End

```bash
npm run dev
```

O Vite exibirá o endereço local da aplicação no terminal, normalmente `http://localhost:5173`.

### 6. Gerar a versão de produção

```bash
npm run build
```

Os arquivos gerados ficam na pasta `dist`.

##  Deploy

O Front-End está publicado na Vercel e consome a API Java hospedada no Render.

- Front-End: https://challenge-front-end-rust.vercel.app/
- Back-End: https://sprint4-java-e7d9.onrender.com


Projeto acadêmico desenvolvido para o Challenge 2026 — Sprint 4.
