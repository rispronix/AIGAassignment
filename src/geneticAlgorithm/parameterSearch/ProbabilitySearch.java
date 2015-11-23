package geneticAlgorithm.parameterSearch;

import chromosomes.Chromosome;
import chromosomes.ChromosomeFactory;
import comparators.CompareMin;
import geneticAlgorithm.GA;
import java.util.Random;
import mutation.ProbabilityMutationDouble;
import population.Population;
import population.PopulationFactory;
import recombination.DoubleMerge;
import selection.BestOfFour;

/**
 *
 * @author rich
 */
public class ProbabilitySearch extends GA {

    protected int chromosomeSize = 2;
    protected GA function;

    public ProbabilitySearch(Random seed, GA function) {
        super(seed);
        this.function = function;
    }

    public ProbabilitySearch(Random seed,
            double recombinationProbability,
            double mutationProbability,
            GA function) {
        super(seed, recombinationProbability, mutationProbability);
        this.function = function;
    }

    public ProbabilitySearch(Random seed,
            int generationCount,
            int populationSize,
            double recombinationProbability,
            double mutationProbability,
            GA function) {
        super(seed, generationCount,
                populationSize,
                recombinationProbability,
                mutationProbability);
        this.function = function;
    }

    @Override
    public void setupFitnessFunction() {
        ff = (Chromosome c) -> {
            function.setRecombinationProbability((double)c.getGene(0));
            function.setMutationProbability((double)c.getGene(1));
            function.run();
            double fitness = function.getBest().fitness();
            function.resetBest();
            return fitness;
        };
    }
    
    @Override
    public void setupChromosomeFactory() {
        chromosomeFactory = new ChromosomeFactory() {

            @Override
            public Chromosome createNew() {
                return new Chromosome(chromosomeSize) {

                    @Override
                    public Chromosome initialise() {
                        genes = new Double[chromosomeSize];
                        for (int i = 0; i < chromosomeSize; i++) {
                            genes[i] = seed.nextDouble();
                        }
                        return this;
                    }

                    @Override
                    public double evaluate() {
                        fitness = ff.calculate(this);
                        return fitness;
                    }
                }.initialise();
            }
        };
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
                        chromosomeFactory,
                        comparator);
            }

            @Override
            public Population createCopy(Population population) {
                Population newPopulation = new Population(populationSize,
                        comparator);
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
        selection = new BestOfFour(seed,
                comparator,
                populationFactory);
    }

    @Override
    public void setupRecombination() {
        recombination = new DoubleMerge(seed,
                populationFactory,
                recombinationProbability,
                chromosomeFactory);
    }

    @Override
    public void setupMutation() {
        mutation = new ProbabilityMutationDouble(seed, mutationProbability);
    }
}
