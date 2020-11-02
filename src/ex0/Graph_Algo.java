package ex0;

import java.util.List;

public class Graph_Algo implements graph_algorithms{
    @Override
    public void init(graph g) {
        g = new Graph_DS();
    }

    @Override
    public graph copy() {
        return null;
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
