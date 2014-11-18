package problemaC;

import static utils.DalgoUtils.*;
import utils.DalgoUtils.CasoPrueba;

public class ProblemaC_Comparador {
	public static void main(String[] args)throws Exception{
		CasoPrueba caso=CasoPrueba.Small;
		prepararMedicion("problemaC"+caso);
		ProblemaC_0.main();
		hacerComparacion("problemaC"+caso);
	}
}
