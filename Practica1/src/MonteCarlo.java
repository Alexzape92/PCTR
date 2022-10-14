import java.util.Random;

public class MonteCarlo {
	public static void main(String[] args) {
		int n = 1000;
		int hits = 0;
		Random r = new Random();
		double x = 0, y = 0;	//puntos generados
		for(int i = 0; i < n; i++) {
			x = r.nextDouble(1); y= r.nextDouble(1);	//Para el rango [min, max] -> nextFloat(max - min) + min
			if(y <= Math.sin(x))
				hits++;
		}
		System.out.println("Integral Aproximada: ");
		System.out.println(hits/n);
	}
}
