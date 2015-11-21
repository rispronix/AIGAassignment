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
public class TournamentSelection extends BaseSelection {

    public TournamentSelection(Random seed,
            BaseFitnessComparator comparator,
            PopulationFactory populationFactory) {
        super(seed, comparator, populationFactory);
    }

    @Override
    public Population select(Population population) {
        population = populationFactory.createCopy(population);
        for (int i = 0; i < population.size(); i++) {
            population.set(i, select(
                    population.get(seed.nextInt(population.size())),
                    population.get(seed.nextInt(population.size()))));
        }
        return population;
    }

    @Override
    public BaseChromosome select(BaseChromosome c1, BaseChromosome c2) {
        return comparator.compare(c1, c2) < 0 ? c2 : c1;
    }
}
