# BFSE-SSSP
SSSP solution using a new BFS strategy called BFS "expansion"
or "step-expansion"

# Algorithm
The algorithm introduces two new properties to store: factor
and step. In order to use step-expansion, a graph must store 
step for each edge. Factor can be stored or calculated at each 
use of step-expansion without negatively affecting performance.

 * Factor: the minimum edge weight found in the graph
 * Step: A represenation of incremental traversal of a single edge

    For each v in V
2.    Distance[v] = INF
3.    Parent[v] = -1
4. For each e in E
5.    Step[e] = e.weight
4. Distance[source] = 0
5. Queue = {}
7. Add source to queue
8. While queue is not empty
9.    initial = poll front of queue
10.   for each outgoing edge at initial
11.       if Step[edge] > factor
12.           decrease Step[edge]
13.           re-add initial to back of queue
14.       else if terminal vertex is not visited
15.           relax this edge
16.           visit the terminal vertex
17.           Parent[terminal] = initial
18.           remove this edge from the adjacency list
