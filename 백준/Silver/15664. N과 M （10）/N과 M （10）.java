import java.util.*;
import java.io.*;

public class Main {
    static int[] numArr;
    static int[] seq;
    static int N;
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numArr = new int[N];
        seq = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numArr);
        dfs(0,0);
    }
    static void dfs(int cnt, int start) {
        if (cnt == M) {
            for (int i=0;i<M;i++){
                System.out.print(seq[i]+" ");
            }
            System.out.println();
            return;
        }
        for (int i=start; i<N; i++) {
            if (i>start && numArr[i] == numArr[i-1]) {continue;}// 중복된 수열 방지
            seq[cnt] = numArr[i];
            dfs(cnt+1,i+1); // 다음 단계에서 같은 위치나 이후의 위치에서 선택 가능
        }
    }


}
