package greedy;

import java.util.List;

public class Tree {
	
	public class Node{
		int id;
		List<Node> filhos;
		double distancia;
		
		Node(int id, double distancia){
			this.id = id;
			this.distancia = distancia;
			this.filhos = null;
		}		
	}
	
	private Node raiz;
}
