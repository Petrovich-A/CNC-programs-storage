README.md# :bowtie: EPAM EXTERNAL JAVA TRAINING.

# JAVA WEB APPLICATION PROJECT:    
># CNC PROGRAMS STORAGE :v:

## The application is an archive for storing programs for CNC machines (computer numerical control). Control programs are written using G-CODE and are files with text in different formats. An employee of a certain division of the enterprise registers on the portal and gets the opportunity to submit the program to the archive. To do this, enter the program number, type of equipment, part number, etc.

## Web application designed using Servlet and JSP technologies. The architecture of the application follows the Layered architecture and MVC design patterns. The subject area information is stored in the MySQL database.

### :one: Реализовано: :ok_hand:
* :white_check_mark: the Layered architecture and MVC design patterns
* :white_check_mark: данные предметной области хранятся в базе даннх (MySQL)
* :white_check_mark: технология доступа к БД реализована посредством JDBC
* :white_check_mark: ConnectionPool (context.xml)
* :white_check_mark: пользовательский tag для footer
* :white_check_mark: интернализация приложения поддерживающая несколько языков (EN, RU)
### :two: To do list :clipboard: 
- [x] реализовать регистрацию пользователя
- [x] сделать страницу логинации
- [x] разобраться с генерацией Id
- [x] кодировка пароля
- [x] разобраться с ролью (вывести роль на мэин)
---
#### Требования к БД:
- Частьданныхв базе хранятся на кириллице, рекомендуется применять кодировку utf-8
- Технология доступа к БД толькоJDBC
- Для работы с БД в приложении должен быть реализованпотокобезопасный пул соединений,использовать synchronizedи volatileзапрещено.
- При проектировании БД рекомендуется использоватьнеменее 6-итаблиц
- Работу сданнымив приложении осуществлять посредствомшаблоновDAOили Repository.
- Реализовать защиту от sqlinjection.
---
#### Базовые требованияк приложению
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
---
#### Минимальные требованияк функциональности приложения
* Авторизация(signin) и выход(signout)в/из системы.
* Регистрацияпользователя и/или добавление артефакта предметной области системы.
* Просмотр информации (например: просмотр всех ставок тотализатора, статистики заказов, счетов и т.д.)
* Удаление информации (например: отмена заказа, удаление сущностии т.д.)
Добавление и модификация информации (например: создать и отредактировать товар, создать и отредактировать заказ и т.д.)
___
>## Структура базы данных :page_facing_up:
![alt text](https://github.com/Petrovich-A/CNC-programs-storage/blob/master/CNC_programs_storage.JPG?raw=true)