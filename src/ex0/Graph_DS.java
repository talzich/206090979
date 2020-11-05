package ex0;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class Graph_DS<main> implements graph{

    private int modeCounter = 0;
    private HashMap<Integer,node_data> nodes;
    private int nodeSize = 0;
    private int edgeSize = 0;


    public Graph_DS(){
        nodes = new HashMap<>();
        modeCounter++;
    }//v

    /**
     *
     * @param key - the node_id
     * @return The node with the specified key, if exists in this graph.
     */
    @Override
    public node_data getNode(int key) {
        //if(key > nodes.size()) return null;
        return nodes.get(key);
    }//v

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
            System.err.println("Not all nodes are in the graph");
            return false;
        }
        return (nodes.get(node1).hasNi(node2));
    }

    /**
     * Adds a node to this graph.
     * @param n
     */
    @Override
    public void addNode(node_data n) {
        if(n==null) return;
        modeCounter++;
        nodes.put(n.getKey(), n);
        nodeSize++;
    }//v

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
        nodes.get(key1).addNi(nodes.get(key2));
        nodes.get(key2).addNi(nodes.get(key1));
        edgeSize++;
        modeCounter++;
    }//v

    /**
     *
     * @return A collection representation of the nodes in this graph
     */
    @Override
    public Collection<node_data> getV() {
        return nodes.values();
    }//v

    /**
     *
     * @param key
     * @return A collection representation of the neighbors of the specified node.
     */
    @Override
    public Collection<node_data> getV(int key) {
        if(!this.nodes.containsKey(key))
        {
            System.err.println("getV(neis): No such node in the graph");
            return null;
        }
        return nodes.get(key).getNi();
    }//v

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

    }//v

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
            nodes.get(node1).removeNode(nodes.get(node2));
            nodes.get(node2).removeNode(nodes.get(node1));
            edgeSize--;
            modeCounter++;

    }//v

    @Override
    public int nodeSize() {
        return nodeSize;
    }//v

    @Override
    public int edgeSize() {
        return edgeSize;
    }//v

    @Override
    public int getMC() {
        return modeCounter;
    }//v

    public String toString()
    {
        String data = "#Nodes: " + nodeSize + "\n#Edges: " + edgeSize + "\nModes: " + modeCounter;
        return "***Graph Data***\n" + data + "\n***End Graph Data***";
    }
//
//    public static void main(String[] args) {
//        Node n0 = new Node();
//        Node n1 = new Node();
//        Node n2 = new Node();
//        Node n3 = new Node();
//
//        n0.setTag(1);
//        n0.setInfo("White");
//        n1.setTag(2);
//        n1.setInfo("White");
//        n2.setTag(3);
//        n2.setInfo("White");
//        n3.setTag(4);
//        n3.setInfo("black");
//
//        Graph_DS G=new Graph_DS();
//        G.addNode(n0);
//        G.addNode(n1);
//        G.addNode(n2);
//        G.addNode(n3);
//        System.out.println(G.toString());
//        G.connect(n0.getKey(), n1.getKey());
//        System.out.println(G.hasEdge(n0.getKey(), n1.getKey()));
//        System.out.println(G.hasEdge(n0.getKey(), n2.getKey()));
//        G.connect(n0.getKey(), n2.getKey());
//        System.out.println(n0.hasNi(n2.getKey()));
//        System.out.println(n2.hasNi(n0.getKey()));
//        G.connect(n1.getKey(), n3.getKey());
//        System.out.println(G.toString());
//        G.removeNode(n0.getKey());
//        System.out.println(G.toString());
//        System.out.println(n0.hasNi(n2.getKey()));
//        System.out.println(n2.hasNi(n0.getKey()));
//    }
}