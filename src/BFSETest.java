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
		System.out.println("test007 negative edge weights");
		
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
		System.out.println("test010 negative cycle");
		
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
		System.out.println("test011 negative cycle");
		
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
		System.out.println("test012 negative cycle");
		
		BFSE bfse = new BFSE();
	
		bfse.addEdge(1, 0, 1);
		bfse.addEdge(-2, 1, 0);
		bfse.addEdge(1, 2, 1);
		
		bfse.sssp(2);
	}
	
	@Test //massive weight range
	public void test013(){
		System.out.println("test013 massive weight range");
		
		BFSE bfse = new BFSE();
	
		bfse.addEdge(1, 0, 1);
		bfse.addEdge(0x0F_FF_FF_FF - 1, 0, 2);
		
		bfse.sssp(0);
	}
	
	@Test //large weight range
	public void test014(){
		
		BFSE bfse = new BFSE();
	
		bfse.addEdge(1, 0, 1);
		bfse.addEdge(1000, 0, 2);
		
		bfse.sssp(0);
	}
	
	@Test //large weight range
	public void test015(){
		
		BFSE bfse = new BFSE();
	
		bfse.addEdge(1, 0, 1);
		bfse.addEdge(10000, 0, 2);
		
		bfse.sssp(0);
	}
	
	@Test //large weight range
	public void test016(){
		System.out.println("test016 many equal large edges");
		
		BFSE bfse = new BFSE();
	
		bfse.addEdge(1, 0, 1);
		bfse.addEdge(10000, 0, 2);
		bfse.addEdge(10000, 0, 3);
		bfse.addEdge(10000, 0, 4);
		bfse.addEdge(10000, 0, 5);
		bfse.addEdge(10000, 0, 6);
		bfse.addEdge(10000, 0, 7);
		bfse.addEdge(10000, 0, 8);
		bfse.addEdge(10000, 0, 9);
		bfse.addEdge(10000, 0, 10);
		bfse.addEdge(10000, 0, 11);
		bfse.addEdge(10000, 0, 12);
		
		bfse.sssp(0);
	}
	
	@Test //large weight range
	public void test017(){
		System.out.println("test017 many unique large edges");
		
		BFSE bfse = new BFSE();
	
		bfse.addEdge(1, 0, 1);
		bfse.addEdge(10000, 0, 2);
		bfse.addEdge(10001, 0, 3);
		bfse.addEdge(10002, 0, 4);
		bfse.addEdge(10003, 0, 5);
		bfse.addEdge(10004, 0, 6);
		bfse.addEdge(10005, 0, 7);
		bfse.addEdge(10006, 0, 8);
		bfse.addEdge(10007, 0, 9);
		bfse.addEdge(10008, 0, 10);
		bfse.addEdge(10009, 0, 11);
		bfse.addEdge(10010, 0, 12);
		
		bfse.sssp(0);
	}
	
	@Test //depth test
	public void test018(){
		System.out.println("test018 depth test");
		
		BFSE bfse = new BFSE();
	
		bfse.addEdge(1, 0, 1);
		bfse.addEdge(1, 0, 2);
		bfse.addEdge(1, 2, 3);
		bfse.addEdge(1, 3, 4);
		bfse.addEdge(1, 5, 5);
		bfse.addEdge(1, 1, 6);
		bfse.addEdge(1, 6, 7);
		bfse.addEdge(1, 7, 8);
		bfse.addEdge(1, 3, 9);
		bfse.addEdge(1, 9, 10);
		bfse.addEdge(1, 10, 11);
		bfse.addEdge(1, 10, 12);
		
		bfse.sssp(0);
	}
	
	@Test //depth test
	public void test019(){
		System.out.println("test019 depth test with weight");
		
		BFSE bfse = new BFSE();
	
		bfse.addEdge(1, 0, 1);
		bfse.addEdge(1, 0, 2);
		bfse.addEdge(1, 2, 3);
		bfse.addEdge(1, 3, 4);
		bfse.addEdge(1, 5, 5);
		bfse.addEdge(1, 1, 6);
		bfse.addEdge(1, 6, 7);
		bfse.addEdge(1, 7, 8);
		bfse.addEdge(1, 3, 9);
		bfse.addEdge(1, 9, 10);
		bfse.addEdge(1, 10, 11);
		bfse.addEdge(1, 10, 12);
		bfse.addEdge(2, 0, 4);
		
		bfse.sssp(0);
	}

}
