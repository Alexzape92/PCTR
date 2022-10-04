public class ParImpar extends Thread{
	private int veces;
	private boolean tipo;	//True -> PAR, False -> IMPAR
	
	public ParImpar(int n, boolean type) {
		veces = n;
		tipo = type;
	}
	
	public void run() {
		for(int i = 0; i < veces; i++) {
			if(tipo)
				System.out.println(2*i);
			else
				System.out.println(2*i + 1);
		}
	}

}