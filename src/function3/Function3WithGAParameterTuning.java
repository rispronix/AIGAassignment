package function3;

import chromosomes.BaseChromosome;
import chromosomes.BaseChromosomeFactory;
import comparators.CompareMin;
import comparators.FitnessComparator;
import fitness.FitnessFunction;
import function1.Function1;
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
public class Function3WithGAParameterTuning {

    private Random seed=new Random();

    private final FitnessFunction ff;
    private final FitnessComparator comparator;
    private final BaseChromosomeFactory chromosomeFactory;
    private final TournamentSelection s;
    private final Recombination r;
    private final BaseMutation m;
    private final int generationCount = 100;
    private final int geneQty = 2;// two float typed variables
    private final int populationSize = 100;// arbitrary and modifiable 
    private final double recombinationProbability = 1;
    private final double mutationProbability = 0.5;
    private Population population;
    BaseChromosome best;

    public Function3WithGAParameterTuning() {

        /*
         minimise fitness of function1 with GA searching for parameters
         */
        ff = (BaseChromosome c) -> {
            Function3 f = new Function3(seed, (double) c.getGene(0), (double) c.getGene(1));
            f.run();
            return f.getBestFitness();
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
                        genes = new Double[geneQty];
//                        for (int i = 0; i < geneQty; i++) {
//                            genes[i] = seed.nextDouble();
//                        }
                        genes[0]=0.05;
                        genes[1]=0.5;
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
        s = new TournamentSelection(seed, comparator, populationFactory);
        r = new Recombination(seed, populationFactory, recombinationProbability);
        m = new BaseMutation(seed, mutationProbability) {

            @Override
            public BaseChromosome mutateGene(BaseChromosome c) {
                int index = seed.nextInt(geneQty);
                c.setGene(index, (double) c.getGene(index) + seed.nextGaussian() / 5);
                return c;
            }
        };

        /*
         create starting population
         */
        population = populationFactory.createNew();
        population.evaluate();
        System.out.println("\nFunction1 with GA parameter tuning: floating point encoding");
        System.out.println("starting population: " + population.toString());

        /*
         initialise placeholder chromosome for best candidate solution so far
         */
        best = chromosomeFactory.createNew();
        best.evaluate();

        // loop evolution
        for (int i = 0; i < generationCount; i++) {
            System.out.println("///////////// searching for optimal parameters //////////////");
            population = s.select(population);
            population = r.singlepointCrossover(population);
            population = m.mutate(population);
            population.evaluate();
            best = population.getBest(comparator, best);
        }

        /*
         display results
         */
        System.out.println("final population: " + population.toString());
        System.out.println("best candidate solution: " + best.toString());
        System.out.println("population average fitness: " + population.averageFitness());
    }

    public float getBestFitness() {
        return best.fitness();
    }
    public double getMutationProbability(){
        return mutationProbability;
    }
    public double getRecombinationProbability(){
        return recombinationProbability;
    }
    public Function3 getBest(){
        return best.
    }
}
