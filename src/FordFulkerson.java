package src;
//@Jonas Facul olha o pq seu codigo nao esta rodando pfv, pois temos que mandar isso para o professor 
// Autor: Jonas Peres
// Ford Fulkerson: algoritmo (guloso) que calcula o fluxo m�ximo de G

import java.util.LinkedList;
import java.util.Queue;
 
public class FordFulkerson {
	
    private int[] parente;
    private Queue<Integer> queue;
    private int vertices;
    private boolean[] visitado;
 
    public FordFulkerson(int Vertices) {
        this.vertices = Vertices;
        this.queue = new LinkedList<>();
        parente = new int[Vertices + 1];
        visitado = new boolean[Vertices + 1];		
    }
 
    public boolean FF(int fonte, int gol, int[][] matriz) {
        boolean camEncontrado = false;
        int destino, elemento;
 
        for(int aux = 1; aux <= vertices; aux++) {
        	parente[aux] = -1;
        	visitado[aux] = false;
        }
 
        queue.add(fonte);
        parente[fonte] = -1;
        visitado[fonte] = true;
 
        while (!queue.isEmpty()) { 
        	elemento = queue.remove();
            destino = 1;
 
            while (destino <= vertices) {
                if (matriz[elemento][destino] > 0 &&  !visitado[destino]) {
                	parente[destino] = elemento;
                    queue.add(destino);
                    visitado[destino] = true;
                }
                destino++;
            }
        }
        if(visitado[gol]) {
        	camEncontrado = true;
        }
        return camEncontrado;
    }
 
    public int fordFulkerson(int[][] matriz, int fonte, int destino) {
        int u, v;
        int fluxoMax = 0;
        int camFluxo;
 
        int[][] residuoMatriz = new int[vertices + 1][vertices + 1];
        for (int verticeFonte = 1; verticeFonte <= vertices; verticeFonte++) {
            for (int verticeDestino = 1; verticeDestino <= vertices; verticeDestino++) {
            	residuoMatriz[verticeFonte][verticeDestino] = matriz[verticeFonte][verticeDestino];
            }
        }
 
        while (FF(fonte ,destino, residuoMatriz)) {
        	camFluxo = Integer.MAX_VALUE;
            for (v = destino; v != fonte; v = parente[v]) {
                u = parente[v];
                camFluxo = Math.min(camFluxo, residuoMatriz[u][v]);
            }
            for (v = destino; v != fonte; v = parente[v]) {
                u = parente[v];
                residuoMatriz[u][v] -= camFluxo;
                residuoMatriz[v][u] += camFluxo;
            }
            fluxoMax += camFluxo;	
        }
 
        return fluxoMax;
    }
    
}