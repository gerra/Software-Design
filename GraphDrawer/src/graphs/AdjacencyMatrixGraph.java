package graphs;

import drawers.DrawingApi;

import java.util.Arrays;

public class AdjacencyMatrixGraph extends Graph {

    private int n;
    private boolean[][] adjacencyMatrix;

    public AdjacencyMatrixGraph(boolean[][] adjacencyMatrix, DrawingApi drawingApi) {
        super(drawingApi);
        n = adjacencyMatrix.length;
        this.adjacencyMatrix = new boolean[n][];
        for (int i = 0; i < n; i++) {
            boolean[] vertexAdjacency = adjacencyMatrix[i];
            assert vertexAdjacency.length == n;
            this.adjacencyMatrix[i] = Arrays.copyOf(vertexAdjacency, n);
        }
    }

    @Override
    public void drawGraphInner() {
        drawVertexesOnCircle();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjacencyMatrix[i][j]) {
                    drawEdge(i, j);
                }
            }
        }
    }

    @Override
    public int getVertexesCount() {
        return n;
    }
}
