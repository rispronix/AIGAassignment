package mutation;

import chromosomes.NewBaseChromosome;
import java.util.Random;

/**
 *
 * @author rich
 */
public class NewFloatGaussianMutation extends NewBaseMutation {

    public NewFloatGaussianMutation(Random seed, double probability) {
        super(seed, probability);
    }

    @Override
    public NewBaseChromosome mutateGene(NewBaseChromosome c) {
        int index = seed.nextInt(c.size());
        c.setGene(index, (float) c.getGene(index) + (float) seed.nextGaussian());
        return c;
    }
}
