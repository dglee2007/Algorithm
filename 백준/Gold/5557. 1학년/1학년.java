import java.io.*;
import java.util.*;

public class Main {
    static int n,plus,minus;
    static int[] arr;
    static long[][] dp;
    static String[] s;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new long[n][21];

        s = br.readLine().split(" ");

        for (int i=0;i<n;i++){
            arr[i] = Integer.parseInt(s[i]);
        }

        dp[0][arr[0]]=1;

        for (int i=1;i<n-1;i++){
            for (int j=0;j<21;j++){
                if (dp[i-1][j]!=0){
                    plus=j+arr[i];
                    minus=j-arr[i];
                    if (plus>=0 && plus<=20){
                        dp[i][plus]+=dp[i-1][j];
                    }
                    if (minus>=0 && minus<=20){
                        dp[i][minus]+=dp[i-1][j];
                    }
                }
            }
        }

        bw.write(dp[n-2][arr[n-1]]+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
