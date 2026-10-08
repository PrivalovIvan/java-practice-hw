# Система управления финансами предприятия (FMSE)

REST API сервис для учета денежных средств, планирования бюджетов и формирования финансовой отчетности предприятия.

## Технологический стек

* **Язык:** Java 21
* **Фреймворк:** Spring Boot (Web, Data JPA, Security, Validation)
* **База данных:** PostgreSQL
* **Миграции:** Liquibase
* **Безопасность:** JWT (JSON Web Token) + Google OAuth2
* **Маппинг:** MapStruct
* **Утилиты:** Lombok
* **Инфраструктура:** Docker, Docker Compose
* **Сборка:** Maven (Multi-module)

## Структура проекта и архитектура

Проект представляет собой многомодульное Maven-приложение:

1. `fmse-app` — основной модуль с бизнес-логикой и REST API.
2. `startup-logger-starter` — собственный Spring Boot Starter, который автоматически логирует информацию о среде (версия
   приложения, профиль, URL БД, время старта) при успешном запуске контекста.

В проекте спроектирована доменная модель с разделением прав доступа на уровне эндпоинтов (роли: ADMIN, FINANCE_MANAGER,
ACCOUNTANT, AUDITOR) и реализован AOP-аудит основных финансовых операций.

## Локальный запуск

Для быстрого развертывания приложения вам понадобится установленный Docker.

1. Клонируйте репозиторий:

```bash
git clone <url-репозитория>
cd java-practice-hw
```

2. Выполните сборку и запуск через Docker Compose:

```bash
docker-compose up -d --build 
```

3. Приложение будет доступно по адресу: http://localhost:8080

## Основные API-эндпоинты

#### Аутентификация

- POST /api/v1/auth/login — вход по email и паролю, получение JWT-токена.
- GET /oauth2/authorization/google — авторизация через Google (OAuth2).

#### Счета (Доступ: ADMIN, авторизованные пользователи)

- POST /api/v1/accounts — создание счета.
- GET /api/v1/accounts — получение списка счетов своего отдела.

#### Транзакции (Доступ: ACCOUNTANT, ADMIN)

- POST /api/v1/transactions — создание черновика транзакции (DRAFT).
- POST /api/v1/transactions/{id}/confirm — подтверждение транзакции (изменение балансов).
- POST /api/v1/transactions/{id}/storno — сторнирование операции.

#### Бюджеты (Доступ: FINANCE_MANAGER, ADMIN)

- POST /api/v1/budgets — создание плана бюджета для категории.

#### Отчеты (Доступ: FINANCE_MANAGER, ADMIN, AUDITOR)

- GET /api/v1/reports/cash/flow?startDate=...&endDate=... — получение отчета о движении денежных средств (ДДС).

#### Аудит (Доступ: AUDITOR)

- GET /api/v1/audits — получение логов действий пользователей (поддерживает пагинацию).

## Примеры запросов

### Счета (Accounts)

Создание нового счета (Доступ: ADMIN)

- Метод: POST
- URL: localhost:8080/api/v1/accounts
- Body (raw -> JSON):

```json
{
  "type": "CORPORATE_CARD",
  "currency": "RUB"
}
```

Получение списка счетов своего отдела

- Метод: GET
- URL: localhost:8080/api/v1/accounts
- Body: не требуется

### Транзакции (Transactions)

Создание транзакции расхода (Доступ: ACCOUNTANT, ADMIN).

Используем счет по умолчанию и категорию "Закупка оборудования" из тестовой БД.

- Метод: POST
- URL: localhost:8080/api/v1/transactions
- Body (raw -> JSON):

```json
{
  "type": "EXPENSE",
  "amount": 150000.00,
  "currency": "RUB",
  "fromAccount": "44444444-4444-4444-4444-444444444444",
  "category": "33333333-3333-3333-3333-333333333332"
}
```

Подтверждение транзакции (Изменение баланса)

Берем id транзакции, который вернулся в ответе на предыдущий запрос.

- Метод: POST
- URL: localhost:8080/api/v1/transactions/{вставь_id_транзакции}/confirm
- Body: не требуется

Сторнирование (Отмена) транзакции

- Метод: POST
- URL: localhost:8080/api/v1/transactions/{вставь_id_транзакции}/storno
- Body: не требуется

## Бюджеты (Budgets)

Создание бюджета на категорию "Заработная плата" (Доступ: FINANCE_MANAGER, ADMIN)

- Метод: POST
- URL: localhost:8080/api/v1/budgets
- Body (raw -> JSON):

```json
{
  "categoryId": "33333333-3333-3333-3333-333333333331",
  "amount": 500000.00,
  "startDate": "2026-10-01",
  "endDate": "2026-10-31"
}
```

## Отчеты (Reports)

Получение отчета о движении денежных средств (ДДС) (Доступ: FINANCE_MANAGER, ADMIN, AUDITOR)

- Метод: GET
- URL: localhost:8080/api/v1/reports/cash/flow?startDate=2026-09-01&endDate=2026-12-31
- Параметры (вкладка Params):
    - startDate = 2026-09-01
    - endDate = 2026-12-31

## 4. Отчеты (Reports)

Получение отчета о движении денежных средств (ДДС) (Доступ: FINANCE_MANAGER, ADMIN, AUDITOR)

- Метод: GET
- URL: localhost:8080/api/v1/reports/cash/flow?startDate=2026-09-01&endDate=2026-12-31
- Параметры (вкладка Params):
    - startDate = 2026-09-01
    - endDate = 2026-12-31
