package greedy;

import java.util.ArrayList;

import javax.swing.tree.DefaultTreeModel;

import tsp.TSP;

public class Greedy extends TSP{
	
	

	ArrayList<double[]> listaNos = new ArrayList<>();
	
	public void acharCaminho(double[][] distancias) {
		preencherListaNos(distancias);
//		DefaultTreeModel tree = new DefaultTreeModel();
		
	}
	
	
	public void preencherListaNos(double[][] distancias) {
		for (double[] no : distancias) {
			listaNos.add(no);
			System.out.println(no[0]);
		}
	}
}
