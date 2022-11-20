import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class integCallable implements Callable<Integer> {
    private int n;

    /**
     * Constructor
     * @param numPuntos Número de puntos que debe lanzar y comprobar este hilo
     */
    integCallable(int numPuntos){
        n = numPuntos;
    }

    
    /** 
     * Comportamiento del hilo. Genera los puntos y comprueba si caen dentro de la curva de cos(x) o no
     * @return Integer, número de puntos que han caído en la curva
     */
    public Integer call(){
        Random rd = new Random();
        int result = 0;

        for(int i = 0; i < n; i++){
            double x = rd.nextDouble(), y = rd.nextDouble();
            if(y <= Math.cos(x))
                result++;
        }

        return result;
    }

    
    /** 
     * Función principal. Ejecuta los hilos en función del número de cores (Cb = 0), recoge los resultados y los imprime por pantalla
     * @param args  Argumentos por consola. No son necesarios en este caso
     */
    public static void main(String[] args) {
        List<Future<Integer>> contParciales = Collections.synchronizedList(new ArrayList<Future<Integer>>());
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for(int i = 0; i < Runtime.getRuntime().availableProcessors(); i++)
            contParciales.add(pool.submit(new integCallable(1000)));
        
        int result = 0;
        for(Future<Integer> iterador:contParciales)
            try{
                result += iterador.get();
            }catch(Exception e){}
        pool.shutdown();
        
        System.out.println("La integral definida es " + result/(1000f * Runtime.getRuntime().availableProcessors()));
    }
}
