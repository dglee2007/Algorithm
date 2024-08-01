import java.io.*;
import java.util.*;
public class Main {
	
	static int[] arr;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N=Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        
        
        int[] arr=new int[N+2];
        
        for (int i=1;i<=N;i++) {
        	arr[i]=Integer.parseInt(st.nextToken());
        }
        
        int[] lgcd=new int[N+2];
        for (int i=1;i<=N;i++) {
        	lgcd[i]=gcd(arr[i],lgcd[i-1]);
        }
        
        int[] rgcd=new int[N+2];
        for (int i=N;i>0;i--) {
        	rgcd[i]=gcd(arr[i],rgcd[i+1]);
        }
        
        int ans=-1;
        int gcd=lgcd[N];
        for (int i=1;i<=N;i++) {
        	int partial_gcd=gcd(lgcd[i-1],rgcd[i+1]);
        	if (partial_gcd>gcd) {
        		if (arr[i]%partial_gcd!=0) {
        			ans=arr[i];
            		gcd=partial_gcd;	
        		}
        		
        	}
        }
        
        if (ans==-1) {
        	System.out.println(-1);
        }
        else {
        	bw.write(gcd + " " + ans+"\n");
        	bw.flush();
        	bw.close();
        }
        
        
        
        
        
        
	}
	
	public static int gcd(int a, int b) {
		if (b==0) {return a;}
		else {return gcd(b,a%b);}
	}

}
