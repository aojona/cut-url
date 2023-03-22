# Cut URL

Данный проект представляет собой REST сервис на языке Java для создания простых URL из более сложных

## Стек используемых технологий

* [Spring](https://spring.io/) — фреймворк для разработки на Java
* [Gradle](https://gradle.org) — инструмент для автоматизации сборки проекта
* [Lombok](https://projectlombok.org/) — библиотека для генерации boilerplate кода
* [Redis](https://redis.io) — база данных типа «ключ–значение»

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
- `strategy.encoder` — стратегия генерации простой ссылки
- `strategy.token-length` — длина простой ссылки


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

* 201 — Created
* 400 — Bad Request

##

 ```http
 GET /api/url/{token}
 ```

#### HTTP status:

* 302 — Found
* 400 — Bad Request

