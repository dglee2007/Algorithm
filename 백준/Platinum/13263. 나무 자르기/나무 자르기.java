import java.io.*;
import java.util.*;

public class Main {
    static class LinearFunc {
        long a, b;
        double s;

        LinearFunc() {
            this(1, 0);
        }

        LinearFunc(long a, long b) {
            this.a = a;
            this.b = b;
            this.s = 0;
        }
    }

    // 두 직선 f, g의 교점의 x좌표를 구함
    static double cross(LinearFunc f, LinearFunc g) {
        return (double) (g.b - f.b) / (f.a - g.a);
    }

    public static void main(String[] args) throws IOException {
        //Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력받기
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] B = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        // dp[0] = 0, dp 테이블을 채우면서 "f(.) = B[i]*x + dp[i]" 를 리스트에 추가
        long[] dp = new long[N];
        ArrayList<LinearFunc> f = new ArrayList<>();
        for (int i = 1; i < N; ++i) {
            // i-1번에 해당하는 새로운 직선을 리스트의 끝에 추가
            LinearFunc g = new LinearFunc(B[i - 1], dp[i - 1]);
            while (f.size() > 0) {
                g.s = cross(f.get(f.size() - 1), g);
                if (f.get(f.size() - 1).s < g.s) break;
                // 쌓기 전에, 마지막 원소와 교점을 구해서 교점이 앞에 있으면 제거
                f.remove(f.size() - 1);
            }
            f.add(g);

            long x = A[i];
            // 주어진 x좌표를 포함하는 선분(fpos)을 이분 탐색으로 찾음
            int fpos = f.size() - 1;
            if (x < f.get(f.size() - 1).s) {
                int lo = 0, hi = f.size() - 1;
                while (lo + 1 < hi) {
                    int mid = (lo + hi) / 2;
                    if (x < f.get(mid).s) {
                        hi = mid;
                    } else {
                        lo = mid;
                    }
                }
                fpos = lo;
            }
            // i번째 dp 값 계산
            dp[i] = f.get(fpos).a * x + f.get(fpos).b;
        }

        // 결과 출력
        System.out.println(dp[N - 1]);
    }
}


