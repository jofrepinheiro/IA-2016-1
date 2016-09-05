

import greedy.Greedy;

public class Main {
	public static void main(String[] args) {
		Greedy greedy = new Greedy();
		String arq = "C:/Users/Jofre/Documents/GitHub/IA-2016-1/01 - Greedy/src/pr76.tsp";
		greedy.carregarTSP(arq); 
		String opt = "C:/Users/Jofre/Documents/GitHub/IA-2016-1/01 - Greedy/src/pr76.opt.tour";
		greedy.carregarOptTour(opt);
		System.out.println(greedy.avaliar(greedy.opt_tour));	
		
		greedy.acharCaminho(greedy.distancias);
		
	}
}
