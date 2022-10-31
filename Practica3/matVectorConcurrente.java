import java.util.Random;

public class matVectorConcurrente implements Runnable {
    private static int n = 4000, nHebras = 16;   //numero de tareas paralelas
    private static int mat[][] = new int[n][n], vec[] = new int[n], res[] = new int[n];
    private int ini, fin;

    public matVectorConcurrente(int i, int f){
        ini = i; fin = f;
    }

    public void run(){
        for(int i = ini; i < fin; i++){
            res[i] = 0;
            for(int j = 0; j < n; j++){
                res[i] += mat[i][j] * vec[j];
            }
        }
    }

    
    /** 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        Random rd = new Random();
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                mat[i][j] = rd.nextInt();

        for(int i = 0; i < n; i++)
            vec[i] = rd.nextInt();
        
        long iniCrom = System.currentTimeMillis();
        for(int t = 0; t < 100; t++){
            Runnable runs[] = new matVectorConcurrente[nHebras];
            for(int i = 0; i < nHebras; i++)
                runs[i] = new matVectorConcurrente(i * n/nHebras, (i+1) * n/nHebras);

            Thread hilos[] = new Thread[nHebras];
            for(int i = 0; i < nHebras; i++){
                hilos[i] = new Thread(runs[i]);
                hilos[i].start();
            }
            for(int i = 0; i < nHebras; i++)
                hilos[i].join();
        }
        long finCrom = System.currentTimeMillis();
        System.out.println("El tiempo medio para " + nHebras + " hebras ha sido " + (float)(finCrom - iniCrom) / 100);
    }
}
