package m1graf2020;

/***
 * Edge class is the class who create and compare the edges
 */
public class Edge implements Comparable<Edge>{
    private Node startnode;
    private Node endnode;
    private int weight;


    /***
     * Constructor of one edge
     *
     * @param startnode the node from of one edge
     * @param endnode the node to of one edge
     * @param weight if the graph is weighted
     */
    public Edge(Node startnode, Node endnode, int weight) {
        this.startnode = startnode;
        this.endnode = endnode;
        this.weight = weight;
    }

    /***
     * Constructor of one edge
     *
     * @param startnode the node from of one edge
     * @param endnode the node to of one edge
     */
    public Edge(Node startnode, Node endnode) {
        this.startnode = startnode;
        this.endnode = endnode;
    }


    /***
     * Constructor of one edge
     *
     * @param id1 the id of node from of one edge
     * @param id2 the id of node to of one edge
     */
    public Edge(int id1, int id2) {
        this.startnode = new Node(id1);
        this.endnode = new Node(id2);
    }

    /***
     * Getter on node from of edge
     *
     * @return the node from
     */
    public Node getStartnode() {
        return startnode;
    }

    /***
     * Setter on node from of edge
     *
     * @param startnode set node from
     */
    public void setStartnode(Node startnode) {
        this.startnode = startnode;
    }

    /***
     * Getter on node to of edge
     *
     * @return the node to
     */
    public Node getEndnode() {
        return endnode;
    }

    /***
     * Setter on node to of edge
     *
     * @param endnode set node to
     */
    public void setEndnode(Node endnode) {
        this.endnode = endnode;
    }

    /***
     * Getter on weighted of edge
     *
     * @return the weighted of edge
     */
    public int getWeight() {
        return weight;
    }

    /***
     * Setter on weighted of edge
     *
     * @param weight set weighted for one edge
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }


    /***
     * Function who compare two edges together
     *
     * @param e edge to compare with an another
     * @return the result of the comparison
     */
    @Override
    public int compareTo(Edge e)
    {
        if(this.getStartnode().compareTo(e.getStartnode()) == 0)
        {
            return this.getEndnode().compareTo(e.getEndnode());
        }
        return this.getStartnode().compareTo(e.getStartnode());
    }

    @Override
    public String toString() {
        return
                "("+ startnode + ") -> " +
                "(" + endnode + ")";
    }
}
