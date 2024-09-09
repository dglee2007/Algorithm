from collections import deque
import sys

dx=[1,-1,0,0]
dy=[0,0,1,-1]
test_num=int(sys.stdin.readline())

def bfs(graph,cnt,m,n,j):
    for i in range(j):
        a,b=map(int,sys.stdin.readline().split())
        graph[b][a]=1
    queue=deque()
    for j in range(n): #i가 가로, j가 세로
        for i in range(m):
            if graph[j][i]==1:
                cnt+=1
                queue.append((i,j))
                graph[j][i]=0
                while queue:
                    x,y=queue.popleft()
                    for i in range(4):
                        nx=x+dx[i]
                        ny=y+dy[i]
                        if nx<0 or nx>=m or ny<0 or ny>=n:
                            continue
                        if graph[ny][nx]==1:
                            queue.append((nx,ny))
                            graph[ny][nx]=0
    
    return cnt
cnts=[]
for i in range(test_num):
    m,n,j=map(int,sys.stdin.readline().split()) #m이 가로, n이 세로
    graph=[[0]*m for _ in range(n)]
    cnt=0
    cnts.append(bfs(graph,cnt,m,n,j))

for i in range(len(cnts)):
    print(cnts[i])
    



                
