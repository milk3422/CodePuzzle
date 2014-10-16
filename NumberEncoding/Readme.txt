# Author: Zachary Radtka
# email: zachary.radtka@gmail.com
# Date: 16/10/2014

#
# Requirements
Java 1.8

#
# How to Build
From inside the NumberEncoding directory run:
> mvn clean install

This will produce the following files:
target/NumberEncoding-0.0.1-SNAPSHOT.jar

#
# How to execute
From the command line the program can be executed by running:
> java -jar NumberEncoding-0.0.1-SNAPSHOT.jar <dictionaryFile>

The program accepts input from STDIN, so if you use it interactively, you will
need to use CTRL-C to exit the application. Use file redirection to direct the
contents of a file to the application. 

Solutions are displayed on STDOUT.

#
# Eclipse
To import in eclipse you will need to run the following command:
> mvn eclipse:clean eclipse:eclipse

#
# More information
More information regarding design desicions pertaining to classes can be found 
in a particular classes JavaDoc. 
