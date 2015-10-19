Project1Unit5-snatara1
-------------------------

Car configuration Application


Instructions:

1. Import the server project
2. Import the client project
3. Run the server project as Java application
4. Run the client project as Java application
5. Switch to the client console.
6. Type upload and upload models
   to the server via the user interface
   provided with the client.
7. Type quit to quit from the client.
8. Start the client, but this time
   select the "Run as on a server"
9. The servlet should start running.
10. From then on, follow the instructions in the
    Graphical user interface.

Closing the servlet Application:
1. Stop tomcat server.
2. Stop the client.





Environment
------------
Eclipse Version: Mars Release (4.5.0)
Java version "1.8.0_20"


Package information
---------------------
java package: project1unit5


Files Submitted
------------------
+ snatara1_Project1Unit5
   + server
   + client

+server
   + bin(folder)
   + src(folder)

+client
	+bin
	+src
	+ Class Diagram file
	+ output file
	+ properties files
	+ text files


Design
----------

The idea is demonstrate configuring
a automobile via servlets.
The servlets enable 
configuration via a nice GUI.

The GUI receives the inputs and
displays the modified 
automobile OptionSets and Options 
with the final price.

The server stores the automobile information
in a in memory linked hash map, so whenever the client
requests this information, the server can 
pass this information to the client.

The server functionality is realised by 
the introduction of the server class
while the client functionality is 
realised by the introduction of the 
Socketclient class.

The server runs on a provided port
accepting client connections 
continuosly.

The client on the other hand connects to 
the server at the given ip address and the
port number and starts the request/response
operations.

As mentioned, the client can read a properties 
file and send the information to the Server
as a properties object.

A new class named "BuildCarModelOptions"
implements a new introduced interface
called AutoServer.
The Autoserver provides generic interfaces
to read from properties object
or read from normal file information.


The Server uses the "BuildCarModelOptions"
object to read the automobile information
received from the client either by properties
or by text file.

Coming to the servlets,
two new Servlets are introduced.
1. SelectModel which enables the user to select a car model
2. UserSelectedOptions which enables the user to input the new values
   for the options of the Optionsets.

The results are displayed via a jsp web page.

The objective of this exercise is same as the last 
assignment, with the only difference being that
the configuration and display is now enabled
as web pages.



Class diagram
----------------

Two new classes added:
1. SelectModel: spawns a servlet and 
   enables the user to select a car model via  a web page.
2. UserSelectedOptions spawns a servlet and
   enables the user to inputs new values
   and view the final configuration with
   the total price.











