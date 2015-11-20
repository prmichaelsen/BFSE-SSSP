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

<source lang="java" line>
Breadth-First-Search(G, v):
    
    for each node n in G:            
        n.distance = INFINITY        
        n.parent = NIL

    factor = NIL
    
    for each edge e in G
        factor = min(factor, e.weight)
        e.step = e.weight

    create empty queue Q      

    v.distance = 0
    Q.enqueue(v)                      

    while Q is not empty:        
    
        u = Q.dequeue()
    
        for each node n that is adjacent to u:
            if n.step > factor
                n.step = n.step - 1
                Q.enqueue(u)
            if n.distance == INFINITY:
                n.distance = u.distance + weight(u,v)
                n.parent = u
                Q.enqueue(n)
                G.remove(n)
</source>

