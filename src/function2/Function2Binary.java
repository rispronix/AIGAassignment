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
 * @author rich
 */
public class Function2Binary {

    private final FitnessFunction ff;
    private final DecimalFromBinary dfb = new DecimalFromBinary();
    private final int geneQty = 12;// binary string to represent two values -15 to 15
    private final BaseChromosomeFactory chromosomeFactory;
    private Population population;
    private final int populationSize = 10;// arbitrary and modifiable 
    private final FitnessComparator comparator;// lower = better
    private final TournamentSelection s;
    private final Recombination r;
    private final double recombinationProbability = 0.5;
    private final BaseMutation m;
    private final double mutationProbability = 0.05;

    public Function2Binary(Random seed) {
        /*
         minimise x^2 + y^2
         */
        ff = (BaseChromosome c) -> {
            float x = dfb.decimalFromBinary(c.getGenes(0, 5)) - 15;
            float y = dfb.decimalFromBinary(c.getGenes(5, c.size())) - 15;
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
                        genes = new Integer[geneQty];
                        for (int i = 0; i < geneQty; i++) {
                            genes[i] = seed.nextInt(2);
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
         Define factory to produce population with predefined
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
                c.setGene(index, 1 - (int) c.getGene(index));
                return c;
            }
        };

        /*
         create starting population
         */
        population = populationFactory.createNew();
        population.calculateAverageFitness();
        System.out.println("\nFunction2: binary encoding");
        System.out.println("starting population:" + population.toString());

        /*
         initialise placeholder chromosome for best candidate solution so far
         */
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
        System.out.println("final population: " + population.toString());
        System.out.println("best candidate solution: " + best.toString());
        System.out.println("population average fitness: " + population.averageFitness());
    }
}
