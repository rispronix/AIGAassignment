package GA.function1;

import GA.BaseFunction;
import chromosomes.BinaryChromosome;
import chromosomes.NewBaseChromosome;
import chromosomes.NewBaseChromosomeFactory;
import comparators.NewCompareMax;
import java.util.Random;
import mutation.NewBinaryMutation;
import population.NewPopulation;
import population.NewPopulationFactory;
import recombination.NewSinglePointCrossover;
import selection.NewTournamentSelection;

/**
 *
 * @author rich
 */
public class NewFunction1 extends BaseFunction {

    int chromosomeSize = 8;//hardcoded as max value 255 is always 8 bits

    public NewFunction1(Random seed) {
        super(seed);
    }

    public NewFunction1(Random seed,
            double mutationProbability,
            double recombinationProbability) {
        super(seed, mutationProbability, 
                recombinationProbability);
    }

    public NewFunction1(Random seed,
            int generationCount,
            int populationSize,
            double mutationProbability,
            double recombinationProbability) {
        super(seed, generationCount,
                populationSize, 
                mutationProbability, 
                recombinationProbability);
    }

    @Override
    public void setupFitnessFunction() {
        ff = new Function1FitnessFunction();
    }

    @Override
    public void setupComparator() {
        comparator = new NewCompareMax();
    }

    @Override
    public void setupChromosomeFactory() {
        chromosomeFactory = new NewBaseChromosomeFactory() {

            @Override
            public NewBaseChromosome createNew() {
                return new BinaryChromosome(seed, chromosomeSize) {

                    @Override
                    public float evaluate() {
                        return fitness = ff.calculate(this);
                    }
                }.initialise();
            }
        };
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
                NewPopulation newPopulation = new NewPopulation(populationSize);
                for (int i = 0; i < populationSize; i++) {
                    newPopulation.set(i, 
                            chromosomeFactory.createCopy(population.get(i)));

                }
                return newPopulation;
            }
        };
    }

    @Override
    public void setupSelection() {
        selection = new NewTournamentSelection(seed, comparator, 
                populationFactory);
    }

    @Override
    public void setupRecombination() {
        recombination = new NewSinglePointCrossover(seed, populationFactory, 
                recombinationProbability);
    }

    @Override
    public void setupMutation() {
        mutation = new NewBinaryMutation(seed, mutationProbability);
    }
}