package greedy;

import java.util.List;

public class Tree {
	
	public Node raiz;
	
	static class Node{
		int id;
		List<Node> filhos;
		double distancia;

		Node(int id, double distancia){
			this.id = id;
			this.distancia = distancia;
			this.filhos = null;
		}		
	}
	
	public Tree(Node no){
		raiz = no;
	}
	
	public List<Node> getFilhos(Node no){
		return no.filhos;
	}
	
//	public Node procuraNo(int id){
//		for(Node n : raiz.filhos){
//			if (n.id == id) return n;
//		}
//	}
	
	
	
}
