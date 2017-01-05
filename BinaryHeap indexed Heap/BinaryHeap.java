// Ver 1.0:  Starter code for Binary Heap implementation

import java.util.Arrays;
import java.util.Comparator;

public class BinaryHeap<T> implements PQ<T> {
    Object[] pq;
    Comparator<T> c;
    int sizefilled;
    /** Build a priority queue with a given array q */
    BinaryHeap(Object[] q, Comparator<T> comp) {
	pq = q;
	c = comp;
	sizefilled=0;
	buildHeap();
	
    }

    boolean isEmpty()
    {
    	return sizefilled<=0;
    }
    /** Create an empty priority queue of given maximum size */
    @SuppressWarnings("unchecked")
	BinaryHeap(int n, Comparator<T> comp) { /* to be implemented */
    
    	pq=new Object[n+1];
    for(int i=0;i<n+1;i++)
    {
    	pq[i]=new Object();
    }
    c=comp;
    }

    public void insert(T x) {
	add(x);
    
    }

    public T deleteMin() {
	return remove();
    }

    public T min() { 
	return peek();
    }

    public void add(T x) { /* to be implemented */
    	int hole=sizefilled+1;
    	pq[0]=x;
    //	System.out.println(hole);
    	percolateUp(hole);
    	sizefilled++;
      }

    public T remove() { /* to be implemented */
	
    	T dummy=(T) pq[1];
    	if(sizefilled>=1)
    	{
    		//System.out.println("in size fikked " +sizefilled);
    	    pq[1]=pq[sizefilled];
    	Vertex v=(Vertex) pq[1];
    	v.putIndex(1);
    	sizefilled--;
    	percolateDown(1);
    	}
    	else{
    		pq[1]=null;
    	}
    	return dummy;
    }

    public T peek() { /* to be implemented */
	return (T) pq[1];
    }

    /** pq[i] may violate heap order with parent */
    void percolateUp(int i) { /* to be implemented */
    	int hole=i; 	
    	T x=(T) pq[i];
    	pq[0]=x;
    /*	System.out.println(x);
    	System.out.println(hole/2+"e");
    	System.out.println(c.compare(x,(T)pq[hole/2]));
    */	while(c.compare(x,(T)pq[hole/2])<0)
    	{
    		//System.out.println("sdsd");
    		pq[hole]=pq[hole/2];
    		move(hole,pq,(T)pq[hole]);	
    		hole=hole/2;
    		//System.out.println("hole/2" +hole);
    	}
    	pq[hole]=pq[0];
    	move(hole,pq,x);
    	//sizefilled++;
    }

    private void move(int hole, Object[] pq2, T x) {
		// TODO Auto-generated method stub
   
	/*Vertex[] o=new Vertex[pq2.length];
	*/
	Vertex d=(Vertex) pq2[hole];
	d.putIndex(hole);
	//o[hole].putIndex(hole);
    }

	/** pq[i] may violate heap order with children */
    void percolateDown(int i) { /* to be implemented */
    	int schild=0;
    	boolean flag=true;
    	while(flag)
    	{
			if (i * 2 > sizefilled)// no children
			{
				flag=false;
			}
			else { // has children
				if (2 * i + 1 <= sizefilled)// two children
				{
					schild = (c.compare((T)pq[2 * i],(T) pq[2 * i + 1]) > 0 ? (2 * i + 1) : (2 * i));
				}
				else {
					schild=2*i;
					}
				
				if(c.compare((T)pq[i],(T) pq[schild])>0)
				{
					T dumm=(T) pq[i];
					pq[i]=pq[schild];
					Vertex c=(Vertex) pq[i];
					c.putIndex(i);
					pq[schild]=dumm;
					Vertex p=(Vertex) pq[schild];
					p.putIndex(schild);
					i=schild;
				}
				else{
					flag=false;
				}				
			}
		}
    }

    /** Create a heap.  Precondition: none. */
    void buildHeap() {
    	int n=pq.length;
    	int q=n/2;
    	sizefilled=n-1;
    	Vertex v=null;
    	while(q>0)
    	{
    	v=(Vertex) pq[q];
    		v.putIndex(q);
    		percolateDown(q);
    		q--;
    	}
    	v=(Vertex) pq[1];
    	v.putIndex(1);//since 0 is not used to fill
    	
    	//to populate next half indexes in the q 
    	q=n/2;
    	while(q<sizefilled+1)
    	{
    		v=(Vertex) pq[q];
    		v.putIndex(q);
    		q++;
    	}
    }
    /* sort array A[1..n].  A[0] is not used. 
       Sorted order depends on comparator used to buid heap.
       min heap ==> descending order
       max heap ==> ascending order
     *///Assuming that we are passing  a heap here in A from 1...n elements
    public static<T> void heapSort(Object[] A, Comparator<Vertex> comp) { /* to be implemented */
    	int count=1;
    	int last=A.length-1;
    	int firstcount=count;
    	Object[] n=new Object[A.length];
    	int index=1;
    	while(firstcount<A.length&&index<A.length)
    	{
    		n[index]=A[firstcount];
    		A[firstcount]=A[last];
        	last--;
        	int schild=0;
        	boolean flag=true;
        	count=firstcount;
        	while(flag)
        	{
        	//	count=firstcount;
    			if (count * 2 > last) // no children
    			{
    				flag=false;
    			}
    			else 
    			{ 
    				// has children
    				if (2 * count + 1 <= last)// two children
    				{
    					schild = (comp.compare((Vertex)A[2 * count],(Vertex) A[2 * count + 1]) > 0 ? (2 * count + 1) : (2 * count));
    	
    				}
    				else { // one child
    					schild=2*count;
    				}				
    				if(comp.compare((Vertex)A[count],(Vertex)A[schild])>0)
    				{
    					T dumm=(T)A[count];
    					A[count]=A[schild];
    					A[schild]=dumm;
    					count=schild;
    				}
    				else{
    					flag=false;
    				}		
    			}
    		}
    		//firstcount++;
    		index++;
    		//A[firstcount-1]=dummy;
    	}
     	for(int i=1;i<A.length;i++)
    	{
    		A[i]=(T) n[i];
    	}
    	for(int i=1;i<A.length;i++)
    	{	
    	System.out.println("As"+A[i]);
    	}
    }
    
    public  void heapSort1(Object[] A, Comparator<T> comp) { /* to be implemented */
    	int count=1;
    	int last=A.length-1;
    	int firstcount=count;
    	Object[] g=new Object[A.length];	
    	for(int i=1;i<A.length;i++){
    		//A[i]=remove();
    		g[i]=new Object();
    		g[i]=remove();
    	}
    	for(Object h:g)
    	{
    		System.out.println(h);
    	}
	  }
    public static void main(String argd[])
    {
    	Integer[] i={0,2,54,6,76,4,34,23,121,54,90};
		/*BinaryHeap<Integer> bh=new BinaryHeap<>(i, new Comp<Integer>());
		heapSort(i, new Comp());*/
    }
}
