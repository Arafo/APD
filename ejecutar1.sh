#!/bin/bash

#CLASSPATH="./"

if [ ! -d bin ]; then
	mkdir -p bin;
fi

echo "Compilación"
javac -d bin -sourcepath src -cp src/practica1/*.java src/practica1/minCut/*.java src/practica1/random/*.java

echo "Ejecución"
#java -cp bin practica1.Main -p 10 -t <tipo> -r <repeticiones> -a <algoritmo> -n <random> -u <probabilidad> fm <ficheroMatriz> -fp <ficheroProductos> -d <debug>

# 10 repeticiones del algoritmo de Karger con el generador Random y con una matriz 6x6 de booleanos.
java -cp bin practica1.Main -p 6 -t 0 -r 10 -a k -n 0 -u 0 -d 0 -fm grafos/relaciones6b.dat -fp grafos/productos6b.dat
# 10 repeticiones del algoritmo de Karger con el generador Random y con una matriz 10x10 de booleanos.
java -cp bin practica1.Main -p 10 -t 0 -r 10 -a k -n 0 -u 0 -d 0 -fm grafos/relaciones10b.dat -fp grafos/productos10b.dat
# 10 repeticiones del algoritmo de Karger con el generador SecureRandom y con una matriz 50x50 de booleanos.
java -cp bin practica1.Main -p 50 -t 0 -r 10 -a k -n 1 -u 0 -d 0 -fm grafos/relaciones50b.dat -fp grafos/productos50b.dat
# 10 repeticiones del algoritmo de Karger con el generador HighQualityRandom y con una matriz 100x100 de booleanos.
java -cp bin practica1.Main -p 100 -t 0 -r 10 -a k -n 2 -u 0 -d 0 -fm grafos/relaciones100b.dat -fp grafos/productos100b.dat
# 10 repeticiones del algoritmo de Karger con el generador XORShiftRandom y con una matriz 200x200 de booleanos.
java -cp bin practica1.Main -p 200 -t 0 -r 10 -a k -n 3 -u 0 -d 0 -fm grafos/relaciones200b.dat -fp grafos/productos200b.dat
# 10 repeticiones del algoritmo de Karger con el generador XORShiftRandom y con una matriz 10x10 de booleanos.
#java -cp bin practica1.Main -p 500 -t 0 -r 10 -a k -n 3 -u 0 -d 0 -fm grafos/relaciones500b.dat -fp grafos/productos500b.dat

# 10 repeticiones del algoritmo de Karger-Stein con el generador Random y con una matriz 6x6 de booleanos.
java -cp bin practica1.Main -p 6 -t 0 -r 10 -a ks -n 0 -u 0 -d 0 -fm grafos/relaciones6b.dat -fp grafos/productos6b.dat
# 10 repeticiones del algoritmo de Karger-Stein con el generador Random y con una matriz 10x10 de booleanos.
java -cp bin practica1.Main -p 10 -t 0 -r 10 -a ks -n 0 -u 0 -d 0 -fm grafos/relaciones10b.dat -fp grafos/productos10b.dat
# 10 repeticiones del algoritmo de Karger-Stein con el generador SecureRandom y con una matriz 50x50 de booleanos.
java -cp bin practica1.Main -p 50 -t 0 -r 10 -a ks -n 1 -u 0 -d 0 -fm grafos/relaciones50b.dat -fp grafos/productos50b.dat
# 10 repeticiones del algoritmo de Karger-Stein con el generador HighQualityRandom y con una matriz 100x100 de booleanos.
java -cp bin practica1.Main -p 100 -t 0 -r 10 -a ks -n 2 -u 0 -d 0 -fm grafos/relaciones100b.dat -fp grafos/productos100b.dat
# 10 repeticiones del algoritmo de Karger-Stein con el generador XORShiftRandom y con una matriz 200x200 de booleanos.
java -cp bin practica1.Main -p 200 -t 0 -r 10 -a ks -n 3 -u 0 -d 0 -fm grafos/relaciones200b.dat -fp grafos/productos200b.dat
# 10 repeticiones del algoritmo de Karger-Stein con el generador XORShiftRandom y con una matriz 500x500 de booleanos.
#java -cp bin practica1.Main -p 500 -t 0 -r 10 -a ks -n 3 -u 0 -d 0 -fm grafos/relaciones500b.dat -fp grafos/productos500b.dat

# 10 repeticiones del algoritmo de Karger con el generador Random y con una matriz 10x10 de enteros.
java -cp bin practica1.Main -p 10 -t 1 -r 10 -a k -n 0 -u 1 -d 0 -fm grafos/relaciones10e.dat -fp grafos/productos10e.dat
# 10 repeticiones del algoritmo de Karger con el generador SecureRandom y con una matriz 50x50 de enteros.
java -cp bin practica1.Main -p 50 -t 1 -r 10 -a k -n 1 -u 1 -d 0 -fm grafos/relaciones50e.dat -fp grafos/productos50e.dat
# 10 repeticiones del algoritmo de Karger con el generador HighQualityRandom y con una matriz 100x100 de enteros.
java -cp bin practica1.Main -p 100 -t 1 -r 10 -a k -n 2 -u 1 -d 0 -fm grafos/relaciones100e.dat -fp grafos/productos100e.dat
# 10 repeticiones del algoritmo de Karger con el generador XORShiftRandom y con una matriz 200x200 de enteros.
java -cp bin practica1.Main -p 200 -t 1 -r 10 -a k -n 3 -u 1 -d 0 -fm grafos/relaciones200e.dat -fp grafos/productos200e.dat
# 10 repeticiones del algoritmo de Karger con el generador XORShiftRandom y con una matriz 500x500 de enteros.
#java -cp bin practica1.Main -p 500 -t 1 -r 10 -a ks -n 3 -u 1 -d 0 -fm grafos/relaciones500e.dat -fp grafos/productos500e.dat