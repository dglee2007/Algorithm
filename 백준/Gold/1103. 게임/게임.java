import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main {
	static int n,m,max;
	static boolean isCycle = false;
	static int[][] dp;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.valueOf(st.nextToken());
        m = Integer.valueOf(st.nextToken());
        dp = new int[n][m];
        map = new char[n][m];
        visited = new boolean[n][m];
        
        for (int i=0;i<n;i++) {
        	String line = br.readLine();
        	map[i] = line.toCharArray();
        }
        
        visited[0][0] = true;
        dfs(0,0,1);
        if (isCycle) {
        	System.out.println("-1");
        }
        else {
        	System.out.println(max);
        }
	}
	//아스키로 3의 값 구하기 '3'-'0'
	static void dfs(int x, int y, int moveCount) {
		int moveSquareCount = Character.getNumericValue(map[y][x]);
		dp[y][x]=moveCount;
		if (moveCount > max) {
			max=moveCount;
		}
		
		for (int i=0;i<dx.length;i++) {
			int nx=x+( moveSquareCount * dx[i] );
			int ny=y+( moveSquareCount * dy[i] );
			
			if (nx<0 ||nx>=m || ny<0 || ny>=n ) {
				continue;
			}
			
			if (map[ny][nx]=='H') {
				continue;
			}
			
			if (moveCount<dp[ny][nx]) {
				continue;
			}
			
			if (visited[ny][nx]) {
				isCycle=true;
				return;
			}
			
			visited[ny][nx]=true;
			dfs(nx,ny,moveCount+1);
			visited[ny][nx]=false;
		}
	}	
}
