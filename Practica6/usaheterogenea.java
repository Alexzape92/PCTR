public class usaheterogenea implements Runnable {
    private heterogenea obj;

    /**
     * Constructor
     * @param o Objeto heterogenea con el que se testeará
     */
    usaheterogenea(heterogenea o){
        obj = o;
    }

    /**
     * Comportamiento del hilo. Incrementa los parámetros n y m del objeto heterogenea
     */
    public void run(){
        for(int i = 0; i < 100; i++){
            obj.incn();
            obj.incm();
        }
    }

    
    /** 
     * Función principal. Crea el objeto heterogenea y los hilos que lo modifican, y a continuación los inicia y muestra el resultado
     * @param args  Argumentos por consola. No son necesarios en este caso
     * @throws Exception    Thread.join()
     */
    public static void main(String[] args) throws Exception {
        heterogenea o = new heterogenea(0, 0);
        Thread h1 = new Thread(new usaheterogenea(o));
        Thread h2 = new Thread(new usaheterogenea(o));
        Thread h3 = new Thread(new usaheterogenea(o));
        Thread h4 = new Thread(new usaheterogenea(o));

        h1.start(); h2.start(); h3.start(); h4.start();
        h1.join(); h2.join(); h3.join(); h4.join();

        System.out.println("n = " + o.n + " y m = " + o.m);
    }
}
