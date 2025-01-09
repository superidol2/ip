#!/usr/bin/env bash

# Print Java version for debugging
echo "Checking Java version..."
java -version

# check if using Java 21
java_ver=$(java -version 2>&1 | grep -i version | head -n 1)
echo "Detected Java version: $java_ver"

version=$(echo $java_ver | sed -n 's/.*version "\([0-9]*\).*/\1/p')
echo "Parsed major version: $version"

if [ "$version" != "21" ]; then
    echo "********** ERROR: Please use Java 21 (current version: $version) **********"
    exit 1
fi

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin Duke < input.txt > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi