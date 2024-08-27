import java.io.*;

public class Main {
    static String[] Nums;
    static int K,N;
    static int MaxBitmask;
    static long[][] dp;
    static int[][] mods;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine()); //집합수
        Nums=new String[N]; //집합을 저장할 배열

        for (int i=0;i<N;i++){
            Nums[i]=br.readLine();
        }
        //나눌 수 K
        K=Integer.parseInt(br.readLine());
        //모든 지점을 방문했을 비트마스크
        MaxBitmask=(1<<N)-1;
        //[방문지점][나머지] 기록을 위한 DP
        dp=new long[1<<N][K];
        //[배열 순서][나머지]에 따른 나머지 값을 계산하는 배열
        mods=new int[N][K];
        //나머지배열과 dp 배열값을 -1로 초기화
        for (int j=0;j<K;j++){
            for (int i=0;i<N;i++){
                mods[i][j]=-1;
            }
            for (int i=0;i<=MaxBitmask;i++){
                dp[i][j]=-1;
            }
        }
        //K로 나눈 나머지가 0인 수 계산 (모든 점 미방문, 나머지 0)
        //bitmask가 0이고 나머지가 0일 때 가능한 총 경우의 수
        long answer = dp(0,0);
        //0이면 0/1 출력
        if (answer==0){
            System.out.println("0/1");
        }
        //0이 아니면 gcd를 이용해 기약분수형태로 출력
        else {
            long fac =1;
            for (int i=2;i<=N;i++){
                fac*=i;
            }
            long gcd = getGCD(fac,answer);
            System.out.println(answer/gcd+"/"+fac/gcd);
        }
        br.close();
    }
    public static long dp(int bitmask,int mod){
        //이미 방문한 경우 dp값 리턴
        if (dp[bitmask][mod]!=-1) return dp[bitmask][mod];
        //모든 점을 방문한 경우 mod가 0이면 1, 아니면 0 리턴
        if (bitmask==MaxBitmask){
            return mod==0?1:0;
        }
        //나머지가 0인 개수
        long count=0;
        //아직 미방문한 idx 조사
        for (int i=0;i<N;i++){
            int idx = 1<<i;
            //아직 미방문했다면
            if ((bitmask & idx)==0){
                //dp[bitmask][mod]=count의 의미
                //현재 bitmask 상태와 현재 나머지(mod)에서 가능한 모든 순열 조함의 수
                count+=dp(bitmask|idx,getMod(i,mod));
            }
        }
        //dp에 count를 저장하고 리턴
        return dp[bitmask][mod]=count;
    }
    //나머지 구하기
    public static int getMod(int idx,int mod){
        if (mods[idx][mod]!=-1) return mods[idx][mod];
        int curr=mod;
        int length=Nums[idx].length();
        for (int i=0;i<length;i++){
            curr*=10; //10의 자리만큼 증가
            curr+=Nums[idx].charAt(i)-'0'; //1의 자리 더하기
            curr%=K; //K로 나눈 나머지 값
        }
        //나머지 출력
        return mods[idx][mod]=curr;
    }
    //GCD 구하기
    public static long getGCD(long n,long m){
        if (m==0) return n;
        return getGCD(m,n%m);
    }
}
