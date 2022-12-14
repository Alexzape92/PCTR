import java.util.concurrent.CyclicBarrier;

public class barrera extends Thread{
    private CyclicBarrier B;

    public barrera(CyclicBarrier B){
        this.B = B;
    }

    /**
     * Comportamiento del hilo. Imprime un mensaje en pantalla hasta llegar a una cierta iteración, en la cual se sincroniza con el resto de hilos, para luego seguir iterando
     */
    public void run(){
        for(int i = 0; i < 100; i++){
            if(i == 50){
                try{
                    B.await();
                }catch(Exception e){}
            }
            System.out.println("Soy el hilo " + getName() + " y voy por la iteracion " + i);
        }
    }

    /**
     * Funcion principal. Lanza los hilos correspondientes
     * @param args  Argumentos por consola, no son necesarios en este caso
     */
    public static void main(String[] args) {
        CyclicBarrier B = new CyclicBarrier(3);

        new barrera(B).start();
        new barrera(B).start();
        new barrera(B).start();
    }
}
