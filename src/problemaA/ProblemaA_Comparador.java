package problemaA;
import static utils.DalgoUtils.*;
import utils.DalgoUtils.CasoPrueba;
public class ProblemaA_Comparador {
	public static void main(String[] args)throws Exception{
		CasoPrueba caso=CasoPrueba.Small;
		prepararMedicion("problemaA"+caso);
		ProblemaA_0.main();
		hacerComparacion("problemaA"+caso);
	}
	
}
