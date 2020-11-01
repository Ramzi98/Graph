package m1graf2020;

import java.io.*;
import java.util.*;

/**
 * The Graf class, it represent the graph and the creation of the nodes, edges and manipulation of directed graph
 * @author Ramzi
 */
public class Graf {
    Map<Node, List<Node>> adjList = new HashMap<>();
    List<Edge> EdgeList = new ArrayList<>();
    private boolean weighted;

    /**
     * the first constructor is a constructor of an empty symmetric graph
     */
    public Graf() {
        this.weighted = false;
        this.adjList = new HashMap<>();
        this.EdgeList = new ArrayList<>();
    }

    /**
     * the second constructor with a boolean value to allow the user to choose if it's a symmetric of weighted graf
     *
     * @param weighted true if it's weighted graph, false else
     */
    public Graf(boolean weighted) {
        // we give the choice to the user to determine if the graph is weighted ou symmetric
        this.weighted = weighted;
        this.adjList = new HashMap<>();
        this.EdgeList = new ArrayList<>();
    }

    /***
     * Constructor of a graph
     *
     * @param adjList map with node and list of node
     */
    public Graf(Map<Node, List<Node>> adjList) {
        this.adjList = new HashMap<>();
    }

    /***
     * Constructor who create a graph with nodes and edges with successor array formalism
     *
     * @param Nodes list of node with successor array formalism
     */
    public Graf(int... Nodes) {
        this.weighted = false;
        int sourceNoude = 0;
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

    // ****************************          Nodes           ************************************************/

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
        }
        /*else {
            System.out.println("Node : " + id + "add wit function getNode Does't existe in list of Nodes before ");
            //addNode(new Node(id, ""));
        }
        */
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
            //System.out.println("Noued add with function(addNode) => Name : " + n.getName() + " ; ID : " + n.getId());
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

        List<Edge> NewEdgeList= new ArrayList<>();
        for (Edge e : EdgeList) {
            if (!(e.getEndnode().equals(n)) && !(e.getStartnode().equals(n)))
            {
                NewEdgeList.add(e);
            }
        }
        adjList.remove(n);
        EdgeList = NewEdgeList;
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
            if (nodes.getKey().equals(n)) {
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
                if (node.equals(v)) {
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
        List<Node> AllNodes = new ArrayList<>();
        for (Map.Entry<Node, List<Node>> nodes : adjList.entrySet()) {
            AllNodes.add(nodes.getKey());
        }
        return AllNodes;
    }

    // ******************************          Edges           ************************************************/

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
            for (Node node : list) {
                if (node.equals(e.getEndnode())) {
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
            for (Node node : list) {
                if (node.equals(v)) {
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
        if(!(list == null))
        {
            for (Node node : list) {
                //pour voir s'il existe un edge entre les nodes qui ont les ids idn1 et idn2
                if (node.getId() == idn2) {
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
        if(!this.adjList.containsKey(from))
        {
            this.adjList.put(from,new ArrayList<>());
        }

        // if the node to exist
        if(!this.adjList.containsKey(to))
        {
            this.adjList.put(to,new ArrayList<>());
        }

        // if the node to is already in the list of target of from ( so the edge already exist)

        // Enlver if pour que add multiple edges ca marche
        //if (!this.adjList.get(from).contains(to))
        //{
            // we add it if it doesn't exist

            this.adjList.get(from).add(to);

            // We add an edge also in the edges array list, if the graph is weighted, we supposed that the weight is 0
            this.EdgeList.add(new Edge(from,to));

        //}


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
            List<Edge> NewEdgeList= new ArrayList<>();
            for (Edge e : EdgeList) {
                if (!(e.getEndnode().equals(to)) && !(e.getStartnode().equals(from)))
                {
                    NewEdgeList.add(e);
                }
            }
            EdgeList = NewEdgeList;
        }


    }


    /***
     * Function who return the list of out edge of node n
     *
     * @param n the node where we want to know the out edge
     * @return the list of out edge of node n
     */
    List<Edge> getOutEdges(Node n) {
        List<Edge> edges = new ArrayList<>();
        if (existsNode(n)) {
            List<Node> successors = getSuccessors(n);
            for (Node successor : successors) {
                Edge edge = new Edge(n, successor);
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
        List<Edge> edges = new ArrayList<>();
        if (existsNode(n)) {
            for (Node node : nodes) {
                successors = getSuccessors(node);
                for (Node successor : successors) {
                    if (n.equals(successor)) {
                        Edge edge = new Edge(node, n);
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
        alledges.addAll(Inedges);
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
        List<Edge> alledges = new ArrayList<>();
        List<Node> nodes = getAllNodes();
        List<Edge> edgeList;
        for (Node node : nodes) {
            edgeList = getOutEdges(node);
            alledges.addAll(edgeList);

        }
        EdgeList = alledges;
        return alledges;
    }

    // ******************************          Degrees           ************************************************/

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

    // ******************************           Graph Representation           ************************************************/

    /***
     * Function who realize a successor array
     *
     * @return an array for obtaining a representation of the graph in the successor array formalism
     */
    int[] toSuccessorArray() {
        sortMapNodeByKey();
        int nb_edge = nbEdges();
        int taille = nb_edge + nbNodes();
        int[] SA = new int[taille];
        int j = 0;
        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            int size = entry.getValue().size();
            for (int i = 0; i < size; i++) {
                SA[j] = entry.getValue().get(i).getId();
                j++;
            }
            SA[j] = 0;
            j++;
        }
        return  SA;
    }

    /***
     * Function who create an adjacency matrix with the graph given
     *
     * @return the matrix of the given graph
     */
    int[][] toAdjMatrix() {
        sortMapNodeByKey();
        List<Edge> edges = getAllEdges();
        List<Node> nodes = getAllNodes();
        int nbr_node = nbNodes();
        int[][] adjMatrix = new int[nbr_node][nbr_node];
        int [] successorArray = toSuccessorArray();
        int SAlength = successorArray.length;
        //initialisation
        for (int i = 0; i < nbr_node; i++) {
            for (int j = 0; j < nbr_node; j++) {
                adjMatrix[i][j] = 0;
            }
        }
        int sourcenode = 1;
        for (int i = 0; i < SAlength ; i++) {
            if(successorArray[i] == 0)
            {
                sourcenode++;
            }
            else
            {
                adjMatrix[sourcenode-1][successorArray[i]-1]++;
            }
        }

        return adjMatrix;
    }


    /***
     * Function who sort one list by number
     * @param n the list of Nodes to be sorted
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
        Map<Node, List<Node>> lmap = new TreeMap<>(
                new Comparator<>() {
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
        Collections.sort(listEdge, new Comparator<>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.compareTo(o2);
            }
        });
        return listEdge;
    }


    // ******************************           Graph Transformation           ************************************************/

    /***
     *  Function who realize a new reversed graph with one graph given
     *
     * @return the reverse graph given
     */
    Graf getReverse() {
        //pour calculer le nouveau graph inverse
        Graf reverseGraf = new Graf();
        Map<Node, List<Node>> newadjList = new HashMap<>();
        List<Edge> edges;

        for (Map.Entry<Node, List<Node>> node : adjList.entrySet()) {
            List<Node> adjacentsList = new ArrayList<>();
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
                List<Edge> edges = new ArrayList<>();
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
     * @param adjMatrix an adjacency matrix
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


    // ******************************           Graph Traversal           ************************************************/

    /**
     * the method that allow the user to get the Depth First Search ( DFS ) of a graph
     *
     * @return a list of Node that represent the DFS algorithm
     */
    public List<Node> getDFS() {
        List<Node> list = new ArrayList<>();
        LinkedList<Node> Pile = new LinkedList<>();
        Boolean[] visited = new Boolean[this.getAllNodes().size()];
        List<Node> Nodes = this.getAllNodes();
        Arrays.fill(visited, false);
        while (!Nodes.isEmpty()) {
            Pile.addFirst(this.getAllNodes().get(0));
            visited[Nodes.get(0).getId()] = true;
            list.add(Nodes.get(0));
            Nodes.remove(0);
            while (!Pile.isEmpty()) {
                Node n = Pile.getFirst();
                boolean changed = false;
                for (Node x : this.getSuccessors(n)) {
                    if (!visited[x.getId()]) {
                        visited[x.getId() ] = true;
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
        List<Node> list = new ArrayList<>();
        LinkedList<Node> Queue = new LinkedList<>();
        Boolean[] visited = new Boolean[this.getAllNodes().size()];
        List<Node> Nodes = this.getAllNodes();

        Arrays.fill(visited, false);

        while (!Nodes.isEmpty()) {
            Queue.add(Nodes.get(0));
            visited[Nodes.get(0).getId() ] = true;
            list.add(Nodes.get(0));
            Nodes.remove(0);
            while (!Queue.isEmpty()) {

                Node n = Queue.get(0);
                for (Node x : this.getSuccessors(n)
                ) {
                    if (!visited[x.getId()]) {
                        visited[x.getId() ] = true;
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

    // ******************************            Graph Export           ************************************************/

    /***
     * Function who create a dot format of a graph given
     *
     * @return a string of a dot representation
     */
    String toDotString() {
        String dotStringGraph = "digraph g {\n";


        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            int nodeFrom = entry.getKey().getId();
            if(entry.getValue().size()==0)
            {
                dotStringGraph += " " + nodeFrom + ";\n";
            }
            else
            {

                //int i =0;
                //dotStringGraph += " " + nodeFrom + " -> " ;
                for (Node nod : entry.getValue()) {
                    int nodeto = nod.getId();
                    dotStringGraph += " " + nodeFrom + " -> " + nodeto + "; \n";
                }
                /*
                    i++;

                    if(i == entry.getValue().size())
                    {
                        dotStringGraph += " " + nodeto;
                    }
                    else
                    {
                        dotStringGraph += " " + nodeto + ",";
                    }
                }
                dotStringGraph += ";\n";

                 */
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
        File f = new File( name + ".dot");
        FileWriter fw = new FileWriter(f);
        fw.write(this.toDotString());
        fw.close();

    }
    /***
     * Overloding toDotFile with no param name
     * Function who exporting the graph as a file in the DOT syntax
     */
    public void toDotFile() {
        String pathOfFileOutput = System.getProperty("user.dir") + "/" + "graph.dot"; //Current directory
        try {
            File ff = new File(pathOfFileOutput);
            ff.createNewFile();
            FileWriter ffw = new FileWriter(ff);
            try {
                ffw.write(toDotString());
            } finally {
                ffw.close();
            }
        } catch (Exception e) {
            System.out.println("Could not create file");
        }
    }


    /***
     * Function who read a file with dot formalism and create a graph
     *
     * @param path of the dot file
     * @return a graph
     */
    public static Graf DotFileToGraph(String path) {
        List<String> listDot = new  ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                listDot.add(line);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Could not open the dot file.");
        }

        int nbLineDotFile = listDot.size();
        Graf g = null;


        for (int i = 0; i < nbLineDotFile; i++) {
            if (listDot.get(i).equals("\n")) {
                continue;
            }
            if (i == 0) {
                String[] line1 = listDot.get(i).split(" ");
                if (line1[0].equals("digraph")) {
                    g = new Graf();
                }
                if (line1[0].equals("graph")) {
                    g = new UndirectedGraf();
                }
            }
            String[] arrOfStr = listDot.get(i).split(" ");
            if (i != 0 && i != nbLineDotFile-1) {
                if (arrOfStr.length < 3) {
                    String [] arrOfStrNode = arrOfStr[1].split(";");
                    Node n1 = new Node(Integer.parseInt(arrOfStrNode[0]));
                    g.addNode(n1);
                }
                if (arrOfStr.length >= 3) {
                    String [] arrOfStrEdge = arrOfStr[3].split(";");
                    Node n1 = new Node(Integer.parseInt(arrOfStr[1]));
                    Node n2 = new Node(Integer.parseInt(arrOfStrEdge[0]));
                    g.addNode(n1);
                    g.addNode(n2);
                    g.addEdge(n1, n2);
                }
            }
        }

        return g;
    }

    /***
     * Create a PDF Image of a given graph with the DOT file
     */
    public void DotFileToPDFImage(String graphname) throws IOException {
        toDotFile(graphname);
        System.out.println(graphname);
        try {
            Runtime.getRuntime().exec("dot -Tpdf .DOT/" +graphname+".dot -o graph.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


