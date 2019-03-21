//based off a question a student ask me
//https://www.geeksforgeeks.org/minimum-cost-connect-cities/ has a similar problem
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class solver {
	private static class Vertex {
		Vertex() {
			outgoing = new HashMap<Vertex,Edge>();
			visited = false;
			cost = Integer.MAX_VALUE;
			parent = null;
		}
		public HashMap<Vertex,Edge> outgoing;
		//used for djikstra algorithm;
		public boolean visited;
		public int cost;
		public Vertex parent;
	}
	private static class Edge {
		public int cost;
		//edges are bidirectional but I call it source/dest anyway
		public Vertex source;
		public Vertex dest;
	}
	public static int pathSolver(int numTotalAvailableCities, int numTotalAvailableRoads,
			ArrayList<ArrayList<Integer>> roadsAvailable, int numRoadsToBeRepaired,
			ArrayList<ArrayList<Integer>> costRoadsToBeRepaired) {
		ArrayList<Vertex> graph = new ArrayList<Vertex>();
		//create a vertex for every city
		for (int i = 0; i <= numTotalAvailableCities; i++) {
			graph.add(new Vertex());
		}
		//create and edge for every road
		for (int i=0; i<numTotalAvailableRoads; i++) {
			Edge e1 = new Edge();
			Edge e2 = new Edge();
			e1.cost = 0;
			//represent our bidirectional roads by adding
			//the edge to the source and the dest
			int source = roadsAvailable.get(i).get(0);
			int dest = roadsAvailable.get(i).get(1);
			e1.source = graph.get(source);
			e1.dest = graph.get(dest);
			e2.cost = 0;
			e2.source = graph.get(dest);
			e2.dest = graph.get(source);
			graph.get(source).outgoing.put(e1.dest,e1);
			graph.get(dest).outgoing.put(e2.dest,e2);
		}
		//update the cost for all the broken roads
		for (int i=0; i<numRoadsToBeRepaired; i++) {
			int source = costRoadsToBeRepaired.get(i).get(0);
			int dest = costRoadsToBeRepaired.get(i).get(1);
			int cost = costRoadsToBeRepaired.get(i).get(2);
			graph.get(source).outgoing.get(graph.get(dest)).cost=cost;
			graph.get(dest).outgoing.get(graph.get(source)).cost=cost;
		}
		//set up djikstras algorithm
		PriorityQueue<Vertex> q = 
				new PriorityQueue<Vertex>(16, Comparator.comparing((Vertex v) -> v.cost));
		graph.get(1).cost = 0;
		q.add(graph.get(1));
		//djikstras algorithm
		while(q.peek() != null) {
			Vertex v = q.poll();
			if (v.visited) {
				continue;
			}
			v.visited = true;
			for (Edge e: v.outgoing.values()) {
				if (!visitedVertexes.contains(e.dest) && v.cost + e.cost < e.dest.cost) {
					e.dest.cost = v.cost+e.cost;
					e.dest.parent = v;
					q.add(e.dest);
				}
			}
		}
		int total = 0;
		for (int i=1; i<graph.size(); i++) {
			//if it isn't connected (i.e., no road to one citiy)
			//return -1
			if (graph.get(i).visited == false) {
				return -1;
			}
			total+=graph.get(i).cost;
			//the cost of a vertex is the total path from the source
			//but this question just wants the total cost of the edges
			//so we subtract away the cost of the parent to account for this
			if (graph.get(i).parent!= null) {
				 total-=graph.get(i).parent.cost;
			}
		}
		return total;
	}
	public static void main(String[] args) {
		Scanner s;
		File f;
		try {
			f = new File("src/solver/input.txt");
			s = new Scanner(f);
		}
		catch (FileNotFoundException e) {
			return;
		}
		int numTotalAvailableCities = s.nextInt(); 
		int numTotalAvailableRoads = s.nextInt();
		ArrayList<ArrayList<Integer>> roadsAvailable = new ArrayList<ArrayList<Integer>>();
		for (int i=0; i<numTotalAvailableRoads; i++) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(s.nextInt());
			temp.add(s.nextInt());
			roadsAvailable.add(temp);
		}
		int numRoadsToBeRepaired = s.nextInt();
		ArrayList<ArrayList<Integer>> costRoadsToBeRepaired = new ArrayList<ArrayList<Integer>>();
		for (int i=0; i<numRoadsToBeRepaired; i++) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(s.nextInt());
			temp.add(s.nextInt());
			temp.add(s.nextInt());
			costRoadsToBeRepaired.add(temp);
		}
		int answer = pathSolver(numTotalAvailableCities, numTotalAvailableRoads,
				roadsAvailable, numRoadsToBeRepaired, costRoadsToBeRepaired);
		System.out.println(answer);
	}
}
