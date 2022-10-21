public class concursoLambda {
    public static String compartida = "";
    
    /** 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        Runnable r1 = new Runnable() {
            public void run(){
                compartida = compartida.concat("hola");
            }
        };
        Runnable r2 = new Runnable() {
            public void run(){
                compartida = compartida.concat("adios");
            }
        };

        Thread h1 = new Thread(r1);
        Thread h2 = new Thread(r2);

        h1.start();
        h2.start();
        h1.join();
        h2.join();

        System.out.println(compartida);
    }
}
