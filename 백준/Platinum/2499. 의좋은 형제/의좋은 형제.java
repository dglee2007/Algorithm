import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 987654321;
    static int N;
    static int[][] map = new int[21][21];
    static int[][] sum = new int[21][21];
    static int allsum = 0;
    static int[][][] dp = new int[21][21][40001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 입력 및 누적 합계 계산
        for (int n = 1; n <= N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int m = 1;m <= N; m++) {
                map[n][m] = Integer.parseInt(st.nextToken());
                allsum += map[n][m];
                sum[n][m] = sum[n-1][m] + map[n][m];
            }
        }

        // DP 배열 초기화
        for (int[][] layer : dp){
            for (int[] row : layer){
                Arrays.fill(row, -1);
            }
        }

        // 최소 수확량 차이 계산
        System.out.println(solve(N,0,0));

        // 최적 경로 출력
        reconstruct(N, 0, 0);
    }

    // 동적 프로그래밍으로 최소 수확량 차이 계산
    static int solve(int y, int x, int Young) {
        if (x==N){
            int Old = allsum - Young;
            return dp[y][x][Young] = Math.abs(Old-Young);
        }
        if (dp[y][x][Young] != -1) return dp[y][x][Young];

        int ret = INF;
        for (int next = y; next>=0; next--) {
            // sum[next][x + 1] 접근 전에 범위 확인
            if (next>=0 && x+1<=N){
                int young = sum[next][x+1];
                ret = Math.min(ret, solve(next,x+1,young+Young));
            }
        }

        return dp[y][x][Young] = ret;
    }

    // 최적 경로를 추적하여 결과를 출력하는 함수
    static void reconstruct(int y, int x, int Young) {
        if (x==N){
            System.out.println();
            return;
        }

        for (int next = y; next>=0; next--) {
            // sum[next][x + 1] 접근 전에 범위 확인
            if (next >= 0 && x + 1 <= N){
                int young = sum[next][x+1];
                if (dp[y][x][Young] == dp[next][x+1][young+Young]) {
                    System.out.print((N-next)+" ");
                    reconstruct(next,x+1,young+Young);
                    break;
                }
            }

        }
    }
}
