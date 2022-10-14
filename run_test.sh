#!/bin/bash
if [ $# -eq 0 ];
then
    echo "$0: Missing arguments"
    exit 1
elif [ $# -gt 2 ];
then
    echo "$0: Too many arguments: $@"
    exit 1
else
    find . -type f -name '*.class' -delete
    currentTest="$1"
    javac -Xlint:rawtypes -Xlint:unchecked "$currentTest.java" && java "$currentTest"
fi
