import java.util.*;
import java.io.*;

public class Main {

    public static int n,q;
    public static int[][] map;
    public static int[] dx = {-1,1,0,0};
    public static int[] dy = {0,0,-1,1};
    public static int land, totalIce;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        n = (int) Math.pow(2,n);
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int[] L = new int[q];
        for (int i = 0; i < q; i++) {
            L[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < q; i++) {
            map = divide(L[i]); // 회전
            map = melt(); // 얼음 녹이기
        }

        land = totalIce =0;

        biggest();
        System.out.println(totalIce);
        System.out.println(land);
    }

    public static int[][] divide(int L){
        int[][] tmp = new int[n][n];
        L = (int) Math.pow(2,L);
        for (int i = 0; i < n; i+=L) {
            for (int j=0; j<n; j+=L){
                rotate(i,j,L,tmp);
            }
        }
        return tmp;
    }

    public static void rotate(int x, int y, int L, int[][] tmp) {
        for (int i=0; i<L;i++){
            for (int j=0; j<L; j++){
                tmp[x+i][y+j]=map[x+L-1-j][y+i];
            }
        }
    }

    public static int[][] melt(){
        int[][] tmp = new int[n][n];
        for (int i = 0; i < n; i++) {
            tmp[i] = Arrays.copyOf(map[i], n);
        }

        for (int i = 0; i < n; i++) {
            for (int j=0; j<n; j++){
                int cnt=0;
                if (map[i][j]==0) continue;
                for (int k=0; k<4; k++){
                    int nx = i+dx[k];
                    int ny = j+dy[k];
                    if (isRange(nx,ny)){
                        if (map[nx][ny]>0) cnt++;
                    }
                }
                if (cnt<3) tmp[i][j]--;
            }
        }
        return tmp;
    }

    public static void biggest(){
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visit = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                totalIce += map[i][j];
                if (map[i][j]>0 && !visit[i][j]){
                    q.add(new int[]{i,j});
                    visit[i][j] = true;
                    int cnt=1;

                    while (!q.isEmpty()){
                        int[] t = q.poll();
                        int tx = t[0];
                        int ty = t[1];

                        for (int k=0; k<4; k++){
                            int nx = tx+dx[k];
                            int ny = ty+dy[k];
                            if (isRange(nx,ny)){
                                if (map[nx][ny]>0 && !visit[nx][ny]){
                                    visit[nx][ny] = true;
                                    q.add(new int[]{nx,ny});
                                    cnt++;
                                }
                            }
                        }
                    }
                    land = Math.max(cnt,land);
                }
            }
        }
    }
    public static boolean isRange(int x, int y){
        return x>=0 && x<n && y>=0 && y<n;
    }
}
