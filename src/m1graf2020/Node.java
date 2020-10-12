package m1graf2020;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Node  implements Comparable<Node> {
    private static int count = 0;
    private Integer  id;
    private String name;


    /***
     * Default constructor
     */
    public Node() {
        count++;
        this.id = count;
    }

    /***
     * Constructor who create a node with a id and without a name
     *
     * @param id the id of one node
     */
    public Node(int id) {
        this.id = id;
    }


    /***
     * Constructor who create a node with a name and a id
     *
     * @param name the name of one node
     * @param id the id of one node
     */
    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }


    /***
     * Getter on id of node
     *
     * @return the id of one node
     */
    public Integer getId() {
        return id;
    }

    /***
     * Setter on id of node
     *
     * @param id set the id of one node
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /***
     * Getter on name of node
     *
     * @return the name of one node
     */
    public String getName() {
        return name;
    }

    /***
     * Setter on name of node
     *
     * @param name set the name of one node
     */
    public void setName(String name) {
        this.name = name;
    }


    /***
     * Function who return a string of one node
     *
     * @return one string of one node with only number
     */
    @Override
    public String toString() {
        return "" + id + "";
    }


    /***
     * Function who compare two nodes together
     *
     * @param o  node to compare with an another
     * @return the result of the comparison
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return this.hashCode() == o.hashCode();
    }

    /***
     * Function who realize a hashcode of one object node
     *
     * @return the result of hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }


    /***
     * Function who compare tow object node
     *
     * @return the result of difference between two nodes
     */
    @Override
    public int compareTo(Node o) {

        return this.getId() - o.getId();
    }
}
