import java.util.*;
import java.util.concurrent.*;

public class numPerfectosParalelo implements Callable<Integer>{
    private final int ini, fin;

    public numPerfectosParalelo(int linf, int lsup){
        this.ini = linf;
        this.fin = lsup;
    }

    private boolean esPerfecto(int num){
        int cont = 1;
        for(int i = 2; i < num; i++){
            if(num % i == 0)
                cont += i;
        }

        return cont == num;
    }

    public Integer call(){
        int total = 0;
        for(int i = ini; i < fin; i++)
            if(esPerfecto(i))
                total++;
        
        return (new Integer(total));
    }

    public static void main(String[] args) {
        int primero = Integer.parseInt(args[0]), ultimo = Integer.parseInt(args[1]), cont = 0;
        int nTareas = Runtime.getRuntime().availableProcessors() / (1-0);   //Cb = 0
        int tVentana = (ultimo - primero + 1)/nTareas;
        int linf = primero, lsup = primero + tVentana;
        float tiempo = 0;

        List<Future<Integer>> contParciales = Collections.synchronizedList(new ArrayList<Future<Integer>>());
        for(int t = 0; t < 100; t++){
            cont = 0; linf = primero; lsup = primero + tVentana;
            long inicTiempo = System.nanoTime();
            ThreadPoolExecutor ept = new ThreadPoolExecutor(nTareas, nTareas, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
            for(int i = 0; i < nTareas; i++){
                contParciales.add(ept.submit(new numPerfectosParalelo(linf, lsup)));
                linf = lsup;
                lsup += tVentana;
            }
            for(Future<Integer> iterador:contParciales)
                try{
                    cont += iterador.get();
                }catch(Exception e){}   //No hacemos nada para las excepciones

            tiempo += (System.nanoTime() - inicTiempo)/1.0e9f;
            ept.shutdown();
            contParciales.clear();  //Vaciamos para la siguiente iteración
        }
        
        System.out.println("Hay " + cont + " números perfectos entre " + primero + " y " + ultimo + ". Calculado con " + nTareas + " tareas y en " + tiempo/100 + "s.");
    }
}
