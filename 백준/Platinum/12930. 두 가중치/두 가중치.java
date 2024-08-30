import java.util.*;
import java.io.*;

class Node implements Comparable<Node> {
    int vertex;
    int w1Sum;
    int w2Sum;

    public Node(int vertex, int w1Sum, int w2Sum) {
        this.vertex = vertex;
        this.w1Sum = w1Sum;
        this.w2Sum = w2Sum;
    }

    @Override
    public int compareTo(Node other) {
        // 최소 힙을 유지하기 위해 두 가중치의 합이 작은 것부터 우선
        return Integer.compare(this.w1Sum * this.w2Sum, other.w1Sum * other.w2Sum);
    }
}

public class Main {
    public static int dijkstra(int n, char[][] weight1, char[][] weight2) {
        int INF = Integer.MAX_VALUE;
        int[][] dist = new int[n][2]; // dist[i][0]: w1 합, dist[i][1]: w2 합
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
        }
        dist[0][0] = dist[0][1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;
            int w1Sum = current.w1Sum;
            int w2Sum = current.w2Sum;

            if (u == 1) {
                return w1Sum * w2Sum;
            }

            for (int v = 0; v < n; v++) {
                if (weight1[u][v] == '.') {
                    continue;
                }

                int newW1Sum = w1Sum + (weight1[u][v] - '0');
                int newW2Sum = w2Sum + (weight2[u][v] - '0');

                if (newW1Sum < dist[v][0] || newW2Sum < dist[v][1]) {
                    dist[v][0] = newW1Sum;
                    dist[v][1] = newW2Sum;
                    pq.add(new Node(v, newW1Sum, newW2Sum));
                }
            }
        }

        return -1;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[][] weight1 = new char[n][n];
        char[][] weight2 = new char[n][n];
        for (int i = 0; i < n; i++) {
            weight1[i]=br.readLine().toCharArray();
        }
        for (int i = 0; i < n; i++) {
            weight2[i]=br.readLine().toCharArray();
        }

        int result = dijkstra(n, weight1, weight2);
        System.out.println(result);
    }
}
