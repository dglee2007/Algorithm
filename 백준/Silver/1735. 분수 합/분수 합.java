import java.io.*;
import java.util.*;

public class Main {
    static int c1,c2,p1,p2;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        c1 = Integer.parseInt(st.nextToken());
        p1 = Integer.parseInt(st.nextToken());
        st=new StringTokenizer(br.readLine());
        c2 = Integer.parseInt(st.nextToken());
        p2 = Integer.parseInt(st.nextToken());

        c1=c1*p2+c2*p1;
        p1=p1*p2;
        int gcd=getGcd(c1,p1);

        bw.write(c1/gcd+" "+p1/gcd+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
    static int getGcd(int a, int b) {
        if (a%b==0) return b;
        return getGcd(b,a%b);

    }
}
