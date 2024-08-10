import java.io.*;
import java.util.*;
public class Main {
    static final int INF = 1000000;
    static int[][][] d; // i번째 지시에서 왼발이 j위치에, 오른발이 k위치에 있을때 최소비용
    static int[] cmds;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        cmds = new int[100001];

        int N = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for ( int i=1; ; i++ ) {
            int cmd = Integer.parseInt(st.nextToken());
            if (cmd == 0) break;
            cmds[i] = cmd;
            N++;
        }

        d = new int[N+1][5][5];
        for ( int i=0; i<=N; i++ ) for ( int j=0; j<=4; j++ ) for ( int k=0; k<=4; k++ ) d[i][j][k] = INF;

        d[1][cmds[1]][0] = 2; // 첫번째 방향을 왼발로 밟는 경우
        d[1][0][cmds[1]] = 2; // 첫번째 방향을 오른발로 밟는 경우
        d[0][0][0] = 0;
        for (int i=2; i<=N; i++ ) {
            for (int j=0; j<=4; j++ ) {
                for (int k=0; k<=4; k++ ) {
                    if ( j == k ) continue;
                    if ( j != cmds[i] && k != cmds[i] ) continue;

                    for ( int z=0; z<=4; z++ ) {
                        if ( j == cmds[i] ) d[i][j][k] = Math.min(d[i][j][k], d[i-1][z][k] + calc(z, j));
                        if ( k == cmds[i] ) d[i][j][k] = Math.min(d[i][j][k], d[i-1][j][z] + calc(z, k));
                    }
                }
            }
        }

        int ans = INF;
        for ( int i=0; i<=4; i++ ) {
            if ( i == cmds[N] ) continue;
            ans = Math.min(ans, d[N][cmds[N]][i]);
            ans = Math.min(ans, d[N][i][cmds[N]]);
        }

        if(ans >= INF) System.out.println(0);
        else System.out.println(ans);
    }

    static int calc(int a, int b) {
        if ( a == b ) return 1;
        if ( a == 0 || b == 0 ) return 2;
        if ( (a == 1 && b == 4) || (a == 4 && b == 1) ) {
            return 3;
        } else {
            return Math.abs(a - b) + 2;
        }
    }
}