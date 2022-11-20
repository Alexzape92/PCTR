public class heterogenea {
    public int n, m;    //Lo suyo sería que fueran privados y poner métodos observadores

    /**
     * Constructor de la clase
     * @param n Parámetro n del objeto (seguro)
     * @param m Parámetro m del objeto (no seguro)
     */
    public heterogenea(int n, int m){
        this.n = n; this.m = m;
    }

    /**
     * Incrementa n en 1 de forma segura
     */
    public synchronized void incn(){
        n++;
    }

    /**
     * Incrementa m en 1
     */
    public void incm(){
        m++;
    }
}
