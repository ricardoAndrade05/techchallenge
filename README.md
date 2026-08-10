# 🛵 Tech Challenge - Delivery API

API RESTful desenvolvida em **Spring Boot** para gerenciamento de pedidos e entregas, utilizando **PostgreSQL** containerizado e autenticação via **JWT (OAuth2 Resource Server)**.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 21+
* **Framework:** Spring Boot 4.x
  * Spring Data JPA
  * Spring Security (OAuth2 Resource Server / JWT)
  * Spring Validation
* **Banco de Dados:** PostgreSQL
* **Containerização:** Docker & Docker Compose
* **Documentação:** OpenAPI 3 / Swagger UI
* **Build Tool:** Maven

---

## 📋 Pré-requisitos

Antes de começar, garanta que você possui instalado na sua máquina:

* [Git](https://git-scm.com/)
* [Docker Desktop](https://www.docker.com/products/docker-desktop/) 
* [JDK 21+](https://www.oracle.com/java/technologies/downloads/)

## 🔐 Autenticação e Segurança

> ⚠️ **Aviso de Projeto Acadêmico:** Para fins de avaliação acadêmica e praticidade na execução local, as chaves de assinatura JWT (`app.key` e `app.pub`) foram disponibilizadas diretamente no repositório. Em um ambiente de produção real, essas chaves de segurança jamais seriam versionadas no Git.

## 🚀 Como Executar a Aplicação

> 💡 **Nota:** Por se tratar de um ambiente acadêmico e para facilitar a execução rápida, todas as variáveis de ambiente (portas, credenciais e conexões) já estão pré-configuradas diretamente no arquivo `docker-compose.yml`, eliminando a necessidade de criar um arquivo `.env`.

### 1. Execução Completa via Docker Compose (Recomendado)

Para subir o banco de dados PostgreSQL e a aplicação Spring Boot simultaneamente:

# 1. Clone o repositório
git clone git@github.com:ricardoAndrade05/techchallenge.git
cd techchallenge

# 2. Suba todos os serviços containerizados
docker compose up -d --build

# 3. Acompanhe os logs da aplicação
docker compose logs -f app