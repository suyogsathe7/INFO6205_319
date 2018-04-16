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
	

    
    public static void main(String[] args)
    {
        //In in = new In(args[0]);
        //In in = new In(args[0]);
        EdgeWeightedGraph edge = new EdgeWeightedGraph(15);
        Random r = new Random(5);
        Edge e;
        int node1=0;
        int node2=0;
        int weight=0;
        Edge eg = new Edge(0, 1, 5);
        edge.addEdge(eg); 
        
        for(int i=1; i<=50; i++) {
            node1 = r.nextInt(10);
            node2 = r.nextInt(10);
            weight = r.nextInt(10);
            if(node1 == node2) {
                    continue;
            }
            e = new Edge(node1, node2, weight);
            if(edge.getEdge(node1)!=edge.getEdge(node2)) {
                    continue;
            }
            
            edge.addEdge(e);
        }
        e = new Edge(0,node2, 7);
        edge.addEdge(e);
        System.out.println("Graph : ");
        for(Edge ed: edge.edges)
            System.out.println( ed.from()+" - "+ed.to(ed.from())+ " weight: "+ ed.getWeight() );
        
        Random rand = new Random(4);
        int op = rand.nextInt(25);
        for(int i = op ; i >= 0; i-- ){
            Dijkstra dj = new Dijkstra(edge,i,edge.V());
            System.out.println( " Shortest Path for "+ i+" : " + dj.shortestDist(3) + " " + "");
        }
        
        Dijkstra dj1 = new Dijkstra(edge,2,edge.V());
        int x = dj1.shortestDist(1);
        System.out.println(x);
        Graph g = new Graph(edge.V());
        g.printAllPaths(0,edge.V());
    }
}