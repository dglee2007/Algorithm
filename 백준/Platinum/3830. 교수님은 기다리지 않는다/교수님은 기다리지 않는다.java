import java.io.*;
import java.util.*;
public class Main {
	
	static int[] parent;
	static int[] weight;
	
	static int N,M;
	static String order;
	static int a,b,w;
	
	static void union(int a, int b, int w) {
		int x = find(a);
		int y = find(b);
		
		if (x<y) {
			int temp = weight[a] + w;
            weight[y] = temp - weight[b];
            parent[y] = x;
		}
		else if (x>y) {
			int temp = weight[b] - w;
            weight[x] = temp - weight[a];
            parent[x] = y;
		}
	}
	
	static int find(int x) {
		if (parent[x]==x) {return x;}
		int parentId=find(parent[x]);
		weight[x]+=weight[parent[x]];
		parent[x]=parentId;
		return parentId;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        while (true) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
            
            N =Integer.parseInt(st.nextToken());
            M =Integer.parseInt(st.nextToken());
            
            if (N==0) {break;}
            
            parent=new int[N+1];
            weight=new int[N+1];
            for (int i=1;i<=N;++i) {
            	parent[i]=i;
            }
            
            for (int i=0;i<M;i++) {
            	st = new StringTokenizer(br.readLine());
            	order= st.nextToken();
            	a=Integer.parseInt(st.nextToken());
            	b=Integer.parseInt(st.nextToken());
            	
            	if ("!".equals(order)) {
            		w=Integer.parseInt(st.nextToken());
            		union(a,b,w);
            	}
            	else if ("?".equals(order)){
            		if (find(a)!=find(b)) {
            			bw.write("UNKNOWN\n");
            		}
            		else {bw.write(weight[b]-weight[a]+"\n");}
            	}
            }
        }
        bw.flush();
        bw.close();
        br.close();

	}

}
