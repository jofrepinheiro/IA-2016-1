import java.util.Comparator;

public class Par {
	private final Integer indice;
	private final Double distancia;

	public Par(Integer indice, Double distancia) {
		this.indice = indice;
		this.distancia = distancia;
	}

	public Integer getIndice() {
		return indice;
	}

	public Double getDistancia() {
		return distancia;
	}

//	@Override
//	public int compare(Object arg0, Object arg1) {
//		Par par0 = (Par) arg0;
//		Par par1 = (Par) arg1;
//		
//		
//		return par0.right.compareTo(par1.right);
//	}

}
