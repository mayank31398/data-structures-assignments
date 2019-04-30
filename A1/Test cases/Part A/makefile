# There are a total of 7 test cases.
# To run the test cases, copy the FabricBreakup.java file of the student to the working directory
# type "make" on the command line. A total of 7 output files are generated along with a result.txt
# type "make clean" to clean the working directory

test: compile test0 test1 test2 test3 test4 test5 test6 compare

compile:
	javac *.java

test0:
	java FabricBreakup test0.txt > output0.dat
test1:
	java FabricBreakup test1.txt > output1.dat
test2:
	java FabricBreakup test2.txt > output2.dat
test3:
	java FabricBreakup test3.txt > output3.dat
test4:
	java FabricBreakup test4.txt > output4.dat
test5:
	java FabricBreakup test5.txt > output5.dat
test6:
	java FabricBreakup test6.txt > output6.dat

compare:
	python compare.py

clean:
	-rm *.class
	-rm *.dat
	-rm *.java