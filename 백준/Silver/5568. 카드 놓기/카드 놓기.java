import java.io.*;
import java.util.*;

public class Main {
    static int CardNum,SelectNum;
    static String[] card;
    static boolean[] visited;
    static HashSet<String> hs = new HashSet<String>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        CardNum = Integer.parseInt(br.readLine());
        SelectNum = Integer.parseInt(br.readLine());
        card = new String[CardNum];
        for (int i = 0; i < CardNum; i++) {
            card[i] = br.readLine();
        }
        visited = new boolean[CardNum];
        SelectCard(SelectNum,"");
        sb.append(hs.size());
        sb.append("\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }
    public static void SelectCard(int remain,String k){
        if (remain == 0){
            hs.add(k);
            return;
        }
        for (int i = 0; i < CardNum; i++) {
            if (visited[i] == false){
                visited[i] = true;
                SelectCard(remain-1,k+card[i]);
                visited[i] = false;
            }
        }
    }
}
