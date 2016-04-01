
package graph;
import org.junit.Test;
import static org.junit.Assert.*;

public class Tests {
	
	@Test
	public void GraphTests() {
		DirectedGraph g = new DirectedGraph();
		g.add();
		g.add();
		g.add();
		g.add(1, 3);
		g.add(3, 2);
		g.add(2, 1);
		assertEquals(3, g.maxVertex());
		assertEquals(2, g.successor(3, 0));
		assertEquals(1, g.predecessor(3, 0));
		assertEquals(0, g.predecessor(3, 2));
		g.add(2, 3);
		g.add();
		g.remove(2, 1);
		g.remove(2);
		assertEquals(4, g.maxVertex());
		assertEquals(3,g.vertexSize());
	}

	public static void main(String[] args) {
		System.exit(ucb.junit.textui.runClasses(Tests.class));
	}
}