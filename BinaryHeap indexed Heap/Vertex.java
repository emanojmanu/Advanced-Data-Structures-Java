/**
 * Class to represent a vertex of a graph
 * @author rbk
 *
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;

public class Vertex implements Index, Comparator<Vertex>, Iterable<Edge> {
    int name; // name of the vertex
    boolean seen; // flag to check if the vertex has already been visited
    int d;  Vertex p;  // fields used in algorithms of Prim and Dijkstra
    List<Edge> adj, revAdj; // adjacency list; use LinkedList or ArrayList
    ArrayList<Vertex> children;

    int index;
    public int getIndex() { // To Do
	return index;
    }

    public void putIndex(int i) { // To Do
    	this.index=i;
    }

    public int compare(Vertex u, Vertex v) { // To Do
	return (u.d>v.d?1:-1);
    }

    /**
     * Constructor for the vertex
     * 
     * @param n
     *            : int - name of the vertex
     */
    Vertex(int n) {
	name = n;
	seen = false;
	d = Integer.MAX_VALUE;
	p = null;
	adj = new ArrayList<Edge>();
	revAdj = new ArrayList<Edge>();   /* only for directed graphs */
	children=new ArrayList<>();
    }

    public Iterator<Edge> iterator() { return adj.iterator(); }

    /**
     * Method to represent a vertex by its name
     */
    public String toString() {
	return Integer.toString(name);
    }
}
