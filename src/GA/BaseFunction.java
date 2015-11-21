package GA;

import chromosomes.BaseChromosome;
import chromosomes.BaseChromosomeFactory;
import comparators.BaseFitnessComparator;
import fitness.FitnessFunction;
import java.util.Random;
import mutation.BaseMutation;
import population.Population;
import population.PopulationFactory;
import recombination.SinglePointCrossover;
import selection.TournamentSelection;

/**
 *
 * @author rich
 */
public abstract class BaseFunction {

    protected FitnessFunction ff;
    protected BaseFitnessComparator comparator;
    protected BaseChromosomeFactory chromosomeFactory;
    protected PopulationFactory populationFactory;
    protected TournamentSelection selection;
    protected SinglePointCrossover recombination;
    protected BaseMutation mutation;

    protected Random seed;

    protected int generationCount = 50;//default parameter
    protected int populationSize = 50;//default parameter

    protected double recombinationProbability = 0.5;//default parameter
    protected double mutationProbability = 0.05;//default parameter

    protected Population population;
    protected BaseChromosome best;

    public BaseFunction(Random seed) {
        this.seed = seed;
    }

    public BaseFunction(Random seed,
            double recombinationProbability,
            double mutationProbability) {
        this.seed = seed;
        this.mutationProbability = mutationProbability;
        this.recombinationProbability = recombinationProbability;
    }

    public BaseFunction(Random seed,
            int generationCount,
            int populationSize,
            double recombinationProbability,
            double mutationProbability) {
        this.seed = seed;
        this.generationCount = generationCount;
        this.populationSize = populationSize;
        this.mutationProbability = mutationProbability;
        this.recombinationProbability = recombinationProbability;
    }

    public abstract void setupFitnessFunction();

    public abstract void setupComparator();

    public abstract void setupChromosomeFactory();

    public abstract void setupPopulationFactory();

    public abstract void setupSelection();

    public abstract void setupRecombination();

    public abstract void setupMutation();

    public void run() {

        setupFitnessFunction();
        setupComparator();
        setupChromosomeFactory();
        setupPopulationFactory();
        setupSelection();
        setupRecombination();
        setupMutation();

        /*
         create starting population
         */
        population = populationFactory.createNew();
        population.evaluate();
//        System.out.println("population: "+population.toString());
        /*
         initialise placeholder chromosome for best candidate solution so far
         */
        best = chromosomeFactory.createNew();
        best.evaluate();

        System.out.println("\nBase Function class");
        System.out.println("starting population: " + population.toString());
        // loop evolution
        for (int i = 0; i < generationCount; i++) {
            population = selection.select(population);
            population = recombination.recombine(population);
            population = mutation.mutate(population);

            population.evaluate();
            best = population.getBest(comparator, best);
        }
        System.out.println("final population: " + population.toString());
        System.out.println("best candidate solution: " + best.toString());
        System.out.println("population average fitness: " + 
                population.averageFitness());

    }

    public BaseChromosome getBest() {
        return best;
    }
}
