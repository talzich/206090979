package ex0;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph_Algo implements graph_algorithms{
    private graph myGraph;

    @Override
    public void init(graph g) {
       myGraph = g;
    }

    @Override
    public graph copy() {
        return myGraph;
    }


    @Override
    public boolean isConnected() {
        //If the graph is empty or has only one node, the graph is vacuously connected
        if(myGraph.getV().isEmpty() || myGraph.nodeSize() == 1) return true;

        zeroAllTags(myGraph);
        Iterator<node_data> vIterator = myGraph.getV().iterator();
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
        return (conComponent.size() == myGraph.nodeSize());
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
        node_data source = myGraph.getNode(src);
        node_data destination = myGraph.getNode(dest);
        //If the nodes doesn't exist in the graph there is no path.
        if(source == null || destination == null)
        {
            System.err.println("shortestPathDist: The graph doesn't contain either src, dest or both");
            return -1;
        }
        zeroAllTags(myGraph);
        Queue<node_data> queue = new LinkedList<>();
        Iterator<node_data> iterator;
        queue.add(source);
        while (!queue.isEmpty())
        {
            node_data pointer = queue.poll();
            node_data nei;
            iterator = pointer.getNi().iterator();
            while (iterator.hasNext())
            {
                nei = iterator.next();
                if(nei.getTag() == 0 && nei != source)
                {
                    nei.setTag(pointer.getTag() + 1);
                    queue.add(nei);
                }
            }
        }

        if(destination.getTag() == 0)
        {
            System.err.println("shortestPathDist: src and dest are not connected in this graph.");
            return -1;
        }
        return destination.getTag();
    }

    @Override
    public List<node_data> shortestPath(int src, int dest) {
        node_data source = myGraph.getNode(src);
        node_data destination = myGraph.getNode(dest);
        //If the nodes doesn't exist in the graph there is no path.
        if(source == null || destination == null)
        {
            System.out.println("shortestPath: The graph doesn't contain either src or dest or both");
            return null;
        }




        return null;
    }

    public static void main(String[] args) {
        Graph_Algo ga = new Graph_Algo();
        Graph_DS graph = new Graph_DS();
        ga.init(graph);
        Node n0 = new Node();
        Node n1 = new Node();
        Node n2 = new Node();
        Node n3 = new Node();
        Node n4 = new Node();
        graph.addNode(n0);
        graph.addNode(n1);
        graph.addNode(n2);
//        graph.connect(0,1);
//        graph.connect(1,2);
//        System.out.println(ga.isConnected());
//        graph.removeEdge(1,2);
//        System.out.println(ga.isConnected());
//        graph.connect(2,0);
//        System.out.println(ga.isConnected());
        graph.addNode(n3);
        graph.addNode(n4);
        graph.connect(0,1);
        graph.connect(0,2);
        graph.connect(1,2);
        graph.connect(1,3);
        graph.connect(2,3);
        graph.connect(2,4);
        graph.connect(3,4);
        System.out.println(ga.shortestPathDist(0,4));
        graph.removeEdge(2,4);
        System.out.println(ga.shortestPathDist(0,4));
        graph.removeEdge(3,4);
        System.out.println(ga.shortestPathDist(0,4));
        System.out.println(ga.shortestPathDist(1,5));
        graph.removeNode(0);
        System.out.println(ga.shortestPathDist(0,1));

    }
}
