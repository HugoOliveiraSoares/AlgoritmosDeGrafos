package src.kruskal;

import src.Grafos;
import java.util.ArrayList;
import java.util.List;

public class Kruskal {

    private Grafos grafo;
    private List<Integer> pesoArestas = new ArrayList<>();
    private List<int[]> arestas =  new ArrayList<>(); // lista de arestas identificadas pelos pares de vértices
    private int[][] arvore;

    public Kruskal() {
    }

    public Kruskal(Grafos grafo) {
        this.grafo = grafo;
//        geraArvoreMinima(grafo.getMatrizPesos());
    }

    public void geraArvoreMinima(int[][] matrizPesos){
        ordenaArestas(matrizPesos);
        arvore = new int[matrizPesos.length][matrizPesos.length];

        int vertice1, vertice2;
        for (int i = 0; i < arestas.size(); i++) {
            vertice1 = arestas.get(i)[0];
            vertice2 = arestas.get(i)[1];
            if (!fechaCircuito(arvore, vertice2)){
                arvore[vertice1][vertice2] = matrizPesos[vertice1][vertice2];
                arvore[vertice2][vertice1] = matrizPesos[vertice2][vertice1];
            }
        }

        String res = "";
        for (int i = 0; i < arvore.length; i++) {
            for (int j = 0; j < arvore[0].length; j++) {
                res += ((j < (arvore[0].length - 1)) ? arvore[i][j] + "  " : arvore[i][j]);
            }
            res += ((i < (arvore.length - 1)) ? "\n" : "");
        }
        System.out.println(res);

    }

    public void ordenaArestas(int[][] matrizPesos){

        //extrai os pesos das arestas junto com as suas conexões
        for (int i = 0; i < matrizPesos.length; i++) {
            for (int j = 0; j < matrizPesos.length; j++) {
                if (matrizPesos[i][j] != 0) {
                    arestas.add(new int[]{i,j});
                    pesoArestas.add(matrizPesos[i][j]);
                }
            }
        }

        int aux; // auxiliar para o pesoAresta
        int[] auxx; // auxiliar para a arestas
        for (int i = 0; i < pesoArestas.size(); i++) {
            for (int j = 0; j < pesoArestas.size() - 1; j++) {
                if (pesoArestas.get(j) > pesoArestas.get(j + 1)){
                    aux = pesoArestas.get(j);
                    pesoArestas.set(j, pesoArestas.get(j + 1));
                    pesoArestas.set(j + 1, aux);
                    auxx = arestas.get(j);
                    arestas.set(j, arestas.get(j + 1));
                    arestas.set(j + 1, auxx);
                }
            }
        }

        System.out.println(">>> Grafo Ordenado <<< ");
        System.out.println();
        arestas.forEach(aresta ->{
            for (int i = 0; i < aresta.length; i++) {
                System.out.print(aresta[i] + "");
            }
            System.out.print(" ");
        });
        System.out.println();
        pesoArestas.forEach(peso -> System.out.print(peso + " "));

    }

    private boolean fechaCircuito(int[][] arvore, int vertice2){

        for (int i = vertice2 - 1; i >= 0 ; i--) {
            if (arvore[i][vertice2] != 0){
                return true;
            }
        }
        return false;
    }

    public Grafos getGrafo() {
        return grafo;
    }

    public void setGrafo(Grafos grafo) {
        this.grafo = grafo;
    }

    public List<int[]> getArestas() {
        return arestas;
    }

    public void setArestas(List<int[]> arestas) {
        this.arestas = arestas;
    }

    public int[][] getArvore() {
        return arvore;
    }

    public void setArvore(int[][] arvore) {
        this.arvore = arvore;
    }
}