import java.io.*;
import java.util.*;

public class Main {
    static int dp[];
    static int cost[][];
    static int N,P;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        cost=new int[N][N];

        for (int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++){
                cost[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        //dp배열을 position 상태인 2^N 크기로 만든다
        //2^N의 포지션일 때 최소비용으로 고치는 DP
        dp=new int[1<<N];
        Arrays.fill(dp,-1);

        int pos=0; //현재 발전소 작동 상태 (ex. YNN -> 001)
        int cnt=0; //현재 작동하는 발전소 개수

        String str= br.readLine();
        for (int i=0;i<N;i++){
            if (str.charAt(i)=='Y'){
                pos= pos | (1<<i);
                cnt++;
            }
        }

        P = Integer.parseInt(br.readLine());

        int ans=dfs(cnt,pos);
        System.out.println(ans==Integer.MAX_VALUE ? -1 : ans);
    }

    static int dfs(int cnt,int pos){
        if (cnt>=P) return 0;
        if (dp[pos]!=-1) return dp[pos];

        dp[pos]=Integer.MAX_VALUE;


        for (int i=0;i<N;i++){
            //i번째 발전소가 정상 작동하는 경우
            if ( (pos & (1<<i)) !=0){
                for (int j=0;j<N;j++){
                    //j번째 발전소가 고장난 경우
                    if ((pos & (1<<j)) ==0){
                        dp[pos]=Math.min(dp[pos],dfs(cnt+1, pos | (1<<j))+cost[i][j]);
                    }
                }
            }
        }
        return dp[pos];
    }
}
