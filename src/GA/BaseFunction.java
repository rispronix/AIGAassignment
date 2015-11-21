package GA;

import chromosomes.NewBaseChromosome;
import chromosomes.NewBaseChromosomeFactory;
import comparators.BaseFitnessComparator;
import fitness.NewFitnessFunction;
import java.util.Random;
import mutation.NewBaseMutation;
import population.NewPopulation;
import population.NewPopulationFactory;
import recombination.NewSinglePointCrossover;
import selection.NewTournamentSelection;

/**
 *
 * @author rich
 */
public abstract class BaseFunction {

    protected NewFitnessFunction ff;
    protected BaseFitnessComparator comparator;
    protected NewBaseChromosomeFactory chromosomeFactory;
    protected NewPopulationFactory populationFactory;
    protected NewTournamentSelection selection;
    protected NewSinglePointCrossover recombination;
    protected NewBaseMutation mutation;

    protected Random seed;

    protected int generationCount = 50;//default parameter
    protected int populationSize = 50;//default parameter

    protected double recombinationProbability = 0.5;//default parameter
    protected double mutationProbability = 0.05;//default parameter

    protected NewPopulation population;
    protected NewBaseChromosome best;

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

    public NewBaseChromosome getBest() {
        return best;
    }
}
