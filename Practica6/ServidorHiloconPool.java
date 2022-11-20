import java.net.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.io.*;

public class ServidorHiloconPool implements Runnable
{
    Socket enchufe;

    /**
     * Constructor
     * @param s Socket sobre el que va a trabajar este hilo
     */
    public ServidorHiloconPool(Socket s)
    {enchufe = s;}

    /**
     * Comportamiento del hilo del servidor. Se conecta con el socket para imprimir por pantalla lo que le envía el cliente
     */
    public void run()
    {
    try{
        BufferedReader entrada = new BufferedReader(
                                    new InputStreamReader(
                                        enchufe.getInputStream()));
        String datos = entrada.readLine();
        int j;
        int i = Integer.valueOf(datos).intValue();
        for(j=1; j<=20; j++){
        System.out.println("El hilo "+Thread.currentThread().getName()+" escribiendo el dato "+i);
        Thread.sleep(1000);}
        enchufe.close();
        System.out.println("El hilo "+Thread.currentThread().getName()+"cierra su conexion...");
    } catch(Exception e) {System.out.println("Error...");}
    }//run


/** 
 * Función principal. Pone el servidor en funcionamiento, abriendo el socket y creando el pool que ejecutará las tareas recibidas
 * @param args  Parámetros por consola. No necesarios en este caso
 */
public static void main (String[] args)
{
    int puerto = 2001;
    int nTareas = (int)(Runtime.getRuntime().availableProcessors() / (1 - 0.6f));   //Aproximamos Cb a 0.6
        try{
            ServerSocket chuff = new ServerSocket (puerto, 3000);
            ThreadPoolExecutor pool = new ThreadPoolExecutor(nTareas, nTareas, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>() );

            while (true){
                System.out.println("Esperando solicitud de conexion...");
                Socket cable = chuff.accept();
                System.out.println("Recibida solicitud de conexion...");
                pool.execute(new ServidorHiloconPool(cable));
        }//while
      } catch (Exception e)
        {System.out.println("Error en sockets...");}
}//main

}//Servidor_Hilos