import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class sPiMonteCarlo extends UnicastRemoteObject implements iPiMonteCarlo {
    private int nPuntos, hits;

    /**
     * Constructor. Inicializa los parámetros
     * @throws RemoteException  RMI
     */
    public sPiMonteCarlo() throws RemoteException{
        nPuntos = 0; hits = 0;
    }

    /**
     * Resetea el servidor poniendo los contadores a 0
     * @throws RemoteException RMI
     */
    public void reset() throws RemoteException{
        nPuntos = 0; hits = 0;
    }

    /**
     * Manda al servidor un número de puntos, que este genera y lanza al círculo unidad
     * @throws RemoteException RMI
     */
    public void masPuntos(int nPuntos) throws RemoteException{
        this.nPuntos += nPuntos;
        Random rd = new Random();
        for(int i = 0; i < nPuntos; i++){
            double x = rd.nextDouble(), y = rd.nextDouble();
            if(x*x + y*y <= 1) hits++;
        }
    }

    /**
     * Observador de pi
     * @throws RemoteException RMI
     * @return double. La aproximación de pi al momento
     */
    public double aproxActual() throws RemoteException{
        return ((float)(hits)/nPuntos * 4f);
    }

    /**
     * Comportamiento del servidor. Se inicializa y se registra en el DNS
     * @param args Argumentos por consola. No necesarios en este caso
     * @throws Exception RMI + DNS
     */
    public static void main(String[] args) throws Exception{
        iPiMonteCarlo oRemoto = new sPiMonteCarlo();

        Naming.bind("Servidor", oRemoto);

        System.out.println("Servidor preparado");
    }
}