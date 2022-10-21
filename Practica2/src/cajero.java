public class cajero implements Runnable {
    private cuentaCorriente c;
    private int t;

    public cajero(cuentaCorriente cuenta, int tipo){
        c = cuenta;
        t = tipo;
    }

    public void run(){
        for(int i = 0; i < 2000; i++){
            if(t == 0)
                c.deposito(20);
            else
                c.reintegro(20);
        }
    }
}
