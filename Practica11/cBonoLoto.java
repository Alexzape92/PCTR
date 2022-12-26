import java.rmi.Naming;
import java.util.Random;

public class cBonoLoto {
    /**
     * Comportamiento del cliente. Se simulan cuatro clientes que van generando apuestas, y se itera hasta que alguno gane. Esto tardará mucho, pues la probabilidad de acertar los 6 números es ínfima
     * @param args  Argumentos por consola, no necesarios en este caso
     * @throws Exception    RMI + DNS
     */
    public static void main(String[] args) throws Exception{
        //obtenemos el objeto remoto
        iBonoLoto remoto = (iBonoLoto) Naming.lookup("//localhost/Servidor");

        //Tenemos 4 jugadores, cada uno generando sus apuestas de 6 números
        int jugadores[][] = new int[4][6], i = 0;
        boolean termina = false;
        while(!termina){
            System.out.print("APUESTA JUGADOR " + i + ": [");
            //Simulamos el comportamiento de los jugadores
            for(int j = 0; j < 6; j++){
                Random rd = new Random();
                jugadores[i][j] = rd.nextInt(49) + 1;
                System.out.print(" " + jugadores[i][j]);
            }
            System.out.print(" ]\n");
            if(remoto.compApuesta(jugadores[i])){
                termina = true;
                System.out.println("El jugador " + i + " ha ganado");
            }
            i++;
            remoto.resetServidor();
            if(!termina && i == 4)  i = 0;
        }
    }
}
