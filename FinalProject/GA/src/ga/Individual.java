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
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Individual {
	private int rank;  
	private ArrayList<Integer> genes = new ArrayList<Integer>();
	private EdgeWeightedGraph graph;
	
	public Individual(EdgeWeightedGraph G, ArrayList<Integer> genes) {
            for(int i=0;i<genes.size();i++)
                    this.genes.add(genes.get(i));
            graph = G;
            }
	
	//method to calulate fitness
	private void calcualteFitness()
	{
            int sumOfPairs=0;
            for(int i=0;i<genes.size();i+=2)
            {
                    sumOfPairs+= shortestPath(genes.get(i), genes.get(i+1));
            }
            rank = graph.sumEdgeWeight()+sumOfPairs;		
	}	
	
	//method to return genes
	public ArrayList<Integer> getGenes()
	{
            return genes;
	}
	
	//method to set genes
	public void setGenes(ArrayList<Integer> genes)
	{
            this.genes = genes;
	}
	
	//method to get fitness
	public int getFitness()
	{
            calcualteFitness();
            return rank;
	}
	
	
	private int shortestPath(int s, int d){
            Boolean[] marked =new Boolean[graph.V()];
            int[] dist = new int[graph.V()];
            int[] prev = new int[graph.V()];		
            for(int i = 0; i<graph.V();i++)
            {
                    dist[i]=Integer.MAX_VALUE;
                    marked[i] = false;			
            }

            dist[s] = 0;
            prev[s] = s;

            Queue<Integer> pq = new PriorityQueue<Integer>();
            pq.add(s);

            while(!pq.isEmpty()){
                    int v = pq.poll();	
                    if(marked[v]) continue;
                    marked[v]=true;
                    for(Edge e: graph.adj(v))
                    {
                            int x = e.from();
                            int w = e.to(x);
                            if(x!=v)
                                    w=x;		
                            if(dist[w]>dist[v]+e.getWeight())
                            {
                                    dist[w] = (int) (dist[v]+e.getWeight());		
                                    prev[w] = v;
                                    pq.add(w);
                            }
                    }
            }	
            return dist[d];
	}


	public int compareTo(Individual that)
	{
            if(this.rank>that.rank) return 1;
            else if(this.rank<that.rank) return -1;
            else return 0;
	}
}
