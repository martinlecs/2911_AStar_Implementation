import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public class Heuristic implements Strategy{

	@Override
	public int getHeuristic(Graph g, State state) {
		int hCost = 0;
		for(Edge e: state.getJobList()) {
			hCost += (e.getCost() + g.getMapOfNodes().get(e.getLocation2()).getUnloadCost());
		}
		hCost -=  getEstimateToNextJob (g, state);
		
		return hCost;
	}
	//If a job is adjacent to current node, effective if a lot of jobs are close together
	private int getEstimateToNextJob (Graph g, State s) {
		//get current location, find neighours 
		sortEdges comparator = new sortEdges();
		g.getMapOfNodes().get(s.getLocation()).getConnected().sort(comparator);
		ArrayList<Edge> sortedList = g.getMapOfNodes().get(s.getLocation()).getConnected();
		LinkedList<Edge> jobList = s.getJobList();
		int costNextJob = 0;
		for(Edge e: sortedList) {
			for(Edge job : jobList) {
				if(e.getLocation2() == job.getLocation1()) {
					costNextJob = job.getCost();
				}
			}
		}
		return costNextJob;
	}
}
final class sortEdges implements Comparator<Edge> {
    
	@Override
    public int compare(Edge a, Edge b){
		if(a.getCost() > b.getCost()){
	        return 1;
	    } else if (a.getCost() < b.getCost()){
	        return -1;
	    } else{
	        return 0;
	    }
	}
}