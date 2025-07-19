## 🚀 **Literalura**

[![Status](https://img.shields.io/badge/status-concluído-success?style=flat-square)](https://github.com/seu-usuario/literalura)
[![Java](https://img.shields.io/badge/Java-21-blue?logo=java&style=flat-square)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?logo=springboot&style=flat-square)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?logo=postgresql&style=flat-square)](https://www.postgresql.org/)
[![Maven](https://img.shields.io/badge/Maven-3.8+-orange?logo=apachemaven&style=flat-square)](https://maven.apache.org/)

---

### 📚 **Sobre o Projeto**

**Literalura** é um catálogo de livros **interativo via console**, desenvolvido como desafio do curso **Spring Boot da Alura**.  
Ele consome a **API pública Gutendex** para buscar livros e autores, armazenando os dados localmente em um **PostgreSQL**, para consultas rápidas sem sobrecarregar a API.

---

### ✨ **Funcionalidades**

✅ **1** — Buscar livros por título na Gutendex e salvar no banco  
✅ **2** — Listar todos os livros salvos  
✅ **3** — Listar todos os autores cadastrados  
✅ **4** — Filtrar autores vivos em um ano específico  
✅ **5** — Listar livros por idioma (ex: `pt`, `en`, `es`)

---

### ⚙️ **Tecnologias**

🚀 **Java 21** — versão moderna, com novas features  
🌱 **Spring Boot** — base para a aplicação  
🗃️ **Spring Data JPA** — consultas e persistência simplificadas  
🐘 **PostgreSQL** — banco de dados robusto  
🧩 **Maven** — build e dependências  
🔗 **Jackson** — processamento JSON  
⚡ **Lombok** — elimina código repetitivo

---

### 🏃‍♂️ **Como Executar**

> **Pré-requisitos:**  
> - ✅ Java 21+  
> - ✅ Maven 3.8+  
> - ✅ PostgreSQL rodando

1️⃣ **Clone o projeto**
```bash
git clone https://github.com/seu-usuario/literalura.git
cd literalura
```

2️⃣ **Configure o banco**

- Crie um banco **literalura** (ou outro nome, ajustando no `application.properties`).

```properties
# Exemplo
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

3️⃣ **Execute**

```bash
mvn spring-boot:run
```

4️⃣ **🎉 Pronto!** O menu aparecerá no terminal para usar todas as funções.

---

### 🌐 **API**

- Este projeto utiliza: [Gutendex API](https://gutendex.com/)

---

### 👨‍💻 **Autor**

Feito com ❤️ por **Rafael Lima**  
[![GitHub](https://img.shields.io/badge/GitHub-Rafael%20Lima-181717?logo=github&style=flat-square)](https://github.com/seu-usuario)
