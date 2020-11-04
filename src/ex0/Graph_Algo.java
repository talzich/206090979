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
        Iterator iter = g0.getV().iterator();
        Iterator nei;
        Queue<node_data> q = new LinkedList<>();
        int nodeCounter = 0;
        Node pointer;
        if(iter.hasNext())
        {
            pointer = (Node)iter.next();
            q.add(pointer);
        }
        else
            return true;
        while (!q.isEmpty())
        {
            pointer = (Node)q.poll();
            pointer.setTag(1);
            nei = pointer.getNi().iterator();


        }



        return false;
    }

    @Override
    public int shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<node_data> shortestPath(int src, int dest) {
        return null;
    }
}
