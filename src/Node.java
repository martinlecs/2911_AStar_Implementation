import java.util.ArrayList;

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
	
	public void printNodeInfo() {
		//Value is connected to X with cost Y
		for (Edge curr : connected) {
			System.out.println(this.getValue() + " is connected to " + curr.getLocation2()
							   + " with cost " + curr.getCost());
		}
	}
	
//	public void checkJob() {
//		Iterator<Tuple<String, String>> curr = this.getJobList().iterator();
//		while(curr.hasNext()) {
//			Tuple<String, String> t = curr.next();
//			if((t.getLeft().equals(this.getParent().getValue()) && t.getRight().equals(this.getValue()))) {
//				this.jobList.remove(curr);
//			}
//		}
		
//	}
    
}
