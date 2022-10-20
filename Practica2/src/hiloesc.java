class hiloesc implements Runnable{
    private int vector[];
    private int ini, fin, factor;

    public hiloesc(int v[], int i, int f, int fa){
        vector = v; ini = i; fin = f; factor = fa;
    }

    public void run(){
        for(int i = ini; i <= fin; i++)
            vector[i] *= factor; //Escalamos por factor
    }
}
