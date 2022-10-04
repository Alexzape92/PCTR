public class Usa_ParImpar{
	public static void main(String[] args) throws InterruptedException{
		ParImpar hiloP = new ParImpar(10, true);
		ParImpar hiloI = new ParImpar(10, false);
		ParImpar hiloP2 = new ParImpar(10, true);
		
		hiloP.start();
		hiloI.start();
		hiloP2.start();
	}
}