import java.io.*;
import java.util.*;

public class Main {
    static int[] dice = new int[7];
    static int n,m,x,y;
    static int[][] map;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        int k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int d = Integer.parseInt(st.nextToken());
            move(d);
        }
    }
    static void move(int d) {
        int nx = x + dx[d-1];
        int ny = y + dy[d-1];
        if (nx<0 || ny<0 || nx>m-1 || ny>n-1) return;
        int tmp=dice[3];
        if (d==1){
            dice[3]=dice[4];
            dice[4]=dice[6];
            dice[6]=dice[2];
            dice[2]=tmp;
        }
        else if (d==2){
            dice[3]=dice[2];
            dice[2]=dice[6];
            dice[6]=dice[4];
            dice[4]=tmp;
        }
        else if (d==3){
            dice[3]=dice[5];
            dice[5]=dice[6];
            dice[6]=dice[1];
            dice[1]=tmp;
        }
        else if (d==4){
            dice[3]=dice[1];
            dice[1]=dice[6];
            dice[6]=dice[5];
            dice[5]=tmp;
        }

        if (map[ny][nx]==0){
            map[ny][nx]=dice[6];
        }
        else {
            dice[6]=map[ny][nx];
            map[ny][nx]=0;
        }
        System.out.println(dice[3]);
        x=nx;y=ny;
    }
}
