package greedy;

import java.util.ArrayList;
import java.util.List;

public class Tree {
	
	public Node raiz;
	
	static class Node{
		int id;
		List<Node> filhos = new ArrayList<Node>();
		double distancia;
		List caminho = new ArrayList();

		Node(int id, double distancia){
			this.id = id;
			this.distancia = distancia;
		}		
	}
	
	public Tree(int id){
		Node no = new Node(id, 0.0);
		raiz = no;
	}
	
	public List<Node> getFilhos(Node no){
		return no.filhos;
	}
	
	public void printTree(Node noPai){
		for(Node noFilho : noPai.filhos){
			System.out.println("Nó número: " + noFilho.id + " - " + noFilho.distancia);
			printTree(noFilho);
			
		}
	}
	
//	public Node procuraNo(int id){
//		for(Node n : raiz.filhos){
//			if (n.id == id) return n;
//		}
//	}
	
	
	
}
