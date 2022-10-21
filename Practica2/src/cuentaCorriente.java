public class cuentaCorriente {
    private int numero;
    private float saldo;

    public cuentaCorriente(int n, float s){
        numero = n; saldo = s;
    }

    
    /** 
     * @return int
     */
    public int numero() {
        return numero;
    }

    
    /** 
     * @return float
     */
    public float saldo() {
        return saldo;
    }

    
    /** 
     * @param cantidad
     */
    public void deposito(float cantidad) {
        saldo += cantidad;
    } 

    
    /** 
     * @param cantidad
     */
    public void reintegro(float cantidad) {
        //Suponemos que una cuenta se puede quedar en numeros rojos
        saldo -= cantidad;
    }
}
