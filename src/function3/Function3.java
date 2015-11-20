package function3;

import chromosomes.BaseChromosome;
import chromosomes.BaseChromosomeFactory;
import comparators.CompareMin;
import comparators.FitnessComparator;
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

    private Random seed;

    private FitnessFunction ff;
    private FitnessComparator comparator;
    private BaseChromosomeFactory chromosomeFactory;
    private TournamentSelection s;
    private Recombination r;
    private BaseMutation m;
    private int generationCount = 10;
    private int geneQty = 10;// two float typed variables
    private int populationSize = 10;// arbitrary and modifiable 
    private double recombinationProbability = 0.5;
    private double mutationProbability = 0.05;
    private Population population;
    private BaseChromosome best;

    public Function3(Random seed) {
        this.seed = seed;
        run();
    }

    public Function3(Random seed, double mutationProbability, double recombinationProbability) {
        this.seed=seed;
        this.mutationProbability = mutationProbability;
        this.recombinationProbability = recombinationProbability;
        System.out.println("Mutation: "+mutationProbability +"\tRecombination: "+recombinationProbability);
//        generationCount = 1000;
//        geneQty = 20;// two float typed variables
//        populationSize = 1000;// arbitrary and modifiable 
//        run();
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
                    public float calculateFitness() {
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
        s = new TournamentSelection(seed, comparator, populationFactory);
        r = new Recombination(seed, populationFactory, recombinationProbability);
        m = new BaseMutation(seed, mutationProbability) {

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
        population.calculateAverageFitness();
//        System.out.println("\nFunction3: floating point encoding");
//        System.out.println("starting population: " + population.toString());

        /*
         initialise placeholder chromosome for best candidate solution so far
         */
        best = chromosomeFactory.createNew();
        best.calculateFitness();

        // loop evolution
        for (int i = 0; i < generationCount; i++) {
            population = s.select(population);
            population = r.singlepointCrossover(population);
            population = m.mutate(population);
            population.calculateAverageFitness();
            best = population.findBest(comparator, best);
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
