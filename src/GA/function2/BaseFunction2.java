package GA.function2;

import GA.BaseFunction;
import comparators.CompareMin;
import java.util.Random;
import population.NewPopulation;
import population.NewPopulationFactory;
import selection.NewTournamentSelection;

/**
 *
 * @author rich
 */
public abstract class BaseFunction2 extends BaseFunction {

    public BaseFunction2(Random seed) {
        super(seed);
    }

    public BaseFunction2(Random seed,
            double recombinationProbability,
            double mutationProbability) {
        super(seed, recombinationProbability,
                mutationProbability);
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
    }

    @Override
    public void setupComparator() {
        comparator = new CompareMin();
    }

    @Override
    public void setupPopulationFactory() {
        populationFactory = new NewPopulationFactory() {

            @Override
            public NewPopulation createNew() {
                return new NewPopulation(populationSize, chromosomeFactory);
            }

            @Override
            public NewPopulation createCopy(NewPopulation population) {
                NewPopulation copy = new NewPopulation(populationSize);
                for (int i = 0; i < populationSize; i++) {
                    copy.set(i, chromosomeFactory.createCopy(population.get(i)));

                }
                return copy;
            }
        }; }

    @Override
    public void setupSelection() {
        selection = new NewTournamentSelection(seed, comparator, populationFactory);
    }
}
