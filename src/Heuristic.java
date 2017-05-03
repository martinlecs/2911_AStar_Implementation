import java.util.ArrayList;
import java.util.Comparator;

public class Heuristic implements Strategy{

	@Override
	public int getHeuristic(Graph g, State state) {
		int hCost = 0;
		for(Edge e: state.getJobList()) {
			hCost += (e.getCost() + g.findNode(e.getLocation2()).getUnloadCost());
		}
		return hCost;
	}
	
	//add distance to closest job
	//finds distance to closest job
//	private int getEstimateToNextJob (Graph g, State s) {
//		//get current location, find neighours 
//		ArrayList<Edge> neighbours = (g.findNode(s.getLocation()).getConnected()).sort();
//		//find cheapest
//	}
}
////public class sortEdges implements Comparator<Edge> {
////    
////	@Override
////    public int compare(Edge a, Edge b){
////		if(a.getCost() > b.getCost()){
////	        return 1;
////	    } else if (a.getCost() < b.getCost()){
////	        return -1;
////	    } else{
////	        return 0;
////	    }
////	}
//}


	
//distance to next job
//precalculate the distance to each edge from each other.
//add distance to nextJob onto the heuristic