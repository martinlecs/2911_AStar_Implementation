import java.util.LinkedList;
import java.util.ListIterator;


public class State {
	
	private String location;
	private LinkedList<Edge> jobList;
	private double costSoFar;
	private LinkedList<Edge> completedJobs;
	private State prevState;
	
	public State (String location, LinkedList<Edge> jobList, double costSoFar, LinkedList<Edge> completedJobs, State prevState) {
		this.location = location;
		this.jobList = jobList;
		this.costSoFar = costSoFar;
		this.completedJobs = completedJobs;
		this.prevState = prevState;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public LinkedList<Edge> getJobList() {
		return jobList;
	}
	public void setJobList(LinkedList<Edge> jobList) {
		this.jobList = jobList;
	}
	public double getCostSoFar() {
		return costSoFar;
	}
	public void setCostSoFar(double costSoFar) {
		this.costSoFar = costSoFar;
	}
	public LinkedList<Edge> getCompletedJobs() {
		return completedJobs;
	}
	public void setCompletedJobs(LinkedList<Edge> completedJobs) {
		this.completedJobs = completedJobs;
	}
	public State getPrevState() {
		return prevState;
	}
	public void setPrevState(State prevState) {
		this.prevState = prevState;
	}
	
	public boolean checkJob(Edge e) {
		for(Edge curr : this.jobList) {
			if(curr.equals(e)) {
				return true;
			}
		}
		return false;
	}
	public void deleteJob (Edge e) {
		ListIterator<Edge> curr = this.jobList.listIterator();
		while(curr.hasNext()) {
			Edge next = curr.next();
			if(next.equals(e)) {
				curr.remove();
				break;
			}
		}
	}
}
