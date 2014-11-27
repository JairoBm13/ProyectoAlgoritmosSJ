package problemaB;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
		int max = 0;
		int[][] piramideN=new int[nivel][];

		for(int e=0;e<nivel;e++){
			piramideN[e]=new int[e+1];
			for(int i=0;i<=e;i++)piramideN[e][i] = 0;
		}

		int[][] piramideIZ=new int[nivel][];
		for(int e=0;e<nivel;e++){
			piramideIZ[e]=new int[e+1];
			for(int i=0;i<=e;i++)piramideIZ[e][i] = 0;
		}

		int[][] piramideDER=new int[nivel][];
		for(int e=0;e<nivel;e++){
			piramideDER[e]=new int[e+1];
			for(int i=0;i<=e;i++)piramideDER[e][i] = 0;
		}

		List<String> mayores = new ArrayList<String>();
		int[] derecha = new int[nivel];
		int[] izquierda = new int[nivel];
		int sum = 0;
		int der = 0;
		int iz = 0;

		//Calcula el puntaje de retirar cada bola individualmente y lo agrega a piramideN
		for(int i =0; i<nivel;i++){

			for(int j=0; j<=i; j++){

				if(i==0){
					derecha[0] = piramide[0][0];
					piramideN[0][0] = piramide[0][0];
					if(piramide[0][0]>max) max = piramide[0][0];
				}

				else if(j==0){
					derecha[0] = piramideN[i-1][0];
					der = derecha[0];
					sum = der + piramide[i][0];
					if(sum>max) max = sum;
					izquierda[1] = der;
					piramideN[i][0] = sum;
					piramideDER[i][0] = der;


				}

				else if(i==j){
					izquierda[j] = piramideN[i-1][j-1];
					iz = izquierda[j];
					sum = iz + piramide[i][j];
					if(sum>max) max = sum;
					piramideN[i][j] = sum;
					piramideIZ[i][j] = iz;


				}

				else{
					izquierda[j] = piramideN[i-1][j-1];
					iz = izquierda[j];
					derecha[j] = derecha[j] + piramide[i-1][j];
					der = derecha[j];
					sum = der + iz + piramide[i][j];
					if(sum>max) max = sum;
					piramideN[i][j] = sum;
					piramideDER[i][j] = der;
					piramideIZ[i][j] = iz;
				}
			}		
		}

		mayoresPorDiagonal(mayores,piramideN,nivel);

		//Calcula todos los posibles puntajes maximos obtenidos de retirar pelotas

		String[] valPosMay = mayores.get(0).split("-");
		if(Integer.parseInt(valPosMay[0]) > 0){
			for(int i =1; i<nivel;i++){

				String[] valPos = mayores.get(i).split("-");
				if(Integer.parseInt(valPos[0]) > 0){
					
				}
			}
		}
		return max;
	}

	static void mayoresPorDiagonal(List<String> lista, int[][] piramideN, int nivel){
		for(int i = 0; i<nivel;i++){
			int max = piramideN[nivel-1][i];
			String pos = nivel + "-" + i;
			for(int j = i; j<nivel-1;j++){
				if(piramideN[j][i]>max){max=piramideN[j][i]; pos =  j + "-" + i;}
			}

			lista.add(max + "-" + pos);
		}

		Collections.sort(lista);
		System.out.println(lista.get(0));
	}
}
