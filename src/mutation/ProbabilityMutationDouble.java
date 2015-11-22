package mutation;

import chromosomes.Chromosome;
import java.util.Random;

/**
 *
 * @author rich
 */
public class ProbabilityMutationDouble extends Mutation {

    public ProbabilityMutationDouble(Random seed, double probability) {
        super(seed, probability);
    }

    double value;
    int index;
    
    @Override
    public Chromosome mutateGene(Chromosome c) {
        if (seed.nextDouble() < probability) {
            value = seed.nextGaussian() / 100;
            index = seed.nextInt(c.size());
            c.setGene(index, (double) c.getGene(index) + value);
        }
        return c;
    }
}
