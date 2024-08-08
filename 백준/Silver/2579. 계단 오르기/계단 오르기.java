import java.io.*;
import java.util.*;

public class Main {
	static int[] arr,dp;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        if (N==1) {System.out.println(br.readLine()) ;}
        else {
        	arr= new int[N+1];
            dp= new int[N+1];
            for (int i=1;i<=N;i++) {
            	arr[i]=Integer.parseInt(br.readLine());
            }
            dp[1]=arr[1];
            dp[2]=arr[1]+arr[2];
            for (int i=3;i<=N;i++) {
            	dp[i]=Math.max(arr[i]+dp[i-2], arr[i]+arr[i-1]+dp[i-3]);
            }
            bw.write(dp[N]+"\n");
            bw.flush();
            bw.close();
            br.close();
        }
        
	}
}
