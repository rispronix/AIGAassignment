package chromosomes;

import java.util.Random;

/**
 *
 * @author rich
 */
public abstract class BinaryChromosome extends Chromosome {

    protected Random seed;
    
    public BinaryChromosome(Random seed, int size) {
        super(size);
        this.seed=seed;
    }
    
    @Override
    public Chromosome initialise() {
        genes = new Integer[size];
        for (int i = 0; i < size; i++) {
            genes[i] = seed.nextInt(2);
        }
        return this;
    }
}
