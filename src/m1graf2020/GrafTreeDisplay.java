package m1graf2020;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;


import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.SwingUtilities;

/***
 * TreeExample class is the class who display a graph in Jtree Format
 */
public class GrafTreeDisplay extends JFrame
{
    private JTree tree;
    private DefaultMutableTreeNode root;

    /**
     * The constructor of Tree
     * @param g is the graph that we create his Jtree Format
     */
    public GrafTreeDisplay(Graf g)
    {
        this.root = new DefaultMutableTreeNode("Graph");
        EditsTree(g);
        tree = new JTree(root);
        add(tree);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("JTree Example");
        this.pack();
        this.setSize(300,400);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setVisible(true);
    }

    /**
     * Function who edit a Tree of graph
     * @param g is the graph that we Edit his Jtree Format
     */
    public void EditsTree(Graf g) {
        for(Node node : g.getAllNodes()) {
            DefaultMutableTreeNode parent = new DefaultMutableTreeNode(node.getId().toString());
            root.add(parent);
            for(Edge edge: g.getOutEdges(node)) {
                DefaultMutableTreeNode son = new DefaultMutableTreeNode(edge.getEndnode().getId().toString());
                parent.add(son);
            }
        }

    }
}