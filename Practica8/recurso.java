public class recurso{
    private long n;

    /**
     * Constructor por defecto. Inicializa la variable privada n a 0
     */
    public recurso(){
        n = 0;
    }

    /**
     * Incrementa la variable que encapsula
     */
    public void inc(){
        n++;
    }

    /**
     * Observador de la variable enapsulada
     * @return Devuelve la variable encapsulada
     */
    public long observer(){
        return n;
    }
}