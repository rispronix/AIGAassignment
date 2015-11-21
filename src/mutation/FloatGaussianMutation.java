package mutation;

import chromosomes.Chromosome;
import java.util.Random;

/**
 *
 * @author rich
 */
public class FloatGaussianMutation extends Mutation {

    public FloatGaussianMutation(Random seed, double probability) {
        super(seed, probability);
    }

    @Override
    public Chromosome mutateGene(Chromosome c) {
        int index = seed.nextInt(c.size());
        c.setGene(index, (float) c.getGene(index) + (float) seed.nextGaussian());
        return c;
    }
}
