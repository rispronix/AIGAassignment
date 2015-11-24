package ga.function1;

import ga.GA;
import chromosomes.BinaryChromosome;
import chromosomes.Chromosome;
import chromosomes.ChromosomeFactory;
import comparators.CompareMax;
import conversions.DecimalFromBinary;
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
public class Function1 extends GA {

    private final int chromosomeSize = 8;//hardcoded as max value 255 is 8 bits

    public Function1(Random seed) {
        super(seed);
        title = "Function1";
    }

    public Function1(Random seed,
            double mutationProbability,
            double recombinationProbability) {
        super(seed, mutationProbability,
                recombinationProbability);
        title = "Function1";
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
        title = "Function1";
    }

    @Override
    public void setupFitnessFunction() {
        DecimalFromBinary dfb = new DecimalFromBinary();
        ff = (Chromosome c) -> Math.pow(dfb.decimalFromBinary(
                c.getGenes(0, c.size())), 2);
    }

    @Override
    public void setupComparator() {
        comparator = new CompareMax();
    }

    @Override
    public void setupChromosomeFactory() {
        chromosomeFactory = new ChromosomeFactory() {

            @Override
            public Chromosome createNew() {
                return new BinaryChromosome(seed, chromosomeSize) {

                    @Override
                    public double evaluate() {
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
                return new Population(populationSize,
                        chromosomeFactory, comparator);
            }

            @Override
            public Population createCopy(Population population) {
                Population newPopulation = new Population(populationSize,
                        comparator);
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
