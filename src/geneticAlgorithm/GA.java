package geneticAlgorithm;

import java.util.Random;
import java.util.ArrayList;

import comparators.FitnessComparator;
import fitness.FitnessFunction;
import chromosomes.ChromosomeFactory;
import chromosomes.Chromosome;
import mutation.Mutation;
import population.Population;
import population.PopulationFactory;
import recombination.Recombination;
import selection.Selection;
import stats.RunStatistics;

/**
 *
 * @author rich
 */
public abstract class GA {

    protected Random seed;

    protected FitnessFunction ff;
    protected FitnessComparator comparator;
    protected ChromosomeFactory chromosomeFactory;
    protected PopulationFactory populationFactory;
    protected Selection selection;
    protected Recombination recombination;
    protected Mutation mutation;

    protected int generationCount = 50;//default parameter
    protected int populationSize = 50;//default parameter

    protected double recombinationProbability = 0.85;//default parameter
    protected double mutationProbability = 0.05;//default parameter

    protected Population population;
    protected Chromosome best;
    protected ArrayList<RunStatistics> stats;

    public GA(Random seed) {
        this.seed = seed;
    }

    public GA(Random seed,
            double recombinationProbability,
            double mutationProbability) {
        this.seed = seed;
        this.mutationProbability = mutationProbability;
        this.recombinationProbability = recombinationProbability;
    }

    public GA(Random seed,
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
        setupFitnessFunction();
        setupPopulationFactory();
        setupSelection();
        setupRecombination();
        setupMutation();

        population = populationFactory.createNew();
        population.evaluate();
        best = chromosomeFactory.createCopy(population.getBest(best));

        stats = new ArrayList();

        stats.add(new RunStatistics(0,
                population.averageFitness(),
                chromosomeFactory.createCopy(best),
                mutationProbability,
                recombinationProbability,
                populationSize));
        
        for (int i = 1; i < generationCount; i++) {
            population = selection.select(population);
            population = recombination.recombine(population);
            population = mutation.mutate(population);

            population.evaluate();
            best = population.getBest(best);
            stats.add(new RunStatistics(i,
                    population.averageFitness(),
                    chromosomeFactory.createCopy(best),
                    mutationProbability,
                    recombinationProbability,
                    populationSize));
        }
    }

    public double getRecombinationProbability() {
        return recombinationProbability;
    }

    public double getMutationProbability() {
        return mutationProbability;
    }

    public ArrayList<RunStatistics> getStats() {
        return stats;
    }

    public int getGenerationCount() {
        return generationCount;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public Chromosome getBest() {
        return best;
    }

    public void setRecombinationProbability(double recombinationProbability) {
        this.recombinationProbability = recombinationProbability;
    }

    public void setMutationProbability(double mutationProbability) {
        this.mutationProbability = mutationProbability;
    }

    public void resetBest() {
        best = chromosomeFactory.createNew();
        best.evaluate();
    }

    @Override
    public String toString() {
        return "GA{" + "generationCount=" + generationCount + 
                ", populationSize=" + populationSize + 
                ", recombinationProbability=" + recombinationProbability + 
                ", mutationProbability=" + mutationProbability + '}';
    }    
}
