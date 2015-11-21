package recombination;

import java.util.Random;
import population.Population;
import population.PopulationFactory;

/**
 *
 * @author rich
 */
public abstract class BaseRecombination {
    
    protected final Random seed;
    protected final PopulationFactory populationFactory;
    protected final double probability;
    
    protected BaseRecombination(Random seed,
            PopulationFactory populationFactory,
            double probability) {
        this.seed = seed;
        this.populationFactory = populationFactory;
        this.probability = probability;
    }

    public abstract Population recombine(Population population);
}
