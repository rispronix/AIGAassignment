package mutation;

import chromosomes.Chromosome;
import java.util.Random;

/**
 *
 * @author rich
 * @param <T>
 */
public class PermutationMutation <T> extends Mutation {

    public PermutationMutation(Random seed, double probability) {
        super(seed, probability);
    }

    @Override
    public Chromosome mutateGene(Chromosome c) {
        int index1 = seed.nextInt(c.size());
        int index2 = seed.nextInt(c.size());
        
        T value = (T) c.getGene(index1);
        c.setGene(index1, c.getGene(index2));
        c.setGene(index2, value);
        
        return c;
    }
    
}
