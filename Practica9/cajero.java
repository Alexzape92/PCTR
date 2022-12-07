public class cajero implements Runnable {
    private ccSem c;
    private int t;

    /**
     * Constructor del cajero
     * @param cuenta    Cuenta con la que va a trabajar el cajero
     * @param tipo      0: deposita 20  1: Retira 20
     */
    public cajero(ccSem cuenta, int tipo){
        c = cuenta;
        t = tipo;
    }

    /**
     * Comportamiento concurrente. Hace lo indicado según el tipo (ver constructor)
     */
    public void run(){
        for(int i = 0; i < 2000; i++){
            if(t == 0)
                c.deposito(20);
            else
                c.reintegro(20);
        }
    }
}
