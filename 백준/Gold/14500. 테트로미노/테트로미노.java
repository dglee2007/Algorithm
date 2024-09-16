import java.util.*;
import java.io.*;

public class Main {
    static int n,m;
    static int max=Integer.MIN_VALUE;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < m; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                visited[y][x] = true;
                dfs(y,x,1,map[y][x]);
                visited[y][x] = false;

                combi(0,0,y,x,map[y][x]);
            }
        }

        System.out.println(max);

        br.close();
    }

    private static void dfs(int y, int x, int cnt, int sum) {
        if (cnt==4){
            max=Math.max(max,sum);
            return;
        }

        for (int d=0;d<4;d++){
            int ny = y+dy[d];
            int nx = x+dx[d];

            if (ny<0||ny>=n||nx<0||nx>=m) continue;
            if (visited[ny][nx]) continue;
            visited[ny][nx] = true;
            dfs(ny,nx,cnt+1,sum+map[ny][nx]);
            visited[ny][nx] = false;
        }
    }

    private static void combi(int cnt, int start, int y, int x, int sum) {
        if (cnt==3){
            max=Math.max(max,sum);
            return;
        }
        for (int d=start;d<4;d++){
            int ny = y+dy[d];
            int nx = x+dx[d];

            if (ny<0||ny>=n||nx<0||nx>=m) continue;
            combi(cnt+1,d+1,y,x,sum+map[ny][nx]);
        }
    }
}
