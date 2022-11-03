public class tryFour extends Thread {
    static boolean wantp = false, wantq = false;
    private short tHilo;

    public tryFour(short tipo){
        tHilo = tipo;
    }

    public void run(){
        while(true){
            switch(tHilo){
                case 0:
                    wantp = true;
                    while(wantq){
                        wantp = false;
                        wantp = true;
                    }
                    System.out.println(getName());
                    wantp = false;
                    break;
                case 1:
                    wantq = true;
                    while(wantp){
                        wantq = false;
                        wantq = true;
                    }
                    System.out.println(getName());
                    wantq = false;
                    break;
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
