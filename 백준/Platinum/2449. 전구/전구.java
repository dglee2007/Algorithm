import java.io.*;
import java.util.*;

public class Main {
	static int N,K;
	static int[][] dp;
	static int[] arr;
	static int INF=987654321;
	static boolean[][] visit;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st=new StringTokenizer(br.readLine());
        arr=new int[N+1];
        dp=new int[N+1][N+1];
        visit=new boolean[N+1][N+1];
        for (boolean a[]:visit) {Arrays.fill(a, false);}
        
        for (int i=1;i<=N;i++) {
        	arr[i]=Integer.parseInt(st.nextToken());
        }
        
        bw.write(cal(1,N)+"\n");
        bw.flush();
        bw.close();
        br.close();
        
        
	}
	static int cal(int i, int j){
		int ret;
		if (i==j) {return 0;}
		if (visit[i][j]) {return dp[i][j];}
		
		ret=INF;
		for (int k=i;k<j;k++) {
			ret=Math.min(ret, cal(i,k)+cal(k+1,j)+(arr[i]==arr[k+1]?0:1));
		}
		visit[i][j]=true;
		return dp[i][j]=ret;
	 }
}
