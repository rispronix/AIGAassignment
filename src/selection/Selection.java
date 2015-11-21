package selection;

import chromosomes.NewBaseChromosome;
import comparators.NewBaseFitnessComparator;
import java.util.Random;
import population.NewPopulation;
import population.NewPopulationFactory;

/**
 *
 * @author rich
 */
public abstract class Selection {

    protected final Random seed;
    protected final NewBaseFitnessComparator comparator;
    protected final NewPopulationFactory populationFactory;

    public Selection(Random seed,
            NewBaseFitnessComparator comparator,
            NewPopulationFactory populationFactory) {
        this.seed = seed;
        this.comparator = comparator;
        this.populationFactory = populationFactory;
    }
    
    public abstract NewPopulation select(NewPopulation population);
    public abstract NewBaseChromosome select(NewBaseChromosome c1, NewBaseChromosome c2);
}
