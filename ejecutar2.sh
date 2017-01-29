#!/bin/bash

if [ ! -d bin ]; then
	mkdir -p bin;
fi

debug=false
clase=Repeticiones
#clase=SuffixTable.Test

echo "Compilacion"
javac -d bin -sourcepath src -cp src/practica2/*.java src/practica2/arbolsufijos/*.java src/practica2/SuffixTable/*.java

echo "Ejecucion"
#java -cp bin practica2.Repeticiones -f <fichero> -c <cadena> -d <debug>

# Cadenas
java -cp bin practica2.$clase -c banana -d true
java -cp bin practica2.$clase -c mississippi -d true
java -cp bin practica2.$clase -c ABABABA -d true
java -cp bin practica2.$clase -c ATCGATCGA -d true
java -cp bin practica2.$clase -c abcpqrabpqpq -d true

# genes1
java -cp bin practica2.$clase -f genes/genes1/Ala.fasta -d $debug
java -cp bin practica2.$clase -f genes/genes1/Arg.fasta -d $debug
java -cp bin practica2.$clase -f genes/genes1/Asp.fasta -d $debug
java -cp bin practica2.$clase -f genes/genes1/Asn.fasta -d $debug
java -cp bin practica2.$clase -f genes/genes1/Cys.fasta -d $debug

# genes2
java -cp bin practica2.$clase -f genes/genes2/Leu1.fasta -d $debug
java -cp bin practica2.$clase -f genes/genes2/Gln.fasta -d $debug
java -cp bin practica2.$clase -f genes/genes2/Leu2.fasta -d $debug
java -cp bin practica2.$clase -f genes/genes2/Lys.fasta -d $debug
java -cp bin practica2.$clase -f genes/genes2/Glu.fasta -d $debug
java -cp bin practica2.$clase -f genes/genes2/His.fasta -d $debug
java -cp bin practica2.$clase -f genes/genes2/Ile.fasta -d $debug
java -cp bin practica2.$clase -f genes/genes2/Gly.fasta -d $debug
java -cp bin practica2.$clase -f genes/genes2/Met.fasta -d $debug

# genes3
java -cp bin practica2.$clase -f genes/genes3/Phe.fasta -d $debug
java -cp bin practica2.$clase -f genes/genes3/Ser1.fasta -d $debug
java -cp bin practica2.$clase -f genes/genes3/Val.fasta -d $debug
java -cp bin practica2.$clase -f genes/genes3/Pro.fasta -d $debug
java -cp bin practica2.$clase -f genes/genes3/Trp.fasta -d $debug
java -cp bin practica2.$clase -f genes/genes3/Thr.fasta -d $debug
java -cp bin practica2.$clase -f genes/genes3/Tyr.fasta -d $debug
java -cp bin practica2.$clase -f genes/genes3/Ser2.fasta -d $debug
