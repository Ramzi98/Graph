package m1graf2020;

import java.util.ArrayList;
import java.util.List;

/**
 * This class extend the graf class, it represent the undirected graph, the only difference is when we add or remove and edge
 * @author Yacine
 */
public class UndirectedGraf extends Graf {
    /**
     * the first constrictor is the same of the super class, empty undirected symmetric graph
     */
    public UndirectedGraf(){
        super();
    }

    /**
     * the second constrictor is the same of the super class, empty undirected graph
     * @param weighted true if the graph is weighted, false else
     */

    public UndirectedGraf(boolean weighted){
        super(weighted);
    }

    /**
     * the third constructor that allow the user to create a symmetric undirected graph initialised by the SA
     * @param args the SA representation
     */
    public UndirectedGraf(Integer...args) throws Exceptiongraf{
        super.setWeighted(false);
        int i=0;
        int cpt=1;
        int cpt_zero=0;
        List<Node> n=new ArrayList<Node>();

        for (int arg : args){
            if (arg == 0 ) cpt_zero ++;
        }
        while(i < args.length) {
            if (args[i]==0)
            {
                cpt ++;

            }
            else{
                if(args[i] >cpt_zero) {

                    return;
                }
                this.addEdge(cpt,args[i]);
            }
            i++;

        }


    }

    /**
     * this method allow the user to add an arc in a undirected graph using only the number of the node source and destination
     * @param x the number of the source Node
     * @param y the number of the destination Node
     */
    @Override
    public void addEdge(int x, int y) {
        Node from = new Node(x);
        Node to = new Node(y);
        this.addEdge(from,to);
    }

    /**
     * this method allow the user to add an arc in a undirected graph using the node source and destination
     * @param from the node source
     * @param to the node destination
     */
    @Override
    public void addEdge(Node from, Node to) {
        // if the node from exist
        if(adjList.containsKey(from)==false)
        {
            adjList.put(from,new ArrayList<Node>());
        }

        // if the node to exist
        if(adjList.containsKey(to)==false)
        {
            adjList.put(to,new ArrayList<Node>());
        }

        // if the node to is already in the list of target of from ( so the edge already exist)

        if (!adjList.get(from).contains(to))
        {
            // we add it if it doesn't exist

            adjList.get(from).add(to);
            adjList.get(to).add(from);

            // We add an edge also in the edges array list, if the graph is weighted, we supposed that the weight is 0
            EdgeList.add(new Edge(from,to,0));
            EdgeList.add(new Edge(to,from,0));

        }

    }

    /**
     * this method allow the user to add a weighted edge for the undirected graph using only the number of the source and destination nodes
     * @param x the source number Node
     * @param y the destination number Node
     * @param weight the weight
     */
    @Override
    public void addEdge(int x, int y, int weight) {
        this.addEdge(x, y, weight);
    }

    /**
     * this method allow the user to add a weighted edge for the undirected graph using the source and destination nodes
     * @param from the node source
     * @param to the node destination
     * @param weight the weight of the arc
     */
    @Override
    public void addEdge(Node from, Node to, int weight) {
        // if the node from exist
        if(adjList.containsKey(from)==false)
        {
            adjList.put(from,new ArrayList<Node>());
        }

        // if the node to exist
        if(adjList.containsKey(to)==false)
        {
            adjList.put(to,new ArrayList<Node>());
        }

        // if the node to is already in the list of target of from ( so the edge already exist)

        if (!adjList.get(from).contains(to))
        {
            // we add it if it doesn't exist

            adjList.get(from).add(to);
            adjList.get(to).add(from);

            // We add an edge also in the edges array list, if the graph is weighted, we supposed that the weight is 0
            EdgeList.add(new Edge(from,to,weight));
            EdgeList.add(new Edge(to,from,weight));

        }

    }

}