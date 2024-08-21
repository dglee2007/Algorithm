import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static long[] factorial;
    static boolean[] used;
    static int N;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        factorial = new long[N + 1];
        used = new boolean[N + 1];
        factorial[0] = 1;

        for (int i = 1; i <= N; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        st = new StringTokenizer(br.readLine());
        int problemType = Integer.parseInt(st.nextToken());

        if (problemType == 1) {
            long k = Long.parseLong(st.nextToken());
            findKthPermutation(k);
        } else if (problemType == 2) {
            int[] permutation = new int[N];
            for (int i = 0; i < N; i++) {
                permutation[i] = Integer.parseInt(st.nextToken());
            }
            findPermutationOrder(permutation);
        }
    }

    static void findKthPermutation(long k) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            int count = 1;
            for (int j = 1; j <= N; j++) {
                if (!used[j]) {
                    if (k <= factorial[N - i]) {
                        result.append(j).append(" ");
                        used[j] = true;
                        break;
                    } else {
                        k -= factorial[N - i];
                    }
                }
            }
        }
        System.out.println(result.toString().trim());
    }

    static void findPermutationOrder(int[] permutation) {
        long order = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < permutation[i]; j++) {
                if (!used[j]) {
                    order += factorial[N - i - 1];
                }
            }
            used[permutation[i]] = true;
        }
        System.out.println(order);
    }
}
