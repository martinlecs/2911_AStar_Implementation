
public class Edge {
    private final double cost;
    private final Node target;

    public Edge(Node targetNode, double costVal) {
        cost = costVal;
        target = targetNode;
    }

	public double getCost() {
		return cost;
	}

	public Node getTarget() {
		return target;
	}
    
}
