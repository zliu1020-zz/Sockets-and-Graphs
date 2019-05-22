import java.util.*; 
import java.lang.*;

class UndirectedGraph {
	// stores all the nodes
	private HashMap<Integer, Node> nodes;
	// stores all the edges
	private ArrayList<Edge> edges;

    
    public UndirectedGraph(){
    	nodes = new HashMap<Integer, Node>();
    	edges = new ArrayList<Edge>();
    }
    
    public void build(int aID, int bID){
		Node a = new Node(aID);
		Node b = new Node(bID);
		nodes.put(a.id, a);
		nodes.put(b.id, b);
		
    	Edge edge = new Edge(aID, bID);
    	edges.add(edge);
    }

    public int findRoot(Node node) {
    	if (node.parentID != node.id)
    		// parent node
    		node.parentID = findRoot(nodes.get(node.parentID));
    	return node.parentID;
    }
    
    public void merge(Node a, Node b) {
    	Node aRoot = nodes.get(findRoot(a));
    	Node bRoot = nodes.get(findRoot(b));
    	
    	if (aRoot.id != bRoot.id) {
        	// if aRoot's rank > bRoot's rank
        	if (aRoot.rank > bRoot.rank) {
            	// attach bRoot to a's tree
        		bRoot.parentID = aRoot.id;
            	// if aRoot's rank < bRoot's rank
        	} else if (aRoot.rank < bRoot.rank) {
        		// attach aRoot to b's tree
        		aRoot.parentID = bRoot.id;
        		// if aRoot's rank == bRoot's rank
        	} else {
        		// choose any root nodes to be the parent
        		bRoot.parentID = aRoot.id;
        		// increase the rank
        		aRoot.rank ++;
        	}
    	} 
    }
    
    public void connectEdges() {
    	for(Edge edge : edges) {		
    		Node a = nodes.get(edge.aID);
    		Node b = nodes.get(edge.bID);
    		merge(a, b);
    	}
    }
    
    public String generateLabels() {
    	StringBuilder sb = new StringBuilder();
        for (HashMap.Entry<Integer, Node> entry: nodes.entrySet()) {
        	sb.append(entry.getKey() + " " + findRoot(entry.getValue()) + "\n");
        }
        return sb.toString();
    }
    
//    public static void main(String args[]){
//        UndirectedGraph graph = new UndirectedGraph();
//        graph.build(10,11);
//        graph.build(10, 12);
//        graph.build(10, 13);
//        graph.build(10, 17);
//        graph.build(11, 12);
//        graph.build(11, 14);
//        graph.build(11, 16);
//        graph.build(11, 18);
//        graph.build(1, 2);
//        graph.build(12, 13);
//        graph.build(1, 3);
//        graph.build(14, 19);
//        graph.build(15, 16);
//        graph.build(15, 17);
//        graph.build(15, 18);
//        graph.build(1, 6);
//        graph.build(16, 17);
//        graph.build(16, 19);
//        graph.build(1, 8);
//        graph.build(2, 10);
//        graph.build(2, 11);
//        graph.build(2, 12);
//        graph.build(2, 15);
//        graph.build(2, 16);
//        graph.build(2, 3);
//        graph.build(2, 4);
//        graph.build(2, 5);
//        graph.build(2, 7);
//        graph.build(3, 17);
//        graph.build(3, 19);
//        graph.build(3, 4);
//        graph.build(3, 5);
//        graph.build(3, 8);
//        graph.build(3, 9);
//        graph.build(4, 10);
//        graph.build(4, 11);
//        graph.build(4, 12);
//        graph.build(4, 13);
//        graph.build(4, 7);
//        graph.build(4, 9);
//        graph.build(5, 9);
//        graph.build(6, 15);
//        graph.build(6, 7);
//        graph.build(7, 10);
//        graph.build(7, 13);
//        graph.build(7, 16);
//        graph.build(7, 8);
//        graph.build(7, 9);
//        graph.build(8, 12);
//        graph.build(8, 18);
//        graph.build(8, 9);
//        graph.build(9, 11);
//        graph.build(9, 14);
//
//        System.out.println("Disjoint Sets: ");
//        graph.connectEdges();
//    }
   
}
