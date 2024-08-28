import java.io.*;
import java.util.*;


public class Main {
    //static final double INF = 987654321;
    static int N;
    static Point[] co = new Point[515];
    static double[][] dp = new double[515][515];

    static class Point {
        int x,y;
        Point(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int loop=1; loop<=T; loop++) {
            N = Integer.parseInt(br.readLine());

            for (double[] row: dp){
                Arrays.fill(row, 0);
            }

            for (int i=1; i<=N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                co[i] = new Point(x, y);
            }
            double result = makeDP(1,1);
            double value = dp[1][1];
            //bw.write(String.format("%.6f", value));
            bw.write(Double.toString(value));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static double dist(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    static double makeDP(int s1, int s2) {
        if (s1==N){
            return dist(co[s2].x,co[s2].y,co[N].x,co[N].y);
        }
        else if (s2==N){
            return dist(co[s1].x,co[s1].y,co[N].x,co[N].y);
        }

        if (dp[s1][s2]!=0) return dp[s1][s2];

        int next = Math.max(s1,s2)+1;
        double p = makeDP(next,s2) + dist(co[s1].x,co[s1].y,co[next].x,co[next].y);
        double q = makeDP(s1,next) + dist(co[s2].x,co[s2].y,co[next].x,co[next].y);

        return dp[s1][s2] = Math.min(p,q);
    }
}
