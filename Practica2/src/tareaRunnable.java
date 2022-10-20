
public class tareaRunnable implements Runnable{
	private critico crit;
	private int mod;
	
	public tareaRunnable(critico c, int mode) {
		crit = c;
		mod = mode;
	}
	
	public void run(){
		for(int i = 0; i < 10; i++) {
			if(mod == 0)
				crit.inc();
			else 
				crit.dec();
		}
	}
}
