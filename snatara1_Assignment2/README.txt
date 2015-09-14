Assignment2-snatara1
------------------

Object relationship And File IO

Usage:
Eclipse -> File -> Import -> Run


Environment
------------
Eclipse Version: Mars Release (4.5.0)
Java version "1.8.0_20"


Package information
---------------------
java package: lab2.fio


Files Submitted
------------------
+ snatara1_Assignment2
    + bin(folder)
    + src(folder)

+ src
    + lab2 (.java files)

+bin
    + lab2 (.class files)    



+ Class Diagram: Class_diagram.pdf
    
+ Test Output:   test_output.txt
   
+ Readme.txt


Design
----------

The objective of the assignment is to determine the high, low
and average quiz scores of a set of students participating
in five quizes.

The scores of the five quizes are read from a file and
the high, low and average scores are calculated.


The solution is modelled around three major classes:
1. Util
2. Student
3. Statistics


The Util class is responsible for reading the input from
the input file.
The input is assumed to be in this format:

Stud Qu1 Qu2 Qu3 Qu4 Qu5


where Stud is the student ID or SID,
      Qu1 is the score in the first quiz,       
      Qu2 is the score in the second quiz,       
      Qu3 is the score in the third quiz,       
      Qu4 is the score in the fourth quiz,       
      Qu5 is the score in the fifth quiz.      


The Util class reads the input file and stores all
the student information into the array of Student 
class objects.


The student class contains the SID and an array
of values that correspond to the scores in each of the
quiz as read from the input file.

So naturally a student array would contain a
list of students with their corresponding scores 
in each of the five quizzes.


This student array is used by the statistics class
to determine the highest, lowest and the average 
quiz score among all the students.


The student class and the Statistics class
implement the printInfo interface.
The student class implements the printScore
method thereby reporting the scores
of each of the quizes to the user, 
while the statistics class implements the
PrintScoreStatistics method thereby reporting
the high, low and the average scores.


As given in the requirements, the implemenation
of the design should obey the max limit of
students to be 40 and the max limit of quiz
to be 5.

The design also reports exceptions in cases
where:
1. Student records are more than 40.
2. No student records.
3. Empty file
4. Number of quiz scores is less than 5.
5. Number of quiz scores is greater than 5.



Class Diagram
---------------
As shown in the class diagram (refer Class_diagram.pdf),

Student class inherits from the GuysAndGals abstract class.
Statistics class inherits from the AbstractStatistics class.

Student class and the Statistics class both implement
the printInfo interface.


The Util class has an association relationship
with the Custom Exception classes which it
uses to throw when it finds that the boundary
conditions have been crossed.


The ConstantValues class is a private non inheritable
class holding the boundary conditions as static values,
giving sufficient freedom to change the boundary 
conditions depending on the requirements.

The MainDriver class provides programing interface
to the User to test the entire functionality.



Testing the functionality
---------------------------

The MainDriver Class runs tests to
test the boundary conditions 
of the functionality:
The test cases that are checked 
are listed below:
1. Student records are more than 40.
2. No student records.
3. Empty file
4. Number of quiz scores is less than 5.
5. Number of quiz scores is greater than 5.



