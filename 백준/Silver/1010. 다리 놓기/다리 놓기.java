import java.io.*;
import java.util.*;

public class Main {
	
	
	final private static int MAX_N = 30;
	private static int[][] C = new int[MAX_N+1][MAX_N+1];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int T=Integer.parseInt(br.readLine());
        
        calCombi();
                
        for (int i=0;i<T;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N=Integer.parseInt(st.nextToken());
            int M=Integer.parseInt(st.nextToken());
            
            bw.write(C[M][N] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
	}
	
	private static void calCombi() {
		int n, k;

	    for (n = 0; n <= 30; n++) {
	        C[n][0] = C[n][n] = 1;
	        for (k = 1; k < n; k++) {
	            C[n][k] = (C[n - 1][k - 1] + C[n - 1][k]);
	        }
	    }
	}
}