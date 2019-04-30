# Copy the ArrayDequeue.java to the working directory
# Run "make" on the command line
# The result is stored in DequeTestResult.txt
# Type "make clean" tp clean the working directory

all: compile test

compile:
	javac DequeChecker.java

test:
	java DequeChecker

clean:
	-mv DequeChecker.java DequeChecker.jav
	-mv DequeInterface.java DequeInterface.jav
	-rm *.class
	-rm *.java
	-rm *.txt
	-mv DequeChecker.jav DequeChecker.java
	-mv DequeInterface.jav DequeInterface.java
