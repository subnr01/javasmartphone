Project1Unit3-snatara1
-------------------------

Car configuration Application


Usage:
Eclipse -> File -> Import -> Run (Driver.java | ModifyOptionSetByThread |ModifyPriceByThread)


Environment
------------
Eclipse Version: Mars Release (4.5.0)
Java version "1.8.0_20"


Package information
---------------------
java package: project1unit3


Files Submitted
------------------
+ snatara1_Project1Unit3
    + bin(folder)
    + src(folder)

+ src
    + project1unit3 (.java files)

+bin
    + project1unit3 (.class files)    


+ Class Diagram: Class_diagram.pdf
    
+ Test Output:   
   + normal_output.txt
   + synchronisedUpdateOptionPrice.txt
   + NotsynchronisedUpdateOptionPrice.txt
   + synchronisedUpdateOptionSetName.txt
   + NotsynchronisedUpdateOptionSetName.txt
 
+ Input format file: format.txt

+ Sample Input file: Focus.txt
   
+ Readme.txt

+ exception.log


Design
----------

The idea is to demonstrate the scaling 
of the system when the same model
is modified by multiple users without
changing the existing
code too much using multi-threading and
synchronization constructs.


This is accomplished by introducing a 
new class called EditOption.
What EditOption does is that it spawns a thread
to perform every update.
EditOption also makes sure that the common code
that performs the real update operation
is synchronized, meaning that only
one thread can update the optionSet
details at a time.

The EditOption class is never exposed to 
the Automobile object.

A generic interface called EditAuto exposes
a method called edit which is implemented
by the ProxyAutomobile class.

So all users who want to update the details
of the automobile OptionSet go through
the edit interface only.

Now what the implementation edit method 
by proxyAutomobile
is interesting.
It instantiates the EditOption object which in turn
spawns thread to make the update.
Now if the edit method is called multiple times,
then multiple threads are created whose
access modification is synchronized inside the
EditOption class.


Class diagram
----------------
A new class "EditOption" is introduced
which spawns thread to make update operations
in a synchronized manner.

A new interface "EditAuto" is introduced
which exposes the edit method by which
the "EditOption" threads are spawned.


















