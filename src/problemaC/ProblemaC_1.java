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
			//			System.err.println(N+" "+B);
			//TODO inicializar la estructura seleccionada para almacenar los arcos
			List<Edge> arcos = new LinkedList<Edge>();
			LinkedList<Edge>[] lAdj = new LinkedList[N];
			LinkedList<Edge>[] lAdjInv = new LinkedList[N];
			for (int i = 0; i < N; i++) {
				lAdj[i] = new LinkedList<Edge>();
				lAdjInv[i] = new LinkedList<Edge>();
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
					//					arcos.add(arco2);
					//					System.err.println(u +"->" +v + "with" + l);
					//					System.err.println(v +"->" +u + "with" + l);
				}
			}

			for (int i = 0; i < lAdj.length; i++) {
				Collections.sort(lAdj[i]);
			}
			System.out.println(solve(N,cableParaMismaToma,arcos, lAdj/*TODO agregar los parametros necesario*/));
		}
	}

	public static int solve(final int N, int cableInservible, List<Edge> arcos, LinkedList<Edge>[] lAdj/*TODO agregar los parametros necesario*/){
		//TODO su solucion
		int respuesta = 0;
		LinkedList<Integer>[] adj = new LinkedList[N];
		Node[] nodos=new Node[N];
		for (int i = 0; i < nodos.length; i++) {
			for(int e=0;e<nodos.length;e++)	{nodos[e]=new Node(e);adj[e]=new LinkedList();}
			nodos[i].llave = -1;
			LinkedList<Node> cola = cola(nodos);
			Collections.sort(cola);
			while(!cola.isEmpty()){
				Node u = minimo(cola);
				Collections.sort(cola);
				for (Edge arco :lAdj[u.id]) {
					Node nodo = pertenece(arco.destino, cola);
					if(nodo != null && nodo.llave > arco.longitud){
						nodo.padre = u.id;
						nodo.llave = arco.longitud;
					}
				}
			}
			for (int j = 0; j < N; j++) {
				if (nodos[j].llave != -1){
					adj[nodos[j].padre].add(j);
				}
				else{
					nodos[j].llave = 0;
				}
			}
			boolean completo = true;
			dfsVisit(0, adj, nodos);
			for (int j = 0; j < nodos.length && completo; j++) {
				if (!nodos[j].marcado)completo = false;
			}
			int aDejar = 0;
			if (completo) {
				for (int j = 0; j < nodos.length; j++) {
					aDejar += nodos[j].llave;
				}
				for (int j = 0; j < arcos.size(); j++) {
					respuesta += arcos.get(j).longitud;
				}
				return respuesta + cableInservible - aDejar;
			}
		}
		return 0;
	}

	public static Node pertenece(int id, LinkedList<Node> cola){
		Node nodo = null;
		for (int i = 0; i < cola.size() && nodo == null; i++) {
			if (cola.get(i).id == id) {
				nodo = cola.get(i);
			}
		}
		return nodo;
	}

	public static LinkedList<Node> cola(Node[] vertices){
		LinkedList<Node> lista = new LinkedList<Node>();
		for (int i = 0; i < vertices.length; i++) {
			lista.add(vertices[i]);
		}
		return lista;
	}

	public static Node minimo(LinkedList<Node> vertices){
		Node min = new Node(-1);
		int lugar = 0;
		for (int i = 0; i < vertices.size(); i++) {
			if(min.llave > vertices.get(i).llave){
				lugar = i;
				min = vertices.get(i);
			}
		}
		return vertices.remove(lugar);
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
