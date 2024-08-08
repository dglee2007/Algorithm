import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main{
    static int N;
    static int[] a, d, idx;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        a = new int[N + 1];
        d = new int[N + 1];
        idx = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        d[1] = a[1];
        idx[1] = 1;
        int len = 1;
        for (int i = 2; i < N + 1; i++) {
            if (a[i] > d[len]) {
                len++;
                idx[i] = len;
                d[len] = a[i];
            } else {
                int tmp = binarySearch(1, len, a[i]);
                d[tmp] = a[i];
                idx[i] = tmp;
            }
        }

        bw.write(len+"\n");
        ArrayList<Integer> ret = new ArrayList<>();
        for (int i = N; i >= 1; i--) {
            if (idx[i] == len) {
                ret.add(a[i]);
                len--;
            }
            if (len < 1)
                break;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = ret.size() - 1; i >= 0; i--) {
            bw.write(ret.get(i)+ " ");
        }
        bw.write("\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static int binarySearch(int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (d[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

}