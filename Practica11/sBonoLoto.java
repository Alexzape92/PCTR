import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class sBonoLoto extends UnicastRemoteObject implements iBonoLoto {
    private int numeros[];

    /**
     * Constructor. Inicializa el resultado de la bonoloto con números aleatorios
     * @throws RemoteException  RMI
     */
    public sBonoLoto() throws RemoteException{
        numeros = new int[6];
        Random r = new Random();
        for(int i = 0; i < 5; i++){
            int num = r.nextInt(48) + 1;    //Numero aleatorio entre 1 y 49
            numeros[i] = num;
        }
    }

    /**
     * Cambia los números que han salido para el servidor, generándolos aleatoriamente
     * @throws RemoteException  RMI
     */
    public void resetServidor() throws RemoteException{
        Random r = new Random();
        for(int i = 0; i < 5; i++){
            int num = r.nextInt(48) + 1;    //Numero aleatorio entre 1 y 49
            numeros[i] = num;
        }
    }

    /**
     * Método para que los clientes comprueben si han ganado
     * @param apuesta Vector con los 6 números por los que apuesta el cliente
     * @return boolean. Verdadero si ha ganado, falso si no
     * @throws RemoteException RMI
     */
    public boolean compApuesta(int[] apuesta)  throws RemoteException{
        boolean result = true;
        for(int i = 0; i < 6; i++){
            if(apuesta[i] != numeros[i]) result = false;
        }
        return result;
    }

    /**
     * Comportamiento del programa. Se lanza el servidor
     * @param args  Argumentos por consola. No necesarios en este caso
     * @throws Exception RMI + DNS
     */
    public static void main(String[] args) throws Exception{
        iBonoLoto oRemoto = new sBonoLoto();

        //Registro en servidor DNS
        Naming.bind("Servidor", oRemoto);

        System.out.println("Servidor Remoto Preparado");
    }
}
