package selection;

import chromosome.BaseChromosome;
import java.util.Random;
import comparators.FitnessComparator;
import population.Population;

/**
 *
 * @author rich
 */
public class TournamentSelection {

    private final Random seed;
    private final FitnessComparator comparator;

    public TournamentSelection(Random seed, FitnessComparator comparator) {
        this.seed = seed;
        this.comparator = comparator;
    }

    public Population select(Population population) {
        Population newPopulation = new Population(population.size());
        for (int i = 0; i < newPopulation.size(); i++) {
            int index1 = seed.nextInt(population.size());
            int index2 = seed.nextInt(population.size());
            population.set(i, select(population.getElement(index1), population.getElement(index2 + 1 % population.size()-1)));
        }
        return newPopulation;
    }

    public BaseChromosome select(BaseChromosome c1, BaseChromosome c2) {
        return comparator.compare(c1, c2) < 0 ? c2 : c1;
    }
}
