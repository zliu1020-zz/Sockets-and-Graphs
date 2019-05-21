
public class Node {
	
	// id of the node itself
	int id;
	// parent id
	int parentID;
	// current rank
	int rank;
	
	public Node(int id) {
		this.id = id;
		this.parentID = id;
		// initial rank is 0
		this.rank = 0;
	}

}
