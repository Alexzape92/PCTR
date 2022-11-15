import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class prodMatricesParalelo implements Runnable {
    private static int n = 400;
    private static int mat[][] = new int[n][n], vec[][] = new int[n][n], res[][] = new int[n][n];
    private int ini, fin;

    /**
     * Constructor del hilo
     * @param i Primera fila que debe modificar este hilo
     * @param f Última fila a modificar (f no se modifica, se modifica hasta f-1)
     */
    public prodMatricesParalelo(int i, int f){
        ini = i; fin = f;
    }

    /**
     * Define el comportamiento del hilo. Calcula y modifica las filas de la matriz resultado indicadas en el constructor
     */
    public void run(){
        for(int i = ini; i < fin; i++){
            for(int j = 0; j < n; j++){
                res[i][j] = 0;
                for(int f = 0; f < n; f++)
                    res[i][j] += mat[i][f] * vec[f][j];
            }
        }
    }

    
    /** 
     * Función principal. Rellena dos matrices con números enteros y las multiplica usando hilos y división automática de la nube de datos
     * @param args  Argumentos por consola. No debemos pasarle ninguno
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        Random rd = new Random();
        int nTareas = Runtime.getRuntime().availableProcessors();   //Suponemos que Cb = 0, pues es computación numérica
        int tVentana = n/nTareas, linf = 0, lsup = tVentana;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++){
                mat[i][j] = rd.nextInt();
                vec[i][j] = rd.nextInt();
            }
        
        long iniCrom = System.currentTimeMillis();
        for(int t = 0; t < 100; t++){
            linf = lsup = 0;
            ExecutorService ept = Executors.newFixedThreadPool(nTareas);
            for(int i = 0; i < nTareas; i++){
                ept.execute(new prodMatricesParalelo(linf, lsup));
                linf = lsup;
                lsup += tVentana;
            }
            ept.shutdown();
            while(!ept.isTerminated());
        }
        long finCrom = System.currentTimeMillis();
        System.out.println("El tiempo medio para " + nTareas + " hebras ha sido " + (float)(finCrom - iniCrom) / 100);
    }
}
