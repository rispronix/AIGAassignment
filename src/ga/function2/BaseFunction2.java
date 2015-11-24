package ga.function2;

import ga.GA;
import comparators.CompareMin;
import java.util.Random;
import population.Population;
import population.PopulationFactory;
import selection.TournamentSelection;

/**
 *
 * @author rich
 */
public abstract class BaseFunction2 extends GA {

    public BaseFunction2(Random seed) {
        super(seed);
        title = "BaseFunction2";
    }

    public BaseFunction2(Random seed,
            double recombinationProbability,
            double mutationProbability) {
        super(seed, recombinationProbability,
                mutationProbability);
        title = "BaseFunction2";
    }

    public BaseFunction2(Random seed,
            int generationCount,
            int populationSize,
            double recombinationProbability,
            double mutationProbability) {
        super(seed, generationCount,
                populationSize,
                recombinationProbability,
                mutationProbability);
        title = "BaseFunction2";
    }

    @Override
    public void setupComparator() {
        comparator = new CompareMin();
    }

    @Override
    public void setupPopulationFactory() {
        populationFactory = new PopulationFactory() {

            @Override
            public Population createNew() {
                return new Population(populationSize,
                        chromosomeFactory, comparator);
            }

            @Override
            public Population createCopy(Population population) {
                Population copy = new Population(populationSize, comparator);
                for (int i = 0; i < populationSize; i++) {
                    copy.set(i, chromosomeFactory.createCopy(
                            population.get(i)));

                }
                return copy;
            }
        };
    }

    @Override
    public void setupSelection() {
        selection = new TournamentSelection(seed,
                comparator, populationFactory);
    }
}
