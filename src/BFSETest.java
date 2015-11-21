import static org.junit.Assert.*;

import org.junit.Test;

public class BFSETest {

	@Test
	public void test() {
		assertEquals(true, (new Edge(1,0,1).compareTo(new Edge(1,0,1))==0));
		assertEquals(true, (new Edge(2,0,1).compareTo(new Edge(1,0,1))==1));
		assertEquals(true, (new Edge(1,1,2).compareTo(new Edge(1,0,1))==1));
		assertEquals(true, (new Edge(1,1,2).compareTo(new Edge(1,1,1))==1));
	}
	
	@Test
	public void test2(){
		
		BFSE bfse = new BFSE();
		
		bfse.addEdge(3, 0, 1);
		bfse.addEdge(1, 0, 2);
		bfse.addEdge(1, 2, 1);
		
		bfse.sssp(0);
		
	}
	
	@Test
	public void test3(){
		
		BFSE bfse = new BFSE();
		
		bfse.addEdge(3, 0, 1);
		bfse.addEdge(2, 0, 2);
		bfse.addEdge(1, 2, 1);

		bfse.sssp(0);
		
	}
	
	@Test
	public void test4(){
		
		BFSE bfse = new BFSE();
		
		bfse.addEdge(4, 0, 1);

		bfse.sssp(0);
		
	}
	
	@Test
	public void test5(){
		
		BFSE bfse = new BFSE();
		
		bfse.addEdge(4, 0, 1);
		bfse.addEdge(5, 1, 2);

		bfse.sssp(0);
		
	}
	
	@Test
	public void test6(){
		
		BFSE bfse = new BFSE();
		
		bfse.addEdge(4, 0, 1);
		bfse.addEdge(5, 1, 2);
		bfse.addEdge(3, 0, 3);
		bfse.addEdge(3, 3, 2);

		bfse.sssp(0);
		
	}
	
	//negative edge weights
	@Test
	public void test7(){
		
		BFSE bfse = new BFSE();
		
		bfse.addEdge(-2, 0, 1);
		bfse.addEdge(-1, 0, 2);
		bfse.addEdge(-1, 2, 1);

		bfse.sssp(0);
		
	}
	
	//cycles
	@Test
	public void test8(){
		
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
	public void test9(){
		
		BFSE bfse = new BFSE();
		
		bfse.addEdge(1, 0, 1);
		bfse.addEdge(1, 1, 2);
		bfse.addEdge(1, 2, 3);
		bfse.addEdge(1, 3, 0);
		bfse.addEdge(2, 0, 3);
		
		bfse.sssp(0);
	}

}
