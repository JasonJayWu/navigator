package graph;
import java.util.Comparator;

public class pathComparator implements Comparator<Integer> {
	/*
	class Comparer extends SimpleShortestPaths {

		public Comparer(Graph G, int source, int dest) {
			super(G, source, dest);
		}

		@Override
		protected double estimatedDistance(int v) {
			return super.estimatedDistance(v);
		}
	}*/
/*
	@Override
	public int compare(Graph G, int source, int dest, int x, int y) {
		Comparer S = new Comparer(G, source, dest);
		double xDistVal = Comparer.getWeight(x) +
		Comparer.estimatedDistance(x);
		double yDistVal = Comparer.getWeight(y) +
		Comparer.estimatedDistance(y);
			return xDistVal - yDistVal;
	}*/

	//@Override
	public int compare(Integer xDistVal, Integer yDistVal) {
		if (xDistVal < yDistVal) {
			return -1;
		} else if (xDistVal > yDistVal) {
			return 1;
		} else {
			return 0;
		}
	}
}