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
    private Queue<Edge> mst = new Queue<Edge>();  // edges in MST
    
    public GA(int V)
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

    
    
    public static void main(String[] args)
    {
        //In in = new In(args[0]);
        GA G = new GA(10);
        //Edge e1 = new Edge(0,1,10);
        Random ran = new Random();
        for( int i = 0 ; i < 10; i++){
            int from = ran.nextInt(10);
            int to = ran.nextInt(10);
            int weight = ran.nextInt(10);
            
            if( G.getEdge(from) < 0 ){
                G.addEdge( new Edge(from,to,weight) );
            }
//            
//            Edge e;
//            
//            for( int j=2; j<G.V; j++ ){
//                e = new Edge(j, ran.nextInt(j)+0, ran.nextInt(20)+0);
//                G.addEdge(e);
//            }
        }
        
        for(Edge e : G.adj(2)){
            System.out.println("GA: Either: "+e.from());
        }
        
        System.out.println(" GA: ");
//        for(int i = (G.adj.length -1); i >= 0; i--) {
//            Bag_Array<Edge> bag_array = G.adj[i];
//            if(bag_array.iterator().hasNext()){
//                Edge e = bag_array.iterator().next();
//                System.out.println(" i:"+ i +" Weight: " + e.getWeight()+" Either: " + e.from() + " Other: "+ e.to(e.from()) );
//            }
//        }
        
        //KruskalMST mst = new KruskalMST(G);
        for (Edge e : G.edges()) {
            System.out.println(" Weight: " + e.getWeight()+" Either: " + e.from() + " Other: "+ e.to(e.from()));
        }
        //System.out.printf("%.2f\n", mst.weight());
    }
}