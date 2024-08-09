import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static class Point{
        int x,y;
        public Point(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    static int N,W;
    static Point[] a;
    static int[][] d;
    static ArrayList ps;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        W=Integer.parseInt(br.readLine());

        a=new Point[W+2];
        d=new int[W+2][W+2];
        ps=new ArrayList();

        a[0]=new Point(1,1);
        a[1]=new Point(N,N);

        for (int w=2;w<W+2;w++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            a[w]=new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        int ans=cal(0,1);
        System.out.println(ans);
        path(0,1);
    }

    static int cal(int f,int s){
        int ret;
        if (f==W+1||s==W+1){return 0;}
        ret = d[f][s];
        if (ret!=0){return ret;}
        int next=Math.max(f,s)+1;
        int p1=cal(next,s)+dist(a[f],a[next]);
        int p2=cal(f,next)+dist(a[s],a[next]);
        ret = Math.min(p1,p2);
        return d[f][s]=ret;
    }

    static int dist(Point p1, Point p2){
        return Math.abs(p1.x-p2.x)+Math.abs(p1.y-p2.y);
    }
    static void path(int f, int s){
        if (f==W+1||s==W+1){
            for (int i=0;i<ps.size();i++){
                System.out.println(ps.get(i));
            }
            return;
        }
        int next= Math.max(f,s)+1;
        int p1=d[next][s]+dist(a[f],a[next]);
        int p2=d[f][next]+dist(a[s],a[next]);
        if (p1<p2){
            ps.add(1);
            path(next,s);
        }
        else {
            ps.add(2);
            path(f,next);
        }
    }
}
