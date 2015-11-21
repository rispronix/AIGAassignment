package mutation;

import chromosomes.BaseChromosome;
import java.util.Random;

/**
 *
 * @author rich
 */
public class FloatGaussianMutation extends BaseMutation {

    public FloatGaussianMutation(Random seed, double probability) {
        super(seed, probability);
    }

    @Override
    public BaseChromosome mutateGene(BaseChromosome c) {
        int index = seed.nextInt(c.size());
        c.setGene(index, (float) c.getGene(index) + (float) seed.nextGaussian());
        return c;
    }
}
