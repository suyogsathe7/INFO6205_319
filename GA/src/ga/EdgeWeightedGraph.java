/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga;

import java.util.ArrayList;

public class EdgeWeightedGraph {
    private final int V;
    private final Bag_Array<Edge>[] adj;
    private Queue<Edge> mst = new Queue<Edge>();  // edges in MST
    public ArrayList<Edge> edges = new ArrayList<>();
    
    public EdgeWeightedGraph(int V)
    {
        this.V = V;
        adj = (Bag_Array<Edge>[]) new Bag_Array[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag_Array<Edge>();
    } 
    
    public void addEdge(Edge e)
    {
        int v = e.from(), w = e.to(v);
        adj[v].add(e);
        adj[w].add(e);
        edges.add(e);
    }
    
    public Iterable<Edge> adj(int v)
    { 
        return adj[v]; 
    } 
    
    public int getEdge( int v ){
        
        for( int i = 0; i < adj.length; i++){
            if( adj[i] != null ){
                if(adj[i].iterator().hasNext())
                {
                    Edge e = adj[i].iterator().next();
                    if( e.from() == v ){
                        return v;
                    }
                }
            }
        }
        
        return -1;
    }
    
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag_Array<Edge>();
        for (int v = 0; v < V; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.to(v) > v) {
                    list.add(e);
                }
                // add only one copy of each self loop (self loops will be consecutive)
                else if (e.to(v) == v) {
                    if (selfLoops % 2 == 0) list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }
    
     public int V() {
        return V;
    }

     public static int degree(EdgeWeightedGraph G, int v)
    {
        int degree = 0;
        for(Edge w: G.adj(v))
                degree++;
        return degree;
    }
    int sumEdgeWeight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}