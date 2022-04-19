README.md# :bowtie: EPAM EXTERNAL JAVA TRAINING.

## JAVA WEB APPLICATION PROJECT:

* [Project's description](#description)
* [Features](#features)
* [Database structure](#database)

>## CNC PROGRAMS STORAGE :v:

<a name="description"></a>
## Project's description

#### :one: This Java web application allows to store CNC machines programs (CNC means computer numerical control :nut_and_bolt:). CNC programs are different formats simple text files included text as combination of latin symbols and digitals :floppy_disk: like this:
```
%
N001M73G99
N002G50X81000Z39000T01S21
N003G00X27500Z8000H08M04
N004Z3750M08
N005G01X21000F35
N006G00X90000Z45000H00M05
N007M30
%
```
 
#### :two: Put another way, application is an archive for storing files with some text information. In order to save program a certain division employee of the enterprise have to register on the portal. After registration, user be able to submit the program to the archive using the HTML-form. 
#### :three: As a best practice, an application is built using the Layered architecture (Model View Controller pattern), Data Access Object (DAO) and other most common basic patterns. The project is designed applying Eclipse IDE for Java Enterprise Edition Platform and Apache Tomcat to run the Servlet and JSP. The subject area information is stored in the MySQL relational database.

<a name="features"></a>
## Features: :ok_hand:
- :white_check_mark: implemented database access technology using JDBC with custom connection pool :notebook_with_decorative_cover:
- :white_check_mark: while processing emerging exceptional situations, as well as positive scenarios, their logs are kept in. The application is used Log4J2 as a logger. :bookmark_tabs:
- :white_check_mark: custom tag for HTML footer :copyright:
- :white_check_mark: localizing web applications (eng :us:, rus :ru:) 
- :white_check_mark: password is hashed with salt :closed_lock_with_key:
- :white_check_mark: pagination :scroll:
- :white_check_mark: filters :eyes:
- :white_check_mark: implemented protection against cross-site scripting (xss) :lock:
## To do: :clipboard:
- [ ] searching for several entity
- [ ] inner builder
- [ ] implement more unit test coverage
- [ ] implement program text renumbering
- [ ] change password
- [ ] sending email when registration successful
### :traffic_light: There are three roles:
___
:baby: **Guest** can:
* register
* see list of programs
* change language
___
:man: **User** can:
* save CNC Programs
* log in and log out
* user registration and authorization
* change language
* search CNC programs by name
* view registration information
* view user's programs list
___
:construction_worker: **Administrator** can:
* update CNC machines
* update CNC Programs
* update details
* update users
* read user's registration information
* view user's programs list
___

<a name="database"></a>
## :page_facing_up: Database structure:
![alt text](https://github.com/Petrovich-A/CNC-programs-storage/blob/master/CNC_programs_storage.JPG?raw=true)