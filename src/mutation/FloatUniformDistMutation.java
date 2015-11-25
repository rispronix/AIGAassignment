package mutation;

import chromosomes.Chromosome;
import java.util.Random;

/**
 *
 * @author r2-rowley
 */
public class FloatUniformDistMutation extends Mutation {

    public FloatUniformDistMutation(Random seed, double probability) {
        super(seed, probability);
    }

    @Override
    public Chromosome mutateGene(Chromosome c) {
        int index = seed.nextInt(c.size());
        c.setGene(index, (float) c.getGene(index) + 
                (float) seed.nextDouble());
        return c;
    }
}
