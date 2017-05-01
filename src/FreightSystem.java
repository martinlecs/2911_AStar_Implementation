import java.util.*;
import java.io.*;

/**
 * 
 * @author martinle
 *
 */
public class FreightSystem {
	public static void main (String[] args) throws IOException{
		
		//declare a new Graph
		Graph g = new Graph();
		LinkedList<Edge> jobList = new LinkedList<Edge>();
		
		Scanner sc = null;
		try {
			sc = new Scanner(new FileReader(args[0]));
			
			while(sc.hasNextLine()) {
			
				String input = sc.nextLine().split("#")[0];
				String[] a = (input.trim()).split("[ ]+");
				
				//ignores lines that are empty or begin with a #
		    	if (!a[0].equals("") && !a[0].equals("#") ) {
		    		switch(a[0]) {
		    			case "Unloading":
		    				//create nodes
		    				g.addNode(a[2], Integer.parseInt(a[1]));
		    				break;
		    			case "Cost":
		    				//create edges
		    				g.addEdge(a[2], a[3], Integer.parseInt(a[1]));
		    				break;
		    			case "Job":
		    				//Make a tuple
		    				Edge e = new Edge (0, a[1], a[2]); //edge with 0 cost isn't really an edge
		    				jobList.add(e);
		    				break;
		    			default:
		    				continue;
		    		}	
		    	}
		    }
			if (!jobList.isEmpty()) {
				Searcher.AStarSearch(g, jobList);
				//List<Node> path = Searcher.printPath(g, "Dubbo");
				//System.out.println("Path: " + path);
			}
		} catch (FileNotFoundException e) {
			System.err.println ("File was not found");
		}
		finally {
			if (sc != null) sc.close();
		}
	}
}
