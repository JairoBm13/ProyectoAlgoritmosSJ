package problemaA;

import static utils.DalgoUtils.*;

public class ProblemaA_Medicion {
	public static void main(String[] args)throws Exception{
		CasoPrueba caso=CasoPrueba.Big;
		prepararMedicion("problemaA"+caso);
		ProblemaA_1.main();
		terminarMedicion("problemaA"+caso);
	}
}
