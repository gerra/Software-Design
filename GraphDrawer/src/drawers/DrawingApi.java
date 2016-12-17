package drawers;

import java.awt.geom.Point2D;

public interface DrawingApi {
    long getDrawingAreaWidth();
    long getDrawingAreaHeight();
    void drawCircle(Point2D center, double r);
    void drawLine(Point2D point1, Point2D point2);
    void drawString(String s, Point2D left);
    void run();
}
