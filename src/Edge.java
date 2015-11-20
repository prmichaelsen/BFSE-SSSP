
public class Edge implements Comparable<Edge>{

	private int weight, initial, terminal, step;

	public Edge(int weight, int initial, int terminal) {
		super();
		this.weight = weight;
		this.initial = initial;
		this.terminal = terminal;
		this.step = weight;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getInitial() {
		return initial;
	}

	public void setInitial(int initial) {
		this.initial = initial;
	}

	public int getTerminal() {
		return terminal;
	}

	public void setTerminal(int terminal) {
		this.terminal = terminal;
	}

	@Override
	public int compareTo(Edge o) {
		int result = 0;
		if((result = weight-o.weight)==0)
			if((result = initial - o.initial) == 0)
				if((result = terminal - o.terminal) == 0)
					return result;
		return result;
	}
	

	@Override
	public String toString() {
		return "Edge [weight=" + weight + ", initial=" + initial + ", terminal=" + terminal + "]";
	}
	
	
}
