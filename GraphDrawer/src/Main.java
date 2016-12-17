import drawers.AwtDrawer;
import drawers.DrawingApi;
import drawers.FxDrawer;
import graphs.AdjacencyMatrixGraph;
import graphs.EdgeListGraph;
import graphs.Graph;
import graphs.GraphEdge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

//    private static final boolean[][] g = new boolean[][] {
//            {true, false, false},
//            {true, true, true},
//            {false, true, true}
//    };
//    private static final List<GraphEdge> edges = Arrays.asList(new GraphEdge(1, 2), new GraphEdge(2, 3));

    private static final DrawingApi fxDrawingApi = new FxDrawer(600, 480);
    private static final DrawingApi awtDrawingApi = new AwtDrawer(600, 480);

    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("Two arguments expected");
            return;
        }
        if (args[0] == null || args[1] == null) {
            System.err.println("Arguments should be non null");
            return;
        }

        DrawingApi drawingApi;
        Graph graph;

        if (args[0].equals("1")) {
            drawingApi = fxDrawingApi;
            System.out.println("fx");
        } else {
            drawingApi = awtDrawingApi;
            System.out.println("awt");
        }
        Random random = new Random();
        if (args[1].equals("1")) {
            int n = random.nextInt(20) + 1;
            boolean[][] am = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = i+1; j < n; j++) {
                    am[i][j] = random.nextBoolean();
                }
                System.out.println(Arrays.toString(am[i]));
            }

            graph = new AdjacencyMatrixGraph(am, drawingApi);
            System.out.println("adjacency");
        } else {

            int n = random.nextInt(20) + 1;
            int m = random.nextInt(n * (n - 1) / 2) + 1;

            List<GraphEdge> edges = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                int from = random.nextInt(n);
                int to = random.nextInt(n);
                System.out.println(from + " -> " + to);
                edges.add(new GraphEdge(from, to));
            }

            graph = new EdgeListGraph(edges, drawingApi);
            System.out.println("edges");
        }

        graph.drawGraph();
    }
}
