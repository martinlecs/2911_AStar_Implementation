
public class Heuristic implements Strategy{

	@Override
	public int getHeuristic(Graph g, State state) {
		int hCost = 0;
		for(Edge e: state.getJobList()) {
			hCost += (e.getCost() + g.findNode(e.getLocation2()).getUnloadCost());
		}
		return hCost;
	}
}

//distance to next job
//precalculate the distance to each edge from each other.
//add distance to nextJob onto the heuristic