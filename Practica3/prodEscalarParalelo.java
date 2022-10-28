public class prodEscalarParalelo extends Thread {
    private static int N = 10, n = 1000000;   //Numero de hebras
    private int idHebra, ini, fin;
    private static int result[] = new int[N], v1[] = new int[n], v2[] = new int[n]; //Vectores resultado y origen

    public prodEscalarParalelo(int idHebra, int inicio, int fin){
        this.idHebra = idHebra;
        ini = inicio;
        this.fin = fin;
    }

    public void run(){
        result[idHebra] = 0;    //Inicializamos el resultado parcial de la hebra
        for(int i = ini; i < fin; i++){
            result[idHebra] += v1[i] * v2[i];   //Vamos calculando el subresultado
        }
    }

    
    /** 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        long res = 0;             //Resultado
        for(int i = 0; i < n; i++){
            v1[i] = 2; v2[i] = 2;         //Rellenamos los vectores
        }

        long inicTiempo = System.nanoTime();
        for(int t = 0; t < 100; t++){
            res = 0;    //Reseteamos el resultado
            prodEscalarParalelo hebras[] = new prodEscalarParalelo[N];
            for(int i = 0; i < N; i++){
                hebras[i] = new prodEscalarParalelo(i, i * n/N, (i+1) * n/N);
            }
            for(int i = 0; i < N; i++) hebras[i].start();
            for(int i = 0; i < N; i++) hebras[i].join();
            for(int i = 0; i < N; i++) res += result[i];
        }
        long finTiempo = System.nanoTime();

        System.out.println("El resultado es " + res + " y se ha tardado " + (float)(finTiempo - inicTiempo) / 100 / (long)1.0e6 + "ms en calcularlo");
    }
}
