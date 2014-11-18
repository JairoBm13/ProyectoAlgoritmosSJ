package problemaC;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProblemaC_0 {
	public static void main()throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		for(String h,j[];(h=br.readLine())!=null;){
			j=h.split(" ");
			final int N=Integer.parseInt(j[0]),B=Integer.parseInt(j[1]);//Se lee el número de tomas eléctricas (N) y el número de cables (B)
			int[][] arcos=new int[B][3];
			for(int e=0;e<B;e++){
				j=br.readLine().split(" ");
				arcos[e][0]=Integer.parseInt(j[0]);
				arcos[e][1]=Integer.parseInt(j[1]);
				arcos[e][2]=Integer.parseInt(j[2]);
			}
			System.out.println(solve(N, B, arcos));
		}
	}
	//Solución ingenua
	public static int solve(final int N,final int B,int[][] arcos){
		int resp=0;
		boolean[] usado=new boolean[B];//Arreglo para marcar los arcos usados
		boolean[] visitados=new boolean[B];//Arreglo para marcar los nodos visitados
		@SuppressWarnings("unchecked")
		List<Integer>[] lAdj=new List[N];//Lista de adyacencia para representar el grafo construido a partir de los arcos usados
		for(int e=0;e<lAdj.length;e++)lAdj[e]=new ArrayList<Integer>();//Inicializa las listas de adyacencia
		for(long test=0,max=(1l<<B);test<=max;test++){
			long mascara=1;//Mascara para iterar sobre los bits de test
			for(int e=0;e<B;e++,mascara<<=1)usado[e]=(test&mascara)!=0;//Si el numero test en el bit e es 1 entonces el arco e es usado
			for(int e=0;e<lAdj.length;e++)lAdj[e].clear();//Reinicia las listas de adyacencia
			int cableRemovido=0;//Iniciliza el costo, representa la suma de las longitudes de los arcos NO usados
			for(int i=0;i<B;i++){
				if(usado[i]){//Si el arco es usado lo agrega a la lista de adyacencia
					lAdj[arcos[i][0]].add(arcos[i][1]);
					lAdj[arcos[i][1]].add(arcos[i][0]);
				}else cableRemovido+=arcos[i][2];//Si el arco no es usado, lo agrega al total de cableRemovido
			}
			Arrays.fill(visitados, false);//Reinicia el arreglo de visitados en falso
			dfsVisit(visitados, 0, lAdj);//Utiliza dfs determinar conectividad
			boolean esConexo=true;
			for(int e=0;e<N&&esConexo;e++){
				if(!visitados[e])esConexo=false;//Verifica que a partir del nodo cero se haya podido llegar a todos los demas nodos
			}
			if(esConexo)resp=Math.max(cableRemovido, resp);//Si el grafo asociado a lAdj es conexo lo tiene en cuenta en el máximo
		}
		return resp; 
	}
	public static void dfsVisit(boolean[] visitados,int src,List<Integer>[] lAdj){
		visitados[src]=true;
		for(Integer v:lAdj[src])if(!visitados[v])dfsVisit(visitados, v, lAdj);
	}
}
