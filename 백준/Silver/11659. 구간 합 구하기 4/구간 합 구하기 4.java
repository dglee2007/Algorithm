import java.io.*;
import java.util.*;
public class Main {
	static int[] dp;
	static int N,M,n,m;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] inputs = br.readLine().split(" ");
        N=Integer.parseInt(inputs[0]);
        M=Integer.parseInt(inputs[1]);
        dp=new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) {
        	dp[i]=dp[i-1]+Integer.parseInt(st.nextToken());
        }
        
        for (int i=0;i<M;i++) {
        	inputs = br.readLine().split(" ");
            n=Integer.parseInt(inputs[0]);
            m=Integer.parseInt(inputs[1]);
        	bw.write((dp[m]-dp[n-1])+"\n");
        }
        
        bw.flush();
        bw.close();
        br.close();
	}
}
