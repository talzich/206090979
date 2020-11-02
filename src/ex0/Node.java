package ex0;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 * This class implements the node_data interface and represents a single
 * node in an unweighted undirected graph.
 * Each node holds a list of its adjacent nodes.
 * @author tal.zichlinsky
 */
public class Node implements node_data {

    private static int serialNumber = 0;
    private int key =0;
    private HashMap<Integer, node_data> nei;
    private String info;
    private int tag;


    /**
     * Gives new node it's unique key and initializes it's set of neighbours.
     */
    public Node(){
        key = serialNumber++;
        nei = new HashMap<>();
        nei.put(this.getKey(), this);
    }

    public Node(node_data node_data) {
        this.key = node_data.getKey();
        this.info = node_data.getInfo();
        this.tag = node_data.getTag();
    }

    /**
     * Implements node_data
     * @return This node's key
     */
    public int getKey() {
        return key;
    }

    /**
     * Implements node_data
     * @return A LinkedList of all nodes adjacent to this node
     */
    public Collection<node_data> getNi() {
        return nei.values();

    }

    /**
     * implements node_data
     * @param key
     * @return True iff a node with a key ,identical to the given parameter, is adjacent to this node.
     */
    public boolean hasNi(int key) {
        if(key > nei.size()) return false;
        if (this.getKey() == key) return true;
        return (nei.get(key) != null);
    }

    /**
     * Adds node t to this node's neighbours set.
     * @param t
     */
    public void addNi(node_data t) {
        if (this == t) return;
        this.nei.put(t.getKey(), t);
    }

    /**
     * Removes node from this node's neighbours set.
     * @param node
     */
    public void removeNode(node_data node) {
        if(this.equals(node)) return;
        nei.remove(node.getKey());
    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public void setInfo(String s) {
        info = s;
    }

    @Override
    public int getTag() {
        return tag;
    }

    @Override
    public void setTag(int t) {
        tag = t;
    }

}