import java.util.concurrent.locks.ReentrantLock;

public class cCRL {
    private int numero;
    private float saldo;
    private static ReentrantLock L = new ReentrantLock();

    /**
     * Constructor de la clase cuentaCorriente
     * @param n numero de cuenta corriente
     * @param s saldo inicial de la cuenta
     */
    public cCRL(int n, float s){
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
        L.lock();
        float s = saldo;
        L.unlock();

        return s;
    }

    
    /** 
     * Añade dinero a la cuenta
     * @param cantidad  Cantidad de dinero a depositar
     */
    public void deposito(float cantidad) {
        L.lock();
        saldo += cantidad;
        L.unlock();
    } 

    
    /** 
     * Retira dinero de la cuenta
     * @param cantidad  Cantidad de dinero a retirar
     */
    public void reintegro(float cantidad) {
        //Suponemos que una cuenta se puede quedar en numeros rojos
        L.lock();
        saldo -= cantidad;
        L.unlock();
    }
}