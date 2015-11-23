package recombination;

import chromosomes.Chromosome;
import java.util.Random;
import population.PopulationFactory;

/**
 *
 * @author rich
 */
public class SinglePointCrossover extends Recombination {

    public SinglePointCrossover(Random seed,
            PopulationFactory populationFactory, 
            double probability) {
        super(seed, populationFactory, probability);
    }

    @Override
    public Chromosome recombine(Chromosome c1,
            Chromosome c2) {
        int pivot = seed.nextInt(c1.size());
        for (int i = pivot; i < c1.size(); i++) {
            c1.setGene(i, c2.getGene(i));
        }
        return c1;
    }
}
