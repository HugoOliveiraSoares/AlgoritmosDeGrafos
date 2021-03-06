package src;

import java.util.Scanner;

public class Grafos_Main {

	public static void buscaEmProfundidade(){

		Grafos grafo = new Grafos("n4e5.csv");

		System.out.println( "Numero de vertices: " + grafo.getNumVertices() );
		System.out.println( "Numero de arestas: " + grafo.getNumArestas() );
		System.out.println( "Matriz de pesos:\n" + grafo.exibirMatrix(grafo.getMatrizAdjacencias()) );

		System.out.println("Grafo em Profundidade: " + grafo.buscaProfundidade( 1));

	}

	public static void buscaAmplitude(){

		Grafos grafo = new Grafos("n4e5.csv");

		System.out.println( "Numero de vertices: " + grafo.getNumVertices() );
		System.out.println( "Numero de arestas: " + grafo.getNumArestas() );
		System.out.println( "Matriz de pesos:\n" + grafo.exibirMatrix(grafo.getMatrizAdjacencias()) );

		System.out.println("Grafo em Amplitude: " + grafo.buscaAmplitude(3));
	}

	public static void dijkstra(){

		Grafos grafo = new Grafos("dijkstra.csv");
		//Grafos ponderados
//		Grafos grafo = new Grafos("dijkstra2.csv");
//		Grafos grafo = new Grafos("dijkstra3.csv");

		System.out.println( "Numero de vertices: " + grafo.getNumVertices() );
		System.out.println( "Numero de arestas: " + grafo.getNumArestas() );
		System.out.println( "Matriz de pesos:\n" + grafo.exibirMatrix(grafo.getMatrizAdjacencias()) );

		System.out.println("Algoritmo de Dijkstra: " + grafo.dijkstra(0));
	}

	public static void kruskal(){

		Grafos grafo = new Grafos("kruskal2.csv");
//		Grafos grafo = new Grafos("kruskal.csv"); // exemplo do Cormen

		System.out.println( "Numero de vertices: " + grafo.getNumVertices() );
		System.out.println( "Numero de arestas: " + grafo.getNumArestas() );
		System.out.println( "Matriz de Adjacencias:\n" + grafo.exibirMatrix(grafo.getMatrizAdjacencias()) );

		Grafos arvoreGeradora = grafo.kruskal(0);

		System.out.println("Matriz de Adjacencias da Arvore Geradora Minima:\n" + arvoreGeradora.exibirMatrix(arvoreGeradora.getMatrizAdjacencias()));

	}

	public static void fordFulkerson(){

/* Dados para teste!!

Matriz:
0 16 13 0 0 0
0 0  10 12 0 0
0 4 0 0 14 0
0 0 9 0 0 20
0 0 0 7 0 4
0 0 0 0 0 0

		   Insira o n??mero de n??s: 6
		   Insira o numero de fontes: 1
		   Insira o numero de sorvedouros: 6
		   Resposta: 23
     */

		int[][] matriz;
		int nos;
		int fonte;
		int sorvedouro;
		int fluxoMax;

		Scanner scanner = new Scanner(System.in);
		System.out.println("Insira o n??mero de n??s");
		nos = scanner.nextInt();
		matriz = new int[nos + 1][nos + 1];

		System.out.println("Insira a matriz"); // COLOCA OS NUMERO DA MATRIZ SEM VIRGULA NA HORA DE PASSAR
		for (int sourceVertex = 1; sourceVertex <= nos; sourceVertex++) {
			for (int destinationVertex = 1; destinationVertex <= nos; destinationVertex++) {
				matriz[sourceVertex][destinationVertex] = scanner.nextInt();
			}
		}

		System.out.println("Insira o numero de fontes");
		fonte= scanner.nextInt();

		System.out.println("Insira o numero de sorvedouros");
		sorvedouro = scanner.nextInt();

		FordFulkerson fordFulkerson = new FordFulkerson(nos);
		fluxoMax = fordFulkerson.fordFulkerson(matriz, fonte, sorvedouro);
		System.out.println("O fluxo m??ximo de G ?? " + fluxoMax);
		scanner.close();
	}

	public static void main(String[] args) {

		buscaEmProfundidade();
		buscaAmplitude();
		dijkstra();
		kruskal();
		fordFulkerson();

	}



}