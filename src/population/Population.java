package population;

import chromosomes.BaseChromosome;
import chromosomes.BaseChromosomeFactory;
import comparators.FitnessComparator;
import java.util.Arrays;

/**
 *
 * @author rich
 */
public class Population {

    private final BaseChromosome[] population;
    private float averageFitness = 0;

    public Population(int size) {
        population = new BaseChromosome[size];
    }

    public Population(int size, BaseChromosomeFactory chromosomeFactory) {
        population = new BaseChromosome[size];
        for (int i = 0; i < size; i++) {
            population[i] = chromosomeFactory.createNew();
        }
    }

    public BaseChromosome findBest(FitnessComparator comparator, BaseChromosome best) {
        for (int i = 0; i < population.length; i++) {
            if (comparator.compare(population[i], best) > 0) {
                best = population[i];
            }
        }
        return best;
    }

    public void set(int index, BaseChromosome c) {
        population[index] = c;
    }

    public BaseChromosome get(int index) {
        return population[index];
    }

    public float calculateAverageFitness() {
        averageFitness = 0;
        for (BaseChromosome chromosome : population) {
            averageFitness += chromosome.calculateFitness();
        }
        return averageFitness = averageFitness / population.length;
    }

    public float averageFitness() {
        return averageFitness;
    }

    public int size() {
        return population.length;
    }

    @Override
    public String toString() {
        return "Population{" + "population=" + Arrays.toString(population) + ", averageFitness=" + averageFitness + '}';
    }

}
