package pl.edu.gis.kayles;

import pl.edu.gis.kayles.util.GameProperties;
import pl.edu.gis.kayles.util.Graph;
import pl.edu.gis.kayles.util.Vertex;
import pl.edu.gis.kayles.view.GamePropertiesWindow;
import pl.edu.gis.kayles.view.GraphWindow;

/**
 * Created with IntelliJ IDEA.
 * Date: 16.01.14
 * Time: 16:33
 */
public class Game {

    private static Graph graph;
    private static GameProperties properties;
    private static GraphWindow graphWindow;

    public static void main(String[] args) {
        GamePropertiesWindow window = new GamePropertiesWindow();
    }

    public static void startGame(GameProperties gameProperties) {
        properties = gameProperties;
        graph = new Graph(gameProperties.getGraphSize());
        graphWindow = new GraphWindow(gameProperties.isSinglePlayer());
        graphWindow.showGraph(graph);
    }

    public static Graph nextMove(String vertex, boolean secondAiPlayer) {
        if (vertex != null) {
            graph.kaylesRemove(vertex);
            return graph;
        } else {
            if (secondAiPlayer) {
                Vertex v = graph.makeDecision(properties.getWa2(), properties.getWb2(), properties.getWc2());
                System.out.println(v.toString());
                graph.kaylesRemove(v);
            } else {
                Vertex v = graph.makeDecision(properties.getWa1(), properties.getWb1(), properties.getWc1());
                System.out.println(v.toString());
                graph.kaylesRemove(v);
            }
            return graph;
        }
    }


}
