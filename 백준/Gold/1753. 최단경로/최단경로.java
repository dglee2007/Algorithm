
import java.io.*;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int node,weight;
        public Edge(int node, int weight){
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o){
            return this.weight <o.weight ? -1 :1;
        }
    }

    static int V,E,K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V=Integer.parseInt(st.nextToken());
        E=Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        ArrayList<Edge> adj[] = new ArrayList[V+1];
        for (int i=0;i<=V;i++){
            adj[i]=new ArrayList<Edge>();
        }
        int u,v,w;
        for (int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine());
            u=Integer.parseInt(st.nextToken());
            v=Integer.parseInt(st.nextToken());
            w=Integer.parseInt(st.nextToken());
            adj[u].add(new Edge(v,w));
        }

        int dist[] = new int[V+1];
        Arrays.fill(dist,Integer.MAX_VALUE);

        dist[K]=0;

        PriorityQueue<Edge> pq= new PriorityQueue<Edge>();
        pq.add(new Edge(K,0));

        Edge now,next;
        while (!pq.isEmpty()){
            now=pq.poll();
            if (dist[now.node]<now.weight){
                continue;
            }

            for (int i=0;i<adj[now.node].size();i++){
                next = adj[now.node].get(i);
                if (dist[next.node]>now.weight+next.weight){
                    dist[next.node]=now.weight+next.weight;
                    pq.add(new Edge(next.node,now.weight+next.weight));
                }
            }
        }

        for (int i=1;i<dist.length;i++){
            if (K==i){
                bw.write("0\n");
            }
            else if (dist[i]==Integer.MAX_VALUE){
                bw.write("INF\n");
            }
            else {
                bw.write(dist[i]+"\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}