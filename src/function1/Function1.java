package function1;

import java.util.Random;
import chromosomes.BaseChromosome;
import chromosomes.BaseChromosomeFactory;
import comparators.CompareMax;
import comparators.FitnessComparator;
import fitness.FitnessFunction;
import population.Population;
import population.PopulationFactory;
import recombination.Recombination;
import selection.TournamentSelection;
import conversions.DecimalFromBinary;
import mutation.BaseMutation;

/**
 *
 * @author rich
 */
public class Function1 {

    private Random seed;
    private FitnessFunction ff;
    private final DecimalFromBinary dfb = new DecimalFromBinary();
    private BaseChromosomeFactory chromosomeFactory;
    private FitnessComparator comparator;// higher = better
    private TournamentSelection s;
    private Recombination r;
    private BaseMutation m;
    private final int geneQty = 8;// binary string to represent values 0 to 255
    private final int populationSize = 10;// arbitrary and modifiable 
    private double mutationProbability = 0.05;
    private double recombinationProbability = 0.5;
    private Population population;

    public Function1(Random seed) {
        this.seed = seed;        
    }

    public Function1(Random seed, double mutationProbability, double recombinationProbability) {
        this.seed = seed;        
        this.mutationProbability=mutationProbability;
        this.recombinationProbability=recombinationProbability;
    }

    public float run() {
        /*
         maximise x^2
         */
        ff = (BaseChromosome c) -> {
            return (float) Math.pow(dfb.decimalFromBinary(c.getGenes(0, geneQty)), 2);
        };

        /*
         define comparator to prefer higher valued fitnesses
         */
        comparator = new CompareMax();

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

        /*
         Define Factory to produce population with predefined
         population size and chromosome factory
         */
        PopulationFactory populationFactory = new PopulationFactory() {

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

        /*
         define selection, recombination, and mutation operations
         */
        s = new TournamentSelection(seed, comparator, populationFactory);
        r = new Recombination(seed, populationFactory, recombinationProbability);
        m = new BaseMutation(seed, mutationProbability) {

            @Override
            public BaseChromosome mutateGene(BaseChromosome c) {
                int index = seed.nextInt(geneQty);
                c.setGene(index, 1 - (int) c.getGene(index));
                return c;
            }
        };

        /*
         create starting population
         */
        population = populationFactory.createNew();
        population.evaluate();
        System.out.println("\nFunction1: binary encoding");
        System.out.println("starting population: " + population.toString());

        /*
         initialise placeholder chromosome for best candidate solution so far
         */
        BaseChromosome best = chromosomeFactory.createNew();
        best.evaluate();

        // loop evolution
        for (int i = 0; i < 100; i++) {
            population = s.select(population);
            population = r.singlepointCrossover(population);
            population = m.mutate(population);
            population.evaluate();
            best = population.getBest(comparator, best);
        }
        System.out.println("final population: " + population.toString());
        System.out.println("best candidate solution: " + best.toString());
        System.out.println("population average fitness: " + population.averageFitness());
        
        return best.fitness();
    }
}
