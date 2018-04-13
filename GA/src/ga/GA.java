/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga;

import java.util.Random;

/**
 *
 * @author ivanr
 */

public class GA {
/*
    int vertices[] = new int[10];
    int phenotype[] = new int[10];
    boolean edgeVisited[] = new boolean[10];
    Random random;
    
    public GA(){
        random = new Random();
        for(int i =0; i < vertices.length; i++){
            vertices[i] = random.nextInt();
        }
        
    }
    
    public static void main(String[] args) {
        
    }
*/    
    private final int V;
    private final Bag_Array<Edge>[] adj;
    public GA(int V)
    {
        this.V = V;
        adj = (Bag_Array<Edge>[]) new Bag_Array[V];
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

    public static void main(String[] args)
    {
        //In in = new In(args[0]);
        GA G = new GA(10);
        for (Edge e : G.adj(10))
            System.out.println(e);
        //System.out.printf("%.2f\n", mst.weight());
        
    }
}