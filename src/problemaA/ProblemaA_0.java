package problemaA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class ProblemaA_0 {

	public static void main()throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		for(String h,j[];(h=br.readLine())!=null;){
			j=h.split(" ");
			int[] numeros=new int[j.length];
			for(int e=0;e<numeros.length;e++)numeros[e]=Integer.parseInt(j[e]);
			System.out.println(solve(numeros)?"Yes":"No");//Se llama el metodo solve con la información leida
		}
	}
	//Solución ingenua
	public static boolean solve(int[] numeros){
		LinkedList<Integer> ent=new LinkedList<Integer>();
		for(int e:numeros)ent.add(e);
		return solve(ent,new LinkedList<Integer>());
	}
	public static boolean solve(LinkedList<Integer> nums,LinkedList<Integer> solucion){
		if(nums.isEmpty()){//Llego a una posible solucion
			int sqrt=(int)(Math.sqrt(solucion.size()));//Calcula el tamaño del cuadrado
			int[][] tablero=new int[sqrt][sqrt];//Crea un tablero
			for(int e=0;e<sqrt;e++){
				for(int i=0;i<sqrt;i++){
					tablero[e][i]=solucion.get(e*sqrt+i);//Agrega los números al tablero
				}
			}
			int suma=0;
			for(int e=0;e<sqrt;e++)suma+=tablero[e][0];//Calcula la suma de la primera columna
			int sumaDiagonal1=0,sumaDiagonal2=0;
			for(int e=0;e<sqrt;e++){
				int sumaFila=0;
				int sumaCol=0;
				for(int i=0;i<sqrt;i++){
					sumaFila+=tablero[e][i];//Calcula la suma de la fila e 
					sumaCol+=tablero[i][e];//Calcula la suma de la columna e
				}
				if(sumaFila!=suma||sumaCol!=suma)return false;//Verifica que las sumas coincidan con la suma de la primera fila
				sumaDiagonal1+=tablero[e][e];//Calcula la suma de la diagonal primaria
				sumaDiagonal2+=tablero[e][sqrt-e-1];//Calcula la suma de la diagonal secundaria
			}
			if(sumaDiagonal1!=suma||sumaDiagonal2!=suma)return false;//Verifica que la sumas de las diagonales sean válida
			return true;//Encontro una solucion
		}else{
			for(int e=0;e<nums.size();e++){
				int v=nums.remove(e);//quita el numero de nums
				solucion.add(v);//Lo agrega a la solucion
				
				if(solve(nums, solucion))return true;//Invoca recursivamente solve
				
				solucion.removeLast();//Quita el elemento que agrego a la solucion
				nums.add(e,v);//Coloca el numero que quito de nums en la posicion en la que estaba 
			}
		}
		return false;
	}
	
}
