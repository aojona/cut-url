# Cut URL

Данный проект представляет собой REST сервис на языке Java для создания простых URL из более сложных. 
Генерация новой ссылки происходит по одному из алгоритмов: Base-62, MurMur-3 или SHA-256

## Стек используемых технологий

* [Spring](https://spring.io/) — фреймворк для разработки на Java
* [Gradle](https://gradle.org) — инструмент для автоматизации сборки проекта
* [Lombok](https://projectlombok.org/) — библиотека для генерации boilerplate кода
* [Redis](https://redis.io) — база данных типа «ключ–значение»
* [Guava](https://github.com/google/guava) — набор инструментов для повышения качества кода
* [JUnit 5](https://github.com/junit-team/junit5) — фреймворк для модульного тестирования на Java

## Функционал

| Метод | URL             | Операция                     |
|:-----:|-----------------|------------------------------|
| POST  | api/url         | генерация простой ссылки     |
|  GET  | api/url/{token} | редирект на оригинальный URL |

## Конфигурация

В [application.yml](src/main/resources/application.yml):

- `spring.data.redis.host` — хост сервера Redis
- `spring.data.redis.port` — порт сервера Redis
- `spring.cache.redis.time-to-live` — время хранения записи в кэш
- `redis.time-to-live` — время хранения записи в базе данных
- `redis.time-unit` — соответсвующая единица измерения
- `strategy.encoder` — стратегия генерации короткой ссылки
- `strategy.token-length` — длина токена новой ссылки

## Запуск

1. Настроить подключение к Redis или запустить локально
```shell
docker run --name some-redis -p 6379:6379 -d redis
```
2. Скомпилировать и запустить приложение
```shell
./gradlew bootRun
```

## Эндпоинты

 ```http
 POST /api/url
 ```
 
 ![short-url-1](https://user-images.githubusercontent.com/112020091/227621584-46796e8e-bc2c-40d0-8207-c242d0f36e6b.svg)

#### Request Body

 ```json
 {
   "url": "https://github.com/aojona/cut-url/blob/main/README.md"
}
 ```

#### Response Body

 ```json
 {
   "shortUrl": "https://localhost:8080/api/url/token",
   "expireAt": "2023-03-15T01:43:39"
}
 ```

#### HTTP status:

* 200 — OK
* 400 — Bad Request

##

 ```http
 GET /api/url/{token}
 ```
 
![short-url-2](https://user-images.githubusercontent.com/112020091/227621627-bbf35f9c-13ea-40ee-87dc-cf0797ed4544.svg)

#### HTTP status:

* 302 — Found
* 400 — Bad Request
