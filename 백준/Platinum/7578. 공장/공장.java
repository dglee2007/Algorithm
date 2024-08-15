import java.io.*;
import java.util.*;

public class Main {
    static long[] tree;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] A =new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++){
            A[i]=Integer.parseInt(st.nextToken());
        }

        Map<Integer, Integer> B = new HashMap<>(); //Key - 식별번호, Value - 인덱스
        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++){
            int val = Integer.parseInt(st.nextToken());
            B.put(val,i);
        }

        tree=new long[N*4];
        long ans=0;

        for (int i=1;i<=N;i++){
            int valA=A[i];
            int valB=B.get(valA);

            ans+=sum(1,N,1,valB+1,N);

            update(1,N,1,valB,1);
        }
        bw.write(ans+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static long sum(int start, int end, int node, int left, int right){
        if (end<left || start>right) return 0;
        if (left<=start && end<=right) return tree[node];
        int mid = (start+end)/2;
        return sum(start,mid,node*2,left,right)+sum(mid+1,end,node*2+1,left,right);
    }

    public static void update(int start, int end, int node, int idx, int dif){
        if (idx<start || idx>end) return;
        tree[node]+=dif;
        if (start!=end){
            int mid = (start+end)/2;
            update(start,mid,node*2,idx,dif);
            update(mid+1,end,node*2+1,idx,dif);
        }
    }
}
