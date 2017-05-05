import java.util.*;
import java.io.*;

/**
 * 
 * @author martinle
 *
 */
public class FreightSystem {
	public static void main (String[] args) throws IOException{
		
		//declare a new Graph
		Graph g = new Graph();;
		LinkedList<Edge> jobList = new LinkedList<Edge>();	
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

				if(addTravelCostToJobs(jobList, g)) {
					AStarSearch(g, jobList);
				} else {
					System.err.println ("No solution");
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println ("File was not found");
		}
		finally {
			if (sc != null) sc.close();
		}
	}
	private static boolean addTravelCostToJobs(LinkedList<Edge> jobList, Graph g) {
		for(Edge e : jobList) { 
			int i = 0;
			for(Edge curr : g.getListOfEdges()) {
				if(e.getLocation1().equals(curr.getLocation1()) && e.getLocation2().equals(curr.getLocation2())) {
					e.setCost(curr.getCost());
					i++;
				}
				else if(!e.getLocation1().equals(curr.getLocation1()) && !e.getLocation2().equals(curr.getLocation2()) && i == g.getListOfEdges().size()) {
					return false;
				}
			}
		}
		return true;
	}
  public static boolean AStarSearch(Graph g, LinkedList<Edge> jobList){
    	PriorityQueue<State> open = new PriorityQueue<State>(new StateComparator());
    	HashSet<State> closed = new HashSet<State>();
    	Strategy s = new Heuristic();
    	 
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
//	            System.out.println((closed));
            //System.out.println(cost_so_far);
            
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
        		next.setHeuristic(s.getHeuristic(g, next)  /* + new_cost*/);
//	             	while (!closed.contains(next)) { //BUG: for some reason keeps checking the same object in set
            		open.add(next);	
//	             	}
            }
        }
        return found;       
    }

    /**
     * Gives you the path that was taken to reach the goal
     * @param target
     * @return
     */
//	    private static List<String> getPath (State end) {
// 
//	    	List<String> path = new ArrayList<String>();
//	    	for(State state = end; state.getPrevState() != null; state = state.getPrevState()) {
//	    		path.add(state.getLocation());
//	    	}
//	    	path.add("Sydney");
//	    	Collections.reverse(path);
//	    	return path;
//	    }
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



  