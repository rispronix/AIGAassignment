package mutation;

import chromosomes.Chromosome;
import java.util.Random;

/**
 *
 * @author rich
 */
public class BinaryMutation extends Mutation {

    public BinaryMutation(Random seed, double probability) {
        super(seed, probability);
    }

    @Override
    public Chromosome mutateGene(Chromosome c) {
        int index = seed.nextInt(c.size());
        c.setGene(index, 1 - (int) c.getGene(index));
        return c;
    }
}
