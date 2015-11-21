package mutation;

import chromosomes.NewBaseChromosome;
import java.util.Random;

/**
 *
 * @author rich
 */
public class NewBinaryMutation extends NewBaseMutation {

    public NewBinaryMutation(Random seed, double probability) {
        super(seed, probability);
    }

    @Override
    public NewBaseChromosome mutateGene(NewBaseChromosome c) {
        int index = seed.nextInt(c.size());
        c.setGene(index, 1 - (int) c.getGene(index));
        return c;
    }
}
