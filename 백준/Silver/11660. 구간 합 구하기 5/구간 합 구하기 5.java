import java.io.*;
public class Main {
	static int[][] dp,table;
	static int N,M,x1,y1,x2,y2;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] inputs = br.readLine().split(" ");
        N=Integer.parseInt(inputs[0]);
        M=Integer.parseInt(inputs[1]);
        
        table=new int[N+1][N+1];
        dp=new int[N+1][N+1];
        
        for (int i=1;i<=N;i++) {
        	inputs=br.readLine().split(" ");
        	for (int j=1;j<=N;j++) {
        		table[i][j]=Integer.parseInt(inputs[j-1]);
        	}
        }
        
        for (int i=1;i<=N;i++) {
        	for (int j=1;j<=N;j++) {
        		dp[i][j]=dp[i-1][j]+dp[i][j-1]-dp[i-1][j-1]+table[i][j];
        	}
        }
        
        for (int i=0;i<M;i++) {
        	inputs = br.readLine().split(" ");
        	x1=Integer.parseInt(inputs[0]);
        	y1=Integer.parseInt(inputs[1]);
        	x2=Integer.parseInt(inputs[2]);
        	y2=Integer.parseInt(inputs[3]);
          	bw.write((dp[x2][y2]-dp[x1-1][y2]-dp[x2][y1-1]+dp[x1-1][y1-1])+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
	}

}
