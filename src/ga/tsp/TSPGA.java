package ga.tsp;

import chromosomes.Chromosome;
import chromosomes.ChromosomeFactory;
import comparators.CompareMin;
import fitness.FitnessFunction;
import ga.GA;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import mutation.PermutationMutation;
import population.Population;
import population.PopulationFactory;
import recombination.PermutationRecombination;
import selection.BestOfFour;

/**
 *
 * @author rich
 */
public class TSPGA extends GA {

    protected final int size;
    protected ArrayList<Point> points;
    protected double[][] distanceMatrix;

    public TSPGA(Random seed, int size) {
        super(seed);
        this.size = size;
        setupDistanceMatrix();
    }

    public TSPGA(Random seed,
            double recombinationProbability,
            double mutationProbability, int size) {
        super(seed, recombinationProbability,
                mutationProbability);
        this.size = size;
        setupDistanceMatrix();
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
        setupDistanceMatrix();
    }

    @Override
    public void setupFitnessFunction() {
        /*
         refer to precalculated distance matrix and sum one 
         location to the next and loop back to start node
         */

        ff = new FitnessFunction() {
            double sum;

            @Override
            public double calculate(Chromosome c) {
                sum = 0;
                for (int i = 0; i < size; i++) {
                    sum += distanceMatrix[(Integer) c.getGene(i)][(Integer) c.getGene((i + 1) % (size - 1))];
                }

                return sum;
            }
        };
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
        recombination = new PermutationRecombination(seed,
                populationFactory,
                recombinationProbability,
                chromosomeFactory);
    }

    @Override
    public void setupMutation() {
        mutation = new PermutationMutation(seed, mutationProbability);
    }

    private void setupDistanceMatrix() {
        points = new ArrayList();
        distanceMatrix = new double[size][size];
        double distance;
        double x, y;

        for (int i = 0; i < size; i++) {
            points.add(new Point(seed.nextInt(1000), seed.nextInt(1000)));
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                x = points.get(i).x - points.get(j).x;
                y = points.get(i).y - points.get(j).y;

                distance = Math.sqrt(x * x + y * y);
                distanceMatrix[i][j] = distance;
                distanceMatrix[j][i] = distance;
            }
        }
    }
}
