import java.io.*;
public class Main {
	static int[][] map;
	static String seq1, seq2;
	static int len1, len2, ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        seq1= br.readLine();
        seq2= br.readLine();
        len1=seq1.length();
        len2=seq2.length();
        ans=0;
        map=new int[len1+1][len2+1];
        for (int i=1;i<=len1;i++) {
        	for (int j=1;j<=len2;j++) {
        		if (seq1.charAt(i-1)==seq2.charAt(j-1)) {
        			map[i][j]=map[i-1][j-1]+1;
        			ans=Math.max(map[i][j], ans);
        		}
        	}
        }
        bw.write(ans+"\n");
        bw.flush();
        bw.close();
        br.close();   
	}
}
