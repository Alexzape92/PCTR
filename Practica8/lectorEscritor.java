public class lectorEscritor {
    private int lectores;
    private boolean escribiendo;

    /**
     * Constructor por defecto. Inicializa las variables privadas a sus valores correspondientes
     */
    public lectorEscritor(){
        lectores = 0; escribiendo = false;
    }

    /**
     * Pre-protocolo para la lectura
     */
    public synchronized void iniciarLectura(){
        while(escribiendo){
            try{
                wait();
            } catch(InterruptedException e){}
        }
        lectores++;
        notifyAll();    //Despertar a más lectores (si hay también escritores, 
                        //se esperarán porque lectores > 0. Si no, se despierta un escritor)
    }

    /**
     * Pos-protocolo para la lectura
     */
    public synchronized void acabarLectura(){
        lectores--;
        notifyAll();
    }

    /**
     * Pre-protocolo para la escritura
     */
    public synchronized void iniciarEscritura(){
        while(lectores > 0 || escribiendo){
            try{
                wait();
            } catch(InterruptedException e){}
        }
        escribiendo = true;
    }

    /**
     * Pos-protocolo para la escritura
     */
    public synchronized void acabarEscritura(){
        escribiendo = false;
        notifyAll();
    }
}
