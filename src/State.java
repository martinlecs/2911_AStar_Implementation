import java.util.LinkedList;
import java.util.ListIterator;


public class State {
	
	private String location;
	private LinkedList<Edge> jobList;
	private int costSoFar;
	private int heuristic;
	private LinkedList<Edge> completedJobs;
	private State prevState;
	
	public State (String location, LinkedList<Edge> jobList, int costSoFar, int heuristic, LinkedList<Edge> completedJobs, State prevState) {
		this.location = location;
		this.jobList = jobList;
		this.costSoFar = costSoFar;
		this.heuristic = heuristic;
		this.completedJobs = completedJobs;
		this.prevState = prevState;
	}
	public int getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
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
	public int getCostSoFar() {
		return costSoFar;
	}
	public void setCostSoFar(int costSoFar) {
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
	public void removeJob (Edge e) {
		ListIterator<Edge> curr = this.jobList.listIterator();
		while(curr.hasNext()) {
			Edge next = curr.next();
			if(next.equals(e)) {
				curr.remove();
				break;
			}
		}
	}

	protected LinkedList<Edge> copyJobList() {
		LinkedList<Edge> newList = new LinkedList<Edge>();
		for(Edge e: this.getJobList()) {
			newList.add(e);
		}
		return newList;
	}

	@Override
	public String toString() {
		String s = (this.getPrevState() == null) ? "at start" : prevState.getLocation();
		return "State [location=" + location + ", jobList=" + jobList + ", costSoFar=" + costSoFar + ", completedJobs="
				+ completedJobs + ", prevState=" + s + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		//Check if LinkedList<Edge> is the same
		if (completedJobs == null) {
			if (other.completedJobs != null)
				return false;
		} else if (!completedJobs.equals(other.completedJobs))
			return false;
		if (costSoFar != other.costSoFar)
			return false;
		if (jobList == null) {
			if (other.jobList != null)
				return false;
		} else if (!jobList.equals(other.jobList))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (prevState == null) {
			if (other.prevState != null)
				return false;
		} else if (!prevState.equals(other.prevState))
			return false;
		return true;
	}
	
	
	
	
	
}
