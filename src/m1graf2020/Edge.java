package m1graf2020;

public class Edge implements Comparable<Edge>{
    private int startnode;
    private int endnode;

    public Edge(int startnode, int endnode) {
        this.startnode = startnode;
        this.endnode = endnode;
    }

    public int getStartnode() {
        return startnode;
    }

    public void setStartnode(int startnode) {
        this.startnode = startnode;
    }

    public int getEndnode() {
        return endnode;
    }

    public void setEndnode(int endnode) {
        this.endnode = endnode;
    }

    @Override
    public int compareTo(Edge o) {
        return 0;
    }
}
