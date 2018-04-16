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

public class Population {
	ArrayList<Individual> member;
	int fitness=0;
	Individual fittest;

	public Population() {
		member = new ArrayList<Individual>();
	}
	
	//adds person
	public void addMember(Individual p)
	{
		member.add(p);
	}
	
	//remove person
	public void killMember(Individual p)
	{
		member.remove(p);
	}
	
	//method to calculate the fittest.
	public void calculateFitness()
	{
		int fitValue = 0;
		for(int i=0; i<member.size();i++)
		{
			fitValue = member.get(i).getFitness();
			if(fitValue>fitness)
			{
				fitness=fitValue;
				fittest = member.get(i);
			}
		}
	}


	public Individual fittestIndividual() {

		calculateFitness();

		return fittest;

	}
}
