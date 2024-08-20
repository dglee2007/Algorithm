import java.util.*;
import java.io.*;

public class Main {
    static int[][] dp;
    public static final int div = 10007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        dp = new int[n+1][k+1];

        bw.write(BC(n,k)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
    public static int BC(int n, int k) {
        if (dp[n][k] != 0) return dp[n][k];
        if (k==0 ||k==n) return dp[n][k]=1;
        return dp[n][k] = (BC(n-1,k-1)+BC(n-1,k))%div;
    }
}
