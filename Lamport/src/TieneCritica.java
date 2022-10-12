

public class TieneCritica extends Thread{
	private static int x = 0;
	
	private int numeros[];
	private Boolean eligiendo[];
	private int num;
	
	public TieneCritica(int n[], Boolean e[], int numero) {
		numeros = n;
		eligiendo = e;
		num = numero;
	}
	
	public void run() {
		//Calculamos el numero de turno
		eligiendo[num] = true;
		numeros[num] = 1 + Math.max(numeros[0], numeros[1]);
		eligiendo[num] = false;
		
		for(int j = 0; j < 2; j++) {
			while(eligiendo[j]) {
				//Esperamos a que termine
			}
			while(numeros[j] != 0 && (numeros[j] < numeros[num] || (numeros[j] == numeros[num] && j < num))) {
				//esperamos
			}
		}

		
		x++;	//Sección crítica
		System.out.println(x);
		
		numeros[num] = 0; //terminado
	}
}
