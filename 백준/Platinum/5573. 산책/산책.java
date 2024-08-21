import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int list[][] = new int[1001][1001];
    static int H, W, N;
 
    static void solve() {
        int x = 1;
        int y = 1;
        while (true) {
            if (list[x][y] == 1)
                y++;
            else
                x++;
 
            if (x > H || y > W)
                break;
        }
 
        System.out.println(x + " " + y);
    }
 
    static void func() {
        int dp[][] = new int[H + 1][W + 1];
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= W; j++) {
                if (i == 1 && j == 1) {
                    dp[i][j] = N - 1;
                    continue;
                }
                
                dp[i][j] = dp[i - 1][j] / 2 + dp[i][j - 1] / 2;
 
                if (dp[i - 1][j] % 2 == 1 && list[i - 1][j] == 0)
                    dp[i][j]++;
                if (dp[i][j - 1] % 2 == 1 && list[i][j - 1] == 1)
                    dp[i][j]++;
            }
        }
 
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= W; j++) {
                if (dp[i][j] % 2 == 0)
                    continue;
 
                list[i][j] = 1 - list[i][j];
            }
        }
    }
 
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
 
        for (int i = 1; i <= H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= W; j++) {
                list[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
 
    public static void main(String[] args) throws Exception {
        input();
        func();
        solve();
    }
}
