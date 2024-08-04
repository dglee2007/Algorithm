import java.io.*;
 
public class Main {
 
	static long[] arr;
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		
		int N = Integer.parseInt(br.readLine());
		
		arr = new long[N + 1];
 
		for(int i = 0; i < N + 1; i++) {
			arr[i] = -1;
		}
        
		arr[0] = 0;
		arr[1] = 1;
        bw.write(Fib(N)+"\n");
        bw.close();
        br.close();
	}
	
	public static long Fib(int N) {
		if(arr[N] == -1) {
			arr[N] = Fib(N - 1) + Fib(N - 2);
		}
		return arr[N];
	}
 
}