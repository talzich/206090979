package ex0;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class Graph_DS<main> implements graph{

    private int modeCounter = 0;
    private HashMap<Integer,node_data> nodes;
    private int nodeSize = 0;
    private int edgeSize = 0;


    /**
     * A simple constructor.
     */
    public Graph_DS(){
        nodes = new HashMap<>();
        modeCounter++;
    }

    /**
     * A method that only copies MC. Should only be used as part of the copy method in Graph_Algo.
     * @param other
     */
    public void copyMC(graph other){
        this.modeCounter = other.getMC();
    }
    /**
     *
     * @param key - the node_id
     * @return The node with the specified key, if exists in this graph.
     */
    @Override
    public node_data getNode(int key) {
        //if(key > nodes.size()) return null;
        return nodes.get(key);
    }

    /**
     *
     * @param node1
     * @param node2
     * @return True iff the nodes with the specified keys are connected in this graph.
     * @return False if one of those nodes does not exist in this graph
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        if(!nodes.containsKey(node1) || !nodes.containsKey(node2))
        {
            System.err.println("hasEdge: Not all nodes are in the graph");
            return false;
        }
        return (nodes.get(node1).hasNi(node2));
    }

    /**
     * Adds noded passed as parameter to this graph.
     * @param n
     */
    @Override
    public void addNode(node_data n) {
        if(n==null) return;
        modeCounter++;
        nodes.put(n.getKey(), n);
        nodeSize++;
    }

    /**
     * Connects nodes with specified keys, if they exist in this graph.
     * @param key1
     * @param key2
     */
    @Override
    public void connect(int key1, int key2) {
        if(this.getNode(key1) == null || this.getNode(key2) == null)
        {
            System.err.println("connect: One of those nodes are not in the graph");
            return;
        }
        if(key1 == key2)
        {
            System.err.println("connect: Nodes are the same");
            return;
        }
        if(hasEdge(key1,key2))
        {
            System.err.println("connect: Nodes already connected " );
            return;
        }
        nodes.get(key1).addNi(nodes.get(key2));
        nodes.get(key2).addNi(nodes.get(key1));
        edgeSize++;
        modeCounter++;
    }

    /**
     *
     * @return A collection representation of the nodes in this graph
     */
    @Override
    public Collection<node_data> getV() {
        return nodes.values();
    }

    /**
     *
     * @param key
     * @return A collection representation of the neighbors of the node with the specified key.
     */
    @Override
    public Collection<node_data> getV(int key) {
        if(!this.nodes.containsKey(key))
        {
            System.err.println("getV(neis): No such node in the graph");
            return null;
        }
        return nodes.get(key).getNi();
    }

    /**
     * Removes the node with the specified key from this graph and disconnects all the
     * edges that comes out of tit.
     * @param key - The key of the node that will be removed.
     * @return The removed node.
     */
    @Override
    public node_data removeNode(int key) {

        if(!nodes.containsKey(key))
        {
            System.err.println("removeNode: No such node in the graph");
            return null;
        }

        Iterator<node_data> iterator = nodes.get(key).getNi().iterator();
        Node pointer;
        while (iterator.hasNext())
        {
            pointer = (Node) iterator.next();
            pointer.removeNode(nodes.get(key));
            edgeSize--;
            modeCounter++;
        }
        modeCounter++;
        nodeSize--;
        nodes.get(key).getNi().clear();
        return nodes.remove(key);

    }

    /**
     * Removes the edge that connects the nodes with the specified keys.
     * @param node1
     * @param node2
     */
    @Override
    public void removeEdge(int node1, int node2) {
        if(!nodes.containsKey(node1) || !nodes.containsKey(node2))
        {
            System.err.println("removeEdge: No such node in the graph");
            return;
        }
        Node n1 = (Node)nodes.get(node1);
        Node n2 = (Node)nodes.get(node2);
        if(n1.hasNi(node2))
        {
            n1.removeNode(n2);
            n2.removeNode(n1);
            edgeSize--;
            modeCounter++;
        }
    }

    /**
     *
     * @return Amount of nodes in this graph
     */
    @Override
    public int nodeSize() {
        return nodeSize;
    }

    /**
     * Amount of edges in this graph
     * @return
     */
    @Override
    public int edgeSize() {
        return edgeSize;
    }

    /**
     *
     * @return Amount of changes made to this graph. Counting: adding nodes, adding edges, removing nodes, removing edges, creating the graph.
     */
    @Override
    public int getMC() {
        return modeCounter;
    }

    /**
     * Checks whether this graph contains a node with specified key
     * @param key
     * @return True iff this graph contains node with a key such as passed as parameter.
     */
    public boolean hasKey(int key){
        return this.nodes.containsKey(key);
    }

    /**
     * A simple toString method.
     * @return
     */
    public String toString()
    {
        String data = "#Nodes: " + nodeSize + "\n#Edges: " + edgeSize + "\nModes: " + modeCounter;
        return "***Graph Data***\n" + data + "\n***End Graph Data***";
    }

}