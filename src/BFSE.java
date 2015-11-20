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
	
	
	// O(1) insertion
	public void addEdge(Edge edge){
		
		int initial = edge.getInitial();
		
		//factor set to minimum edge weight
		if(factor>edge.getWeight())
			factor = edge.getWeight();
		
		//increase capacity to fit new vertex
		if(initial + 1>adjList.size()){
			int amountToAdd = initial + 1 - adjList.size();
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
	
}