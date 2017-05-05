
/**
 * Simple heuristic that calculates the total edge costs and unload costs of the jobs that we need to complete.
 * The less jobs we have to complete the lower the value that we calculate will be, so it will be higher up in the PQ.
 * We never over estimate the cost of completion as it only calculates the cost of the edges that we need to visit and their unload cost.
 * This heuristic so it is admissible. 
 * It has O(n) runtime.
 * 
 * @author martinle
 *
 */
public class Heuristic implements Strategy{
	
	@Override
	public int getHeuristic(Graph g, State state) {
		int hCost = 0;
		for(Edge e: state.getJobList()) {
			hCost += (e.getCost() + g.getMapOfNodes().get(e.getLocation2()).getUnloadCost()) + e.getCost();
		}
		return hCost;
	}

}