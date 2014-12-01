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
			int[][] piramide=new int[N][];
			for(int e=0;e<N;e++){
				piramide[e]=new int[e+1];
				String[] j=br.readLine().split(" ");
				for(int i=0;i<=e;i++)piramide[e][i]=Integer.parseInt(j[i]);

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

		int[][] piramideDER=new int[nivel][];
		for(int e=0;e<nivel;e++){
			piramideDER[e]=new int[e+1];
			for(int i=0;i<=e;i++)piramideDER[e][i] = 0;
		}

		List<String> mayoresDiagonalDer = new ArrayList<String>();
		
		List<String> mayoresDiagonalIz = new ArrayList<String>();

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

				}

				else{
					izquierda[j] = piramideN[i-1][j-1];
					iz = izquierda[j];
					derecha[j] = derecha[j] + piramide[i-1][j];
					der = derecha[j];
					sum = der + iz + piramide[i][j];
					piramideN[i][j] = sum;
					piramideDER[i][j] = der;

				}
			}		
		}

		//Calcula todos los posibles puntajes maximos obtenidos de retirar pelotas
		mayoresPorDiagonal(mayoresDiagonalDer, mayoresDiagonalIz, piramideN, nivel);

		//Recorrido diagonales derecha
		for(int i = 0; i<mayoresDiagonalDer.size();i++){

			String[] puntajeYPos = mayoresDiagonalDer.get(i).split(",");

			int posI = Integer.parseInt(puntajeYPos[1]);
			int posJ = Integer.parseInt(puntajeYPos[2]);


			int posDiag = (nivel-1) - (posI - posJ);
			int nivelDiagIz = quitadoDiagonalIz[posDiag];
			if(nivelDiagIz <= posJ){

				int valorQuitar = 0;
				int lim = nivelDiagIz;
				if(nivelDiagIz==-1) lim = 0;

				boolean choque = false;

				for(int e = posI, k = posJ; e>=0 && k>=lim && !choque; e--,k--){

					int limDiagDerActual = quitadoDiagonalDer[k];
					int valDiag = 0;

					if(limDiagDerActual  != -1){
						if(limDiagDerActual < e)
							valDiag = piramideDER[limDiagDerActual+1][k];
						else if(limDiagDerActual >= e){choque = true; valDiag += piramideN[e][k];}
					}
					valorQuitar+= valDiag;
				}


				int valorSum = Integer.parseInt(puntajeYPos[0]) - valorQuitar;
				if(valorSum>=0){
					max+= valorSum;

					for(int e = posI, k = posJ; e>=0 && k>=lim ; e--,k--){
						int quitadoDerActual = quitadoDiagonalDer[k];
						if(e > quitadoDerActual)quitadoDiagonalDer[k] = e;
					}

					for(int e = posDiag; e<nivel ; e++){
						int quitadoIzActual = quitadoDiagonalIz[e];
						if(posJ > quitadoIzActual)quitadoDiagonalIz[e] = posJ;
					}

				}
			}
		}

		//Recorrido diagonales Izquierda y Derecha
		for(int i = 0; i<nivel;i++){

			String[] puntajeYPos = mayoresDiagonalIz.get(i).split(",");

			int posI = Integer.parseInt(puntajeYPos[1]);
			int posJ = Integer.parseInt(puntajeYPos[2]);

			int posDiag = (nivel-1) - (posI - posJ);
			int nivelDiag = quitadoDiagonalIz[posDiag];

			if(nivelDiag <= posJ){

				int valorQuitar = 0;
				int lim = nivelDiag;
				if(nivelDiag==-1) lim = 0;
				boolean choque = false;

				for(int e = posI, k = posJ; e>=0 && k>=lim && !choque; e--,k--){

					int limDiagDerActual = quitadoDiagonalDer[k];
					int valDiag = 0;

					if(limDiagDerActual  != -1){
						if(limDiagDerActual < e)
							valDiag = piramideDER[limDiagDerActual+1][k];

						else if(limDiagDerActual >= e){
							choque = true;
							valDiag += piramideN[e][k];
						}
					}
					valorQuitar+= valDiag;
				}


				int valorSum = Integer.parseInt(puntajeYPos[0]) - valorQuitar;
				if(valorSum>0){
					max+= valorSum;

					for(int e = posI, k = posJ; e>=0 && k>=lim ; e--,k--){
						int quitadoDerActual = quitadoDiagonalDer[k];
						if(e > quitadoDerActual)quitadoDiagonalDer[k] = e;
					}

					for(int e = posDiag; e<nivel ; e++){
						int quitadoIzActual = quitadoDiagonalIz[e];
						if(posJ > quitadoIzActual)quitadoDiagonalIz[e] = posJ;
					}

				}
			}
			
			puntajeYPos = mayoresDiagonalIz.get(i).split(",");

			posI = Integer.parseInt(puntajeYPos[1]);
			posJ = Integer.parseInt(puntajeYPos[2]);

			posDiag = (nivel-1) - (posI - posJ);
			nivelDiag = quitadoDiagonalIz[posDiag];

			if(nivelDiag <= posJ){

				int valorQuitar = 0;
				int lim = nivelDiag;
				if(nivelDiag==-1) lim = 0;
				boolean choque = false;

				for(int e = posI, k = posJ; e>=0 && k>=lim && !choque; e--,k--){

					int limDiagDerActual = quitadoDiagonalDer[k];
					int valDiag = 0;

					if(limDiagDerActual  != -1){
						if(limDiagDerActual < e)
							valDiag = piramideDER[limDiagDerActual+1][k];

						else if(limDiagDerActual >= e){
							choque = true;
							valDiag += piramideN[e][k];
						}
					}
					valorQuitar+= valDiag;
				}


				int valorSum = Integer.parseInt(puntajeYPos[0]) - valorQuitar;
				if(valorSum>0){
					max+= valorSum;

					for(int e = posI, k = posJ; e>=0 && k>=lim ; e--,k--){
						int quitadoDerActual = quitadoDiagonalDer[k];
						if(e > quitadoDerActual)quitadoDiagonalDer[k] = e;
					}

					for(int e = posDiag; e<nivel ; e++){
						int quitadoIzActual = quitadoDiagonalIz[e];
						if(posJ > quitadoIzActual)quitadoDiagonalIz[e] = posJ;
					}

				}
			}
		}
		return max;

	}


	static void mayoresPorDiagonal(List<String> listaDer, List<String> listaIz, int[][] piramideN, int nivel){
		Comparator<String> comp = new Comparator<String>(){

			public int compare(String o1, String o2) {

				String[] a1 = o1.split(",");
				String[] a2 = o2.split(",");


				if(Integer.parseInt(a1[0]) > Integer.parseInt(a2[0])) return -1;
				else if(Integer.parseInt(a1[0]) < Integer.parseInt(a2[0])) return 1;
				else if(Integer.parseInt(a1[1]) > Integer.parseInt(a2[1])) return -1;
				else if(Integer.parseInt(a1[1]) < Integer.parseInt(a2[1])) return 1;


				else return 0;
			}
		};

		for(int i = 0; i<nivel;i++){

			int max = piramideN[nivel-1][i];
			String pos = (nivel-1) + "," + i;

			for(int j = i; j<nivel;j++){

				if(piramideN[j][i]>max){
					max=piramideN[j][i]; 
					pos =  j + "," + i;
				}
			}
			listaDer.add(max + "," + pos);
		}

		for(int i = (nivel-1); i>=0;i--){

			int max = piramideN[i][0];
			String pos = i + "," + 0;

			for(int j = 0, e = i; j < (nivel-i);j++, e++){
				if(piramideN[e][j]>=max){
					max=piramideN[e][j]; 
					pos = e + "," + j;
				}
			}
			listaIz.add(max + "," + pos);
		}

		Collections.sort(listaDer, comp);
		Collections.sort(listaIz, comp);
	}




}
