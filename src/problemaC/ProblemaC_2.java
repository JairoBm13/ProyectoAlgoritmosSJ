package problemaC;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import problemaC.German.Node;

public class ProblemaC_2{

	public static void main()throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int caso = 0;
		for(String h,j[];(h=br.readLine())!=null;){
			int cableParaMismaToma = 0;
			int total = 0;
			j=h.split(" ");
			caso++;
			final int N=Integer.parseInt(j[0]),B=Integer.parseInt(j[1]);//Se lee el número de tomas eléctricas (N) y el número de cables (B)
			//			System.err.println(N+" "+B);
			//TODO inicializar la estructura seleccionada para almacenar los arcos
			List<Edge> arcos = new LinkedList<Edge>();
			int[][] mAdj = new int[N][N];
			for (int i = 0; i < mAdj.length; i++) {
				for (int k = 0; k < mAdj.length; k++) {
					mAdj[i][k] = (int) Double.POSITIVE_INFINITY;
				}
			}
			for(int e=0;e<B;e++){
				j=br.readLine().split(" ");
				int u=Integer.parseInt(j[0]);
				int v=Integer.parseInt(j[1]);
				int l=Integer.parseInt(j[2]);
				//TODO agregar el arco a la estructura
				if (u==v) {cableParaMismaToma+=l;}
				else{
					total+=l;
					if (mAdj[u][v] > l ) {					
						mAdj[u][v] = l;mAdj[v][u] = l;
					}
					Edge arco1 = new Edge(v, l);
					arcos.add(arco1);
					Edge arco2 = new Edge(u, l);
				}
				
			}
//			if (caso == 4) {
//				for (int i = 0; i < mAdj.length; i++) {
//					for (int k = 0; k < mAdj.length; k++) {
//						System.out.print(mAdj[i][k]+" ");
//					}
//					System.out.print("\n");
//				}
//			}
			

			System.out.println(solve(N, total ,cableParaMismaToma,arcos, mAdj/*TODO agregar los parametros necesario*/));
		}
	}

	public static int solve(final int N,int total, int cableInservible, List<Edge> arcos, int[][] mAdj/*TODO agregar los parametros necesario*/){
		//TODO su solucion
		int respuesta = 0;
		LinkedList<Integer>[] lAdj = new LinkedList[N];
		Node[] nodos = new Node[N];
		PriorityQueue<Node> cola = new PriorityQueue<Node>(N);
		for (int i = 0; i < N; i++) {
//			System.out.println("Caso "+i);
			cola.clear();
			for(int e=0;e<N;e++){
				if (e != i) {					
					nodos[e] = new Node(e);
					cola.add(nodos[e]);
					lAdj[e]=new LinkedList();
				}else{
					nodos[i] = new Node(i);
					nodos[i].llave = -1;
					cola.add(nodos[e]);
					lAdj[e]=new LinkedList();
				}
				
			}
			while(!cola.isEmpty()){
				Node u = cola.poll();
//				System.out.println("Desde "+u.id);
				for (int j = 0; j < N; j++) {
					int costo = mAdj[u.id][j];
					if(cola.contains(nodos[j]) && nodos[j].llave > costo ){
						nodos[j].padre = u.id;
						nodos[j].llave = costo;
//						System.out.println(costo);
					}
				}
			}
			for (int j = 0; j < N; j++) {
				if (nodos[j].llave != -1){
					lAdj[nodos[j].padre].add(j);
				}
				else{
					nodos[j].llave = 0;
				}
			}
			boolean completo = true;
			dfsVisit(0, lAdj, nodos);
			for (int j = 0; j < nodos.length && completo; j++) {
				if (!nodos[j].marcado)completo = false;
			}
			int aDejar = 0;
			if (completo) {
				for (int j = 0; j < nodos.length; j++) {
					aDejar += nodos[j].llave;
				}
				return total + cableInservible - aDejar;
			}
		}
		return 0;
	}

	public static LinkedList<Node> cola(Node[] vertices){
		LinkedList<Node> lista = new LinkedList<Node>();
		for (int i = 0; i < vertices.length; i++) {
			lista.add(vertices[i]);
		}
		return lista;
	}

	static int time = 0;
	public static void dfsVisit(int u,List<Integer>[] lAdj,Node[] nodos){
		nodos[u].marcado = true;
		for(Integer v:lAdj[u])if(!nodos[v].marcado)dfsVisit(v, lAdj,nodos);
	}

	static class Edge implements Comparable<Edge>{
		int destino;
		double longitud;
		boolean usado=false;
		public Edge(int d, int l)
		{
			destino = d;
			longitud = l;
		}

		@Override
		public int compareTo(Edge arg0) {
			double a = longitud-arg0.longitud;
			return (int)a;
		}

		public String toString(){
			return longitud+"";
		}
	}

	static class Node implements Comparable<Node>{
		final static int BLANCO=0,GRIS = 1,NEGRO = 2;
		boolean marcado = false;
		int id;
		int color;
		double llave;
		int padre;
		int tiempoFinalVisita;
		public Node(int id){
			this.id = id;
			llave = Double.POSITIVE_INFINITY;
		}
		@Override
		public int compareTo(Node o) {
			int a=tiempoFinalVisita-o.tiempoFinalVisita;
			if(a==0)return id-o.id;
			return a;
		}
	}
}
