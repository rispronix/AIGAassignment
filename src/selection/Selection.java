package selection;

import chromosomes.Chromosome;
import comparators.FitnessComparator;
import java.util.Random;
import population.Population;
import population.PopulationFactory;

/**
 *
 * @author rich
 */
public abstract class Selection {

    protected final Random seed;
    protected final FitnessComparator comparator;
    protected final PopulationFactory populationFactory;

    public Selection(Random seed,
            FitnessComparator comparator,
            PopulationFactory populationFactory) {
        this.seed = seed;
        this.comparator = comparator;
        this.populationFactory = populationFactory;
    }
    
    public abstract Population select(Population population);
    public abstract Chromosome select(Chromosome c1, Chromosome c2);
}
