package m1graf2020;

public class Edge implements Comparable<Edge>{
    private Node startnode;
    private Node endnode;
    private int weight;
    private boolean directed;

    public Edge(Node startnode, Node endnode, int weight, boolean directed) {
        this.startnode = startnode;
        this.endnode = endnode;
        this.weight = weight;
        this.directed = directed;
    }

    public Edge(Node startnode, Node endnode, int weight) {
        this.startnode = startnode;
        this.endnode = endnode;
        this.weight = weight;
    }

    public Edge(Node startnode, Node endnode) {
        this.startnode = startnode;
        this.endnode = endnode;
    }

    public Node getStartnode() {
        return startnode;
    }

    public void setStartnode(Node startnode) {
        this.startnode = startnode;
    }

    public Node getEndnode() {
        return endnode;
    }

    public void setEndnode(Node endnode) {
        this.endnode = endnode;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isDirected() {
        return directed;
    }

    public void setDirected(boolean directed) {
        this.directed = directed;
    }

    @Override
    public int compareTo(Edge o)
    {
        if(this.getStartnode().compareTo(o.getStartnode()) == 0)
        {
            return this.getEndnode().compareTo(o.getEndnode());
        }
        return this.getStartnode().compareTo(o.getStartnode());
    }
}
