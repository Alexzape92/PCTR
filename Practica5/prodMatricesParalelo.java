import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class prodMatricesParalelo implements Runnable {
    private static int n = 400;
    private static int mat[][] = new int[n][n], vec[][] = new int[n][n], res[][] = new int[n][n];
    private int ini, fin;

    public prodMatricesParalelo(int i, int f){
        ini = i; fin = f;
    }

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
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        Random rd = new Random();
        int nTareas = 16;   //Suponemos que Cb = 0, pues es computación numérica
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
