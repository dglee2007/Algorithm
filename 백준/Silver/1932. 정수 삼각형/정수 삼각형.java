import java.io.*;
import java.util.*;
public class Main {
	static int[][] map;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N= Integer.parseInt(br.readLine());
        //String[] inputs = br.readLine().split(" ");
        
        map=new int[N+1][N+1];
        dp=new int[N+1][N+1];
        
        for (int i=1;i<=N;i++) {
        	String[] inputs= br.readLine().split(" ");
        	for (int j=1;j<=i;j++) {
        		map[i][j]=Integer.parseInt(inputs[j-1]);
        	}
        }
        dp[1][1]=map[1][1];
        
        for (int i=2;i<=N;i++) {
        	for (int j=1;j<=i;j++) {
        		dp[i][j]=Math.max(dp[i-1][j-1], dp[i-1][j])+map[i][j];
        	}
        }
        
        int maxsum=0;
        for (int j=1;j<=N;j++) {
        	if (dp[N][j]>maxsum) {
        		maxsum=dp[N][j];
        	}
        }
        
        bw.write(String.valueOf(maxsum));
        bw.flush();
        bw.close();
        br.close();
        

	}

}
