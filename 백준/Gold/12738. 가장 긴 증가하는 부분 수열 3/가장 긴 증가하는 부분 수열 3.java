import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        List<Integer> dp = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            int num = A[i];
            
            if (dp.isEmpty() || dp.get(dp.size() - 1) < num) {
                dp.add(num);
            } else {
                int pos = Collections.binarySearch(dp, num);
                if (pos < 0) pos = -(pos + 1);
                dp.set(pos, num);
            }
        }
        
        System.out.println(dp.size());
    }
}
