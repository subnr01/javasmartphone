Assignment1-snatara1
---------------------

Parking Ticket


Environment
------------
Eclipse Version: Luna Service Release 2 (4.4.2)
Java version "1.8.0_20"

Package information
---------------------
java package: simparkingticket


Files Submitted
------------------
+ ParkingTicket
    + bin(folder)
    + src(folder)

+ Class Diagram: class-diagram.pdf
    
+ Test Output:   test_output.txt
   
+ Readme


Design
----------


The objective of this assignment is to simulate the
event of a parking ticket voilation in a real world
and return the details of the parked car, the 
resulting fine/penalty incurred with the offence
and the details of the police officer
who issued the ticket/fine/penalty.

A parking ticket is issued when the parking time
surpasses the purchased Parking time and when a police
officer discovers this offence, he/she calculates the
penalty/fine incurred.

The above stated event is modelled in 
terms of four java classes:

1. ParkedCar
2. ParkedMeter
3. ParkingTicket
4. PoliceOfficer

The ParkedCar class holds all the car information of the parked car.

The ParkedMeter class holds the parking minutes purchased by the parking
customer.

The ParkingTicket class holds the information to calculate
the fine associated with the committed offence.

The PoliceOfficer class holds the identification information
of the police officer who issues the parking ticket.

When a ParkedCar is instantiated, it has the information
on the minutes that the car has been parked and
when the parking meter holds the minutes purchased
for parking this car.

The PoliceOfficer object is responsible for determining
whether an offence is committed or not.
It has a method "CheckParkingtimeExpired" which 
determines whether the parking time surpassed the 
purchased minutes.
The fine is accordingly caculated by the police officer by
instantiating the ParkingTicket object.

To test the functionality, a simulation class is designed
to simulate the event of committing a parking voilation.

The simation class uses a test case object.
The testClass object belongs to the testCase class
which contains the PoliceOfficer, ParkingMeter
and the ParkedCar as instance variables.
It also contains the purchased parking 
minutes and parked minutes.

The simulation class instantiates the testCase object
for different scenarios and obtains the parking
ticket in these scenarios.


Class diagram
--------------
As shown in the class diagram, 
the testCase class has a compostion 
relationship with the PoliceOfficer, ParkingMeter
and the ParkedCar classes.

The ParkingTicket class has a composition
reliationship with the PoliceOfficer, ParkingMeter
and the ParkedCar class.

The PoliceOfficer class has an association 
relationship with the ParkingTicket
class as it uses the ParkingTicket
for its operation.