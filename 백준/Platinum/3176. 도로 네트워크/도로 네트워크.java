import java.util.*;
import java.io.*;

public class Main {

    static class City{
        int to,dis;

        public City(int to,int dis){
            this.to=to;
            this.dis=dis;
        }
    }
    static int n,h;
    static List<City>[] list;
    static int[][] parent,minRoad,maxRoad;
    static int[] depth;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        n = Integer.parseInt(br.readLine());
        depth = new int[n+1];
        list = new ArrayList[n+1];
        for (int i=1;i<=n;i++){
            list[i] = new ArrayList<>();
        }

        h=getTreeH();

        parent=new int[n+1][h];
        minRoad=new int[n+1][h];
        maxRoad=new int[n+1][h];

        boolean[] root = new boolean[n+1];
        for (int i=0;i<n-1;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new City(b,c));
            list[b].add(new City(a,c));
            root[b]=true;
        }

        int rIdx = 0;
        for (int i=1;i<=n;i++){
            if (!root[i]){
                rIdx=i;
                break;
            }
        }

        init(rIdx,1,0);
        fillParents();

        int k= Integer.parseInt(br.readLine());
        for (int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            int[] res = LCA(d,e);
            bw.write(res[0]+" "+res[1]);
            if (i!=k-1) bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();

    }
    static int getTreeH(){
        return (int) Math.ceil(Math.log(n)/Math.log(2))+1;
    }
    static void fillParents(){
        for (int i=1;i<h;i++){
            for (int j=1;j<n+1;j++){
                parent[j][i]=parent[parent[j][i-1]][i-1];

                maxRoad[j][i]=Math.max(maxRoad[j][i-1],maxRoad[parent[j][i-1]][i-1]);
                minRoad[j][i]=Math.min(minRoad[j][i-1],minRoad[parent[j][i-1]][i-1]);
            }
        }
    }
    static void init(int cur,int h, int pa){
        depth[cur]=h;
        for (City next : list[cur]){
            if (next.to!=pa){
                init(next.to,h+1,cur);
                minRoad[next.to][0]=next.dis;
                maxRoad[next.to][0]=next.dis;
                parent[next.to][0]=cur;
            }
        }
    }

    static int[] LCA(int a,int b){
        int ah=depth[a];
        int bh=depth[b];
        if (ah<bh){
            int tmp=a;
            a=b;
            b=tmp;
        }

        int min = 1_000_001;
        int max = -1;
        for (int i=h-1;i>=0;i--){
            if (Math.pow(2,i)<=depth[a]-depth[b]){
                min=Math.min(min,minRoad[a][i]);
                max=Math.max(max,maxRoad[a][i]);
                a=parent[a][i];
            }
        }

        if (a==b) return new int[] {min,max};

        for (int i=h-1;i>=0;i--){
            if (parent[a][i]!=parent[b][i]){
                min = Math.min(min,Math.min(minRoad[a][i],minRoad[b][i]));
                max=Math.max(max,Math.max(maxRoad[a][i],maxRoad[b][i]));
                a=parent[a][i];
                b=parent[b][i];
            }
        }

        min=Math.min(min,Math.min(minRoad[a][0],minRoad[b][0]));
        max=Math.max(max,Math.max(maxRoad[a][0],maxRoad[b][0]));
        return new int[] {min,max};
    }
}
