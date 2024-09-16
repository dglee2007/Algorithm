import java.io.*;
import java.util.*;

public class Main {
    static class work {
        int time,pay;
        public work(int time, int pay) {
            this.time = time;
            this.pay = pay;
        }
    }
    static int n;
    static work[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new work[n];
        dp = new int[n+1];
        StringTokenizer st = null;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new work(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < n; i++) {
            if (i+arr[i].time<n+1){
                dp[i+arr[i].time]=Math.max(dp[i+arr[i].time],dp[i]+arr[i].pay);
            }
            dp[i+1]=Math.max(dp[i+1],dp[i]);
        }
        System.out.println(dp[n]);

    }

}
