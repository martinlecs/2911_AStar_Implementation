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

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((completedJobs == null) ? 0 : completedJobs.hashCode());
//		result = prime * result + costSoFar;
//		result = prime * result + heuristic;
//		result = prime * result + ((jobList == null) ? 0 : jobList.hashCode());
//		result = prime * result + ((location == null) ? 0 : location.hashCode());
//		result = prime * result + ((prevState == null) ? 0 : prevState.hashCode());
//		return result;
//	}
//	
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		State other = (State) obj;
//		if (completedJobs == null) {
//			if (other.completedJobs != null)
//				return false;
//			System.out.println("printing");
//		} else if (!EdgeListEquals(this.completedJobs, other.completedJobs))
//			return false;
//		if (costSoFar != other.costSoFar)
//			return false;
//		if(this.heuristic != other.heuristic) return false;
//		if (jobList == null) {
//			if (other.jobList != null)
//				return false;
//		} else if (!EdgeListEquals(this.jobList, other.jobList))
//			return false;
//		if (location == null) {
//			if (other.location != null)
//				return false;
//		} else if (!location.equals(other.location))
//			return false;
//		if (prevState == null) {
//			if (other.prevState != null)
//				return false;
//		} else if (!prevState.equals(other.prevState))
//			return false;
//		return true;
//	}
//	
//	private boolean EdgeListEquals (LinkedList<Edge> a, LinkedList<Edge> b) {
//		//Check if either are null
//		if(a == null || b == null) return false;
//		//check if size is the same
//		if(a.size() != b.size()) return false; //This is causing a problem
//		//check if reference is the same
//		if(a == b) return true;
//		
//		for(Edge e1 : a) {
//			for(Edge e2 : b) {
//				if(e1.equals(e2)) {
//					continue;
//				} else {
//					return false;
//				}
//			}
//		}
//		return true;
//	}
//	
//	public void testState () {
//		//Make two new states and compare them
//		LinkedList<Edge> list1 = new LinkedList<Edge>();
//		LinkedList<Edge> list2 = new LinkedList<Edge>();
//		
//		Edge e1 = new Edge(150, "Home", "Uni");
//		Edge e2 = new Edge(50, "Uni", "Shops");
//		Edge e3 = new Edge(12, "Home", "Toilet");
//		
//		list1.add(e1);
//		list1.add(e2);
//		
//		list2.add(e1);
//		list2.add(e3);
//		
//		LinkedList<Edge> list3 = new LinkedList<Edge>();
//		LinkedList<Edge> list4 = new LinkedList<Edge>();
//		
//		list3.add(e3);
//		list3.add(e2);
//		State blank1 = new State ("House", list1, 0, 0, list3, null);
//		State blank2 = new State ("Home", list2, 20, 100239, list4, blank1);
//		State s1 = new State("Home", list1, 0, 0, list3, blank1);
//		State s2 = new State("Home", list1, 0, 0, list3, blank1);
//		
//		if(s1.equals(s2)) System.out.println("equal");
//		else System.out.println("false");
//	}
//	
	
}
