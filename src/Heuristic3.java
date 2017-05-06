import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Calculates how far it is to the closest job (ie. with minimum travel cost). It looks for the cheapest possible move, that is an objective on the joblist
 * It runs at O(n^2). However, we must sort the list of jobs we have each time for it to work efficiently.
 * This heuristic is admissible.
 * 
 * @author martinle
 *
 */
public class Heuristic3 implements Strategy{
	
	@Override
	public int getHeuristic(Graph g, State state) {
		return getEstimateToNextJob (g, state); 
	}
	
	private int getEstimateToNextJob (Graph g, State s) {
		//get current location, find neighours 
		sortEdges comparator = new sortEdges();
		g.getMapOfNodes().get(s.getLocation()).getConnected().sort(comparator);
		ArrayList<Edge> sortedList = g.getMapOfNodes().get(s.getLocation()).getConnected();
		LinkedList<Edge> jobList = s.getJobList();
		int costNextJob = 0;
		for(Edge e: sortedList) {
			for(Edge job : jobList) {
				if(e.getLocation2().equals(job.getLocation1()) || e.getLocation2().equals(job.getLocation2())) {
					costNextJob = job.getCost();
				}
			}
		}
		return costNextJob;
	}
}
final class sortEdges implements Comparator<Edge> {
	@Override
    public int compare(Edge a, Edge b) {
		if(a.getCost() > b.getCost()){
	        return 1;
	    } else if (a.getCost() < b.getCost()){
	        return -1;
	    } else{
	        return 0;
	    }
	}
}

