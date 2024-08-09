import java.io.*;
import java.util.*;
public class Main {
    static int[][] dp;
    static int N,M;
    static int[] Memory, Cost;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        Memory=new int[N+1];
        Cost=new int[N+1];
        st=new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            Memory[i]=Integer.parseInt(st.nextToken());
        }
        st=new StringTokenizer(br.readLine());
        int costSum=0;
        for (int i = 1; i <= N; i++) {
            Cost[i]=Integer.parseInt(st.nextToken());
            costSum+=Cost[i];
        }
        dp=new int[N+1][costSum+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= costSum; j++) {
                if (j-Cost[i]>=0) {dp[i][j]=Math.max(dp[i-1][j-Cost[i]]+Memory[i],dp[i][j]);}
                dp[i][j]=Math.max(dp[i][j],dp[i-1][j]);
            }
        }
        int cost=0;
        for (int i = 1; i <= N; i++) {
            for (cost=0;cost<costSum && dp[i][cost]<M;cost++);
        }
        bw.write(cost+"\n");
        bw.flush();
        bw.close();
        br.close();

    }
}
