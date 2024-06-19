# Digital Library (Book Management System)
This web application, built using Spring MVC and Thymeleaf, offers a comprehensive solution for managing a library's book inventory and tracking the borrowing status of books. It provides a user-friendly interface for managing books and users, making the process of lending and returning books seamless and efficient.
---
## Features
```
- CRUD Operations for Books: Easily add, update, delete, and view books in the library.
- CRUD Operations for Users: Manage library users with straightforward create, read, update, and delete functionality.
- Book Assignment: Assign books to users.
- Book Return: Free up books when they are returned to the library.
- User Book List: Display the list of books currently borrowed by each user.
```
---
## Technologies used
```
- Java 17
- Spring MVC
- Spring Data JPA
- Thymeleaf
- Postgres
- JDBCTemplate (v1)
- Hibernate
- Maven
```

Examples:
### /people
<img width="399" alt="Снимок экрана 2024-06-19 в 15 26 46" src="https://github.com/squ1ky/Digital-Library/assets/86202668/41f18d45-0104-4453-a4c4-d31e1a6be141">

### /people/{id} (With books)
<img width="400" alt="Снимок экрана 2024-06-19 в 15 30 55" src="https://github.com/squ1ky/Digital-Library/assets/86202668/d09c48b9-0b73-4f8b-91b9-b55d6763228d">

### /people/{id} (With overdue books)
<img width="367" alt="Снимок экрана 2024-06-19 в 15 44 31" src="https://github.com/squ1ky/Digital-Library/assets/86202668/495b6ed9-e806-4e3a-8688-f336f284af87">

### /people/{id} (Without books)
<img width="336" alt="Снимок экрана 2024-06-19 в 15 31 49" src="https://github.com/squ1ky/Digital-Library/assets/86202668/1f46865d-d5db-4ff4-b4af-d753fa9016fc">

### /books
<img width="432" alt="Снимок экрана 2024-06-19 в 15 33 08" src="https://github.com/squ1ky/Digital-Library/assets/86202668/eb8c7d16-7826-4995-b462-0f46fc640d84">

### /books?page=0&books_per_page=10&sort_by_year=true
<img width="433" alt="Снимок экрана 2024-06-19 в 15 34 08" src="https://github.com/squ1ky/Digital-Library/assets/86202668/f0318890-1e94-4999-99e4-b4eefd4e0b92">

### /books/{id} (With owner)
<img width="598" alt="Снимок экрана 2024-06-19 в 15 34 55" src="https://github.com/squ1ky/Digital-Library/assets/86202668/cc520338-d551-4e3f-97b5-4517ac85edb8">

### /books/{id} (Without owner)
<img width="664" alt="Снимок экрана 2024-06-19 в 15 36 01" src="https://github.com/squ1ky/Digital-Library/assets/86202668/047da382-b082-4c3a-a28f-172224487b62">

