package problemaC;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class German {

	public static void main(String[] args)throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String primeraLineaCaso =br.readLine();
		for(;!primeraLineaCaso.equals("0 0");primeraLineaCaso =br.readLine()){
			String[] j=primeraLineaCaso.split(" ");
			final int N=Integer.parseInt(j[0])+1;
			final int B=Integer.parseInt(j[1]);
			
			List<Integer>[] lAdj=new List[N];
			List<Integer>[] lAdjInv=new List[N];
			for(int e=0;e<N;e++){
				lAdj[e]=new ArrayList<Integer>(); 
				lAdjInv[e]=new ArrayList<Integer>();
			}

			for(int e=0;e<B;e++){
				j=br.readLine().split(" ");
				int u=Integer.parseInt(j[0]);
				int v=Integer.parseInt(j[1]);
				if(u!=v){
					lAdj[u].add(v);
					lAdjInv[v].add(u);
				}
			}
			System.out.println(solve(lAdj,lAdjInv));
		}
	}
	public static int solve(List<Integer>[] lAdj,List<Integer>[] lAdjInv){
		Node[] nodos=new Node[lAdj.length],nodosOrdenados=new Node[lAdj.length];
		for(int e=0;e<nodos.length;e++){
			nodosOrdenados[e]=nodos[e]=new Node(e);
		}

		for(int e=0;e<nodos.length;e++)if(nodos[e].color==Node.BLANCO)dfsVisit(e, lAdj, nodos);
		Arrays.sort(nodosOrdenados);
		for(int e=0;e<nodos.length;e++)nodos[e].color=Node.BLANCO;

		for(int e=nodos.length-1;e>=0;e--){
			if(nodosOrdenados[e].color==Node.BLANCO){
				List<Integer> componente=new ArrayList<Integer>();
				dfsVisitInverso(nodosOrdenados[e].id,componente,lAdjInv,nodos);
				int min=Collections.min(componente);
				nodos[min].padreComponenteConexa=min;
				for(int v:componente)if(v!=min){
					nodos[v].padreComponenteConexa=min;
					for(int u:lAdj[v])if(u!=min)lAdj[min].add(u);
					lAdj[v].clear();
				}
			}
		}
		int fuentes=0;
		for(int n=0;n<nodos.length;n++){
			for(int u:lAdj[n])nodos[nodos[u].padreComponenteConexa].marcado=true;
		}
		for(int e=1;e<nodos.length;e++){
			if(nodos[e].padreComponenteConexa==e&&!nodos[e].marcado)fuentes++;
		}
		return fuentes;
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
	}
	public static void dfsVisitInverso(int u,List<Integer> componente,List<Integer>[] lAdjInv,Node[] nodos){
		nodos[u].color=Node.GRIS;
		componente.add(u);
		for(int v:lAdjInv[u])if(nodos[v].color==Node.BLANCO){
			dfsVisitInverso(v,componente,lAdjInv,nodos);
		}
	}
	static class Node implements Comparable<Node>{
		final static int BLANCO=0,GRIS=1;
		int id;
		int color;
		int tiempoFinalVisita;
		int padreComponenteConexa;
		boolean marcado=false;
		public Node(int id) {
			this.id = id;
		}
		@Override
		public int compareTo(Node o) {
			int a=tiempoFinalVisita-o.tiempoFinalVisita;
			if(a==0)return id-o.id;
			return a;
		}
	}
}
