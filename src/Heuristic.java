import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

public class Heuristic implements Strategy{
	HashMap<String, Integer> cities;
	HashMap<Integer, String> hash;
	double[][] matrix;
	
	@Override
	public int getHeuristic(Graph g, State state) {
		getMapOfCities(g);
		getFloydWarshall(g, cities);
		getHashOfIntegersToCities (g);
		int hCost = 0;
		for(Edge e: state.getJobList()) {
			hCost += (e.getCost() + g.getMapOfNodes().get(e.getLocation2()).getUnloadCost());
		}
		hCost += getCostsToReachAllJobs (g, state); 
		//hCost += getEstimateToNextJob (g, state);
		return hCost;
	}
	//If a job is adjacent to current node, effective if a lot of jobs are close together
	private int getEstimateToNextJob (Graph g, State s) {
		//get current location, find neighours 
		sortEdges comparator = new sortEdges();
		g.getMapOfNodes().get(s.getLocation()).getConnected().sort(comparator);
		ArrayList<Edge> sortedList = g.getMapOfNodes().get(s.getLocation()).getConnected();
		LinkedList<Edge> jobList = s.getJobList();
		int costNextJob = 0;
		for(Edge e: sortedList) {
			for(Edge job : jobList) {
				if(e.getLocation2() == job.getLocation1()) {
					costNextJob = job.getCost();
				}
			}
		}
		return costNextJob;
	}
	/**
	 * Gets total travel cost from current location to all other jobs.
	 * @param g
	 * @param s
	 * @return
	 */
	public int getCostsToReachAllJobs (Graph g, State s) {
		HashMap<String, Integer> cities = this.cities;
		double[][] matrix = this.matrix;
		//check if i is in joblist
		int i = 0;
		double total = matrix[cities.get(s.getLocation())][i];
		for( ; i < cities.size(); i++) {
			if(checkInJobList(g, s, i)) {
				total += matrix[cities.get(s.getLocation())][i];
			}
		}
		return (int) total;
	}
	
	private boolean checkInJobList(Graph g, State s, int city) {
		for (Edge edge : s.getJobList()) {
			if (this.hash.get(city).equals(edge.getLocation1()) || this.hash.get(city).equals(edge.getLocation2())) {
				return true;
			}
		}
		return false;
	}
	
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
	}
}

final class sortEdges implements Comparator<Edge> {
    
	@Override
    public int compare(Edge a, Edge b){
		if(a.getCost() > b.getCost()){
	        return 1;
	    } else if (a.getCost() < b.getCost()){
	        return -1;
	    } else{
	        return 0;
	    }
	}
}


final class FloydWarshall {

    // graph represented by an adjacency matrix
    private double[][] graph;

    public FloydWarshall(int n) {
        this.graph = new double[n][n];
        initGraph();
    }

    private void initGraph() {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
    }

    // utility function to add edges to the adjacencyList
    public void addEdge(int from, int to, double weight) {
        graph[from][to] = weight;
    }

    // all-pairs shortest path
    public double[][] floydWarshall() {
        double[][] distances;
        int n = this.graph.length;
        distances = Arrays.copyOf(this.graph, n);

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
                }
            }
        }
        return distances;
    }

}