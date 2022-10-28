import java.util.Random;

public class matVector {
    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        int n = 5000;
        int mat[][] = new int[n][n]; 
        int v[] = new int[n], s[] = new int[n];

        Random rand = new Random();     
        for(int i = 0; i < n; i++)  //Rellenamos la matriz con numeros aleatorios
            for(int j = 0; j < n; j++)
                mat[i][j] = rand.nextInt();
        
        for(int i = 0; i < n; i++)  //Rellenamos el vector con numeros aleatorios
            v[i] = rand.nextInt();
        
        long iniCrom = System.currentTimeMillis();
        for(int t = 0; t < 100; t++){
            for(int i = 0; i < n; i++){ //Calculamos el producto
                s[i] = 0;
                for(int j = 0; j < n; j++){
                    s[i] += mat[i][j] * v[j];
                }
            }
        }
        long finCrom = System.currentTimeMillis();

        System.out.println("El tiempo es " + (float)(finCrom - iniCrom)/100);
    }
}
