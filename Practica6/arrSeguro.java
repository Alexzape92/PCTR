public class arrSeguro extends Thread {
    private static int n = 100, array[] = new int[n];
    private static Object lock[] = new Object[n];

    /**
     * Comportamiento del hilo. Aumenta en 1 todos los elementos de array de forma segura
     */
    public void run(){
        for(int i = 0; i < n; i++){
            synchronized(lock[i]){
                array[i]++;
            }
        }
    }

    
    /** 
     * Función principal. Rellena y muestra por pantalla el array inicial, y después el resultado después de incrementarlo 4 veces.
     * @param args  Parámetros por consola. No son necesarios en este caso
     * @throws InterruptedException Thread.join()
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Array inicial:");
        for(int i = 0; i < n; i++){ //rellenamos los arrays
            array[i] = (int)(Math.random()*10);
            System.out.print(array[i] + " ");
            lock[i] = new Object();
        }
        System.out.println('\n');

        Thread h1 = new arrSeguro();
        Thread h2 = new arrSeguro();
        Thread h3 = new arrSeguro();
        Thread h4 = new arrSeguro();

        h1.start(); h2.start(); h3.start(); h4.start();
        h1.join(); h2.join(); h3.join(); h4.join();

        System.out.println("Array final:");
        for(int i = 0; i < n; i++)
            System.out.print(array[i] + " ");
        System.out.println();
    }
}
