public class HiloRunn implements Runnable {
	private int fi, in;
	
	public HiloRunn(int ini, int fin){
		fi = fin;
		in = ini;
	}
	public void run() {
		for(int i = in; i <= fi; i++)
			System.out.println(i);
	}
}