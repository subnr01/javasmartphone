Assignment1-snatara1
------------------

Coin Toss

Environment
------------
Eclipse Version: Luna Service Release 2 (4.4.2)
Java version "1.8.0_20"


Package information
---------------------
java package: simcointoss


Files Submitted
------------------
+ CoinToss
    + bin(folder)
    + src(folder)

+ Class Diagram: ClassDiagram.pdf
    
+ Test Output:   test_output.txt
   
+ Readme


Design
----------

The objective of the assignment is to simulate a coin toss and
obtain the results of tossing the coin for a good number of times.

The solution models the functionality in terms of a java
class called Coin which comprises of methods to perform
the tossing operation of a coin and return the "heads|tails"
result with a random probability.

The toss method of the coin forms the core of the coin
toss functionality. It uses a random function to return
either a head|tail. So everytime the toss method is invoked,
the result may be different from the previous toss.

When a coin object is instantiated, its face may be head|tail.
So to realize this behavior, the coin constructor invokes
the toss method to obtain a random "heads|tail" face, which
forms a good example of code reusability.

To test the functionality, a simulation class is designed
to toss the coin for sane and insane values.

The simulation class instantiates coin object and
"tosses" (invoke method) the coin object to obtain
the coin face result.

The "tossing" operation is run for different values
and the coin tossing results are obtained.


Class Diagram
---------------

The class diagram shows an association relationship
between the Simulation class and the Coin class
as the Simulation Class uses the Coin class 
for its operation.
