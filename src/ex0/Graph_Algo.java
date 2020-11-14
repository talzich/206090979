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
        Graph_DS copy = new Graph_DS(); //The graph we will be returning
        Iterator<node_data> origIter = myGraph.getV().iterator();// This iterator will iterate through the original graph 
        Iterator<node_data> adjIter;//When origIter will point at a specific node, this Iterator will iterate through the node's neighbors  
        NodeData originPointer;// Will be used to point at the nodes of the original graph 
        while (origIter.hasNext())
        {
            originPointer = (NodeData)origIter.next();
            NodeData copyNode = new NodeData(originPointer);
            if(copy.hasKey(copyNode.getKey())) continue;
            copy.addNode(copyNode);
            adjIter = originPointer.getNi().iterator();
            while (adjIter.hasNext())
            {
                NodeData nei = new NodeData((NodeData) adjIter.next());
                if(!copy.hasKey(nei.getKey()))
                {
                    copy.addNode(nei);
                }
                copy.connect(copyNode.getKey(), nei.getKey());
            }
        }
        copy.copyMC(myGraph);//Because this graph is copied, its MC would be the amount of modes it has gone through while copying, we need to set it to be the same as the original. 
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

        zeroAllTags(myGraph);// Sets the tags of all nodes to 0.
        Iterator<node_data> vIterator = myGraph.getV().iterator();// This iterator will iterate through the graph's nodes.
        LinkedList<NodeData> conComponent = new LinkedList<>();// We will be listing all the nodes in the first connected component we will come across
        Queue<NodeData> neiQueue = new LinkedList<>();// This queue will let us know what node needs to be explored (in terms of its neighbors)
        NodeData pointer = (NodeData)vIterator.next();
        neiQueue.add(pointer);
        pointer.setTag(1);//We set the tag of every node that goes through the queue to 1, in order to keep track of the exploration.
        while (!neiQueue.isEmpty())
        {
            pointer = neiQueue.poll();
            conComponent.add(pointer);
            vIterator = pointer.getNi().iterator();//This will iterate through every node's neighbors
            while (vIterator.hasNext())
            {
                pointer = (NodeData)vIterator.next();
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
        node_data source = myGraph.getNode(src); // A pointer to the source node.
        node_data destination = myGraph.getNode(dest); // A pointer to the destination node. 
        
        //If the nodes doesn't exist in the graph there is no path.
        if(source == null || destination == null)
        {
            return -1;
        }
        
        zeroAllTags(myGraph);// Sets the tags of all the nodes to 0
        Queue<node_data> queue = new LinkedList<>();// Will keep track of which node to explore next
        Iterator<node_data> iterator;
        queue.add(source);
        
        /*
           We will be iterating through the graph, starting at the source node that has 0 as its tag. We will set each node's tag to its 
           distance from source (in edges). The way to do it is to set source to 0 and then exploring the graph only through its neighbors, and then their 
           neighbors and so on, while iterating we will set each node's tag to be one more than the node that sent us to it.  
         */
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
        LinkedList<node_data> path = new LinkedList<>();
        
        //If the nodes doesn't exist in the graph there is no path.
        if(source == null || destination == null)
        {
            return null;
        }
        int dist = shortestPathDist(src, dest); // The number of edges it takes to get from src to dest
        
        //If the nodes are not connected we will return null
        if (dist == -1) return null;
        
        //If the distance between src and dest is 0, src and dest are the same node. 
        if (dist == 0)
        {
            path.add(source);
            return path;
        }
        node_data pointer;//Where we are now
        node_data step; // Where to go next
        Iterator<node_data> nIterator;
        
        /*
        The way we will return the shortest path is to use the shortestPathDist method in order to set each node's tag to be its distance from src. 
        We will start our path at dest and will iterate through its neighbors to find the one with the smallest tag i.e. the one that marks the 
        shortest way to src and add it to our list. Eventually, we will get a list that contains the shortest path from dest to src. We will reverse
        that list and return it. 
        */
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
