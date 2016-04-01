package graph;

/* See restrictions in Graph.java. */

import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.*;
import java.util.Comparator;

/** The shortest paths through an edge-weighted labeled graph of type GRAPHTYPE.
 *  By overrriding methods getWeight, setWeight, getPredecessor, and
 *  setPredecessor, the client can determine how to get parameters of the
 *  search and to return results.  By overriding estimatedDistance, clients
 *  can search for paths to specific destinations using A* search.
 *  @author Jason Wu
 */
public abstract class ShortestPaths {

    /** The shortest paths in G from SOURCE. */
    public ShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public ShortestPaths(Graph G, int source, int dest) {
        _G = G;
        _source = source;
        _dest = dest;
        Iteration<Integer> iterator = G.vertices();
        while (iterator.hasNext()) {
            allVertices.add(iterator.next());
        }
    }

    /** Initialize the shortest paths.  Must be called before using
     *  getWeight, getPredecessor, and pathTo. */
    public void setPaths() {
        Comparator comparator = new vertComparator();
        PriorityQueue<Integer> vertex =
        new PriorityQueue<Integer>(20, comparator);

        for (int v = 1; v < _G.maxVertex() + 1; v += 1) {
            setWeight(v, Double.POSITIVE_INFINITY);
        }
        setWeight(_source, 0);
        for (int i = 0; i < allVertices.size(); i += 1) {
            vertex.add(allVertices.get(i));
        }
        while (!vertex.isEmpty()) {
            int v = vertex.remove();
            if (v == _dest) {
                return;
            }
            Iteration vSuccessor = _G.successors(v);
            while (vSuccessor.hasNext()) {
                int w = (int) vSuccessor.next();
                if (getWeight(w) > getWeight(v, w) + getWeight(v)) {
                    setWeight(w, getWeight(v, w) + getWeight(v));
                    setPredecessor(w, v);
                    vertex.remove(w);
                    vertex.add(w);
                }
            }
        }
    }

    /** Comparator to compare "smallness" of vertices. */
    class vertComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer xVert, Integer yVert) {
            double xTotalDist = getWeight(xVert) + estimatedDistance(xVert);
            double yTotalDist = getWeight(yVert) + estimatedDistance(yVert);
            if (xTotalDist < yTotalDist) {
                return -1;
            } else if (xTotalDist > yTotalDist) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    /** Returns the starting vertex. */
    public int getSource() {
        return _source;
    }

    /** Returns the target vertex, or 0 if there is none. */
    public int getDest() {
        return _dest;
    }

    /** Returns the current weight of vertex V in the graph.  If V is
     *  not in the graph, returns positive infinity. */
    public abstract double getWeight(int v);

    /** Set getWeight(V) to W. Assumes V is in the graph. */
    protected abstract void setWeight(int v, double w);

    /** Returns the current predecessor vertex of vertex V in the graph, or 0 if
     *  V is not in the graph or has no predecessor. */
    public abstract int getPredecessor(int v);

    /** Set getPredecessor(V) to U. */
    protected abstract void setPredecessor(int v, int u);

    /** Returns an estimated heuristic weight of the shortest path from vertex
     *  V to the destination vertex (if any).  This is assumed to be less
     *  than the actual weight, and is 0 by default. */
    protected double estimatedDistance(int v) {
        return 0.0;
    }

    /** Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     *  not in the graph, returns positive infinity. */
    protected abstract double getWeight(int u, int v);

    /** Returns a list of vertices starting at _source and ending
     *  at V that represents a shortest path to V.  Invalid if there is a
     *  destination vertex other than V. */
    public List<Integer> pathTo(int v) {
        Comparator comparator = new vertComparator();
        PriorityQueue<Integer> vertex =
        new PriorityQueue<Integer>(20, comparator);
        ArrayList<Integer> path = new ArrayList<Integer>();
        for (int i = 1; i < _G.maxVertex() + 1; i += 1) {
            setWeight(i, Double.POSITIVE_INFINITY);
        }
        setWeight(_source, 0);
        for (int i = 0; i < allVertices.size(); i += 1) {
            vertex.add(allVertices.get(i));
        }
        while (!vertex.isEmpty()) {
            int vert = vertex.remove();
            if (vert == v) {
                break;
            }
            Iteration vSuccessor = _G.successors(vert);
            while (vSuccessor.hasNext()) {
                int w = (int) vSuccessor.next();
                if (getWeight(w) > getWeight(vert, w) + getWeight(vert)) {
                    setWeight(w, getWeight(vert, w) + getWeight(vert));
                    setPredecessor(w, vert);
                    vertex.remove(w);
                    vertex.add(w);
                }
            }
        }
        path.add(v);
        while (v != _source) {
            path.add(getPredecessor(v));
            v = getPredecessor(v);
        }
        Collections.reverse(path);
        return path;
    }

    /** Returns a list of vertices starting at the source and ending at the
     *  destination vertex. Invalid if the destination is not specified. */
    public List<Integer> pathTo() {
        return pathTo(getDest());
    }

    /** The graph being searched. */
    protected final Graph _G;
    /** The starting vertex. */
    private final int _source;
    /** The target vertex. */
    private final int _dest;
    /** Priority queue of different vertices ordered by distances values
     *  with smaller vertices having higher priorities. */
    private PriorityQueue<Integer> vertices = new PriorityQueue<Integer>();
    /** List of all vertices. */
    private ArrayList<Integer> allVertices = new ArrayList<Integer>();
}
