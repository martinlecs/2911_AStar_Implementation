
public class State {
	
	private String location;
	private Edge[] jobList;
	private int costSoFar;
	private Edge[] completedJobs;
	private State prevState;
	
	public State (String location, Edge[] jobList, int costSoFar, Edge[] completedJobs, State prevState) {
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
	public Edge[] getJobList() {
		return jobList;
	}
	public void setJobList(Edge[] jobList) {
		this.jobList = jobList;
	}
	public int getCostSoFar() {
		return costSoFar;
	}
	public void setCostSoFar(int costSoFar) {
		this.costSoFar = costSoFar;
	}
	public Edge[] getCompletedJobs() {
		return completedJobs;
	}
	public void setCompletedJobs(Edge[] completedJobs) {
		this.completedJobs = completedJobs;
	}
	public State getPrevState() {
		return prevState;
	}
	public void setPrevState(State prevState) {
		this.prevState = prevState;
	}
}
