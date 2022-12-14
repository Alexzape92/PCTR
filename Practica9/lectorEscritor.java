import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class lectorEscritor {
    private int lectores;
    private boolean escribiendo;

    private ReentrantLock L = new ReentrantLock();
    Condition cLectores = L.newCondition();
    Condition cEscritores = L.newCondition();

    /**
     * Constructor por defecto. Inicializa las variables privadas a sus valores correspondientes
     */
    public lectorEscritor(){
        lectores = 0; escribiendo = false;
    }

    /**
     * Pre-protocolo para la lectura
     */
    public void iniciarLectura(){
        L.lock();
        try{
            while(escribiendo){
                try{
                    cLectores.await();
                } catch(InterruptedException e){}
            }
            lectores++;
            cLectores.signalAll();   //Despertar a más lectores (si hay también escritores, 
                        //se esperarán porque lectores > 0. Si no, se despierta un escritor)
        }finally{
            L.unlock();
        }
    }

    /**
     * Pos-protocolo para la lectura
     */
    public void acabarLectura(){
        L.lock();

        lectores--;
        if(lectores > 0) cLectores.signalAll();
        else cEscritores.signalAll();
        
        L.unlock();
    }

    /**
     * Pre-protocolo para la escritura
     */
    public void iniciarEscritura(){
        L.lock();
        try{
            while(lectores > 0 || escribiendo){
                try{
                    cEscritores.await();
                } catch(InterruptedException e){}
            }
            escribiendo = true;
        }finally{
            L.unlock();
        }
    }

    /**
     * Pos-protocolo para la escritura
     */
    public void acabarEscritura(){
        L.lock();
        escribiendo = false;
        cLectores.signalAll(); cEscritores.signalAll();
        L.unlock();
    }
}
