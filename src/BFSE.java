import java.util.*;
import java.io.*;

public class BFSE {
	
	private static final Integer INF = 0x0F_FF_FF;
	private ArrayList<ArrayList<Edge>> adjList;
	private ArrayList<Integer> distance;
	private ArrayList<Integer> parent;
	private int factor;

	// O(1) initialization
	public BFSE() {
		super();
		
		this.adjList = new ArrayList<ArrayList<Edge>>();
		this.distance = new ArrayList<Integer>();
		this.parent = new ArrayList<Integer>();
	}
	
	// O((initial - V) + (terminal - V)) insertion
	public void addEdge(Edge edge){
		
		int initial = edge.getInitial();
		int terminal = edge.getTerminal();
		
		//factor set to minimum edge weight
		if(factor == 0)
			factor=edge.getWeight();
		else if(factor>edge.getWeight())
			factor = edge.getWeight();
		
		//increase capacity to fit new initial vertex
		if(initial + 1>adjList.size()){
			int amountToAdd = initial + 1 - adjList.size();
			adjList.addAll(Collections.nCopies(amountToAdd, null));
			distance.addAll(Collections.nCopies(amountToAdd, INF));
			parent.addAll(Collections.nCopies(amountToAdd, -1));
		}
		
		//increase capacity to fit new terminal vertex
		if( terminal + 1 > adjList.size()){
			int amountToAdd = terminal + 1 - adjList.size();
			adjList.addAll(Collections.nCopies(amountToAdd, null));
			distance.addAll(Collections.nCopies(amountToAdd, INF));
			parent.addAll(Collections.nCopies(amountToAdd, -1));
		}		
		
		//initialize this vertex's edgelist if null
		if(adjList.get(initial)==null){
			adjList.set(initial, new ArrayList<Edge>());
		}
		
		//update the arrays
		adjList.get(initial).add(edge);
	}
	
	public void addEdge(int weight, int initial, int terminal){
		addEdge(new Edge( weight,  initial,  terminal));
	}

	// O(V+E*w(E))
	public ArrayList<Integer> sssp(int source){
		
		//copy over everything so we don't destroy the original graph
		ArrayList<Integer> ssspParent = new ArrayList<Integer>(parent);
		ArrayList<Integer> ssspDistance = new ArrayList<Integer>(distance);
		ArrayList<ArrayList<Edge>> ssspAdjList = new ArrayList<ArrayList<Edge>>(adjList);
		Queue<Integer> queue = new LinkedList<Integer>();
		
		//set source values
		ssspDistance.set(source, 0);
		queue.add(source);
		
		while(!queue.isEmpty()){
			
			int initial = queue.poll();
			Iterator<Edge> it;
			
			//if this vertex has outgoing edges
			if(ssspAdjList.get(initial)!=null){
				
				it = ssspAdjList.get(initial).iterator();
				
				//for each edge from this vertex
				while(it.hasNext()){
					//get the edge
					Edge edge = it.next();
					int weight = edge.getWeight();
					int step = edge.getStep();
					int terminal = edge.getTerminal();
				
					//check to see if this 
					//edge is fully traversed
					if(step>factor){
						
						//if the edge is not 
						//traversed, then move
						//up edge one step
						edge.setStep(step-1);
						queue.add(initial);
					
					}
					//check to see if the
					//terminal vertex at this
					//edge has been visited
					else if(ssspDistance.get(terminal) == INF){
					
						//relax this edge
						ssspDistance.set(terminal, ssspDistance.get(initial)+weight);
						ssspParent.set(terminal,initial);
						queue.add(terminal);
						
						//important: do not revisit this edge
						//when stepping through the edges
						//of old vertices
						it.remove();
					}
				}
			}
		}
		
		System.out.println("Distances: " + ssspDistance);
		System.out.println("Parents: " + ssspParent);
		System.out.println();
		
		return ssspDistance;
	}
	

	@Override
	public String toString() {
		return "BFSE [adjList=" + adjList + "]";
	}
	
	
}