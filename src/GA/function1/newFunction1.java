package GA.function1;

import GA.BaseFunction;
import chromosomes.BaseChromosome;
import chromosomes.BaseChromosomeFactory;
import comparators.CompareMax;
import conversions.DecimalFromBinary;
import java.util.Random;
import population.Population;
import population.PopulationFactory;

/**
 *
 * @author rich
 */
public class newFunction1 extends BaseFunction {

    DecimalFromBinary dfb = new DecimalFromBinary();

    public newFunction1(Random seed) {
        super(seed);
    }

    public newFunction1(Random seed,
            double mutationProbability,
            double recombinationProbability) {
        super(seed, mutationProbability, recombinationProbability);
    }

    @Override
    public void setupFitnessFunction() {
        /*
         maximise x^2
         */
        ff = (BaseChromosome c) -> {
            return (float) Math.pow(dfb.decimalFromBinary(
                    c.getGenes(0, geneQty)), 2);
        };
    }

    @Override
    public void setupComparator() {
        /*
         define comparator to prefer higher valued fitnesses
         */
        comparator = new CompareMax();
    }

    @Override
    public void setupChromosomeFactory() {
        /*
         define factory to produce binary string chromosomes with predefined
         length and fitness function
         */
        chromosomeFactory = new BaseChromosomeFactory() {

            @Override
            public BaseChromosome createNew() {
                return new BaseChromosome() {

                    @Override
                    public BaseChromosome initialise() {
                        genes = new Integer[geneQty];
                        for (int i = 0; i < geneQty; i++) {
                            genes[i] = seed.nextInt(2);
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
        /*
         Define Factory to produce population with predefined
         population size and chromosome factory
         */
        populationFactory = new PopulationFactory() {

            @Override
            public Population createNew() {
                return new Population(populationSize, chromosomeFactory);
            }

            @Override
            public Population createCopy(Population population) {
                Population newPopulation = new Population(populationSize);
                for (int i = 0; i < populationSize; i++) {
                    newPopulation.set(i, chromosomeFactory.createCopy(population.get(i)));

                }
                return newPopulation;
            }
        };
    }

    @Override
    public void setupSelection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setupRecombination() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setupMutation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
