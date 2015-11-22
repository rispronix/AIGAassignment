package stats;

import chromosomes.Chromosome;

/**
 *
 * @author r2-rowley
 */
public class RunStatistics {

    private final int generation;
    private final double average;
    private final Chromosome best;
    private final double mutationProbability;
    private final double recombinationProbability;
    private final int populationSize;

    public RunStatistics(int generation, 
            double average,
            Chromosome best,
            double mutationProbability,
            double recombinationProbability, 
            int populationSize) {
        this.generation = generation;
        this.average = average;
        this.best = best;
        this.mutationProbability = mutationProbability;
        this.recombinationProbability = recombinationProbability;
        this.populationSize = populationSize;
//        System.out.println(toString());
    }
    
    public Chromosome getBest() {
        return best;
    }

    public int getGeneration() {
        return generation;
    }

    public double getAverage() {
        return average;
    }

    public double getMutationProbability() {
        return mutationProbability;
    }

    public double getRecombinationProbability() {
        return recombinationProbability;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    @Override
    public String toString() {
        return "RunStatistics{" + "generation=" + generation + ", average=" + average + ", best fitness=" + best.fitness() + ", mutationProbability=" + mutationProbability + ", recombinationProbability=" + recombinationProbability + ", populationSize=" + populationSize + '}';
    }
}
