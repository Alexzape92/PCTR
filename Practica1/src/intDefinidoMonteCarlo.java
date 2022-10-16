import java.util.Random;
import java.io.*;

public class intDefinidoMonteCarlo {
	public static void main(String[] args) throws Exception{
		int n = 0;
		int hitsx = 0, hitssin = 0;
		BufferedReader dato = new BufferedReader(new InputStreamReader(System.in));
		do {
			System.out.println("Introduce la cantidad de numeros a generar: ");
			n = Integer.parseInt(dato.readLine());
		} while(n <= 0);
		
		Random r = new Random();
		double x = 0, y = 0;	//puntos generados
		for(int i = 0; i < n; i++) {
			x = r.nextDouble(); y= r.nextDouble();	//Para el rango [min, max] -> nextFloat(max - min) + min
			if(y <= x)
				hitsx++;
			if(y <= Math.sin(x)) {
				hitssin++;
			}
		}
		System.out.println("Integral [0, 1] aproximada para f(x) = x: ");
		System.out.println((double) (hitsx)/n);
		
		System.out.println("Integral [0, 1] aproximada para f(x) = sin(x): ");
		System.out.println((double) (hitssin)/n);
	}
}
