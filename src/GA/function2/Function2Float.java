package GA.function2;

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
 * @author r2-rowley
 */
public class Function2Float {

    private final FitnessFunction ff;
    private final BaseFitnessComparator comparator;
    private final BaseChromosomeFactory chromosomeFactory;
    private final TournamentSelection s;
    private final Recombination r;
    private final BaseMutation m;
    private final int generationCount = 100;
    private final int geneQty = 2;// two float typed variables
    private final int populationSize = 10;// arbitrary and modifiable 
    private final double recombinationProbability = 0.5;
    private final double mutationProbability = 0.05;
    private Population population;

    public Function2Float(Random seed) {

        /*
         minimise x^2 + y^2
         */
        ff = (BaseChromosome c) -> {
            float x = (float) c.getGene(0);
            float y = (float) c.getGene(1);
            return (float) (0.26 * (x * x * y * y) - 0.48 * x * y);
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
                            genes[i] = seed.nextFloat() * 31 - 15;
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
        s = new TournamentSelection(seed, comparator, populationFactory);
        r = new Recombination(seed, populationFactory, recombinationProbability);
        m = new BaseMutation(seed, mutationProbability) {

            @Override
            public BaseChromosome mutateGene(BaseChromosome c) {
                int index = seed.nextInt(geneQty);
                c.setGene(index, (float) c.getGene(index) + (float) seed.nextGaussian() / 2);
                return c;
            }
        };

        /*
         create starting population
         */
        population = populationFactory.createNew();
        population.evaluate();
        System.out.println("\nFunction2: floating point encoding");
        System.out.println("starting population: " + population.toString());

        /*
         initialise placeholder chromosome for best candidate solution so far
         */
        BaseChromosome best = chromosomeFactory.createNew();
        best.evaluate();

        // loop evolution
        for (int i = 0; i < generationCount; i++) {
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
}
