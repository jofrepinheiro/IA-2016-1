package greedy;

import greedy.Tree.Node;

import java.util.ArrayList;
import java.util.List;

import tsp.TSP;

public class Greedy extends TSP{
	
	

	public ArrayList<Tree.Node> listaNos = new ArrayList();
	
	public void acharCaminho(double[][] distancias) {
		preencherListaNos(distancias);

		Tree arvore = new Tree(listaNos.get(0));
		
		int c = 0;
		for (Node no : listaNos) {
			System.out.println(listaNos.get(c).distancia);
			c++;
		}
//		
//		
//		
//		while(listaNos.size()>=0){
////			no = escolheNo();
////			removeNo(0);
//		}
	}
	
	
	public void preencherListaNos(double[][] distancias) {
		int c = 0;
	 	for (double[] no : distancias) {
			listaNos.add(new Node(c, no[0]));
//			System.out.println(no[0]);
			c++;
		}
	}
	
	
	//Olhar? o indice vai mudar depois
	public void removeNoLista(int index){
		listaNos.remove(index);
	}
}
