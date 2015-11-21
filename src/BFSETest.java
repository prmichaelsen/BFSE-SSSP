import static org.junit.Assert.*;

import org.junit.Test;

public class BFSETest {

	@Test
	public void test000() {
		assertEquals(true, (new Edge(1,0,1).compareTo(new Edge(1,0,1))==0));
		assertEquals(true, (new Edge(2,0,1).compareTo(new Edge(1,0,1))==1));
		assertEquals(true, (new Edge(1,1,2).compareTo(new Edge(1,0,1))==1));
		assertEquals(true, (new Edge(1,1,2).compareTo(new Edge(1,1,1))==1));
	}
	
	@Test
	public void test002(){
		
		BFSE bfse = new BFSE();
		
		bfse.addEdge(3, 0, 1);
		bfse.addEdge(1, 0, 2);
		bfse.addEdge(1, 2, 1);
		
		bfse.sssp(0);
		
	}
	
	@Test
	public void test003(){
		
		BFSE bfse = new BFSE();
		
		bfse.addEdge(3, 0, 1);
		bfse.addEdge(2, 0, 2);
		bfse.addEdge(1, 2, 1);

		bfse.sssp(0);
		
	}
	
	@Test
	public void test004(){
		
		BFSE bfse = new BFSE();
		
		bfse.addEdge(4, 0, 1);

		bfse.sssp(0);
		
	}
	
	@Test
	public void test005(){
		
		BFSE bfse = new BFSE();
		
		bfse.addEdge(4, 0, 1);
		bfse.addEdge(5, 1, 2);

		bfse.sssp(0);
		
	}
	
	@Test
	public void test006(){
		
		BFSE bfse = new BFSE();
		
		bfse.addEdge(4, 0, 1);
		bfse.addEdge(5, 1, 2);
		bfse.addEdge(3, 0, 3);
		bfse.addEdge(3, 3, 2);

		bfse.sssp(0);
		
	}
	
	//negative edge weights
	@Test
	public void test007(){
		
		BFSE bfse = new BFSE();
		
		bfse.addEdge(-2, 0, 1);
		bfse.addEdge(-1, 0, 2);
		bfse.addEdge(-1, 2, 1);

		bfse.sssp(0);
		
	}
	
	//cycles
	@Test
	public void test008(){
		
		BFSE bfse = new BFSE();
		
		bfse.addEdge(1, 0, 1);
		bfse.addEdge(1, 1, 2);
		bfse.addEdge(1, 2, 3);
		bfse.addEdge(1, 3, 0);
		bfse.addEdge(4, 0, 3);
		
		bfse.sssp(0);
	}
	
	//cycles
	@Test
	public void test009(){
		
		BFSE bfse = new BFSE();
		
		bfse.addEdge(1, 0, 1);
		bfse.addEdge(1, 1, 2);
		bfse.addEdge(1, 2, 3);
		bfse.addEdge(1, 3, 0);
		bfse.addEdge(2, 0, 3);
		
		bfse.sssp(0);
	}
	
	@Test //negative cycles
	//exp output for
	//distance: [-inf,-inf,-inf]
	public void test010(){
		
		BFSE bfse = new BFSE();
		
		bfse.addEdge(1, 0, 1);
		bfse.addEdge(1, 1, 2);
		bfse.addEdge(-3, 2, 0);
		
		bfse.sssp(0);
	}
	
	@Test //negative cycles
	//exp output for
	//distance: [-inf,-inf,-inf, 0, 1]
	public void test011(){
		
		BFSE bfse = new BFSE();
		
		bfse.addEdge(1, 3, 4);
		bfse.addEdge(1, 4, 0);
		bfse.addEdge(1, 0, 1);
		bfse.addEdge(1, 1, 2);
		bfse.addEdge(-3, 2, 0);
		
		bfse.sssp(3);
	}
	
	@Test //negative cycles
	//exp output for
	//distance: [-inf,-inf, 0]
	public void test012(){
		
		BFSE bfse = new BFSE();
	
		bfse.addEdge(1, 0, 1);
		bfse.addEdge(-2, 1, 0);
		bfse.addEdge(1, 2, 1);
		
		bfse.sssp(2);
	}

}
