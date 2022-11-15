import java.util.Random;

public class prodMatricesSecuencial {
    
    /** 
     * Función principal. Rellena dos matrices aleatoriamente y las multiplica
     * @param args  Argumentos recibidos por consola. No debe enviarse ninguno
     */
    public static void main(String[] args) {
        int n = 400;
        int mat[][] = new int[n][n]; 
        int v[][] = new int[n][n], s[][] = new int[n][n];

        Random rand = new Random();     
        for(int i = 0; i < n; i++)  //Rellenamos las matrices con numeros aleatorios
            for(int j = 0; j < n; j++){
                mat[i][j] = rand.nextInt();
                v[i][j] = rand.nextInt();
            }
        
        long iniCrom = System.currentTimeMillis();
        for(int t = 0; t < 100; t++){
            for(int i = 0; i < n; i++){ //Calculamos el producto
                for(int j = 0; j < n; j++){
                    s[i][j] = 0;
                    for(int f = 0; f < n; f++){
                        s[i][j] += mat[i][f] * v[f][j];
                    }
                }
            }
        }
        long finCrom = System.currentTimeMillis();

        System.out.println("El tiempo medio ha sido " + (float)(finCrom - iniCrom)/100);
    }
}
