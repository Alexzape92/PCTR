
public class escalaVector {
	
    /** 
     * @param args
     */
    public static void main(String[] args){
        int N = 1000000;
        int vector[] = new int[N];

        for(int i = 0; i < N; i++)
            vector[i] = i + 1;  //inicializamos el vector

        for(int i = 0; i < N; i++){
            vector[i] *= 2; //Escalamos el vector por dos
            System.out.println(vector[i]);
        }
    }
}
