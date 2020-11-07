package ex0;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * This class implements the graph_algorithm interface.
 * Most of the methods in this class work with a BFS algorithm in order to reach the desired nodes in a graph and
 * preform the desired calculations.
 * @author Tal.Zichlinsky
 */
public class Graph_Algo implements graph_algorithms{
    private graph myGraph;

    /**
     * Sets the graph the class will be working on to point to the graph passed as parameter.
     * @param g
     */
    @Override
    public void init(graph g) {
       myGraph = g;
    }

    /**
     * Method initializes a new graph that has nodes with the same keys and neighbors as this graph.
     * This method also connects the required nodes such that the copied graph will have all the edges and connections
     * the original graph has.
     * @return A deep copy of this graph.
     */
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

    /**
     * Method return true of false based on whether this graph is connected.
     * @return True iff there is a path from every node to every node in this graph.
     */
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


    /**
     * Method takes keys of two nodes, src and dest, and calculates the length (in edges) of the shortest path from src to dest.
     * @param src - start node
     * @param dest - end (target) node
     * @return An integer, representing number of edges from src to dest.
     */
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

    /**
     *
     * Method takes two keys of nodes, src and dest, and returns the shortest path from src to dest.
     * @param src - start node
     * @param dest - end (target) node
     * @return A list containing the path that is needed to go from src to dest, null if there isn't one.
     */
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

    /**
     * This function takes a list and reverses the order of the elements.
     * @param list
     * @return A new list, same as the one passed as parameter, in reverse order.
     */
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

    /**
     * Method goes over the nodes of graph passed as parameter and sets 0 as their tag.
     * @param g0
     */
    private void zeroAllTags(graph g0) {
        Iterator<node_data> iterator = g0.getV().iterator();
        node_data pointer;
        while (iterator.hasNext())
        {
            pointer = iterator.next();
            pointer.setTag(0);
        }
    }
}
