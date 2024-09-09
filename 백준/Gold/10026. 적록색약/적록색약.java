import java.io.*;
import java.util.*;

public class Main {
    static int[] dx= {0,1,0,-1};
    static int[] dy= {-1,0,1,0};
    static char[][] map;
    static boolean[][] visited;
    static Queue<int[]> q= new LinkedList<>();
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n=Integer.parseInt(br.readLine());
        map = new char[n][n];
        visited = new boolean[n][n];

        for (int i=0;i<n;i++) {
            String s = br.readLine();
            for (int j=0;j<n;j++) {
                map[i][j] = s.charAt(j);
            }
        }

        int cnt=0;

        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (!visited[i][j]) {
                    cnt++;
                    bfs(i,j);
                }
            }
        }

        bw.write(cnt + " ");
        cnt=0;
        visited=new boolean[n][n];

        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (!visited[i][j]) {
                    cnt++;
                    bfs(i,j);
                }
            }
        }
        bw.write(cnt + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
    public static void bfs(int x, int y) {
        q.offer(new int[]{x,y});
        visited[x][y] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];

            for (int i=0;i<4;i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (visited[nx][ny] || map[cx][cy]!=map[nx][ny]) continue;
                visited[nx][ny] = true;
                q.offer(new int[]{nx,ny});
            }

            if (map[cx][cy]=='G') map[cx][cy]='R';
        }
    }
}
