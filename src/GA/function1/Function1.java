package GA.function1;

import GA.BaseFunction;
import chromosomes.BinaryChromosome;
import chromosomes.NewBaseChromosome;
import chromosomes.NewBaseChromosomeFactory;
import comparators.CompareMax;
import conversions.DecimalFromBinary;
import fitness.NewFitnessFunction;
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
public class Function1 extends BaseFunction {

    private final int chromosomeSize = 8;//hardcoded as max value 255 is 8 bits

    public Function1(Random seed) {
        super(seed);
    }

    public Function1(Random seed,
            double mutationProbability,
            double recombinationProbability) {
        super(seed, mutationProbability,
                recombinationProbability);
    }

    public Function1(Random seed,
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
        ff = new NewFitnessFunction() {

            DecimalFromBinary dfb = new DecimalFromBinary();

            @Override
            public float calculate(NewBaseChromosome c) {

                return (float) Math.pow(dfb.decimalFromBinary(
                        c.getGenes(0, c.size())), 2);
            }
        };
    }

    @Override
    public void setupComparator() {
        comparator = new CompareMax();
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
