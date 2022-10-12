public class UsahilRunn{
	public static void main(String[] args) {
		Runnable hilo1 = new HiloRunn(0, 20);
		Runnable hilo2 = new HiloRunn(-20, 0);
		
		new Thread(hilo1).start();
		new Thread(hilo2).start();
	}
}