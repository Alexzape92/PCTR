public class usalectorEscritor extends Thread {
    private recurso r;
    private lectorEscritor le;
    private int tipo;

    /**
     * Constructor de la clase usalectorEscritor
     * @param r     Objeto de la clase recurso (que debería ser común a varias hebras)
     * @param le    Monitor con el que controlaremos los accesos a r
     * @param tipo  Tipo de hebra. 0 = lector, 1 = escritor
     */
    public usalectorEscritor(recurso r, lectorEscritor le, int tipo){
        this.r = r;
        this.le = le;
        this.tipo = tipo;
    }

    /**
     * Comportamiento del hilo, dependiendo de si es lector o escritor. Los lectores imprimen por pantalla el valor, y los escritores lo incrementan
     */
    public void run(){
        switch(tipo){
            case 0: //lector
                for(int i = 0; i < 100; i++){
                    le.iniciarLectura();
                    System.out.println("El hilo " + getName() + " ha leído el dato " + r.observer());
                    le.acabarLectura();
                }
            break;
            case 1: //escritor
            for(int i = 0; i < 100; i++){
                    le.iniciarEscritura();
                    r.inc();
                    le.acabarEscritura();
                }
            break;
        }
    }

    /**
     * Función principal. Lanza 3 lectores y 3 escritores
     * @param args  Argumentos por consola. No necesarios en este caso
     */
    public static void main(String[] args) {
        recurso r = new recurso();
        lectorEscritor le = new lectorEscritor();

        new usalectorEscritor(r, le, 0).start();
        new usalectorEscritor(r, le, 0).start();
        new usalectorEscritor(r, le, 0).start();

        new usalectorEscritor(r, le, 1).start();
        new usalectorEscritor(r, le, 1).start();
        new usalectorEscritor(r, le, 1).start();
    }
}
