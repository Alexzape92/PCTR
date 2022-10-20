public class escalaVpar {
    
    /** 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        int N = 1000000;
        int v[] = new int[N];
        for(int i = 0; i < N; i++)
            v[i] = i + 1;   //Inicializamos el vector
        
        //En mi caso tengo 8 cores -> 8 hilos. Como es 10⁶, da exacta la división de datos

        Runnable run1 = new hiloesc(v, 0, N/8 - 1, 2);
        Runnable run2 = new hiloesc(v, N/8, N/4 - 1, 2);
        Runnable run3 = new hiloesc(v, N/4, 3 * N/8 - 1, 2);
        Runnable run4 = new hiloesc(v, 3 * N/8, N/2 - 1, 2);
        Runnable run5 = new hiloesc(v, N/2, 5 * N/8 - 1, 2);
        Runnable run6 = new hiloesc(v, 5 * N/8, 3 * N/4 - 1, 2);
        Runnable run7 = new hiloesc(v, 3 * N/4, 7 * N/8 - 1, 2);
        Runnable run8 = new hiloesc(v, 7 * N/8, N - 1, 2);

        Thread hilo1 = new Thread(run1);
        Thread hilo2 = new Thread(run2);
        Thread hilo3 = new Thread(run3);
        Thread hilo4 = new Thread(run4);
        Thread hilo5 = new Thread(run5);
        Thread hilo6 = new Thread(run6);
        Thread hilo7 = new Thread(run7);
        Thread hilo8 = new Thread(run8);

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        hilo6.start();
        hilo7.start();
        hilo8.start();

        hilo1.join();
        hilo2.join();
        hilo3.join();
        hilo4.join();
        hilo5.join();
        hilo6.join();
        hilo7.join();
        hilo8.join();

        for(int i = 0; i < N; i++){
            System.out.println(v[i]);
        }
    }
}
