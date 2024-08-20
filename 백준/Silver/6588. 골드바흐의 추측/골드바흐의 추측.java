import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] isPrime = new boolean[1000001];
        isPrime[0] = true;
        isPrime[1] = true;

        //배수 구하기
        for (int i=2;i<=(int) Math.sqrt(1000000);i++){
            for (int j=2;i*j<1000001;j++){
                isPrime[i*j] = true;
            }
        }

        while (true){
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;
            boolean ans = false;
            for (int i=2;i<=n;i++){
                if (!isPrime[i] && !isPrime[n-i]){
                    System.out.println(n + " = " + i + " + " + (n - i));
                    ans = true;
                    break;
                }
            }
            if (!ans) System.out.println("Goldbach's conjecture is wrong.");

        }
    }
}
