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
public class Edge implements Comparable<Edge>
{
    private final int v, w;
    private final double weight;
    
    public Edge(int v, int w, double weight)
    {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    
    public int from()
    { 
        return v; 
    }
    
    public int to(int vertex)
    {
        if (vertex == v) return w;
        else return v;
    } 

    public double getWeight() {
        return weight;
    }
    
    public int compareTo(Edge that)
    {
        if (this.weight < that.weight) return -1;
        else if (this.weight > that.weight) return +1;
        else return 0;
    }
    
    public int getFitness(){
        
        return 0;
    }
}
