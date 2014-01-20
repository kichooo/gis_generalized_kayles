package pl.edu.gis.kayles.view;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.picking.PickedState;
import pl.edu.gis.kayles.Game;
import pl.edu.gis.kayles.util.Graph;
import pl.edu.gis.kayles.util.Vertex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created with IntelliJ IDEA.
 * Date: 16.01.14
 * Time: 16:34
 */
public class GraphWindow extends JFrame {

    private boolean singlePlayer;

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private boolean secondAiPlayer = false;


    public GraphWindow(boolean singlePlayer) {
        super("generalized kayles");

        this.singlePlayer = singlePlayer;



    }

    public void showGraph(Graph g) {
        getContentPane().removeAll();
        edu.uci.ics.jung.graph.Graph<String, String> graph = transformToGuiGraph(g);

        Layout<String, String> layout = new CircleLayout<>(graph);
        layout.setSize(new Dimension(WIDTH, HEIGHT));
        VisualizationViewer<String, String> vv = new VisualizationViewer<>(layout);

        DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
        gm.setMode(DefaultModalGraphMouse.Mode.PICKING);

        vv.setGraphMouse(gm);

        final PickedState<String> pickedState = vv.getPickedVertexState();

        if (singlePlayer) {
            pickedState.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    Object subject = e.getItem();
                    if (pickedState.isPicked((String) subject)) {
                        int response = JOptionPane.showConfirmDialog(getContentPane(), "Delete this node? ("+subject+")");
                        if (response == JOptionPane.YES_OPTION) {
                            Graph g = Game.nextMove((String) subject, false);
                            if (g.isGameOver()) {
                                JOptionPane.showMessageDialog(getContentPane(), "You have won!");
                            } else {
                                g = Game.nextMove(null, false);
                                if (g.isGameOver()) {
                                    JOptionPane.showMessageDialog(getContentPane(), "You have lost!");
                                } else {
                                    showGraph(g);
                                }
                            }
                        }
                    }
                }
            });
        }

        if (singlePlayer) {
            getContentPane().add(vv);
        } else {
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            JButton button = new JButton("Next Player");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    secondAiPlayer = false;
                    Graph g = Game.nextMove(null, secondAiPlayer);
                    secondAiPlayer = !secondAiPlayer;
                    if (g.isGameOver()) {
                        JOptionPane.showMessageDialog(getContentPane(), "Player "+(secondAiPlayer?2:1)+" has won!");
                    } else {
                        showGraph(g);
                    }
                }
            });
            panel.add(button, BorderLayout.NORTH);
            panel.add(vv, BorderLayout.SOUTH);
            getContentPane().add(panel);
        }
        pack();
        setVisible(true);

    }

    private edu.uci.ics.jung.graph.Graph<String, String> transformToGuiGraph(Graph g) {
        edu.uci.ics.jung.graph.Graph<String, String> graph = new SparseMultigraph<>();
        for (Vertex v : g.getVertices()) {
            graph.addVertex(v.toString());
        }
        for (Vertex from : g.getVertices()) {
            for (Vertex to : from.getNeighbours()) {
                if (!graph.containsEdge(to.toString()+"-"+from.toString())) {
                    graph.addEdge(from.toString()+"-"+to.toString(), from.toString(), to.toString());
                }
            }
        }
        return graph;
    }

}
