package ex0;
import java.util.Collection;
import java.util.HashMap;

/**
 * This class implements the node_data interface and represents a single
 * node in an unweighted undirected graph.
 * Each node holds a list of its adjacent nodes.
 * @author tal.zichlinsky
 */
public class NodeData implements node_data {

    private static int serialNumber = 0;
    private int key =0;
    private HashMap<Integer, node_data> nei;
    private String info = "";
    private int tag = 0;


    /**
     * Gives new node it's unique key and initializes it's set of neighbours.
     */
    public NodeData(){
        key = serialNumber++;
        nei = new HashMap<>();
    }

    /**
     * Copy constructor - Should only be used when copying a graph.
     * This copy constructor DOES NOT copy a nodes adj list, but it does initialize it.
     * This copy constructor copies only key, info and tag.
     *
     * @param other
     */
    public NodeData(NodeData other) {
        if (other == null) return;
        this.key = other.getKey();
        this.info = other.getInfo();
        this.tag = other.getTag();
        this.nei = new HashMap<>();
   }

    /**
     * Implements node_data
     * @return This node's key
     */
    @Override
    public int getKey() {
        return this.key;
    }

    /**
     * Implements node_data
     * @return A collection representation of all nodes adjacent to this node
     */
    @Override
    public Collection<node_data> getNi() {
        return nei.values();
    }

    /**
     * Implements node_data
     * @param key
     * @return True iff a node with a key ,identical to the given parameter, is adjacent to this node.
     */
    @Override
    public boolean hasNi(int key) {
        return (nei.get(key) != null);
    }

    /**
     * Adds node t to this node's neighbours set.
     * @param t
     */
    @Override
    public void addNi(node_data t) {
        if (this == t || t == null) return;
        this.nei.put(t.getKey(), t);
    }

    /**
     * Removes node from this node's neighbours set.
     * @param node
     */
    @Override
    public void removeNode(node_data node) {
        if(node == null) return;
        this.nei.remove(node.getKey());
    }

    /**
     *
     * @return The info for this graph as provided in the setInfo method.
     */
    @Override
    public String getInfo() {
        return info;
    }

    /**
     * Sets this info to be s
     * @param s
     */
    @Override
    public void setInfo(String s) {
        info = s;
    }

    /**
     *
     * @return The tag of this node
     */
    @Override
    public int getTag() {
        return tag;
    }

    /**
     * Sets this node's tag to be t
     * @param t - the new value of the tag
     */
    @Override
    public void setTag(int t) {
        tag = t;
    }

    /**
//     * A simple toString method.
     * @return
     */
    public String toString(){
        String data = "Key: " + key + "\nTag: " + tag + "\nInfo: " +info;
        return data;
    }
}