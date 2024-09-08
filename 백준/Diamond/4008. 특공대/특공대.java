import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static long a, b, c;
    static long sum[] = new long[1000001];
    static long dp[] = new long[1000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());
        c = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            sum[i] = Long.parseLong(st.nextToken());
            sum[i] += sum[i - 1];
        }

        bw.write(solve() + "");
        br.close();
        bw.flush();
        bw.close();
    }

    private static double intersect(int i, int j) {
        return (a * ((sum[j] * sum[j]) - (sum[i] * sum[i])) - b * (sum[j] - sum[i]) + dp[j] - dp[i]) / (2.0 * a * (sum[j] - sum[i]));
    }

    private static long evaluate(int n){
        return a*(sum[n]*sum[n])+b*sum[n]+c;
    }

    private static long solve(){
        LinkedList<Integer> candid = new LinkedList<>();
        candid.addLast(0);

        for (int k=1; k<=n; k++){
            while (candid.size()>1 && intersect(candid.getFirst(),candid.get(1))<sum[k]){
                candid.removeFirst();
            }
            int i = candid.getFirst();
            dp[k]= -2 * a * sum[i]*sum[k] + a*(sum[i]*sum[i]) - b*sum[i] + dp[i] + evaluate(k);

            while (candid.size()>1 && intersect(k,candid.get(candid.size()-2))<intersect(candid.getLast(),candid.get(candid.size()-2))){
                candid.removeLast();
            }
            candid.addLast(k);
        }
        return dp[n];
    }
}
