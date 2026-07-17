#!/bin/bash
set -e
JAVA_HOME="$(/usr/libexec/java_home 2>/dev/null || dirname "$(dirname "$(dirname "$(readlink -f "$(which javac 2>/dev/null || which java)")")")")"
LIBS="lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar"
mkdir -p out
javac -cp "$LIBS" -d out src/TicTacToe.java src/AIPlayer.java test/TicTacToeTest.java
java -cp "out:$LIBS" org.junit.runner.JUnitCore TicTacToeTest
