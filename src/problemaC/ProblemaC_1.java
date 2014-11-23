package problemaC;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import problemaC.German.Node;

public class ProblemaC_1 {

	public static void main()throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		for(String h,j[];(h=br.readLine())!=null;){
			int cableParaMismaToma = 0;
			j=h.split(" ");
			final int N=Integer.parseInt(j[0]),B=Integer.parseInt(j[1]);//Se lee el número de tomas eléctricas (N) y el número de cables (B)
			//TODO inicializar la estructura seleccionada para almacenar los arcos
			
			List<Edge> arcos = new LinkedList<Edge>();
			List<Edge>[] lAdj = new List[N];
			for (int i = 0; i < N; i++) {
				lAdj[i] = new LinkedList<Edge>();
			}
			for(int e=0;e<B;e++){
				j=br.readLine().split(" ");
				int u=Integer.parseInt(j[0]);
				int v=Integer.parseInt(j[1]);
				int l=Integer.parseInt(j[2]);
				//TODO agregar el arco a la estructura
				if (u==v) {cableParaMismaToma+=l;}
				else{
					Edge arco1 = new Edge(v, l);
					lAdj[u].add(arco1);
					arcos.add(arco1);
					Edge arco2 = new Edge(u, l);
					lAdj[v].add(arco2);
					arcos.add(arco2);
				}
			}
			
			for (int i = 0; i < lAdj.length; i++) {
				Collections.sort(lAdj[i]);
			}
			Collections.sort(arcos);
			System.out.println("cable "+cableParaMismaToma);
			System.out.println(solve(N,cableParaMismaToma,arcos, lAdj/*TODO agregar los parametros necesario*/));
		}
	}

	public static int solve(final int N, int cableInservible, List<Edge> arcos, List[] lAdj/*TODO agregar los parametros necesario*/){
		//TODO su solucion
		Node[] nodos=new Node[lAdj.length];
		for(int e=0;e<nodos.length;e++)	nodos[e]=new Node(e);
		int max = 0;
		int respuesta = 0;
		for (int i = 0; i < lAdj.length; i++) {
			
			List<Edge> actual = lAdj[i];
			nodos[i].marcado = true;
			for (Edge edge : actual) {
				if (nodos[edge.destino].llave > edge.longitud && !nodos[edge.destino].marcado) {
					if (nodos[edge.destino].llave!=Double.POSITIVE_INFINITY) {
						respuesta += nodos[edge.destino].llave;
					}
					nodos[edge.destino].llave = edge.longitud;
					nodos[edge.destino].padre = i;
				}else respuesta += edge.longitud;	
			}
			max=Math.max(respuesta, max);
		}
		return max+cableInservible;
	}
	
	static int time = 0;
	public static void dfsVisit(int u,List<Integer>[] lAdj,Node[] nodos){
		nodos[u].color=Node.GRIS;
		time++;
		for(int v:lAdj[u])if(nodos[v].color==Node.BLANCO){
			dfsVisit(v,lAdj,nodos);
		}
		time++;
		nodos[u].tiempoFinalVisita=time;
		nodos[u].color = Node.NEGRO;
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
