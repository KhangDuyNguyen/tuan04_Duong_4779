#!/bin/bash
cd "$(dirname "$0")" || exit 1
mkdir -p bin
javac -encoding UTF-8 -d bin $(find src -name "*.java")
java -cp bin Main
