package GA;

import chromosomes.BaseChromosome;
import chromosomes.BaseChromosomeFactory;
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
public abstract class BaseFunction {

    protected Random seed;

    protected FitnessFunction ff;
    protected BaseFitnessComparator comparator;
    protected BaseChromosomeFactory chromosomeFactory;
    protected PopulationFactory populationFactory;
    protected TournamentSelection selection;
    protected Recombination recombination;
    protected BaseMutation mutation;
    
    protected int geneQty;
    protected int generationCount;
    protected int populationSize; 
    
    protected double recombinationProbability;
    protected double mutationProbability;
    
    protected Population population;
    protected BaseChromosome best;

    public BaseFunction(Random seed) {
        this.seed = seed;
    }

    public BaseFunction(Random seed, double mutationProbability, double recombinationProbability) {
        this.seed = seed;
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
        /*
         create starting population
         */
        population = populationFactory.createNew();
        population.evaluate();

        /*
         initialise placeholder chromosome for best candidate solution so far
         */
        best = chromosomeFactory.createNew();
        best.evaluate();

        // loop evolution
        for (int i = 0; i < generationCount; i++) {
            population = selection.select(population);
            population = recombination.recombine(population);
            population = mutation.mutate(population);
            
            population.evaluate();
            best = population.getBest(comparator, best);
        }

    }

    public BaseChromosome getBest() {
        return best;
    }
}