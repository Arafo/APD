#!/bin/bash

if [ ! -d bin ]; then
	mkdir -p bin;
fi

debug=true

echo "Compilacion"
javac -d bin -sourcepath src -cp src/practica2/*.java src/practica2/arbolsufijos/*.java

echo "Ejecucion"
#java -cp bin practica2.Repeticiones -f <fichero> -c <cadena> -d <debug>

# Cadenas
java -cp bin practica2.Repeticiones -c banana -d $debug
java -cp bin practica2.Repeticiones -c mississippi -d $debug
java -cp bin practica2.Repeticiones -c ABABABA -d $debug
java -cp bin practica2.Repeticiones -c ATCGATCGA -d $debug
java -cp bin practica2.Repeticiones -c abcpqrabpqpq -d $debug

# genes1
java -cp bin practica2.Repeticiones -f genes/genes1/12S.fasta -d $debug
java -cp bin practica2.Repeticiones -f genes/genes1/Ala.fasta -d $debug
java -cp bin practica2.Repeticiones -f genes/genes1/Arg.fasta -d $debug
java -cp bin practica2.Repeticiones -f genes/genes1/Asn.fasta -d $debug
java -cp bin practica2.Repeticiones -f genes/genes1/ATP8.fasta -d $debug
java -cp bin practica2.Repeticiones -f genes/genes1/Cys.fasta -d $debug
