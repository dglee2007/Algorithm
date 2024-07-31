import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int H=Integer.parseInt(st.nextToken());
        int[] down = new int[H+2];
        int[] up = new int[H+2];

        for(int i=0;i<N/2;i++){
            int d = Integer.parseInt(br.readLine());
            int u = H-Integer.parseInt(br.readLine())+1;
            down[d]++;
            up[u]++;
        }

        for(int i=1;i<=H;i++) down[i] += down[i-1];
        for(int i=H;i>=1;i--) up[i] += up[i+1];

        int min = Integer.MAX_VALUE;
        int num=0;
        for(int i=1;i<=H;i++){
            int d = down[H]-down[i-1];
            int u = up[1]-up[i+1];

            if (min>d+u){
                min=d+u;
                num=1;
            }
            else if (min==d+u){num++;}
        }
        bw.write(min+" "+num+"\n");
        bw.flush();





    }
}
