#!/bin/bash

#CLASSPATH="./"
echo "Compilación"
javac -d bin -sourcepath src -cp src/practica1/*.java src/practica1/minCut/*.java src/practica1/random/*.java

echo "Ejecución"
#java -cp bin practica1.Main -p 10 -t <tipo> -r <repeticiones> -a <algoritmo> -n <random> -u <probabilidad> fm <ficheroMatriz> -fp <ficheroProductos> -d <debug>

# 10 repeticiones del algoritmo de Karger con el generador Random y con una matriz 10x10 de booleanos.
java -cp bin practica1.Main -p 10 -t 0 -r 10 -a k -n 0 -u 0 -d 0 -fm src/practica1/relaciones.dat -fp src/practica1/productos.dat
# 10 repeticiones del algoritmo de Karger con el generador SecureRandom y con una matriz 10x10 de booleanos.
java -cp bin practica1.Main -p 10 -t 0 -r 10 -a k -n 1 -u 0 -d 0 -fm src/practica1/relaciones.dat -fp src/practica1/productos.dat
# 10 repeticiones del algoritmo de Karger con el generador HighQualityRandom y con una matriz 10x10 de booleanos.
java -cp bin practica1.Main -p 10 -t 0 -r 10 -a k -n 2 -u 0 -d 0 -fm src/practica1/relaciones.dat -fp src/practica1/productos.dat
# 10 repeticiones del algoritmo de Karger con el generador XORShiftRandom y con una matriz 10x10 de booleanos.
java -cp bin practica1.Main -p 10 -t 0 -r 10 -a k -n 3 -u 0 -d 0 -fm src/practica1/relaciones.dat -fp src/practica1/productos.dat

# 10 repeticiones del algoritmo de Karger-Stein con el generador Random y con una matriz 10x10 de booleanos.
java -cp bin practica1.Main -p 10 -t 0 -r 10 -a ks -n 0 -u 0 -d 0 -fm src/practica1/relaciones.dat -fp src/practica1/productos.dat
# 10 repeticiones del algoritmo de Karger-Stein con el generador SecureRandom y con una matriz 10x10 de booleanos.
java -cp bin practica1.Main -p 10 -t 0 -r 10 -a ks -n 1 -u 0 -d 0 -fm src/practica1/relaciones.dat -fp src/practica1/productos.dat
# 10 repeticiones del algoritmo de Karger-Stein con el generador HighQualityRandom y con una matriz 10x10 de booleanos.
java -cp bin practica1.Main -p 10 -t 0 -r 10 -a ks -n 2 -u 0 -d 0 -fm src/practica1/relaciones.dat -fp src/practica1/productos.dat
# 10 repeticiones del algoritmo de Karger-Stein con el generador XORShiftRandom y con una matriz 10x10 de booleanos.
java -cp bin practica1.Main -p 10 -t 0 -r 10 -a ks -n 3 -u 0 -d 0 -fm src/practica1/relaciones.dat -fp src/practica1/productos.dat

# 10 repeticiones del algoritmo de Karger con el generador Random y con una matriz 10x10 de enteros.
java -cp bin practica1.Main -p 10 -t 1 -r 10 -a ks -n 0 -u 1 -d 0 -fm src/practica1/relaciones.dat -fp src/practica1/productos.dat
# 10 repeticiones del algoritmo de Karger con el generador SecureRandom y con una matriz 10x10 de enteros.
java -cp bin practica1.Main -p 10 -t 1 -r 10 -a ks -n 1 -u 1 -d 0 -fm src/practica1/relaciones.dat -fp src/practica1/productos.dat
# 10 repeticiones del algoritmo de Karger con el generador HighQualityRandom y con una matriz 10x10 de enteros.
java -cp bin practica1.Main -p 10 -t 1 -r 10 -a ks -n 2 -u 1 -d 0 -fm src/practica1/relaciones.dat -fp src/practica1/productos.dat
# 10 repeticiones del algoritmo de Karger con el generador XORShiftRandom y con una matriz 10x10 de enteros.
java -cp bin practica1.Main -p 10 -t 1 -r 10 -a ks -n 3 -u 1 -d 0 -fm src/practica1/relaciones.dat -fp src/practica1/productos.dat