
import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 100000001;
    static int N, M, K;

    static class Edge implements Comparable<Edge> {
        int e;
        int c;

        Edge(int e, int c) {
            this.e = e;
            this.c = c;
        }

        @Override
        public int compareTo(Edge o) {
            return this.c < o.c ? -1 : 1;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ArrayList<Edge> adj[] = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {adj[i] = new ArrayList<Edge>();}

        int s,e,c;
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            adj[s].add(new Edge(e,c));
        }

        PriorityQueue<Integer>[] dist = new PriorityQueue[N+1];
        Comparator<Integer> cp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1>o2 ? -1:1;
            }
        };

        for (int i=0;i<=N;i++) {
            dist[i] = new PriorityQueue<Integer>(K,cp);
        }

        dist[1].add(0);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1,0));

        Edge now,next;
        while (!pq.isEmpty()) {
            now = pq.poll();
            for (int i=0;i<adj[now.e].size();i++) {
                next = adj[now.e].get(i);
                if (dist[next.e].size()<K){
                    // 저장된 경로가 K개가 안될 경우 그냥 추가한다.
                    dist[next.e].add(now.c+next.c);
                    pq.add(new Edge(next.e,now.c+next.c));
                }
                else if (dist[next.e].peek()>now.c+next.c){
                    // 저장된 경로가 K개이고, 현재 가장 큰 값보다 작다면
                    dist[next.e].poll();
                    dist[next.e].add(now.c+next.c);
                    pq.add(new Edge(next.e,now.c+next.c));
                }
            }
        }

        for (int i=1;i<=N;i++) {
            if (dist[i].size()==K){
                bw.write(dist[i].peek()+"\n");
            }
            else {
                bw.write(-1+"\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
