package problemaB;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class ProblemaB_0{
	public static void main()throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		for(String h;(h=br.readLine())!=null;){
			final int N=Integer.parseInt(h);//Lee el n�mero de niveles
			int[][] piramide=new int[N][];
			for(int e=0;e<N;e++){
				piramide[e]=new int[e+1];//Inicializa el nivel e
				String[] j=br.readLine().split(" ");
				for(int i=0;i<=e;i++)piramide[e][i]=Integer.parseInt(j[i]);//Lee los valores de las esferas del nivel e
			}
			System.out.println(solve(piramide));
		}
	}
	static int solve(int[][] piramide){
		int max=0;
		int[] currentNumber=new int[piramide.length];//N�mero que representa cuantas esferas ha quitado de la piramide. currentNumber[e] representa la cantidad de esferas que ha quitado del nivel e
													 //, contando los niveles de la esfera de m�s a la derecha hacia el lado superior izquierdo 
		cicloPrincipal:do{
			for(int e=1;e<piramide.length;e++){//Ciclo para verificar que se cumple la restricci�n que indica que no se pueden quitar esferas si no se han previamente quitado las de arriba
				if(currentNumber[e-1]<currentNumber[e])continue cicloPrincipal;//Continua con la siguiente iteraci�n si esta no cumple la restricciones
			}
			int puntajeActual=0;//Entero para llevar el puntaje de la soluci�n actual
			for(int e=0;e<piramide.length;e++){//Itera sobre los niveles de la piramide
				for(int i=0;i<currentNumber[e];i++){
					puntajeActual+=piramide[i+e][e];//Aumenta el puntaje seg�n el valor de las esferas removidas
				}
			}
			if(puntajeActual>max)max=puntajeActual;//Calcula el puntaje m�ximo
		}while(avanzar(currentNumber));//Avanza a la siguiente posible soluci�n
		return max;
	}
	static boolean avanzar(int[] indices){
		for(int e=indices.length-1;;){//Calcula la siguiente permitaci�n
			if(indices[e]==indices.length-e)indices[e--]=0;//Este d�gito se desborda, entonces se env�a a cero
			else{
				indices[e]++;//Se incrementa el d�gito m�s significativo y se termina el ciclo de avance
				break;
			}
			if(e==-1)return false;//El n�mero de desbord�
		}
		return true;//Hay siguiente n�mero
	}
}
