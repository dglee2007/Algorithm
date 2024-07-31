import java.util.*;
import java.io.*;

class Jewelry{
    int mass;
    int value;

    Jewelry(int mass,int value){
        this.mass=mass;
        this.value=value;
    }
}

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Jewelry[] jewelries = new Jewelry[N];
        for (int i = 0; i < N; i++) {
            st=new StringTokenizer(br.readLine());
            int m=Integer.parseInt(st.nextToken());
            int v=Integer.parseInt(st.nextToken());

            jewelries[i] = new Jewelry(m,v);
        }
        //무게로 1차 오름차순 정렬, 무게가 같은 경우 가격을 내림차순 정렬
        Arrays.sort(jewelries,new Comparator<Jewelry>(){

            @Override
            public int compare(Jewelry o1, Jewelry o2){
                if (o1.mass==o2.mass){
                    return o2.value-o1.value;
                }
                return o1.mass-o2.mass;
            }
        });

        int[] bags = new int[K];
        for (int i=0;i<K;i++){
            bags[i]=Integer.parseInt(br.readLine());
        }

        //가방 무게 오름차순 정렬
        Arrays.sort(bags);

        //가격 내림차순 정렬된 MaxHeap
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        long ans=0;

        for (int i=0,j=0;i<K;i++){
            //현재 가방의 무게보다 작거나 같은 보석들 모두 Maxheap에 add
            while (j<N && jewelries[j].mass<=bags[i]){
                pq.add(jewelries[j].value);
                j++;
            }
            if (!pq.isEmpty()){
                ans+=pq.poll(); //가방에 제일 값나가는 것 담고 ans에 값 더함
            }
        }

        bw.write(ans+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
