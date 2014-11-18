package problemaB;

import static utils.DalgoUtils.hacerComparacion;
import static utils.DalgoUtils.prepararMedicion;
import utils.DalgoUtils.CasoPrueba;

public class ProblemaB_Comparador {
	public static void main(String[] args)throws Exception{
		CasoPrueba caso=CasoPrueba.Small;
		prepararMedicion("problemaB"+caso);
		ProblemaB_0.main();
		hacerComparacion("problemaB"+caso);
	}
}
