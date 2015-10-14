Project1Unit4-snatara1
-------------------------

Car configuration Application( client-server)


Usage:
For client:
Eclipse -> File -> Import -> Run 

For Server:
Eclipse -> File -> Import -> Run 


Please switch between consoles for client server interaction.


Environment
------------
Eclipse Version: Mars Release (4.5.0)
Java version "1.8.0_20"


Package information
---------------------
java package: project1unit4


Files Submitted
------------------
+ snatara1_Project1Unit4
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

The idea is to demonstrate the working
client server system, where the client
uploads automobile information to
the server either by reading a 
properties file or
a normal text file.

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





Class diagram
----------------

The following interface is
added to the server package:
1. AutoServer

The following classes have been
added to the server:
1. BuildCarModelOptions
2. Server
3. ServerThread

BuildCarModelOptions implements 
the interface AutoServer.
It calls corresponding methods
of BuildAuto which also implements
this interface.

The Server is the entry point to
the Server functionality.
It spawns one thread for every client
that connected.
This thread is represented
by the ServerThread class.

The ServerThread class is the one
which communicates with the client
directly.

It recieves the automobile information 
via the properties or the 
text file and stores the automobile
information into a linkedhashmap
using the BuildCarModelOptions object
methods.

It also returns the client the list
of automobile objects that it has stored 
in the linkedhashmap when it gets the configure
message from the client.


User Interface
-----------------
The user does not interact with the server.

The Client package provides an user interface
where it gives three options to the user.

1. Upload
2. Configure
3. Quit

The upload option helps the user to send
automobile information to the server.
The user can use a properties file or a normal 
text file for this purpose.


The Configure option helps the user to retrieve
the details and validate the prices
for changing some of the options of the 
already fed data to the server.
















