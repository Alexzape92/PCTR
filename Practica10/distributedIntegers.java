// Devuelve el numero de numeros primos entre 0 y 10⁷
// COMPILACION : javac -cp $MPJ_HOME/lib/mpj.jar distributedIntegers.java
// EJECUCION : mpjrun.sh -np 1000 distributedIntegers
import mpi.*;

public class distributedIntegers {

    /**
     * Comprueba si un número es primo o no
     * @param num   Número a comprobar
     * @return  True si es primo, false si no
     */
    private static boolean esPrimo(int num){
        boolean primo = true;
        if(num == 0 || num == 1) primo = false;
        for(int i = 2; i < num && primo; i++){
            if(num % i == 0)
                primo = false;
        }
        return primo;
    }

    /**
     * Función principal, comprueba los números primos que hay entre 0 y 10⁷
     * @param args  Argumentos por consola. Ver comentario EJECUCION: 
     */
    public static void main(String[] args) {
        MPI.Init(args);
        int me = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int emisor = 0, linf[] = new int[size], lsup[] = new int[size], primos[] = new int[1], mis_primos[] = new int[1];

        if(me == emisor){
            int slaves = size-1,tam = 10000000, window = tam/slaves, r = tam % slaves;
            for(int i = 1; i < size; i++){  //rellenamos como si fuera exacta la división
                linf[i] = (i-1)*window;
                lsup[i] = i*window;
            }
            //Agregamos el resto al último proceso (si el resto es 0 no cambiará)
            lsup[size-1] += r;
        }

        //Enviamos los limites inferiores y superiores
        MPI.COMM_WORLD.Bcast(linf, 0, size, MPI.INT, emisor);
        MPI.COMM_WORLD.Bcast(lsup, 0, size, MPI.INT, emisor);

        //Comportamiento para los receptores
        if(me != emisor){
            for(int i = linf[me]; i < lsup[me]; i++){
                if(esPrimo(i))  primos[0]++;
            }
        }
        MPI.COMM_WORLD.Reduce(primos, 0, mis_primos, 0, 1, MPI.INT, MPI.SUM, emisor);
        if(me == emisor){
            System.out.println("Hay " + mis_primos[0] + " numeros primos en el rango dado");
        }

        MPI.Finalize();
    }
}
