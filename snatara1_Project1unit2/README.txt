Project1Unit2-snatara1
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
+ snatara1_Project1Unit2
    + bin(folder)
    + src(folder)

+ src
    + project1unit2 (.java files)

+bin
    + project1unit2 (.class files)    


+ Class Diagram: Class_diagram.pdf
    
+ Test Output:   output.txt

+ Input format file: format.txt

+ Sample Input file: Focus.txt
   
+ Readme.txt


Design
----------

The objective of the assignment is to 
use interfaces and create custom 
exception handlers.

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


Three new interfaces are added to the adapter
package:
CreateAuto.
FixAuto.
UpdateAuto.


An Abstract class ProxyAutomobile
has also been added.

The buildAuto class inherits
from the abstract class and 
implements each of the above 
interfaces.

There is also as 
class CarShowRoom
that stores list
of automobiles.


Hence the user can load
the Automotive details
from a file and change the Automotive
details and write to a file
using serialization successfully.










