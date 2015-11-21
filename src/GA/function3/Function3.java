package GA.function3;

import GA.BaseFunction;
import chromosomes.BaseChromosome;
import chromosomes.BaseChromosomeFactory;
import comparators.CompareMin;
import java.util.Random;
import mutation.FloatGaussianMutation;
import population.Population;
import population.PopulationFactory;
import recombination.SinglePointCrossover;
import selection.TournamentSelection;

/**
 *
 * @author rich
 */
public class Function3 extends BaseFunction {

    private final int chromosomeSize;

    public Function3(Random seed, int chromosomeSize) {
        super(seed);
        this.chromosomeSize = chromosomeSize;
    }

    public Function3(Random seed,
            double recombinationProbability,
            double mutationProbability,
            int chromosomeSize) {
        super(seed, recombinationProbability,
                mutationProbability);
        this.chromosomeSize = chromosomeSize;
    }

    public Function3(Random seed,
            int generationCount,
            int populationSize,
            double recombinationProbability,
            double mutationProbability,
            int chromosomeSize) {
        super(seed, generationCount,
                populationSize,
                recombinationProbability,
                mutationProbability);
        this.chromosomeSize = chromosomeSize;
    }

    @Override
    public void setupFitnessFunction() {
        ff = (BaseChromosome c) -> {
            float result = 10 * c.size();
            for (int i = 0; i < c.size(); i++) {
                float value = (float) c.getGene(i);
                result += value * value - 10 * Math.cos(
                        2 * Math.PI * value);
            }
            return result;
        };
    }

    @Override
    public void setupComparator() {
        comparator = new CompareMin();
    }

    @Override
    public void setupChromosomeFactory() {
        chromosomeFactory = new BaseChromosomeFactory() {

            @Override
            public BaseChromosome createNew() {
                return new BaseChromosome(chromosomeSize) {

                    @Override
                    public BaseChromosome initialise() {
                        genes = new Float[chromosomeSize];
                        for (int i = 0; i < chromosomeSize; i++) {
                            genes[i] = (seed.nextFloat()
                                    * 1024 - 512) / 100;
                        }
                        return this;
                    }

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
                    newPopulation.set(i, chromosomeFactory.createCopy(
                            population.get(i)));
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
        mutation = new FloatGaussianMutation(seed, mutationProbability);
    }

}
