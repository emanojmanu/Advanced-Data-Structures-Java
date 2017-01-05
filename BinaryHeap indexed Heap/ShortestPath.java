
/* Ver 1.0: Starter code for Dijkstra's Shortest path algorithm */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

class ShortestInfo
{
 Vertex destination;
 int i;
 public ShortestInfo(Vertex v,int h)
 {
	 destination=v;
	 i=h;
 }
}
public class ShortestPath {
    static final int Infinity = Integer.MAX_VALUE;
    static void DijkstraShortestPaths(Graph g, Vertex s)
    {
        // Code for Dijkstra's algorithm needs to be written
	// Shortest paths will be stored in fields d,p in the Vertex class
    	Vertex u=null;
    	for(int i=1;i<=g.size;i++)
    	{
    		
    		u=g.getVertex(i);
    		u.d=Infinity;
    		u.p=null;
    	}
    	s.d=0;
    	Comparator c=new CompDikshatra();
    	BinaryHeap<Vertex> bp=new BinaryHeap<Vertex>(g.v.toArray(), new Comp());
    	//ArrayList<Vertex> al=new ArrayList<>();
    	HashSet<Vertex> al=new HashSet<>();
    	
    	int dist=0;
    	Vertex dummy=null;
    	while(!bp.isEmpty())
    	{
    	  dummy=bp.remove();
    	 // dist=dist+dummy.d;
    	  //int inde=dummy.getIndex();
    	 /* while(al.contains(dummy))
    	  {
    		  dummy=(Vertex) bp.pq[inde+1];
    		  inde++;
    	  }*/
    	  al.add(dummy);
    	  //boolean duplicate=true;
    	  for(Edge x:dummy.adj)
    	  {
    		  Vertex other =x.otherEnd(dummy);
    		  boolean j=!al.contains(other);
    		  if(j&&c.compare(dummy.d+x.weight,other.d)<0)
    		  {
    			  other.d=dummy.d+x.weight;
    			  other.p=dummy;
    			  bp.percolateUp(other.getIndex());
    			//  duplicate=false;
    		  }
    	  }
    	 /* if(dummy.name==2)
    	  {
    	    	dist=dummy.d;//
    	  }*/
    	}
    	//System.out.println(dist+"distance ");
    }

    static void travellingSalesman(Graph g,Vertex s)
    {
    	int wmst = 0;
        s.d=0;        
        BinaryHeap<Vertex> q=new BinaryHeap<Vertex>(g.v.toArray(),new Comp());
        //code for the prim2 algorithm is written to construect the MST
        /*q.add(s);*/
        Vertex dummy=null;
        HashSet<Vertex> hs=new HashSet<>();
        
       // ArrayList<Vertex> hs=new ArrayList<>(); 
      //  hs.add(s);
        while(!q.isEmpty())
        {
        	//System.out.println("in q");
        	dummy=q.remove();
        	if(dummy.p!=null)
        	{
        		dummy.p.children.add(dummy);	
        	}
        	wmst=wmst+dummy.d;
            hs.add(dummy);      	
        	for(Edge e:dummy.adj)
        	{
        		Vertex otherend=e.otherEnd(dummy);
        		if(!hs.contains(otherend)&&e.weight<otherend.d)
        		{
        			otherend.d=e.weight;
        		//	dummy.children.add(otherend);
        			otherend.p=dummy;
        			q.percolateUp(otherend.getIndex());
        		}
        	}
        }
      
        Vertex  mainSource=s;
        System.out.println("travelling salesaman  "+modifiedDfs(s,null,mainSource));
    }
    private static int  modifiedDfs(Vertex s,Vertex possibleCousin,Vertex mainSource) {
		// TODO Auto-generated method stub
		int i=0;
	//	System.out.println("s");//mainSource is unchanged to handle the termination case.
    	if(!s.children.isEmpty())
    	{
    		Vertex dummy=s.children.remove(0);
    		i=i+dummy.d;
    		if(!s.children.isEmpty())
    		{
    			possibleCousin=s.children.remove(0);
    		}
    		else if(s.p!=null&&!s.p.children.isEmpty())
    		{
    			possibleCousin=s.p.children.remove(0);
    		}
    		s=dummy;
			i=i+modifiedDfs(s, possibleCousin,mainSource);
    	}
    	else if(possibleCousin!=null){
    		for(Edge f:s.adj){
    			if(f.otherEnd(s)==possibleCousin)
    			{
    				i=f.weight;
    			}
    		}
    		s=possibleCousin;
    		if(!s.p.children.isEmpty())
    		{
    		possibleCousin=s.p.children.get(0);
    		}
    		else{
    			possibleCousin=null;
    		}
    		i=i+modifiedDfs(s,possibleCousin,mainSource);
    	}	
    	else{
			for (Edge h : s.adj) {                   // to return the edge weight between the leaf at the bottom and the mainsource
				if(h.otherEnd(s)==mainSource)//base case /|| loop termination case
				{
					return h.weight;
				}
			}
    		return 0;
    	}
    	return i;
	}

    
    
/*
    private static int  modifiedDfs1(Vertex s,Vertex possibleCousin) {
		// TODO Auto-generated method stub
		int i=0;
	//	System.out.println("s");
    	if(!s.children.isEmpty())
    	{
    		Vertex dummy=s.children.remove(0);
    		i=i+dummy.d;
    		if(!s.children.isEmpty())
    		{
    			possibleCousin=s.children.remove(0);
    		}
    		else if(s.p!=null&&!s.p.children.isEmpty())
    		{
    			possibleCousin=s.p.children.remove(0);
    		}
    		s=dummy;
			i=i+modifiedDfs(s, possibleCousin);
    	}
    	else if(possibleCousin!=null){
    		for(Edge f:s.adj){
    			if(f.otherEnd(s)==possibleCousin)
    			{
    				i=f.weight;
    			}
    		}
    		s=possibleCousin;
    		if(!s.p.children.isEmpty())
    		{
    		possibleCousin=s.p.children.get(0);
    		}
    		else{
    			possibleCousin=null;
    		}
    		i=i+modifiedDfs(s,possibleCousin);
    	}	
    	else{
    		return 0;
    	}
    	return i;
	}
*/    
    
	public static void main(String[] args) throws FileNotFoundException {
	Scanner in;
        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }

	Graph g = Graph.readGraph(in);
	int src = in.nextInt();
	int target = in.nextInt();
        Vertex s = g.getVertex(src);
	Vertex t = g.getVertex(target);
	//DijkstraShortestPaths(g, s);
     //System.out.println("shortest path "+t.d);
     travellingSalesman(g, s);
     
    }
}
