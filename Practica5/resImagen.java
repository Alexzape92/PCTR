import java.util.Random;

public class resImagen {
    
    /** 
     * Función main. Aplica la operación de resaltado sobre una imagen
     * @param args  Argumentos por consola. No se necesita ninguno
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        int k = 48;
        int mat[][] = new int[k][k], res[][] = new int[k][k];
        Random rd = new Random();

        mat = CargaImagen.cargar("uca.png");
        
        long iniCrom = System.currentTimeMillis();
        //for(int t = 0; t < 100;t++){
            for(int i = 0; i < k; i++)
                for(int j = 0; j < k; j++){
                    if(i == 0 || i == k-1 || j == 0 || j == k-1)
                        res[i][j] = mat[i][j];
                    else{
                        int color = (4*mat[i][j] - mat[i+1][j] - mat[i][j+1] - mat[i-1][j] - mat[i][j-1])/8;
                        if(color > 255) res[i][j] = 255;
                        else if(color < 0) res[i][j] = 0;
                        else res[i][j] = color;
                        }
                }
        //}
        float tiempo = (System.currentTimeMillis() - iniCrom)/100f;
        
        CargaImagen.guardar(res, "resultado.png");

        System.out.println("El tiempo medio ha sido " + tiempo + "ms");
    }
}
