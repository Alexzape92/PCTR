import java.rmi.Naming;

public class cPiMonteCarlo {

    /**
     * Comportamiento del cliente. Manda 1000 puntos, solicita la aproximación y manda otros 10000 puntos, volviendo a solicitar la aproximación (que ahora deberá ser mejor)
     * @param args Argumentos por consola, no necesarios en este caso
     * @throws Exception    RMI + DNS
     */
    public static void main(String[] args) throws Exception{
        iPiMonteCarlo remoto = (iPiMonteCarlo) Naming.lookup("//localhost/Servidor");

        remoto.masPuntos(1000);
        System.out.println("Pi = " + remoto.aproxActual());
        remoto.masPuntos(10000);
        System.out.println("Pi = " + remoto.aproxActual());
    }
}
