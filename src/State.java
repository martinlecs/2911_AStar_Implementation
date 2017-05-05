import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Objects;

/**
 * Implementation of State class for A* search
 * @author martinle
 *
 */
public class State {
	
	private String location;
	private LinkedList<Edge> jobList;
	private int costSoFar;
	private int heuristic;
	private LinkedList<Edge> path = new LinkedList<Edge>();
	private State prevState;
	
	public State (String location, LinkedList<Edge> jobList, int costSoFar, int heuristic, State prevState) {
		this.location = location;
		this.jobList = jobList;
		this.costSoFar = costSoFar;
		this.heuristic = heuristic;
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
	public State getPrevState() {
		return prevState;
	}
	public void setPrevState(State prevState) {
		this.prevState = prevState;
	}
	public LinkedList<Edge> getPath() {
		return path;
	}
	public void setPath(LinkedList<Edge> path) {
		this.path = path;
	}
	
	/**
	 * Checks if an edge is in the job list
	 * @param e		The edge that we want to check
	 * @return		A boolean value
	 */
	public boolean checkJob(Edge e) {
		for(Edge curr : this.jobList) {
			if(curr.equals(e)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Removes a job from the list
	 * @param e		The edge that we want to remove
	 */
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
	/**
	 * Copies a list of edges
	 * @return	A copy of the LinkedList<Edge> target
	 */
	protected LinkedList<Edge> copyJobList() {
		LinkedList<Edge> newList = new LinkedList<Edge>();
		for(Edge e: this.getJobList()) {
			newList.add(e);
		}
		return newList;
	}
	
	/**
	 * Overriding the toString method, mainly for debugging purposes
	 */
	@Override
	public String toString() {
		String s = (this.getPrevState() == null) ? "at start" : prevState.getLocation();
		return "State [location=" + location + ", jobList=" + jobList + ", costSoFar=" + costSoFar + ", prevState=" + s + "]";
	}
	
	/**
	 * Overriding the hashCode method, for the closed set
	 */
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + costSoFar;
//		result = prime * result + heuristic;
//		result = prime * result + ((jobList == null) ? 0 : (int) jobListHash(jobList));
//		result = prime * result + ((location == null) ? 0 : location.hashCode());
//		result = prime * result + ((prevState == null) ? 0 : prevState.hashCode());
//		System.out.println(result);
//		return result;
//	}
	
//	/**
//	 * Overriding the equals method, for the closed set
//	 */
//	@Override
//	public boolean equals(Object obj) {
//		if (obj == null) return false;
//		if (this == obj) return true;
//		if (!(obj instanceof State)) return false;
//		
//		State other = (State) obj;
//
//		//Compare integers
//		if (this.costSoFar != other.costSoFar) return false;
//		if(this.heuristic != other.heuristic) return false;
//		
//		if (jobList == null && other.jobList != null) {
//			return false;
//		} else if (!EdgeListEquals(this.jobList, other.jobList)) return false;
//		
//		if (location == null && other.location != null) {
//			return false;
//		} else if (!location.equals(other.location)) return false;
//		
//		if (prevState == null && other.prevState != null) {
//			return false;
//		} else if (!prevState.equals(other.prevState)) { 
//			return false;
//		}
//		return true;
//	}
//	
//	/**
//	 * Check if a list of edges is equal to another list of edges
//	 * @param a		List A
//	 * @param b		List B
//	 * @return		Boolean value
//	 */
//	private boolean EdgeListEquals (LinkedList<Edge> a, LinkedList<Edge> b) {
//		//Check if either are null
//		if((a == null && b != null) || (a != null && b == null)) return false;
//		//check if size is the same
//		if(a.size() != b.size()) return false; //This is causing a problem
//		//check if reference is the same
//		if(a == b) return true;
//		
//		for(Edge e1 : a) {
//			for(Edge e2 : b) {
//				if(!e1.equals(e2)) {
//					return false;
//				}
//			}
//		}
//		return true;
//	}
//
//	/**
//	 * Creates a hashcode for a LinkedList of Edges
//	 * @param e
//	 * @return
//	 */
//	private int jobListHash(LinkedList<Edge> e) {
//		System.out.println(e);
//		final int prime = 31;
//		int result = 1;
//		for(Edge curr : e) {
//			result += prime * curr.hashCode();
//		}
//		return result;
//	}
//    @Override
//    public boolean equals(Object o) {
//
//        if (o == this) return true;
//        if (!(o instanceof State)) {
//            return false;
//        }
//        State user = (State) o;
//        return (location == user.location) &&
//                Objects.equals(costSoFar, user.costSoFar) &&
//                Objects.equals(heuristic, user.heuristic);
//    }
//
//    @Override
//    public int hashCode() {
//    	System.out.println(this.location + "cost=" + this.costSoFar);
//    	System.out.println(Objects.hash(location, jobList, costSoFar, heuristic, path, prevState));
//        return Objects.hash(location, jobList, costSoFar, heuristic, path, prevState);
//        
//    }

}
