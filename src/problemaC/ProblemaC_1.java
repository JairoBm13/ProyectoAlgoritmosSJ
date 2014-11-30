package problemaC;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class ProblemaC_1 {

	public static void main()throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		for(String h,j[];(h=br.readLine())!=null;){
			int total = 0;
			j=h.split(" ");
			final int N=Integer.parseInt(j[0]),B=Integer.parseInt(j[1]);//Se lee el número de tomas eléctricas (N) y el número de cables (B)
			//TODO inicializar la estructura seleccionada para almacenar los arcos
			LinkedList<Edge>[] lAdj = new LinkedList[N];
			for (int i = 0; i < N; i++) {
				lAdj[i] = new LinkedList<Edge>();
			}
			for(int e=0;e<B;e++){
				j=br.readLine().split(" ");
				int u=Integer.parseInt(j[0]);
				int v=Integer.parseInt(j[1]);
				int l=Integer.parseInt(j[2]);
				//TODO agregar el arco a la estructura
				if (u==v) {total+=l;}
				else{
					total+=l;
					Edge arco1 = new Edge(v, l);
					lAdj[u].add(arco1);
					Edge arco2 = new Edge(u, l);
					lAdj[v].add(arco2);
				}
			}
			System.out.println(solve(N, total, lAdj/*TODO agregar los parametros necesario*/));
		}
	}

	public static int solve(final int N,int total, LinkedList<Edge>[] lAdj/*TODO agregar los parametros necesario*/){
		//TODO su solucion
		LinkedList<Integer>[] adj = new LinkedList[N];
		Node[] nodos=new Node[N];
		for (int i = 0; i < nodos.length; i++) {
			PriorityQueue<Node> cola = new PriorityQueue<Node>(N);
			nodos[i] = new Node(i);
			nodos[i].llave = -1;
			cola.add(nodos[i]);
			for(int e=0;e<nodos.length;e++){
				if (i!=e) {
					nodos[e]=new Node(e);
					cola.add(nodos[e]);					
				}
				adj[e] = new LinkedList<Integer>();
			}
			while(!cola.isEmpty()){
				Node u = cola.poll();
				for (Edge arco :lAdj[u.id]) {
					if(cola.contains(nodos[arco.destino]) && nodos[arco.destino].llave > arco.longitud){
						nodos[arco.destino].padre = u.id;
						Node temp = nodos[arco.destino];
						cola.remove(nodos[arco.destino]);
						temp.llave = arco.longitud;
						cola.add(temp);
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
				return total - aDejar;
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
			return (int)(llave-o.llave);
		}
		
		public String toString(){
			return "id" + id + "," + llave;
		}
	}
}