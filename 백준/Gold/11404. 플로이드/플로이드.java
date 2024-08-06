import java.util.*;
import java.io.*;

public class Main {
    static final int INF = Integer.MAX_VALUE/2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] arr= new int[n+1][n+1];
        for (int i=1;i<=n;i++){
            for (int j=1;j<=n;j++){
                arr[i][j] = INF;

                if (i==j){arr[i][j]=0;}
            }
        }

        for (int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[a][b]=Math.min(arr[a][b], c);
        }

        for (int k=1;k<=n;k++){
            for (int i=1;i<=n;i++){
                for (int j=1;j<=n;j++){
                    if (arr[i][j]>arr[i][k]+arr[k][j]){
                        arr[i][j]=arr[i][k]+arr[k][j];
                    }
                }
            }
        }

        for (int i=1;i<=n;i++){
            for (int j=1;j<=n;j++){
                if (arr[i][j]==INF){ //갈 수 없는 곳은 0으로 초기화
                    arr[i][j]=0;
                }
                bw.write(arr[i][j]+" ");
            }
            //bw.write("\n");
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
