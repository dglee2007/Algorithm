import java.io.*;
import java.util.*;

public class Main {
	
	public static int[] histogram;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st ;
        int N;
        
        while (true) {
        	st = new StringTokenizer(br.readLine());
        	
        	N= Integer.parseInt(st.nextToken());
        	
        	if (N==0) {break;}
        	
        	histogram = new int[N];
        	
        	for (int i=0;i<N;i++) {
        		histogram[i] = Integer.parseInt(st.nextToken());
        	}
        	bw.write(getArea(0,N-1)+"\n");
        	histogram=null;
        }
        bw.flush();
        bw.close();
	}
	
	public static long getArea(int lo, int hi) {
		if (lo==hi) {return histogram[lo];}
		
		int mid = (lo+hi)>>1;
        	
        long leftArea=getArea(lo,mid);
        long rightArea=getArea(mid+1,hi);
        
        long max=Math.max(leftArea, rightArea);
        
        max=Math.max(max, getMidArea(lo,hi,mid));
        
        return max;
	}
	
	public static long getMidArea(int lo, int hi, int mid) {
		int toLeft=mid; //중간에서왼쪽으로 갈 포인터
		int toRight=mid; //중간에서 오른쪽으로 갈 포인터
		
		long height = histogram[mid]; //높이
		
		long maxArea = height; //초기 최대 넓이
		
		//왼쪽과 오른쪽 중에서 더 높은 높이 선택
		//but 갱신되는 높이는 기존 높이보다 작거나 같아야함
		while (lo<toLeft && toRight<hi) {
			if (histogram[toLeft-1]<histogram[toRight+1]) {
				toRight++;
				height=Math.min(height, histogram[toRight]);
			}
			else {
				toLeft--;
				height = Math.min(height, histogram[toLeft]);
			}
			
			maxArea=Math.max(maxArea, height*(toRight-toLeft+1));
		}
		
		//오른쪽 구간 탐색 미완료 시 끝까지 탐색
		while (toRight<hi) {
			toRight++;
			height=Math.min(height, histogram[toRight]);
			maxArea=Math.max(maxArea, height*(toRight-toLeft+1));
		}
		
		//왼쪽 구간 탐색 미완료 시 끝까지 탐색
		while (lo<toLeft) {
			toLeft--;
			height=Math.min(height, histogram[toLeft]);
			maxArea=Math.max(maxArea, height*(toRight-toLeft+1));
		}
		
		return maxArea;
		
	}

}
