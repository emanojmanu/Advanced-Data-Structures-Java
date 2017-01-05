/**
 * Class to represent a vertex of a graph
 * @author rbk
 *
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Vertex implements Iterable<Edge>{
    int name; // name of the vertex
    boolean seen; // flag to check if the vertex has already been visited
    int distance;
    int indexIter;// distance of vertex from a source
    List<Edge> adj, revAdj;
    int verifiedEdgeposition;
    // adjacency list; use LinkedList or ArrayList
    /**
     * Constructor for the vertex
     * 
     * @param n
     *            : int - name of the vertex
     */
    Vertex(int n) {
	name = n;
	verifiedEdgeposition=0;
	seen = false;
	distance = Integer.MAX_VALUE;
	adj = new ArrayList<Edge>();
	revAdj = new ArrayList<Edge>();   /* only for directed graphs */
    indexIter=0;
    }

    /**
     * Method to represent a vertex by its name
     */
    public String toString() {
	return Integer.toString(name);
	}

	@Override
	public Iterator<Edge> iterator() {
		// TODO Auto-generated method stub
	 return new EdgeIterator();
	}
	private class EdgeIterator<Edge> implements Iterator<Edge>
	{
             int index=0;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return index<adj.size();
		}

		@Override
		public Edge next() {
			// TODO Auto-generated method stub
			Edge e=null;
			if(hasNext())
			{				
			e=(Edge) adj.get(index);
			index++;
			}
			return e; 
		}
		
	}
}
