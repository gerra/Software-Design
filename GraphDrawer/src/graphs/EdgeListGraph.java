package graphs;

import drawers.DrawingApi;

import java.util.List;

public class EdgeListGraph extends Graph {

    private int n;
    private List<GraphEdge> edges;

    public EdgeListGraph(List<GraphEdge> edges, DrawingApi drawingApi) {
        super(drawingApi);
        this.edges = edges;
        for (GraphEdge edge : edges) {
            n = Math.max(edge.getFrom() + 1, n);
            n = Math.max(edge.getTo() + 1, n);
        }
    }

    @Override
    public void drawGraphInner() {
        drawVertexesOnCircle();
        for (GraphEdge edge : edges) {
            drawEdge(edge.getFrom(), edge.getTo());
        }
    }

    @Override
    public int getVertexesCount() {
        return n;
    }
}
