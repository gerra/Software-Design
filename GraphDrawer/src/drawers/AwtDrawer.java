package drawers;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class AwtDrawer implements DrawingApi {

    private int width;
    private int height;

    private List<DrawTask> paintQueue = new ArrayList<>();
    private JFrame frame;

    private class GraphPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D) g;
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.setRenderingHints(hints);
            for (DrawTask drawTask : paintQueue) {
                drawTask.draw(graphics2D);
            }
        }
    }

    public AwtDrawer(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public long getDrawingAreaWidth() {
        return width;
    }

    @Override
    public long getDrawingAreaHeight() {
        return height;
    }

    @Override
    public void drawCircle(Point2D center, double r) {
        Point2D leftCornerVertex = new Point2D.Double(center.getX() - r, center.getY() - r);
        final double finalR = r * 2;
        paintQueue.add(graphics -> {
            graphics.setColor(Color.RED);
            Shape oval = new Ellipse2D.Double(leftCornerVertex.getX(), leftCornerVertex.getY(), finalR, finalR);
            graphics.draw(oval);
            graphics.fill(oval);
        });
        repaintInAwtThread();
    }

    @Override
    public void drawLine(Point2D point1, Point2D point2) {
        paintQueue.add(graphics -> {
            graphics.setColor(Color.BLUE);
            Shape line = new Line2D.Double(point1, point2);
            graphics.draw(line);
        });
        repaintInAwtThread();
    }

    @Override
    public void drawString(String s, Point2D left) {
        paintQueue.add(graphics -> {
            graphics.setColor(Color.BLACK);
            graphics.drawString(s, (float) left.getX(), (float) left.getY());
        });
        repaintInAwtThread();
    }

    @Override
    public void run() {
        EventQueue.invokeLater(() -> {
            if (frame == null) {
                frame = new JFrame();
                frame.getContentPane().add(new GraphPanel());
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setSize(width, height);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            } else {
                frame.repaint();
            }
        });
    }

    private void repaintInAwtThread() {
        EventQueue.invokeLater(this::repaintInCurrentThread);
    }

    private void repaintInCurrentThread() {
        if (frame != null) {
            frame.repaint();
        }
    }

    private interface DrawTask {
        void draw(Graphics2D graphics);
    }
}
