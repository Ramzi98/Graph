package m1graf2020;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Node  implements Comparable<Node> {
    private static int count = 0;
    private Integer  id;
    private String name;

    private static Set<Integer> assignedIds = new HashSet<Integer>();

    public Node(int id) {
        if ((this.id.equals(id))) {
            if (assignedIds.contains(id))
            {
                System.out.println("Erreur not unique ID");
            }
            else
            {
                assignedIds.add(id);
                this.id = id;
            }

        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node() {
        count++;
        this.id = count;
        assignedIds.add(id);
    }

    public Node(int id, String name) {
        this.id = id;
        this.name = name;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return this.hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public int compareTo(Node o) {

        return this.getId() - o.getId();
    }
}
