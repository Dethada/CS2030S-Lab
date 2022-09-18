#!/bin/bash

rm *.class ; javac ArrayTest.java && java ArrayTest
rm *.class ; javac QueueTest.java && java QueueTest
rm *.class ; javac -Xlint:rawtypes -Xlint:unchecked Lab3.java && ./test.sh Lab3
