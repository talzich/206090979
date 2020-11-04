package ex0;

import java.util.List;

public class Graph_Algo implements graph_algorithms{
    private graph g0;

    @Override
    public void init(graph g) {
       g0 = g;
    }

    @Override
    public graph copy() {
        graph copy = new graph();
    }

    @Override
    public boolean isConnected() {
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
