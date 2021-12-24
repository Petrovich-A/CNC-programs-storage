README.md# EPAM EXTERNAL JAVA TRAINING.

# FINAL TASK. WEB PROJECTF: CNC PROGRAMS STORAGE

## Cоздать приложение,применяя технологии Servlet и JSP. Архитектура  приложения  должна  соответствовать  шаблонам Layeredarchitectureи MVC. Controllerможет быть только двух видов: контроллер роли или контроллер приложения. Информация о предметной области должна хранится в БД

### Требования к БД:
```
* Частьданныхв базе хранятся на кириллице, рекомендуется применять кодировку utf-8
* Технология доступа к БД толькоJDBC
* Для работы с БД в приложении должен быть реализованпотокобезопасный пул соединений,использовать synchronizedи volatileзапрещено.
* При проектировании БД рекомендуется использоватьнеменее 6-итаблиц
* Работу сданнымив приложении осуществлять посредствомшаблоновDAOили Repository.
* Реализовать защиту от sqlinjection.
```

### Базовые требованияк приложению
```
* Интерфейс приложения долженбыть локализован; выбор из языков: EN|BE|DEetc.
* Приложение должнокорректно обрабатывать возникающие исключительные ситуации, в том числе вести их логи. В качестве логгера использоватьLog4J2/SLF4J.
* Классы и другие сущности приложения должны быть грамотно структурированы по пакетам и иметь отражающую их функциональностьназвание.
* При  реализации  бизнес-логики  приложения  следует  при  необходимости  использовать шаблоны проектирования (например, шаблоны GoF: FactoryMethod, Command, Builder, Strategy, State, Observer, Singleton, Proxyetc).
* Для хранения пользовательской информации между запросами использовать сессию.
* Для перехвата и корректировки объектов запроса (request) и ответа (response) применить фильтры.
* Разрешается использовать технологииAspectJи Web-services.
* При реализации страниц JSPследует использовать тегибиблиотеки JSTL
* Использовать скриплеты запрещено.*При  реализации  пользовательского  интерфейса  разрешается  использовать любыетехнологииfront-endразработки (js, AJAX).
* Реализовать защиту от crosssitescripting(xss)
* Реализовать защиту от отповторного выполнения запроса нажатиемF5.
* Реализоватьсобственныетеги. *Просмотр “длинных списков”желательно организовывать в постраничном режиме.
* Валидацию входных данных производить на клиенте и на сервере.
* Документацию к проекту необходимо оформить согласно требованиям javadoc.
* Оформление кода должносоответствовать JavaCodeConvention.
* При развертывании приложения разрешается использовать технологию Mavenили Gradle.
* Приложение должносодержать тесты TestNG, JUnit, MockitoилиEasyMock.
* Приложение должнобыть размещено наGitHub илиBitbucket.
```
### Минимальные требованияк функциональности приложения
```
* Авторизация(signin) и выход(signout)в/из системы.
* Регистрацияпользователя и/или добавление артефакта предметной области системы.
* Просмотр информации (например: просмотр всех ставок тотализатора, статистики заказов, счетов и т.д.)
* Удаление информации (например: отмена заказа, удаление сущностии т.д.)
Добавление и модификация информации (например: создать и отредактировать товар, создать и отредактировать заказ и т.д.)
```
## Структура базы данных
![alt text](https://github.com/Petrovich-A/CNC-programs-storage/blob/master/CNC_programs_storage.JPG?raw=true)