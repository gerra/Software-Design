package drawers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class FxDrawer extends Application implements DrawingApi {

    private static Map<String, Queue<DrawTask>> drawTasksMap = new HashMap<>();
    private Thread workerThread;

    private static Queue<DrawTask> getDrawTasks(String key) {
        return drawTasksMap.get(key);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        List<String> parameters = getParameters().getRaw();
        int width = Integer.parseInt(parameters.get(0));
        int height = Integer.parseInt(parameters.get(1));
        String key = parameters.get(2);

        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        Queue<DrawTask> localDrawTasks = getDrawTasks(key);
        if (localDrawTasks != null) {
            workerThread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    DrawTask drawTask = localDrawTasks.poll();
                    if (drawTask != null) {
                        Platform.runLater(() -> drawTask.draw(root));
                    }
                }
            });
            workerThread.start();
        }
    }

    @Override
    public void stop() {
        System.out.println("Stage is closing");
        if (workerThread != null) {
            workerThread.interrupt();
        }
    }


    public FxDrawer() {
    }

    private int width;
    private int height;
    private String key;
    private Queue<DrawTask> drawTasks = new LinkedBlockingQueue<>();

    public FxDrawer(int width, int height) {
        this.width = width;
        this.height = height;

        key = toString();
        drawTasksMap.put(key, drawTasks);
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
        drawTasks.add(root -> {
            Circle circle = new Circle(center.getX(), center.getY(), r, Color.BLUE);
            root.getChildren().add(circle);
        });
    }

    @Override
    public void drawLine(Point2D point1, Point2D point2) {
        drawTasks.add(root -> {
            Line line = new Line(point1.getX(), point1.getY(), point2.getX(), point2.getY());
            line.setStroke(Color.RED);
            root.getChildren().add(line);
        });
    }

    @Override
    public void drawString(String s, Point2D left) {
        drawTasks.add(root -> {
            Text text = new Text(left.getX(), left.getY(), s);
            text.setStroke(Color.WHITE);
            root.getChildren().add(text);
        });
    }

    @Override
    public void run() {
        new Thread(() -> launch(FxDrawer.class, String.valueOf(width), String.valueOf(height), key)).start();
    }


    private interface DrawTask {
        void draw(Group group);
    }
}
