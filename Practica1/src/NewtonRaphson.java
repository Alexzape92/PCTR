import java.io.*;

public class NewtonRaphson {
	public static void main(String[] args) throws Exception{
		int n = 0;
		BufferedReader dato = new BufferedReader(new InputStreamReader(System.in));
		do {
			System.out.println("Introduce la cantidad de numeros a generar: ");
			n = Integer.parseInt(dato.readLine());
		} while(n <= 0);
		
		double x01 = 0;
		System.out.println("Introduce la aproximacion inicial para cos(x) - x3: ");
		x01 = Double.parseDouble(dato.readLine());
		double x02 = 0;
		System.out.println("Introduce la aproximacion inicial para x2 - 5: ");
		x02 = Double.parseDouble(dato.readLine());
		
		
		double xN1 = x01, xN2 = x02;
		for(int i = 0; i < n; i++) {
			double fderxn1 = (-1) * Math.sin(xN1) - 3 * (Math.pow(xN1, 2));
			double fxn1 = Math.cos(xN1) - Math.pow(xN1, 3);
			if(fderxn1 != 0) {
				xN1 = xN1 - fxn1 / fderxn1;
				System.out.println("cos(x) - x3 --------------------------------------------------------------");
				System.out.print("Iteración: ");
				System.out.print(i);
				System.out.print(", Aproximacion: ");
				System.out.println(xN1);
			}
			double fderxn2 = 2 * xN2;
			double fxn2 = Math.pow(xN2, 2) - 5;
			if(fderxn2 != 0) {
				xN2 = xN2 - fxn2 / fderxn2;
				System.out.println("x2 - 5 -------------------------------------------------------------------");
				System.out.print("Iteración: ");
				System.out.print(i);
				System.out.print(", Aproximacion: ");
				System.out.println(xN2);
			}
		}
		System.out.print("Resultado para cos(x) - x3: ");
		System.out.println(xN1);
		
		System.out.print("Resultado para x2 - 5: ");
		System.out.println(xN2);
	}
}
