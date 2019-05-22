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
//    	makeNodes(nodeIDList);
    }
    
    public void build(int aID, int bID){
		Node a = new Node(aID);
		Node b = new Node(bID);
		nodes.put(a.id, a);
		nodes.put(b.id, b);
		
    	Edge edge = new Edge(aID, bID);
    	edges.add(edge);
    }
    
//    // makeSet
//    public void makeNodes(ArrayList<Integer> nodeIDList) {
//    	for(Integer nodeID: nodeIDList) {
//    		Node node = new Node(nodeID);
////    		System.out.print("Debug: " + node.id);
//    		nodes.put(node.id, node);
//    	
//    	}
//    }
    
    public Node findRoot(Node node) {
    	if (node.parentID != node.id)
    		// parent node
    		return findRoot(nodes.get(node.parentID));
    	return node;
    }
    
    public void merge(Node a, Node b) {
    	Node aRoot = findRoot(a);
    	Node bRoot = findRoot(b);
    
    	
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
    
    public void connectEdges() {
    	for(Edge edge : edges) {
    		
    		Node a = nodes.get(edge.aID);
    		Node b = nodes.get(edge.bID);
    		
    		Node aRoot = findRoot(a);
    		Node bRoot = findRoot(b);
    		
    		// do nothing if aRoot == bRoot 
    		if (aRoot.id == bRoot.id) {
    			
    		} else {
    			merge(a, b);
    		}
    	}
//    	printNodes();
    }
    
    public String generateLabels() {
    	StringBuilder sb = new StringBuilder();
        for (HashMap.Entry<Integer, Node> entry: nodes.entrySet()) {
        	sb.append(entry.getKey() + " " + entry.getValue().parentID + "\n");
        }
        return sb.toString();
    }
    
//    public void printNodes(){
//        //Find different Sets
//        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
//        for (HashMap.Entry<Integer, Node> entry: nodes.entrySet()) {
//            if(map.containsKey(entry.getValue().parentID)){
//                ArrayList<Integer> list = map.get(entry.getValue().parentID);
//                list.add(entry.getKey());//add vertex
//                map.put(entry.getValue().parentID, list);
//            }else{
//                ArrayList<Integer> list = new ArrayList<>();
//                list.add(entry.getKey());
//                map.put(entry.getValue().parentID, list);
//            }
//        }
//        //Print the Different sets
//        Set<Integer> set = map.keySet();
//        Iterator<Integer> iterator = set.iterator();
//        while(iterator.hasNext()){
//            int key = iterator.next();
//            System.out.println("Set Id: " + key + " elements: " + map.get(key));
//        }
//    }
    
//    public static void main(String args[]){
//        ArrayList vertices = new ArrayList<Integer>();
//        vertices.add(0);
//        vertices.add(1);
//        vertices.add(2);
//        vertices.add(3);
//        vertices.add(4);
//        vertices.add(5);
//        
//        UndirectedGraph graph = new UndirectedGraph(vertices);
//        graph.addEdge(0, 1);
//        graph.addEdge(0, 2);
//        graph.addEdge(1, 3);
//        graph.addEdge(4, 5);
//        System.out.println("Disjoint Sets: ");
//        graph.connectEdges();
//    }
   
}