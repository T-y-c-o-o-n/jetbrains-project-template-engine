# Тестовое задание для стажировки **JetBrains** на направление "User experience improvements in internal product (backend)"

### Сервис для формирования шаблонизированных сообщений
2 эндпоинта:
* *POST* запрос `api/1/template`. Загружает шаблон. Запрос содержит имя шаблона, текст с подстановками вида `$value$` и список *url* получателей. Пример шаблона:

`{
    "templateId": "internshipRequest",
    "template": "JetBrains Internship in $teamName$ team.",
    "recipients": ["http://localhost:8081/some.server.url/endpoint-1",
                   "http://localhost:8081/some.server.url/endpoint-2"]
}`

* *POST* запрос `api/1/template/sent`. Отправляет шаблон. Запрос содержит имя шаблона и список переменных, которые необходимо подставить в шаблон. Пример запроса:

`{ "templateId": "internshipRequest", "variables": [{"teamName": "Analytics Platform"}] }`

В результате на эндпоинты получателей отправляется *POST* запрос с сообщением. Пример сообщения:

`{ "message": "JetBrains Internship in Analytics Platform team." }`

