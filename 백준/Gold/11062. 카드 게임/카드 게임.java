import java.io.*;
import java.util.*;
public class Main {
    static int[] card;
    static int[] CardSum;
    static int[][] dp; //i~j까지 카드로 얻을 수 있는 최고점수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            card= new int[N+1];
            CardSum= new int[N+1];
            for (int j = 1; j <= N; j++) {
                card[j]=Integer.parseInt(st.nextToken());
                CardSum[j]=card[j]+CardSum[j-1];
            }
            dp= new int[N+1][N+1];
            bw.write(cal(1,N)+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    //카드가 i에서 j까지 남았을 때 얻을 수 있는 최고 점수
    static int cal(int i, int j) {
        if (dp[i][j]!=0){return dp[i][j];}
        int sum=0;
        if (i==j){return card[i];}
        else{
            sum=CardSum[j]-CardSum[i-1];
            return dp[i][j]=Math.max(sum-cal(i+1,j),sum-cal(i,j-1));
        }
    }
}