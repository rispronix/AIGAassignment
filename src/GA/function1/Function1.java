package GA.function1;

import GA.BaseFunction;
import chromosomes.BinaryChromosome;
import chromosomes.BaseChromosome;
import chromosomes.BaseChromosomeFactory;
import comparators.CompareMax;
import conversions.DecimalFromBinary;
import fitness.FitnessFunction;
import java.util.Random;
import mutation.BinaryMutation;
import population.Population;
import population.PopulationFactory;
import recombination.SinglePointCrossover;
import selection.TournamentSelection;

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
        ff = new FitnessFunction() {

            DecimalFromBinary dfb = new DecimalFromBinary();

            @Override
            public float calculate(BaseChromosome c) {

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
        chromosomeFactory = new BaseChromosomeFactory() {

            @Override
            public BaseChromosome createNew() {
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
        populationFactory = new PopulationFactory() {

            @Override
            public Population createNew() {
                return new Population(populationSize, chromosomeFactory);
            }

            @Override
            public Population createCopy(Population population) {
                Population newPopulation = new Population(populationSize);
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
        selection = new TournamentSelection(seed, comparator,
                populationFactory);
    }

    @Override
    public void setupRecombination() {
        recombination = new SinglePointCrossover(seed, populationFactory,
                recombinationProbability);
    }

    @Override
    public void setupMutation() {
        mutation = new BinaryMutation(seed, mutationProbability);
    }
}
