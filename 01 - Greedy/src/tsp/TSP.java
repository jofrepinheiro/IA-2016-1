package tsp;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import jdk.nashorn.internal.runtime.ListAdapter;
import sun.reflect.generics.tree.Tree;

/**
 * Fique atendo por que a matriz de distância está instanciada de 0 até dimensão-1
 * Porém, o TSP trabalho com a primeira cidade como sendo 1.
 * Assim, as distâncias da cidade 1 estão armazenadas na linha 0 da matriz e assim em diante.
 * Um solução do TSP é um array de inteiros.
 * @author andre
 *
 */


public class TSP {
	
	//Matriz com as distâncias entre as cidades
	public double[][] distancias;
	//Coordenados das cidades
	public double[][] coordenadas;
	//Número de cidades
	public int dimensao;
	//Nome do problema
	public String nome = null;
	//Tipo de representação das distâncias
	public String edgeType = null;
	//Formato da matriz de distâncias
	public String formato = null;
	//Tour ótimo do problema
	public int[] opt_tour = null;
	
	
	/**
	 * Método que carregar o arquivo da TSPLib na matriz de distâncias
	 * @param arquivo
	 */
	public void carregarTSP(String arquivo){
		try{
		BufferedReader buff = new BufferedReader(new FileReader(arquivo));
		while(buff.ready()){
			String[] linha = buff.readLine().trim().split(":");
			if(linha.length == 1){
				//Distância explícitas
				if(linha[0].equals("NODE_COORD_SECTION")){
					carregarCoordenadas(buff);
					break;
				}
				//Distância em coordenadas
				if(linha[0].equals("EDGE_WEIGHT_SECTION")){
					carregarMatrizEdge(buff);
					break;
				}
			} else{
				String tag = linha[0].trim();
				String valor = linha[1].trim();
				if(tag.equals("DIMENSION"))
					dimensao = new Integer(valor);
				if(linha[0].equals("NAME"))
					nome = valor;
				if(tag.equals("EDGE_WEIGHT_TYPE"))
					edgeType = valor;
				if(linha[0].equals("EDGE_WEIGHT_FORMAT"))
					formato = valor;
			}
			
		}
		
		buff.close();
		}catch(IOException ex){ex.printStackTrace();}
	}
	
	/**
	 * Carrega a matriz através das distâncias explícitas.
	 * Carregar somente o formato UPPER_ROW
	 * @param buff
	 * @throws IOException
	 */
	public void carregarMatrizEdge(BufferedReader buff) throws IOException{
		int i = 0;
		distancias = new double[dimensao][dimensao];
		while(buff.ready()){
			String[] linha = buff.readLine().trim().split(" ");
			int j = i+1;
			int k = 0;
			while(j<dimensao){
				String valor = linha[k++].trim();
				if(!valor.isEmpty()){
					distancias[i][j] = new Integer(valor);
					distancias[j][i] = distancias[i][j];
					j++;
				}
			}

			i++;
		}
		
	}
	
	/**
	 * Carrega a matriz de distâncias a partir de coordenadas.
	 * Somente está implementado as coordenadas GEO e EUC_2D
	 * @param buff
	 * @throws IOException
	 */
	public void carregarCoordenadas(BufferedReader buff) throws IOException{
		distancias = new double[dimensao][dimensao];
		coordenadas = new double[dimensao][2];
		int cidade = 0;
		while(buff.ready()){
			String[] linha = buff.readLine().trim().split(" ");
			int cont = 0;
			
			for(int i = 0; i<linha.length ; i++){
				String linha_i = linha[i]; 
				if(!linha_i.isEmpty()){
					if(cont !=0){
						coordenadas[cidade][cont-1] = new Double(linha[i].trim());
					}
					cont++;
				}
			}
			cidade++;
		}
		if(edgeType.equals("GEO"))
			preencherMatrizCoordenadasGeo();
		if(edgeType.equals("EUC_2D"))
			preencherMatrizCoordenadasEUC2D();
	}
	
	public void preencherMatrizCoordenadasEUC2D(){
		if(distancias == null){
			System.err.println("Distâncias não carregadas");
			System.exit(0);
		} else{
			for(int i=0; i<dimensao-1; i++){
				for(int j = i+1; j< dimensao; j++){
					double dist_ij = distanciaEuclidiana(coordenadas[i],coordenadas[j]);
					distancias[i][j] = Math.round(dist_ij);
					distancias[j][i] = Math.round(dist_ij);
				}
			}
		}
	}
	
	public double distanciaEuclidiana(double[] x, double[] y){
		double soma = 0;
		for (int i = 0; i < x.length; i++) {
			soma+= Math.pow(x[i] - y[i],2);
		}
		return Math.sqrt(soma);
	}
	
	public void preencherMatrizCoordenadasGeo(){
		if(distancias == null){
			System.err.println("Distâncias não carregadas");
			System.exit(0);
		} else{
			double distLatLong[][] = new double[dimensao][2];
			
			for(int i=0; i<dimensao; i++){
				double xi = coordenadas[i][0];
				double yi = coordenadas[i][1];		
				int deg = (int)Math.round(xi);
				double min = xi - deg;
				distLatLong[i][0] = Math.PI * (deg + 5 *min /3.0) /180;
				deg = (int)Math.round(yi);
				min = yi - deg;
				distLatLong[i][1] = Math.PI * (deg + 5 *min /3.0) /180;
			}
			
			double RRR = 6378.388;
			for(int i=0; i<dimensao-1; i++){
				for(int j = i+1; j< dimensao; j++){
					double q1 = Math.cos(distLatLong[i][1] - distLatLong[j][1]);
					double q2 = Math.cos(distLatLong[i][0] - distLatLong[j][0]);
					double q3 = Math.cos(distLatLong[i][0] + distLatLong[j][0]);
					double dist_ij = RRR* Math.acos(0.5*((1.0+q1)*q2 - (1.0-q1)*q3)) + 1.0;
					distancias[i][j] = dist_ij;
					distancias[j][i] = dist_ij;
				}
			}
		}
	}
	
	public void imprimirMatriz(){
		for(int i = 0; i< dimensao; i++){
			for(int j = 0; j< dimensao; j++)
				System.out.print(distancias[i][j] + " ");
			System.out.println();
		}
		
	}
	
	/**
	 * Método que recebe uma solução como parâmetro e retorna o valor do tour do TSP
	 * @param solucao Array de inteiros que indica o a sequencia de cidades do TSP
	 * @return -1 se a soulção tem dimensão diferente do TSP carregado ou o valor do tour do TSP
	 */
	public double avaliar(int[] solucao){
		if(solucao.length!=dimensao)
			return -1;
		else{
			double tour = 0;
			int cidade_ant = solucao[0];
			int cidade_prox = -1;
			for (int i = 1; i < solucao.length; i++) {
				cidade_prox = solucao[i];
				tour += distancias[cidade_ant-1][cidade_prox-1];
				cidade_ant = cidade_prox;
			}
			cidade_prox = solucao[0];
			tour+=distancias[cidade_ant-1][cidade_prox-1];
			return tour;
		}
	}
	
	/**
	 * Método para carregar o tour ótimo do problema
	 * @param arquivo
	 */
	public void carregarOptTour(String arquivo){
		try{
		if(distancias ==null){
			System.err.println("TSP não carregado");
			System.exit(0);
		}
		BufferedReader buff = new BufferedReader(new FileReader(arquivo));
		while(buff.ready()){
			String[] linha = buff.readLine().trim().split(":");
			if(linha.length == 1){
				preencherArrayOptTour(buff);
				break;
			}
		}
		buff.close();
		} catch(IOException ex){ex.printStackTrace();}
	}
	
	public void preencherArrayOptTour(BufferedReader buff) throws IOException{
		opt_tour = new int[dimensao];
		int i = 0;
		while(buff.ready()){
			String linha = buff.readLine().trim();
			int num = new Integer(linha);
			if(num == -1)
				break;
			else
				opt_tour[i++] = num;
		}
	}
}
