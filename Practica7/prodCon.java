public class prodCon{
    private int buffer[], in_ptr, out_ptr, eltos, N;

    public prodCon(int n){
        N = n;
        in_ptr = out_ptr =  eltos = 0;
        buffer = new int[N];
    }

    public synchronized void add(int x){
        while(eltos == N) try{wait();}catch(Exception e){}

        buffer[in_ptr] = x;
        in_ptr = (in_ptr + 1) % N;
        eltos++;
        notifyAll();
    }

    public synchronized int get(){
        while(eltos == 0) try{wait();}catch(Exception e){}

        int res = buffer[out_ptr];
        out_ptr = (out_ptr + 1) % N;
        eltos--;
        notifyAll();

        return res;
    }
}