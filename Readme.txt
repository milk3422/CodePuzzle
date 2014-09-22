# Author: Zachary Radtka
# email: zachary.radtka@gmail.com
# Date: 22/9/2014

#
# Requirements
Java 1.8

#
# How to Build
From inside the AdtechCodePuzzle directory run:
> mvn clean install

This will produce the following files:
target/WellFormedString-0.0.1-SNAPSHOT.jar
target/ParallelWellFormedString-0.0.1-SNAPSHOT.jar

#
# How to execute
From the command line either program can be executed by running:
> java -jar <executable>

Because both applications are made to read from STDIN they can be used 
interactively or utilize file redirection. If the programs are used 
interactively, CTRL-c must be used to exit the program. If the programs
utilize file redirection they will automatically exit when the end of the file
is reached.

#
# Eclipse
To import in eclipse you will need to run the following command:
> mvn eclipse:clean eclipse:eclipse

#
# Project layout
A few projects are used for the logical division of work and they will be 
explained in this section.

AdtechCodePuzzle
----------------
This is the parent project that will ensure all sub projects are built in the 
correct order and handle all common dependencies and plugins.

Util
----
This project contains all of the classes required to determine if a string
is well formed. The WellFormedString class utilizes a Stack to enqueue 
characters being read in to determine if a character is a valid delimiter. 

To make assigning rules for particular characters easy, neat, and extensible
the abstract Delimiter class was created. Implementing this class allows
a delimiter's opening character, closing character, and allowed nested 
delimiters to be defined.

A factory pattern was also used to allow delimiter objects to be easily 
created.

WellFormedString
----------------
This project is the single threaded implementation. It consists of a simple
driver that reads from STDIN, utilizes the objects contained in the Util 
project, and prints results to STDOUT.

ParallelWellFormedString
------------------------
This project is the multithreaded implementation. It consists of a simple 
driver and an implementation of the Runnable class. It creates a thread pool
equal to the amount of cores on the machine running the application. The
parallel implementation reads from STDIN, utilizes the objects contained in 
the Util project and prints results to STDOUT.

#
# More information
More information regarding design desicions pertaining to classes can be found 
in a particular classes JavaDoc. 
