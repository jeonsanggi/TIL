## 백준 DFS와 BFS (1260번)

https://www.acmicpc.net/problem/1260

```python
from collections import defaultdict
from collections import deque

def dfs(graph, v):
    for value in graph.values():
        value.sort(reverse=True)
        
    q = deque()
    q.append(v)
    visited = list()
    
    while q:
        node = q.pop()
        if node not in visited:
            visited.append(node)
            for item in graph[node]:
                q.append(item)
    return visited
    
def bfs(graph, v):
    for value in graph.values():
        value.sort()
    q = deque()
    q.append(v)
    visited = list()
    
    while q:
        node = q.popleft()
        if node not in visited:
            visited.append(node)
            for item in graph[node]:
                q.append(item)
    return visited

n, m, v = map(int, input().split())

graph = defaultdict(list)
visited = [True for _ in range(n+1)]
for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

print(*dfs(graph, v))
print(*bfs(graph, v))
```