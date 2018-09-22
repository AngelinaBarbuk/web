Сервис загадок. Представляет из себя хранилище загадок с возможностью добавления новых. Пользователь может ответить на загадку и получить статистику предыдущих ответов.
Загадка состоит из текста загадок, вариантов ответов и правильного ответа.

Выбран REST подход.

**•	Получение списка загадок.** Возвращает заголовки загадок.
**REQ:  GET /puzzle
RESP:  200 Content-Type: application/vnd.puzzles.list+json**
```json
{
	"links": {
		"self": "/puzzle"
	},
	“puzzles”: [{
			“text”: ”QWERTY”,
			"links": {
				"self": "/puzzle/123"
			}
		}
	]
}
```

**•	Получение загадки по id.** Возвращает текст загадки и возможные ответы
**REQ:  GET /puzzle/{id}
RESP:  200 Content-Type: application/vnd.puzzles.puzzle+json**
```json
{
	“text”: ”QWERTY”,
	“answers”:
	[{
			“answerId”: ”1”,
			“answerText”: ”answer 1”
		}
	],
	"links": {
		"self": "/puzzle/123",
		"answer": "/puzzle/123/answer"
	}

}
```

**•	Добавление своей загадки.** Принимает текст загадки, возможные ответы и верный ответ. Возвращает ссылку на созданную загадку.
**REQ: POST /puzzle**
```json
{
	“text”: ”QWERTY”,
	“answers”:
	[{
			“answerId”: ”1”,
			“answerText”: ”answer 1”
		}
	]
	“correct”: ”1”
}
```
**RESP:  201 Location: /puzzle/{id}**

**•	Ответить на загадку.** Возвращает в поле result верный или неверный ответ, correct - количество правильных ответов, wrong - количество неправильных.
**REQ: POST /puzzle/{id}/answer**
```json
{
	“answer”: “1”
}
```
**RESP: 200 Content-Type: application/vnd.puzzles.stat+json**
```json
{
	“result”: true,
	“correct”: ”12”,
	“wrong”: ”3”,
	"links": {
		"self": "/puzzle/123"
	}
}
```
