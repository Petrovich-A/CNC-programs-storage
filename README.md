README.md# :bowtie: EPAM EXTERNAL JAVA TRAINING.

## JAVA WEB APPLICATION PROJECT:

* [Project's description](#description)
* [Features](#features)
* [Database structure](#database)

>## CNC PROGRAMS STORAGE :v:

<a name="description"></a>
## Project's description

#### :one: This Java web application allows to store CNC machines programs (CNC means computer numerical control :nut_and_bolt:). CNC programs are different formats simple text files included text as combination of latin symbols and digitals like this: :pushpin:
```
%
N1 ;DNETAL 75581-3537072 PLITA SOEDINIT.
N2 ;OPER 020
N3 ;YSTANOV I OBRABOTKA A1 NA B0,B90 NA PRILADE R10
N7 ;PRISPOSOBLENIE 7273-6005 (POZIC. M192(STOL-N2)
N8 ;STANOK NDH-6 INV. N 12000!
N9 ;PETROVICH A.V. 18-24.08.2016
N10 ;TC=119_MIN (NA 4 YST.)
N11 DEF REAL POM_Y,FUS=3000,ZBEZ,ZPRIPUSK,XBEZ,YBEZ,ZAG,RO,Y_B180,X_B180,Z_B180,Y_B90,X_B90,Z_B90,Y_B0,X_B0,Z_B0,X_BB0,Z_BB0,Y_BB0,X_BB180,Z_BB180,Y_BB180,X_B270,Z_B270,Y_B270
N12 DEFINE SMENA_Z AS G0 G53 Z930 D0 M9 M5 
N13 $AC_TIMER[1]=0
N14 ;*G54: X-CENTR STOLA, Y-PLOSKOST PALETI, Z-CENTR STOLA
N15 $P_UIFR[1]=CTRANS(X,0,Y,0,Z,0,B,0);  G54 
N16 ZBEZ=3
N17 XBEZ=5
N18 YBEZ=7
N19 GOTOF _PROVERKA
N20 START_:
N21 ;YSTANOV 1 DETAL A1 OT SEBIA STORONA B90********
N22 M0;   DETAL NA PRILADY R10!!!!
N23 SMENA_Z
N24 M193
N25 ;***YSTANOV I OBRABOTKA A1 NA B0,B90 NA PRILADE R10******
N26 ;****************YSTANOV I - DETAL A1********************
N27 ;************DETAL A1 OT SEBIA STORONA B0,B90************
N28 ST2: WORK_STEP(2)
N29 T="17.1" M6
N30 MSG("T= FREZA D=80 CHERN, WORK_STEP(2) ")
N31 G0 G54 B0
N32 TRANS X=X_B0 Z=Z_B0 Y=Y_B0             ;TRANS DETAL A1 OT SEBIA STORONA B0
N33 REGIME(140,80,8,0.06)
N34 G0 G54 X=110+80/2+XBEZ Y=80/2-2.5-3 D1 M3
N35 Z=1.5 M8
N36 G1 X=0
N37 G0 Z5
N38 X=110+80/2+XBEZ Y=80/2-2.5-3 D1 M3
N39 Z=0.5 M8
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
- :white_check_mark: searching to several entities :flashlight:
## To do: :clipboard:
- [ ] inner builder
- [ ] implement more unit test coverage
- [ ] implement program text renumbering
- [ ] change password
- [ ] sending email when registration successful
### :vertical_traffic_light: There are three roles:
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