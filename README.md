# 📢 Курсовая работа: Приложение для управления объявлениями

![Java](https://img.shields.io/badge/Java-20-brightgreen) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7-blue) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-13+-blue)

## 📋 Описание проекта

Приложение для управления объявлениями, реализованное в рамках курсовой работы. Оно предоставляет пользователям возможность создавать, редактировать и удалять объявления, а также управлять их категориями и пользователями.

## 🛠️ Стек технологий

- **Backend:** Java 20, Spring Boot 2.7, Spring Data JPA, Liquibase
- **Frontend:** (Укажите, если есть)
- **База данных:** PostgreSQL
- **Сборка:** Maven
- **Логирование:** Logback
- **Тестирование:** JUnit, Mockito

🎯 Функциональные возможности
Регистрация и аутентификация пользователей.
Управление объявлениями (CRUD операции).
Управление категориями объявлений.
Поиск и фильтрация объявлений.
📂 Структура проекта
plaintext
Копировать код
src/
│
├── main/
│   ├── java/
│   │       ├── controller/         # REST-контроллеры
│   │       ├── dto/                # Объекты передачи данных (DTO)
│   │       ├── entity/             # Сущности базы данных
│   │       ├── repository/         # Репозитории
│   │       ├── service/            # Бизнес-логика
│   └── resources/
│       ├── db/
│       │   └── changelog/           # Миграции Liquibase
│       ├── application.properties   # Конфигурация
│       └── static/                  # Статические ресурсы
📜 Лицензия
Проект распространяется под лицензией MIT. Подробности см. в LICENSE.

✨ Авторы
Дмитрий Ильин - разработчик
