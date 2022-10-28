
public class prodEscalar{
    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        int N = 1000000;
        int v1[] = new int[N], v2[] = new int[N];
        long result = 0;

        for(int i = 0; i < N; i++){
            v1[i] = 2;
            v2[i] = 2;
        }

        long inicCrom = System.nanoTime();
        for(int t = 0; t < 100; t++){   //Medimos 100 veces para hacer la media
            result = 0; //Reseteamos el contador
            for(int i = 0; i < N; i++){
                result += v1[i] * v2[i];
            }
        }
        long finCrom = System.nanoTime();
        System.out.println("El resultado ha sido " + result);
        System.out.println("El tiempo medio ha sido " + (float)(finCrom - inicCrom) / 100 / (long) 1.0e6 + "milisegundos");
    }
}