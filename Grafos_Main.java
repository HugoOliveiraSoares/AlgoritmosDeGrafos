public class Grafos_Main {
	
	public static void main(String[] args) {
		
		Grafos g1 = new Grafos("K5.csv");
		
		System.out.println(g1.toString()); // Exibe info do grafo G
		
		// Outro modo de obter e exibir a info do grafo G
		System.out.println( "Matriz do grafo K5:\n" + g1.exibirMatrix(g1.getMatrizAdjacencias()) );
		System.out.println( "Numero de vertices: " + g1.getNumVertices() );
		System.out.println( "Numero de arestas: " + g1.getNumArestas() );
		System.out.println( "Grau dos vertices: " + g1.exibirVetor(g1.getGrausVertices()) );
		System.out.println( "Grau do vertice 2: " + g1.getGrauVertice(2) );
		
		
		//Grafos g2 = new Grafos("n3e2.csv");
		
		
		//Grafos g3 = new Grafos("n4e5.csv");

		
	}

}