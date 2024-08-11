import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> arr;
    static int dfs[];

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        int r=Integer.parseInt(st.nextToken())-1;

        arr=new ArrayList<>();
        dfs=new int[n];

        for(int i=0; i<n; i++) {
            arr.add(new ArrayList<>());
            dfs[i]=-1;
        }

        for(int i=0; i<m; i++) {
            st=new StringTokenizer(br.readLine());
            int u=Integer.parseInt(st.nextToken())-1;
            int v=Integer.parseInt(st.nextToken())-1;

            arr.get(u).add(v);
            arr.get(v).add(u);
        }

        for(int i=0; i<n; i++) {
            Collections.sort(arr.get(i));
        }

        dfs[r]=0;
        DFS(r);

        for(int i=0; i<n; i++) {
            bw.write(dfs[i]+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    private static void DFS(int r){
        for (int i=0;i<arr.get(r).size();i++){
            if (dfs[arr.get(r).get(i)]==-1){
                dfs[arr.get(r).get(i)]=dfs[r]+1;
                DFS(arr.get(r).get(i));
            }
        }
    }
}
