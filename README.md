# Boas-Vindas! 🌟

Olá! Meu nome é **Gabriel Melim** e este repositório foi criado para documentar de forma clara e organizada o **desafio técnico proposto pela Deliver IT**.  
Aqui você encontrará uma visão resumida e objetiva sobre o que será desenvolvido, bem como os links para os repositórios **Back-End** e **Front-End**, onde o código completo está versionado.

---

## 🚀 Sobre o Desafio

O objetivo deste desafio é desenvolver uma aplicação completa composta por:

- **Back-End em Java (Spring Boot)**  
- **Front-End em Angular**  
- **Persistência em banco relacional**  
- **Testes unitários**  
- **Containerização (Docker)**  

Trata-se de um sistema simples de **Contas a Pagar**, onde o usuário poderá **cadastrar** contas e **listar** contas já registradas, incluindo cálculo automático de multa e juros por atraso.

---

## 🧠 Regras de Negócio (Resumo)

Ao cadastrar uma conta, devem ser informados:

- **Nome**
- **Valor Original**
- **Data de Vencimento**
- **Data de Pagamento**

A aplicação deverá:

1. **Validar todos os campos** (todos são obrigatórios).  
2. **Calcular atraso**, caso a data de pagamento seja posterior à data de vencimento.  
3. Aplicar as regras:

| Dias de atraso | Multa | Juros ao dia |
|----------------|-------|--------------|
| Até 3 dias     | 2%    | 0,1%         |
| Mais de 3 dias | 3%    | 0,2%         |
| Mais de 5 dias | 5%    | 0,3%         |

4. **Persistir no banco**:  
   - quantidade de dias em atraso  
   - regra aplicada  
   - valor corrigido  

📝 A listagem das contas deve retornar:  
- Nome  
- Valor original  
- Valor corrigido  
- Dias de atraso  
- Data de pagamento  

---

## 🛠️ Tecnologias Avaliadas

Segundo a documentação oficial do desafio:  
- Java 8+  
- Spring Boot  
- JPA / Hibernate  
- Maven ou Gradle  
- Flyway ou Liquibase  
- JUnit  
- AngularJS ou Angular 2+  
- Docker  

---

## 📦 Repositórios do Projeto

Aqui estão os dois projetos

### 🔹 **Back-End (Java / Spring Boot)**  
➡️[back-end]()
 
### 🔹 **Front-End (Angular)**  
➡️[front-end]()

---

## 📄 Documento Oficial do Desafio

Você pode baixar o PDF original aqui:

👉 **[Download do Teste Prático Java (PDF)](./TestePraticoJava.pdf)**

---

## ✨ Contato

Se quiser saber mais sobre mim ou ver meus outros projetos:

- **Email**: gabrielmelim2012@hotmail.com  
- **LinkedIn**: https://www.linkedin.com/in/gabrielmelim/  

---

Obrigado por visitar! 🚀  
Que a força esteja com você! 🌌
