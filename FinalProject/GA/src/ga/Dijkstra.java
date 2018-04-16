/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * @author ivanr
 */
public class Dijkstra {
    Boolean[] marked;
	int[] dist;
	int[] distances;
	int[] prev;
        int src;
        private ArrayList<Integer> genes;
	public Dijkstra(EdgeWeightedGraph G, int s, int V)
	{
            src=s;
            marked =new Boolean[V];
            dist = new int[V];
            prev = new int[V];
            for(int i = 0; i<V;i++)
            {
                    dist[i]=Integer.MAX_VALUE;
                    marked[i] = false;			
            }

            dist[s] = 0;
            prev[s] = s;

            java.util.Queue<Integer> pq = new PriorityQueue<Integer>();
            pq.add(s);

            while(!pq.isEmpty())
            {
                int v = pq.poll();

                if(marked[v]) continue;
                marked[v]=true;
                for(Edge e: G.adj(v))
                {
                    int x = e.from();
                    int w = e.to(x);
                    if(x!=v)
                        w=x;
                    if(dist[w]>dist[v]+e.getWeight())
                    {
                        dist[w] = (int) (dist[v] + e.getWeight());
                        prev[w] = v;
                        pq.add(w);
                    }
                }
            }
	}
	
	public int shortestDist(int w)
	{
		//previous(w);
		return dist[w];
	}
        
        private void previous(int w)
        {
//            System.out.print(" "+w);
            if(w==this.src)
                previous(w);
        }
        
	
        public ArrayList<Integer> getGenes()
	{
		return genes;
	}
        
        /*set genes*/
	public void setGenes(ArrayList<Integer> genes)
	{
		this.genes = genes;
	}
}
