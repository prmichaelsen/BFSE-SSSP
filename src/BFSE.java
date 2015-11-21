import java.util.*;
import java.io.*;

public class BFSE {
	
	private static final Integer INF = 0x0F_FF_FF_FF;
	private static final Integer NEG_INF = 0xF0_00_00_01;
	private ArrayList<ArrayList<Edge>> adjList;
	private ArrayList<Integer> distance;
	private ArrayList<Integer> parent;
	private int factor;
	private int absoluteMinimum;

	// O(1) initialization
	public BFSE() {
		super();
		
		this.adjList = new ArrayList<ArrayList<Edge>>();
		this.distance = new ArrayList<Integer>();
		this.parent = new ArrayList<Integer>();
		
		factor = 0;
		absoluteMinimum = 0;
	}
	
	// O((initial - V) + (terminal - V)) insertion
	public void addEdge(Edge edge){
		
		int initial = edge.getInitial();
		int terminal = edge.getTerminal();
		int weight = edge.getWeight();
		
		//factor set to minimum edge weight
		if(factor == 0)
			factor=weight;
		else if(factor>weight)
			factor = weight;
		
		if(weight<0)
			absoluteMinimum+=weight;
		
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
		Queue<Object> queue = new LinkedList<Object>();
		
		//set source values
		ssspDistance.set(source, 0);
		queue.add(source);
		
		while(!queue.isEmpty()){
			
			Object item = queue.poll();
			int initial;
			Edge expanded;
			
			// if this is a vertex
			// explore the neighbors
			if(item instanceof Integer){
				initial = (Integer) item;
				Iterator<Edge> it;
				
				//if this vertex has outgoing edges
				if(ssspAdjList.get(initial)!=null){
					
					it = ssspAdjList.get(initial).iterator();
					
					//for each edge from this vertex
					while(it.hasNext()){
						
						//get the edge
						Edge edge = it.next();
						step(edge, queue, ssspParent, ssspDistance);
					}
				}
			
			//if this is an edge
			//increment the edge's step
			}else if(item instanceof Edge){
				
				expanded = (Edge) item;
				step(expanded, queue, ssspParent, ssspDistance);
			}
		}
		
		System.out.println("Distances: " + ssspDistance);
		System.out.println("Parents: " + ssspParent);
		System.out.println();
		
		return ssspDistance;
	}
	
	public void step(Edge edge, Queue<Object> queue, 
			ArrayList<Integer> ssspParent, 
			ArrayList<Integer> ssspDistance){
	
		int weight = edge.getWeight();
		int initial = edge.getInitial();
		int terminal = edge.getTerminal();
		int step = edge.getStep();
	
		//check to see if this 
		//edge is fully traversed
		if(step>factor){
			
			//if the edge is not 
			//traversed, then move
			//up edge one step
			//and add the edge 
			//to the queue
			edge.setStep(step-1);
			queue.add(edge);
		}
		//check to see if the
		//terminal vertex at this
		//edge has been visited
		else if(ssspDistance.get(terminal) > ssspDistance.get(initial) + weight){
		
			//check for negative cycle
			if(ssspDistance.get(terminal)<absoluteMinimum){
				ssspDistance.set(terminal, NEG_INF);
			}else{
				//relax this edge
				ssspDistance.set(terminal, ssspDistance.get(initial)+weight);
			}
			
			ssspParent.set(terminal,initial);
			
			//mark the terminal vertex
			//for exploration
			queue.add(terminal);
		}
	}

	@Override
	public String toString() {
		return "BFSE [adjList=" + adjList + "]";
	}
	
	
}