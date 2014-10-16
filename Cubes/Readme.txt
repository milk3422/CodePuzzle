# Author: Zachary Radtka
# email: zachary.radtka@gmail.com
# Date: 16/10/2014

#
# Requirements
Java 1.8

#
# How to Build
From inside the Cubes directory run:
> mvn clean install

This will produce the following files:
target/Cubes-0.0.1-SNAPSHOT.jar

#
# How to execute
From the command line the program can be executed by running:
> java -jar Cubes-0.0.1-SNAPSHOT.jar <cubeFile>

It will display the solutions to STDOUT, to print them to a file use file
redirection.

#
# Eclipse
To import in eclipse you will need to run the following command:
> mvn eclipse:clean eclipse:eclipse


#
# More information
More information regarding design desicions pertaining to classes can be found 
in a particular classes JavaDoc. 
