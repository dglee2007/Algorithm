import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    final static int MAX_N      =   1000000;
    static int N, OFFSET;
    static int[] tree;
    static Num[] nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        nums = new Num[N];

        for (OFFSET = 1; OFFSET < N; OFFSET *= 2);
        tree = new int[OFFSET * 2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = new Num(Integer.parseInt(st.nextToken()), i);
        }

        Arrays.sort(nums);
        for (int i = 0; i < N; i++) {
            int l = query(0, nums[i].i);
            update(nums[i].i, ++l);
        }

        bw.write(tree[1] + "\n");

        bw.flush();
    }

    static int merge(int l, int r) {
        return Math.max(l, r);
    }

    static void update(int i, int v) {
        i += OFFSET;
        tree[i] = v;
        while ((i /= 2) > 0) {
            tree[i] = merge(tree[i * 2], tree[i * 2 + 1]);
        }
    }

    static int query(int s, int e) {
        int res = 0;

        s += OFFSET;
        e += OFFSET;
        while (s <= e) {
            if (s % 2 == 1) {
                res = merge(res, tree[s]);
                s++;
            }
            if (s % 2 == 0) {
                res = merge(res, tree[e]);
                e--;
            }
            s /= 2;
            e /= 2;
        }

        return res;
    }

    static class Num implements Comparable<Num> {
        int n, i;

        public Num(int n, int i) {
            this.n = n;
            this.i = i;
        }

        @Override
        public int compareTo(Num o) {
            if (this.n == o.n) return Integer.compare(o.i, this.i);
            else return Integer.compare(this.n, o.n);
        }

    }

}