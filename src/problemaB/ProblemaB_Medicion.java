package problemaB;

import static utils.DalgoUtils.*;
import utils.DalgoUtils.CasoPrueba;

public class ProblemaB_Medicion {
	public static void main(String[] args)throws Exception{
		CasoPrueba caso=CasoPrueba.Small;
		prepararMedicion("problemaB"+caso);
		ProblemaB_1.main();
		terminarMedicion("problemaB"+caso);
	}
}
