public class redCajeros {
    
    /** 
     * Funcion principal. Crea una cuenta corriente y lanza 2 hilos cajeros
     * @param args  Argumentos por consola, no necesarios en este caso
     * @throws Exception    Thread.join()
     */
    public static void main(String[] args) throws Exception{
        ccSem C = new ccSem(1, 1000);
        Runnable r1 = new cajero(C, 0);
        Runnable r2 = new cajero(C, 1);
        Thread h1 = new Thread(r1);
        Thread h2 = new Thread(r2);

        h1.start();
        h2.start();
        h1.join();
        h2.join();

        System.out.println(C.saldo());
    }
}
