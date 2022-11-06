public class tryFour extends Thread {
    private static volatile boolean wantp = false, wantq = false;
    private short tHilo;

    public tryFour(short tipo){
        tHilo = tipo;
    }

    public void run(){
        switch(tHilo){
            case 0:
            while(true){
                wantp = true;
                while(wantq){
                    wantp = false;
                    wantp = true;
                }
                System.out.println(getName());
                wantp = false;
            }
            case 1:
            while(true){
                wantq = true;
                while(wantp){
                    wantq = false;
                    wantq = true;
                }
                System.out.println(getName());
                wantq = false;
            }
        } 
    }

    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        tryThree h1 = new tryThree((short)0);   //p
        tryThree h2 = new tryThree((short)1);   //q
        h1.setName("Hilo p");
        h2.setName("Hilo q");

        h1.start();
        h2.start();
    }
}
