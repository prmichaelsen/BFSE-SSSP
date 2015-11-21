# Introduction to Step-Expansion
This is an explanation on finding the single-source shortest path using a modifed breadth-first-search algorithm called  "step-expansion". Step-expansion can solve SSSP on cyclic graphs with arbitrary edge weight. 
It accomplishes this by simulating extra steps in the traversal process by "expanding"
a large weighted edge into several segmented edges or "steps".

Initially, the step-expansion only worked on arbirtrarily weighted DAG. This stage of development can be found under the "DAG" branch.

## Time Complexity
The algorithm runs in O(V + E*W) where W is the maximum edge weight in the graph. More precisely, the algorithm runs in O(V + E + W) time where W is the summation of all edge weights minus the total number of edges times the minimum edge weight. I use E + W because each edge e will be processed at least once and w as many times where w is the the difference between e.weight and the  minimum edge weight. Therefore complexity is the sum of the number of vertices, the number of edges, and the number of additional times all edges have to be processed.

## Effectiveness
The algorithm should be effective on any directed graph with arbitrary weights. The algorithm is an improvement over naïve BFS SSSP. BFS SSSP will always produce correct output for any DAG if and only if edge weight is constant across all edges. BFS Step-Expansion SSSP corrects this. Therefore, BFSE is effective for any positive weighted DAG. Further modifications to BFSE allow for it to be effective on any directed graph with arbitrary weights with or without cycles.

Formal proof and more inclusive testing is required to substantiate my claim that BFSE can produce correct output for any arbitrarily weighted graph with or without cycles. So far, in my limited testing, I have not discovered a counter-example.

## Algorithm
The algorithm introduces three new properties: factor, step, and ideal weight.
In order to use step-expansion, a graph must track 
step for each edge along with the factor and ideal weight for itself. 
The algorithm can be optimized a bit by storing 
these values instead of finding them each time BFSE is called.

 * Factor: the minimum edge weight found in the graph
 * Step: a represenation of incremental traversal of a single edge
 * Ideal weight: the summation of all negative edges in the graph

The algorithm can detect negative weight cycles if the weight of a path exceeds
the ideal weight of the graph. A path can only have weight smaller than 
the ideal weight of the graph if and only if there exists a negative cycle 
along the path. That is, the ideal weight is the smallest possible weight
a path can have without using a negative weight cycle.

Modified BFS from wikipedia (modifications are commented):

<source lang="java" line>
Step-Expansion(G, v):
    
    for each node n in G:            
        n.distance = INFINITY        
        n.parent = NIL

    factor = NIL                                        //initialize factor
    absoluteMinimum = 0                                 //initialize minimum possible weight
    
    for each edge e in G                                //initialize step for each edge
        factor = min(factor, e.weight)                  //factor = minimum edge weight in graph
        e.step = e.weight                               //step for each edge is initialized to edge weight
        if(e.weight<0)                                  //if the edge is negative
            absoluteMinimum += e.weight                 //contribute to absolute minimum possible weight of graph

    create empty queue Q                                //this queue will hold vertices and edges
                                                        //as opposed to just vertices

    v.distance = 0
    Q.enqueue(v)                      

    while Q is not empty:        
    
        u = Q.dequeue()
    
        if(u is vertex)                                 //if this is avertex 
            for each edge e from u to n:                //for each outgoing edge
                step(e)                                 //run step-subroutine 
        if(u is edge)                                   //if this is an edge
            step(u)                                     //run step-subroutine on this edge
            
</source>

<source lang="java" line>
step(Edge e):

    if e.step > factor                              //if edge requires stepping
        e.step = e.step - 1                         //step on edge
        Q.enqueue(e)                                //enqueue this edge
    else if n.distance > n.distance + e.weight:     //else if node is not visited
        if n.distance < absoluteMinimum             //detect negative cycle
            n.distance = -INF                       //set distance as -INF
        else
            n.distance = u.distance + e.weight      //relax using weight of edge
        n.parent = u
        Q.enqueue(n)
</source>

