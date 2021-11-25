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

		System.out.println( "Numero de vertices: " + grafo.getNumVertices() );
		System.out.println( "Numero de arestas: " + grafo.getNumArestas() );
		System.out.println( "Matriz de pesos:\n" + grafo.exibirMatrix(grafo.getMatrizAdjacencias()) );

		System.out.println("Algoritmo de Dijkstra: " + grafo.dijkstra(2));
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

		   Insira o número de nós: 6
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
		System.out.println("Insira o número de nós");
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
		System.out.println("O fluxo máximo de G é " + fluxoMax);
		scanner.close();
	}
	
	public static void main(String[] args) {

		buscaEmProfundidade();
		buscaAmplitude();
		dijkstra();
		fordFulkerson();

	}



}