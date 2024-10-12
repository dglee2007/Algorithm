import java.io.*;
import java.util.*;

public class Main {
    static class Point{
        int x,y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static class Wood{
        int x,y,dir,dist;

        Wood(int x, int y, int dir, int dist){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.dist = dist;
        }
    }

    static int N;
    static char[][] map;
    static Point[] SW,EW;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        map = new char[N][N];

        SW = new Point[3]; //시작 통나무 위치
        EW = new Point[3]; //도착 위치

        int sIdx=0, eIdx=0;
        for(int i=0; i<N; i++){
            String s = br.readLine();
            for(int j=0; j<N; j++){
                map[i][j] = s.charAt(j);
                if (map[i][j]=='B') SW[sIdx++]=new Point(i,j);
                if (map[i][j]=='E') EW[eIdx++]=new Point(i,j);
            }
        }
        int ans = bfs();

        System.out.println(ans);
    }

    static int bfs() {
        Queue<Wood> q = new LinkedList<>();
        boolean[][][] visited = new boolean[2][N][N]; //가로, 세로 경우 나눠서 방문 확인

        //통나무 상태 확인
        int dir=0;

        if (SW[0].y + 1 == SW[1].y) dir=0; //가로 방향
        else dir=1; //세로 방향

        //통나무 중간을 기준으로 이동
        q.add(new Wood(SW[1].x,SW[1].y,dir,0));
        visited[dir][SW[1].x][SW[1].y]=true;

        while(!q.isEmpty()){
            Wood w = q.poll();
            int curX = w.x;
            int curY = w.y;
            int curDir = w.dir;
            int curDist = w.dist;

            //통나무의 중간이 목적지의 중간에 도달하면
            if (curX == EW[1].x && curY == EW[1].y) {
                //통나무가 가로 방향이면
                if (curDir == 0 && map[curX][curY-1] == 'E' && map[curX][curY+1] == 'E') return curDist;
                else if (curDir == 1 && map[curX-1][curY] == 'E' && map[curX+1][curY] == 'E') return curDist;
            }

            //상하좌우 탐색
            for (int i=0; i<4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                //통나무 이동 여부 확인
                if (!checkRange(curDir,nx,ny,i)) continue;
                //방문 여부 확인
                if (visited[curDir][nx][ny]) continue;
                visited[curDir][nx][ny] = true;
                q.add(new Wood(nx,ny,curDir,curDist+1));
            }

            //회전 가능 여부 확인
            if (canRotate(curX,curY)){
                //통나무가 가로일 때
                if (curDir==0 && !visited[1][curX][curY]){
                    visited[1][curX][curY]=true;
                    q.add(new Wood(curX,curY,1,curDist+1)); //방향 바꿔서 큐에 삽입
                }
                //통나무가 세로일 때
                if (curDir==1 && !visited[0][curX][curY]){
                    visited[0][curX][curY]=true;
                    q.add(new Wood(curX,curY,0,curDist+1)); //방향 바꿔서 큐에 삽입
                }
            }
        }
        return 0;
    }
    //옮길 수 있는 지 확인
    static boolean checkRange(int dir, int x, int y, int t) {
        if (dir==0){ // 가로 방향일 때 수평 방향으로 이동할 수 있는지 확인
            if (t<2){ //상하로 이동
                //통나무 끝이 범위를 넘어가면 false
                if (x<0 || x>=N) return false;
                //나무가 있을 경우 false
                if (map[x][y]=='1' || map[x][y-1]=='1' || map[x][y+1]=='1') return false;
            }
            else { //좌우로 이동
                //통나무 끝이 범위를 넘어가면 false
                if (y-1 <0 || y+1>=N) return false;
                //나무가 있을 경우 false
                if (map[x][y]=='1' || map[x][y-1]=='1' || map[x][y+1]=='1') return false;
            }
        }
        else if (dir==1){
            //상하로 이동
            if (t<2){
                //통나무 끝이 범위를 넘어가면 false
                if (x-1<0 || x+1 >= N) return false;
                // 나무가 있을 경우 false
                if (map[x][y]=='1' || map[x-1][y]=='1' || map[x+1][y]=='1' ) return false;
            }
            else { //좌우로 이동
                //통나무 끝이 범위를 넘어가면 false
                if (y <0 || y>=N) return false;
                // 나무가 있을 경우 false
                if (map[x][y]=='1' || map[x-1][y]=='1' || map[x+1][y]=='1' ) return false;
            }
        }
        return true;
    }
    // 회전시킬 수 있는지 확인하는 메소드
    static boolean canRotate(int x, int y) {
        for (int i=x-1;i<=x+1;i++){
            for (int j=y-1;j<=y+1;j++){
                //범위 나간 경우
                if (i<0 || i>=N || j<0 || j>=N) return false;
                //나무 있는 경우
                if (map[i][j]=='1') return false;
            }
        }
        return true;
    }
}
