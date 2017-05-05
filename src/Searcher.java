import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Collections;
import java.util.HashSet;
import java.util.ArrayList;
/**
 * Returns boolean value indicating if path has been found
 * @author martinle
 *
 */
public class Searcher{
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
            System.out.println((closed));
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
                Edge e = new Edge(0, next.getPrevState().getLocation(), next.getLocation());
                if(next.checkJob(e)) {
                	new_cost += g.getMapOfNodes().get(next.getLocation()).getUnloadCost();
                	next.setCostSoFar(new_cost);
                	next.removeJob(e);
                }
        		next.setHeuristic(s.getHeuristic(g, next)  /* + new_cost*/);
             	while (!closed.contains(next)) { //BUG: for some reason keeps checking the same object in set
            		open.add(next);	
             	}
            }
        }
        return found;       
    }

    /**
     * Gives you the path that was taken to reach the goal
     * @param target
     * @return
     */
//    private static List<String> getPath (State end) {
// 
//    	List<String> path = new ArrayList<String>();
//    	for(State state = end; state.getPrevState() != null; state = state.getPrevState()) {
//    		path.add(state.getLocation());
//    	}
//    	path.add("Sydney");
//    	Collections.reverse(path);
//    	return path;
//    }
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

