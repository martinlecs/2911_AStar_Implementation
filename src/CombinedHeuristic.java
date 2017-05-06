
/**
 * Combines the result of Heuristic 1 and 2 to create a super heuristic
 * Combines the Total Cost of Job List with the Current Best Move (Cheapest)
 * Heuristic3 chooses the best move but only considers neighbours
 * Not admissible
 * @author martinle
 *
 */
public class CombinedHeuristic implements Strategy{
	
	@Override
	public int getHeuristic(Graph g, State state) {
		Strategy i = new Heuristic();
		Strategy j = new Heuristic2();
//		Strategy k = new Heuristic3();
		return (i.getHeuristic( g, state)+ j.getHeuristic(g, state));
//				+ k.getHeuristic(g, state));
	}
	
}