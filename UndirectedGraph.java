import java.util.*; 
import java.lang.*;

class UndirectedGraph {
	// stores all the nodes
//	private HashMap<Integer, Node> nodes;
	private HashMap<Integer, Integer> nodeParentIDList;
	private HashMap<Integer, Integer> nodeRankList;
	// stores all the edges


    
    public UndirectedGraph(){
//    	nodes = new HashMap<Integer, Node>();
    	nodeParentIDList = new HashMap<Integer, Integer>();
    	nodeRankList = new HashMap<Integer, Integer>();

    }
    
//    public int findRoot(Node node) {
//    	if (node.parentID != node.id)
//    		// parent node
//    		node.parentID = findRoot(nodes.get(node.parentID));
//    	return node.parentID;
//    }
    
    public int findRoot(int nodeID) {
//    	int nodeParentID = nodeParentIDList.get(nodeID);
//    	if (nodeParentID != nodeID)
//    		// parent node
////    		nodeParentIDList.put(nodeID, findRoot(nodeParentIDList.get(nodeID)));
//    		return findRoot(nodeParentIDList.get(nodeID));
////    		nodeParentID = findRoot(nodeParentIDList.get(nodeID));
//    	return nodeID;
    	while (nodeID != nodeParentIDList.get(nodeID)) {
    		nodeID = nodeParentIDList.get(nodeID);
    	}
    	
    	return nodeID;
    }
    
    public void merge(int aID, int bID) {
    	int aRootID = 0;
    	int bRootID = 0;
    	
    	// init
    	if(nodeParentIDList.containsKey(aID)) {
    		aRootID = findRoot(aID);
    	} else {
    		nodeParentIDList.put(aID, aID);
    		nodeRankList.put(aID, 1);
    		aRootID = aID;
    	}
    	
    	if(nodeParentIDList.containsKey(bID)) {
    		bRootID = findRoot(bID);
    	} else {
    		nodeParentIDList.put(bID, bID);
    		nodeRankList.put(bID, 1);
    		bRootID = bID;
    	}

    	int aRootRank = nodeRankList.get(aRootID);
    	int bRootRank = nodeRankList.get(bRootID);

    	
    	if (aRootID != bRootID) {
        	// if aRoot's rank > bRoot's rank
        	if (aRootRank >= bRootRank) {
            	// attach bRoot to a's tree
        		nodeParentIDList.put(bRootID, aRootID);
        		nodeRankList.put(aRootID, aRootRank+bRootRank);
//        		bRootParentID = aRootID;
            	// if aRoot's rank < bRoot's rank
        	} else if (aRootRank < bRootRank) {
        		// attach aRoot to b's tree
        		nodeParentIDList.put(aRootID, bRootID);
        		nodeRankList.put(bRootID, aRootRank+bRootRank);
//        		aRootParentID = bRootID;
        		// if aRoot's rank == bRoot's rank
        	}
//        	} else {
//        		// choose any root nodes to be the parent
////        		bRootParentID = aRootID;
//        		nodeParentIDList.put(bRootID, aRootID);
//        		// increase the rank
//        		aRootRank++;
//        		nodeRankList.put(bRootID, aRootRank);
////        		aRootRank ++;
//        	}
    	} 
    }
    
    
    public String generateLabels() {
    	StringBuilder sb = new StringBuilder();
        for (HashMap.Entry<Integer, Integer> entry: nodeParentIDList.entrySet()) {
        	sb.append(entry.getKey() + " " + findRoot(entry.getKey()) + "\n");
        }
        return sb.toString();
    }
    
//    public static void main(String args[]){
//        UndirectedGraph graph = new UndirectedGraph();
////        graph.build(10,11);
////        graph.build(10, 12);
////        graph.build(10, 13);
////        graph.build(10, 17);
////        graph.build(11, 12);
////        graph.build(11, 14);
////        graph.build(11, 16);
////        graph.build(11, 18);
////        graph.build(1, 2);
////        graph.build(12, 13);
////        graph.build(1, 3);
////        graph.build(14, 19);
////        graph.build(15, 16);
////        graph.build(15, 17);
////        graph.build(15, 18);
////        graph.build(1, 6);
////        graph.build(16, 17);
////        graph.build(16, 19);
////        graph.build(1, 8);
////        graph.build(2, 10);
////        graph.build(2, 11);
////        graph.build(2, 12);
////        graph.build(2, 15);
////        graph.build(2, 16);
////        graph.build(2, 3);
////        graph.build(2, 4);
////        graph.build(2, 5);
////        graph.build(2, 7);
////        graph.build(3, 17);
////        graph.build(3, 19);
////        graph.build(3, 4);
////        graph.build(3, 5);
////        graph.build(3, 8);
////        graph.build(3, 9);
////        graph.build(4, 10);
////        graph.build(4, 11);
////        graph.build(4, 12);
////        graph.build(4, 13);
////        graph.build(4, 7);
////        graph.build(4, 9);
////        graph.build(5, 9);
////        graph.build(6, 15);
////        graph.build(6, 7);
////        graph.build(7, 10);
////        graph.build(7, 13);
////        graph.build(7, 16);
////        graph.build(7, 8);
////        graph.build(7, 9);
////        graph.build(8, 12);
////        graph.build(8, 18);
////        graph.build(8, 9);
////        graph.build(9, 11);
////        graph.build(9, 14);
//////        graph.build(0,1);
//////        graph.build(0,2);
//////        graph.build(0,3);
//////        graph.build(4,5);
//////        graph.build(1,4);
//
//        System.out.println("Disjoint Sets: ");
//        graph.connectEdges();
//        System.out.println(graph.generateLabels());
//    }
   
}
