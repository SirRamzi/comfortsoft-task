# ComfortSoft Task

Сервис для поиска N-ного максимального числа из XLSX-файла. Принимает JSON-запрос с указанием пути к файлу и числа N, возвращает результат через REST API.

---

## Требования
- Java 17
- Maven 3.8+
- Docker (опционально)

---

## Запуск локально

### Сборка и запуск
1. Клонируйте репозиторий:  
   `git clone https://github.com/your-repo/comfortsoft-task.git`  
   `cd comfortsoft-task`

2. Соберите проект:  
   `mvn clean install`

3. Запустите приложение:  
   `java -jar target/comfortsoft-task-0.0.1-SNAPSHOT.jar`

### Пример запроса
Отправьте POST-запрос на `http://localhost:8080/findNthMax` с телом:  
`{"filePath": "src/test/resources/test.xlsx", "n": 3}`

Ожидаемый ответ: `30`

В атрибуте `"filePath"` необходимо передать полный пусть до xlsx файла на компьютере или относительный пусть к файлу в директории проекта.

Пример полного пути `"D:/Tables/numbers.xlsx`

Пример полного пути `src/test/resources/test.xlsx`

---

## Запуск в Docker

1. Соберите Docker-образ из корня проекта:  
   `docker build -t comfortsoft-task .`

2. Запустите контейнер:  
   `docker run -p 8080:8080 comfortsoft-task`

---

## Документация API
После запуска сервиса откройте в браузере:  
`http://localhost:8080/swagger-ui.html`

---

## Тестирование
- Тестовые XLSX-файлы расположены в `src/test/resources`.
- Запуск тестов: `mvn test`

