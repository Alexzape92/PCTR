import java.util.concurrent.*;;

public class algPeterson implements Runnable{
    private int tHilo;
    private static volatile boolean wantp = false, wantq = false;
    private static volatile int last = 1;

    public algPeterson(int t){
        tHilo = t;
    }

    public void run(){
        switch(tHilo){
            case 0:
            Thread.currentThread().setName("Hilo p");
            while(true){
                wantp = true;
                last = 1;
                while(wantq && last == 1);
                System.out.println(Thread.currentThread().getName());
                wantp = false;
            }
            case 1:
            Thread.currentThread().setName("Hilo q");
            while(true){
                wantq = true;
                last = 2;
                while(wantp && last == 2);
                System.out.println(Thread.currentThread().getName());
                wantq = false;
            }
        }
    }

    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService ejecutor = Executors.newFixedThreadPool(2);

        ejecutor.execute(new algPeterson(0));
        ejecutor.execute(new algPeterson(1));
    }
}
