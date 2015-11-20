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

}
