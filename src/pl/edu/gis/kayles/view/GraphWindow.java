package pl.edu.gis.kayles.view;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import pl.edu.gis.kayles.util.Graph;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Date: 16.01.14
 * Time: 16:34
 */
public class GraphWindow extends JFrame {

    private mxGraph graph;
    private Object parent;

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;


    public GraphWindow() {
        super("generalized kayles");

        graph = new mxGraph();
        parent = graph.getDefaultParent();

        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLocationRelativeTo(null);
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
    }

    public void showGraph(Graph g) {

        getContentPane().removeAll();

        graph = new mxGraph();
        parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        Map<Object, Object> inserted = new HashMap<Object, Object>();
        try {
            int x = 10;
            int y = 10;
            for (Object v : g.getVertices()) {
                Object o = graph.insertVertex(parent, v.toString(), v, x, y, 10, 10);
                inserted.put(v, o);

                x += 20;
                if (x >= WIDTH) {
                    y += 20;
                    x = 10;
                }
            }
        } finally {
            graph.getModel().endUpdate();
        }
        final mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);

        graphComponent.getGraphControl().addMouseListener(new MouseAdapter()
        {

            public void mouseReleased(MouseEvent e)
            {
                Object cell = graphComponent.getCellAt(e.getX(), e.getY());

                if (cell != null && !"edge".equals(graph.getLabel(cell).toLowerCase()))
                {
                    int decision = JOptionPane.showConfirmDialog(graphComponent, "Delete this node?");
                    if (decision == JOptionPane.YES_OPTION) {
                        //todo handle delete!
                    }
                }
            }
        });
    }

}
