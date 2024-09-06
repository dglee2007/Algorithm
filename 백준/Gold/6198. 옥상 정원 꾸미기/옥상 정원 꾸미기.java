import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 첫 번째 입력: 빌딩의 개수
        int N = Integer.parseInt(br.readLine());
        
        // 두 번째 입력부터 빌딩의 높이 배열
        int[] heights = new int[N];
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(br.readLine());
        }
        
        // 스택을 이용한 풀이
        Stack<Integer> stack = new Stack<>();
        long totalVisibleCount = 0;

        // 각 빌딩을 순차적으로 처리
        for (int i = 0; i < N; i++) {
            // 스택의 탑에 있는 빌딩이 현재 빌딩보다 작거나 같다면, 그 빌딩은 더 이상 볼 수 없으므로 스택에서 제거
            while (!stack.isEmpty() && stack.peek() <= heights[i]) {
                stack.pop();
            }
            
            // 현재 스택에 남아있는 빌딩들은 현재 빌딩에서 볼 수 있는 빌딩들
            totalVisibleCount += stack.size();
            
            // 현재 빌딩을 스택에 추가
            stack.push(heights[i]);
        }
        
        // 결과 출력
        System.out.println(totalVisibleCount);
    }
}
