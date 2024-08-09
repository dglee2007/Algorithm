import sys
input=sys.stdin.readline
n=int(input())
points=[list(map(int,input().split())) for _ in range(n)]

points.sort() #x축 기준 정렬

def get_distance(p1,p2): #거리 구하는 함수
    return (p1[0]-p2[0])**2 + (p1[1]-p2[1])**2

def solution(left,right):
    if left==right: #점이 1개 이하는 에러
        return float('inf')
    if right-left==1: #점이 2개면 거리 구하기
        return get_distance(points[left],points[right])
    
    #이분탐색
    mid=(left+right)//2
    minDist=min(solution(left,mid),solution(mid,right))
    
    #x축 기준 조건에 부합하는 점들
    target_points=[] 
    for i in range(left,right+1):
        if (points[mid][0]-points[i][0])**2<minDist:
            target_points.append(points[i])
    
    target_points.sort(key=lambda x:x[1]) #y축 기준 정렬

    #y축 기준 조건에 부합하는 점들
    target_num=len(target_points) 
    for i in range(target_num-1):
        for j in range(i+1,target_num):
            if (target_points[i][1]-target_points[j][1])**2<minDist:
                minDist=min(minDist,get_distance(target_points[i],target_points[j]))
            else:
                break #현재 후보 점이 다음 점과 멀다면 루프 종료
    
    return minDist

print(solution(0,n-1))