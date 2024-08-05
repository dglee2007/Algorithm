import java.io.*;
import java.util.*;

public class Main {
	static int N,M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N =Integer.parseInt(st.nextToken());
        M =Integer.parseInt(st.nextToken());
        
        List<Integer> adj[]=new ArrayList[N+1];
        int indegree[] = new int[N+1];
        
        for (int i=1;i<=N;i++) {
        	adj[i] = new ArrayList();
        }
        
        int s,e;
        
        for (int i=0;i<M;i++) {
        	st=new StringTokenizer(br.readLine());
        	s=Integer.parseInt(st.nextToken());
        	e=Integer.parseInt(st.nextToken());
        	
        	adj[s].add(e);
        	indegree[e]++;
        }
        
        Queue<Integer> q= new ArrayDeque<>();
        
        for (int i=1;i<=N;i++) {
        	if (indegree[i]==0) {
        		q.offer(i);
        		bw.write(i+" ");
        	}
        }
        
        while(!q.isEmpty()) {
        	int zeroNode=q.poll();
        	for (int node : adj[zeroNode]) {
        		indegree[node]--;
        		if (indegree[node]==0) {
        			q.offer(node);
        			bw.write(node+" ");
        		}
        	}
        }
        
        bw.flush();
        bw.close();
        br.close();
	}

}
