Project1unit6-snatara1
-------------------------

Car configuration Application


Instructions:

1. Import the server project
2. Import the client project
3. Run the server project as Java application
4. Run the client project as Java application
5. Switch to the server console.
6. Run the driver program.
7. It should populate the database with 
   the automobile information from the 
   text and the properties files.


Closing the servlet Application:
1. Stop tomcat server.
2. Stop the client.





Environment
------------
Eclipse Version: Mars Release (4.5.0)
Java version "1.8.0_20"


Package information
---------------------
java package: project1unit6


Files Submitted
------------------
+ snatara1_Project1unit6
   + server
   + client

+server
   + bin(folder)
   + src(folder)
   + output
   + class diagram file
   + properties file
   + text files
   + database-execute files

+client
	+bin
	+src
	+ Class Diagram file
	+ output file
	+ properties files
	+ text files


Design
----------

The idea is demonstrate storing
automobile object information
into the mysql database.

The database is populated with automobile
information run by sql statements from
the java server code.


In addition to the in memory 
linked hash map, the server stores
the automobile information in the
database as well.

Most of the functionality in this
unit is implemented on the server side.
A new interface “DatabaseAuto” is introduced
to insert,update,delete automobile
information into the mysql database.

The BuildAuto implements this interface 
which and routes data into the database by
invoking the methods of a newly introduced
class “DatabaseIO”. The methods of “DatabaseIO”
provide the interface to the database by connecting
to the database and performing 
create/delete/modify operations with the
database.




Class diagram
----------------

1. New class “DatabaseIO” is introduced, which acts as
   an interface to the database for CRUD operations.
2. New interface “DatabaseAuto” is introduced which
   provides interfaces for the Automobile object
   to interact with database.










