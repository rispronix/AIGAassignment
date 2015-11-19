/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mutation;

import chromosomes.BaseChromosome;
import java.util.Random;
import population.Population;
import population.PopulationFactory;

/**
 *
 * @author r2-rowley
 */
public abstract class Mutation {

    private final Random seed;
    private final PopulationFactory populationFactory;
    private final double probability;

    public Mutation(Random seed, PopulationFactory populationFactory, double probability) {
        this.seed = seed;
        this.populationFactory = populationFactory;
        this.probability = probability;
    }

    public Population mutate(Population population) {
        for (int i = 0; i < population.size(); i++) {
            if (seed.nextDouble() < probability) {
                population.set(i, mutateGene(population.getElement(i)));
            }
        }

        return population;
    }

    public abstract BaseChromosome mutateGene(BaseChromosome c);
}
