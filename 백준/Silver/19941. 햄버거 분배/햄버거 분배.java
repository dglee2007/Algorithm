import java.io.*;
import java.util.*;

public class Main {
    static char[] info;
    static int answer=0;
    static int N,K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());
        info = br.readLine().toCharArray();

        for (int i = 0; i < N; i++) {
            if (info[i] == 'P') {

                int index=Math.max(i-K,0);
                boolean flag=false;
                //먹을 수 있는 최대한의 햄버거 탐색

                for (int j=index;j<i;j++){
                    if (burgerCheck(j)){
                        flag=true;
                        break;
                    }
                }

                if (!flag){
                    index = i+K >=N ? N-1:i+K;
                    for (int j=i+1;j<=index;j++){
                        if (burgerCheck(j)) break;
                    }
                }
            }
        }
        bw.write(answer+"");
        bw.flush();
        bw.close();
        br.close();
    }
    static boolean burgerCheck(int index){
        if (info[index] == 'H'){
            info[index]='X';
            answer++;
            return true;
        }
        return false;
    }
}
