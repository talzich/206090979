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
        Graph_DS copy = new Graph_DS();
        Iterator<node_data> origIter = myGraph.getV().iterator();
        Iterator<node_data> adjIter;
        Node originPointer;
        while (origIter.hasNext())
        {
            originPointer = (Node)origIter.next();
            Node copyNode = new Node(originPointer);
            if(copy.hasKey(copyNode.getKey())) continue;
            copy.addNode(copyNode);
            adjIter = originPointer.getNi().iterator();
            while (adjIter.hasNext())
            {
                Node nei = new Node((Node) adjIter.next());
                if(!copy.hasKey(nei.getKey()))
                {
                    copy.addNode(nei);
                }
                copy.connect(copyNode.getKey(), nei.getKey());
            }
        }

        return copy;
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
            System.out.println("shortestPath: The graph doesn't contain either src, dest or both");
            return null;
        }
        int dist = shortestPathDist(src, dest);
        if (dist == -1) return null;
        LinkedList<node_data> path = new LinkedList<>();
        if (dist == 0)
        {
            path.add(source);
            return path;
        }
        node_data pointer;
        node_data step;
        Iterator<node_data> nIterator;
        path.add(destination);
        pointer = step = destination;
        while (step != source)
        {
            nIterator = step.getNi().iterator();
            while (nIterator.hasNext())
            {
                pointer = nIterator.next();
                if(pointer.getTag() < step.getTag())
                {
                    step = pointer;
                }
            }
            path.add(step);
        }
        path = reverseList(path);
        return path;
    }

    private LinkedList reverseList(LinkedList list) {
        if(list == null) return null;
        if(list.isEmpty()) return null;
        LinkedList reverse = new LinkedList();
        Iterator iterator = list.descendingIterator();
        while (iterator.hasNext())
        {
            reverse.add(iterator.next());
        }
        return reverse;

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
        //graph.connect(1,2);
        Graph_DS graph1 = (Graph_DS)ga.copy();
        Graph_Algo ga1 = new Graph_Algo();
        ga1.init(graph1);
        System.out.println(ga.isConnected());
        System.out.println(ga1.isConnected());
        graph.removeEdge(1,2);
        graph1.removeEdge(1,2);
        System.out.println(ga.isConnected());
        System.out.println(ga1.isConnected());
        graph.connect(2,0);
        System.out.println(ga.isConnected());
        System.out.println(ga1.isConnected());
//        graph.addNode(n3);
//        graph.addNode(n4);
//        graph.connect(0,1);
//        graph.connect(0,2);
//        graph.connect(1,2);
//        graph.connect(1,3);
//        graph.connect(2,3);
//        graph.connect(2,4);
//        graph.connect(3,4);
//        System.out.println(ga.shortestPathDist(0,4));
//        graph.removeEdge(2,4);
//        System.out.println(ga.shortestPathDist(0,4));
//        graph.removeEdge(3,4);
//        System.out.println(ga.shortestPathDist(0,4));
//        System.out.println(ga.shortestPathDist(1,5));
//        graph.removeNode(0);
//        System.out.println(ga.shortestPathDist(0,1));


    }
}
