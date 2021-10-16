package src.kruskal;

import src.Grafos;
import java.util.ArrayList;
import java.util.List;

public class Kruskal {

    private Grafos grafos;
    private List<Integer> pesoArestas = new ArrayList<>();
    private List<int[]> vertices =  new ArrayList<>();
    private List<Integer> arvore = new ArrayList<>();

    public Kruskal() {
    }

    public Kruskal(Grafos grafos) {
        this.grafos = grafos;
    }

    public void geraArvoreMinima(){
        ordenaArestas(this.grafos.getMatrizPesos(), this.grafos.getNumArestas());

        for (int i = 0; i < vertices.size(); i++) {
            if (i > 0){
                if (!(arvore.contains(vertices.get(i)[0]) || arvore.contains(vertices.get(i)[1])) ){
                    arvore.add(vertices.get(i)[0]);
                    arvore.add(vertices.get(i)[1]);
                }
            }
        }

        System.out.println("\n Arvore minima");
        arvore.forEach(integer -> System.out.print(integer + " "));

    }

    public void ordenaArestas(int[][] matrizPesos, int numArestas){

        //extrai os pesos das arestas junto com as suas conexões
        for (int i = 0; i < matrizPesos.length; i++) {
            for (int j = 0; j < matrizPesos.length; j++) {
                if (matrizPesos[i][j] != 0) {
                    pesoArestas.add(matrizPesos[i][j]);
                    vertices.add(new int[]{i,j});
                }
            }
        }

        // ordena as arestas em ordem crescente de pesos
        int aux;
        int[] aux2;
        for(int i = 0; i < pesoArestas.size(); i++){
            for(int k = 0; k < pesoArestas.size() - 1; k++){
                if(pesoArestas.get(k) > pesoArestas.get(k + 1)){
                    aux = pesoArestas.get(k);
                    pesoArestas.set(k,pesoArestas.get(k+1));
                    pesoArestas.set(k+1, aux);
                    aux2 = vertices.get(k);
                    vertices.set(k, vertices.get(k+1));
                    vertices.set(k+1, aux2);
                }
            }
        }

        System.out.println(">>> Grafo Ordenado <<< ");
        for (int i = 0; i < pesoArestas.size(); i++) {
            System.out.print(pesoArestas.get(i) + " ");
        }

        System.out.println();
        vertices.forEach(vertice ->{
            for (int i = 0; i < vertice.length; i++) {
                System.out.print(vertice[i] + "");
            }
            System.out.print(" ");
        });
    }

    private boolean pertenceAoGrafo(int vertice, int[] vertices){
        return vertice == vertices[0] || vertice == vertices[1];
    }

    public Grafos getGrafos() {
        return grafos;
    }

    public void setGrafos(Grafos grafos) {
        this.grafos = grafos;
    }

    public List<Integer> getPesoArestas() {
        return pesoArestas;
    }

    public void setPesoArestas(List<Integer> pesoArestas) {
        this.pesoArestas = pesoArestas;
    }

    public List<int[]> getVertices() {
        return vertices;
    }

    public void setVertices(List<int[]> vertices) {
        this.vertices = vertices;
    }

    public List<Integer> getArvore() {
        return arvore;
    }

    public void setArvore(List<Integer> arvore) {
        this.arvore = arvore;
    }
}