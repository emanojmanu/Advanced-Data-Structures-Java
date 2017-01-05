import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
//import java.util.Collections;
import java.util.HashMap;
//import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Driver {
	List<CircularList<Vertex>> tours;
	CircularList<Vertex> finalStitched;
	List<Vertex> discovered;
	Map<Integer, CircularList<Vertex>.Entry<Vertex>> discoveredposition;

	public Driver() {
		finalStitched = new CircularList<Vertex>();
		discovered = new ArrayList<Vertex>();
		tours = new LinkedList<CircularList<Vertex>>();
		discoveredposition = new HashMap<Integer, CircularList<Vertex>.Entry<Vertex>>();
	}
	public boolean verifyTour(Graph g,CircularList<Vertex> tour)
	{
		HashMap<String,Edge> verifymap=new HashMap<>();
		Edge[] verifyEdge=new Edge[g.edgeCount];
		Vertex ver=new Vertex(0);
			
		int count=0;
		for(int i=1;i<=g.size;i++)
		{	ver=g.getVertex(i);
		if(count<g.edgeCount)
		{
			for(Edge e:ver.adj)
			{
				if(!e.inMap)
				{
					verifymap.put(e.keytomap,e);
					e.inMap=true;
					verifyEdge[count]=e;
					
					count++;
				}				
			}	
		}
		}
		int k=0;
		CircularList<Vertex>.Entry<Vertex> start = (CircularList<Vertex>.Entry<Vertex>) tour.header.next;
		String s=null;
		String s1=null;
		while (k <= tour.size-1) {
			
			s=start.element+" "+start.next.element;
			s1=start.next.element+" "+start.element;
			if(k==tour.size-1)
			{
				//to look for the condition at the last node
				//1 2 3 6 4 5 6 1 4 3   now we need to see for  3 1
				s=start.element+" "+ ((CircularList<Vertex>.Entry<Vertex>) tour.header.next).element;
				s1=( (CircularList<Vertex>.Entry<Vertex>) tour.header.next).element+" "+start.element;			
			}
			
			Edge t1=verifymap.get(s);
			
			if(t1==null)
			{
			t1=verifymap.get(s1);
			}
			if(t1!=null&&!t1.visitedVerification)
			{
				t1.visitedVerification=true;
				/*if(!t1.visitedVerification)
				{
					t1.visitedVerification=true;  6017 34793
				}*/
				/*else{
					return false;
				}*/
			}
			else{
				return false;
			}
			start=start.next;
			k++;
		}
		return true;
	}
	public List<CircularList<Vertex>> breakGraphIntoTours(Graph g) {
		boolean allEdgesDone = false; //for a particular vertex as start to see whether all its edges are already visited
		CircularList<Vertex> cl;    
		Vertex start = new Vertex(0); 
		start = g.getVertex(1); //initial starting vertex is 1
		
		int k = 0, indexl = 0, indexiniter = 1;
		Iterator[] iterarr = new Iterator[g.size + 1];
		
		boolean exception = false;   //to note if a Euler graph exits or any exception
		try {
			while (k < g.edgeCount) { //to cover all the edges
				if (!exception) {
					cl = new CircularList<>();
					cl.add(start);
					Iterator<Edge> iter = null;
					if (start.seen == false) {
						start.seen = true;    // to note discovered nodes
						discoveredposition.put(start.name, cl.header);  //to remember where to stitch at stiching method
						discovered.add(start); // only for unseen nodes iterator
												// is// created
					}
					Edge currentEdge = null;
					if (iterarr[start.indexIter] == null) {   //if iterator not exists create one and 
						// System.out.println("inside if");
						iter = start.iterator();
						iterarr[indexiniter] = iter;
						start.indexIter = indexiniter;
						indexiniter++; // values are very large to convert them
										// in to small size array
						// to update indexiter value and to add iter in iterar
						// at
						// indexiter pos
						currentEdge = iter.next(); //first edge of the vertex
						// System.out.println();
						while (currentEdge.visited) {   //to move on to unvisited edges which are visited using previous vertices
							currentEdge = iter.next();
						}
					} else {
				
						iter = iterarr[start.indexIter]; //if iter is there in array
						if (iter.hasNext()) {          
							currentEdge = iter.next();
							while (iter.hasNext() && currentEdge.visited == true) {
								currentEdge = iter.next();
							}
						} else if (iter.hasNext() == false) {
							allEdgesDone = true; //all edges are visited with current starting vertex go to next newly discovered node
						}
					}
					Vertex next = new Vertex(0);
					if (!allEdgesDone) {
						next = currentEdge.otherEnd(start);
					}
					if (next.seen == false) {
						next.seen = true;
						if (!allEdgesDone) {
							discovered.add(next);   //if undiscovered adidng it
						}
					}
				 while (!allEdgesDone && next.name != start.name && currentEdge.visited == false) {
				//going on a tour till the next == start or any edge is visited twice	
						if (next.seen == false) {
							next.seen = true;
							discovered.add(next);
						}
						currentEdge.visited = true; //every unvisited edge is visited 
						
						k++; //adding the vertex predecessor position in circular list to the hashmap
						if (!discoveredposition.containsKey(next.name)) {
							discoveredposition.put(next.name, cl.tail);
						}
						cl.add(next);
						if (iterarr[next.indexIter] == null) {
							Iterator t = next.iterator();
							next.indexIter = indexiniter;
							iterarr[next.indexIter] = next.iterator();
							currentEdge = (Edge) iterarr[next.indexIter].next();
							indexiniter++;
						}// to see if current edge is null occurs for invalid input
						try {
						
							while (currentEdge.visited) {
								currentEdge = (Edge) iterarr[next.indexIter].next(); //to skip visited edges if any
							}
                        	next = currentEdge.otherEnd(next); //next is updated
							if (next.name == start.name) {
								currentEdge.visited = true; 
								k++;              
								if (next.seen == false) {
									next.seen = true;
									discovered.add(next);
								}
							}
						} catch (Exception e) {
							exception = true;
							
						}
					}
					if (!allEdgesDone) {
						if (cl.size >= 2) {
							tours.add(cl); //since we add start node to circular list everytime to remove Cl of length one							
						}
						} 
					indexl++; //to get next undiscovered vertex as starting vertex
					allEdgesDone = false;//flag
					if (indexl < g.size) {
						start = discovered.get(indexl);
					} else {
						indexl = (int) (Math.random() * g.size);
						System.out.println(indexl + "indexis given");
						start = discovered.get(indexl);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return tours;
	}
		public CircularList<Vertex> StitchTours(List<CircularList<Vertex>> tourslist) {
		CircularList<Vertex> cl1 = tourslist.get(0);
		CircularList<Vertex> cl2 = tourslist.get(1);
		CircularList<Vertex>.Entry<Vertex> flag2 = cl2.header.next;
		int k = 1;
		while (k < tourslist.size()) {
			cl2 = tourslist.get(k);
			flag2 = cl2.header.next;
			//to remember the dummy previous vertices where we need to stitch
			
			CircularList<Vertex>.Entry<Vertex> dummypos = (CircularList<Vertex>.Entry<Vertex>) discoveredposition
					.get(flag2.element.name); //previous node of the flag2 in CL
			CircularList<Vertex>.Entry<Vertex> tempnex = dummypos.next;
			dummypos.next = flag2;
			cl2.tail.next = tempnex;
			cl1.size = cl1.size + cl2.size; //can be avoided
			k++;  //for all the tour lists
		}
		//cl1.add(cl1.header.next.element);//simply for displaying purpose of last node
		return cl1;
	}

		public static void main(String[] args) throws FileNotFoundException {
		Scanner in;
		if (args.length > 0) {
			File inputFile = new File(args[0]);
			in = new Scanner(inputFile);
		} else {
			in = new Scanner(System.in);
		}
		Timer readData = new Timer();
		readData.start();
		Graph g = Graph.readGraph(in);
		readData.end();
		boolean except = false;
		Driver f = new Driver();
		Timer t = new Timer();
		Timer t1 = new Timer();
		List<CircularList<Vertex>> toursList = new ArrayList<>();
		
		try {
			t.start();
			toursList = f.breakGraphIntoTours(g);
			t.end();
		} catch (Exception e) {
			except = true;
			System.out.println("Graph is not Eulerian");
		}
		if (!except) {
			int ssize = 0;	
			CircularList<Vertex> eulerPath = new CircularList<Vertex>();
			t1.start();
			eulerPath = f.StitchTours(toursList);
			t1.end();
			/*for (Vertex gl : l2) {
				y++;
				// hs.add(gl);
			}*/
		//l2.printList();
			for(Vertex v:eulerPath)
			{
				System.out.println(v);
			}
			Timer verification = new Timer();
			verification.start();

			//System.out.println("is Tour a Euler path " + f.verifyTour(g, eulerPath));
			verification.end();
	//System.out.println("verification time " + verification);
			
		}
	}
}