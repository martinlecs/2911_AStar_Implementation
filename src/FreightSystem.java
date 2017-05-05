import java.util.*;
import java.io.*;

/**
 * 
 * Reads a file and prints out the result of the A* search
 * @author martinle
 *
 */
public class FreightSystem {
	public static void main (String[] args) throws IOException{
		
		//declare a new Graph
		Graph g = new Graph();
		//Create a Floyd-Warshall Matrix

		LinkedList<Edge> jobList = new LinkedList<Edge>();
    	Strategy strategy = new Heuristic2();

		Scanner sc = null;
		try {
			sc = new Scanner(new FileReader(args[0]));
			
			while(sc.hasNextLine()) {
			
				String input = sc.nextLine().split("#")[0];
				String[] a = (input.trim()).split("[ ]+");
				
				//ignores lines that are empty or begin with a #
		    	if (!a[0].equals("") && !a[0].equals("#") ) {
		    		switch(a[0]) {
		    			case "Unloading":
		    				//create nodes
		    				g.addNode(a[2], Integer.parseInt(a[1]));
		    				break;
		    			case "Cost":
		    				//create edges
		    				g.addEdge(a[2], a[3], Integer.parseInt(a[1]));
		    				g.getListOfEdges().add(new Edge (Integer.parseInt(a[1]), a[2], a[3]));
		    				break;
		    			case "Job":
		    				//Create a job, represented as edges.
		    				Edge e = new Edge (0, a[1], a[2]); 
		    				jobList.add(e);
		    				break;
		    			case "Print":
		    				break;
		    			default:
		    				continue;
		    		}	
		    	}
		    }
			if (!jobList.isEmpty()) {
				FloydWarshall f = new FloydWarshall(g.getMapOfNodes().size());
				//Create a hashmap of Cities
				ArrayList<Node> locations = new ArrayList<Node>();
				locations.addAll(g.getMapOfNodes().values());
				HashMap<String, Integer> citiesMap = new HashMap<String, Integer>();
				Iterator<Node> iter = locations.iterator();
				for(int i = 0; i < g.getMapOfNodes().size(); i++) {
					citiesMap.put(iter.next().getValue(), i);
				}
				for(Edge curr : g.getListOfEdges()) {
					
					f.addEdge(citiesMap.get(curr.getLocation1()), citiesMap.get(curr.getLocation2()), (double) curr.getCost());
				}
				System.out.println(citiesMap.values());
				System.out.println(citiesMap.keySet());
			
				double[][] matrix = f.floydWarshall();

				//from a, must be able to find a shortest path to any node
				//if there is no path to a node and the node is in the job list, then there is no solution
				int j = 0;
				for(; j < g.getMapOfNodes().size()-1; j++) {
					if (matrix[0][j] == Double.POSITIVE_INFINITY) {
						//check through job list to find occurrence of 
						ArrayList<String> s = new ArrayList<String>();
						s.addAll(citiesMap.keySet());
						String location = s.get(j);
						for(Edge cur : jobList) {
							if(location.equals(cur.getLocation1()) || location.equals(cur.getLocation2())) {
								System.err.println ("No solution");
								System.exit(0);
							}
						}				
					}
				}
				//If passed the test, perform the search
				addTravelCostToJobs(jobList, g);
				//Check if there is a solution
				AStarSearch(g, jobList, strategy);
			}
		} catch (FileNotFoundException e) {
			System.err.println ("File was not found");
		}
		finally {
			if (sc != null) sc.close();
		}
	}
	/**
	 * Adds travel costs to edges
	 * @param jobList	The list of jobs (LinkedList<Edge>)
	 * @param g			The Graph object
	 */
	private static void addTravelCostToJobs(LinkedList<Edge> jobList, Graph g) {
		for(Edge e : jobList) { 
			for(Edge curr : g.getListOfEdges()) {
				if(e.getLocation1().equals(curr.getLocation1()) && e.getLocation2().equals(curr.getLocation2())) {
					e.setCost(curr.getCost());
				}
			}
		}
	}
	/**
	 * Given a graph, the list of jobs to complete and a Strategy find the path the has the smallest cost
	 * @param g			The Graph object
	 * @param jobList	The LinkedList<Edge> of jobs to complete
	 * @param strategy	The heuristic to use
	 * @return			A boolean value depending if the search was successful
	 */
  public static boolean AStarSearch(Graph g, LinkedList<Edge> jobList, Strategy strategy){
    	PriorityQueue<State> open = new PriorityQueue<State>(new StateComparator());
    	HashSet<State> closed = new HashSet<State>();
    	 
    	boolean found = false;
        int nodesExpanded = 0;
        
    	//Create initial state (Sydney)
    	State initial = new State ("Sydney", jobList, 0, 0, null);
        open.add(initial);

        //while frontier is not empty
        while(!open.isEmpty()){
        	//Print out queue and their
            State current = open.poll();
            closed.add(current);
            nodesExpanded++;
            int cost_so_far = current.getCostSoFar();
            
            if (current.getJobList().isEmpty()) {
            	found = true;
            	System.out.println(nodesExpanded + " nodes expanded");
            	System.out.println("cost = " + current.getCostSoFar());
            	printPath(g, current, jobList);
            	break;
            }
            //Find graph node for current state
            Node curr = g.getMapOfNodes().get(current.getLocation());
        	
            for(Edge edge: curr.getConnected()) {
            	
            	int new_cost = cost_so_far + edge.getCost(); 
            	State next = new State (edge.getLocation2(), new LinkedList<Edge>(), new_cost, 0 ,current);
            	
                //update job completion
                next.setJobList(current.copyJobList());
                Edge e = new Edge(edge.getCost(), next.getPrevState().getLocation(), next.getLocation());
 
                if(next.checkJob(e)) {
                	new_cost += g.getMapOfNodes().get(next.getLocation()).getUnloadCost();
                	next.setCostSoFar(new_cost); 	
                	next.removeJob(e);
                }
        		next.setHeuristic(strategy.getHeuristic(g, next));
	             while (!closed.contains(next)) { //BUG: for some reason keeps checking the same object in set
            		open.add(next);	
	             }
            }
        }
        return found;       
    }
  	
  	/**
  	 * Gives you the path taken to reach the goal state
  	 * @param g		The graph objectg
  	 * @param end	The goal state found by A* search
  	 * @param jobList The list of jobs that had to be completed
  	 */
    private static void printPath (Graph g, State end, LinkedList<Edge> jobList) {
    	//Get list of states accessed
    	ArrayList<Edge> jobPath = new ArrayList<Edge>();
    	for(State state = end; state.getPrevState() != null; state = state.getPrevState()) {
    		//path.add(state);
    		jobPath.add(new Edge(0, state.getPrevState().getLocation() , state.getLocation()));
    	}
    	Collections.reverse(jobPath);
    	
    	//iterate through path and check if its in the job list, if so print X else print Y
    	for(Edge curr : jobPath) {
    		String s = "";
    		for(Edge jobCurr : jobList) {
        		if(curr.getLocation1().equals(jobCurr.getLocation1()) && curr.getLocation2().equals(jobCurr.getLocation2())) {
        			s = "Job " + curr.getLocation1() + " to " + curr.getLocation2();
        		}
    		}
    		if (s == "") {
    			s = "Empty " + curr.getLocation1() + " to " + curr.getLocation2();
    		}
    		System.out.println(s);
    	}
    }    
}
/**
 * Implementation of the Comparator class for the PQ
 * @author martinle
 *
 */
final class StateComparator implements Comparator<State> {

	@Override
    public int compare(State i, State j){
        if((i.getCostSoFar() + i.getHeuristic()) > (j.getCostSoFar() + j.getHeuristic())){
            return 1;
        } else if ((i.getCostSoFar() + i.getHeuristic()) < (j.getCostSoFar() + j.getHeuristic())){
            return -1;
        } else{
            return 0;
        }
    }
}



  