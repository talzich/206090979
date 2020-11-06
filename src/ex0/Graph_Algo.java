package ex0;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph_Algo implements graph_algorithms{
    private graph g0;

    @Override
    public void init(graph g) {
       g0 = g;
    }

    @Override
    public graph copy() {
        return g0;
    }


    @Override
    public boolean isConnected() {
        //If the graph is empty or has only one node, the graph is vacuously connected
        if(g0.getV().isEmpty() || g0.nodeSize() == 1) return true;

        zeroAllTags(g0);
        Iterator<node_data> vIterator = g0.getV().iterator();
        LinkedList<Node> conComponent = new LinkedList<>();
        Queue<Node> neiQueue = new LinkedList<>();
        Node pointer = (Node)vIterator.next();
        neiQueue.add(pointer);
        pointer.setTag(1);
        while (!neiQueue.isEmpty())
        {
            pointer = neiQueue.poll();
            conComponent.add(pointer);
            vIterator = pointer.getNi().iterator();
            while (vIterator.hasNext())
            {
                pointer = (Node)vIterator.next();
                if(pointer.getTag() != 1)
                {
                    neiQueue.add(pointer);
                    pointer.setTag(1);
                }

            }
        }
        return (conComponent.size() == g0.nodeSize());
    }

    private void zeroAllTags(graph g0) {
        Iterator<node_data> iterator = g0.getV().iterator();
        node_data pointer;
        while (iterator.hasNext())
        {
            pointer = iterator.next();
            pointer.setTag(0);
        }
    }

    @Override
    public int shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<node_data> shortestPath(int src, int dest) {
        return null;
    }

    public static void main(String[] args) {
        Graph_Algo ga = new Graph_Algo();
        Graph_DS graph = new Graph_DS();
        ga.init(graph);
        Node n0 = new Node();
        Node n1 = new Node();
        Node n2 = new Node();
        graph.addNode(n0);
        graph.addNode(n1);
        graph.addNode(n2);
        graph.connect(0,1);
        graph.connect(1,2);
        System.out.println(ga.isConnected());
        graph.removeEdge(1,2);
        System.out.println(ga.isConnected());
        graph.connect(2,0);
        System.out.println(ga.isConnected());
    }
}
