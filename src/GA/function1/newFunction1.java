package GA.function1;

import GA.BaseFunction;
import chromosomes.BaseChromosome;
import chromosomes.BaseChromosomeFactory;
import chromosomes.BinaryChromosome;
import comparators.CompareMax;
import java.util.Random;
import population.Population;
import population.PopulationFactory;

/**
 *
 * @author rich
 */
public class newFunction1 extends BaseFunction {

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
        ff = new Function1FitnessFunction();
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
                return new BinaryChromosome(seed, 8).initialise();
            }

            @Override
            public BaseChromosome createCopy(BaseChromosome c) {
                return new BinaryChromosome(c);
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
