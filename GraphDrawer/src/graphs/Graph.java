package graphs;

import drawers.DrawingApi;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public abstract class Graph {

    private DrawingApi drawingApi;
    private Map<Integer, Point2D> vertexesCoordinates;

    public Graph(DrawingApi drawingApi) {
        this.drawingApi = drawingApi;
    }

    protected abstract void drawGraphInner();
    public abstract int getVertexesCount();

    public void drawGraph() {
        drawGraphInner();
        drawingApi.run();
    }

    protected double getVertexRadiusMultiplier() {
        return 1. / 20;
    }

    protected double getVertexRadius() {
        return getVertexRadiusMultiplier() * getDrawingAreaRadius();
    }

    protected String getVertexLabelById(int vertexId) {
        return Integer.toString(vertexId);
    }

    private Point2D getDrawingAreaCenter() {
        return new Point2D.Double(
                drawingApi.getDrawingAreaWidth() / 2.0,
                drawingApi.getDrawingAreaHeight() / 2.0);
    }

    private double getDrawingAreaRadius() {
        Point2D drawingCenter = getDrawingAreaCenter();
        double x = drawingCenter.getX();
        double y = drawingCenter.getY();
        double r = Math.min(x, y);
        r = Math.min(r, drawingApi.getDrawingAreaWidth() - x);
        r = Math.min(r, drawingApi.getDrawingAreaHeight() - y);
        return r;
    }

    private void initVertexesCoordinates() {
        if (vertexesCoordinates == null) {
            Map<Integer, Point2D> localVertexesCoordinates = new HashMap<>();
            int n = getVertexesCount();

            Point2D areaCenter = getDrawingAreaCenter();
            double areaRadius = getDrawingAreaRadius() - getVertexRadius();

            for (int i = 0; i < n; i++) {
                double angle = Math.toRadians(360) / n * i;
                double vertexX = Math.cos(angle) * areaRadius + areaCenter.getX();
                double vertexY = Math.sin(angle) * areaRadius + areaCenter.getY();
                localVertexesCoordinates.put(i, new Point2D.Double(vertexX, vertexY));
            }
            vertexesCoordinates = localVertexesCoordinates;
        }
    }

    protected void drawVertexesOnCircle() {
        initVertexesCoordinates();
        double r = getVertexRadius();
        for (Map.Entry<Integer, Point2D> entry : vertexesCoordinates.entrySet()) {
            Point2D vertexCenter = entry.getValue();
            drawingApi.drawCircle(vertexCenter, r);
            drawingApi.drawString(getVertexLabelById(entry.getKey()), vertexCenter);
        }
    }

    protected void drawEdge(int fromId, int toId) {
        if (fromId == toId) {
            return;
        }
        initVertexesCoordinates();
        Point2D from = vertexesCoordinates.get(fromId);
        Point2D to = vertexesCoordinates.get(toId);
        if (from == null) {
            throw new IllegalArgumentException("There is no vertex with id = " + fromId);
        }
        if (to == null) {
            throw new IllegalArgumentException("There is no vertex with id = " + toId);
        }
        double x1 = from.getX();
        double y1 = from.getY();
        double x2 = to.getX();
        double y2 = to.getY();
        double angle = Math.atan2(y1 - y2, x2 - x1);
        x1 += Math.cos(angle) * getVertexRadius();
        y1 -= Math.sin(angle) * getVertexRadius();
        x2 -= Math.cos(angle) * getVertexRadius();
        y2 += Math.sin(angle) * getVertexRadius();

        from = new Point2D.Double(x1, y1);
        to = new Point2D.Double(x2, y2);

        drawingApi.drawLine(from, to);

        // draw arrow
        for (double arrowDegree : new double[] {-30, 30}) {
            angle = Math.toRadians(arrowDegree);
            double cos = Math.cos(angle);
            double sin = Math.sin(angle);
            double x1p = ((x1 - x2) * cos - (y1 - y2) * sin) / 15 + x2;
            double y1p = ((x1 - x2) * sin + (y1 - y2) * cos) / 15 + y2;
            drawingApi.drawLine(new Point2D.Double(x2, y2), new Point2D.Double(x1p, y1p));
        }
    }
}
