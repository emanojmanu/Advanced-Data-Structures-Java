// Ver 1.0:  Starter code for Indexed heaps

import java.util.Comparator;
class Comp implements Comparator<Vertex>
{
	@Override
	public int compare(Vertex o1, Vertex o2) {
		// TODO Auto-generated method stub
		if(o1.d>o2.d)
		{
			return 1;
		}
		else if(o1.d<o2.d)
		{
			return -1;
		}
		return 0;
	}
}

class CompDikshatra<T extends Comparable<? super T>> implements Comparator<T>
{
	@Override
	public int compare(T o1, T o2) {
		// TODO Auto-generated method stub
		/*if(o1.)
		{
			return 1;
		}
		else if(o1.d<o2.d)
		{
			return -1;
		}
*/		return o1.compareTo(o2);
	}
}


public class IndexedHeap<T extends Index> extends BinaryHeap<T>{
    /** Build a priority queue with a given array q */
    IndexedHeap(T[] q, Comparator<T> comp) {
	super(q, comp);
    }
    
    /** Create an empty priority queue of given maximum size */
    IndexedHeap(int n, Comparator<T> comp) {
	super(n, comp);
    }
    /** restore heap order property after the priority of x has decreased */
    void decreaseKey(T x) {
	percolateUp(x.getIndex());
    }
}
