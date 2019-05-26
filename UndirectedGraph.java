import java.util.*; 
import java.lang.*;

class UndirectedGraph {
	
	private HashMap<Integer, Integer> nodeParentIDs;
	private HashMap<Integer, Integer> nodeSizes;

    public UndirectedGraph(){
    	nodeParentIDs = new HashMap<Integer, Integer>();
    	nodeSizes = new HashMap<Integer, Integer>();
    }
    
    public int findRoot(int nodeID) {
        // find the root by travesing up the chain
    	while (nodeID != nodeParentIDs.get(nodeID)) {
    		nodeID = nodeParentIDs.get(nodeID);
    	}
    	
    	return nodeID;
    }
    
    public int initNode(int id) {
    	int rootID = 0;
    	if(nodeParentIDs.containsKey(id)) {
    		rootID = findRoot(id);
    	} else {
    		nodeParentIDs.put(id, id);
    		nodeSizes.put(id, 1);
    		rootID = id;
    	}
    	return rootID;
    }
    
    public void merge(int aID, int bID) {
    	int aRootID = initNode(aID);
    	int bRootID = initNode(bID);
    	    	
    	int aRootSize = nodeSizes.get(aRootID);
    	int bRootSize = nodeSizes.get(bRootID);

    	if (aRootID != bRootID) {
    		// union by size
        	// if aRoot's size > bRoot's size
        	if (aRootSize >= bRootSize) {
            	// attach bRoot to a's tree
        		nodeParentIDs.put(bRootID, aRootID);
        		nodeSizes.put(aRootID, aRootSize+bRootSize);
            	// if aRoot's size < bRoot's size
        	} else if (aRootSize < bRootSize) {
        		// attach aRoot to b's tree
        		nodeParentIDs.put(aRootID, bRootID);
        		nodeSizes.put(bRootID, aRootSize+bRootSize);
        	}
    	} 
    }
    
    
    public String generateLabels() {
    	StringBuilder sb = new StringBuilder();
        for (HashMap.Entry<Integer, Integer> entry: nodeParentIDs.entrySet()) {
        	sb.append(entry.getKey() + " " + findRoot(entry.getKey()) + "\n");
        }
        return sb.toString();
    }
   
}
