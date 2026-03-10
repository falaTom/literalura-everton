# 📚 LiterAlura - Catálogo de Livros

<p align="center">
  <img src="https://img.shields.io/static/v1?label=STATUS&message=CONCLUIDO&color=GREEN&style=for-the-badge" alt="Status Concluído">
  <img src="https://img.shields.io/static/v1?label=JAVA&message=21&color=orange&style=for-the-badge&logo=java" alt="Java 21">
  <img src="https://img.shields.io/static/v1?label=SPRING&message=BOOT&color=brightgreen&style=for-the-badge&logo=spring" alt="Spring Boot">
</p>

## 📝 Descrição do Projeto
O *LiterAlura* é um sistema de catálogo de livros desenvolvido como desafio final do módulo de Java/Spring Boot. A aplicação consome a API *Gutendex*, processa dados JSON e os armazena em um banco de dados relacional (PostgreSQL), permitindo buscas dinâmicas e filtragens inteligentes diretamente pelo console.

## 🔨 Funcionalidades
- Busca por título: Pesquisa livros na API externa e registra automaticamente no banco local.
- Listagem Geral: Exibe todos os livros e autores já cadastrados.
- Filtro de Autores: Localiza escritores que estavam vivos em anos específicos.
- Filtro por Idioma: Lista obras disponíveis em siglas como en, pt, es ou fr.

## 🛠️ Tecnologias Utilizadas
- *Java 21*: Utilizando as últimas funcionalidades da linguagem.
- *Spring Boot 3.2.3*: Framework base para a aplicação.
- *Spring Data JPA*: Para persistência de dados.
- *PostgreSQL*: Banco de dados relacional.
- *Lombok*: Para um código mais limpo e produtivo.
- *Jackson*: Manipulação de dados JSON.

## 📁 Acesso ao Projeto
Você pode clonar este repositório usando o comando:
```bash
git clone [https://github.com/SEU-USUARIO/desafio-literalura.git](https://github.com/SEU-USUARIO/desafio-literalura.git)