package m1graf2020;

public class Main {
    public static void main(String[] args) throws Exceptiongraf {
        System.out.println(">>>>>>>> Creating the subject example graph in G");
        Graf g = new Graf(2, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0);
        System.out.println("Nbr noued : " + g.nbNodes());
        //////////////
        System.out.println("getNodes => Name :" + g.getNode(3).getName() + " ; ID : " + g.getNode(3).getId());
        Node test_node = new Node(9, "NODE9");
        g.addNode(test_node);
        System.out.println("Nbr noued : " + g.nbNodes());
        g.removeNode(test_node);
        System.out.println("Nbr noued : " + g.nbNodes());
        g.removeNode(1);
        System.out.println("Nbr noued : " + g.nbNodes());
        System.out.print("Successors of 4 => : ");
        for (Node node : g.getSuccessors(4)) {
            System.out.print(node.getId() + " , ");
        }
        System.out.println();
        System.out.println("7 And 2 are adjacent ? : " + g.adjacent(7, 2));
        System.out.print("Get All Nodes : [");
        for (int i = 0; i < g.nbNodes(); i++) {
            System.out.print(g.getAllNodes().get(i).getId() + " , ");
        }
        System.out.println("]");
        System.out.println("Existe edge entre 4,2 ?" + g.existsEdge(4, 2));
        g.addEdge(2, 7);
        System.out.println("Add edge from 2 -> 7");
        g.addEdge(7, 7);
        System.out.println("Add edge from 7 -> 7");
        System.out.print("Successors of 2 => : ");
        for (Node node : g.getSuccessors(2)) {
            System.out.print(node.getId() + " , ");
        }
        System.out.println();
        System.out.print("OutEdges of 4 => : ");
        for (Edge edges : g.getOutEdges(4)) {
            System.out.print("( " + edges.getStartnode().getId() + " , " + edges.getEndnode().getId() + " ) ; ");
        }
        System.out.println();
        System.out.print("InEdges of 2 => : ");
        for (Edge edges : g.getInEdges(2)) {
            System.out.print("( " + edges.getStartnode().getId() + " , " + edges.getEndnode().getId() + " ) ; ");
        }
        System.out.println();
        System.out.print("Incident Edges of 2 => : ");
        for (Edge edges : g.getIncidentEdges(2)) {
            System.out.print("( " + edges.getStartnode().getId() + " , " + edges.getEndnode().getId() + " ) ; ");
        }
        System.out.println();
        System.out.print("All Edges of graphe (with function getAllEdges) => : ");
        for (Edge edges : g.getAllEdges()) {
            System.out.print("( " + edges.getStartnode().getId() + " , " + edges.getEndnode().getId() + " ) ; ");
        }
        System.out.println();
        System.out.print("All Edges of graphe => : ");
        for (Edge edges : g.EdgeList) {
            System.out.print("( " + edges.getStartnode().getId() + " , " + edges.getEndnode().getId() + " ) ; ");
        }
        System.out.println();
        System.out.println("InDegree of Node 2 => : " + g.inDegree(2));
        System.out.println("OutDegree of Node 4 => : " + g.outDegree(4));
        System.out.println("Degree of Node 7 => : " + g.degree(7));
        Graf reverse = g.getReverse();
        System.out.print("All Edges of Reverse graphe => : ");
        for (Edge edges : reverse.EdgeList) {
            System.out.print("( " + edges.getStartnode().getId() + " , " + edges.getEndnode().getId() + " ) ; ");
        }
        System.out.println();
        System.out.println("Adjacency matrix of Graf g: ");
        int[][] adjmatrix = g.toAdjMatrix();
        for (int i = 0; i < adjmatrix.length; i++) {
            for (int j = 0; j < adjmatrix.length; j++) {
                System.out.print(" " + adjmatrix[i][j] + " ");
            }
            System.out.println();
        }
        int[] SuccessorArray = g.toSuccessorArray();

        System.out.println("Adjacency matrix of Transitive Graf : ");
        int[][] Transitiveadjmatrix = g.getTransitiveClosure().toAdjMatrix();
        for (int i = 0; i < Transitiveadjmatrix.length; i++) {
            for (int j = 0; j < Transitiveadjmatrix.length; j++) {
                System.out.print(" " + Transitiveadjmatrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.print("Successor Array of Graf : ");
        int[] SA = g.toSuccessorArray();
        for (int i : SA) {
            System.out.print(SA[i] + " ");
        }
        System.out.println();
    }
}
