# Set the output to a png file
set terminal jpeg size 500,500
# The file we will write to
set output "fn.jpeg"

# The graphic title
set title "Método vs f(método)"
set datafile separator "\t"

#set nokey

set xrange [0:600000]
set yrange [0:12500000]

set xlabel "Iteraciones"

set ylabel "Microsegundos"

plot "tiempos.dat" using 1:2 title "Synchronized" with lines, "tiempos.dat" using 1:3 title "Semaforos" with lines, "tiempos.dat" using 1:4 title "Cerrojos" with lines, "tiempos.dat" using 1:5 title "Atomics" with lines
