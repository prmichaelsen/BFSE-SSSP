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
