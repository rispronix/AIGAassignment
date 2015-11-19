/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function2;

import chromosomes.BaseChromosome;
import chromosomes.BaseChromosomeFactory;
import comparators.CompareMin;
import comparators.FitnessComparator;
import conversions.DecimalFromBinary;
import fitness.FitnessFunction;
import java.util.Random;
import mutation.BaseMutation;
import population.Population;
import population.PopulationFactory;
import recombination.Recombination;
import selection.TournamentSelection;

/**
 *
 * @author r2-rowley
 */
public class Function2Float {

        private final Random seed = new Random(System.currentTimeMillis());
//    private final Random seed = new Random(1);//debug
    private final FitnessFunction ff;
    private final int geneQty = 2;// two float typed variables
    private final BaseChromosomeFactory chromosomeFactory;
    private Population population;
    private final int populationSize = 10;// arbitrary and modifiable 
    private final FitnessComparator comparator;// lower = better
    private final TournamentSelection s;
    private final Recombination r;
    private final double recombinationProbability = 0.5;
    private final BaseMutation m;
    private final double mutationProbability = 0.05;

    public Function2Float() {
        /*
         maximise x^2
         */
        DecimalFromBinary dfb = new DecimalFromBinary();
        ff = (BaseChromosome c) -> {
            float x =(float) c.getGene(0);
            float y = (float) c.getGene(1);
            return (float) (0.26 * (x * x * y * y) - 0.48 * x * y);
        };

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
                        genes = new Float[geneQty];
                        for (int i = 0; i < geneQty; i++) {
                            genes[i] = seed.nextFloat() * 31 - 15;
                        }
                        return this;
                    }

                    @Override
                    public float calculateFitness() {
                        return fitness = ff.calculate(this);
                    }
                }.initialise();
            }
        };

//        BaseChromosome chromosome1 = chromosomeFactory.createNew();
//        chromosome1.calculateFitness();
//        System.out.println(chromosome1.toString());
//        population = new Population(populationSize, chromosomeFactory);
//        population.calculateAverageFitness();
//        System.out.println(population.toString());
        comparator = new CompareMin();
//        System.out.println(comparator.compare(population.getElement(0), population.getElement(1)));

        PopulationFactory populationFactory = new PopulationFactory() {

            @Override
            public Population createNew() {
                return new Population(populationSize, chromosomeFactory);
            }

            @Override
            public Population createCopy(Population population) {
                Population newPopulation = new Population(populationSize);
                for (int i = 0; i < populationSize; i++) {
                    newPopulation.set(i, chromosomeFactory.createCopy(population.getElement(i)));

                }
                return newPopulation;
            }
        };

        population = populationFactory.createNew();
        population.calculateAverageFitness();
        System.out.println(population.toString());

        s = new TournamentSelection(seed, comparator, populationFactory);
//        population = s.select(population);
//        population.calculateAverageFitness();
//        System.out.println(population.toString());

        r = new Recombination(seed, populationFactory, recombinationProbability);
//        population = r.singlepointCrossover(population);
//        population.calculateAverageFitness();
//        System.out.println(population.toString());

        m = new BaseMutation(seed, mutationProbability) {

            @Override
            public BaseChromosome mutateGene(BaseChromosome c) {
                int index = seed.nextInt(geneQty);
                c.setGene(index, (float)c.getGene(index) + (float)seed.nextGaussian()/2);
                return c;
            }
        };

//        population = m.mutate(population);
//        population.calculateAverageFitness();
//        System.out.println(population.toString());

        BaseChromosome best = chromosomeFactory.createNew();
        best.calculateFitness();
        // loop evolution
        for (int i = 0; i < 100; i++) {
            population = s.select(population);
            population = r.singlepointCrossover(population);
            population = m.mutate(population);
            population.calculateAverageFitness();
            best = population.findBest(comparator, best);
        }
        System.out.println(population.toString());
        System.out.println(best.toString());
        System.out.println("average: " + population.averageFitness());
    }
}
