package selection;

import chromosomes.BaseChromosome;
import comparators.BaseFitnessComparator;
import java.util.Random;
import population.Population;
import population.PopulationFactory;

/**
 *
 * @author rich
 */
public abstract class BaseSelection {

    protected final Random seed;
    protected final BaseFitnessComparator comparator;
    protected final PopulationFactory populationFactory;

    public BaseSelection(Random seed,
            BaseFitnessComparator comparator,
            PopulationFactory populationFactory) {
        this.seed = seed;
        this.comparator = comparator;
        this.populationFactory = populationFactory;
    }
    
    public abstract Population select(Population population);
    public abstract BaseChromosome select(BaseChromosome c1, BaseChromosome c2);
}
