package greedy;

import greedy.Tree.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import tsp.TSP;

public class Greedy extends TSP{

	public ArrayList<Node> listaNos = new ArrayList();
	public PriorityQueue<Node> fila = new PriorityQueue<Node>(1);
	public List solucao = new ArrayList();
	
	public Greedy(String path){
		carregarTSP(path);
	}

	public void acharCaminho() {
		Tree arvore = new Tree(0);
		expandir(arvore.raiz);
		while(solucao.size() == 0){
			//Adicione todos os filhos de raiz a uma fila de prioridade
			//Selecione na fila o que tem menor distância
			//Expanda esse nó
			//Torne-o a nova raiz
			//Se chegou a uma solução, adicione o caminho do nó à solução
		}
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
		if (noExp.caminho.size() == dimensao){
			Node novoNo = new Node(0, distancias[0][noExp.id]);
			listaNos.add(novoNo);
		}else{
			for (double[] no : distancias) {
				Node novoNo = new Node(c, no[noExp.id]);
				novoNo.caminho.addAll(noExp.caminho);
				novoNo.caminho.add(noExp.id);
				novoNo.distancia = no[noExp.id];
				listaNos.add(novoNo);
				c++;
			}
		}
	}


	//Olhar? o indice vai mudar depois
	public void removeNoLista(int index){
		listaNos.remove(index);
	}
}
