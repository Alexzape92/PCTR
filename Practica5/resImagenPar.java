import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class resImagenPar implements Runnable{
    private int ini, fin;   //filas de la matriz resultado de las que se encarga el hilo
    private static int k = 4000, mat[][] = new int[k][k], res[][] = new int[k][k];  //matrices y k

    /**
     * Constructor del hilo
     * @param i Primera fila a modificar
     * @param f Última fila a modificar (no se modifica f, se modifica hasta f-1)
     */
    public resImagenPar(int i, int f){
        ini = i; fin = f;
    }

    /**
     * Define el comportamiento de cada hilo. Aplica el operador de resolución para las filas dadas por el constructor.
     */
    public void run() {
        for(int i = ini; i < fin; i++)
            for(int j = 0; j < k; j++){
                if(i == 0 || i == k-1 || j == 0 || j == k-1)
                    res[i][j] = mat[i][j];
                else
                    res[i][j] = (4*mat[i][j] - mat[i+1][j] - mat[i][j+1] - mat[i-1][j] - mat[i][j-1])/8;
            }
    }

    
    /** 
     * Función principal. Aplica el operador de resolución a una imagen.
     * @param args  Argumentos dados por consola. No son necesarios en este caso.
     */
    public static void main(String[] args) {
        int nTareas = 32;
        int tVentana = k/nTareas, linf = 0, lsup = tVentana;
        
        long iniCrom = System.currentTimeMillis();
        for(int t = 0; t < 100; t++){
            linf = 0; lsup = tVentana;
            ExecutorService ept = Executors.newFixedThreadPool(nTareas);   //Creamos un pool de threads de tamaño fijo
            for(int i = 0; i < nTareas; i++){
                ept.execute(new resImagenPar(linf, lsup));
                linf = lsup;
                lsup += tVentana;
            }
            ept.shutdown();
            while(!ept.isTerminated());
        }
        float tiempo = (float)(System.currentTimeMillis() - iniCrom)/100f;

        System.out.println("El tiempo medio para " + nTareas + " hebras ha sido " + tiempo + "ms");
    }
}
