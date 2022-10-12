
public class NoUsaLamport {
	public static void main(String[] args) {
		/*Vector<Integer> num = new Vector<Integer>(2);	//Numeros
		num.add(0);
		num.add(0);
		
		Vector<Boolean> choose = new Vector<Boolean>(2);
		choose.add(false);
		choose.add(false);*/
		
		TieneCritica Hilo1 = new TieneCritica();
		TieneCritica Hilo2 = new TieneCritica();
		
		Hilo1.start();
		Hilo2.start();
	}
}
