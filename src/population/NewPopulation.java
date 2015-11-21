package population;

import chromosomes.NewBaseChromosome;
import chromosomes.NewBaseChromosomeFactory;
import comparators.BaseFitnessComparator;
import java.util.Arrays;

/**
 *
 * @author rich
 */
public class NewPopulation {

    private final NewBaseChromosome[] population;
    private float averageFitness = 0;

    public NewPopulation(int size) {
        population = new NewBaseChromosome[size];
    }

    public NewPopulation(int size, NewBaseChromosomeFactory chromosomeFactory) {
        population = new NewBaseChromosome[size];
        for (int i = 0; i < size; i++) {
            population[i] = chromosomeFactory.createNew();
        }
    }

    public NewBaseChromosome getBest(BaseFitnessComparator comparator, NewBaseChromosome best) {
        for (NewBaseChromosome chromosome : population) {
            if (comparator.compare(chromosome, best) > 0) {
                best = chromosome;
            }
        }
        return best;
    }

    public void set(int index, NewBaseChromosome c) {
        population[index] = c;
    }

    public NewBaseChromosome get(int index) {
        return population[index];
    }

    public float evaluate() {
        averageFitness = 0;
        for (NewBaseChromosome chromosome : population) {
            averageFitness += chromosome.evaluate();
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
