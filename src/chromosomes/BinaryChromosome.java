package chromosomes;

import fitness.FitnessFunction;
import java.util.Random;

/**
 *
 * @author rich
 */
public class BinaryChromosome extends BaseChromosome {

    protected Random seed;
    protected int size;

    public BinaryChromosome(Random seed, int size) {
        this.seed = seed;
        this.size = size;
    }

    public BinaryChromosome(BaseChromosome source) {
    }

    @Override
    public BaseChromosome initialise() {
        genes = new Integer[size];
        for (int i = 0; i < size; i++) {
            genes[i] = seed.nextInt(2);
        }
        return this;
    }

//    @Override
//    public float evaluate(FitnessFunction ff) {
//        return fitness = ff.calculate(this);
//    }

    @Override
    public float evaluate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
