import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int N, M;
    static int S, D;
    static int dist[];
    static ArrayList<Integer>[] route;

    static class Edge implements Comparable<Edge>{
        int e; int c;
        public Edge(int e, int c) { 
            this.e = e; 
            this.c = c;
        }
        @Override
        public int compareTo(Edge o) {
            return (this.c > o.c)?1:-1;
        }
    }

    static ArrayList<Edge>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N==0 && M==0) break;

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            adj = new ArrayList[N];
            for (int i=0; i<N; i++) adj[i] = new ArrayList<Edge>();

            int s, e, c;
            for (int m=0; m<M; m++) {
                st = new StringTokenizer(br.readLine());
                s = Integer.parseInt(st.nextToken());
                e = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());
                adj[s].add(new Edge(e, c));
            }

            // 최단경로 (역방향)
            route = new ArrayList[N];
            for (int i=0; i<N; i++) route[i] = new ArrayList<Integer>();

            dist = new int[N];
            Arrays.fill(dist, INF);

            dist[S] = 0;
            PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
            Edge now = new Edge(S, 0);
            pq.offer(now);
            Edge next;
            while (!pq.isEmpty()) {
                now = pq.poll();
                // if (visit[now.e] < now.c) continue;

                for (int i=0; i<adj[now.e].size(); i++) {
                    next = adj[now.e].get(i);

                    if (dist[next.e] > dist[now.e] + next.c) {
                        dist[next.e] = dist[now.e] + next.c;
                        pq.add(new Edge(next.e, dist[now.e] + next.c));

                        route[next.e].clear();
                        route[next.e].add(now.e);
                    } else if (dist[next.e] == dist[now.e] + next.c) {
                        route[next.e].add(now.e);
                    }
                }
            }

            // 경로 삭제
            removeRoute();

            //다시 다잌스트라를 구한다.
            Arrays.fill(dist, INF);            
            dist[S] = 0;

            pq.clear();
            now = new Edge(S, 0);
            pq.add(now);

            while (!pq.isEmpty()) {
                now = pq.poll();
                // if (visit[now.e] < now.c) continue;

                int size = adj[now.e].size();
                for (int i=0; i<size; i++) {
                    next = adj[now.e].get(i);

                    if (dist[next.e] > dist[now.e] + next.c) {
                        dist[next.e] = dist[now.e] + next.c;
                        pq.add(new Edge(next.e, dist[now.e] + next.c));
                    }
                }                
            }

            if (dist[D] == INF) {
                System.out.println(-1);
            } else {
                System.out.println(dist[D]);
            }
        }
    } // eof Main();

    public static void removeRoute() {
        Queue<Integer> q = new ArrayDeque();
        q.offer(D);
        boolean visited[] = new boolean[N];
        visited[D] = true;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i=0; i<route[cur].size(); i++) {
                int p = route[cur].get(i);
                for (int j=0; j<adj[p].size(); j++) {
                    if (adj[p].get(j).e == cur){
                        adj[p].remove(j);
                        break;
                    }
                }
                if (!visited[p]) {
                    q.offer(p);
                    visited[p] = true;
                }
            }
        }
    }
} // eof Class