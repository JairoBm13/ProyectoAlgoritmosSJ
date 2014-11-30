package problemaB;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProblemaB_1 {
	public static void main()throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		for(String h;(h=br.                  readLine())!=null;){
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

		List<String> mayoresDiagonalDer = new ArrayList<String>();

		int[] quitadoDiagonalIz = new int[nivel]; //Altura en la cual fue removido
		for(int i =0; i<nivel;i++){
			quitadoDiagonalIz[i] = -1;
		}

		int[] quitadoDiagonalDer = new int[nivel]; //Altura en la cual fue removido
		for(int i =0; i<nivel;i++){
			quitadoDiagonalDer[i] = -1;
		}


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
				}

				else if(j==0){
					derecha[0] = piramideN[i-1][0];
					der = derecha[0];
					sum = der + piramide[i][0];
					izquierda[1] = der;
					piramideN[i][0] = sum;
					piramideDER[i][0] = der;


				}

				else if(i==j){
					izquierda[j] = piramideN[i-1][j-1];
					iz = izquierda[j];
					sum = iz + piramide[i][j];
					piramideN[i][j] = sum;
					piramideIZ[i][j] = iz;


				}

				else{
					izquierda[j] = piramideN[i-1][j-1];
					iz = izquierda[j];
					derecha[j] = derecha[j] + piramide[i-1][j];
					der = derecha[j];
					sum = der + iz + piramide[i][j];
					piramideN[i][j] = sum;
					piramideDER[i][j] = der;
					piramideIZ[i][j] = iz;
				}
			}		
		}

		//Calcula todos los posibles puntajes maximos obtenidos de retirar pelotas
		mayoresPorDiagonal(mayoresDiagonalDer, piramideN, nivel);

		for(int i = 0; i<nivel;i++){

			String[] puntajeYPos = mayoresDiagonalDer.get(i).split(",");

			int posI = Integer.parseInt(puntajeYPos[1]);
			int posJ = Integer.parseInt(puntajeYPos[2]);

			int posDiag = (nivel-1) - (posI - posJ);
			int nivelDiagIz = quitadoDiagonalIz[posDiag];

			if(nivelDiagIz <= posI){

				int valorQuitar = 0;
				int lim = nivelDiagIz;
				if(nivelDiagIz==-1) lim = 0;
				boolean choque = false;

				for(int e = posI, k = posJ; e>=lim && k>=0 && !choque; e--,k--){

					int limDiagDerActual = quitadoDiagonalDer[k];
					int valDiag = 0;

					if(limDiagDerActual  != -1){
						if(limDiagDerActual < e)
							valDiag = piramideDER[limDiagDerActual+1][k];
						else choque = true;

					}
					valorQuitar+= valDiag;
				}

				int valorSum = Integer.parseInt(puntajeYPos[0]) - valorQuitar;
				System.out.println(valorSum);
				if(valorSum>0){
					max+= valorSum;
					quitadoDiagonalDer[posJ] = posI;
					quitadoDiagonalIz[posDiag] = posJ;
				}
			}
		}

		return max;

	}


	static void mayoresPorDiagonal(List<String> lista, int[][] piramideN, int nivel){
		for(int i = 0; i<nivel;i++){
			int max = piramideN[nivel-1][i];
			String pos = (nivel-1) + "," + i;
			for(int j = i; j<nivel;j++){
				if(piramideN[j][i]>max){max=piramideN[j][i]; pos =  j + "," + i;}
			}
			lista.add(max + "," + pos);

		}
		Collections.sort(lista, new Comparator<String>(){
			public int compare(String o1, String o2) {

				String[] a1 = o1.split(",");
				String[] a2 = o2.split(",");

				if(Integer.parseInt(a1[0]) < Integer.parseInt(a2[0])) return 1;
				else if(Integer.parseInt(a1[0]) > Integer.parseInt(a2[0])) return -1;
				else return 0;
			}
		});
	}


	//	static int solve(int nivel, int[][] piramide){
	//
	//		String[] maximosNivel = new String[nivel];
	//		int max = 0;
	//		for (int i= (nivel-1); i>=0;i--){
	//
	//			int puntajeAcumulado = 0;
	//			int maxPuntajeNivel = 0;
	//			int maxPosNivel = 0;
	//			
	//			for(int j = 0; j<nivel;j++){
	//
	//
	//				puntajeAcumulado += piramide[j][i];
	//				if(puntajeAcumulado > maxPuntajeNivel){
	//					maxPuntajeNivel = puntajeAcumulado;
	//					maxPosNivel = j;
	//				}
	//			}
	//
	//			if(maxPuntajeNivel<0){
	//				maximosNivel[i] = maxPuntajeNivel + "," + (-1);
	//			}
	//			else
	//				maximosNivel[i] = maxPuntajeNivel + "," + maxPosNivel;
	//		}
	//
	//
	//
	//		return max;
	//	}

}
