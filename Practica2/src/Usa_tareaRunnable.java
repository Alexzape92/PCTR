
public class Usa_tareaRunnable {
	
	/** 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		critico c = new critico();
		
		Runnable run1 = new tareaRunnable(c, 0);
		Runnable run2 = new tareaRunnable(c, 1);
		Thread hilo1 = new Thread(run1);
		Thread hilo2 = new Thread(run2);
		
		hilo1.start();
		hilo2.start();
		hilo1.join();
		hilo2.join();
		
		c.show();
	}
}
