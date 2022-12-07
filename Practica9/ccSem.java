import java.util.concurrent.Semaphore;

public class ccSem {
    private int numero;
    private float saldo;
    private static Semaphore L = new Semaphore(1);

    /**
     * Constructor de la clase cuentaCorriente
     * @param n numero de cuenta corriente
     * @param s saldo inicial de la cuenta
     */
    public ccSem(int n, float s){
        numero = n; saldo = s;
    }

    
    /** 
     * Devuelve el numero de cuenta
     * @return int  Numero de la cuenta corriente
     */
    public int numero() {
        return numero;
    }

    
    /** 
     * Devuelve el saldo de la cuenta
     * @return float    Saldo de la cuenta
     */
    public float saldo() {
        float s = 0;
        try{
            L.acquire();
        } catch(Exception e){}
        
        try{s = saldo;}
        finally{L.release();}

        return s;
    }

    
    /** 
     * Añade dinero a la cuenta
     * @param cantidad  Cantidad de dinero a depositar
     */
    public void deposito(float cantidad) {
        try{
            L.acquire();
        } catch(Exception e){}

        try{saldo += cantidad;}
        
        finally{L.release();}
    } 

    
    /** 
     * Retira dinero de la cuenta
     * @param cantidad  Cantidad de dinero a retirar
     */
    public void reintegro(float cantidad) {
        //Suponemos que una cuenta se puede quedar en numeros rojos
        try{
            L.acquire();
        } catch(Exception e){}

        try{saldo -= cantidad;}

        finally{L.release();}
    }
}