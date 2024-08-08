import java.io.*;
import java.util.*;
public class Main {
	static int N;
	static int[][] arr;
	static int[][] dp; //i~j까지 최소 연산 회수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N=Integer.parseInt(br.readLine());
        
        arr=new int[N+1][2];
        dp=new int[N+1][N+1];
        
        for (int i=1;i<=N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
        	arr[i][0]=Integer.parseInt(st.nextToken());
        	arr[i][1]=Integer.parseInt(st.nextToken());
        }
        bw.write(cal(1,N)+"\n");
        bw.flush();
        bw.close();
        br.close();
	}
	//top-down
	static int cal(int i, int j) {
		int ret;
		if (i==j) {return 0;}
		ret = dp[i][j];
		if (ret==0) {
			ret = 987654321;
			for (int k=i;k<j;k++) {
				ret = Math.min(cal(i,k)+cal(k+1,j)+arr[i][0]*arr[k][1]*arr[j][1],ret);
			}
		}
		return dp[i][j]=ret;
	}
	//사선DP
	/*
	 static int cal(int i, int j){
	 	int ret;
	 	for (int i=1;i<N;i++){
	 		for (int j=1;j<N;j++){
	 			int s=j;
	 			int e=j+i;
	 			if (e>N){break;}
	 			ret=987654321;
	 			for (int k=s+1;k<=e;k++){
	 				ret=Math.min(dp[s][k-1]+dp[k][e]+arr[s][0]*arr[k][1]*arr[e][1],ret)
	 			}
	 			d[s][e]=ret;
	 		}
	 	}
	 	ans=dp[1][N];
	 }
	 
	 */

}
