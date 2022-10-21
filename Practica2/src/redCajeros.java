public class redCajeros {
    
    /** 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        cuentaCorriente C = new cuentaCorriente(1, 1000);
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
