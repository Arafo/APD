#!/bin/bash

if [ ! -d bin ]; then
	mkdir -p bin;
fi

debug=false

echo "Compilacion"
javac -d bin -sourcepath src -cp src/practica2/*.java src/practica2/arbolsufijos/*.java

echo "Ejecucion"
#java -cp bin practica2.Repeticiones -f <fichero> -c <cadena> -d <debug>

# genes1
java -cp bin practica2.Repeticiones -f genes/12S.fasta -d $debug

java -cp bin practica2.Repeticiones -f genes/genes1/12S.fasta -d $debug
java -cp bin practica2.Repeticiones -f genes/genes1/16S.fasta -d $debug
java -cp bin practica2.Repeticiones -f genes/genes1/Ala.fasta -d $debug
java -cp bin practica2.Repeticiones -f genes/genes1/Arg.fasta -d $debug
java -cp bin practica2.Repeticiones -f genes/genes1/Asn.fasta -d $debug
java -cp bin practica2.Repeticiones -f genes/genes1/ATP6.fasta -d $debug
java -cp bin practica2.Repeticiones -f genes/genes1/ATP8.fasta -d $debug
java -cp bin practica2.Repeticiones -f genes/genes1/CO1.fasta -d $debug
java -cp bin practica2.Repeticiones -f genes/genes1/CO2.fasta -d $debug
java -cp bin practica2.Repeticiones -f genes/genes1/CO3.fasta -d $debug
java -cp bin practica2.Repeticiones -f genes/genes1/CYB.fasta -d $debug
java -cp bin practica2.Repeticiones -f genes/genes1/Cys.fasta -d $debug
