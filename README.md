# 📝 Todo List — Java Web App

## 📌 Описание

Проект "Todo List" — это веб-приложение на Java с использованием **Spring MVC (без Spring Boot)** и **Hibernate**, позволяющее управлять списком задач:

- Добавление, редактирование и удаление задач (CRUD)
- Хранение задач в базе данных MySQL
- Интерфейс с Bootstrap 5 и поддержкой пагинации
- Запуск MySQL через Docker Compose
- Деплой на Tomcat 10.1.40

---

## 🛠️ Технологии

- Java 17+
- Spring MVC (без Boot)
- Hibernate (JPA)
- Thymeleaf
- Bootstrap 5
- MySQL 8
- Docker + Docker Compose
- Apache Tomcat 10.1.40
- Maven

---

## 💻 Функциональность

Каждая задача состоит из:

- **Description** — описание задачи
- **Status** — текущее состояние (`IN_PROGRESS`, `DONE`, `PAUSED`)
- **Actions** — кнопки "Edit" и "Delete"

Пагинация реализована с помощью Bootstrap и Thymeleaf.
