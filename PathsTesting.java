package graph;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class PathsTesting {

	class VideoGraphPaths extends SimpleShortestPaths {

		public VideoGraphPaths(Graph G, int source, int dest) {
		super(G, source, dest);
		}

		@Override
		public double getWeight(int u, int v) {
			return super.getWeight(u, v);
		}

		@Override
		protected double estimatedDistance(int v) {
			return super.estimatedDistance(v);
		}

	}

	@Test
	public void testWeights() {
		//Graph videoGraph = generateVideoGraph();
		DirectedGraph v = new DirectedGraph();
		v.add();
		v.add();
		v.add();
		v.add();
		v.add();
		v.add();
		v.remove(1);
		v.add(4, 5);
		v.add(4, 2);
		v.add(4, 3);
		v.add(2, 3);
		v.add(5, 6);
		v.add(5, 3);
		VideoGraphPaths vgp = new VideoGraphPaths(v, 4, 3);
		vgp.setPaths();
		test stuff inside vgp
	}
}