package recombination;

import chromosomes.NewBaseChromosome;
import java.util.Random;
import population.NewPopulation;
import population.NewPopulationFactory;

/**
 *
 * @author rich
 */
public class NewSinglePointCrossover extends NewBaseRecombination {

    public NewSinglePointCrossover(Random seed, NewPopulationFactory populationFactory, double probability) {
        super(seed, populationFactory, probability);
    }

    @Override
    public NewPopulation recombine(NewPopulation population) {
        NewPopulation newp = populationFactory.createNew();
        for (int i = 0; i < population.size(); i++) {
            if (seed.nextDouble() < probability) {
                newp.set(i, singlepointCrossover(
                        population.get(i),
                        population.get(i + 1 % population.size() - 1)));
            }
        }
        return newp;
    }
    
    public NewBaseChromosome singlepointCrossover(NewBaseChromosome c1,
            NewBaseChromosome c2) {
        int pivot = seed.nextInt(c1.size());
        for (int i = pivot; i < c1.size(); i++) {
            c1.setGene(i, c2.getGene(i));
        }
        return c1;
    }
}
