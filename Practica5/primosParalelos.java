
import java.util.concurrent.*;
import java.util.*;

public class primosParalelos {

  public static void main(String[] args) throws Exception {
    long nPuntos     = Integer.parseInt(args[0]);
    int  nTareas     = Runtime.getRuntime().availableProcessors();
    nTareas = 16;
    long tVentana    = nPuntos/nTareas;
    long primosTotal = 0;
    long linf        = 0;
    long lsup        = tVentana;
    float tiempoTotal = 0;
    
    List<Future<Long>> contParciales = Collections.synchronizedList(
      new ArrayList<Future<Long>>());  
    for(int t = 0; t < 100; t++){
    	    linf = 0; lsup = tVentana;
    	    primosTotal = 0;
	    
	    long inicTiempo = System.nanoTime();
	    ThreadPoolExecutor ept = new ThreadPoolExecutor(
	      nTareas,
	      nTareas,
	      0L,
	      TimeUnit.MILLISECONDS,
	      new LinkedBlockingQueue<Runnable>());
	    for(int i=0; i<nTareas; i++){
	      contParciales.add(ept.submit(
	      	 new tareaPrimos(linf, lsup)));
	      linf=lsup+1;
	      lsup+=tVentana;
	    }  
	    for(Future<Long> iterador:contParciales)
	      try{
	      	  primosTotal +=  iterador.get(); 
	      }catch (CancellationException e){}
	       catch (ExecutionException e){}
	       catch (InterruptedException e){}      
	    ept.shutdown();
	    tiempoTotal += (System.nanoTime()-inicTiempo)/(float)1.0e9; 
	    contParciales.clear();
    } 
    
    System.out.println("Primos hallados: "+primosTotal);
    System.out.println("Calculo finalizado en "+tiempoTotal/100f+" segundos");
 }   
}
