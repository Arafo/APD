#!/bin/bash

#CLASSPATH="./"
echo "Compilación"
javac -d bin -sourcepath src -cp src/practica1/*.java src/practica1/minCut/*.java src/practica1/random/*.java

echo "Ejecución"
java -cp bin practica1.Main -p 10 -t 0 -r 10 -a k
java -cp bin practica1.Main -p 10 -t 1 -r 10 -a k
java -cp bin practica1.Main -p 10 -t 0 -r 10 -a ks
java -cp bin practica1.Main -p 10 -t 1 -r 10 -a ks

java -cp bin practica1.Main -p 100 -t 0 -r 10 -a k
java -cp bin practica1.Main -p 100 -t 1 -r 10 -a k
java -cp bin practica1.Main -p 100 -t 0 -r 10 -a ks
java -cp bin practica1.Main -p 100 -t 1 -r 10 -a ks