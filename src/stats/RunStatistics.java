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

    public RunStatistics(int generation, double average, Chromosome best) {
        this.generation = generation;
        this.average = average;
        this.best = best;
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
}
