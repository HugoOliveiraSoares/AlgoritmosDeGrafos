package src;

import java.util.List;

public class Grafos_Main {
	
	public static void main(String[] args) {
		
		Grafos g1 = new Grafos("dijkstra.csv");
		
//		System.out.println(g1.toString()); // Exibe info do grafo G
		
		// Outro modo de obter e exibir a info do grafo G
		System.out.println( "Matriz do grafo:\n" + g1.exibirMatrix(g1.getMatrizAdjacencias()) );
//		System.out.println( "Numero de vertices: " + g1.getNumVertices() );
//		System.out.println( "Numero de arestas: " + g1.getNumArestas() );
//		System.out.println( "Grau dos vertices: " + g1.exibirVetor(g1.getGrausVertices()) );
//		System.out.println( "Grau do vertice 2: " + g1.getGrauVertice(2) );
//		System.out.println( "Matriz de pesos: " + g1.exibirMatrix(g1.getMatrizPesos()));

		//src.Grafos g2 = new src.Grafos("n3e2.csv");
		//src.Grafos g3 = new src.Grafos("n4e5.csv");

//		int[] kruskal = g1.kruskal();
//
//		for (int i = 0; i < kruskal.length; i++){
//			System.out.println(i + "," + kruskal[i]);
//		}

		List<Integer> dijkstra = g1.dijkstra(0);

		System.out.println("incluidos " + dijkstra);

//		System.out.println(g1.buscaProfundidade(2, 3));

	}

}