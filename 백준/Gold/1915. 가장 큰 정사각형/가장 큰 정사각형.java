import java.io.*;
import java.util.*;
public class Main {
	static int[][] dp,table;
	static int N,M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        
        table=new int[N+1][M+1];
        dp=new int[N+1][M+1];
        
        for (int i=1;i<=N;i++) {
        	String line=br.readLine();
        	for (int j=1;j<=M;j++) {
        		table[i][j]=line.charAt(j-1)-'0';
        	}
        }
        int ans=0;

        for (int i=1;i<=N;i++) {
        	for (int j=1;j<=M;j++) {
        		if (table[i][j]==1) {
            		dp[i][j]=Math.min(Math.min(dp[i][j-1], dp[i-1][j]),dp[i-1][j-1])*table[i][j]+table[i][j];
            		ans=Math.max(ans, dp[i][j]);
        		}
        	}
        }
        
        bw.write((ans*ans)+"\n");
        bw.flush();
        bw.close();
        br.close();
	}

}
