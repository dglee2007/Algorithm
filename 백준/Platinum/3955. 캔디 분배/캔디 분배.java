import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N=Integer.parseInt(br.readLine());


        for (int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K=Integer.parseInt(st.nextToken());
            int C=Integer.parseInt(st.nextToken());

            if (C==1) {
                if (K==Math.pow(10,9)) {bw.write("IMPOSSIBLE\n");}
                else {bw.write((K+1)+"\n");}
            }

            else if (K==1) {bw.write(1+"\n");}
            else if (gcd(K,C)!=1) {bw.write("IMPOSSIBLE\n");}

            else {
                bw.write(euclide_gcd((long) K,(long) C)+"\n");
            }
        }
        bw.flush();
        bw.close();

    }

    public static long gcd(long a, long b) {
        if (b==0) {return a;}
        else {return gcd(b,a%b);}
    }

    private static long euclide_gcd(long a, long b) {
        long s0=1,t0=0,s1=0,t1=1;
        long originalA = a;

        while (b != 0) {
            long q = a / b;
            long r = a % b;
            a = b;
            b = r;

            long s = s0 - s1 * q;
            long t = t0 - t1 * q;
            s0 = s1;
            s1 = s;
            t0 = t1;
            t1 = t;
        }

        // Make sure t0 is positive
        if (t0 < 0) {
            t0 += originalA;
        }

        return t0;
    }
}
