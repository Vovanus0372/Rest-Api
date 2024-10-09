
# Bank Account API

## Описание

Это RESTful API для управления банковскими счетами, выполненное с использованием Spring Boot и PostgreSQL в качестве базы данных. API позволяет создавать счета, выполнять операции по пополнению и снятию средств, переводить деньги между счетами, а также просматривать баланс и историю транзакций.

## Возможности

- Создание банковских счетов
- Пополнение и снятие средств
- Перевод денег между счетами
- Получение информации о балансе и владельце счета
- Просмотр истории транзакций для каждого счета

## Стек технологий

- **Java** 11+
- **Spring Boot** 2.7+
- **Spring Data JPA** для работы с базой данных
- **PostgreSQL** в качестве базы данных
- **Maven** для управления зависимостями
- **Swagger** для документирования API

## Установка и запуск

### 1. Клонирование репозитория

```bash
git clone https://github.com/your-repo-url/bank-account-api.git
cd bank-account-api
```

### 2. Настройка базы данных

- Убедитесь, что PostgreSQL установлена и запущена на вашем компьютере.
- Создайте базу данных:

```bash
psql -U postgres -c "CREATE DATABASE bank_db;"
```

- В файле `src/main/resources/application.yml` настройте подключение к базе данных:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bank_db
spring.datasource.username=postgres
spring.datasource.password=your_password
```

### 3. Сборка и запуск приложения

#### Использование Maven:

```bash
./mvnw spring-boot:run
```

После запуска приложение будет доступно по адресу: `http://localhost:8080`.

### 4. Использование API

#### Создание счета

```bash
POST /api/accounts
```

Пример тела запроса (JSON):
```json
{
  "ownerName": "John Doe",
  "pinCode": "1234"
}
```

#### Пополнение счета

```bash
POST /api/accounts/deposit
```

Пример тела запроса (JSON):
```json
{
  "accountNumber": "1234567890",
  "amount": 500
}
```

#### Снятие средств

```bash
POST /api/accounts/withdraw
```

Пример тела запроса (JSON):
```json
{
  "accountNumber": "1234567890",
  "amount": 100,
  "pinCode": "1234"
}
```

#### Перевод между счетами

```bash
POST /api/accounts/transfer
```

Пример тела запроса (JSON):
```json
{
  "fromAccountNumber": "1234567890",
  "toAccountNumber": "0987654321",
  "amount": 200,
  "pinCode": "1234"
}
```

#### Получение всех счетов

```bash
GET /api/accounts
```

#### Получение истории транзакций для счета

```bash
GET /api/accounts/{accountNumber}/transactions
```

### 5. Тестирование

Для запуска тестов используйте команду:

```bash
./mvnw test
```
или для Gradle:
```bash
./gradlew test
```

## Swagger документация

Документация к API доступна по адресу:

```
http://localhost:8080/swagger-ui.html
```

## Принятые решения

1. **Spring Boot и JPA**: Использование Spring Boot упрощает конфигурацию и создание RESTful API, а JPA (с использованием Spring Data) автоматизирует работу с базой данных.
2. **PostgreSQL**: Для базы данных была выбрана PostgreSQL, так как она широко поддерживается и надежна.
3. **PIN-код**: Операции списания средств требуют ввода правильного PIN-кода для повышения безопасности.
4. **Обработка ошибок**: В случае возникновения ошибок API возвращает понятные сообщения и соответствующие HTTP-коды.
