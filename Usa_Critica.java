public class Usa_Critica extends Thread{
	private Critica c;
	
	public Usa_Critica(Critica crit) {
		c = crit;
	}
	
	public void run() {
		c.inc();
		c.show();
	}
	
	public static void main(String[] args) throws InterruptedException{
		Critica cr = new Critica();
		Usa_Critica hilo1 = new Usa_Critica(cr);
		Usa_Critica hilo2 = new Usa_Critica(cr);
		Usa_Critica hilo3 = new Usa_Critica(cr);
		
		hilo1.start();
		hilo2.start();
		hilo3.start();
		hilo1.join();
		hilo2.join();
		hilo3.join();
		
	}
}