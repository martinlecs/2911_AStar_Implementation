import java.util.Comparator;

public class StateComparator implements Comparator<State> {

	@Override
    public int compare(State i, State j){
        if((i.getCostSoFar() + i.getHeuristic()) > (j.getCostSoFar() + j.getHeuristic())){
            return 1;
        } else if ((i.getCostSoFar() + i.getHeuristic()) < (j.getCostSoFar() + j.getHeuristic())){
            return -1;
        } else{
            return 0;
        }
    }
}


