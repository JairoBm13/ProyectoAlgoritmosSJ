package problemaB;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class ProblemaB_1{
	public static void main()throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		for(String h;(h=br.readLine())!=null;){
			final int N=Integer.parseInt(h);//Lee el número de niveles
			//TODO inicializar la estructura seleccionada para almacenar el los niveles de la piramide
			for(int e=0;e<N;e++){
				//TODO inicializar la estructura seleccionada para almacenar el nivel e
				String[] j=br.readLine().split(" ");
				for(int i=0;i<=e;i++){
					int valor=Integer.parseInt(j[i]);
					//TODO agregar el valor a la estructura
				}
			}
			System.out.println(solve(/*TODO Agregar los parametros necesarios de su solucion*/));
		}
	}
	static int solve(/*TODO Agregar los parametros necesarios de su solucion*/){
		//TODO implemente su solucion
		return 0;
	}
}
