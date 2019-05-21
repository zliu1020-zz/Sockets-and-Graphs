import java.util.*; 
import java.lang.*;

class UndirectedGraph {
	// stores all the nodes
	private HashMap<Integer, Node> nodes;
	// stores all the edges
	private ArrayList<Edge> edges;

    
    public UndirectedGraph(ArrayList<Integer> nodeIDList){
    	nodes = new HashMap<Integer, Node>();
    	edges = new ArrayList<Edge>();
    	makeNodes(nodeIDList);
    }
    
    public void addEdge(int aID, int bID){
    	Node a = nodes.get(aID);
    	Node b = nodes.get(bID);
    	Edge edge = new Edge(a, b);
    	edges.add(edge);
    }
    
    // makeSet
    public void makeNodes(ArrayList<Integer> nodeIDList) {
    	for(Integer nodeID: nodeIDList) {
    		Node node = new Node(nodeID);
//    		System.out.print("Debug: " + node.id);
    		nodes.put(node.id, node);
    	
    	}
    }
    
    public Node findRoot(Node node) {
    	if (node.parentID != node.id) 
    		return findRoot(nodes.get(node.parentID));
    	return node;
    }
    
    public void merge(Node a, Node b) {
    	Node aRoot = findRoot(a);
    	Node bRoot = findRoot(b);
    
    	
    	// if aRoot's rank > bRoot's rank
    	if (aRoot.rank > bRoot.rank) {
        	// attach bRoot to a's tree
    		bRoot.parentID = a.id;
        	// if aRoot's rank < bRoot's rank
    	} else if (aRoot.rank < bRoot.rank) {
    		// attach aRoot to b's tree
    		aRoot.parentID = b.id;
    		// if aRoot's rank == bRoot's rank
    	} else {
    		// choose any root nodes to be the parent
    		bRoot.parentID = a.id;
    		// increase the rank
    		aRoot.rank ++;
    	}
    }
    
    public void connectEdges() {
    	for(Edge edge : edges) {
    		Node a = edge.a;
    		Node b = edge.b;
    		
    		Node aRoot = findRoot(a);
    		Node bRoot = findRoot(b);
    		
    		// do nothing if aRoot == bRoot 
    		if (aRoot.id == bRoot.id) {
    			
    		} else {
    			merge(a, b);
//    	        for (HashMap.Entry<Integer, Node> entry: nodes.entrySet()) {
//    	        	System.out.println("ID: " + entry.getKey() + " ParentID: " + entry.getValue().parentID + " Rank: " + entry.getValue().rank);
//    	        }
    		}
    	}
//        for (HashMap.Entry<Integer, Node> entry: nodes.entrySet()) {
//        	System.out.println("ID: " + entry.getKey() + " ParentID: " + entry.getValue().parentID + " Rank: " + entry.getValue().rank);
//        }
    	printNodes();
    }
    
    public void printNodes(){
        //Find different Sets
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (HashMap.Entry<Integer, Node> entry: nodes.entrySet()) {
            if(map.containsKey(entry.getValue().parentID)){
                ArrayList<Integer> list = map.get(entry.getValue().parentID);
                list.add(entry.getKey());//add vertex
                map.put(entry.getValue().parentID, list);
            }else{
                ArrayList<Integer> list = new ArrayList<>();
                list.add(entry.getKey());
                map.put(entry.getValue().parentID, list);
            }
        }
        //Print the Different sets
        Set<Integer> set = map.keySet();
        Iterator<Integer> iterator = set.iterator();
        while(iterator.hasNext()){
            int key = iterator.next();
            System.out.println("Set Id: " + key + " elements: " + map.get(key));
        }
    }
    
    public static void main(String args[]){
        ArrayList vertices = new ArrayList<Integer>();
        vertices.add(0);
        vertices.add(1);
        vertices.add(2);
        vertices.add(3);
        vertices.add(4);
        vertices.add(5);
        
        UndirectedGraph graph = new UndirectedGraph(vertices);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(4, 5);
        System.out.println("Disjoint Sets: ");
        graph.connectEdges();
    }
   
}