public class numPerfectos {
    
    /** 
     * Comprueba si un número es perfecto o no
     * @param num   Número a comprobar si es perfecto
     * @return boolean
     */
    public static boolean esPerfecto(int num){
        int cont = 1;
        for(int i = 2; i < num; i++){
            if(num % i == 0)
                cont += i;
        }

        return cont == num;
    }

    
    /** 
     * Función principal. Imprime por pantalla el número de números perfectos en un rango.
     * @param args Argumentos recibidos por consola. Debe recibir el primer y último número a comprobar si es perfecto
     */
    public static void main(String[] args) {
        int inicio = Integer.parseInt(args[0]);
        int fin = Integer.parseInt(args[1]);
        int cont = 0;

        long inicTiempo = System.nanoTime();
        for(int t = 0; t < 100; t++){
            cont = 0;
            for(int i = inicio; i <= fin; i++){
                if(esPerfecto(i))
                    cont++;
            }
        }
        float tiempo = (System.nanoTime() - inicTiempo)/1.0e11f;
        
        System.out.println("Hay " + cont + " números perfectos entre " + inicio + " y " + fin + ". Calculado en " + tiempo + "s.");
    }
}
