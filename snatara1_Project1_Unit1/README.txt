Project1Unit1-snatara1
------------------

Car configuration Application


Usage:
Eclipse -> File -> Import -> Run


Environment
------------
Eclipse Version: Mars Release (4.5.0)
Java version "1.8.0_20"


Package information
---------------------
java package: project1unit1


Files Submitted
------------------
+ snatara1_Assignment2
    + bin(folder)
    + src(folder)

+ src
    + project1unit1 (.java files)

+bin
    + project1unit1 (.class files)    


+ Class Diagram: Class_diagram.pdf
    
+ Test Output:   output.txt

+ Input format file: format.txt

+ Sample Input file: filename1.txt
   
+ Readme.txt


Design
----------

The objective of the assignment is to demonstrate serialization
and de-serialization of java objects.

The Automotive class contains some base attributes
like name, price and a collection of
optionSets like Color, Braking, 
Transmission etc.

Each of the option Sets is fulfilled by 
a number of options.

The Option Sets are represented by the
OptionSet Class
and the options are
represented by the Option
Class
The Option is implemented as an inner 
class inside the OptionSet 
class.

Both the Automotive and the
OptionSet class implement
the serialize interface, 
and hence provide for serialization
and serialization of objects.


Hence the user can load
the Automotive details
from a file and change the Automotive
details and write to a file
using serialization successfully.




Class Diagram
--------------

This lends itself to a
simple class diagram
which shows a composition relationship between
Automotive and the OptionSet class.

The variables of OptionSet class are declared
private and methods are declared protected
so as to ensure no outside visibility for the
OptionSet class thus emphasising 
data encapsulation.


The FileIO and the Driver class instantiate 
objects of the Automotive class to demonstrate
the serialization and de-serialization
operations.

