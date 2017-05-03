import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Returns boolean value indicating if path has been found
 * @author martinle
 *
 */
public class Searcher{
    public static boolean AStarSearch(Graph g, LinkedList<Edge> jobList){
    	PriorityQueue<State> open = new PriorityQueue<State>(new StateComparator());
    	 
    	boolean found = false;
        int nodesExpanded = 0;
        
    	//Create initial state (Sydney)
    	State initial = new State ("Sydney", jobList, 0, 0, new LinkedList<Edge>(), null);
        open.add(initial);

        //while frontier is not empty
        while(!open.isEmpty()){
        	//Print out queue and their
            State current = open.poll();
            nodesExpanded++;
            int cost_so_far = current.getCostSoFar();
            
            if(current.getPrevState() != null) {
	            //update job completion
	            current.setJobList(current.copyJobList());
	            Edge e = new Edge(0, current.getPrevState().getLocation(), current.getLocation());
	            if(current.checkJob(e)) {
	            	cost_so_far += g.getMapOfNodes().get(current.getLocation()).getUnloadCost();
	            	current.setCostSoFar(cost_so_far);
	            	current.removeJob(e);
	            	current.getCompletedJobs().add(e);
	            }
            }
            //Check for the finishing state (where all jobs have been completed)
            if (current.getJobList().isEmpty()) {
            	found = true;
            	System.out.println(nodesExpanded + " nodes expanded");
            	System.out.println("cost = " + current.getCostSoFar());
            	System.out.println(getPath(current));
            	break;
            }
            
            //Find graph node for current state
            Node curr = g.getMapOfNodes().get(current.getLocation());
        	Strategy s = new Heuristic();
        	
            for(Edge edge: curr.getConnected()) {
                String neighbour = edge.getLocation2();
            	int new_cost = cost_so_far + edge.getCost(); 
            	State next = new State (neighbour, current.getJobList(), new_cost, 0 ,current.getCompletedJobs(), current);
            	next.setHeuristic(s.getHeuristic(g, next) + new_cost);
                open.add(next);
            }
        }
        return found;
    }

    /**
     * Gives you the path that was taken to reach the goal
     * @param target
     * @return
     */
    private static List<String> getPath (State end) {
    	List<String> path = new ArrayList<String>();
    	for(State state = end; state.getPrevState() != null; state = state.getPrevState()) {
    		path.add(state.getLocation());
    	}
    	path.add("Sydney");
    	Collections.reverse(path);
    	return path;
    }
//    private static String printPath (Graph g, List<String> path) {
//    	//iterate through pairs
//    	
//    }//The order that jobs were completed are stored in LinkedList<Edge> completedJobs (make sure to reverse this)
    
    
}



