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

Modified BFS from wikipedia (modifications are commented):

<source lang="java" line>
Step-Expansion(G, v):
    
    for each node n in G:            
        n.distance = INFINITY        
        n.parent = NIL

    factor = NIL                                        //initialize factor
    
    for each edge e in G                                //initialize step for each edge
        factor = min(factor, e.weight)                  //factor = minimum edge weight in graph
        e.step = e.weight                               //step for each edge is initialized to edge weight

    create empty queue Q      

    v.distance = 0
    Q.enqueue(v)                      

    while Q is not empty:        
    
        u = Q.dequeue()
    
        for each edge e from u to n:                    //process edges
            if e.step > factor                          //if edge requires stepping
                e.step = e.step - 1                     //step on edge
                Q.enqueue(u)                            //enqueue this vertex
            else if n.distance == INFINITY:             //else if node is not visited
                n.distance = u.distance + e.weight      //relax using weight of edge
                n.parent = u
                Q.enqueue(n)
                G.remove(n)                             //do not ever process this edge again
</source>

