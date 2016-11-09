/*
 * Trabalho 2 - KNN
 * 
 * Autor: Jofre Pinheiro
 * 
 * */
public class main {
	public static void main (String[] args){
		BasicoWeka irisDados = new BasicoWeka("C:\\Users\\Jofre\\Dropbox\\Workspaces\\Java\\KNN\\src\\iris\\iris_data.arff");
		BasicoWeka irisTeste = new BasicoWeka("C:\\Users\\Jofre\\Dropbox\\Workspaces\\Java\\KNN\\src\\iris\\iris_test.arff");
		
		System.out.println("----------------------");
		System.out.println("Base Iris");
		System.out.println("----------------------");
		Classificador c = new Classificador(irisDados, irisTeste);
		
		
		System.out.println("\n\n");
		
		
		BasicoWeka numericaDados = new BasicoWeka ("C:\\Users\\Jofre\\Dropbox\\Workspaces\\Java\\KNN\\src\\cm1_numerica\\cm1_numerica_data.arff");
		BasicoWeka numericaTeste = new BasicoWeka ("C:\\Users\\Jofre\\Dropbox\\Workspaces\\Java\\KNN\\src\\cm1_numerica\\cm1_numerica_test.arff");
		
		System.out.println("----------------------");
		System.out.println("Base CM1");
		System.out.println("----------------------");
		Classificador c1 = new Classificador(numericaDados, numericaTeste);
		
	}
}
