package recombination;

import java.util.Random;
import population.NewPopulation;
import population.NewPopulationFactory;

/**
 *
 * @author rich
 */
public abstract class NewBaseRecombination {
    
    protected final Random seed;
    protected final NewPopulationFactory populationFactory;
    protected final double probability;
    
    protected NewBaseRecombination(Random seed,
            NewPopulationFactory populationFactory,
            double probability) {
        this.seed = seed;
        this.populationFactory = populationFactory;
        this.probability = probability;
    }

    public abstract NewPopulation recombine(NewPopulation population);
}
