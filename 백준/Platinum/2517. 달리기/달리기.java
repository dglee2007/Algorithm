import java.io.*;
import java.util.*;

public class Main {

    static class Runner {
        int idx;
        int skill;

        public Runner(int idx, int skill) {
            this.idx = idx;
            this.skill = skill;
        }
    }

    static int n;
    static int[] tree;
    static List<Runner> runners;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        runners = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            runners.add(new Runner(i, Integer.parseInt(br.readLine())));
        }

        // 초기화 함수 호출
        init();

        tree = new int[n * 4];

        for (int i = 0; i < n; i++) {
            int skill = runners.get(i).skill;
            // 현재 선수의 최선의 등수 계산
            bw.write((i+1)-query(1,n,1,1,skill)+"\n");
            // 세그먼트 트리 업데이트
            update(1, n, 1, skill);
        }
        bw.flush();
        bw.close();
        br.close();
    }

    // 초기화 함수
    private static void init() {
        // 실력에 따라 정렬하여 인덱스를 재할당
        Collections.sort(runners, Comparator.comparingInt(a -> a.skill));
        for (int i = 0; i < runners.size(); i++) {
            runners.get(i).skill = i + 1;
        }
        // 원래 순서대로 재정렬
        Collections.sort(runners, Comparator.comparingInt(a -> a.idx));
    }

    // 세그먼트 트리 쿼리 함수
    static int query(int s, int e, int node, int l, int r) {
        if (e < l || r < s) return 0;
        if (l <= s && e <= r) return tree[node];

        int mid = (s + e) / 2;
        return query(s, mid, node * 2, l, r) + query(mid + 1, e, node * 2 + 1, l, r);
    }

    // 세그먼트 트리 업데이트 함수
    static int update(int s, int e, int node, int idx) {
        if (idx < s || e < idx) return tree[node];
        if (s == e) return tree[node] += 1;

        int mid = (s + e) / 2;
        return tree[node] = update(s, mid, node * 2, idx) + update(mid + 1, e, node * 2 + 1, idx);
    }
}
