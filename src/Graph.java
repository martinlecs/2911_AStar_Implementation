import java.util.HashMap;

public class Graph {
	private final HashMap<String, Node> mapOfNodes;
	
	public Graph() {
		this.mapOfNodes = new HashMap<String, Node>();
	}
	public void addNode (String val, int cost) {
		Node n = new Node(val, cost);
		mapOfNodes.put(val, n);
	}
	/**
	 * Creates an edge between two existing nodes.
	 * @param start		One of the locations where the edge lies between
	 * @param end		The other location where the edge lies between
	 * @param weight	The weight of the edge
	 */
	public void addEdge (String start, String end, int weight) {
		//find start node, if it exists already in the List
		Node a = this.mapOfNodes.get(start);
		Node b = this.mapOfNodes.get(end);
		if (a != null && b != null) {
			//Create edges. Edge needs Node and weight
			Edge edge1 = new Edge(weight, start, end);
			Edge edge2 = new Edge(weight, end, start);

			//Add edges to the right lists
			a.getConnected().add(edge1);
			b.getConnected().add(edge2);
		}
	}
	public HashMap<String, Node> getMapOfNodes() {
		return mapOfNodes;
	}
}
