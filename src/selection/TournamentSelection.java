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
public class TournamentSelection extends Selection {

    public TournamentSelection(Random seed,
            FitnessComparator comparator,
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
    public Chromosome select(Chromosome c1, Chromosome c2) {
        return comparator.compare(c1, c2) < 0 ? c2 : c1;
    }
}
