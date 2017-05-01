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
		ArrayList<Tuple<String, String>> jobList = new ArrayList<Tuple<String, String>>();
		
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
		    				Tuple<String, String> job = new Tuple<String, String>(a[1], a[2]);
		    				jobList.add(job);
		    				break;
		    			default:
		    				continue;
		    		}	
		    	}
		    }
			if (!jobList.isEmpty()) {
				UniformCostSearchAlgo.UniformCostSearch(g, "Dubbo");
				List<Node> path = UniformCostSearchAlgo.printPath(g, "Dubbo");
				System.out.println("Path: " + path);
			}
		} catch (FileNotFoundException e) {
			System.err.println ("File was not found");
		}
		finally {
			if (sc != null) sc.close();
		}
	}
}
