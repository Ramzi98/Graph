package m1graf2020;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class Graf {
    Map<Node, List<Node>> adjList = new HashMap<Node,List<Node>>();
    List<Edge> EdgeList = new ArrayList<Edge>();

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
    public Graf(int ...Nodes) {
        int sourceNoude = 1;
        for(int currentNode : Nodes)
        {
            if(currentNode == 0)
            {
                if(!existsNode(sourceNoude))
                {
                    List<Node> emptyList = new ArrayList<>();
                    adjList.put(new Node(sourceNoude,""),emptyList);
                    System.out.println("Noued add : ID = "+sourceNoude);
                }
                sourceNoude++;
                continue;
            }
            if(!existsNode(sourceNoude))
            {
                List<Node> emptyList = new ArrayList<>();
                Node newNoued = new Node(currentNode,"");
                emptyList.add(newNoued);
                adjList.put(new Node(sourceNoude,""),emptyList);
                System.out.println("Noued add : ID = "+sourceNoude);
            }
            else
            {
                List<Node> newList = new ArrayList<>();
                Node newNoued = new Node(currentNode,"");
                Node node = getNode(sourceNoude);
                for(Node n:getSuccessors(node))
                {
                    newList.add(n);
                }
                newList.add(newNoued);
                adjList.put(node,newList);
                //emptyList.add(newNoued);
            }
        }
    }
    /******************************          Nodes           ************************************************/

    /***
     * Function who return the number of node in graph
     *
     * @return the number of node in graph
     */
    int nbNodes()
    {
        return adjList.size();
    }

    /***
     * Function who check if the node already exist
     *
     * @param n the node to control
     * @return a boolean who say the edge exist
     */
    boolean existsNode(Node n) {
        for (Map.Entry<Node, List<Node>> nodes : adjList.entrySet())
        {
            if(nodes.getKey().equals(n))
            {
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
        for (Map.Entry<Node, List<Node>> nodes : adjList.entrySet())
        {
            Node n = nodes.getKey();
            if( n.getId() == id )
            {
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
        if (existsNode(id))
        {
            for (Map.Entry<Node, List<Node>> nodes : adjList.entrySet())
            {
                Node n = nodes.getKey();
                if( n.getId()==id )
                {
                    return n;
                }
            }
        }
        else
        {
            System.out.println("Node : "+id+" Is created he Does't existe in list of Nodes before ");
            addNode(new Node(id,""));
        }
        return null;
    }

    /***
     * Function who add a node
     *
     * @param n the node added
     */
    void addNode(Node n) {
        if(!existsNode(n))
        {
            List<Node> emptyList = new ArrayList<>();
            adjList.put(n,emptyList);
            System.out.println("Noued add with function(addNode) => Name : "+n.getName()+" ; ID : "+n.getId());
        }
        else
        {
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
    void removeNode(Node n) {
        if (existsNode(n))
        {
            this.adjList.remove(n);
            for (Map.Entry<Node, List<Node>> entry : adjList.entrySet())
            {
                int j = entry.getValue().size();
                for (int i = 0; i < j; i++) {
                    Node nodeTo = entry.getValue().get(i);
                    if (n.getId() == nodeTo.getId()) {
                        entry.getValue().remove(i);
                    }
                }
            }
            System.out.println("Noued deleted with function(removeNode) => Name : "+n.getName()+" ; ID : "+n.getId());
        }
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
        for (Map.Entry<Node, List<Node>> nodes : adjList.entrySet())
        {
            if( nodes.getKey() == n )
            {
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
        if(existsNode(u) && existsNode(v))
        {
            List<Node> successors_of_u = getSuccessors(u);
            for(Node node:successors_of_u)
            {
                if(node.getId() == v.getId())
                {
                    return true;
                }
            }
        }
        else
        {
            if(!existsNode(u))
            {
                System.out.println("Node : "+u.getId()+" dont existe in the listes of nodes");
            }
            else
            {
                System.out.println("Node : "+v.getId()+" dont existe in the listes of nodes");
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
        return adjacent(u,v);
    }

    /***
     * Function who return a list of all nodes in graph
     *
     * @return a list of all nodes in graph
     */
    List<Node> getAllNodes() {
        List<Node> AllNodes = new ArrayList<Node>();
        for (Map.Entry<Node, List<Node>> nodes : adjList.entrySet())
        {
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
        int nbr=0;
        for (Map.Entry<Node, List<Node>> nodes : adjList.entrySet())
        {
            nbr += nodes.getValue().size();
        }
        System.out.println("edges Number : "+nbr);
        return nbr;
    }

    /***
     * Function who check if the edge already exist
     *
     * @param e the edge to control
     * @return a boolean who say the edge exist
     */
    boolean existsEdge(Edge e) {
        List<Node> list ;
        list=getSuccessors(e.getStartnode());
        if (!(list==null))
        {
            for(int i=0;i<list.size();i++)
            {
                if (list.get(i).getId() == e.getEndnode().getId())
                {
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
            List<Node> list ;
            list=getSuccessors(u);
            if (!(list==null))
            {
                for(int i=0;i<list.size();i++)
                {
                    if (list.get(i).getId() == v.getId())
                    {
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
        list=getSuccessors(idn1);
        for(int i=0;i<list.size();i++)
        {
            //pour voir s'il existe un edge entre les nodes qui ont les ids idn1 et idn2
            if (list.get(i).getId() == idn2)
            {
                return true;
            }
        }
        return false;
    }

    /***
     * Function who create a new edge with two node
     *
     * @param from the node from of edge
     * @param to the node to of edge
     */
    void addEdge(Node from, Node to) {
        if(!existsNode(from))
        {
            System.out.println("The first node doesn't exists. He is added");
            addNode(from);
        }
        if(!existsNode(to))
        {
            System.out.println("The second node doesn't exists. He is added");
            addNode(to);
        }
        Edge e = new Edge(from, to);
        if (!existsEdge(e))
        {
            adjList.get(from).add(to);
        }
        else {
            System.out.print("The node " + e.toString() + " already exist. Please create a new edge with other node.");
        }
    }

    /***
     * Overload of the addEdge function
     *
     * @param id_from the id of node from of edge
     * @param id_to the id of node to of edge
     */
    void addEdge(int id_from, int id_to) {
        Node node_from = getNode(id_from);
        Node node_to = getNode(id_to);
        addEdge(node_from,node_to);
    }

    /***
     * Overload of the addEdge function
     *
     * @param e the Edge to add
     */
    void addEdge(Edge e) {
        addEdge(e.getStartnode(),e.getEndnode());
    }

    /***
     * Overload on the addEdge function
     *
     * @param from the node from of edge
     * @param to the node to of edge
     * @param w the weighted of edge
     */
    public void addEdge(Node from, Node to, int w) {
        if(!existsNode(from))
        {
            System.out.println("The first node doesn't exists. He is added");
            addNode(from);
        }
        if(!existsNode(to))
        {
            System.out.println("The second node doesn't exists. He is added");
            addNode(to);
        }
        Edge e = new Edge(from, to, w);
        if (!existsEdge(e))
        {
            adjList.get(from).add(to);
        } else {
            System.out.print("The node " + e.toString() + " already exist. Please create a new edge with other node.");
        }

    }

    /***
     * Overload on the addEdge function
     *
     * @param idn1 the node from of edge
     * @param idn2 the node to of edge
     * @param w the weighted of edge
     */
    public void addEdge(int idn1, int idn2, int w) {
        addEdge(new Node(idn1), new Node(idn2), w);
    }


    /***
     *  Function who remove one edge with two nodes given in parameters and check if the edge exist
     *
     * @param from node from of edge
     * @param to node to of edge
     */
    void removeEdge(Node from, Node to) {
        if (adjacent(from,to))
        {
            for (Map.Entry<Node, List<Node>> node : adjList.entrySet())
            {
                if(node.getKey().getId() == from.getId())
                {

                    for (int i=0;i<node.getValue().size();i++)
                    {
                        if(node.getValue().get(i).getId() == to.getId())
                        {
                            removeNode(to);
                            break;
                        }
                    }
                }
            }
        }

    }


    /***
     *  Overload of the removeEdge function
     *
     * @param id_from node from of edge
     * @param id_to node to of edge
     */
    void removeEdge(int id_from, int id_to) {
        Node node_from = getNode(id_from);
        Node node_to = getNode(id_to);
        removeEdge(node_from,node_to);
    }

    /***
     * Function who return the list of out edge of node n
     *
     * @param n the node where we want to know the out edge
     * @return the list of out edge of node n
     */
    List<Edge> getOutEdges(Node n) {
        List<Edge> edges = new ArrayList<Edge>();
        if(existsNode(n))
        {
            List<Node> successors=getSuccessors(n);
            for (int i=0;i<successors.size();i++)
            {
                Edge edge=new Edge(n,successors.get(i));
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
        if(existsNode(n))
        {
            for (int i=0;i<nodes.size();i++)
            {
                successors = getSuccessors(nodes.get(i));
                for(int j=0;j<successors.size();j++)
                {
                    if(n.getId() == successors.get(j).getId())
                    {
                        Edge edge=new Edge(nodes.get(i),n);
                        edges.add(edge);
                    }
                }
            }
        }
        else
        {
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
        for (int i=0;i<Inedges.size();i++)
        {
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
        List<Edge> edgeList=new ArrayList<Edge>();
        for (int i=0;i<nodes.size();i++)
        {
            edgeList=getOutEdges(nodes.get(i));
            for (Edge e:edgeList)
            {
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
        List<Edge> edges=getInEdges(n);
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
        List<Edge> edges=getOutEdges(n);
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
        return inDegree(n)+outDegree(n);
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
        List <Node> nodes = getAllNodes();
        ArrayList<Integer> arrayList= new ArrayList<Integer>();
        for(Node currentnode : nodes)
        {   arrayList.add(currentnode.getId());
            for (Node othernode : nodes)
            {
                if(existsEdge(currentnode,othernode))
                {
                    arrayList.add(othernode.getId());
                }
            }
            arrayList.add(0);
        }
        int [] SA = new int[arrayList.size()];
        for(int i=0;i<arrayList.size();i++)
        {
            SA[i] = arrayList.get(i);
        }
        return  SA;
    }

    /***
     * Function who create an adjacency matrix with the graph given
     *
     * @return the matrix of the given graph
     */
    int[][] toAdjMatrix() {
        List<Edge> edges = getAllEdges();
        List<Node> nodes = getAllNodes();
        int startnode,endnode;
        int max = 0,max1 = 0;
        //Recuperation d'ID max
        for(int i=0;i<(nodes.size()-1);i++)
        {
            max1=Math.max(nodes.get(i).getId(),nodes.get(i+1).getId());
            if(max<max1)
            {
                max = max1;
            }
        }
        int [][] adjMatrix = new int[max+1][max+1];
        //initialisation
        for(int i=0;i<=max;i++)
        {
            for(int j=0;j<=max;j++)
            {
                adjMatrix[i][j] = 0;
            }
        }
        for(Edge edge : edges)
        {
            startnode=edge.getStartnode().getId();
            endnode=edge.getEndnode().getId();
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
        Map<Node, List<Node>> newadjList = new HashMap<Node,List<Node>>();
        List<Edge> edges;
        
        for (Map.Entry<Node, List<Node>> node : adjList.entrySet())
        {
            List<Node> adjacentsList=new ArrayList<Node>();
            reverseGraf.addNode(node.getKey());
            edges = getInEdges(node.getKey());
            for (Edge e : edges)
            {
                adjacentsList.add(e.getStartnode());
            }
            newadjList.put(node.getKey(),adjacentsList);
        }
        reverseGraf.adjList=newadjList;
        reverseGraf.EdgeList=reverseGraf.getAllEdges();
        return reverseGraf;
    }

    /***
     * Function who realize transitive closure with one graph
     *
     * @return the graph transformed by the transitive closure
     */
    Graf getTransitiveClosure() {
        int[][] adjMatrix = toAdjMatrix();
        int numberNode = adjMatrix.length;
        int[][] result = new int[numberNode][numberNode];
        result = adjMatrix;
        /*
        for (int i = 0; i < numberNode; i++) {
            for (int j = 0; j < numberNode; j++) {
                result[i][j] = adjMatrix[i][j];
            }
        }

         */

        for (int k = 0; k < numberNode; k++) {
            for (int i = 0; i < numberNode; i++) {
                for (int j = 0; j <numberNode; j++) {
                    result[i][j] = (result[i][j] != 0) ||
                                    ((result[i][k] != 0) && (result[k][j] != 0)) ? 1 : 0;
                }
            }
        }

        return adjMatrixToGraf(result);
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

    /***
     * Function who realize a dfs starting by the node given in parameters
     *
     * @param n the node by which to start to realize a dfs
     * @return a list of node of the course
     */
    List<Node> getDFS(Node n) {
        Node nParam = n;
        int numberNode = nbNodes();
        List<Node> DFS = new ArrayList<>();
        Vector<Boolean> visited = new Vector<Boolean>(numberNode);
        for (int i = 0; i < numberNode+1; i++)
        {
            visited.add(false);
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(nParam.getId());

        int[] tab = new int[numberNode];
        int cpt = 0;

        while (!stack.empty())
        {
            nParam.setId(stack.peek());
            stack.pop();
            if (!visited.get(nParam.getId())){
                tab[cpt] = nParam.getId();
                cpt++;
                visited.set(nParam.getId(), true);
            }
            List<Edge> outEdge = getOutEdges(nParam);
            Iterator<Edge> itr = outEdge.iterator();
            while (itr.hasNext()) {
                int v = itr.next().getEndnode().getId();
                if(!visited.get(v)){
                    stack.push(v);
                }
            }
        }

        for (int i = 0, c = tab.length; i < c; i++)
        {
            DFS.add(new Node(tab[i]));
        }
        return DFS;
    }

    /***
     * Overload of the getDFS function
     *
     * @param n the node by which to start to realize a dfs
     * @return a list of node of the course
     */
    List<Node> getDFS(int n) {
        return getDFS(new Node(n));
    }

    /***
     * Function who realize a bfs starting by the node given in parameters
     *
     * @param n the node by which to start to realize a bfs
     * @return a list of node of the course
     */
    List<Node> getBFS(Node n){
        Node nParam = n;
        int numberNode = nbNodes();
        List<Node> BFS = new ArrayList<>();

        int[] tab = new int[numberNode];
        int cpt = 0;

        Vector<Boolean> visited = new Vector<Boolean>(numberNode);
        for (int i = 0; i < numberNode+1; i++) {
            visited.add(false);
        }
        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited.set(nParam.getId(), true);
        queue.add(nParam.getId());

        while (queue.size() != 0) {
            nParam.setId(queue.poll());
            tab[cpt] = nParam.getId();
            cpt++;

            List<Edge> outEdge = getOutEdges(nParam);
            Iterator<Edge> itr = outEdge.iterator();

            while (itr.hasNext()) {
                int v = itr.next().getEndnode().getId();
                if(!visited.get(v)){
                    visited.set(v, true);
                    queue.add(v);
                }
            }
        }

        for (int i = 0, c = tab.length; i < c; i++) {
            BFS.add(new Node(tab[i]));
        }

        return BFS;
    }

    /***
     * Overload of the getBFS function
     *
     * @param n the node by which to start to realize a dfs
     * @return a list of node of the course
     */
    List<Node> getBFS(int n) {
        return getBFS(new Node(n));
    }

    /******************************            Graph Export           ************************************************/

    /***
     * Function who create a dot format of a graph given
     *
     * @return a string of a dot representation
     */
    String toDotString() {
        sortMapNodeByKey();
        String dotStringGraph = "digraph g {\n";
        int numberEdge = nbEdges();
        int numberNode = nbEdges();

        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            int n = entry.getKey().getId();
            dotStringGraph += " " + n + ";\n";
        }

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

    /***
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
            System.out.println("Error. Could not create file");
        }
    }

    /******************************            Additional feature           ************************************************/












/*
    public static void main(String[] args){
        System.out.println("Bonjour, SVP choisissez un numéro");
        System.out.println("create a new empty graph => 1");
        System.out.println("if is symmetric => 1 or not => 2");
        System.out.println("is weighted => 1 or not => 2");
        System.out.println("add a node to the graph");
        System.out.println("add an edge to the graph");
        System.out.println("Whats the weight of the edge ?");
        System.out.println("delete a node");
        System.out.println("delete an edge");
        Node n1= new Node(2);
        Node n2= new Node(2);
        Edge g1 = new Edge(1,2);
    }
*/
}
