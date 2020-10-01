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
                    System.out.println("Noued ajouter"+sourceNoude);
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
                System.out.println("Noued ajouter"+sourceNoude);
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
                //emptyList.add(newNoued);
                adjList.put(node,newList);
                System.out.println("Noued ajouter"+sourceNoude);
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
            if( nodes.getKey() == n )
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
        for (Map.Entry<Node, List<Node>> nodes : adjList.entrySet())
        {
            Node n = nodes.getKey();
            if( n.getId()==id )
            {
                return n;
            }
        }
        return null;
    }
    void addNode(Node n)
    {
        //pour ajouter le node n au graph
        List<Node> emptyList = new ArrayList<>();
        adjList.put(n,emptyList);
        System.out.println("Noued ajouter => Name : "+n.getName()+" ; ID : "+n.getId());
    }
    void addNode(int id)
    {
        //pour ajouter le node qui a l'id id au graph
        List<Node> emptyList = new ArrayList<>();
        Node node = new Node(id,"");
        adjList.put(node,emptyList);
        System.out.println("Noued ajouter => Name : "+node.getName()+" ; ID : "+node.getId());

    }
    void removeNode(Node n)
    {
        //pour supprimer le node n
        adjList.remove(n);
        System.out.println("Noued supprimer => Name : "+n.getName()+" ; ID : "+n.getId());
    }
    void removeNode(int id)
    {
        //pour supprimer le node qui à l'id id
        for (Map.Entry<Node, List<Node>> nodes : adjList.entrySet())
        {
            Node n = nodes.getKey();
            if( n.getId() == id )
            {
                removeNode(n);
                break;
            }
        }
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
        for (Map.Entry<Node, List<Node>> nodes : adjList.entrySet())
        {
            Node n = nodes.getKey();
            if( n.getId() == id )
            {
                return nodes.getValue();
            }
        }
        return null;
    }
    boolean adjacent(Node u, Node v)
    {
        //pour vérifier si les nodes n et v sont adjecents
        List<Node> successors_of_u = getSuccessors(u);
        List<Node> successors_of_v = getSuccessors(v);
        for(Node node:successors_of_u)
        {
            if(node.getId() == v.getId())
            {
                return true;
            }
        }
        for(Node node:successors_of_v)
        {
            if(node.getId() == u.getId())
            {
                return true;
            }
        }
        return false;
    }
    boolean adjacent(int idn1, int idn2)
    {
        //pour vérifier si les nodes qui ont les ids idn1 et idn2 sont adjecents
        List<Node> successors_of_u = getSuccessors(idn1);
        List<Node> successors_of_v = getSuccessors(idn2);
        for(Node node:successors_of_u)
        {
            if(node.getId() == idn2)
            {
                return true;
            }
        }
        for(Node node:successors_of_v)
        {
            if(node.getId() == idn1)
            {
                return true;
            }
        }
        return false;
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
        System.out.println("Nombre des edges : "+nbr);
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
    void addEdge(Node from, Node to){} // pour ajouter un edge entre node from(début) et node to(fin)
    void addEdge(int id_from, int id_to){} // pour ajouter un edge entre node qui à id id_from(début) et node qui à id id_to(fin)
    void removeEdge(Node from, Node to){}//pour supprimer l'edge
    void removeEdge(int id_from, int id_to){} //pour supprimer l'edge
    List<Edge> getOutEdges(Node n){return null;} //pour récuperer toute les edges qui sort depuis node n
    List<Edge> getOutEdges(int id){return null;} //pour récuperer toute les edges qui sort depuis node qui à comme id id
    List<Edge> getInEdges(Node n){return null;} //pour récuperer toute les edges qui entre vers le node n
    List<Edge> getInEdges(int id){return null;} //pour récuperer toute les edges qui entre vers le node qui à comme id id
    List<Edge> getIncidentEdges(Node n){return null;} //récuperer toutes les nodes qui sort et entre vers le node n
    List<Edge> getIncidentEdges(int id){return null;} //récuperer toutes les nodes qui sort et entre vers le node qui à comme id id
    List<Edge> getAllEdges(){return null;} //récuperer toutes les edges de graph

    /******************************          Degrees           ************************************************/

    int inDegree(Node n){return 0;} //pour connaitre le in-degree de node n
    int inDegree(int id){return 0;} //pour connaitre le in-degree de node qui à id comme id
    int outDegree(Node n){return 0;} //pour connaitre le out-degree de node n
    int outDegree(int id){return 0;} //pour connaitre le out-degree de node qui à id comme id
    int degree(Node n){return 0;} //pour connaitre le degree de node n
    int degree(int id){return 0;} //pour connaitre le degree de node qui à id comme id

    /******************************           Graph Representation           ************************************************/

    Graf getReverse(){return null;} //pour calculer le nouveau graph inverse
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
