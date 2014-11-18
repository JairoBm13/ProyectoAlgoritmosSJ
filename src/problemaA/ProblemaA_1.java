package problemaA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProblemaA_1 {

	public static void main()throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		for(String h,j[];(h=br.readLine())!=null;){
			j=h.split(" ");
			int[] numeros=new int[j.length];
			for(int e=0;e<numeros.length;e++)numeros[e]=Integer.parseInt(j[e]);
			System.out.println(solve(numeros)?"Yes":"No");//Se llama el metodo solve con la información leida
		}
	}
	public static boolean solve(int[] numeros){
		//TODO su solucion, puede usar las estructuras de datos que crea convenientes
		return false;
	}
}
