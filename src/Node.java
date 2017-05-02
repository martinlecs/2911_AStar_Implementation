import java.util.ArrayList;
import java.util.Iterator;

public class Node {
	private final String value;
	private int unloadCost = 0;
	private int costSoFar = 0;
    private ArrayList<Edge> connected = new ArrayList<Edge>();
    private Node parent = null;
    private ArrayList<Tuple<String, String>> jobList = new ArrayList<Tuple<String, String>>();
    private ArrayList<Tuple<String, String>> completedJobs = new ArrayList<Tuple<String, String>>();

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
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
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
	public int getCostSoFar() {
		return costSoFar;
	}

	public void setCostSoFar(int costSoFar) {
		this.costSoFar = costSoFar;
	}

	public ArrayList<Tuple<String, String>> getJobList() {
		return jobList;
	}

	public void setJobList(ArrayList<Tuple<String, String>> jobList) {
		this.jobList = jobList;
	}

	public ArrayList<Tuple<String, String>> getCompletedJobs() {
		return completedJobs;
	}

	public void setCompletedJobs(ArrayList<Tuple<String, String>> completedJobs) {
		this.completedJobs = completedJobs;
	}

	@Override
	public String toString() {
		return "Node [Location=" + value + ", pathCost=" + costSoFar + /*", connected=" + connected +*/ "]";
	}
	
	public void printNodeInfo() {
		//Value is connected to X with cost Y
		for (Edge curr : connected) {
			System.out.println(this.getValue() + " is connected to " + curr.getLocation2()
							   + " with cost " + curr.getCost());
		}
	}
	
	public void checkJob() {
		Iterator<Tuple<String, String>> curr = this.getJobList().iterator();
		while(curr.hasNext()) {
			Tuple<String, String> t = curr.next();
			if((t.getLeft().equals(this.getParent().getValue()) && t.getRight().equals(this.getValue()))) {
				this.jobList.remove(curr);
			}
		}
		
	}
    
}
