package problemaC;

import static utils.DalgoUtils.*;
import utils.DalgoUtils.CasoPrueba;
public class ProblemaC_Medicion {
	
	
	public static void main(String[] args)throws Exception{
		CasoPrueba caso=CasoPrueba.Small;
		prepararMedicion("problemaC"+caso);
		ProblemaC_2.main();
		terminarMedicion("problemaC"+caso);
	}
}
