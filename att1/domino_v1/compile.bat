@echo off
dir /s /B *.java > sources2.txt
javac -d bin -cp "libs/*;" -encoding utf8 @sources2.txt
del "sources2.txt"

