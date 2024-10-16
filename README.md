<h1 align="center">
📖 Library (API)
</h1>

## 📚 Project

My first api where through it I was able to use java with the spring framework, where it is currently under development.

## 👨‍🏫 Class Diagram

```mermaid
classDiagram
    class Cliente {
        - int id
        - String nome
        - String email
    }

    class Livro {
        - int id
        - String titulo
        - String autor
        - double preco
    }

    class Livraria {
    }

    Cliente *-- Livraria
    Livro *-- Livraria
```

## 🔍 Operation Flows

1. **Create Client/Book:** Clients or books can cadastrate in the system.
2. **Find Client/Book:** Return a list of all clients or books in database.
3. **Modify Client/Book:** Picks a registered client or book and modify some feature.
4. **Delete Client/Book:** Picks a registered client or book and delete.

## 👨‍💻 Endpoints

<details>
    <summary><b>Clients</b></summary>
    <img src="./images/pg01- backend.png" alt="">
</details>

<details>
    <summary><b>Books</b></summary>
   <img src="./images/pg02-backend.png" alt="">
</details>

## 💻 Technology
- Language: Java
- Framework: Spring (Web, JPA) -> Security in progress
- Database: PostgreSQL
- Maven

# 👨‍💻 Dev
Yan Carlos

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/yan-carlos-00a740251/)
