import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
/**
 * Calculates the total distance to reach each job from the current location + total distance to complete a job
 * Initialising the heuristic for the first time is O(n^3) but any subsequent call to this class has O(n) runtime
 * Very good to use on certain graphs
 * Not very good to use on graphs where one edge that we must visit has high cost compared to the other edges.
 * @author martinle
 *
 */
public class Heuristic2 implements Strategy{
	private HashMap<String, Integer> cities;
	private HashMap<Integer, String> hash;
	private double[][] matrix;
	
	/**
	 * Initialises the heuristic, if it hasn't been yet and calls a function to calculate the heuristic
	 */
	@Override
	public int getHeuristic(Graph g, State state) { 
		if (this.cities == null && this.hash == null && this.matrix == null) {
			initialiseHeuristic(g);
		}
		return getCostsToReachAllJobs (g, state);
	}
	/**
	 * Initialises the heuristic by creating a HashMap of Cities and Integers for accessory functions and creates the Floyd-Warshall matrix
	 * @param g
	 */
	private void initialiseHeuristic(Graph g) {
		getMapOfCities(g);
		getFloydWarshall(g, cities);
		getHashOfIntegersToCities (g);
	}
	/**
	 * Gets total travel cost from current location to all other jobs.
	 * @param g		Graph object
	 * @param s		The current state
	 * @return		The value of the heuristic
	 */
	public int getCostsToReachAllJobs (Graph g, State s) {
		HashMap<String, Integer> cities = this.cities;
		double[][] matrix = this.matrix;
		
		int i = 0;
		double total = matrix[cities.get(s.getLocation())][i];
		for( ; i < cities.size(); i++) {
			if(checkInJobList(g, s, i)) {
				total += matrix[cities.get(s.getLocation())][i];
			}
		}
		return (int) total;
		
//		//find best possible move (cheapest) thats in the job list
//		double min = Double.POSITIVE_INFINITY;
//		int i = 0;
//		for( ; i < cities.size(); i++) {
//		if(checkInJobList(g, s, i)) {
//			if(matrix[cities.get(s.getLocation())][i] < min && matrix[cities.get(s.getLocation())][i] != 0) {
//				min = matrix[cities.get(s.getLocation())][i];
//			}
//		}
//	}
//	return (int) min;
	}
	/**
	 * Checks if an location is in the joblist
	 * @param g			The graph object
	 * @param s			The current state
	 * @param city		The location as an integer as defined in the HashMap
	 * @return			A boolean value, true if in joblist, false otherwise
	 */
	private boolean checkInJobList(Graph g, State s, int city) {
		for (Edge edge : s.getJobList()) {
			if (this.hash.get(city).equals(edge.getLocation1()) || this.hash.get(city).equals(edge.getLocation2())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Creates a hash of Cities where cities are defined by the order that they were made
	 * @param g		The graph object
	 */
	private void getHashOfIntegersToCities (Graph g) {
		HashMap<Integer, String> hash = new HashMap<Integer, String>();
		int counter = 0;
		Iterator<Entry<String, Node>> it = g.getMapOfNodes().entrySet().iterator();
		while(it.hasNext()) {
			Node n = it.next().getValue();
			if(!hash.containsKey(n.getValue())) {
				hash.put(counter, n.getValue());
				counter++;
			}
		}
		this.hash = hash;
	}
	
	/**
	 * Creates a hash of cities where inputting a String (name of location) will return its number
	 * @param g		The Graph object
	 */
	private void getMapOfCities(Graph g) {
		int counter = 0;
		HashMap<String, Integer> cities = new HashMap<String, Integer>();
		Iterator<Entry<String, Node>> it = g.getMapOfNodes().entrySet().iterator();
		while(it.hasNext()) {
			Node n = it.next().getValue();
			if(!cities.containsKey(n.getValue())) {
				cities.put(n.getValue(), counter);
				counter++;
			}
		}
		this.cities = cities;
	}
	/**
	 * Creates the Floyd-Warshall matrix which finds the shortest path to each location, from every location
	 * @param g			The graph object
	 * @param cities	The city hash
	 */
	private void getFloydWarshall(Graph g, HashMap<String, Integer> cities) {
		FloydWarshall f = new FloydWarshall(g.getMapOfNodes().size());
		Iterator<Entry<String, Node>> curr = g.getMapOfNodes().entrySet().iterator();
		while(curr.hasNext()) {
			Node node = curr.next().getValue();
			for(Edge edge : node.getConnected()) {
				f.addEdge(cities.get(edge.getLocation1()), cities.get(edge.getLocation2()), edge.getCost());
			}
		}
		this.matrix = f.floydWarshall();
//		printMatrix(matrix);
	}
	/**
	 * Prints out the matrix
	 * @param matrix
	 */
    public void printMatrix(double[][] matrix) {
    	for(int i = 0; i < matrix.length; i++) {
    		for(int j = 0; j < matrix.length; j++) {
    			System.out.print("[" + matrix[i][j] + "]");
    		}
    		System.out.print("\n");
    	}
    }
}