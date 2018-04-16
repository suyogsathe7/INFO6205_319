/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ga.Edge;
import ga.EdgeWeightedGraph;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ivanr
 */
public class EdgeWeightedGraphJUnitTest {
    
    public EdgeWeightedGraphJUnitTest() {
    }
    
    @Test
    public void getEdge(){
        EdgeWeightedGraph eWG = new EdgeWeightedGraph(5);
        assertEquals(0,eWG.getEdge(5));
    }
    
    @Test
    public void edges(){
        EdgeWeightedGraph eWG = new EdgeWeightedGraph(5);
        //assertEquals(,eWG.edges());
    }
}
