import java.util.ArrayList;
/**
 * Implementation of Node class for the graph
 * @author martinle
 *
 */
public class Node {
	private final String value;
	private int unloadCost = 0;
    private ArrayList<Edge> connected = new ArrayList<Edge>();


    //Constructor
    public Node(String value, int cost) {
    	this.value = value;
    	this.unloadCost = cost;
    }
    
    //Getters and Setters
	public ArrayList<Edge> getConnected() {
		return connected;
	}
	public void setConnected(ArrayList<Edge> connected) {
		this.connected = connected;
	}
	public String getValue() {
		return value;
	}
	public int getUnloadCost() {
		return unloadCost;
	}
	public void setUnloadCost(int unloadCost) {
		this.unloadCost = unloadCost;
	}
	@Override
	public String toString() {
		return "Node [Location=" + value + "]";
	}
}
