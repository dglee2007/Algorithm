import java.io.*;
import java.util.*;
public class Main {
    static final int MOD = 1000000007;
    static int N, H;
    static int d[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine().trim());
        H = N/2;

        int a[] = new int[N+1];

        d = new int[N+1][H+3];

        StringTokenizer st = new StringTokenizer(br.readLine().trim()); 
        for ( int i=1; i<=N; i++ ) a[i] = Integer.parseInt(st.nextToken());

        d[1][0] = a[1]==-1?1:a[1]==0?1:0;
        for ( int i=2; i<=N; i++ ) {
            if ( a[i] == -1 ) {
                d[i][0] = (d[i-1][0] + d[i-1][1])%MOD;
                for ( int j=1; j<=H; j++ ) {
                    d[i][j] = ((d[i-1][j-1] + d[i-1][j])%MOD + d[i-1][j+1])%MOD;
                }
            } else if ( a[i] == 0 ) {
                d[i][0] = (d[i-1][0] + d[i-1][1])%MOD;
            } else {
                d[i][a[i]] = ((d[i-1][a[i]-1] + d[i-1][a[i]])%MOD + d[i-1][a[i]+1])%MOD;
            }
        }

        bw.write(d[N][0]+"\n");
        bw.flush();
        bw.close();
        br.close();    
   }
}