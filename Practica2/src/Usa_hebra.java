
public class Usa_hebra {
	
	/** 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		hebra hebra1 = new hebra(0);
		hebra hebra2 = new hebra(1);
		
		hebra1.start();
		hebra2.start();
		hebra1.join();
		hebra2.join();
		hebra.show();
	}
}
