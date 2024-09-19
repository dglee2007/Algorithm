import java.io.*;
import java.util.*;

public class Main {

    static class virus {
        int x,y;
        public virus(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int N,M;
    public static int[] dx = {1,-1,0,0};
    public static int[] dy = {0,0,1,-1};
    public static int[][] map,copyMap;

    public static Queue<virus> queue = new LinkedList<virus>();
    public static int maxSafetyRoom = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);

        System.out.println(maxSafetyRoom);
    }

    public static void dfs(int wallCnt) {
        if (wallCnt == 3) {
            bfs();
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(wallCnt + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    public static void bfs() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2) {
                    queue.add(new virus(i, j));
                }
            }
        }

        copyMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            copyMap[i] = map[i].clone();
        }

        while (!queue.isEmpty()) {
            virus v = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = v.x + dx[i];
                int ny = v.y + dy[i];

                if(0 <= nx && nx <N && 0<= ny && ny <M){
                    if (copyMap[nx][ny] == 0) {
                        copyMap[nx][ny] = 2;
                        queue.add(new virus(nx, ny));
                    }
                }
            }
        }
        cntSafeZone(copyMap);
    }

    private static void cntSafeZone(int[][] copyMap) {
        int safeZoneCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 0) {
                    safeZoneCnt++;
                }
            }
        }
        maxSafetyRoom = Math.max(maxSafetyRoom, safeZoneCnt);
    }

}
