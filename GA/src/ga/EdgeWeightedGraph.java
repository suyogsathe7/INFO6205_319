/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga;

/**
 *
 * @author ivanr
 */
public class EdgeWeightedGraph {
    
    private final int V;
    private final Bag<Edge>[] adj;
    
    public EdgeWeightedGraph(int V)
    {
        this.V = V;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
        adj[v] = new Bag_Array<Edge>();
    }
    
    public void addEdge(Edge e)
    {
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
    }
    
    public Iterable<Edge> adj(int v)
    { 
        return adj[v]; 
    }
}
