import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

public class BasicoWeka {
	
	public Instances dados;
	
	public BasicoWeka(String file){
		try{
			this.carregarDados(file); 
//			this.percorrerDados();
		} catch(IOException ex) {ex.printStackTrace();}
	}
	
	/**
	 * M�todo que carrega um arquivo texto no formato dos dados do Weka
	 * @param arquivoArff Arquivo texto no formato ARFF, padr�o do Weka.
	 * @throws IOException Exce��o lan�ada pela leitura do arquivo.
	 */
	public void carregarDados(String arquivoArff) throws IOException{
		/*Estrutura do Java que carrega um arquivo texto em memoria
		No Java existem muitas formas para se carregar um arquivo, 
		cada classe possibilita fazer alguma coisa diferente com ele.
		Como a gente nao precisa fazer nada, so repassar para classe do Weka
		eu utilizei esta estrutura. Este metodo lan�a uma exce��o IOException,
		 casa haja algum problema com o arquivo que eu passei (ex. ele n�o existe...).
		Eu estou usando uma condi��o que repassa essa exce��o para o m�todo 
		que vai chamar este m�todo carregarDados.
		*/
		Reader reader = new FileReader(arquivoArff);
		//Carregando o arquivo texto na estrutuda do Weka
		dados = new Instances(reader);
		//Aponta qual � o atributo classe da base, nas bases que n�s estamos
		//trabalhando sempre � o �ltimo
		dados.setClassIndex(dados.numAttributes()-1);
	}
	
	/**
	 * M�tod que percorre todos os dados pertencentes � Instances dados. 
	 * Imprimindo as informa��es da base.
	 *
	 */
	public void percorrerDados(){
	
		if(dados!=null){
		/*Cada exemplo contido nos dados � identificado no Weka atrav�s da 
		 * classe Instance. Assim, o objeto dados, do tipo Instances, � uma cole�ao de 
		 * Instance. Voce vai ter metodos que possibilitam acessar todos os exemplos
		 * presentes na base.
		 * */
			//Percorre todos os exemples presentes na base
			for(int i=0; i<dados.numInstances(); i++){
				//M�todo para obter a instance de n�mero 1.
				//Voce pode pegar a primeira e a ultima instance tb.
				//Al�m de poder deletar entre outras coisas.
				Instance exemplo = dados.instance(i);
				
				/*Uma Intance � formada por v�rios atributos, que s�o os atributos
				 * da base. Voce pode percorrer todos os atributos Instace, ou pode
				 * "setar" (set) ou pegar (get) um atributo especifico.
				 * */
				
				//� poss�vel transforma todos os atributos em um array de double
				
				double[] arrayAtributos = exemplo.toDoubleArray();
				
				System.out.println("Valores para o exemplo " + i);
				System.out.print("Array de atributos: ");
				for (int j = 0; j < arrayAtributos.length; j++) {
					System.out.print(arrayAtributos[j] + " ");
				}
				
				System.out.println();
				//Percorrendo todos os atributos para se obter informacoes sobre eles
				for(int j = 0; j<exemplo.numAttributes(); j++){
					Attribute att = exemplo.attribute(j);
					double valor = exemplo.value(att);
					System.out.println("Valor do atributo " + att.name() + ":"
							+ valor + " - " + att.value((int) valor ));
					
				}
				
				System.out.println();
				//Mudando o valor do atributo 0, para um valor poss�vel do atributos
				//Obtendo as informacoes do atributo 0;
				Attribute att = exemplo.attribute(0);
				//Obtendo o valor do atributo 0.
				double valorDoAtributo0 = exemplo.value(att);
			
				System.out.println("Valor antigo, em double: " + valorDoAtributo0);
				System.out.println("Valor antigo, em nome: " + att.value((int)valorDoAtributo0));
				
				int novoValor = 1;
				exemplo.setValue(att, novoValor);
				
				valorDoAtributo0 = exemplo.value(att);
				
				System.out.println("Valor novo, em nome: " + att.value((int)valorDoAtributo0));

				System.out.println();
				System.out.println();
				
			}
		}
		
	}

//	public static void main(String[] args) {
//		String nomebase = "hepatitis.arff";
//		String caminhoBase = "C:\\bases\\hepatitis\\";
//		String arquivoARFF = caminhoBase + nomebase;
//		BasicoWeka basico = new BasicoWeka();
//		//Aquela exce��o que eu repassei, agora eu tenho que tratar ela.
//		//Caso haja um problema, eu mando imprimir a StackTrace, que vai me mostrar
//		//aonde ocorreu o problema.
//		try{
//			basico.carregarDados(arquivoARFF); 
//			basico.percorrerDados();
//		} catch(IOException ex) {ex.printStackTrace();}
//		
//	}

}
