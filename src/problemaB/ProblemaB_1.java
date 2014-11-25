package problemaB;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class ProblemaB_1{
	public static void main()throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		for(String h;(h=br.readLine())!=null;){
			final int N=Integer.parseInt(h);//Lee el número de niveles
			//TODO inicializar la estructura seleccionada para almacenar el los niveles de la piramide
			int[][] piramide=new int[N][];
			for(int e=0;e<N;e++){
				//TODO inicializar la estructura seleccionada para almacenar el nivel e
				piramide[e]=new int[e+1];
				String[] j=br.readLine().split(" ");
				for(int i=0;i<=e;i++)piramide[e][i]=Integer.parseInt(j[i]);
				//TODO agregar el valor a la estructura

			}
			System.out.println(solve(N, piramide));
		}
	}
	static int solve(int nivel, int[][] piramide){
		int max = piramide[0][0];
		int[][] piramideN=piramide;
		int[] derecha = new int[nivel];
		int[] izquierda = new int[nivel];
		int sum = 0;
		int der = 0;
		int iz = 0;
		
		for(int i =0; i<nivel;i++){
			
			for(int j=0; j<=i; j++){
				
				if(i==0){
					derecha[0] = max;
				}
				
				else if(j==0){
					derecha[0] = piramideN[i-1][0];
					sum = der + piramide[i][0];
					if(sum>max) max = sum;
					izquierda[1] = der;
					piramideN[i][0] = sum;

				}
				
				else if(i==j){
					izquierda[j] = piramideN[i-1][j-1];
					iz = izquierda[j];
					sum = iz + piramide[i][j];
					if(sum>max) max = sum;
					piramideN[i][j] = sum;
					derecha[j] = 0;
				}
				
				else{
					izquierda[j] = piramideN[i-1][j-1];
					iz = izquierda[j];
					derecha[j] = derecha[j] + piramide[i-1][j];
					der = derecha[j];
					sum = der + iz + piramide[i][j];
					if(sum>max) max = sum;
					piramideN[i][j] = sum;
				}
				
			}
		}
		
		return max;
	}
}
