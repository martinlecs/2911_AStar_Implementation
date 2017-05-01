import java.util.ArrayList;

public class Graph {
	ArrayList<Node> listOfNodes;
	//consider Map<E, Node> for faster lookup time
	
	public Graph() {
		this.listOfNodes = new ArrayList<Node>();
	}
	public void addNode (String val, int cost) {
		Node n = new Node(val, cost);
		listOfNodes.add(n);
	}
	/**
	 * Creates an edge between two existing nodes.
	 * @param start		One of the locations where the edge lies between
	 * @param end		The other location where the edge lies between
	 * @param weight	The weight of the edge
	 */
	public void addEdge (String start, String end, int weight) {
		//find start node, if it exists already in the List
		Node a = findNode(start);
		Node b = findNode(end);
		if (a != null && b != null) {
			//Create edges. Edge needs Node and weight
			Edge edge1 = new Edge(weight, start, end);
			Edge edge2 = new Edge(weight, end, start);

			//Add edges to the right lists
			a.getConnected().add(edge1);
			b.getConnected().add(edge2);
		}
	}
	public Node findNode(String val) {
		for(Node curr : listOfNodes) { //Probs better to just use a HashMap
			if (curr.getValue().equals(val)) {
				return curr;
			}
		}
		return null;
	}
	public void printGraph() {
		for (Node n : listOfNodes) {
			System.out.println(n);
		}
	}
	public void printNodeInfo() {
		for(Node curr : listOfNodes) {
			curr.printNodeInfo();
		}
	}
}
