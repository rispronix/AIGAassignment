package geneticAlgorithm.parameterTuning;

import chromosomes.Chromosome;
import chromosomes.ChromosomeFactory;
import comparators.CompareMin;
import geneticAlgorithm.GA;
import geneticAlgorithm.function3.Function3BestOfFourSelection;
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

    public ProbabilitySearch(Random seed,
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
    public void setupFitnessFunction() {
        ff = (Chromosome c) -> {
            GA ga = new Function3BestOfFourSelection(seed,
                    (double) c.getGene(0),
                    (double) c.getGene(1),
                    10);
            ga.run();
            return ga.getBest().fitness();
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
//                        for (int i = 0; i < chromosomeSize; i++) {
//                            genes[i] = seed.nextDouble();
//                        }

                        genes[0] = 1.0;//recombination
                        genes[1] = 0.1;//mutation

                        return this;
                    }

                    @Override
                    public float evaluate() {
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
