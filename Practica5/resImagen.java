import java.util.Random;

public class resImagen {
    public static void main(String[] args) {
        int k = 4000;
        int mat[][] = new int[k][k], res[][] = new int[k][k];
        Random rd = new Random();

        for(int i = 0; i < k; i++){
            for(int j = 0; j < k; j++)
                mat[i][j] = rd.nextInt(255);
        }
        long iniCrom = System.currentTimeMillis();
        for(int t = 0; t < 100;t++){
            for(int i = 0; i < k; i++)
                for(int j = 0; j < k; j++){
                    if(i == 0 || i == k-1 || j == 0 || j == k-1)
                        res[i][j] = mat[i][j];
                    else
                        res[i][j] = (4*mat[i][j] - mat[i+1][j] - mat[i][j+1] - mat[i-1][j] - mat[i][j-1])/8;
                }
        }
        float tiempo = (System.currentTimeMillis() - iniCrom)/100f;

        System.out.println("El tiempo medio ha sido " + tiempo + "ms");
    }
}
