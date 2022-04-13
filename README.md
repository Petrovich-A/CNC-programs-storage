README.md# :bowtie: EPAM EXTERNAL JAVA TRAINING.

## JAVA WEB APPLICATION PROJECT:
>## CNC PROGRAMS STORAGE :v:

#### :white_check_mark: This Java web application allows to store CNC machines programs (CNC means computer numerical control :nut_and_bolt:). CNC programs are different formats simple text files included text as combination of latin symbols and digitals :floppy_disk:. 
Put another way, application is an archive for storing files with some text information. In order to save program a certain division employee of the enterprise have to register on the portal. After registration, user be able to submit the program to the archive using the HTML-form. 
___
#### :white_check_mark: As a best practice, an application is built using the Layered architecture (Model View Controller pattern), Data Access Object (DAO) and other most common basic patterns. The project is designed applying Eclipse IDE for Java Enterprise Edition Platform and Apache Tomcat to run the Servlet and JSP. The subject area information is stored in the MySQL relational database.

#### :one: realized: :ok_hand:
- [x] implemented database access technology using JDBC with custom connection pool :lock:
- [x] custom tag for HTML footer :copyright:
- [x] localizing web applications (eng :us:, rus :ru:) 
- [x] password hash :closed_lock_with_key:
- [x] pagination :scroll:
- [x] filters :eyes:
#### :two: To do list :clipboard:
- [ ] searching for several entity
- [ ] inner builder
#### :traffic_light: There are three roles:
___
:baby: Guest can:
* register
* see list of programs
* change language
___
:man: User can:
* save CNC Programs
* log in and log out
* user registration and authorization
* change language
* search CNC programs by name
* view registration information
* view user's programs list
___
:construction_worker: Administrator can:
* update CNC machines
* update CNC Programs
* update details
* update users
* read user's registration information
* view user's programs list
___
>### :page_facing_up: Database structure:
![alt text](https://github.com/Petrovich-A/CNC-programs-storage/blob/master/CNC_programs_storage.JPG?raw=true)