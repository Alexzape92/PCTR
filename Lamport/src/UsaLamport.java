
public class UsaLamport {
	public static void main(String[] args) {
		int nums[] = {0, 0};
		
		Boolean choosing[] = {false, false};
		
		TieneCritica Hilo1 = new TieneCritica(nums, choosing, 0);
		TieneCritica Hilo2 = new TieneCritica(nums, choosing, 1);
		
		Hilo1.start();
		Hilo2.start();
	}
}
