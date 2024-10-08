import java.io.*;
import java.util.*;
public class Main{
    //항공 관련 생성자
    public static class airport implements Comparable<airport> {
        int point, cost, perid;

        public airport(int point, int cost, int perid) {
            this.point = point;		//도착 지점
            this.cost = cost;		//비용
            this.perid = perid;		//소요 기간
        }

        @Override
        public int compareTo(airport o) {
            return this.perid - o.perid;
        }

        public int getPoint() {
            return point;
        }

        public int getCost() {
            return cost;
        }

        public int getPerid() {
            return perid;
        }

    }
    public static ArrayList<ArrayList<airport>> graph = new ArrayList<>();	//그래프 값
    public static int[][] distance;		//최단 거리 저장 배열 distance[정점][비용]
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //입력값 처리하는 BufferedReader
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //결과값 출력하는 BufferedWriter
        //-----입력값 저장 및 그래프 초기화----
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++) {
            st = new StringTokenizer(br.readLine()," ");
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            for(int j=0;j<=N;j++)
                graph.add(new ArrayList<>());

            for(int j=0;j<K;j++) {
                st = new StringTokenizer(br.readLine()," ");
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                graph.get(u).add(new airport(v, c, d));		//그래프 값 저장
            }
            for(int j=0;j<N;j++){
                Collections.sort(graph.get(j));
            }

            distance = new int[N+1][M+1];		//최소 거리 저장 배열 초기화
            shortestPath(1, N, M);		//다익스트라 알고리즘 실행
            int result = Integer.MAX_VALUE/2;
            for(int j=0;j<=M;j++) {	//distance[N][0~최대비용] 중 최소경로 값 구하기
                result = Math.min(result, distance[N][j]);
            }
            if(result==Integer.MAX_VALUE/2)	//모든 값이 INF일 때
                bw.write("Poor KCM\n");
            else		//최소 경로 값 BufferedWriter 저장
                bw.write(result + "\n");

            graph.clear();		//그래프 초기화
        }
        bw.flush();		//결과 출력
        bw.close();
        br.close();
    }
    //BFS를 통한 다익스트라 알고리즘 수행 함수
    public static void shortestPath(int start, int end, int maxCost) {
        PriorityQueue<airport> queue = new PriorityQueue<>();

        //distance[][] INF로 초기화
        //저는 INF = Integer.MAX_VALUE / 2로 설정하였습니다.
        for(int i=1;i<=end;i++)
            Arrays.fill(distance[i], Integer.MAX_VALUE/2);
        //출발 정점 0으로 초기화
        distance[start][0] = 0;
        queue.add(new airport(start, 0, 0));
        while(!queue.isEmpty()) {
            airport temp = queue.poll();
            int point = temp.getPoint();
            int cost = temp.getCost();
            int perid = temp.getPerid();
    		/*
            	정점에 도착했을 때 최소 거리보다 크면
            	해당 정점에 간선 어디를 가도 최소 거리가 되지 않기 때문에
            	해당 탐색을 그대로 넘어갔습니다.
            	*/
            if(distance[point][cost] < perid)
                continue;

            for(airport value : graph.get(point)) {
                int nextCost = cost + value.getCost();
                int nextPerid = perid + value.getPerid();
                if(maxCost >= nextCost) {		//최대 비용 넘지 않았을 때
                    if(distance[value.getPoint()][nextCost] > nextPerid) {
                        distance[value.getPoint()][nextCost] = nextPerid;
                        queue.add(new airport(value.getPoint(), nextCost, nextPerid));
                    }
                }
            }
        }
    }
}