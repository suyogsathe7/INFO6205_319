/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;

public class GeneticAlgorithm {
	private EdgeWeightedGraph G;
	private Population population;
	private int generations;
	
	public GeneticAlgorithm(EdgeWeightedGraph G) {
		this.G= G;
		generations =0;
	}	
	
	public void run()
	{
		initialize();
		System.out.println("generation: "+generations);
		System.out.println("population:");	
		for(int i=0;i<population.member.size();i++)
			System.out.println(population.member.get(i).getGenes());
		System.out.println("best rank: "+ population.member.get(0).getFitness());	
		int lastBest =Integer.MAX_VALUE;
		int consistentGenerations=0;	
		while(true)
		{					
			evolve();
			generations++;	
			System.out.println("generation: "+ generations);	
			for(int j=0;j<population.member.size();j++)
				System.out.println(population.member.get(j).getGenes());
			System.out.println("best rank: "+ population.member.get(0).getFitness());
			/*break if the solution is not getting significantly better for 10 generations*/
			if(lastBest>population.member.get(0).getFitness()) {
				lastBest = population.member.get(0).getFitness();
				consistentGenerations=0;
			}
			else {
				consistentGenerations++;
			}
			if (consistentGenerations>10)
			break;			
		}
	}
	
	/*generates initial population- Generation-0

	 * the number of individuals in the starting generation is taken as 4*/

	public void initialize()
	{		
		ArrayList<Integer> oddVertices = new ArrayList<Integer>();
		for(int i=0;i<G.V();i++)
		{
			int degree = EdgeWeightedGraph.degree(G, i);
			if((degree%2)!=0)
				oddVertices.add(i);
		}
int sizeOfPopulation = 6;
		population = new Population();

		/*10 individuals are created*/

		for(int loop=0;loop<sizeOfPopulation;loop++)
		{
			Individual adam = new Individual(G, oddVertices);
			population.addMember(adam);			
			Collections.shuffle(oddVertices);
		}
		population.calculateFitness();
	}


	private void evolve()
	{
		if(generations>0)
			cull();	
		ArrayList<Individual> offsprings = new ArrayList<Individual>();		
		for(int i=0; i<population.member.size();i++)
		{
			ArrayList<Integer> genome = new ArrayList<>();
			for(int j=0;j< population.member.get(i).getGenes().size(); j++) {
				genome.add(population.member.get(i).getGenes().get(j));
			}
			Individual child = new Individual(G, genome);
			offsprings.add(child);
		}

	for(int i=0;i<offsprings.size()-1;i+=2)
            {
                    crossBreed(offsprings.get(i), offsprings.get(i+1));
            }

            /*Select 2 Individuals randomly as mutants*/
            Random r = new Random();
            int select = r.nextInt(offsprings.size());		
            mutation(offsprings.get(select));
            select = r.nextInt(offsprings.size());	
            mutation(offsprings.get(select));

            for(int i=0;i<offsprings.size();i++)
            {
                    population.member.add(offsprings.get(i));
            }

            population.calculateFitness();		
	}

	private void cull()
	{		
		/*set rank cut-off as 30% more than the best rank*/
		int fitScore = population.member.get(0).getFitness();
		fitScore = fitScore+100;
	
		/*save 2nd best individuals for cross-over in case of bad cull cutoff selection*/		
		Individual secondFittest  = population.member.get(1);
		Iterator<Individual> iter = population.member.iterator();
		while(iter.hasNext())
		{
			Individual ind = iter.next();
			if(ind.getFitness()> fitScore)
			{
				/*remove any individual with 30% more rank value than the fittest*/ 
				iter.remove();
			}				
		}
		
		/*in case of culling of all the members except the best individual, add save second best to

		*continue the population*/

		if(population.member.size()<2)
		{

			population.member.add(secondFittest);

		}	

		

		/*in case of more than 10 solutions, save the best 10 and cull the rest

		*continue the population*/

		if(population.member.size()>10)

		{

			for(int iter1=population.member.size()-1; iter1>10;iter1--)

				population.member.remove(population.member.get(iter1));

		}	

		

		

	}

	

	/*changes the gene pool of the individual*/

	public void crossBreed(Individual a, Individual b)

	{

		int geneSize = a.getGenes().size();

		Random rand = new Random();

		

		int randomNum = rand.nextInt(a.getGenes().size()/2);

		

		

		ArrayList<Integer> newGene = new ArrayList<>();

		ArrayList<Integer> newGene2 = new ArrayList<>();

		

		for(int i=0;i<2*randomNum;i++)

		{

			newGene.add(a.getGenes().get(i));

			newGene2.add(b.getGenes().get(i));

		}



		

		for(int i=0; i<geneSize; i++)

		{

			newGene.add(b.getGenes().get(i));

			newGene2.add(a.getGenes().get(i));

		}

		

		/*remove duplicate genes*/

		newGene = new ArrayList<Integer>(new LinkedHashSet<Integer>(newGene));

		newGene2 = new ArrayList<Integer>(new LinkedHashSet<Integer>(newGene2));

		

		

		a.setGenes(newGene);

		b.setGenes(newGene2);

		



		

		

	}

	

	/*mutation is means of evolution for asexual beings

	 * this functions pairs the genes randomly with other in the genome*/

	public void mutation(Individual a)

	{

		

		Random rand = new Random();

		

		int randomNum = rand.nextInt(a.getGenes().size());

		System.out.println(randomNum);

		ArrayList<Integer> newGene = new ArrayList<>();

	

		int geneSize = a.getGenes().size();

		for(int i=0;i<geneSize;i++)

		{

			newGene.add(a.getGenes().get(i));

			newGene.add(a.getGenes().get((i+randomNum)%geneSize));	

			

		}

		

		/*remove duplicate genes*/

		newGene = new ArrayList<Integer>(new LinkedHashSet<Integer>(newGene));

	

		a.setGenes(newGene);

		

	}
}
