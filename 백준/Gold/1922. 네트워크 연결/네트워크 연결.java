import java.io.*;
import java.util.*;

public class Main {
	static class Edge implements Comparable<Edge> {
		int s;
		int e;
		int c;
		
		public Edge(int s,int e, int c) {
			this.s=s;
			this.e=e;
			this.c=c;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.c - o.c;
		}
	}

	static int N,M;
	static int parent[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N =Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M =Integer.parseInt(st.nextToken());
        
        int s,e,c;
        Edge edge[] = new Edge[M];
        for (int i=0;i<M;i++) {
        	st=new StringTokenizer(br.readLine());
        	s=Integer.parseInt(st.nextToken());
        	e=Integer.parseInt(st.nextToken());
        	c=Integer.parseInt(st.nextToken());
        	edge[i] = new Edge(s,e,c);
        }
        
        parent = new int[N+1];
        for (int i=1;i<N;i++) {parent[i]=i;}
        
        Arrays.sort(edge);
        
        long ans=0;
        for (int i=0;i<M;i++) {
        	if(find(edge[i].s)!=find(edge[i].e)) {
        		union(edge[i].s,edge[i].e);
        		ans+=edge[i].c;
        	}
        }
        bw.write(ans+"\n");
        bw.flush();
        bw.close();
        br.close();
	}

    private static void union(int a, int b) {
        int x = find(parent[a]);
        int y = find(parent[b]);

        if (x<y) parent[y] = x;
        else if (x>y) parent[x] = y;
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

}
