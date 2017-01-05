
/* Ver 1.0: Starter code for Prim's MST algorithm */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

class EdgeComparator implements Comparator<Edge>
{

	@Override
	public int compare(Edge o1, Edge o2) {
		// TODO Auto-generated method stu
		if(o1.weight>o2.weight)
		{
			return 1;
		}
		else if(o1.weight<o2.weight)
		{
			return -1;
		}
		return 0;
	}	
}

public class MST {
    static final int Infinity = Integer.MAX_VALUE;

    static int prim1(Graph g,Vertex s)
    {
    	int wmst=0;
    	Vertex v=s;
    	v.seen=true;
    	PriorityQueue<Edge> q=new PriorityQueue<>(new EdgeComparator());
    	for(Edge e:v.adj)
    	{
    		q.add(e);
    	}
    	while(!q.isEmpty()){
    		Edge removing=q.remove();		
    		Vertex unSeenver=null;
    		if(removing.from.seen&& removing.to.seen)
    		{
    			continue;
    		}
    		else if(removing.from.seen){
    			unSeenver=removing.to;
    			unSeenver.p=removing.from;
    			unSeenver.seen=true;
    			wmst=wmst+removing.weight;
    		}
    		else{
    			unSeenver=removing.from;
    			unSeenver.p=removing.to;
    			unSeenver.seen=true;
    			wmst=wmst+removing.weight;
    		}
    		if(unSeenver!=null)
    		{
    			for(Edge e:unSeenver.adj)
    	    	{
    	    		q.add(e);
    	    	}
    			//wmst++;
    		}	
    	}   	
    	return wmst;
    }
    static int PrimMST(Graph g, Vertex s)
    {
    	int l=prim1(g,s);
    //	System.out.println("h is "+l);
        int wmst = 0;
        s.d=0;        
        BinaryHeap<Vertex> q=new BinaryHeap<Vertex>(g.v.toArray(),new Comp());
        /*q.add(s);*/
       // q.heapSort(g.v.toArray(),new Comp());
        Vertex dummy=null;
        HashSet<Vertex> hs=new HashSet<>();
        //ArrayList<Vertex> hs=new ArrayList<>(); 
      //  hs.add(s);
        while(!q.isEmpty())
        {
        	//System.out.println("in q");
        	dummy=q.remove();
        	wmst=wmst+dummy.d;
            hs.add(dummy);      	
        	for(Edge e:dummy.adj)
        	{
        		Vertex otherend=e.otherEnd(dummy);
        		if(!hs.contains(otherend)&&e.weight<otherend.d)
        		{
        			otherend.d=e.weight;
        			otherend.p=dummy;
        			q.percolateUp(otherend.getIndex());
        		}
        	}
        }
        // Code for Prim's algorithm needs to be written
        return wmst;
    }

    public static void main(String[] args) throws FileNotFoundException {
	Scanner in;

        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }
	Graph g = Graph.readGraph(in);
        Vertex s = g.getVertex(1);
        Object[] i=g.v.toArray();
      /*  BinaryHeap<Vertex> bh=new BinaryHeap<>(i, new Comp());
        bh.buildHeap();
      */ 
       // int h= PrimMST(g,s);
       /* System.out.println("sa"+h);
       */System.out.println("result "+prim1(g,s));
        System.out.println(PrimMST(g, s));
       
        }
}
