package ga.tsp;

import chromosomes.Chromosome;
import chromosomes.ChromosomeFactory;
import comparators.CompareMin;
import ga.GA;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import population.Population;
import population.PopulationFactory;
import selection.BestOfFour;

/**
 *
 * @author rich
 */
public class TSPGA extends GA {

    protected final int size;

    public TSPGA(Random seed, int size) {
        super(seed);
        this.size = size;
    }

    public TSPGA(Random seed,
            double recombinationProbability,
            double mutationProbability, int size) {
        super(seed, recombinationProbability,
                mutationProbability);
        this.size = size;
    }

    public TSPGA(Random seed,
            int generationCount,
            int populationSize,
            double recombinationProbability,
            double mutationProbability, int size) {
        super(seed, generationCount,
                populationSize,
                recombinationProbability,
                mutationProbability);
        this.size = size;
    }

    @Override
    public void setupFitnessFunction() {
        /*
         refer to precalculated distance matrix and sum one 
         location to the next and loop back to start node
         */

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setupComparator() {
        comparator = new CompareMin();
    }

    @Override
    public void setupChromosomeFactory() {
        chromosomeFactory = new ChromosomeFactory() {

            @Override
            public Chromosome createNew() {
                return new Chromosome(size) {

                    @Override
                    public Chromosome initialise() {
                        genes = new Integer[size];
                        /*
                         random permutation of 0..size
                         */
                        for (int i = 0; i < size; i++) {
                            genes[i] = i;
                        }
                        Collections.shuffle(Arrays.asList(genes));
                        return this;
                    }

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
        selection = new BestOfFour(seed, comparator, populationFactory);
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
