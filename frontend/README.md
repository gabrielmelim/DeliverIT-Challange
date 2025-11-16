
# Contas a Pagar – Frontend

Aplicação web moderna para cadastro e listagem de contas a pagar, desenvolvida em Angular, com foco em responsividade, experiência do usuário e integração total com API REST. Pronta para deploy em produção via Docker.

---

## O que foi feito

- Frontend Angular para cadastro e listagem de contas a pagar.
- Layout escuro, moderno e 100% responsivo (desktop/mobile).
- Formulário com máscaras e validações para datas e valores.
- Mensagens de sucesso/erro automáticas e auto-dismissing.
- Busca e paginação na listagem de contas.
- Integração completa com API REST para persistência dos dados.
- Pronto para deploy com Docker e Nginx.

## Tecnologias Utilizadas

- **Angular (standalone components):** Estrutura principal do frontend, sem módulos tradicionais.
- **SCSS:** Estilização avançada e responsiva, com media queries para mobile.
- **RxJS:** Requisições HTTP reativas.
- **Nginx + Docker:** Build de produção servido via Nginx, com Dockerfile e docker-compose para facilitar o deploy.

## Como funciona

1. **Instalação local:**
   - Pré-requisitos: Node.js 18+ e backend rodando em http://localhost:8080/api/pay-accounts
   - Instale as dependências:
     ```sh
     npm install
     ```
   - Execute em modo desenvolvimento:
     ```sh
     npm start
     ```
   - Acesse em [http://localhost:4200](http://localhost:4200)

2. **Execução com Docker (produção):**
   - Pré-requisito: Docker instalado
   - Rode o comando:
     ```sh
     docker compose up --build
     ```
   - O frontend estará disponível em [http://localhost:4200](http://localhost:4200)

3. **Integração com backend:**
   - O frontend espera o backend disponível em http://localhost:8080/api/pay-accounts
   - Todas as operações de cadastro e listagem são feitas via API REST.

## Estrutura do Projeto

- `src/app/pages/contas-page/` – Página principal com formulário, tabela, busca e paginação
- `src/app/services/conta.service.ts` – Serviço central de integração com API
- `src/app/models/conta.model.ts` – Modelo de dados alinhado ao backend
- `src/app/pages/contas-page/contas-page.html` – Estrutura visual (formulário, tabela, alertas)
- `src/app/pages/contas-page/contas-page.scss` – Estilos responsivos e modernos

## Experiência do Usuário

- **Responsividade:** Layout adaptado para qualquer tamanho de tela, sem scroll horizontal.
- **Feedback visual:** Alertas automáticos para sucesso e erro, desaparecendo após 4 segundos.
- **Facilidade de uso:** Máscaras e validações nos campos do formulário, evitando erros de digitação.
- **Busca e paginação:** Permite encontrar contas rapidamente mesmo com muitos registros.

## Deploy e Diferenciais

- **Deploy simplificado:** Basta rodar `docker compose up --build` para subir o frontend pronto para produção.
- **Nginx configurado:** SPA servida corretamente, com fallback para index.html.
- **Pronto para produção:** Build otimizado, seguro e escalável.

## Observações

- Certifique-se de que o backend está ativo e acessível na URL informada.
- Todos os campos do formulário são obrigatórios e validados.
- O projeto pode ser facilmente adaptado para outros endpoints ou integrações.

---

