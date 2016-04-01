package graph;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import static java.util.Arrays.asList;

/** Unit testing for Traversal classes
 *  @author Jason Wu
 */

public class TraversalTesting {

	public class DepthFirst extends DepthFirstTraversal {
	    /** A depth-first Traversal of G, using FRINGE as the fringe. */
	    protected DepthFirst(Graph G) {
	        super(G);
	    }

	    @Override
	    protected boolean visit(int v) {
	    	actual.add(v);
	        return super.visit(v);
	    }

	    @Override
	    protected boolean postVisit(int v) {
	    	postactual.add(v);
	        return super.postVisit(v);
	    }


	}

	public class BreadthFirst extends BreadthFirstTraversal {
			/** A depth-first Traversal of G, using FRINGE as the fringe. */
	    protected BreadthFirst(Graph G) {
	        super(G);
	    }

	    @Override
	    protected boolean visit(int v) {
	    	actual.add(v);
	        return super.visit(v);
	    }
	}

	@Test
	public void DepthFirstTest1() {
		DirectedGraph g = new DirectedGraph();
		g.add();
		g.add();
		g.add();
		g.add();
		g.add();
		g.add(5, 4);
		g.add(5, 3);
		g.add(4, 1);
		g.add(3, 2);
		g.add(1, 5);
		ArrayList<Integer> actual = new ArrayList<Integer>();
		DepthFirst depthTraverse = new DepthFirst(g);
		List<Integer> a = new ArrayList<Integer>();
		a.add(5);
		a.add(4);
		a.add(1);
		a.add(3);
		a.add(2);
		List<Integer> b = new ArrayList<Integer>();
		b.add(5);
		b.add(3);
		b.add(2);
		b.add(4);
		b.add(1);
		assertEquals(actual, a || b);
	}

	@Test
	public void DepthFirstTest2() {
		DirectedGraph g = new DirectedGraph();
		g.add();
		g.add();
		g.add();
		g.add();
		g.add();
		g.add(5, 4);
		g.add(5, 3);
		g.add(4, 1);
		g.add(3, 2);
		g.add(1, 5);
		ArrayList<Integer> postactual = new ArrayList<Integer>();
		DepthFirst depthTraverse = new DepthFirst(g);
		List<Integer> a = new ArrayList<Integer>();
		a.add(1);
		a.add(4);
		a.add(2);
		a.add(3);
		a.add(5);
		List<Integer> b = new ArrayList<Integer>();
		b.add(2);
		b.add(3);
		b.add(1);
		b.add(4);
		b.add(5);
		assertEquals(postactual, a || b);
	}

	@Test
	public void BreadthFirstTest() {
		DirectedGraph g = new DirectedGraph();
		g.add();
		g.add();
		g.add();
		g.add();
		g.add();
		g.add(5, 4);
		g.add(5, 3);
		g.add(4, 1);
		g.add(3, 2);
		g.add(1, 5);
		ArrayList<Integer> actual = new ArrayList<Integer>();
		BreadthFirst breadthTraverse = new BreadthFirst(g);
		List<Integer> c = new ArrayList<Integer>();
		c.add(5);
		c.add(4);
		c.add(3);
		c.add(1);
		c.add(2);
		List<Integer> d = new ArrayList<Integer>();
		d.add(5);
		d.add(3);
		d.add(4);
		d.add(2);
		d.add(1);
		List<Integer> e = new ArrayList<Integer>();
		e.add(5);
		e.add(4);
		e.add(3);
		e.add(2);
		e.add(1);
		List<Integer> f = new ArrayList<Integer>();
		f.add(5);
		f.add(4);
		f.add(3);
		f.add(1);
		f.add(2);
		assertEquals(actual, c || d || e || f);
	}
}