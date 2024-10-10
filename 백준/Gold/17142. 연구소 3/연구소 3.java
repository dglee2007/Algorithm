import java.io.*;
import java.util.*;

public class Main {
    public static int n,m,ans,zero_cnt;
    public static int[][] map;
    public static int[] dx = {-1,1,0,0};
    public static int[] dy = {0,0,-1,1};
    public static class Node {
        int x;
        int y;
        int dis;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
        public Node(int x, int y, int dis){
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }
    public static ArrayList<Node> virus;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        ans= Integer.MAX_VALUE;
        virus = new ArrayList<>();
        zero_cnt = 0; // 빈 칸(0)의 개수

        // 연구소 정보 입력 및 바이러스 위치 저장
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) virus.add(new Node(i, j)); // 비활성 바이러스 위치 저장
                else if (map[i][j] == 0) zero_cnt++; // 빈 칸 개수
            }
        }

        // DFS로 바이러스 M개를 선택하여 최소 시간 계산
        dfs(0,0,new int[m]);

        // 결과 출력
        if (ans == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);
    }

    // DFS로 M개의 바이러스 선택
    public static void dfs(int depth,int start,int[] arr){
        if (depth == m){
            int time = bfs(arr);
            if (time != -1) ans = Math.min(ans, time);
            return;
        }
        for (int i = start; i < virus.size(); i++){
            arr[depth] = i;
            dfs(depth + 1, i + 1, arr);
        }
    }

    // BFS로 바이러스 확산 시뮬레이션
    public static int bfs(int[] arr){
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        int infectedCnt = 0; // 감염된 빈 칸 개수

        // 선택된 M개의 바이러스를 큐에 삽입
        for (int a : arr) {
            Node node = virus.get(a);
            visited[node.x][node.y] = true;
            q.add(new Node(node.x, node.y, 0)); // 초기 바이러스 위치, 확산 시간 0
        }

        int time = 0;

        // BFS로 바이러스 확산
        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = node.x + dx[d];
                int ny = node.y + dy[d];

                // 연구소 범위를 벗어나지 않고, 아직 방문하지 않았으며, 벽이 아닌 경우
                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny]) {
                    if (map[nx][ny] == 0) { // 빈 칸인 경우 바이러스 확산
                        visited[nx][ny] = true;
                        infectedCnt++;
                        time = node.dis + 1;
                        q.add(new Node(nx, ny, time));
                    } else if (map[nx][ny] == 2) { // 비활성 바이러스인 경우
                        visited[nx][ny] = true; // 활성화된 것으로 처리
                        q.add(new Node(nx, ny, node.dis + 1));
                    }
                }
            }
        }

        // 모든 빈 칸을 감염시키지 못한 경우
        if (infectedCnt != zero_cnt) return -1;
        return time;
    }
}
