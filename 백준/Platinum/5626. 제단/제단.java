import java.io.*;
import java.util.*;

public class Main {
    static int N, H;
    static final int MOD = 1000000007;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine().trim());
        H=N/2;
        int[] arr;
        arr = new int[N+1];
        dp = new int[2][H+3];
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        for (int i = 1; i <= N; i++) {arr[i]=Integer.parseInt(st.nextToken());}
        //제단의 높이가 -1이면 1, 제단의 높이가 0이면 1, arr[0]>0이면 0
        dp[0][0] = arr[1]==-1?1:arr[1]==0?1:0;
        //dp[0][0] = (arr[1] == -1 || arr[1] == 0) ? 1 : 0;

        for (int i=2; i <= N; i++) {
            if (arr[i]==-1){
                dp[1][0] = (dp[0][0]+dp[0][1])%MOD;
                for (int j = 1; j <= H; j++) {
                    dp[1][j]=((dp[0][j-1]+dp[0][j])%MOD+dp[0][j+1])%MOD;
                }
            }
            else if (arr[i]==0){
                dp[1][0] = (dp[0][0] + dp[0][1])%MOD;
            }
            else {
                dp[1][arr[i]]=((dp[0][arr[i]-1]+dp[0][arr[i]])%MOD+dp[0][arr[i]+1])%MOD;
            }

            for (int j=0;j<=H;j++) {
                dp[0][j]=dp[1][j];
                dp[1][j]=0;
            }
        }
        bw.write(dp[0][0]+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
