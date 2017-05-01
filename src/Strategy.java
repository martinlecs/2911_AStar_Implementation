import java.util.Comparator;

public class Strategy implements Comparator<Node> {

	@Override
    public int compare(Node i, Node j){
        if((i.getCostSoFar() + getHeuristic(i,j)) > (j.getCostSoFar() + getHeuristic(i,j))){
            return 1;
        } else if ((i.getCostSoFar() + getHeuristic(i,j)) < (j.getCostSoFar() + + getHeuristic(i,j))){
            return -1;
        } else{
            return 0;
        }
    }
	public static int getHeuristic(Node n1, Node n2) {
		
		for(Tuple<String, String> curr : n1.getJobList()) {	//this is bad, better to binary sort
			if(curr.getLeft().equals(n1.getValue()) && curr.getRight().equals(n2.getValue())) {
				return -50;
			}
		}
		return 0;
	}

}
