import java.io.*;
import java.util.*;

public class Main {
    private final static int log = 18;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int m = Integer.parseInt(br.readLine());

        dp = new int[log+1][m+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < m+1; i++) {
            dp[0][i] = Integer.parseInt(st.nextToken());
        }
        //dp[k][i]는 f^2^k(i)를 저장
        for (int i=1; i<log+1; i++){
            for (int j=1;j<=m;j++){
                dp[i][j] = dp[i-1][dp[i-1][j]];
            }
        }

        int q = Integer.parseInt(br.readLine());

        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            for (int i=log;i>=0;i--){
                int cur = (1<<i);
                if (n>=cur){
                    x=dp[i][x];
                    n-=cur;
                    if (n==0) break;
                }
            }

            bw.write(x+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
