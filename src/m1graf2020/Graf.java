package m1graf2020;

import java.util.*;

public class Graf {
    Map<Node, List<Node>> adjList = new HashMap<Node,List<Node>>();
    List<Edge> EdgeList = new ArrayList<Edge>();

    public Graf(int ...targetNode)
    {
        int sourceNoude = 1;
        for(int currentNode : targetNode)
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

    int nbNodes()
    {
        return adjList.size();
    }
    boolean existsNode(Node n)
    {
        //pour voir si le Node n existe dans le graph

        for (Map.Entry<Node, List<Node>> nodes : adjList.entrySet())
        {
            if(nodes.getKey().equals(n))
            {
                return true;
            }
        }
        return false;

    }
    boolean existsNode(int id)
    {
        //pour voir si le Node qui a l'ID id existe dans le graph
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
    Node getNode(int id)
    {
        //pour retourner le node
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
            System.out.println("Node : "+id+" Dont existe in list of Nodes");
        }
        return null;
    }
    void addNode(Node n)
    {
        //pour ajouter le node n au graph
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
    void addNode(int id)
    {
        //pour ajouter le node qui a l'id id au graph
        //Node node = getNode(id);
        Node node = new Node(id,"");
        addNode(node);

    }
    void removeNode(Node n)
    {
        //pour supprimer le node n
        adjList.remove(n);
        System.out.println("Noued deleted with function(removeNode) => Name : "+n.getName()+" ; ID : "+n.getId());
    }
    void removeNode(int id)
    {
        //pour supprimer le node qui à l'id id
        Node node = getNode(id);
        removeNode(node);
    }
    List<Node> getSuccessors(Node n)
    {
        //pour avoir la liste des successeurs de node n
        for (Map.Entry<Node, List<Node>> nodes : adjList.entrySet())
        {
            if( nodes.getKey() == n )
            {
                return nodes.getValue();
            }
        }
        return null;
    }
    List<Node> getSuccessors(int id)
    {
        //pour avoir la liste des successeurs de node qui à l'id id
        Node n = getNode(id);
        return getSuccessors(n);
    }

    /************************* AVANT cette lingne il faut ajouter les conditions comme si(existNode(u)).... *************************/

    boolean adjacent(Node u, Node v)
    {
        //pour vérifier si les nodes n et v sont adjecents
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
    boolean adjacent(int idn1, int idn2)
    {
        //pour vérifier si les nodes qui ont les ids idn1 et idn2 sont adjecents
        Node u = getNode(idn1);
        Node v = getNode(idn2);
        return adjacent(u,v);
    }
    List<Node> getAllNodes()
    {
        //pour récuperer la liste des nodes de graph
        List<Node> AllNodes = new ArrayList<Node>();
        for (Map.Entry<Node, List<Node>> nodes : adjList.entrySet())
        {
              AllNodes.add(nodes.getKey());
        }
        return AllNodes;
    }

    /******************************          Edges           ************************************************/

    int nbEdges()
    {
        //pour connaitre le nombre des edges de graph
        int nbr=0;
        for (Map.Entry<Node, List<Node>> nodes : adjList.entrySet())
        {
            nbr += nodes.getValue().size();
        }
        System.out.println("edges Number : "+nbr);
        return nbr;
    }
    boolean existsEdge(Node u, Node v)
    {
        //pour voir s'il existe un edge entre les nodes u et v
            List<Node> list;
            list=getSuccessors(u);
            for(int i=0;i<list.size();i++)
            {
                if (list.get(i).getId() == v.getId())
                {
                    return true;
                }
            }
        return false;
    }
    boolean existsEdge(int idn1, int idn2)
    {
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
    void addEdge(Node from, Node to)
    {
        // pour ajouter un edge entre node from(début) et node to(fin)
        if (existsNode(from) && existsNode(to) && !existsEdge(from,to))
        {
            for (Map.Entry<Node, List<Node>> node : adjList.entrySet())
            {
                if(node.getKey().getId() == from.getId())
                {
                    node.getValue().add(to);
                    break;
                }
            }
        }
        else if(existsEdge(from,to))
        {
            System.out.println("Edge already existe");
        }
        else
        {
            System.out.println("cant add Edge because Node dont exist ");
        }


    }
    void addEdge(int id_from, int id_to)
    {
        // pour ajouter un edge entre node qui à id id_from(début) et node qui à id id_to(fin)
        Node node_from = getNode(id_from);
        Node node_to = getNode(id_to);
        addEdge(node_from,node_to);
    }
    void removeEdge(Node from, Node to)
    {
        //pour supprimer l'edge
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
    void removeEdge(int id_from, int id_to)
    {
        //pour supprimer l'edge
        Node node_from = getNode(id_from);
        Node node_to = getNode(id_to);
        removeEdge(node_from,node_to);
    }
    List<Edge> getOutEdges(Node n)
    {
        //pour récuperer toute les edges qui sort depuis node n
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
    List<Edge> getOutEdges(int id)
    {
        //pour récuperer toute les edges qui sort depuis node qui à comme id id
        Node n = getNode(id);
        return getOutEdges(n);
    }
    List<Edge> getInEdges(Node n)
    {
        //pour récuperer toute les edges qui entre vers le node n
        List<Node> nodes = getAllNodes();
        List<Node> successors;//=new ArrayList<Node>();
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
    List<Edge> getInEdges(int id)
    {
        //pour récuperer toute les edges qui entre vers le node qui à comme id id
        Node n = getNode(id);
        return getInEdges(n);
    }
    List<Edge> getIncidentEdges(Node n)
    {
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
    List<Edge> getIncidentEdges(int id)
    {
        //récuperer toutes les nodes qui sort et entre vers le node qui à comme id id
        Node n = getNode(id);
        return getIncidentEdges(n);
    }
    List<Edge> getAllEdges()
    {
        //récuperer toutes les edges de graph
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

    int inDegree(Node n)
    {
        //pour connaitre le in-degree de node n
        int compteur=0;
        List<Edge> edges=getInEdges(n);
        for (Edge e : edges)
        {
            if(e.getStartnode().equals(e.getEndnode()))
            {
                compteur+=2;
            }
            else
            {
                compteur++;
            }
        }
        return edges.size();
    }
    int inDegree(int id)
    {
        //pour connaitre le in-degree de node qui à id comme id
        Node node = getNode(id);
        return inDegree(node);
    }
    int outDegree(Node n)
    {
        //pour connaitre le out-degree de node n
        int compteur=0;
        List<Edge> edges=getOutEdges(n);
        for (Edge e : edges)
        {
            if(e.getStartnode().equals(e.getEndnode()))
            {
                compteur+=2;
            }
            else
            {
                compteur++;
            }
        }
        return edges.size();
    }
    int outDegree(int id)
    {
        //pour connaitre le out-degree de node qui à id comme id
        Node node = getNode(id);
        return outDegree(node);
    }
    int degree(Node n)
    {
        //pour connaitre le degree de node n
        return inDegree(n)+outDegree(n);
    }
    int degree(int id)
    {
        //pour connaitre le degree de node qui à id comme id
        Node node = getNode(id);
        return degree(node);
    }

    /******************************           Graph Representation           ************************************************/

    Graf getReverse()
    {
        //pour calculer le nouveau graph inverse
        Graf reverseGraf = new Graf();
        Map<Node, List<Node>> newadjList = new HashMap<Node,List<Node>>();
        List<Node> successors;

        List<Edge> edges;
        
        for (Map.Entry<Node, List<Node>> node : adjList.entrySet())
        {
            List<Node> adjacentsList=new ArrayList<Node>();
            successors = node.getValue();
            for(Node successor:successors)
            {
                if(!reverseGraf.existsNode(successor))
                {
                    edges = getInEdges(successor);
                    for (Edge e : edges)
                    {
                        adjacentsList.add(e.getStartnode());
                    }
                    reverseGraf.addNode(successor);
                    newadjList.put(successor,adjacentsList);

                }

            }

        }
        reverseGraf.adjList=newadjList;
        reverseGraf.EdgeList=reverseGraf.getAllEdges();
        return reverseGraf;
    }
    Graf getTransitiveClosure(){return null;} //pour calculer dans le nouveau graph transitive closure du graph

    /******************************           Graph Traversal           ************************************************/

    List<Node> getDFS(){return null;}
    List<Node> getBFS(){return null;}

    /******************************            Graph Export           ************************************************/

    String toDotString(){return null;}
    void toDotFile(String fileName){}

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
