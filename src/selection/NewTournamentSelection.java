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
public class NewTournamentSelection extends Selection {

    public NewTournamentSelection(Random seed,
            NewBaseFitnessComparator comparator,
            NewPopulationFactory populationFactory) {
        super(seed, comparator, populationFactory);
    }

    @Override
    public NewPopulation select(NewPopulation population) {
        population = populationFactory.createCopy(population);
        for (int i = 0; i < population.size(); i++) {
            population.set(i, select(
                    population.get(seed.nextInt(population.size())),
                    population.get(seed.nextInt(population.size()))));
        }
        return population;
    }

    @Override
    public NewBaseChromosome select(NewBaseChromosome c1, NewBaseChromosome c2) {
        return comparator.compare(c1, c2) < 0 ? c2 : c1;
    }
}
