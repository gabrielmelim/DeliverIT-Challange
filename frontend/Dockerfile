# Dockerfile para Angular Frontend

# Etapa de build do Angular
FROM node:22.12.0-alpine AS build
WORKDIR /app

# Copia apenas os manifests primeiro (ajuda cache)
COPY package*.json ./
RUN npm install

# Copia o restante do código
COPY . .

# Build do Angular (SEM output-path custom)
RUN npm run build

# Etapa de runtime com Nginx
FROM nginx:alpine

# COPIA SOMENTE A PASTA browser DO PROJETO
# se o nome do projeto for "frontend" (padrão do Angular), é esse caminho:
COPY --from=build /app/dist/frontend/browser /usr/share/nginx/html

# Config do Nginx pra SPA Angular
COPY ./nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
