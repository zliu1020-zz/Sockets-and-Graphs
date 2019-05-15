import java.util.*; 
import java.lang.*;

class UndirectedGraph {
    private HashMap<Integer, ArrayList<Integer>> adjacencyList;
    
    public UndirectedGraph(){
        this.adjacencyList = new HashMap<Integer, ArrayList<Integer>>();
    }
    
    public Boolean nodeExists(Integer node){
        return this.adjacencyList.containsKey(node);
    }
    
    public void addNode(Integer node){
        this.adjacencyList.put(node, new ArrayList<Integer>());
    }
    
    public void addEdge(Integer source, Integer destination){
        if(!nodeExists(source)){
            addNode(source);
        }
        if(!nodeExists(destination)){
            addNode(destination);
        }
        
        this.adjacencyList.get(source).add(destination);
        this.adjacencyList.get(destination).add(source);
    }
    
    public void printAdjacencyList(){
        for (Integer node: this.adjacencyList.keySet()){
            String key = node.toString();
            String value = this.adjacencyList.get(node).toString();  
            System.out.println(key + ": " + value);  
        } 
    }
    
    public void depthFirstSearch(Integer node, HashMap<Integer, Boolean> visited, ArrayList<Integer> component){
        visited.put(node, Boolean.TRUE);
        component.add(node);

        for (Integer adjacentNode : this.adjacencyList.get(node)) { 
            if(visited.get(adjacentNode).equals(Boolean.FALSE)) {
                depthFirstSearch(adjacentNode, visited, component);   
            } 
        } 
    }
    
    public ArrayList<ArrayList<Integer>> connectedComponents(){
        ArrayList<ArrayList<Integer>> components = new ArrayList<ArrayList<Integer>>();
        HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
        for (Integer node: this.adjacencyList.keySet()){
            visited.put(node, Boolean.FALSE);
        }
        
        for(Integer node: this.adjacencyList.keySet()){
            if(visited.get(node).equals(Boolean.FALSE)){
                ArrayList<Integer> component = new ArrayList<Integer>();
                components.add(component);
                depthFirstSearch(node, visited, component);
            }
        }
        
        return components;
    }
    
//    public void generateComponentLabels(){
//        ArrayList<ArrayList<Integer>> components = g.connectedComponents();
//        HashMap<Integer, Integer> result = new HashMap<Integer, Integer>();
//    }
    
	public static void main(String[] args){ 
		UndirectedGraph g = new UndirectedGraph(); 
		
		g.addEdge(1, 0); 
		g.addEdge(2, 3); 
		g.addEdge(3, 4); 
        System.out.println("**Adjacency List:**");
		g.printAdjacencyList(); 
        System.out.println("**Connected Components:**");
        ArrayList<ArrayList<Integer>> components = g.connectedComponents();
        for(ArrayList<Integer> component: components){
            System.out.println(component.toString());
        }
	} 
}