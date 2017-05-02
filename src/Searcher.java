import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
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
    	
    	//BUG: jobList keeps getting created and added. Ie. Keeps repeating the same job list
    	
    	//Create initial state (Sydney)
    	State initial = new State ("Sydney", jobList, 0, new LinkedList<Edge>(), null);
    	
    	//Implement comparator for State
        Comparator<State> strategy = new Strategy();
        PriorityQueue<State> open = new PriorityQueue<State>(strategy);
        HashSet<State> close = new HashSet<State>();
        boolean found = false;
        int nodesExpanded = 0;
        
        //add our initial state to the PQ
        open.add(initial);

        //while frontier is not empty
        while(!open.isEmpty()){
            State current = open.poll();
            nodesExpanded++;
            close.add(current);
            int cost_so_far = current.getCostSoFar(); // this is an issue
            
            if(current.getPrevState() != null) {
	            //update job completion
	            //create a new job list and complete list
	            //TO-DO
	            current.setJobList(current.copyJobList());
	            Edge e = new Edge(0, current.getPrevState().getLocation(), current.getLocation());
	            if(current.checkJob(e)) {
	                //If job, need to add unloading cost to cost_so_far
	            	int totalCost = g.findNode(current.getLocation()).getUnloadCost() + cost_so_far; //unload cost is correct
//	            	System.out.println("unload cost " + current.getLocation() + " = " + g.findNode(current.getLocation()).getUnloadCost());
//	            	System.out.println("cost so far = " + cost_so_far);
//	            	System.out.println("Total cost to get to " + current.getLocation() + " from " + current.getPrevState().getLocation() +  "= " + totalCost);
	            	current.setCostSoFar(totalCost);
	            	current.removeJob(e);
	            	current.getCompletedJobs().add(e);
	            
	            }
            }
            //Check for the finishing state (where all jobs have been completed)
            if (current.getJobList().isEmpty()) {
            	found = true;
            	System.out.println(nodesExpanded + " nodes expanded");
            	System.out.println("cost = " + current.getCostSoFar());
            	System.out.println(printPath(current));
            	System.out.println(current);
            	System.out.println(current.getPrevState());
            	break;
            }
            
            //Find all branching states
            //Note: Make Graph hold a HashMap of vertices instead of ArrayList
            
            //Find graph node for current state
            Node curr = g.findNode(current.getLocation());
            
            for(Edge edge: curr.getConnected()) {
                String child = edge.getLocation2();
                System.out.println(edge.getLocation2() + " edge = " + edge.getCost());
            	int new_cost = cost_so_far + edge.getCost(); //add weight of edges to cost so far
                
            	//if not in close and not in open, add to open. 
            	//OR (even if it exists in the open PQ, if it has a cost that is less then a similar state,
            	//add it it to open
            	//TO-DO: need to modify that condition to actually work.
//            	For each successor node, we check to see if the state that it represents has been visited already. 
//            	If it hasn't, we add it to the open list. If it has been visited, we need to determine if we have arrived at this state through a better path. 
//            	If so, we need to place this node on the open list and remove the suboptimal node. 
//            	METHOD:
//            	if  successor in open or successor in closed
//                IMPROVE(successor)
            	
                if((!close.contains(child) && !open.contains(child)) || (new_cost < cost_so_far)){ 
                	//Make a new state
                	State next = new State (child, current.getJobList(), new_cost, current.getCompletedJobs(), current);
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
    private static List<String> printPath (State end) {
    	List<String> path = new ArrayList<String>();
    	for(State state = end; state.getPrevState() != null; state = state.getPrevState()) {
    		path.add(state.getLocation());
    	}
    	path.add("Sydney");
    	Collections.reverse(path);
    	return path;
    }
    
    
}



