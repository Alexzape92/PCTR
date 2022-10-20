
public class hebra extends Thread {
	private static int n = 0;
	private int tipo;
	
	public hebra(int type) {
		tipo = type;
	}
	
	public static void show() {
		System.out.println(n);
	}
	
	public void run() {
		for(int i = 0; i < 2000; i++) {
			if(tipo == 0)
				n++;
			else
				n--;
		}
	}
}
