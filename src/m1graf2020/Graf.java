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
     * The first constructor is a constructor of an empty symmetric graph
     */
    public Graf() {
        this.weighted = false;
        this.adjList = new HashMap<>();
        this.EdgeList = new ArrayList<>();
    }

    /**
     * The second constructor with a boolean value to allow the user to choose if it's a symmetric of weighted graf
     * @param weighted true if it's weighted graph, false else
     */
    public Graf(boolean weighted) {
        // we give the choice to the user to determine if the graph is weighted ou symmetric
        this.weighted = weighted;
        this.adjList = new HashMap<>();
        this.EdgeList = new ArrayList<>();
    }

    /***
     * Constructor of a graph with adjlist given in parameters
     * @param adjList map with node and list of node
     */
    public Graf(Map<Node, List<Node>> adjList) {
        this.adjList = new HashMap<>();
    }

    /***
     * Constructor who create a graph with nodes and edges with successor array formalism
     * @param Nodes list of node with successor array formalism
     */
    public Graf(int... Nodes) {
        System.out.println("hi");
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
     * Method that return the number of node in graph
     * @return the number of node in graph
     */
    int nbNodes() {
        return adjList.size();
    }

    /***
     * Method that check if the node already exist
     * @param n the node to control
     * @return a boolean who say the edge exist or not
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
     * Overload of the existsNode Method
     * @param id the id node to control
     * @return a boolean who say the edge exist or not
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
     * Method that get back the node with an id
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
        return null;
    }

    /***
     * Method that add a node to the graph
     * @param n the node to be added
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
     * Overload of the addNode Method
     * @param id the id of node added
     */
    void addNode(int id) {
        Node node = new Node(id);
        addNode(node);
    }

    /***
     * Method that remove one node given in parameters
     * @param n node that we have to remove
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
     * Overload on the removeNode Method
     * @param id the id of node who where remove
     */
    void removeNode(int id) {
        Node node = getNode(id);
        removeNode(node);
    }

    /***
     * Method that where we want to know the successors
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
     * Overload of the getSuccessors Method
     * @param id the id of node where we want to know the successors
     * @return the list of successors of node n
     */
    List<Node> getSuccessors(int id) {
        Node n = getNode(id);
        return getSuccessors(n);
    }

    /***
     * Method That check if two nodes u and v are adjacent
     * @param u the 1 st node
     * @param v the 2 nd node
     * @return a boolean who say the Nodes are adjacent or not
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
     * Overload of the adjacent Method
     * @param idn1 the id of the 1 st node
     * @param idn2 the id of the 2 nd node
     * @return a boolean who say the Nodes are adjacent or not
     */
    boolean adjacent(int idn1, int idn2) {
        Node u = getNode(idn1);
        Node v = getNode(idn2);
        return adjacent(u, v);
    }

    /***
     * Method that return a list of all nodes in graph
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
     * Method that return the number of edge in graph
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
     * Method that check if the edge already exist
     * @param e the edge to control
     * @return a boolean who say that edge exist or not
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
     * Overload of the existsEdge Method
     * @param u the Nodefrom to control
     * @param v the Nodeto to control
     * @return a boolean who say that edge exist or not
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
     * Overload of the existsEdge Method
     * @param idn1 the id of Nodefrom to control
     * @param idn2 the id of Nodeto to control
     * @return a boolean who say that edge exist or not
     */
    boolean existsEdge(int idn1, int idn2) {
        List<Node> list;
        list = getSuccessors(idn1);
        if(!(list == null))
        {
            for (Node node : list) {
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
     * Method that allow us to add an arc between two Nodes using there numbers
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
     * Method that allow us to add an arc between two Nodes
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

        this.adjList.get(from).add(to);
            // We add an edge also in the edges array list, if the graph is weighted, we supposed that the weight is 0
        this.EdgeList.add(new Edge(from,to));


    }

    /**
     * Method that for creating a new arc, but this method is used only for the weighted graph because it require a weight
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
     * Method to add an arc between two nodes, only used for the weighted graph
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

        this.adjList.get(from).add(to);
        this.EdgeList.add(new Edge(from,to,weight));


    }

    /**
     * Method that allow the user to remove an Edge from the graph using only the numbers of Nodes
     * @param from the number of the Node source
     * @param to the number of the Node destination
     */
    public void removeEdge(int from,int to){
        this.removeEdge(new Node(from),new Node(to));
    }

    /**
     * Method that allow the user to remove an Edge grom the graph, it use the Nodes
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
     * Method that return the list of out edge of node n
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
     * Overload of the getOutEdge Method
     * @param id the node where we want to know the in edge
     * @return the list of in edge of node n
     */
    List<Edge> getOutEdges(int id) {
        Node n = getNode(id);
        return getOutEdges(n);
    }

    /***
     * Method That return the list of inedges of node n
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
     * Overload of the getInEdge Method
     * @param id the id of node where we want to know the in edge
     * @return the list of in edge of node n
     */
    List<Edge> getInEdges(int id) {
        Node n = getNode(id);
        return getInEdges(n);
    }

    /***
     * Method That return the list of incident edge of node n
     * @param n the node where we want to know the incident edge
     * @return the list of incident edge of node n
     */
    List<Edge> getIncidentEdges(Node n) {
        List<Edge> alledges;
        alledges = getOutEdges(n);
        List<Edge> Inedges = getInEdges(n);
        alledges.addAll(Inedges);
        return alledges;
    }

    /***
     * Overload of the getIncidentEdges Method
     * @param id the node where we want to know the in edge
     * @return the list of in edge of node n
     */
    List<Edge> getIncidentEdges(int id) {
        Node n = getNode(id);
        return getIncidentEdges(n);
    }

    /***
     * Method That return the list of all edges in graph
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
     * Method that return the inDegree of Node
     * @param n the node where we want to know the inDegree
     * @return a inDegree of Node n
     */
    int inDegree(Node n) {
        List<Edge> edges = getInEdges(n);
        return edges.size();
    }

    /***
     * Overload of the inDegree Method
     * @param id the id of node where we want to know the inDegree
     * @return a inDegree of Node
     */
    int inDegree(int id) {
        Node node = getNode(id);
        return inDegree(node);
    }

    /***
     * Method that return a outDegree of Node
     * @param n the node where we want to know the outDegree
     * @return a outDegree of Node n
     */
    int outDegree(Node n) {
        List<Edge> edges = getOutEdges(n);
        return edges.size();
    }

    /***
     * Overload of the outDegree Method
     * @param id the id of node where we want to know the outDegree
     * @return a outDegree of Node
     */
    int outDegree(int id) {
        Node node = getNode(id);
        return outDegree(node);
    }

    /***
     * Method that return a Degree of Node
     * @param n the node where we want to know the Degree
     * @return a Degree of Node n
     */
    int degree(Node n) {
        return inDegree(n) + outDegree(n);
    }

    /***
     * Overload of the Degree Method
     * @param id the id of node where we want to know the Degree
     * @return a Degree of Node
     */
    int degree(int id) {
        Node node = getNode(id);
        return degree(node);
    }

    // ******************************           Graph Representation           ************************************************/

    /***
     * Method That generates a successor array
     * @return an array representation of the graph
     */
    int[] toSuccessorArray() {
        int taille = nbEdges() + nbNodes();
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
     * Method that create an adjacency matrix with the graph given
     * @return the Adjacency matrix of the given graph
     */
    int[][] toAdjMatrix() {
        Collections.sort(getAllNodes());
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

    // ******************************           Graph Transformation           ************************************************/

    /***
     * Method that generates a new reversed graph from a given graph
     * @return a new graph represent the reverse of graph given
     */
    Graf getReverse() {
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
     * Method that allow the user to get the transitive closure of a graph
     * @return a new graph represent the transitive closure of a graph
     */
    public Graf getTransitiveClosure() throws Exceptiongraf {
        Graf TC = new Graf();
        for(Edge edge : getAllEdges())
        {
            TC.addEdge(edge.getStartnode(),edge.getEndnode());
        }

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

        Graf TC1 = new Graf();
        for(Node node : getAllNodes())
        {
            List<Node> sucWithoutDuplicates = new ArrayList<>(new HashSet<>(TC.getSuccessors(node)));
            for (Node n : sucWithoutDuplicates)
            {
                TC1.addEdge(node,n);
                int occurrences = Collections.frequency(this.getSuccessors(node), n);
                for (int i = 0; i < occurrences-1; i++) {
                    TC1.addEdge(node,n);
                }

            }

        }
        Collections.sort(TC1.getAllEdges());
        return TC1;

    }

    // ******************************           Graph Traversal           ************************************************/

    /**
     * Method that allow the user to get the Depth First Search ( DFS ) of a graph
     * @return a list of Node that represent the DFS algorithm
     */
    public List<Node> getDFS() {
        List<Node> nodes = getAllNodes();
        int max = 0, max1;
        //Recuperation d'ID max
        for (int i = 0; i < (nodes.size() - 1); i++) {
            max1 = Math.max(nodes.get(i).getId(), nodes.get(i + 1).getId());
            if (max < max1) {
                max = max1;
            }
        }

        List<Node> list = new ArrayList<>();
        LinkedList<Node> Pile = new LinkedList<>();
        Boolean[] visited = new Boolean[max+1];
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
     * Method that allow the user to get the Breadth-first search of the graph ( BFS )
     * @return a list of node that represent the BFS
     */
    public List<Node> getBFS() {
        List<Node> nodes = getAllNodes();
        int max = 0, max1;
        //Recuperation d'ID max
        for (int i = 0; i < (nodes.size() - 1); i++) {
            max1 = Math.max(nodes.get(i).getId(), nodes.get(i + 1).getId());
            if (max < max1) {
                max = max1;
            }
        }

        List<Node> list = new ArrayList<>();
        LinkedList<Node> Queue = new LinkedList<>();
        Boolean[] visited = new Boolean[max+1];
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
                Collections.sort(entry.getValue());
                for (Node nod : entry.getValue()) {
                    int nodeto = nod.getId();
                    dotStringGraph += " " + nodeFrom + " -> " + nodeto + "; \n";
                }
            }
        }

        dotStringGraph += "}";
        return  dotStringGraph;
    }

    /***
     * Method that read a dot file and generates a graph equivalent
     * @param path the absolute path of the dot file
     * @return a graph
     */
    public static Graf DotFileToGraph(String path) {
        List<String> list = new  ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int nbr_line = list.size();
        Graf g = null;

        for (int i = 0; i < nbr_line; i++) {
            if (list.get(i).equals("\n")) {
                continue;
            }
            if (i == 0) {
                String[] line1 = list.get(i).split(" ");
                if (line1[0].equals("digraph")) {
                    g = new Graf();
                }
                if (line1[0].equals("graph")) {
                    g = new UndirectedGraf();
                }
            }
            String[] Str = list.get(i).split(" ");
            if (i != 0 && i != nbr_line-1) {
                if (Str.length < 3) {
                    String [] Node1 = Str[1].split(";");
                    Node n1 = new Node(Integer.parseInt(Node1[0]));
                    g.addNode(n1);
                }
                if (Str.length >= 3) {
                    String [] Node2 = Str[3].split(";");
                    Node n1 = new Node(Integer.parseInt(Str[1]));
                    Node n2 = new Node(Integer.parseInt(Node2[0]));
                    g.addNode(n1);
                    g.addNode(n2);
                    g.addEdge(n1, n2);
                }
            }
        }
        return g;
    }
    /**
     * Method that convert the graph into DOT format and stock it in a the DOT repository
     * @param name the name of the file .dot
     * @throws IOException the exception for manipulating the files
     */

    public void toDotFile(String name) throws IOException {
        String workingDir = System.getProperty("user.dir");
        String dir = workingDir+"\\DOT";
        File file = new File(dir);
        file.mkdirs();
        File f = new File( dir+"\\"+name + ".dot");
        FileWriter fw = new FileWriter(f);
        fw.write(this.toDotString());
        fw.close();

    }


    /***
     * Create a PDF Image of a graph with there DOT file
     * @param graphname the name of the graph
     */
    public void DotFileToPDF(String graphname) throws IOException {
        toDotFile(graphname);
        String workingDir = System.getProperty("user.dir");
        String dir = workingDir+"\\DOT\\";
        String pdfdir = workingDir+"\\PDF\\";
        File file = new File(pdfdir);
        file.mkdirs();

        try {
            Runtime.getRuntime().exec("dot -Tpdf "+dir+graphname+".dot -o "+pdfdir+graphname+".pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


