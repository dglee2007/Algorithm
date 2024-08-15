import java.util.*;
import java.io.*;

public class Main {
    static final int KMAX = 17;
    static final int MAX = 1000001;
    static ArrayList<Integer> adj[] = new ArrayList[MAX];
    static Queue<Integer> q = new ArrayDeque<Integer>();
    static int depth[] = new int[MAX];
    static int parent[][] = new int[KMAX+1][MAX];

    static int N,M=0,K;
    static int a,b=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        K=0;
        int n = N-1;
        while (n>0){
            n>>=1;
            K++;
        }

        for (int i = 0; i < MAX; i++) {
            depth[i] = -1;
            adj[i] = new ArrayList<Integer>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }

        depth[1]=0;
        parent[1][0]=0;
        q.offer(1);

        while (!q.isEmpty()){
            int now = q.poll();
            for (int next :adj[now]){
                if (depth[next]==-1){
                    depth[next]=depth[now]+1;
                    parent[0][next]=now;
                    q.offer(next);
                }
            }
        }

        //2^k번째 조상 memoization
        for (int k=1;k<=K;k++){
            for (int v=1;v<=N;v++){
                parent[k][v]= parent[k-1][parent[k-1][v]];
            }
        }

        M=Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            bw.write(lca(a,b)+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static int lca(int a, int b){
        //depth가 a가 더 낮으면 더 깊은 것으로 swap
        //temp와 같은 변수를 사용하지 않는 테크닉
        if (depth[a]<depth[b]){
            a^=b;
            b^=a;
            a^=b;
        }
        //a==b 되도록 2^k만큼 올라간다
        int diff = depth[a]-depth[b];
        for (int k=K;k>=0;k--){
            if (diff>=(1<<k)){
                a=parent[k][a];
                diff=depth[a]-depth[b];
            }
        }
        //위로 올라가 b와 동일한 값이 나오면 a가 LCA이다
        if (a==b) return a;
        //깊이는 이제 같으니 같은 높이만큼 올라가 만나게하기
        for (int k=K;k>=0;k--){
            if (parent[k][a]!=parent[k][b]){
                a=parent[k][a];
                b=parent[k][b];
            }
        }

        return parent[0][a];
    }
}
