package ex0;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Graph_DS implements graph{

    private int modeCounter = 0;
    private HashMap<Integer,node_data> nodes;
    private int nodeSize = 0;
    private int edgeSize = 0;


    public Graph_DS(){
        nodes = new HashMap<>();
        modeCounter++;
    }

    @Override
    public node_data getNode(int key) {
        System.out.println("*****getNode  - Start*****");
        System.out.println("*****getNode  - End*****");
        return nodes.get(key);

    }//v

//    @Override
    public boolean hasEdge(int node1, int node2) {
        System.out.println("*****hasEdge  - Start*****");
        System.out.println("*****hasEdge  - End*****");
        return (nodes.get(node1).hasNi(node2));
    }//v

    @Override
    public void addNode(node_data n) {
        System.out.println("*****addNode  - Start*****");
        modeCounter++;
        nodes.put(n.getKey(), n);
        nodeSize++;
        System.out.println("*****addNode  - End*****");
    }//v

    @Override
    public void connect(int key1, int key2) {
        System.out.println("*****connect  - Start*****");
        nodes.get(key1).addNi(nodes.get(key2));
        nodes.get(key2).addNi(nodes.get(key1));
        edgeSize++;
        modeCounter++;
        System.out.println("*****connect  - End*****");
    }//v

    @Override
    public Collection<node_data> getV() {
        return nodes.values();
    }//v

    @Override
    public Collection<node_data> getV(int key) {
        return nodes.get(key).getNi();
    }//v

    @Override
    public node_data removeNode(int key) {
        System.out.println("*****removeNode - start*****");
        System.out.println("");
        if(!nodes.containsKey(key))
            return null;

        Node[] nei = (Node[]) nodes.get(key).getNi().toArray();
        for (int i = 0; i < nei.length; i++)
        {
            nei[i].removeNode(nodes.get(key));
        }


        Node n = new Node(nodes.get(key));
        nodes.remove(key);
        modeCounter++;
        System.out.println("*****removeNode - start*****");
        return n;

    }//v

    @Override
    public void removeEdge(int node1, int node2) {
        System.out.println("*****removeEdge - start*****");
        try{
            nodes.get(node1).removeNode(nodes.get(node2));
            nodes.get(node2).removeNode(nodes.get(node1));
            edgeSize--;
            modeCounter++;
        }catch (Exception e){
            throw new RuntimeException("Can't remove edge");
        }
        System.out.println("*****removeEdge - end*****");
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
}