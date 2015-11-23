package population;

import chromosomes.ChromosomeFactory;
import chromosomes.Chromosome;
import comparators.FitnessComparator;
import java.util.Arrays;

/**
 *
 * @author rich
 */
public class Population {

    private final Chromosome[] population;
    private final FitnessComparator comparator;
    private double averageFitness = 0;

    public Population(int size, FitnessComparator comparator) {
        population = new Chromosome[size];
        this.comparator = comparator;
    }

    public Population(int size, ChromosomeFactory chromosomeFactory,
            FitnessComparator comparator) {
        population = new Chromosome[size];
        this.comparator = comparator;
        for (int i = 0; i < size; i++) {
            population[i] = chromosomeFactory.createNew();
        }
    }

    public Chromosome getBest(Chromosome best) {
        if (best == null) {
            best = population[0];
        }
        for (Chromosome chromosome : population) {
            if (comparator.compare(chromosome, best) > 0) {
                best = chromosome;
            }
        }
        return best;
    }

    public void set(int index, Chromosome c) {
        population[index] = c;
    }

    public Chromosome get(int index) {
        return population[index];
    }

    public double evaluate() {
        averageFitness = 0;
        for (Chromosome chromosome : population) {
            averageFitness += chromosome.evaluate();
        }
        return averageFitness = averageFitness / population.length;
    }

    public double averageFitness() {
        return averageFitness;
    }

    public int size() {
        return population.length;
    }

    @Override
    public String toString() {
        return "Population{" + "population=" + Arrays.toString(population)
                + String.format(", averageFitness= %.2f}", averageFitness);
    }

}
