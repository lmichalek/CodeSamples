#Grading Script

##Overview

Grading programming assignments can be largely automated. This script aims to do that: it facilitates the movement of each student's homework solution files to the test folder and evaluates his or her code by running a thorough series of test cases.

##Assignment

This homework assignment aimed to teach students linked lists so that they could become learn to work with them and, in particular, to become familiar with common edge cases. The assignment is a series of 10 problems to solve, where each problem is represented by a method. Each problem involves manipulation of a linked list (of strings). The 10 problems are summarized here:

1. ```String concatenate (Node head)``` - Creates a string containing all the strings in the list

2. ```void insertAfter (Node head, String findData, String newData)``` - Inserts a new node containing ```newData``` after the first occurence of ```findData```

3. ```Node buildList (ArrayList<String> list)``` - Creates a new linked list of the strings in list, returns head of the new list

4. ```boolean sameList (Node head1, Node head2)``` - Returns true if and only if ```head1``` and ```head2``` are the heads of two identical lists of strings

5. ```Node bringToFront (Node head, int position)``` - Moves the node at the given position to the head of the list, returns the new list

6. ```void makeCircular (Node head)``` - Creates a circular list by pointing the last node back to the first

7. ```Node intersection (Node head1, Node head2)``` - Creates a new list with the strings present in both lists

8. ```Node removeAll (Node head, int length)``` - Removes all nodes that contain strings of the given length

9. ```Node insertInOrder (Node head, String newData)``` - Inserts ```newData``` into the list in alphabetical order, assumes the given list is already alphabetized

10. ```Node reverse (Node head)``` - Reverses the existing list

##The Grader

The automated grader has two categories of tasks: the first is to automate the locating and moving of each student's solution, and the second is to actually carry out the task of testing the student's code. The first of these tasks is performed by the test.sh script, which moves files to the testing folder based on the student id entered on the command line. The testing is performed by HW3Tester.java, which contains a number of test cases for each problem.

An additional feature of this grader involves the necessity to find out what mistakes the student is making, if errors are found by the tester. Since manually navigating to and searching through student's solution files is time consuming, test.sh optionally takes in a second argument (in addition to the student id) - this second argument is a number 1-10. The script then searches through the student's solution file and prints out the method associated with the problem number indicated by the argument. For example:

```$sh test.sh student1 3```

will print out problem 3 - the buildList method - as submitted by student1.

As a grader, the process looks something like this: you choose the next student you want to grade, and you run their student id through the tester:

![Tester](https://raw.github.com/lmichale/CodeSamples/master/automated-grader/screenshots/screenshot1.png)

You then notice that the student has failed some test cases. In this case, the student is having some issues with problem 4. You want to find out what's going on with this, so you ask the script to show you the student's code for that problem:

![Method Printer](https://raw.github.com/lmichale/CodeSamples/master/automated-grader/screenshots/screenshot2.png)

And you can quickly figure out what went wrong. You deduct the corresponding number of points, rinse and repeat.