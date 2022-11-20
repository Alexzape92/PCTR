public class deadlock extends Thread {
    private Object obj1, obj2, obj3;

    /**
     * Constructor
     * @param o1    Primer cerrojo para sincronizar
     * @param o2    Segundo cerrojo para sincronizar
     * @param o3    Tercer cerrojo para sincronizar
     */
    deadlock(Object o1, Object o2, Object o3){
        obj1 = o1; obj2 = o2; obj3 = o3;
    }

    /**
     * Comportamiento del hilo. Muestra por pantalla si se produce o no el interblqueo. Muy probablemente se produzca un entrelazado que de a interbloqueo
     */
    public void run(){
        synchronized(obj1){
            synchronized(obj2){
                synchronized(obj3){
                    System.out.println("Soy " + getName() + " y sali del deadlock! :D");
                }
            }
        }
    }

    
    /** 
     * Función principal. Crea los cerrojos y crea e inicializa los hilos deadlock
     * @param args  Argumentos por consola. No necesarios en este caso
     */
    public static void main(String[] args) {
        Object obj1 = new Object(), obj2 = new Object(), obj3 = new Object();

        Thread h1 = new deadlock(obj1, obj2, obj3);
        Thread h2 = new deadlock(obj2, obj3, obj1);
        Thread h3 = new deadlock(obj3, obj1, obj2);

        h1.start(); h2.start(); h3.start();
    }
}
