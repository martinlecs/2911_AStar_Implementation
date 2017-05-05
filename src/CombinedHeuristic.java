
/**
 * Combines the result of Heuristic 1 and 2 to create a super heuristic
 * Probably not admissible but very good in certain cases!
 * @author martinle
 *
 */
public class CombinedHeuristic implements Strategy{
	
	@Override
	public int getHeuristic(Graph g, State state) {
		Strategy i = new Heuristic();
		Strategy j = new Heuristic3();
		return i.getHeuristic( g, state) + j.getHeuristic(g, state);
	}
	
}