import java.util.*;
import java.io.*;

public class Main {

    static class Pos {
        int x,y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static int[][] map; //입력 받은 원본 배열
    static int[][] dist; //여러 물고기가 있을 때 거리 체크 배열
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static int size = 2; // 상어 사이즈
    static int eat = 0; // 상어가 물고기 먹은 횟수
    static int cnt = 0; // 상어가 이동한 횟수
    static int sharkX = -1; // 상어의 X 좌표
    static int sharkY = -1; // 상어의 Y 좌표
    static int minX, minY, minDist; // while문 탈출 조건으로 쓸 변수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());

                if (map[i][j]==9){
                    sharkX=i;
                    sharkY=j;
                    map[i][j] = 0; //상어가 있었던 위치도 이 후로 탐색해야 하기 떄문에 0으로 초기화
                }
            }
        }

        while (true){
            dist = new int[N][N]; // 거리 알아낼 배열 && 자동으로 방문 처리 됨
            minX = Integer.MAX_VALUE; // 상어에서 가장 가까이 있는 물고기 X좌표
            minY = Integer.MAX_VALUE; // 상어에서 가장 가까이 있는 물고기 Y좌표
            minDist = Integer.MAX_VALUE; // 상어에서 가장 가까이 있는 물고기 거리

            bfs(sharkX,sharkY); //가장 가까이 있는 먹을 수 있는 물고기 먹으로 가는 함수

            //먹을 수 있는 물고기의 위치로 이동했다면
            if (minX!=Integer.MAX_VALUE && minY!=Integer.MAX_VALUE){
                eat++; //상어가 먹은 물고기 횟수
                map[minX][minY]=0; //물고기를 먹었으므로 해당 위츠는 0으로 갱신
                sharkX = minX; // 먹은 물고기의 X좌표가 현재 상어의 X좌표
                sharkY = minY; // 먹은 물고기의 Y좌표가 현재 상어의 Y좌표
                cnt += dist[minX][minY]; //상어가 이동한 횟수는 dist배열값을 더한 값

                //물고기를 먹은 횟수가 현재 상어의 사이즈와 같다면
                if (eat==size){
                    size++; //상어의 사이즈 +1
                    eat=0; //물고기 먹은 횟수 0으로 갱신
                }
            }
            else break; //minX, minY가 초기값과 같으면 더 이상 먹을 물고기 없음
        }
        System.out.println(cnt);
    }

    static void bfs(int x,int y){
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(x,y));

        while (!q.isEmpty()){
            Pos cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;

            for (int i=0;i<4;i++){
                int nx = curX+dx[i];
                int ny = curY+dy[i];

                // 새롭게 탐색한 위치가 범위 안 && 그 위치가 탐색할 수 있는 위치 && 아직 방문한 적 없는 위치 라면?
                if (isArea(nx,ny) && isAblePos(nx,ny) && dist[nx][ny]==0){
                    dist[nx][ny]=dist[curX][curY]+1;

                    //해당 위치에 상어보다 작은 물고기가 있다면
                    if (isEat(nx,ny)){
                        //탐색하는 위치에 있는 물고기의 거리가 가장 가까운지 확인
                        if (minDist>dist[nx][ny]){
                            //갱신
                            minDist=dist[nx][ny];
                            minX=nx;
                            minY=ny;
                        }
                        //거리가 같다면 더 위에 있는 물고기 선택
                        else if (minDist==dist[nx][ny]){
                            //그것마저 같다면 가장 왼쪽에 있는 물고기 선택
                            if (minX==nx){
                                if (minY>ny){
                                    minX=nx;
                                    minY=ny;
                                }
                            }
                            else if (minX>nx){
                                minX=nx;
                                minY=ny;
                            }
                        }
                    }
                    q.add(new Pos(nx,ny));
                }
            }
        }
    }

    //map 범위 안에 있는지 판별
    static boolean isArea(int x,int y){
        return x>=0 && x<N && y>=0 && y<N;
    }

    //상어가 먹을 수 있는 물고기 판별 -> 상어 사이즈보다 작아야 함
    static boolean isEat(int x,int y){
        return map[x][y]!=0 && map[x][y]<size;
    }

    //상어가 해당 위치로 이동 가능한지 판별 -> 해당 위치가 비어 있거나(0) 물고기가 있다면 상어보다 크기가 작아햐함
    static boolean isAblePos(int x,int y){
        return map[x][y]<=size;
    }
}
