package problemaA;

import static utils.DalgoUtils.*;

public class ProblemaA_Medicion {
	public static void main(String[] args)throws Exception{
		CasoPrueba caso=CasoPrueba.Small;
		prepararMedicion("problemaA"+caso);
		ProblemaA_0.main();
		terminarMedicion("problemaA"+caso);
	}
}
