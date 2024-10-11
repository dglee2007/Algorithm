import java.util.*;
import java.io.*;

public class Main {
    static int arr[][], n, m, order[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        arr = new int[m][m];
        order = new int[2*m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int idx1 = Integer.parseInt(st.nextToken());
            int idx2 = Integer.parseInt(st.nextToken());
            int idx3 = Integer.parseInt(st.nextToken());
            order[idx1]++;
            order[idx2+idx1]++;
        }
        int sum=0, idx=0;
        for (int i=m-1;i>=0;i--) {
            sum+=order[idx++];
            arr[i][0] = sum;
        }
        for (int i=1;i<m;i++) {
            sum+=order[idx++];
            arr[0][i] = sum;
        }
        for (int i=1;i<m;i++){
            for (int j=1;j<m;j++){
                arr[i][j]=arr[0][j];
            }
        }
        for (int i=0;i<m;i++){
            for (int j=0;j<m;j++){
                System.out.print(arr[i][j]+1+" ");
            }
            System.out.println();
        }
    }
}
