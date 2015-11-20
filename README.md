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
    Distance[v] = INF
    Parent[v] = -1
  For each e in E
    Step[e] = e.weight
  Distance[source] = 0
  Queue = {}
  Add source to queue
  While queue is not empty
    initial = poll front of queue
      for each outgoing edge at initial
        if Step[edge] > factor
          decrease Step[edge]
          re-add initial to back of queue
        else if terminal vertex is not visited
          relax this edge
            Distance[terminal] = Distance[initial] + edge weight
          visit the terminal vertex
          Parent[terminal] = initial
          remove this edge from the adjacency list
