import mpi.*;

// transfiere un array de enteros del emisor al recpetor
// COMPILACION : javac -cp $MPJ_HOME/lib/mpj.jar prodInterno.java
// EJECUCION : mpjrun.bat - np 2 prodInterno
public class prodInterno{
    public static void main(String[] args) throws Exception {
        MPI.Init(args);
        int me = MPI.COMM_WORLD.Rank();
        int tag = 100, emisor = 0, unitSize = 4, receptor = 1, v[] = new int[4], w[] = new int[4], res[] = new int[1];

        if(me == emisor){
            //Inicializamos vectores
            v[0] = 1; v[1] = 2; v[2] = 4; v[3] = 8;
            w[0] = 1; w[1] = 1; w[2] = 1; w[3] = 1;

            //Y los enviamos
            MPI.COMM_WORLD.Send(v, 0, unitSize, MPI.INT, receptor, tag);
            MPI.COMM_WORLD.Send(w, 0, unitSize, MPI.INT, receptor, tag);

            MPI.COMM_WORLD.Recv(res, 0, 1, MPI.INT, receptor, tag+100);
            System.out.println("El resultado ha sido " + res[0]);
        }
        else{
            //Recibimos los vectores
            MPI.COMM_WORLD.Recv(v, 0, unitSize, MPI.INT, emisor, tag);
            MPI.COMM_WORLD.Recv(w, 0, unitSize, MPI.INT, emisor, tag);

            res[0] = 0;
            for(int i = 0; i < unitSize; i++){
                res[0] += v[i] * w[i];
            }

            MPI.COMM_WORLD.Send(res, 0, 1, MPI.INT, emisor, tag+100);
        }
    }
}