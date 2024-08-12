import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long[] arr;
    static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new long[N+1];
        dp = new long[N+1][N+1];

        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                dp[i][j]=-1;
            }
        }

        long ans=0;
        for (int i=0;i<N;i++){
            ans=Math.max(ans,arr[i]+ioi(i,i));
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }

    static long ioi(int left, int right) {
        //1. if right is the end
        if (goRight(right)==left){return 0;}
        //2. left>right, go left
        if (arr[goLeft(left)]>arr[goRight(right)]){
            return joi(goLeft(left),right);
        }
        //3. else, then go right
        return joi(left,goRight(right));
    }

    static long joi(int left, int right) {
        //1. reached the end
        if (goRight(right)==left){
            return dp[left][right]=0;
        }
        //2. renewal
        if (dp[left][right]!=-1) return dp[left][right];

        //3. left / right unite
        long leftV=arr[goLeft(left)]+ioi(goLeft(left),right);
        long rightV=arr[goRight(right)]+ioi(left,goRight(right));

        return dp[left][right]=Math.max(leftV,rightV);
    }

    static int goRight(int idx) {
        return (idx+1)%N;
    }

    static int goLeft(int idx) {
        return (idx+N-1)%N;
    }
}
