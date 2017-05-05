import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Calculates how far it is to the next job.
 * It runs at O(n^2). However, we must sort the list of jobs we have each time for it to work efficiently,
 * This algorithm is used mainly for experimentation.
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
		System.out.println(sortedList);
		LinkedList<Edge> jobList = s.getJobList();
		int costNextJob = 0;
		for(Edge e: sortedList) {
			for(Edge job : jobList) {
				if(e.getLocation2().equals(job.getLocation1()) || e.getLocation2().equals(job.getLocation2())) {
					costNextJob = job.getCost();
				}
			}
		}
		System.out.println(costNextJob);
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

