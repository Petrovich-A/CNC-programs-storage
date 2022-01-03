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
- [ ] реализовать регистрацию пользователя
- [ ] сделать страницу логинации
- [ ] разобраться с генерацией Id
- [ ] кодировка пароля
- [ ] разобраться с ролью (вывести роль на мэин)
___
>## Структура базы данных :page_facing_up:
![alt text](https://github.com/Petrovich-A/CNC-programs-storage/blob/master/CNC_programs_storage.JPG?raw=true)