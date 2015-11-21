package GA.function3;

import chromosomes.BaseChromosome;
import chromosomes.BaseChromosomeFactory;
import comparators.CompareMin;
import comparators.BaseFitnessComparator;
import fitness.FitnessFunction;
import java.util.Random;
import mutation.BaseMutation;
import population.Population;
import population.PopulationFactory;
import recombination.Recombination;
import selection.TournamentSelection;

/**
 *
 * @author rich
 */
public class Function3 {

    private final Random seed;

    private FitnessFunction ff;
    private BaseFitnessComparator comparator;
    private BaseChromosomeFactory chromosomeFactory;
    private TournamentSelection selection;
    private Recombination recombination;
    private BaseMutation mutation;
    
    private int geneQty = 10;
    private int generationCount = 50;
    private int populationSize=50; 
    
    private double recombinationProbability=0.5;
    private double mutationProbability=0.05;
    
    private Population population;
    private BaseChromosome best;

    public Function3(Random seed) {
        this.seed = seed;
    }

    public Function3(Random seed, double mutationProbability, double recombinationProbability) {
        this.seed = seed;
        this.mutationProbability = mutationProbability;
        this.recombinationProbability = recombinationProbability;
    }

    public void run() {

        /*
         minimise ...
         */
        ff = (BaseChromosome c) -> {
            float result = 10 * c.size();
            for (int i = 0; i < c.size(); i++) {
                float value = (float) c.getGene(i);
                result += value * value - 10 * Math.cos(2 * Math.PI * value);
            }
            return result;
        };

        /*
         define comparator to prefer lower valued fitnesses
         */
        comparator = new CompareMin();

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
                            genes[i] = (seed.nextFloat() * 1024 - 512) / 100;
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
         Define factory to produce populations with predefined
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
         define selection, recombination, and mutation operators
         */
        selection = new TournamentSelection(seed, comparator, populationFactory);
        recombination = new Recombination(seed, populationFactory, recombinationProbability);
        mutation = new BaseMutation(seed, mutationProbability) {

            @Override
            public BaseChromosome mutateGene(BaseChromosome c) {
                int index = seed.nextInt(geneQty);
                c.setGene(index, (float) c.getGene(index) + (float) seed.nextGaussian());
                return c;
            }
        };

        /*
         create starting population
         */
        population = populationFactory.createNew();
        population.evaluate();
//        System.out.println("\nFunction3: floating point encoding");
//        System.out.println("starting population: " + population.toString());

        /*
         initialise placeholder chromosome for best candidate solution so far
         */
        best = chromosomeFactory.createNew();
        best.evaluate();

        // loop evolution
        for (int i = 0; i < generationCount; i++) {
            population = selection.select(population);
            population = recombination.singlepointCrossover(population);
            population = mutation.mutate(population);
            population.evaluate();
            best = population.getBest(comparator, best);
        }

        /*
         display results
         */
//        System.out.println("final population: " + population.toString());
//        System.out.println("best candidate solution: " + best.toString());
//        System.out.println("population average fitness: " + population.averageFitness());
//        return best.fitness();
    }

    public float getBestFitness() {
        return best.fitness();
    }
}
