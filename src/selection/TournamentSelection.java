package selection;

import java.util.Random;
import chromosomes.BaseChromosome;
import comparators.FitnessComparator;
import population.Population;
import population.PopulationFactory;

/**
 *
 * @author rich
 */
public class TournamentSelection {

    private final Random seed;
    private final FitnessComparator comparator;
    private final PopulationFactory populationFactory;

    public TournamentSelection(Random seed,
            FitnessComparator comparator,
            PopulationFactory populationFactory) {
        this.seed = seed;
        this.comparator = comparator;
        this.populationFactory = populationFactory;
    }

    public Population select(Population population) {
        population = populationFactory.createCopy(population);
        for (int i = 0; i < population.size(); i++) {
            population.set(i, select(
                    population.get(seed.nextInt(population.size())),
                    population.get(seed.nextInt(population.size()))));
        }
        return population;
    }

    public BaseChromosome select(BaseChromosome c1, BaseChromosome c2) {
        return comparator.compare(c1, c2) < 0 ? c2 : c1;
    }
}
