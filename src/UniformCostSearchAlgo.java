import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

//diff between uniform cost search and dijkstra algo is that UCS has a goal
/**
 * Returns boolean value indicating if path has been found
 * @author martinle
 *
 */
public class UniformCostSearchAlgo{
    public static boolean UniformCostSearch(Graph g, String end){
    	//Source node is always Sydney
    	Node source = g.findNode("Sydney");
    	Node target = g.findNode(end);
    	
        source.setCostSoFar(0);
        Comparator<Node> strategy = new Strategy();
        PriorityQueue<Node> open = new PriorityQueue<Node>(strategy);
        
        open.add(source);
        Set<Node> explored = new HashSet<Node>();
        boolean found = false;
        double cost_so_far = 0;

        //while frontier is not empty
        while(!open.isEmpty()){
            System.out.println(open);
            Node current = open.poll();
            explored.add(current);
            cost_so_far = current.getCostSoFar();
            
            
            if (current.getValue().equals(target.getValue())) {
            	found = true;
            	//early exit, this breaks too early
            	break;
            }
            
            for(Edge e: current.getConnected()) {
            	double new_cost = cost_so_far + e.getCost();
                Node child = e.getTarget();
                
                if((!explored.contains(child) && !open.contains(child)) || (new_cost < cost_so_far)){
                    child.setParent(current);
                    child.setCostSoFar(new_cost);
                    open.add(child);
                }
            }
        }
        return found;

    }

    /**
     * Gives you the path that was taken to reach the goal
     * @param target
     * @return
     */
    public static List<Node> printPath(Graph g, String end){
    	Node target = g.findNode(end);
        List<Node> path = new ArrayList<Node>();
        for(Node node = target; node != null; node = node.getParent()){
            path.add(node);
        }
        
        Collections.reverse(path);

        return path;
    }
}



