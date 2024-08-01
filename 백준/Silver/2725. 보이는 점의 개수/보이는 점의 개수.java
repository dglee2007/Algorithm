import java.io.*;
//import java.util.*;

public class Main {
	static int C,N;
	static int[] ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        C=Integer.parseInt(br.readLine());
        
        ans=new int[1002];
        ans[0]=0;
        ans[1]=3;
        
        for (int i=2;i<=1000;i++) {
        	int now_ans=0;
        	for (int j=0;j<i;j++) {
        		if (gcd(i,j)==1) {now_ans++;}
        	}
        	ans[i]=ans[i-1]+2*now_ans;
        }
        
        for (int i=0;i<C;i++) {
        	N=Integer.parseInt(br.readLine());
        	bw.write(ans[N]+"\n");
        	
        } 
        bw.flush();
	}
	
	private static int gcd(int a, int b) {
		if (b==0) {return a;}
		else {return gcd(b,a%b);}
	}

}
