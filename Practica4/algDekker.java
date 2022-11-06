public class algDekker extends Thread {
    private int tHilo;
    private static volatile boolean wantp = false, wantq = false;
    private static volatile int turn = 1;

    public algDekker(int t){
        tHilo = t;
    }

    public void run(){
        switch(tHilo){
            case 0:
            while(true){
                wantp = true;
                while(wantq){
                    if (turn == 2){
                        wantp = false;
                        while(turn == 2);
                        wantp = true;
                    }
                }
                System.out.println(getName());
                turn = 2;
                wantp = false;
            }
            //break;
            case 1:
            while(true){
                wantq = true;
                while(wantp){
                    if (turn == 1){
                        wantq = false;
                        while(turn == 1);
                        wantq = true;
                    }
                }
                System.out.println(getName());
                turn = 1;
                wantq = false;
            } 
            //break;
        }
    }

    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        algDekker h1 = new algDekker(0);   //p
        algDekker h2 = new algDekker(1);   //q
        h1.setName("Hilo p");
        h2.setName("Hilo q");

        h1.start();
        h2.start();
    }
}
