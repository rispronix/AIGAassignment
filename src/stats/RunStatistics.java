package stats;

import chromosomes.Chromosome;

/**
 *
 * @author r2-rowley
 */
public class RunStatistics {

    private final String title;
    private final int generation;
    private final double average, averageFitness, averagesd, fitnesssd, averageVar, fitnessVar;
    private final Chromosome best;
    private final double mutationProbability, recombinationProbability;
    private final int populationSize;

    public RunStatistics(int generation,
            double average,
            Chromosome best,
            double mutationProbability,
            double recombinationProbability,
            int populationSize,
            String title) {
        this.generation = generation;
        this.average = average;
        this.best = best;
        this.mutationProbability = mutationProbability;
        this.recombinationProbability = recombinationProbability;
        this.populationSize = populationSize;
        this.title = title;
        averageFitness = averagesd = fitnesssd = averageVar = fitnessVar = 0;
    }

    public RunStatistics(int generation,
            double average,
            double averageFitness,
            double averagesd,
            double fitnesssd,
            double fitnessVar,
            double averageVar,
            double mutationProbability,
            double recombinationProbability,
            int populationSize,
            String title) {
        this.generation = generation;
        this.average = average;
        this.averageFitness = averageFitness;
        this.mutationProbability = mutationProbability;
        this.recombinationProbability = recombinationProbability;
        this.populationSize = populationSize;
        this.title = title;
        this.averagesd = averagesd;
        this.fitnesssd = fitnesssd;
        this.averageVar = averageVar;
        this.fitnessVar = fitnessVar;
        best = null;
    }

    public double getAverageVar() {
        return averageVar;
    }

    public double getFitnessVar() {
        return fitnessVar;
    }

    public double getAveragesd() {
        return averagesd;
    }

    public double getFitnesssd() {
        return fitnesssd;
    }

    public double getAverageFitness() {
        return averageFitness;
    }

    public String getTitle() {
        return title;
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
        return "generation=" + generation
                + ", best=" + best.fitness()
                + ", average=" + average
                + ", best=" + best.toString() + '}';
    }
}
