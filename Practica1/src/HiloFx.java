import java.util.Random;

public class HiloFx implements Runnable {
	private int destino[];
	HiloFx(int dest[]){
		destino = dest;
	}
	
	public void run() {
		int hits = 0;	//Aciertos
		int n = 100;	//El numero de dardos que lanza cada hilo
		Random r = new Random();
		float x = 0, y = 0;	//puntos generados
		for(int i = 0; i < n; i++) {
			x = r.nextFloat(); y= r.nextFloat();
			if(y <= Math.sin(x))
				hits++;
		}
		synchronized(destino) { destino[0]++;}	//Hay que ver que es el parámetro
	}
}
