import java.util.*;
import java.io.*;

public class Main {
    static int R,C,T;
    static int[][] map;
    static int[] up = {1,-1,0,0}, side = {0,0,1,-1};
    static int airPos1, airPos2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        int num = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        findAir(); //공기청정기 위치를 찾아서 airPos1, airpos2에서 넣어준다
        for (int i = 0; i < T; i++) {
            solve();
        }

        int result = count(); //map의 배열에 남아있는 먼지의 양을 계산해준다

        System.out.println(result+2); // count()에서 공기청정기 값으로 1을 2번 빼줬기 떄문에 2 더함
    }

    public static void findAir() {
        for (int i = 0; i < R; i++) {
            if (map[i][0]==-1){
                airPos1 = i;
                airPos2 = i+1;
                break;
            }
        }
    }

    public static void solve() {
        map=dustSimulation(); //먼지가 퍼져나가는 것을 구하는 함수
        airSimulation(); // 공기청정기로 먼지가 들어오며 나가는 것을 구하는 함수
    }

    public static void airSimulation() {
        int top = airPos1; //공기청정기 윗 부분좌표이며, 반시계 방향 진행

        for (int x=top-1; x>0; x--){
            map[x][0]=map[x-1][0];
        }

        for (int y=0; y<C-1; y++){
            map[0][y]=map[0][y+1];
        }

        for (int x=0; x<top; x++){
            map[x][C-1] = map[x+1][C-1];
        }

        for (int y=C-1; y>1; y--){
            map[top][y] = map[top][y-1];
        }

        map[top][1]=0; //공기청정기로 나가는 곳이므로 먼지는 0이다

        int bottom = airPos2; //공기청정기 밑 부분좌표, 시계방향 진행

        for (int x = bottom + 1; x < R - 1; x++) {
            map[x][0] = map[x + 1][0];
        }

        for (int y = 0; y < C - 1; y++) {
            map[R - 1][y] = map[R - 1][y + 1];
        }

        for (int x = R - 1; x > bottom; x--) {
            map[x][C - 1] = map[x - 1][C - 1];
        }

        for (int y = C - 1; y > 1; y--) {
            map[bottom][y] = map[bottom][y - 1];
        }

        map[bottom][1] = 0; // 공기청정기가 나나는 곳이므로, 먼지는 0이다
    }

    public static int[][] dustSimulation() {
        int[][] tMap = new int[50][50];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j]==-1){
                    tMap[i][j]=-1;
                    continue;
                }
                tMap[i][j] += map[i][j];
                for (int k=0; k<4; k++){
                    int nx = j + side[k];
                    int ny = i + up[k];

                    if (ny<0 || ny>=R || nx<0 || nx>=C) continue;
                    if (map[ny][nx]==-1) continue;

                    tMap[ny][nx] += (map[i][j]/5);
                    tMap[i][j]-=(map[i][j]/5);
                }
            }
        }
        return tMap;
    }

    public static int count() {
        int temp = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                temp+=map[i][j];
            }
        }
        return temp;
    }
}
