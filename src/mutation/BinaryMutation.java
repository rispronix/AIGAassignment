package mutation;

import chromosomes.BaseChromosome;
import java.util.Random;

/**
 *
 * @author rich
 */
public class BinaryMutation extends BaseMutation {

    public BinaryMutation(Random seed, double probability) {
        super(seed, probability);
    }

    @Override
    public BaseChromosome mutateGene(BaseChromosome c) {
        int index = seed.nextInt(c.size());
        c.setGene(index, 1 - (int) c.getGene(index));
        return c;
    }
}
