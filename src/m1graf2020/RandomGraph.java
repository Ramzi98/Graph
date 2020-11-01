package m1graf2020;

import java.util.Random;
/***
 * RandomGraph class is the class who create random graph in different type
 */
public class RandomGraph
{

    /**
     * Function who Create a Random Directed Graph
     * @param j is the number of nodes to be created
     * @return a Random Directed Graph
     */
    public Graf RandomDirectedGraph(final int j) {
        final Graf p = new Graf();
        for (int i = 0; i < j; ++i) {
            final Node a = new Node(i);
            p.addNode(a);
        }
        final Random rand = new Random();
        for (int k = 0; k < j; ++k) {
            final int randomNum = rand.nextInt(j - 1 - 0 + 1) + 0;
            if (randomNum != k) {
                p.addEdge(k, randomNum);
            }
        }
        return p;
    }

    /**
     * Function who Create a Random Undirected Graph
     * @param j is the number of nodes to be created
     * @return a Random Undirected Graph
     */
    public Graf RandomUndirectedGraph(final int j) {
        final Graf p = new Graf();
        for (int i = 0; i < j; ++i) {
            final Node a = new Node(i);
            p.addNode(a);
        }
        for (int i = 0; i < j; ++i) {
            for (int count = 0; count < j; ++count) {
                if (count != i) {
                    p.addEdge(i, count);
                    p.addEdge(count, i);
                }
            }
        }
        return p;
    }

    /**
     * Function who Create a Random Dense Graph
     * @param j is the number of nodes to be created
     * @return a Random Dense Graph
     */
    public Graf RandomDanseGraph(final int j) {
        final Graf p = new Graf();
        for (int i = 0; i < j; ++i) {
            final Node a = new Node(i);
            p.addNode(a);
        }
        for (int i = 0; i < j; ++i) {
            for (int count = 0; count < j; ++count) {
                if (count != i) {
                    p.addEdge(i, count);
                    p.addEdge(count, i);
                }
            }
        }
        return p;
    }

    /**
     * Function who Create a Random Spare Graph
     * @param j is the number of nodes to be created
     * @return a Random Spare Graph
     */
    public Graf RandomSpareGraph(final int j) {
        final Graf p = new Graf();
        for (int i = 0; i < j; ++i) {
            final Node a = new Node(i);
            p.addNode(a);
        }
        final Random rand = new Random();
        int randomNum = rand.nextInt(j - 1 - 0 + 1) + 0;
        for (int k = 0; k < j; ++k) {
            if (randomNum <= 0) {
                randomNum = rand.nextInt(j - 1 - 1 + 1) + 1;
                if (randomNum == k) {
                    continue;
                }
            }
            p.addEdge(k, randomNum);
            p.addEdge(randomNum, k);
            --randomNum;
        }
        return p;
    }

    /**
     * Function who Create a Random Connected Graph
     * @param j is the number of nodes to be created
     * @return a Random Connected Graph
     */
    public Graf RandomConnectedGraph(final int j) {
        final Graf s = new Graf();
        final Random rand = new Random();
        final int randomNum = rand.nextInt(j - 1 - 0 + 1) + 1;
        for (int i = 0; i < j; ++i) {
            final Node a = new Node(i);
            s.addNode(a);
        }
        final Boolean[] visited = new Boolean[s.getAllNodes().size()];
        for (int k = 0; k < visited.length; ++k) {
            visited[k] = false;
        }
        for (int k = 0; k < j; ++k) {
            for (int count = randomNum; count < j; ++count) {
                if (count != k) {
                    if (!visited[count]) {
                        s.addEdge(k, count);
                        s.addEdge(count, k);
                    }
                }
            }
            visited[k] = true;
        }
        return s;
    }

    /**
     * Function who Create a Random Dag Graph
     * @param j is the number of nodes to be created
     * @return a Random Dag Graph
     */
    public Graf RandomDagGraph(final int j) {
        final Graf s = new Graf();
        final Random rand = new Random();
        final int randomNum = rand.nextInt(j - 1 - 0 + 1) + 1;
        for (int i = 0; i < j; ++i) {
            final Node a = new Node(i);
            s.addNode(a);
        }
        final Boolean[] visited = new Boolean[s.getAllNodes().size()];
        for (int k = 0; k < visited.length; ++k) {
            visited[k] = false;
        }
        for (int k = 0; k < j; ++k) {
            for (int count = randomNum; count < j; ++count) {
                if (count != k) {
                    if (!visited[count]) {
                        s.addEdge(k, count);
                    }
                }
            }
            visited[k] = true;
        }
        return s;
    }

}
