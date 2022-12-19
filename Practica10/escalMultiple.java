// Transfiere un vector a varios clientes, los cuales lo escalan según su id
// COMPILACION : javac -cp $MPJ_HOME/lib/mpj.jar escalMultiple.java
// EJECUCION : mpjrun.sh -np 5 escalMultiple
import mpi.*;

public class escalMultiple {

    /**
     * Función principal. El emisor pasa el vector como Broadcast y los receptores imprimen por pantalla el vector que reciben escalados por su identificador
     * @param args  Argumentos por consola. Ver comentario EJECUCION:
     */
    public static void main(String[] args) {
        MPI.Init(args);
        int me = MPI.COMM_WORLD.Rank();
        int tag = 100, emisor = 0, unitSize = 10, v[] = new int[10];

        if(me == emisor){
            //Rellenamos el vector
            for(int i = 0; i < unitSize; i++)
                v[i] = i;
        }
        
        //El emisor envía / los receptores reciben
        MPI.COMM_WORLD.Bcast(v, 0, unitSize, MPI.INT, emisor);

        if(me != emisor){
            for(int i = 0; i < unitSize; i++){
                v[i] *= me;
            }
            //Hay que hacerlo todo junto porque la salida por pantalla es una sección crítica, y saldría todo mezclado si no
            System.out.println("VECTOR FINAL PROCESO " + me + ": " + v[0] + " " + v[1] + " " + v[2] + " " + v[3] + " " + v[4] + " " + v[5] + " " + v[6] + " " + v[7] + " " + v[8] + " " + v[9]);
        }

        MPI.Finalize();
    }
}
