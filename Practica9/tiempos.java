
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;
import java.io.*;

public class tiempos {

	private static int c = 0;
	private static AtomicInteger at = new AtomicInteger(0);

	/**
	 * Toma el tiempo para syncronized
	 * 
	 * @param N: iteraciones a realizar
	 * @return Tiempo que ha tardado
	 */
	private static long cronsynchronized(int N){
		long inicio = System.nanoTime();
		Object lock = new Object();

		for (int i=0; i<N; i++) {
			synchronized(lock) {
				c++;
			}
		}

		return System.nanoTime() - inicio;
	}

	/**
	 * Toma el tiempo para Semaphore
	 * @param N: iteraciones a realizar
	 * @return Tiempo que ha tardado
	 */
	private static long cronsemaphore(int N){
		long inicio = System.nanoTime();
		Semaphore sem = new Semaphore(1);

		for (int i=0; i<N; i++) {				
			try {
				sem.acquire();	
			}catch(Exception e){}
		
			c++;
			
			sem.release();
		}

		return System.nanoTime() - inicio;
	}

	/**
	 * Toma el tiempo para ReentrantLock
	 * @param N: iteraciones a realizar
	 * @return Tiempo que ha tardado
	 */
	private static long cronreentrantlock(int N){
		long inicio = System.nanoTime();

		ReentrantLock lock = new ReentrantLock();

		for (int i=0; i<N; i++) {

			lock.lock();
			c++;
			lock.unlock();
		}

		return System.nanoTime() - inicio;
	}

	/**
	 * Toma el tiempo para variables Atomic
	 * @param N: iteraciones a realizar
	 * @return Tiempo que ha tardado
	 */
	private static long cronatomic(int N){
		long inicio = System.nanoTime();

		for (int i=0; i<N; i++) {
			at.incrementAndGet();
		}

		return System.nanoTime() - inicio;
	}
	
	/**
	 * Funcion principal. Toma los tiempos
	 * @param args: Argumentos pasados por consola.
	 */
	public static void main(String[] args) {
		int[] exps = new int[]{1000, 10000, 20000, 50000, 100000, 200000, 500000};
		int nmedic = 100;

		long preci = 1L;

		// Fichero de salida
		try {
			BufferedWriter fic = new BufferedWriter(new FileWriter("./tiempos.dat"));
			for (int i = 0; i < 7; i++) {
					
				int n = exps[i];
				int[] medias = new int[]{0, 0, 0, 0}; 
				
				// Cronometramos nmedic veces
				for (int j=0; j<nmedic; j++){
					long tiempoSync = cronsynchronized(n) / preci;
					long tiempoSem = cronsemaphore(n) / preci;
					long tiempoRenLock = cronreentrantlock(n) / preci;
					long tiempoAtomic = cronatomic(n) / preci;
					
					medias[0] += tiempoSync;
					medias[1] += tiempoSem;
					medias[2] += tiempoRenLock;
					medias[3] += tiempoAtomic;
				}
				
				for(int j = 0; j < 4; j++){
					medias[j] /= nmedic;
				}
				
				fic.write(n + "\t" + medias[0] + "\t" + medias[1] + "\t" + medias[2] + "\t" + medias[3] + "\n");
			}
			fic.close();
			

		}catch(Exception e){
		}
	}
}
