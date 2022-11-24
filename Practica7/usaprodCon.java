public class usaprodCon extends Thread {
    private int tipo;   //0: productor, 1: consumidor
    private prodCon buffer;
    private static int total = 0;
    private static Object O = new Object();

    public usaprodCon(int t, prodCon b){tipo = t; buffer = b;}

    public void run(){
        switch(tipo){
            case 0:
                buffer.add(1);  //Añadimos un 1 en el buffer
            break;
            case 1:
                synchronized(O){
                    total += buffer.get();
                }
            break;
        }
    }

    public static void main(String[] args) throws Exception {
        prodCon buffer = new prodCon(1);
        int nprod = 100, ncon = 2;
        usaprodCon productores[] = new usaprodCon[nprod];
        usaprodCon consumidores[] = new usaprodCon[ncon];

        for(int i = 0; i < nprod; i++)
            productores[i] = new usaprodCon(0, buffer);
        for(int i = 0; i < ncon; i++)
            consumidores[i] = new usaprodCon(1, buffer);
        
        for(int i = 0; i < nprod; i++)
            productores[i].start();
        for(int i = 0; i < ncon; i++)
            consumidores[i].start();

        for(int i = 0; i < ncon; i++)
            consumidores[i].join();
        
        System.out.println("El resultado ha sido " + total);

    }
}
