import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class clienteMultiple {
    
    /** 
     * Función principal del cliente. Manda varias peticiones al servidor
     * @param args  Parámetros por consola. No necesarios en este caso.
     */
    public static void main(String[] args) {
        for(int k = 0; k < 20; k++){
            int i = (int)(Math.random()*10);
            int puerto = 2001;
            try{
                System.out.println("Realizando conexion...");
                Socket cable = new Socket("localhost", puerto);
                System.out.println("Realizada conexion a " + cable);
                PrintWriter salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(cable.getOutputStream())));
                salida.println(i);
                salida.flush();
                System.out.println("Cerrando conexion...");
                cable.close();
            }catch(Exception e){System.out.println("Error en sockets...");}
        }
    }
}
