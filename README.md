# Introduction to Step-Expansion
This is an explanation on finding the single-source shortest path using a modifed breadth-first-search algorithm called  "step-expansion". Step-expansion can solve SSSP on graphs with varying edge weight. 
It accomplishes this by simulating extra steps in the traversal process by "expanding"
a large weighted edge into several segmented edges or "steps".

## Time Complexity
The algorithm runs in O(V + E*W) where W is the maximum edge weight in the graph.

## Effectiveness
The algorithm should be effective on any Directed Acyclic Graph. The algorithm is an improvement over naïve BFS SSSP. BFS SSSP will always produce correct output for any DAG if and only if edge weight is constant across all edges. BFS Step-Expansion SSSP corrects this. Therefore, BFSE is effective for any positive weighted DAG. I haven't tested negative edge weights.

## Algorithm
The algorithm introduces two new properties: factor
and step. In order to use step-expansion, a graph must track 
step for each edge. The algorithm can be optimized a bit by storing 
these values instead of finding them each time BFSE is called.

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

