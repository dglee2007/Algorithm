import java.io.*;

public class Main {
	static int[][] map;
	static String seq1, seq2;
	static StringBuilder ans;
	static int len1, len2;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        seq1= br.readLine();
        seq2= br.readLine();
        len1=seq1.length();
        len2=seq2.length();
        map=new int[len1+1][len2+1];
        ans=new StringBuilder();
        for (int i=1;i<=len1;i++) {
        	for (int j=1;j<=len2;j++) {
        		if (seq1.charAt(i-1)==seq2.charAt(j-1)) {
        			map[i][j]=map[i-1][j-1]+1;
        		}
        		else {
        			map[i][j]=Math.max(map[i-1][j], map[i][j-1]);
        		}
        	}
        }
        
        getRoute(len1,len2);
        
        bw.write(map[len1][len2]+"\n");
        if (ans.length()>0) {bw.write(ans.reverse().toString()+"\n");}
        bw.flush();
        bw.close();
        br.close(); 
	}
	public static void getRoute(int r, int c) {
		if (r==0 || c==0) {return;}
		if (seq1.charAt(r-1)==seq2.charAt(c-1)) {
			ans.append(seq1.charAt(r-1));
			getRoute(r-1,c-1);
		}
		else {
			if (map[r-1][c]>map[r][c-1]) {getRoute(r-1,c);}
			else {getRoute(r,c-1);}
		}
	}

}
