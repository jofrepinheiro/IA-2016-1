package greedy;

import greedy.Tree.Node;

import java.util.ArrayList;
import java.util.PriorityQueue;

import tsp.TSP;

public class Greedy extends TSP{

	protected ArrayList<Node> listaNos = new ArrayList();
	public PriorityQueue<Node> fila = new PriorityQueue<Node>(1);

	public Greedy(String path){
		carregarTSP(path);
	}

	public void acharCaminho() {
		Tree arvore = new Tree(0);
		expandir(arvore.raiz);
		expandir(arvore.raiz.filhos.get(2));
		expandir(arvore.raiz.filhos.get(2).filhos.get(2));
	}


	private void expandir(Node noExp) {
		preencherListaNos(noExp);
		System.out.println("Expansao de " + noExp.id);
		for (Node no : listaNos){
			if(!noExp.caminho.contains(no.id) && no.id != noExp.id){
					noExp.filhos.add(no);				
			}
		}
		for(Node no : noExp.filhos){
			System.out.println("ID: " + no.id);
		}
	}


	public void preencherListaNos(Node noExp) {
		listaNos.clear();
		int c = 0;
		for (double[] no : distancias) {
			Node novoNo = new Node(c, no[noExp.id]);
			novoNo.caminho.addAll(noExp.caminho);
			novoNo.caminho.add(noExp.id);
			listaNos.add(novoNo);
			c++;
		}
	}


	//Olhar? o indice vai mudar depois
	public void removeNoLista(int index){
		listaNos.remove(index);
	}
}
