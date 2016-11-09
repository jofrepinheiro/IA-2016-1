import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import weka.core.Instances;

public class Classificador {

	Instances baseDados;
	Instances baseTeste;

	int matrizConfusao[][];

	public Classificador(BasicoWeka dados, BasicoWeka teste) {
		baseDados = dados.dados;
		baseTeste = teste.dados;

		knn(1);
		knn(3);
		knn(5);
		knn(13);
		knn(21);
	}

	public void knn(int k) {
		/*
		 * Para cada elemento da base de testes, faça: Para cada elemento da
		 * base de dados, faça: Meça a distância entre o elemento de teste e
		 * elemento de dados, insira os elementos com K menores distâncias numa
		 * estrutura ordenadamente
		 * 
		 * pegue a classe que é maioria nesta estrutura e defina como classe do
		 * elemento testado.
		 */

		System.out.println("\n----------------------");
		System.out.println("KNN para k = " + k);
		System.out.println("----------------------");
		
		int numClasses = baseDados.numClasses();
		matrizConfusao = new int[numClasses][numClasses];

		for (int i = 0; i < baseTeste.numInstances(); i++) {
			double[] elementoTeste = baseTeste.instance(i).toDoubleArray();
			List<Par> resultado = new ArrayList<>();

			for (int j = 0; j < baseDados.numInstances(); j++) {
				double[] elementoDado = baseDados.instance(j).toDoubleArray();

				resultado.add(new Par(j, distEuclidiana(elementoTeste,
						elementoDado)));

			}

			Collections.sort(resultado, new Comparator<Par>() {
				@Override
				public int compare(Par par0, Par par1) {
					return par0.getDistancia().compareTo(par1.getDistancia());
				}
			});

			double classeEsperada = baseTeste.instance(i).classValue();
			double classeObtida = getMaioria(resultado.subList(0, k));

			// System.out.println("---------------------");
			// System.out.println("Elemento "+i);
			// System.out.println(classeEsperada+"\t"+classeObtida);
			
			matrizConfusao[(int) classeEsperada][(int) classeObtida]++;
		}
		printMatriz();
		printMedidas();
	}

	public double getMaioria(List<Par> resultado) {
		List<Double> list = new ArrayList<>(baseDados.numClasses());

		for (int i = 0; i < baseDados.numClasses(); i++) {
			list.add(0.0);
		}
		for (int i = 0; i < resultado.size(); i++) {
			int indice = resultado.get(i).getIndice();
			int classe = (int) baseDados.instance(indice).classValue();

			list.set(classe, list.get(classe) + 1);
		}
		return (double) list.indexOf(Collections.max(list));
	}

	public void printMatriz() {
		System.out.println("Matriz de confusão");
		System.out.println("----------------------");
		for (int i = 0; i < matrizConfusao.length; i++) {
			for (int j = 0; j < matrizConfusao[i].length; j++) {
				System.out.print(matrizConfusao[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	public double distEuclidiana(double origem[], double destino[]) {
		double soma = 0;
		double fator = 0;
		for (int i = 0; i < origem.length - 1; i++) {
			fator = destino[i] - origem[i];
			soma += fator * fator;
		}
		return Math.sqrt(soma);
	}

	public void printMedidas() {
		double[][] medidas = new double[baseDados.numClasses()][3];
		double[] tp = new double[baseDados.numClasses()];
		double[] tn = new double[baseDados.numClasses()];
		double[] fp = new double[baseDados.numClasses()];
		double[] fn = new double[baseDados.numClasses()];


		for (int i = 0; i < tp.length; i++) {
			tp[i] = matrizConfusao[i][i];
			for (int j = 0; j < tn.length; j++) {
				if (i != j) {
					tn[i] += matrizConfusao[j][j];

					fn[i] += matrizConfusao[i][j];
					fp[i] += matrizConfusao[j][i];
				}
			}
		}


		for (int i = 0; i < baseDados.numClasses(); i++) {
			// Accuracy = TP + TN / N
			medidas[i][0] = (tp[i] + tn[i]) / (tn[i] + tp[i] + fp[i] + fn[i]);

			// Precisao = TP / TP + FP
			medidas[i][1] = tp[i] / (tp[i] + fp[i]);

			// Precision = TP / TP + FN
			medidas[i][2] = tp[i] / (tp[i] + fn[i]);
		}

		
		// Print matriz de medidas
		System.out.println("----------------------");
		System.out.println("Matriz de Medidas");
		System.out.println("----------------------");
		for (int i = 0; i < medidas.length; i++) {
			for (int j = 0; j < medidas[0].length; j++) {
				System.out.printf("%.2f",medidas[i][j]*100);
				System.out.print("\t");
			}
			System.out.println();
		}
	}
}
