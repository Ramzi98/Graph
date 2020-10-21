package m1graf2020;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Graf {
    Map<Node, List<Node>> adjList = new HashMap<Node, List<Node>>();
    List<Edge> EdgeList = new ArrayList<Edge>();
    private boolean weighted;

    /**
     * the first constructor is a constructor of an empty symmetric graph
     */
    public Graf() {
        this.weighted = false;
        this.adjList = new HashMap<Node, List<Node>>();
        this.EdgeList = new ArrayList<Edge>();
    }

    /**
     * the second constructor with a boolean value to allow the user to choose if it's a symmetric of weighted graf
     *
     * @param weighted true if it's weighted graph, false else
     */
    public Graf(boolean weighted) {
        // we give the choice to the user to determine if the graph is weighted ou symmetric
        this.weighted = weighted;
        this.adjList = new HashMap<Node, List<Node>>();
        this.EdgeList = new ArrayList<Edge>();
    }

    /***
     * Constructor of a graph
     *
     * @param adjList map with node and list of node
     */
    public Graf(Map<Node, List<Node>> adjList) {
        this.adjList = new HashMap<Node, List<Node>>();
    }

    /***
     * Constructor who create a graph with nodes and edges with successor array formalism
     *
     * @param Nodes list of node with successor array formalism
     */
    public Graf(int... Nodes) {
        this.weighted = false;
        int sourceNoude = 1;
        for (int currentNode : Nodes) {
            if (currentNode == 0) {
                if (!existsNode(sourceNoude)) {
                    List<Node> emptyList = new ArrayList<>();
                    adjList.put(new Node(sourceNoude, ""), emptyList);
                }
                sourceNoude++;
                continue;
            }
            if (!existsNode(sourceNoude)) {
                List<Node> emptyList = new ArrayList<>();
                Node newNoued = new Node(currentNode, "");
                emptyList.add(newNoued);
                adjList.put(new Node(sourceNoude, ""), emptyList);
            } else {
                List<Node> newList = new ArrayList<>();
                Node newNoued = new Node(currentNode, "");
                Node node = getNode(sourceNoude);
                for (Node n : getSuccessors(node)) {
                    newList.add(n);
                }
                newList.add(newNoued);
                adjList.put(node, newList);
            }
        }
    }

    /**
     * the getter of the private attribute weighted
     *
     * @return the value of weighted
     */
    public boolean isWeighted() {
        return weighted;
    }

    /**
     * the setter of the private attribute weighted, it can be modified only when the graph is empty
     *
     * @param weighted the new value of weighted
     * @throws Exceptiongraf don't allow the user to change the type of an no empty graph
     */
    public void setWeighted(boolean weighted) throws Exceptiongraf {
        if (adjList.isEmpty())
            this.weighted = weighted;
        else {
            throw new Exceptiongraf("The graph is not empty, impossible to change his type");
        }
    }

    /******************************          Nodes           ************************************************/

    /***
     * Function who return the number of node in graph
     *
     * @return the number of node in graph
     */
    int nbNodes() {
        return adjList.size();
    }

    /***
     * Function who check if the node already exist
     *
     * @param n the node to control
     * @return a boolean who say the edge exist
     */
    boolean existsNode(Node n) {
        for (Map.Entry<Node, List<Node>> nodes : adjList.entrySet()) {
            if (nodes.getKey().equals(n)) {
                return true;
            }
        }
        return false;

    }

    /***
     * Overload of the existsNode function
     *
     * @param id the id node to control
     * @return a boolean who say the edge exist
     */
    boolean existsNode(int id) {
        for (Map.Entry<Node, List<Node>> nodes : adjList.entrySet()) {
            Node n = nodes.getKey();
            if (n.getId() == id) {
                return true;
            }
        }
        return false;
    }

    /***
     * Function who get back the node with an id
     *
     * @param id the id of node to get
     * @return the Node with the id given in the parameters
     */
    Node getNode(int id) {
        if (existsNode(id)) {
            for (Map.Entry<Node, List<Node>> nodes : adjList.entrySet()) {
                Node n = nodes.getKey();
                if (n.getId() == id) {
                    return n;
                }
            }
        } else {
            System.out.println("Node : " + id + "add wit function getNode Does't existe in list of Nodes before ");
            //addNode(new Node(id, ""));
        }
        return null;
    }

    /***
     * Function who add a node
     *
     * @param n the node added
     */
    void addNode(Node n) {
        if (!existsNode(n)) {
            List<Node> emptyList = new ArrayList<>();
            adjList.put(n, emptyList);
            System.out.println("Noued add with function(addNode) => Name : " + n.getName() + " ; ID : " + n.getId());
        } else {
            System.out.println("Noued existe deja");
        }

    }

    /***
     * Overload of the addNode function
     *
     * @param id the id of node added
     */
    void addNode(int id) {
        Node node = new Node(id);
        addNode(node);
    }

    /***
     * Function who remove one node with one node given in parameters and check if the node exist
     *
     * @param n node who we have to remove
     */
    public void removeNode(Node n) {
        for (Node x : this.getAllNodes()) {
            if (this.getSuccessors(x).contains(n)) adjList.get(x).remove(n);
        }

        for (Edge e : this.EdgeList) {
            if (e.getEndnode().getId() == n.getId() || e.getStartnode().getId() == n.getId())
            {
                this.EdgeList.remove(new Edge(e.getStartnode(),e.getEndnode()));
            }
        }
        adjList.remove(n);
    }


    /***
     * Overload on the removeNode function
     *
     * @param id the id of node who where remove
     */
    void removeNode(int id) {
        Node node = getNode(id);
        removeNode(node);
    }

    /***
     * Function who where we want to know the successors
     *
     * @param n the node where we want to know the successors
     * @return the list of successors of node n
     */
    List<Node> getSuccessors(Node n) {
        for (Map.Entry<Node, List<Node>> nodes : adjList.entrySet()) {
            if (nodes.getKey().getId() == n.getId()) {
                return nodes.getValue();
            }
        }
        return null;
    }

    /***
     * Overload of the getSuccessors function
     *
     * @param id the id of node where we want to know the successors
     * @return the list of successors of node n
     */
    List<Node> getSuccessors(int id) {
        Node n = getNode(id);
        return getSuccessors(n);
    }

    /***
     * Function who check if two nodes u and v are adjacent
     *
     * @param u the 1 st node
     * @param v the 2 nd node
     * @return a boolean who say the Nodes are adjacent
     */
    boolean adjacent(Node u, Node v) {
        if (existsNode(u) && existsNode(v)) {
            List<Node> successors_of_u = getSuccessors(u);
            for (Node node : successors_of_u) {
                if (node.getId() == v.getId()) {
                    return true;
                }
            }
        } else {
            if (!existsNode(u)) {
                System.out.println("Node : " + u.getId() + " dont existe in the listes of nodes");
            } else {
                System.out.println("Node : " + v.getId() + " dont existe in the listes of nodes");
            }
        }
        return false;
    }

    /***
     * Overload of the adjacent function
     *
     * @param idn1 the id of the 1 st node
     * @param idn2 the id of the 2 nd node
     * @return a boolean who say the Nodes are adjacent
     */
    boolean adjacent(int idn1, int idn2) {
        Node u = getNode(idn1);
        Node v = getNode(idn2);
        return adjacent(u, v);
    }

    /***
     * Function who return a list of all nodes in graph
     *
     * @return a list of all nodes in graph
     */
    List<Node> getAllNodes() {
        List<Node> AllNodes = new ArrayList<Node>();
        for (Map.Entry<Node, List<Node>> nodes : adjList.entrySet()) {
            AllNodes.add(nodes.getKey());
        }
        return AllNodes;
    }

    /******************************          Edges           ************************************************/

    /***
     * Function who return the number of edge in graph
     *
     * @return the number of edge in graph
     */
    int nbEdges() {
        int nbr = 0;
        for (Map.Entry<Node, List<Node>> nodes : adjList.entrySet()) {
            nbr += nodes.getValue().size();
        }
        System.out.println("edges Number : " + nbr);
        return nbr;
    }

    /***
     * Function who check if the edge already exist
     *
     * @param e the edge to control
     * @return a boolean who say the edge exist
     */
    boolean existsEdge(Edge e) {
        List<Node> list;
        list = getSuccessors(e.getStartnode());
        if (!(list == null)) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId() == e.getEndnode().getId()) {
                    return true;
                }
            }
        }
        return false;
    }

    /***
     * Overload of the existsEdge function
     *
     * @param u the Nodefrom to control
     * @param v the Nodeto to control
     * @return a boolean who say the edge exist
     */
    boolean existsEdge(Node u, Node v) {
        List<Node> list;
        list = getSuccessors(u);
        if (!(list == null)) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId() == v.getId()) {
                    return true;
                }
            }
        }
        return false;
    }

    /***
     * Overload of the existsEdge function
     *
     * @param idn1 the id of Nodefrom to control
     * @param idn2 the id of Nodeto to control
     * @return a boolean who say the edge exist
     */
    boolean existsEdge(int idn1, int idn2) {
        List<Node> list;
        list = getSuccessors(idn1);
        if(!list.equals(null))
        {
            for (int i = 0; i < list.size(); i++) {
                //pour voir s'il existe un edge entre les nodes qui ont les ids idn1 et idn2
                if (list.get(i).getId() == idn2) {
                    return true;
                }
            }
        }
        else
        {
            System.out.println("There is no edge ("+idn1+" , "+idn2+" )");
        }
        return false;
    }

    /**
     * the method that allow us to add an arc between two Nodes using there numbers
     * @param x the number of the source Node
     * @param y the number of the destination Node
     */
    public void addEdge(int x, int y)
    {
        Node from = new Node(x);
        Node to = new Node(y);
        this.addEdge(from,to);

    }

    /**
     * the second method that allow us to add an arc between two Nodes
     * @param from the node source
     * @param to the node destination
     */
    public void addEdge(Node from, Node to)
    {
        // if the node from exist
        if(this.adjList.containsKey(from)==false)
        {
            this.adjList.put(from,new ArrayList<Node>());
        }

        // if the node to exist
        if(!this.adjList.containsKey(to))
        {
            this.adjList.put(to,new ArrayList<Node>());
        }

        // if the node to is already in the list of target of from ( so the edge already exist)

        // Enlver if pour que add multiple edges ca marche
        // if (!this.adjList.get(from).contains(to))
        // {
            // we add it if it doesn't exist

            this.adjList.get(from).add(to);

            // We add an edge also in the edges array list, if the graph is weighted, we supposed that the weight is 0
            this.EdgeList.add(new Edge(from,to));

        // }


    }

    /**
     * the third method that for creating a new arc, but this method is used only for the weighted graph because it require a weight
     * @param x the source number Node
     * @param y the destination number Node
     * @param weight the weight
     */
    public void addEdge(int x, int y,int weight) throws Exceptiongraf
    {
        // if the graph is symmetric
        if (!weighted) {
            throw(new Exceptiongraf("Impossible to add a weight, the graph is symmetric"));
        }
        Node from = new Node(x);
        Node to = new Node(y);
        this.addEdge(from,to, weight);

    }

    /**
     * the fourth method to adding an arc between two nodes, only used for the weighted graph
     * @param from the node source
     * @param to the node destination
     * @param weight the weight of the arc
     */
    public void addEdge(Node from, Node to,int weight) throws Exceptiongraf
    {
        // if the graph is symmetric
        if (!weighted) {
            throw(new Exceptiongraf("Impossible to add a weight, the graph is symmetric"));
        }
        // if the node from exist
        if(!this.adjList.containsKey(from))
        {
            addNode(from);
        }

        // if the node to exist
        if(!this.adjList.containsKey(to))
        {
            addNode(to);
        }

        // if the node to is already in the list of target of from ( so the edge already exist)

        // Enlver if pour que add multiple edges ca marche
        // if (!this.adjList.get(from).contains(to))
        // {
            // we add it if it doesn't exist

            this.adjList.get(from).add(to);
            this.EdgeList.add(new Edge(from,to,weight));

        // }

    }

    /**
     * this method allow the user to remove an Edge from the graph using only the numbers of Nodes
     * @param from the number of the Node source
     * @param to the number of the Node destination
     */
    public void removeEdge(int from,int to){
        this.removeEdge(new Node(from),new Node(to));
    }

    /**
     * the second method that allow the user to remove an Edge grom the graph, it use the Nodes
     * @param from the node source
     * @param to the node destination
     */
    public void removeEdge(Node from, Node to){
        if(existsEdge(from, to)) {
            this.adjList.get(from).remove(to);
        }
        EdgeList.remove(new Edge(from,to));
    }


    /***
     * Function who return the list of out edge of node n
     *
     * @param n the node where we want to know the out edge
     * @return the list of out edge of node n
     */
    List<Edge> getOutEdges(Node n) {
        List<Edge> edges = new ArrayList<Edge>();
        if (existsNode(n)) {
            List<Node> successors = getSuccessors(n);
            for (int i = 0; i < successors.size(); i++) {
                Edge edge = new Edge(n, successors.get(i));
                edges.add(edge);
            }
        }
        return edges;
    }

    /***
     * Overload of the getOutEdge function
     *
     * @param id the node where we want to know the in edge
     * @return the list of in edge of node n
     */
    List<Edge> getOutEdges(int id) {
        Node n = getNode(id);
        return getOutEdges(n);
    }

    /***
     * Function who return the list of in edge of node n
     *
     * @param n the node where we want to know the in edge
     * @return the list of in edge of node n
     */
    List<Edge> getInEdges(Node n) {
        List<Node> nodes = getAllNodes();
        List<Node> successors;
        List<Edge> edges = new ArrayList<Edge>();
        if (existsNode(n)) {
            for (int i = 0; i < nodes.size(); i++) {
                successors = getSuccessors(nodes.get(i));
                for (int j = 0; j < successors.size(); j++) {
                    if (n.getId() == successors.get(j).getId()) {
                        Edge edge = new Edge(nodes.get(i), n);
                        edges.add(edge);
                    }
                }
            }
        } else {
            System.out.println("Node dont exist in list of Nodes");
        }
        return edges;
    }

    /***
     * Overload of the getInEdge function
     *
     * @param id the id of node where we want to know the in edge
     * @return the list of in edge of node n
     */
    List<Edge> getInEdges(int id) {
        Node n = getNode(id);
        return getInEdges(n);
    }

    /***
     * Function who return the list of incident edge of node n
     *
     * @param n the node where we want to know the incident edge
     * @return the list of incident edge of node n
     */
    List<Edge> getIncidentEdges(Node n) {
        //récuperer toutes les nodes qui sort et entre vers le node n
        List<Edge> alledges;// = new ArrayList<Edge>();
        alledges = getOutEdges(n);
        List<Edge> Inedges = getInEdges(n);
        for (int i = 0; i < Inedges.size(); i++) {
            alledges.add(Inedges.get(i));
        }
        return alledges;
    }

    /***
     * Overload of the getIncidentEdges function
     *
     * @param id the node where we want to know the in edge
     * @return the list of in edge of node n
     */
    List<Edge> getIncidentEdges(int id) {
        Node n = getNode(id);
        return getIncidentEdges(n);
    }

    /***
     * Function who return a list of all edges in graph
     *
     * @return a list of all edges in graph
     */
    List<Edge> getAllEdges() {
        List<Edge> alledges = new ArrayList<Edge>();
        List<Node> nodes = getAllNodes();
        List<Edge> edgeList = new ArrayList<Edge>();
        for (int i = 0; i < nodes.size(); i++) {
            edgeList = getOutEdges(nodes.get(i));
            for (Edge e : edgeList) {
                alledges.add(e);
            }

        }
        EdgeList = alledges;
        return alledges;
    }

    /******************************          Degrees           ************************************************/

    /***
     * Function who return the inDegree of Node
     *
     * @param n the node where we want to know the inDegree
     * @return a inDegree of Node n
     */
    int inDegree(Node n) {
        List<Edge> edges = getInEdges(n);
        return edges.size();
    }

    /***
     * Overload of the inDegree function
     *
     * @param id the id of node where we want to know the inDegree
     * @return a inDegree of Node
     */
    int inDegree(int id) {
        Node node = getNode(id);
        return inDegree(node);
    }

    /***
     * Function who return a outDegree of Node
     *
     * @param n the node where we want to know the outDegree
     * @return a outDegree of Node n
     */
    int outDegree(Node n) {
        List<Edge> edges = getOutEdges(n);
        return edges.size();
    }

    /***
     * Overload of the outDegree function
     *
     * @param id the id of node where we want to know the outDegree
     * @return a outDegree of Node
     */
    int outDegree(int id) {
        Node node = getNode(id);
        return outDegree(node);
    }

    /***
     * Function who return a Degree of Node
     *
     * @param n the node where we want to know the Degree
     * @return a Degree of Node n
     */
    int degree(Node n) {
        return inDegree(n) + outDegree(n);
    }

    /***
     * Overload of the Degree function
     *
     * @param id the id of node where we want to know the Degree
     * @return a Degree of Node
     */
    int degree(int id) {
        Node node = getNode(id);
        return degree(node);
    }

    /******************************           Graph Representation           ************************************************/

    /***
     * Function who realize a successor array
     *
     * @return an array for obtaining a representation of the graph in the successor array formalism
     */
    int[] toSuccessorArray() {
        List<Node> nodes = getAllNodes();
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (Node currentnode : nodes) {
            arrayList.add(currentnode.getId());
            for (Node othernode : nodes) {
                if (existsEdge(currentnode, othernode)) {
                    arrayList.add(othernode.getId());
                }
            }
            arrayList.add(0);
        }
        int[] SA = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            SA[i] = arrayList.get(i);
        }
        return SA;
    }

    /***
     * Function who create an adjacency matrix with the graph given
     *
     * @return the matrix of the given graph
     */
    int[][] toAdjMatrix() {
        List<Edge> edges = getAllEdges();
        List<Node> nodes = getAllNodes();
        int startnode, endnode;
        int max = 0, max1 = 0;
        //Recuperation d'ID max
        for (int i = 0; i < (nodes.size() - 1); i++) {
            max1 = Math.max(nodes.get(i).getId(), nodes.get(i + 1).getId());
            if (max < max1) {
                max = max1;
            }
        }
        int[][] adjMatrix = new int[max + 1][max + 1];
        //initialisation
        for (int i = 0; i <= max; i++) {
            for (int j = 0; j <= max; j++) {
                adjMatrix[i][j] = 0;
            }
        }
        for (Edge edge : edges) {
            startnode = edge.getStartnode().getId();
            endnode = edge.getEndnode().getId();
            adjMatrix[startnode][endnode]++;
        }
        return adjMatrix;
    }


    /***
     * Function who sort one list by number
     */
    public void sortListNode(List<Node> n) {
        Collections.sort(n, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.getId() > o2.getId()) {
                    return 1;
                }
                if (o1.getId() < o2.getId()) {
                    return -1;
                }
                return 0;
            }
        });
    }

    /***
     * Function who sort the map adjList by key and sort the list of the associated value
     */
    public void sortMapNodeByKey() {
        Map<Node, List<Node>> current = adjList;
        Map<Node, List<Node>> lmap = new TreeMap<Node, List<Node>>(
                new Comparator<Node>() {
                    @Override
                    public int compare(Node o1, Node o2) {
                        if (o1.getId() > o2.getId()) {
                            return 1;
                        }
                        if (o1.getId() < o2.getId()) {
                            return -1;
                        }
                        return 0;
                    }
                }
        );
        lmap.putAll(current);
        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            sortListNode(entry.getValue());
        }
        adjList = lmap;
    }


    public List<Edge> sortListOfEdge() {
        List<Edge> listEdge = getAllEdges();
        Collections.sort(listEdge, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.compareTo(o2);
            }
        });
        return listEdge;
    }


    /******************************           Graph Transformation           ************************************************/

    /***
     *  Function who realize a new reversed graph with one graph given
     *
     * @return the reverse graph given
     */
    Graf getReverse() {
        //pour calculer le nouveau graph inverse
        Graf reverseGraf = new Graf();
        Map<Node, List<Node>> newadjList = new HashMap<Node, List<Node>>();
        List<Edge> edges;

        for (Map.Entry<Node, List<Node>> node : adjList.entrySet()) {
            List<Node> adjacentsList = new ArrayList<Node>();
            reverseGraf.addNode(node.getKey());
            edges = getInEdges(node.getKey());
            for (Edge e : edges) {
                adjacentsList.add(e.getStartnode());
            }
            newadjList.put(node.getKey(), adjacentsList);
        }
        reverseGraf.adjList = newadjList;
        reverseGraf.EdgeList = reverseGraf.getAllEdges();
        return reverseGraf;
    }

    /**
     * the method that allow the user to get the transitive closure of a graph
     *
     * @return a new graph represent the transitive closure of a graph
     */
    public Graf getTransitiveClosure() throws Exceptiongraf {
        Graf TC = this;

        boolean changed = true;

        while (changed) {
            changed = false;
            for (Node n : TC.getAllNodes())
            {
                List<Edge> edges = new ArrayList<Edge>();
                for (Edge x : TC.getOutEdges(n))
                {
                    for (Edge z : TC.getOutEdges(x.getEndnode()))
                    {
                        if (!TC.adjList.get(n).contains(new Node(z.getEndnode().getId())))
                        {
                            edges.add(new Edge(n, z.getEndnode(), x.getWeight() + z.getWeight()));
                            changed = true;
                        }

                    }

                }
                if(!weighted)
                {
                    for (Edge x : edges)
                    {
                        TC.addEdge(x.getStartnode().getId(), x.getEndnode().getId());
                    }
                }
                else
                {
                    for (Edge x : edges)
                    {
                        TC.addEdge(x.getStartnode().getId(), x.getEndnode().getId(), x.getWeight());
                    }
                }


            }

        }
        return TC;

    }

    /***
     * Function who realize a graph with adjacency matrix
     *
     * @param adjMatrix
     * @return a graph with one adjacency matrix given in parameters
     */
    public Graf adjMatrixToGraf(int[][] adjMatrix) {
        Graf g = new Graf();
        int lgtAdjMatrix = adjMatrix.length;
        for (int i = 0; i < lgtAdjMatrix; i++) {
            for (int j = 0; j < lgtAdjMatrix; j++) {
                if (adjMatrix[i][j] == 1) {
                    Node n1 = new Node(i);
                    Node n2 = new Node(j);
                    g.addEdge(n1, n2);
                }
            }
        }
        return g;
    }


    /******************************           Graph Traversal           ************************************************/

    /**
     * the method that allow the user to get the Depth First Search ( DFS ) of a graph
     *
     * @return a list of Node that represent the DFS algorithm
     */
    public List<Node> getDFS() {
        List<Node> list = new ArrayList<Node>();
        LinkedList<Node> Pile = new LinkedList<Node>();
        Boolean[] visited = new Boolean[this.getAllNodes().size()];
        List<Node> Nodes = this.getAllNodes();
        Arrays.fill(visited, false);
        while (!Nodes.isEmpty()) {
            Pile.addFirst(this.getAllNodes().get(0));
            visited[Nodes.get(0).getId() - 1] = true;
            list.add(Nodes.get(0));
            Nodes.remove(0);
            while (!Pile.isEmpty()) {
                Node n = Pile.getFirst();
                boolean changed = false;
                for (Node x : this.getSuccessors(n)) {
                    if (!visited[x.getId() - 1]) {
                        visited[x.getId() - 1] = true;
                        Pile.addFirst(x);
                        Nodes.remove(x);
                        list.add(x);
                        changed = true;
                        break;
                    }
                }
                if (!changed) {
                    Pile.removeFirst();
                }


            }
        }
        return list;

    }

    /**
     * this method allow the user to get the Breadth-first search of the graph ( BFS )
     *
     * @return a list of node that represent the BFS
     */
    public List<Node> getBFS() {
        List<Node> list = new ArrayList<Node>();
        LinkedList<Node> Queue = new LinkedList<Node>();
        Boolean[] visited = new Boolean[this.getAllNodes().size()];
        List<Node> Nodes = this.getAllNodes();

        Arrays.fill(visited, false);

        while (!Nodes.isEmpty()) {
            Queue.add(Nodes.get(0));
            visited[Nodes.get(0).getId() - 1] = true;
            list.add(Nodes.get(0));
            Nodes.remove(0);
            while (!Queue.isEmpty()) {

                Node n = Queue.get(0);
                for (Node x : this.getSuccessors(n)
                ) {
                    if (!visited[x.getId() - 1]) {
                        visited[x.getId() - 1] = true;
                        Queue.add(x);
                        list.add(x);
                        Nodes.remove(x);
                    }

                }
                Queue.remove(n);

            }
        }

        return list;

    }

    /******************************            Graph Export           ************************************************/

    /***
     * Function who create a dot format of a graph given
     *
     * @return a string of a dot representation
     */
    String toDotString() {
        String dotStringGraph = "digraph g {\n";


        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            int nFrom = entry.getKey().getId();
            for (Node nod : entry.getValue()) {
                int nto = nod.getId();
                dotStringGraph += " " + nFrom + " -> " + nto + ";\n";
            }
        }

        dotStringGraph += "}";
        return  dotStringGraph;
    }

    /**
     * this method convert the graph into DOT format and stock it in a the DOT repository
     *
     * @param name the name of the file .dot
     * @throws IOException the exception for manipulating the files
     */
    public void toDotFile(String name) throws IOException {
        File f = new File("DOT/" + name + ".dot");
        FileWriter fw = new FileWriter(f);
        fw.write(this.toDotString());
        fw.close();

    }
}


