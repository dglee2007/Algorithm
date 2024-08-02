import java.io.*;
import java.util.*;

public class Main {
	static int[] arr;
	static int[] LIS;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N=Integer.parseInt(br.readLine());
        arr=new int[N];
        LIS=new int[N];
        LIS[0]=1;
        
        StringTokenizer st=new StringTokenizer(br.readLine());
        
        for (int i=0;i<N;i++) {
        	arr[i]=Integer.parseInt(st.nextToken());
        }
        
        for (int i=1;i<N;i++) {
        	int num=1;
        	for (int j=0;j<i;j++) {
        		if (arr[i]>arr[j]) {
        			num=Math.max(num, LIS[j]+1);
        		}
        	}
        	LIS[i]=num;
        }
        
        Arrays.sort(LIS);
        bw.write(LIS[arr.length-1]+"\n");
        bw.flush();
        
        
	}

}
