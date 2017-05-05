/**
 * Implements Edges of Graph
 * @author martinle
 *
 */
public class Edge {
    private int cost;	//the weight of the edge
    private final String location1;
    private final String location2;
    
    public Edge(int cost, String location1, String location2) {
		this.cost = cost;
		this.location1 = location1;
		this.location2 = location2;
	}

	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getLocation1() {
		return location1;
	}

	public String getLocation2() {
		return location2;
	}

	@Override
	protected Edge clone() throws CloneNotSupportedException {
		Edge newEdge = new Edge(this.getCost(), this.getLocation1(), this.getLocation2());
		return newEdge;
	}
	
	@Override
	public String toString() {
		return "Edge [cost=" + cost + ", location1=" + location1 + ", location2=" + location2 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cost;
		result = prime * result + ((location1 == null) ? 0 : location1.hashCode());
		result = prime * result + ((location2 == null) ? 0 : location2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
//		if (cost != other.cost)
//			return false;
		if (location1 == null) {
			if (other.location1 != null)
				return false;
		} else if (!location1.equals(other.location1))
			return false;
		if (location2 == null) {
			if (other.location2 != null)
				return false;
		} else if (!location2.equals(other.location2))
			return false;
		return true;
	}
}
