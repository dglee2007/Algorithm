import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main {
	static int[] map;
	static int ans=0;
	static int N;
		
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N=Integer.parseInt(br.readLine());
        
        map=new int[N];
        dfs(0);
        System.out.println(ans);
	}
	
	public static void dfs(int depth) {
		if (depth==N) {
			ans++;
			return;
		}
		
		for (int i=0; i<N;i++) {
			map[depth]=i;
			if (is_promising(depth)) {
				dfs(depth+1);
			}
		}
	}
	
	public static boolean is_promising(int col) {
		for (int i=0; i<col;i++) {
			if (map[i]==map[col]) {
				return false;
			}
			
			else if (Math.abs(col-i)==Math.abs(map[col]-map[i])) {
				return false;
			}
		}
		return true;
	}

}
